/**
 * Name: Lingpeng Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/08/2024
 * File Name: ParticleAppTest.java
 * Description: This test class ensures the functionality of ParticleApp, including loading configurations and handling keyboard interactions.
 */

package com.example.particlevisualization;

import org.junit.Test;
import static org.junit.Assert.*;

public class ParticleAppTest {

    @Test
    public void testLoadConfiguration() {
        ParticleApp app = new ParticleApp();
        app.loadConfiguration();

        // Verify default configuration values are loaded
        assertEquals(50, app.getColorSpectrum());
        assertEquals(10, app.getParticleSpeed());
        assertEquals(50, app.getParticleCount());
        assertEquals("circle", app.getParticleShape());
    }

    @Test
    public void testKeyPressed() {
        ParticleApp app = new ParticleApp();
        app.setup(); // Simulate setup

        // Simulate pressing the 'c' key for capturing frames
        app.key = 'c';
        app.keyPressed();
        assertTrue("Frame capturing should be enabled", app.isCapturingFrames());

        // Simulate pressing the 'v' key for video recording
        app.key = 'v';
        app.keyPressed();
        assertTrue("Video recording should be enabled", app.isRecordingVideo());
    }
}
