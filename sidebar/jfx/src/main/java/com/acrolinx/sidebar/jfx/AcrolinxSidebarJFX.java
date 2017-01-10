/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar.jfx;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.AcrolinxSidebar;
import com.acrolinx.sidebar.pojo.document.CheckedDocumentPart;
import com.acrolinx.sidebar.pojo.settings.CheckOptions;
import com.acrolinx.sidebar.pojo.settings.SidebarConfiguration;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebErrorEvent;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AcrolinxSidebarJFX extends Region implements AcrolinxSidebar
{
    private final WebView browser = new WebView();
    private final WebEngine webEngine = browser.getEngine();
    private final int prefHeight;
    private AcrolinxSidebarPlugin acrolinxSidebarPlugin;

    private final Logger logger = LoggerFactory.getLogger(AcrolinxSidebarJFX.class);

    /**
     * @param integration The implementation of the Acrolinx Integration.
     * @param prefHeight The preferred height of the Acrolinx Sidebar.
     */
    public AcrolinxSidebarJFX(AcrolinxIntegration integration, int prefHeight)
    {
        this.prefHeight = prefHeight;
        logger.debug("Trying to load sidebar url: " + integration.getInitParameters().getSidebarUrl());
        getChildren().add(browser);
        webEngine.setJavaScriptEnabled(true);
        browser.contextMenuEnabledProperty();

        webEngine.setOnError((final WebErrorEvent arg0) -> logger.error("Error: " + arg0.getMessage()));
        webEngine.setOnAlert((final WebEvent<String> arg0) -> logger.debug("Alert: " + arg0.getData()));

        webEngine.getLoadWorker().stateProperty().addListener(
                (final ObservableValue<? extends Worker.State> observedValue, final Worker.State oldState, final Worker.State newState) -> {
                    if (newState == Worker.State.SUCCEEDED) {
                        logger.debug("Sidebar loaded from " + webEngine.getLocation());
                        final JSObject jsobj = (JSObject) webEngine.executeScript("window");
                        webEngine.executeScript(JSConsole.overwriteJSLogging());
                        acrolinxSidebarPlugin = new AcrolinxSidebarPlugin(integration, jsobj);
                        jsobj.setMember("acrolinxPlugin", acrolinxSidebarPlugin);
                        jsobj.setMember("java", new JSConsole());
                    }
                    logger.debug("state changed: " + observedValue.getValue() + ": " + oldState + " -> " + newState);
                    if ("FAILED".equals("" + newState)) {
                        try {
                            throw webEngine.getLoadWorker().getException();
                        } catch (Throwable throwable) {
                            logger.error(throwable.getMessage());
                        }
                    }
                });
        Platform.runLater(() -> webEngine.load(integration.getInitParameters().getSidebarUrl()));
    }

    @Override
    protected void layoutChildren()
    {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
    }

    @Override
    protected double computePrefWidth(double height)
    {
        return 300;
    }

    @Override
    protected double computePrefHeight(double width)
    {
        return this.prefHeight;
    }

    @Override
    public void configure(SidebarConfiguration configuration)
    {
        acrolinxSidebarPlugin.configureSidebar(configuration);

    }

    @Override
    public CompletableFuture<String> checkGlobal(String documentContent, CheckOptions options)
    {
        return acrolinxSidebarPlugin.checkGlobal(documentContent, options);
    }

    @Override
    public void onGlobalCheckRejected()
    {

        acrolinxSidebarPlugin.onGlobalCheckRejected();
    }

    @Override
    public void invalidateRanges(List<CheckedDocumentPart> invalidCheckedDocumentRanges)
    {
        acrolinxSidebarPlugin.invalidateRanges(invalidCheckedDocumentRanges);

    }
}
