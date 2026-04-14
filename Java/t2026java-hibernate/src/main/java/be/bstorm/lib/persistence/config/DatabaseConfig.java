package be.bstorm.lib.persistence.config;

import be.bstorm.lib.persistence.config.hibernate.HibernateConfig;
import lombok.Data;

@Data
public class DatabaseConfig {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private HibernateConfig hibernate;
}
