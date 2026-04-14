package be.bstorm.lib.persistence;

import be.bstorm.lib.config.Config;
import be.bstorm.lib.config.ConfigParser;
import be.bstorm.lib.persistence.config.hibernate.HibernateConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.Entity;
import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;
import org.reflections.Reflections;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class JpaPersistenceUnit implements PersistenceUnitInfo {
    private final Config config;

    public JpaPersistenceUnit() {
        try {
            this.config = ConfigParser.readConfig();
            System.out.println(this.config.getDatabase().getUrl());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String getPersistenceUnitName() {
        return "dal";
    }

    @Override
    public String getPersistenceProviderClassName() {
        return "org.hibernate.jpa.HibernatePersistenceProvider";
    }

    @Override
    public String getScopeAnnotationName() {
        return "";
    }

    @Override
    public List<String> getQualifierAnnotationNames() {
        return List.of();
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return PersistenceUnitTransactionType.RESOURCE_LOCAL;
    }

    @Override
    public DataSource getJtaDataSource() {
        return null;
    }

    @Override
    public DataSource getNonJtaDataSource() {
        if (this.config == null || this.config.getDatabase() == null) {
            throw new IllegalArgumentException("Database configuration is missing");
        }

        var database = this.config.getDatabase();
        var dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(database.getUrl());
        dataSource.setUsername(database.getUsername());
        dataSource.setPassword(database.getPassword());

        return dataSource;
    }

    @Override
    public List<String> getMappingFileNames() {
        return List.of();
    }

    @Override
    public List<URL> getJarFileUrls() {
        return List.of();
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    @Override
    public List<String> getManagedClassNames() {
        Reflections reflections = new Reflections("be.bstorm");
        Set<Class<?>> entities = reflections.getTypesAnnotatedWith(Entity.class);
        return entities.stream()
                .map(Class::getName)
                .toList();
    }

    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        return null;
    }

    @Override
    public ValidationMode getValidationMode() {
        return null;
    }

    @Override
    public Properties getProperties() {
        if (config == null && config.getDatabase() == null) {
            throw new IllegalArgumentException("Database configuration is missing");
        }
        Properties properties = new Properties();

        HibernateConfig hibernateConfig = config.getDatabase().getHibernate();

        if (hibernateConfig != null) {
            if (hibernateConfig.getDialect() != null) {
                properties.put("hibernate.dialect", hibernateConfig.getDialect());
            }
            if (hibernateConfig.getHbm2ddl() != null) {
                properties.put("hibernate.hbm2ddl.auto", hibernateConfig.getHbm2ddl().getAuto());
            }
            if (hibernateConfig.isShowSql()) {
                properties.put("hibernate.show_sql", hibernateConfig.isShowSql());
            }
            if (hibernateConfig.isFormatSql()) {
                properties.put("hibernate.format_sql", hibernateConfig.isFormatSql());
            }
            if (config.getDatabase().getDriverClassName() != null) {
                properties.put("hibernate.connection.driver_class", config.getDatabase().getDriverClassName());
            }
        }

        return properties;
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        return "";
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public void addTransformer(ClassTransformer classTransformer) {

    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;
    }
}
