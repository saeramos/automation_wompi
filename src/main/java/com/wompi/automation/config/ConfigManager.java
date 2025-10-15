package com.wompi.automation.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration manager for Wompi API tests
 * Implements Singleton pattern to ensure single instance across tests
 */
public class ConfigManager {
    private static ConfigManager instance;
    private Properties properties;

    private ConfigManager() {
        loadProperties();
    }

    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    private void loadProperties() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration properties", e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getUatPrincipalUrl() {
        return getProperty("uat.principal.url");
    }

    public String getUatSandboxUrl() {
        return getProperty("uat.sandbox.url");
    }

    public String getPublicKey() {
        return getProperty("public.key");
    }

    public String getPrivateKey() {
        return getProperty("private.key");
    }

    public String getEventsKey() {
        return getProperty("events.key");
    }

    public String getIntegrityKey() {
        return getProperty("integrity.key");
    }

    public String getTestAmount() {
        return getProperty("test.amount");
    }

    public String getTestCurrency() {
        return getProperty("test.currency");
    }

    public String getTestReference() {
        return getProperty("test.reference");
    }

    public String getTestDescription() {
        return getProperty("test.description");
    }

    public int getApiTimeout() {
        return Integer.parseInt(getProperty("api.timeout"));
    }

    public int getTransactionTimeout() {
        return Integer.parseInt(getProperty("transaction.timeout"));
    }
}
