package com.config;

import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.*;

import javax.sql.DataSource;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class JpaPersistenceUnit implements PersistenceUnitInfo {

    private final PersistenceUnitConfig config;

    public JpaPersistenceUnit(PersistenceUnitConfig config) {
        this.config = config;
    }

    @Override
    public String getPersistenceUnitName() {
        return config.name;
    }

    @Override
    public String getPersistenceProviderClassName() {
        return config.provider;
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
        return PersistenceUnitTransactionType.valueOf(config.transactionType);
    }

    @Override
    public DataSource getJtaDataSource() {
        return null;
    }

    @Override
    public DataSource getNonJtaDataSource() {
        return null;
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
        return config.entities;
    }

    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        return SharedCacheMode.UNSPECIFIED;
    }

    @Override
    public ValidationMode getValidationMode() {
        return ValidationMode.NONE;
    }

    @Override
    public Properties getProperties() {
        Properties props = new Properties();
        props.putAll(config.properties);
        return props;
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        return "3.0";
    }

    @Override
    public ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    @Override
    public void addTransformer(ClassTransformer transformer) {
    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}