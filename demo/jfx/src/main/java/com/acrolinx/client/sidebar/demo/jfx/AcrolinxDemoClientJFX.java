/* Copyright (c) 2018-present Acrolinx GmbH */
package com.acrolinx.client.sidebar.demo.jfx;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import com.acrolinx.sidebar.AcrolinxSidebar;
import com.acrolinx.sidebar.jfx.AcrolinxSidebarJFX;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;
import com.acrolinx.sidebar.pojo.settings.InputFormat;
import com.acrolinx.sidebar.pojo.settings.PluginSupportedParameters;
import com.acrolinx.sidebar.pojo.settings.SoftwareComponent;
import com.acrolinx.sidebar.pojo.settings.SoftwareComponentCategory;
import com.acrolinx.sidebar.utils.LoggingUtils;

public class AcrolinxDemoClientJFX extends Application
{
    static final AtomicReference<InputFormat> inputFormat = new AtomicReference<>();
    static final AtomicReference<AcrolinxSidebar> sidebar = new AtomicReference<>();

    private final TextArea textArea = new TextArea();

    @Override
    public void start(final Stage primaryStage)
    {
        try {
            LoggingUtils.setupLogging("AcrolinxDemoClientJFX");
        } catch (final Exception e) {
            e.printStackTrace();
        }

        final ArrayList<SoftwareComponent> softwareComponents = new ArrayList<>();
        softwareComponents.add(new SoftwareComponent("com.acrolinx.client.sidebar.demo.jfx", "Acrolinx Demo Client JFX",
                "1.0", SoftwareComponentCategory.MAIN));
        final AcrolinxSidebarInitParameter initParameter = new AcrolinxSidebarInitParameter.AcrolinxSidebarInitParameterBuilder(
                "SW50ZWdyYXRpb25EZXZlbG9wbWVudERlbW9Pbmx5", softwareComponents).withPluginSupportedParameters(
                        new PluginSupportedParameters(true)).withShowServerSelector(true).build();

        final BorderPane borderPane = new BorderPane();
        final TextArea textArea = this.getTextArea();
        final ComboBox<InputFormat> formatDropdown = new ComboBox<>();
        formatDropdown.getItems().addAll(InputFormat.XML, InputFormat.HTML, InputFormat.TEXT, InputFormat.MARKDOWN,
                InputFormat.AUTO);
        formatDropdown.setValue(InputFormat.TEXT);
        inputFormat.set(InputFormat.TEXT);
        formatDropdown.valueProperty().addListener(
                (observable, oldValue, newValue) -> inputFormat.set(InputFormat.valueOf(newValue.toString())));

        final AcrolinxJFXIntegration integration = new AcrolinxJFXIntegration(this.textArea, initParameter);
        sidebar.set(new AcrolinxSidebarJFX(integration));
        ((AcrolinxSidebarJFX) sidebar.get()).getWebView().setPrefWidth(300);
        borderPane.setRight(((AcrolinxSidebarJFX) sidebar.get()).getWebView());
        borderPane.setLeft(textArea);
        borderPane.setTop(formatDropdown);
        final Scene scene = new Scene(borderPane, 900, 600);
        primaryStage.setTitle("Acrolinx Demo JFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(final String[] args)
    {
        launch(args);
    }

    private TextArea getTextArea()
    {
        textArea.setPrefHeight(600);
        textArea.setPrefWidth(600);
        return textArea;
    }
}
