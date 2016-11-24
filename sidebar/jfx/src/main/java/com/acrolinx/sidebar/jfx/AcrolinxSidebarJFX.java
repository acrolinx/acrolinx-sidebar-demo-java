package com.acrolinx.sidebar.jfx;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.AcrolinxSidebarPlugin;
import com.acrolinx.sidebar.JSConsole;
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

public class AcrolinxSidebarJFX extends Region
{
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();

    final Logger logger = LoggerFactory.getLogger(AcrolinxSidebarJFX.class);

    public AcrolinxSidebarJFX(AcrolinxIntegration integration)
    {
        logger.debug("Trying to load sidebar url: " + integration.getInitParameters().getSidebarUrl());
        String sidebarUrl = integration.getInitParameters().getSidebarUrl();
        webEngine.load(integration.getInitParameters().getSidebarUrl());
        getChildren().add(browser);
        webEngine.setJavaScriptEnabled(true);
        browser.contextMenuEnabledProperty();

        webEngine.setOnError((final WebErrorEvent arg0) -> logger.error("Error: " + arg0.getMessage()));
        webEngine.setOnAlert((final WebEvent<String> arg0) -> logger.info("Alert: " + arg0.getData()));

        webEngine.getLoadWorker().stateProperty().addListener(
                (final ObservableValue<? extends Worker.State> observedValue, final Worker.State oldState, final Worker.State newState) -> {
                    if (newState == Worker.State.SUCCEEDED) {
                        logger.debug("Sidebar loaded from " + webEngine.getLocation());
                        final JSObject jsobj = (JSObject) webEngine.executeScript("window");
                        final AcrolinxSidebarPlugin acrolinxSidebarPlugin = new AcrolinxSidebarPlugin(integration,
                                jsobj);
                        jsobj.setMember("acrolinxPlugin", acrolinxSidebarPlugin);
                        jsobj.setMember("java", new JSConsole());
                        webEngine.executeScript(JSConsole.overwriteJSLogging());
                    }
                    logger.debug("state changed: " + observedValue.getValue() + ": " + oldState + " -> " + newState);
                    if ("FAILED".equals("" + newState)) {
                        logger.error(webEngine.getLoadWorker().getException().getMessage());
                    }
                });
    }

    @Override protected void layoutChildren()
    {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
    }

    @Override protected double computePrefWidth(double height)
    {
        return 300;
    }

    @Override protected double computePrefHeight(double width)
    {
        return 500;
    }
}
