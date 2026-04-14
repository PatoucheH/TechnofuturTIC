package be.bstorm.lib.config;

import be.bstorm.lib.vault.config.VaultConfig;
import be.bstorm.lib.vault.config.VaultResponse;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public interface ConfigParser {

    static Config readConfig() throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream inputStream = ConfigParser.class.getClassLoader().getResourceAsStream("application.json")) {
            if (inputStream == null) {
                throw new IOException("application.json not found in resources");
            }

            Config config = objectMapper.readValue(inputStream, Config.class);

            if (config.getVault() == null) {
                return config;
            }

            Map<String, Object> vaultData = fetchVaultData(config, objectMapper);
            return replacePlaceholders(config, vaultData, objectMapper);
        }
    }

    private static Map<String, Object> fetchVaultData(Config config, ObjectMapper objectMapper)
            throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(config.getVault().getUrl()))
                .header("Accept", "application/json")
                .header("X-Vault-Token", config.getVault().getToken())
                .build();

        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8)
        );

        if (response.statusCode() / 100 != 2) {
            throw new IOException("Vault request failed with status " + response.statusCode());
        }

        VaultResponse vaultConfig = objectMapper.readValue(response.body(), VaultResponse.class);

        if (vaultConfig.getData() == null || vaultConfig.getData().get("data") == null) {
            throw new IOException("Invalid Vault response: missing data.data");
        }

        return objectMapper.convertValue(
                vaultConfig.getData().get("data"),
                new TypeReference<Map<String, Object>>() {}
        );
    }

    private static Config replacePlaceholders(Config config, Map<String, Object> vaultData, ObjectMapper objectMapper) {
        Map<String, Object> configMap = objectMapper.convertValue(
                config,
                new TypeReference<Map<String, Object>>() {}
        );

        Object resolved = resolveValue(configMap, vaultData);

        return objectMapper.convertValue(resolved, Config.class);
    }

    private static Object resolveValue(Object value, Map<String, Object> vaultData) {
        if (value instanceof Map<?, ?> map) {
            Map<String, Object> resolved = new HashMap<>();
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                resolved.put(String.valueOf(entry.getKey()), resolveValue(entry.getValue(), vaultData));
            }
            return resolved;
        }

        if (value instanceof Iterable<?> iterable) {
            java.util.List<Object> resolved = new java.util.ArrayList<>();
            for (Object item : iterable) {
                resolved.add(resolveValue(item, vaultData));
            }
            return resolved;
        }

        if (value instanceof String str) {
            return resolvePlaceholder(str, vaultData);
        }

        return value;
    }

    private static Object resolvePlaceholder(String value, Map<String, Object> vaultData) {
        if (!value.startsWith("${vault:") || !value.endsWith("}")) {
            return value;
        }

        String path = value.substring("${vault:".length(), value.length() - 1);
        Object resolved = getByPath(vaultData, path);

        if (resolved == null) {
            throw new IllegalArgumentException("Vault placeholder not found: " + value);
        }

        return resolved;
    }

    private static Object getByPath(Map<String, Object> data, String path) {
        String[] parts = path.split("\\.");
        Object current = data;

        for (String part : parts) {
            if (!(current instanceof Map<?, ?> map)) {
                return null;
            }
            current = map.get(part);
        }

        return current;
    }
}
