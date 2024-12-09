/**
 * Name: Lingpeng Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/08/2024
 * File Name: ExportManager.java
 * Description: This class manages the export of frames, videos, and GIFs.
 */
package com.example.particlevisualization;

import com.squareup.gifencoder.GifEncoder;
import com.squareup.gifencoder.ImageOptions;
import org.jcodec.api.SequenceEncoder;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Picture;
import processing.core.PApplet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Manages the export of frames, videos, and GIFs.
 */
public class ExportManager {
    private SequenceEncoder encoder;

    public void captureFrame(PApplet app, String outputPath) {
        try {
            BufferedImage bufferedImage = new BufferedImage(app.width, app.height, BufferedImage.TYPE_INT_ARGB);

            app.loadPixels();
            for (int y = 0; y < app.height; y++) {
                for (int x = 0; x < app.width; x++) {
                    bufferedImage.setRGB(x, y, app.pixels[y * app.width + x]);
                }
            }

            ImageIO.write(bufferedImage, "png", new File(outputPath));
            System.out.println("Frame captured: " + outputPath);
        } catch (IOException e) {
            System.err.println("Error capturing frame: " + e.getMessage());
        }
    }

    public void captureVideoFrame(PApplet app) {
        if (encoder == null) {
            System.err.println("Video encoder is not initialized. Start video export first.");
            return;
        }

        try {
            BufferedImage bufferedImage = new BufferedImage(app.width, app.height, BufferedImage.TYPE_INT_ARGB);

            app.loadPixels();
            for (int y = 0; y < app.height; y++) {
                for (int x = 0; x < app.width; x++) {
                    bufferedImage.setRGB(x, y, app.pixels[y * app.width + x]);
                }
            }

            encoder.encodeNativeFrame(convertBufferedImageToPicture(bufferedImage));
            System.out.println("Video frame captured.");
        } catch (IOException e) {
            System.err.println("Error capturing video frame: " + e.getMessage());
        }
    }

    public void startVideoExport(String outputFileName) {
        try {
            encoder = SequenceEncoder.createSequenceEncoder(new File(outputFileName), 30);
            System.out.println("Video recording started: " + outputFileName);
        } catch (IOException e) {
            System.err.println("Error initializing video export: " + e.getMessage());
        }
    }

    public void stopVideoExport() {
        if (encoder == null) {
            System.err.println("No video recording in progress to stop.");
            return;
        }

        try {
            encoder.finish();
            encoder = null;
            System.out.println("Video recording stopped.");
        } catch (IOException e) {
            System.err.println("Error stopping video export: " + e.getMessage());
        }
    }

    public void exportGIF(String inputDir, String outputPath) {
        File dir = new File(inputDir);
        File[] frames = dir.listFiles((d, name) -> name.endsWith(".png"));
        if (frames == null || frames.length == 0) {
            System.err.println("No frames found for GIF export.");
            return;
        }

        // Sort frames alphabetically to ensure the correct sequence
        Arrays.sort(frames);

        try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
            BufferedImage firstFrame = ImageIO.read(frames[0]);
            GifEncoder gifEncoder = new GifEncoder(outputStream, firstFrame.getWidth(), firstFrame.getHeight(), 0);

            ImageOptions options = new ImageOptions();
            options.setDelay(100, TimeUnit.MILLISECONDS); // Set frame delay

            System.out.println("Starting GIF export...");
            for (int i = 0; i < frames.length; i++) {
                File frame = frames[i];
                System.out.println("Adding frame: " + frame.getName());
                BufferedImage img = ImageIO.read(frame);
                if (img == null) {
                    System.err.println("Skipping unreadable frame: " + frame.getName());
                    continue;
                }
                gifEncoder.addImage(convertBufferedImageToIntArray(img), options);

                // Log after adding each frame
                System.out.println("Frame " + frame.getName() + " added to GIF.");
            }

            gifEncoder.finishEncoding();
            System.out.println("GIF exported successfully to " + outputPath);
        } catch (IOException e) {
            System.err.println("Error exporting GIF: " + e.getMessage());
        }
    }


    private Picture convertBufferedImageToPicture(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        Picture picture = Picture.create(width, height, ColorSpace.RGB);
        int[] rgb = new int[width * height];
        bufferedImage.getRGB(0, 0, width, height, rgb, 0, width);
        byte[] data = picture.getPlaneData(0);

        for (int i = 0; i < rgb.length; i++) {
            int r = (rgb[i] >> 16) & 0xFF;
            int g = (rgb[i] >> 8) & 0xFF;
            int b = rgb[i] & 0xFF;

            data[3 * i] = (byte) r;
            data[3 * i + 1] = (byte) g;
            data[3 * i + 2] = (byte) b;
        }

        return picture;
    }

    private int[][] convertBufferedImageToIntArray(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int[][] pixels = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[y][x] = img.getRGB(x, y);
            }
        }
        return pixels;
    }
}
