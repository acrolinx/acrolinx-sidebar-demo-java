/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.client.sidebar.demo.jfx;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acrolinx.sidebar.AcrolinxSidebar;
import com.acrolinx.sidebar.jfx.AcrolinxSidebarJFX;
import com.acrolinx.sidebar.lookup.LookupRangesDiff;
import com.acrolinx.sidebar.pojo.settings.*;
import com.acrolinx.sidebar.utils.LoggingUtils;

@SuppressWarnings("unused")
public class AcrolinxDemoClientJFX extends Application
{
    private final TextArea textArea = new TextArea();
    private final LookupRangesDiff lookup = new LookupRangesDiff();
    private String logFilePath;

    final static AtomicReference<InputFormat> inputFormat = new AtomicReference<>();
    final static AtomicReference<AcrolinxSidebar> sidebar = new AtomicReference<>();

    private final Logger logger = LoggerFactory.getLogger(AcrolinxDemoClientJFX.class);

    @Override
    public void start(Stage primaryStage)
    {
        try {
            LoggingUtils.setupLogging("AcrolinxDemoClientJFX");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<SoftwareComponent> softwareComponents = new ArrayList<>();
        softwareComponents.add(new SoftwareComponent("com.acrolinx.client.sidebar.demo.jfx", "Acrolinx Demo Client JFX",
                "1.0", SoftwareComponentCategory.MAIN));
        AcrolinxSidebarInitParameter initParameter = new AcrolinxSidebarInitParameter.AcrolinxSidebarInitParameterBuilder(
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

        AcrolinxJFXIntegration integration = new AcrolinxJFXIntegration(this.textArea, initParameter);
        sidebar.set(new AcrolinxSidebarJFX(integration));
        borderPane.setRight(((AcrolinxSidebarJFX) sidebar.get()).getWebView());
        borderPane.setLeft(textArea);
        borderPane.setTop(formatDropdown);
        Scene scene = new Scene(borderPane, 900, 600);
        primaryStage.setTitle("Acrolinx Demo JFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    private TextArea getTextArea()
    {
        textArea.setPrefHeight(600);
        textArea.setPrefWidth(600);
        return textArea;
    }

    private Stage getPopUpStage(String url)
    {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load(url);
        Scene scene = new Scene(browser, 900, 600);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("Acrolinx Demo Client JFX");
        return newStage;
    }
}
