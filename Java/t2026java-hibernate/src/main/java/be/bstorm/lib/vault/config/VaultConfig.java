package be.bstorm.lib.vault.config;

import lombok.Data;

@Data
public class VaultConfig {
    private String url;
    private String token;
}
