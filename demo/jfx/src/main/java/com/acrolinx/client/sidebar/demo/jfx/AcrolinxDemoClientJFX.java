/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.client.sidebar.demo.jfx;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.LookupRanges;
import com.acrolinx.sidebar.jfx.AcrolinxSidebarJFX;
import com.acrolinx.sidebar.jfx.adapter.TextAreaAdapter;
import com.acrolinx.sidebar.pojo.SidebarError;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;
import com.acrolinx.sidebar.pojo.settings.InputFormat;
import com.acrolinx.sidebar.pojo.settings.SoftwareComponent;
import com.acrolinx.sidebar.pojo.settings.SoftwareComponentCategory;
import com.acrolinx.sidebar.utils.LookupRangesDiff;
import com.acrolinx.sidebar.utils.SidebarUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Optional;

public class AcrolinxDemoClientJFX extends Application implements AcrolinxIntegration
{
    private final TextArea textArea = new TextArea();
    private TextAreaAdapter textAreaAdapter;
    private final LookupRangesDiff lookup = new LookupRangesDiff();

    private final Logger logger = LoggerFactory.getLogger(AcrolinxDemoClientJFX.class);

    @Override
    public void start(Stage primaryStage)
    {

        final BorderPane borderPane = new BorderPane();
        final TextArea textArea = this.getTextArea();
        final ComboBox<InputFormat> formatDropdown = new ComboBox<>();
        formatDropdown.getItems().addAll(InputFormat.XML, InputFormat.HTML, InputFormat.TEXT, InputFormat.MARKDOWN);
        formatDropdown.setValue(InputFormat.TEXT);
        this.textAreaAdapter = new TextAreaAdapter(this.getTextArea(), InputFormat.TEXT);

        formatDropdown.valueProperty().addListener((observable, oldValue, newValue) -> textAreaAdapter.setInputFormat(
                InputFormat.valueOf(newValue.toString())));
        borderPane.setRight(this.createSidebar());
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

    private Region createSidebar()
    {
        return new AcrolinxSidebarJFX(this, 600);
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

    @Override
    public InputAdapterInterface getEditorAdapter()
    {
        return textAreaAdapter;
    }

    @Override
    public AcrolinxSidebarInitParameter getInitParameters()
    {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        ArrayList<SoftwareComponent> softwareComponents = new ArrayList<>();
        softwareComponents.add(
                new SoftwareComponent("com.acrolinx.client.sidebar.demo.jfx", "Acrolinx Demo Client JFX", "1.0",
                        SoftwareComponentCategory.MAIN));
        return new AcrolinxSidebarInitParameter.AcrolinxSidebarInitParameterBuilder(
                "SW50ZWdyYXRpb25EZXZlbG9wbWVudERlbW9Pbmx5", softwareComponents).
                withShowServerSelector(true).build();
    }

    @Override
    public LookupRanges getLookup()
    {
        return lookup;
    }

    @Override
    public void onCheckResult(CheckResult checkResult)
    {
        logger.debug("Got check result for check id: " + checkResult.getCheckedDocumentPart().getCheckId());
        // Do nothing for now;
    }

    @Override
    public void openWindow(String url)
    {
        SidebarUtils.openWebPageInDefaultBrowser(url);
    }

    @Override
    public void onInitFinished(
            @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<SidebarError> initResult)
    {
        logger.debug("Sidebar init done: " + initResult.toString());
        // Do nothing for now;

    }

    @Override
    public boolean canCheck()
    {
        return true;
    }

}
