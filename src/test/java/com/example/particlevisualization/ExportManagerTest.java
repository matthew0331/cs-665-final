/**
 * Name: Lingpeng Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/08/2024
 * File Name: ExportManagerTest.java
 * Description: This test class validates the functionality of ExportManager for capturing frames, exporting GIFs, and handling errors.
 */

package com.example.particlevisualization;

import org.junit.Test;
import java.io.File;

import static org.junit.Assert.*;

public class ExportManagerTest {

    @Test
    public void testCaptureFrame() {
        ExportManager manager = new ExportManager();
        ParticleApp app = new ParticleApp();
        app.setup(); // Initialize the app
        app.draw();

        String outputPath = "exports/test_frame.png";
        manager.captureFrame(app, outputPath);

        File file = new File(outputPath);
        assertTrue("Frame file should be created", file.exists());
        file.delete(); // Clean up after test
    }

    @Test
    public void testExportGIFWithNoFrames() {
        ExportManager manager = new ExportManager();
        String outputPath = "exports/test_animation.gif";

        manager.exportGIF("exports/empty_dir", outputPath);
        File file = new File(outputPath);
        assertFalse("GIF file should not be created for empty directory", file.exists());
    }
}
