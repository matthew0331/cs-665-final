/**
 * Name: Lingpeng Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/08/2024
 * File Name: ParticleApp.java
 * Description: This class contains the core logic for particle visualization and integrates user interactions for settings and exporting functionalities.
 */

package com.example.particlevisualization;

import processing.core.PApplet;

import java.io.File;

public class ParticleApp extends PApplet {

    private ExportManager exportManager;
    private boolean capturingFrames = false;
    private boolean recordingVideo = false;
    private String outputDir = "exports"; // Directory to store outputs

    // Configuration parameters
    private int colorSpectrum = 50;
    private int particleSpeed = 10;
    private int particleCount = 50;
    private String particleShape = "circle";

    @Override
    public void settings() {
        size(800, 800);
    }

    @Override
    public void setup() {
        exportManager = new ExportManager();
        File dir = new File(outputDir);
        if (!dir.exists()) dir.mkdirs();

        loadConfiguration(); // Load configuration
    }

    @Override
    public void draw() {
        background(0); // Set black background

        noStroke();
        fill(colorSpectrum, 100, 255);

        for (int i = 0; i < particleCount; i++) {
            float x = random(width);
            float y = random(height);

            switch (particleShape.toLowerCase()) {
                case "circle":
                    ellipse(x, y, particleSpeed, particleSpeed);
                    break;
                case "square":
                    rect(x, y, particleSpeed, particleSpeed);
                    break;
                case "triangle":
                    triangle(x, y, x + particleSpeed, y, x + particleSpeed / 2, y - particleSpeed);
                    break;
                default:
                    ellipse(x, y, particleSpeed, particleSpeed); // Default to circle
            }
        }

        if (capturingFrames) {
            exportManager.captureFrame(this, outputDir + "/frame_" + frameCount + ".png");
        }
        if (recordingVideo) {
            exportManager.captureVideoFrame(this);
        }
    }

    @Override
    public void keyPressed() {
        if (key == 'c') {
            capturingFrames = !capturingFrames;
            System.out.println("Frame capturing: " + (capturingFrames ? "ON" : "OFF"));
        }

        if (key == 'g') {
            System.out.println("Exporting GIF...");
            exportManager.exportGIF(outputDir, outputDir + "/particle_visualization.gif");
        }

        if (key == 'v') {
            if (!recordingVideo) {
                exportManager.startVideoExport(outputDir + "/particle_visualization.mp4");
            } else {
                exportManager.stopVideoExport();
            }
            recordingVideo = !recordingVideo;
            System.out.println("Video recording: " + (recordingVideo ? "ON" : "OFF"));
        }

        if (key == 's') {
            new SettingsUI("src/main/resources/config.properties", this::loadConfiguration).showSettings();
        }
    }

    void loadConfiguration() {
        try {
            ConfigLoader config = new ConfigLoader("src/main/resources/config.properties");
            colorSpectrum = config.getInt("colorSpectrum", 50);
            particleSpeed = config.getInt("particleSpeed", 10);
            particleCount = config.getInt("particleCount", 50);
            particleShape = config.getString("particleShape", "circle");
            System.out.println("Configuration reloaded: "
                    + "Color Spectrum=" + colorSpectrum
                    + ", Speed=" + particleSpeed
                    + ", Count=" + particleCount
                    + ", Shape=" + particleShape);
        } catch (Exception e) {
            System.err.println("Error loading configuration: " + e.getMessage());
        }
    }

    public int getColorSpectrum() {
        return colorSpectrum;
    }

    public int getParticleSpeed() {
        return particleSpeed;
    }

    public int getParticleCount() {
        return particleCount;
    }

    public String getParticleShape() {
        return particleShape;
    }

    public boolean isCapturingFrames() {
        return capturingFrames;
    }

    public boolean isRecordingVideo() {
        return recordingVideo;
    }
}
