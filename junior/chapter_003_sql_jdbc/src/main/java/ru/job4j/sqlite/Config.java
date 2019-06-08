package ru.job4j.sqlite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

/**
 * Database connection details.
 */
public class Config {
    private static final Logger LOG = LogManager.getLogger(Config.class.getName());
    // properties
    private final Properties properties = new Properties();
    public static final String APP_PROPERTIES = "sqlite.properties";

    public Config init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream(APP_PROPERTIES)) {
            properties.load(in);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return this;
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
