/**
 * Name: Lingpeng Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/08/2024
 * File Name: SettingsUI.java
 * Description: This class provides a graphical user interface for adjusting visualization parameters and saving them.
 */

package com.example.particlevisualization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingsUI extends JFrame {

    private Runnable onSettingsUpdated; // Callback to notify ParticleApp

    public SettingsUI(String configPath, Runnable onSettingsUpdated) {
        this.onSettingsUpdated = onSettingsUpdated;

        setTitle("Settings");
        setSize(300, 300);
        setLayout(new GridLayout(6, 2));

        JLabel colorLabel = new JLabel("Color Spectrum (1-255):");
        JTextField colorField = new JTextField();

        JLabel speedLabel = new JLabel("Particle Speed (1-20):");
        JTextField speedField = new JTextField();

        JLabel countLabel = new JLabel("Particle Count (10-500):");
        JTextField countField = new JTextField();

        JLabel shapeLabel = new JLabel("Particle Shape:");
        JComboBox<String> shapeComboBox = new JComboBox<>(new String[]{"circle", "square", "triangle"});

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        add(colorLabel);
        add(colorField);
        add(speedLabel);
        add(speedField);
        add(countLabel);
        add(countField);
        add(shapeLabel);
        add(shapeComboBox);
        add(saveButton);
        add(cancelButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Properties properties = new Properties();
                    properties.setProperty("colorSpectrum", colorField.getText());
                    properties.setProperty("particleSpeed", speedField.getText());
                    properties.setProperty("particleCount", countField.getText());
                    properties.setProperty("particleShape", shapeComboBox.getSelectedItem().toString());
                    properties.store(new FileOutputStream(configPath), null);
                    JOptionPane.showMessageDialog(null, "Settings saved successfully!");

                    if (onSettingsUpdated != null) {
                        onSettingsUpdated.run(); // Notify ParticleApp
                    }

                    dispose();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving settings: " + ex.getMessage());
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void showSettings() {
        setVisible(true);
    }
}
