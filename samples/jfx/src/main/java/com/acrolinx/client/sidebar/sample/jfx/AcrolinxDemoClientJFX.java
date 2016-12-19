package com.acrolinx.client.sidebar.sample.jfx;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.InvocationAdapterInterface;
import com.acrolinx.sidebar.jfx.AcrolinxSidebarJFX;
import com.acrolinx.sidebar.jfx.adapter.InvocationAdapterJFX;
import com.acrolinx.sidebar.jfx.adapter.TextAreaAdapter;
import com.acrolinx.sidebar.pojo.InitResult;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.document.DownloadInfo;
import com.acrolinx.sidebar.pojo.settings.*;
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
    private Stage applicationStage;

    private final Logger logger = LoggerFactory.getLogger(AcrolinxDemoClientJFX.class);

    @Override public void start(Stage primaryStage)
    {

        applicationStage = primaryStage;
        final BorderPane borderPane = new BorderPane();
        final TextArea textArea = this.getTextArea();
        final ComboBox<InputFormat> formatDropdown = new ComboBox<>();
        formatDropdown.getItems().addAll(InputFormat.XML, InputFormat.HTML, InputFormat.TEXT);
        formatDropdown.setValue(InputFormat.TEXT);
        this.textAreaAdapter = new TextAreaAdapter(this.getTextArea(), InputFormat.TEXT);

        formatDropdown.valueProperty().addListener((observable, oldValue, newValue) -> textAreaAdapter.setInputFormat(
                        InputFormat.valueOf(newValue.toString())));
        borderPane.setRight(this.createSidebar());
        borderPane.setLeft(textArea);
        borderPane.setTop(formatDropdown);
        Scene scene = new Scene(borderPane, 900, 600);
        applicationStage.setTitle("Acrolinx Demo JFX");
        applicationStage.setScene(scene);
        applicationStage.show();
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
        //tell stage it is meannt to pop-up (Modal)
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("Acrolinx");
        return newStage;
    }

    @Override public InputAdapterInterface getEditorAdapter()
    {
        return textAreaAdapter;
    }

    @Override public InvocationAdapterInterface getInvocationAdapter()
    {
        return new InvocationAdapterJFX();
    }

    @Override public AcrolinxSidebarInitParemeters getInitParameters()
    {
        ArrayList<SoftwareComponent> softwareComponents = new ArrayList<>();
        softwareComponents.add(new SoftwareComponent("com.acrolinx.sample", "Acrolinx Demo Client JFX", "1.0.0.1",
                Optional.of(SoftwareComponentCategory.MAIN)));
        return new AcrolinxSidebarInitParemeters.AcrolinxSidebarInitParemetersBuilder(
                "SW50ZWdyYXRpb25EZXZlbG9wbWVudERlbW9Pbmx5", softwareComponents).
                withShowServerSelector(true).build();
    }

    @Override public void onCheckResult(CheckResult checkResult)
    {
        logger.debug("Got check result for check id: " + checkResult.getCheckedDocumentPart().getCheckId());
        // Do nothing for now;
    }

    @Override public void openWindow(String url)
    {
        Stage popupStage = getPopUpStage(url);
        popupStage.setOnCloseRequest(event -> popupStage.close());
        popupStage.showAndWait();
    }

    @Override public void configure(AcrolinxPluginConfiguration pluginConfiguration)
    {

    }

    @Override public void onInitFinished(InitResult initResult)
    {
        logger.debug("Got check result for check id: " + initResult.toString());
        // Do nothing for now;

    }

    @Override public void download(DownloadInfo downloadInfo)
    {

    }

}
