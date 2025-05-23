/* Copyright (c) 2018 Acrolinx GmbH */
package com.acrolinx.client.sidebar.demo.swing;

import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter.AcrolinxSidebarInitParameterBuilder;
import com.acrolinx.sidebar.pojo.settings.SoftwareComponent;
import com.acrolinx.sidebar.pojo.settings.SoftwareComponentCategory;
import com.acrolinx.sidebar.swing.AcrolinxSidebarSwing;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class SwingAcrolinxApplication {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(
        () -> {
          JFrame.setDefaultLookAndFeelDecorated(true);
          createJFrame(createJTextArea());
          createJDialog(createJOptionPane());
        });
  }

  private static AcrolinxSidebarInitParameter createAcrolinxSidebarInitParameter() {
    return new AcrolinxSidebarInitParameterBuilder(
            "SW50ZWdyYXRpb25EZXZlbG9wbWVudERlbW9Pbmx5", createSoftwareComponents())
        .withShowServerSelector(true)
        .build();
  }

  private static void createJDialog(JOptionPane jOptionPane) {
    JDialog jDialog = jOptionPane.createDialog("Warning!");
    jDialog.setAlwaysOnTop(true);
    jDialog.setVisible(true);
  }

  private static void createJFrame(JTextArea jTextArea) {
    JFrame jFrame = new JFrame("Acrolinx Demo Client Swing");
    jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    jFrame.setResizable(false);
    jFrame.getContentPane().add(createJPanel(jTextArea));
    jFrame.pack();
    jFrame.setVisible(true);
  }

  private static JOptionPane createJOptionPane() {
    return new JOptionPane(
        "This is not a feature complete demo of the Java SDK, please have a look into the Java FX Demo",
        JOptionPane.WARNING_MESSAGE);
  }

  private static JPanel createJPanel(JTextArea jTextArea) {
    JPanel innerPanel = new JPanel();
    innerPanel.setLayout(new FlowLayout());
    innerPanel.setPreferredSize(new Dimension(870, 600));
    innerPanel.add(jTextArea);
    innerPanel.add(createSwingAcrolinxIntegration(jTextArea));
    innerPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    return innerPanel;
  }

  private static JTextArea createJTextArea() {
    JTextArea jTextArea = new JTextArea();
    jTextArea.setPreferredSize(new Dimension(550, 600));
    return jTextArea;
  }

  private static List<SoftwareComponent> createSoftwareComponents() {
    return List.of(
        new SoftwareComponent(
            "com.acrolinx.client.sidebar.demo.swing",
            "Acrolinx Demo Client Swing",
            "1.0",
            SoftwareComponentCategory.MAIN));
  }

  private static AcrolinxSidebarSwing createSwingAcrolinxIntegration(JTextArea jTextArea) {
    return new AcrolinxSidebarSwing(
        new SwingAcrolinxIntegration(createAcrolinxSidebarInitParameter(), jTextArea), null);
  }
}
