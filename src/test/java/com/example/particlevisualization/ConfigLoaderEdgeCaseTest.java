/**
 * Name: Lingpeng Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/08/2024
 * File Name: ConfigLoaderEdgeCaseTest.java
 * Description: This test class ensures that ConfigLoader handles edge cases such as invalid values and missing keys correctly.
 */

package com.example.particlevisualization;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigLoaderEdgeCaseTest {

    @Test
    public void testInvalidIntegerValue() throws Exception {
        ConfigLoader loader = new ConfigLoader("src/main/resources/config.properties");

        // Simulate invalid integer value
        String key = "invalidIntegerKey";
        int defaultValue = 100;
        assertEquals("Should return default value for invalid integer", defaultValue, loader.getInt(key, defaultValue));
    }

    @Test
    public void testMissingConfigurationKey() throws Exception {
        ConfigLoader loader = new ConfigLoader("src/main/resources/config.properties");

        // Simulate missing key
        String missingKey = "nonexistentKey";
        String defaultValue = "default";
        assertEquals("Should return default value for missing key", defaultValue, loader.getString(missingKey, defaultValue));
    }
}
