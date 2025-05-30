/* Copyright (c) 2018 Acrolinx GmbH */
package com.acrolinx.client.sidebar.demo.jfx;

import com.acrolinx.sidebar.jfx.AcrolinxSidebarJFX;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter.AcrolinxSidebarInitParameterBuilder;
import com.acrolinx.sidebar.pojo.settings.InputFormat;
import com.acrolinx.sidebar.pojo.settings.PluginSupportedParameters;
import com.acrolinx.sidebar.pojo.settings.SoftwareComponent;
import com.acrolinx.sidebar.pojo.settings.SoftwareComponentCategory;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JfxAcrolinxApplication extends Application {
  static final AtomicReference<AcrolinxSidebarJFX> acrolinxSidebar = new AtomicReference<>();
  static final AtomicReference<InputFormat> inputFormat = new AtomicReference<>();

  public static void main(String[] args) {
    Application.launch(args);
  }

  private static AcrolinxSidebarInitParameter createAcrolinxSidebarInitParameter() {
    return new AcrolinxSidebarInitParameterBuilder(
            "SW50ZWdyYXRpb25EZXZlbG9wbWVudERlbW9Pbmx5", createSoftwareComponents())
        .withPluginSupportedParameters(new PluginSupportedParameters(true))
        .withShowServerSelector(true)
        .build();
  }

  private static ComboBox<InputFormat> createComboBox() {
    ComboBox<InputFormat> comboBox = new ComboBox<>();
    comboBox.getItems().addAll(InputFormat.values());
    comboBox.setValue(InputFormat.TEXT);
    comboBox
        .valueProperty()
        .addListener(
            (observableValue, oldInputFormat, newInputFormat) -> inputFormat.set(newInputFormat));
    return comboBox;
  }

  private static List<SoftwareComponent> createSoftwareComponents() {
    return List.of(
        new SoftwareComponent(
            "com.acrolinx.client.sidebar.demo.jfx",
            "Acrolinx Demo Client JFX",
            "1.0",
            SoftwareComponentCategory.MAIN));
  }

  private static TextArea createTextArea() {
    TextArea textArea = new TextArea();
    textArea.setPrefHeight(600);
    textArea.setPrefWidth(600);
    return textArea;
  }

  private final TextArea textArea = createTextArea();

  @Override
  public void start(Stage primaryStage) {
    AcrolinxSidebarJFX acrolinxSidebarJfx = new AcrolinxSidebarJFX(createJfxAcrolinxIntegration());
    acrolinxSidebarJfx.getWebView().setPrefWidth(300);
    acrolinxSidebar.set(acrolinxSidebarJfx);

    ComboBox<InputFormat> comboBox = createComboBox();
    inputFormat.set(comboBox.getValue());

    Scene scene = new Scene(createBorderPane(acrolinxSidebarJfx, comboBox), 900, 600);
    primaryStage.setTitle("Acrolinx Demo JFX");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private BorderPane createBorderPane(
      AcrolinxSidebarJFX acrolinxSidebarJfx, ComboBox<InputFormat> comboBox) {
    BorderPane borderPane = new BorderPane();
    borderPane.setRight(acrolinxSidebarJfx.getWebView());
    borderPane.setLeft(textArea);
    borderPane.setTop(comboBox);
    return borderPane;
  }

  private JfxAcrolinxIntegration createJfxAcrolinxIntegration() {
    return new JfxAcrolinxIntegration(textArea, createAcrolinxSidebarInitParameter());
  }
}
