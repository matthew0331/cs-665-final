/**
 * Name: Lingpeng Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/08/2024
 * File Name: ConfigLoader.java
 * Description: This class is responsible for loading and parsing configuration properties from a file.
 */
package com.example.particlevisualization;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigLoader is responsible for loading and parsing configuration properties.
 */
public class ConfigLoader {
    private Properties properties;

    /**
     * Loads configuration from a specified file path.
     *
     * @param filePath The path to the configuration file.
     * @throws IOException if there is an issue reading the file.
     */
    public ConfigLoader(String filePath) throws IOException {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        }
    }

    /**
     * Gets an integer value from the configuration.
     *
     * @param key          The key for the desired property.
     * @param defaultValue The default value if the key is not found or invalid.
     * @return The integer value associated with the key or the default value.
     */
    public int getInt(String key, int defaultValue) {
        try {
            return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            System.err.println("Invalid integer for key '" + key + "': " + properties.getProperty(key));
            return defaultValue;
        }
    }

    /**
     * Gets a string value from the configuration.
     *
     * @param key          The key for the desired property.
     * @param defaultValue The default value if the key is not found.
     * @return The string value associated with the key or the default value.
     */
    public String getString(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
