package com.config;

import java.util.List;
import java.util.Map;

public class PersistenceUnitConfig {
    public String name;
    public String provider;
    public String transactionType;
    public List<String> entities;
    public Map<String, String> properties;
}