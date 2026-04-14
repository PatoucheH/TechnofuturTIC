package be.bstorm.lib.vault.config;

import lombok.Data;

import java.util.Map;

@Data
public class VaultResponse {
    private String request_id;
    private String lease_id;
    private Boolean renewable;
    private Integer lease_duration;

    private Map<String, Object> data;

    private String wrap_info;
    private String warnings;
    private String auth;
    private String mount_type;

}
