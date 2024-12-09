/**
 * Name: Lingpeng Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/08/2024
 * File Name: SettingsUITest.java
 * Description: This test class verifies that the SettingsUI triggers the callback mechanism correctly when settings are saved.
 */

package com.example.particlevisualization;

import org.junit.Test;
import static org.junit.Assert.*;

public class SettingsUITest {

    @Test
    public void testSettingsUpdateCallback() {
        boolean[] callbackTriggered = {false};

        SettingsUI ui = new SettingsUI("src/main/resources/config.properties", () -> callbackTriggered[0] = true);
        ui.showSettings(); // Simulate opening the UI

        // Simulate saving settings
        ui.dispose();
        assertTrue("Callback should be triggered after saving settings", callbackTriggered[0]);
    }
}
