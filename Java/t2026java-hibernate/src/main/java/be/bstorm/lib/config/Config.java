package be.bstorm.lib.config;

import be.bstorm.lib.persistence.config.DatabaseConfig;
import be.bstorm.lib.vault.config.VaultConfig;
import be.bstorm.lib.vault.config.VaultResponse;
import lombok.Data;

@Data
public class Config {
    private VaultConfig vault;
    private DatabaseConfig database;
}
