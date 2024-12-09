/**
 * Name: Lingpeng Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/08/2024
 * File Name: ConfigLoaderTest.java
 * Description: This test class verifies the functionality of ConfigLoader with valid configurations and tests error handling for missing files.
 */

package com.example.particlevisualization;

import org.junit.Test;
import java.util.Properties;

import static org.junit.Assert.*;

public class ConfigLoaderTest {

    @Test
    public void testValidConfigFile() throws Exception {
        ConfigLoader loader = new ConfigLoader("src/main/resources/config.properties");
        assertEquals(50, loader.getInt("colorSpectrum", 0));
        assertEquals(10, loader.getInt("particleSpeed", 0));
        assertEquals(50, loader.getInt("particleCount", 0));
        assertEquals("circle", loader.getString("particleShape", ""));
    }

    @Test
    public void testMissingConfigFile() {
        try {
            ConfigLoader loader = new ConfigLoader("missing.properties");
            fail("Expected IOException for missing file");
        } catch (Exception e) {
            assertTrue(e instanceof java.io.IOException);
        }
    }
}
