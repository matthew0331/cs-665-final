/**
 * Name: Lingpeng Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/08/2024
 * File Name: SettingsListener.java
 * Description: This interface defines the contract for listening to settings updates and applying them to the application.
 */

package com.example.particlevisualization;

import java.util.Properties;

public interface SettingsListener {
    void onSettingsUpdated(Properties updatedProperties);
}
