/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar.swing;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.AcrolinxSidebar;
import com.acrolinx.sidebar.jfx.AcrolinxSidebarJFX;
import com.acrolinx.sidebar.pojo.document.CheckedDocumentPart;
import com.acrolinx.sidebar.pojo.settings.CheckOptions;
import com.acrolinx.sidebar.pojo.settings.SidebarConfiguration;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("SameParameterValue")
public class AcrolinxSidebarSwing extends JFXPanel implements AcrolinxSidebar
{
    private final VBox pane = new VBox();
    private final int prefHeight;
    private AcrolinxSidebarJFX sidebarJFX;

    private final AcrolinxIntegration integration;

    public AcrolinxSidebarSwing(AcrolinxIntegration integration, int prefHeight)
    {
        pane.setAlignment(Pos.CENTER);
        this.integration = integration;
        this.prefHeight = prefHeight;
        Platform.runLater(this::createScene);
    }

    private void createScene()
    {
        sidebarJFX = new AcrolinxSidebarJFX(integration, this.prefHeight);
        pane.getChildren().addAll(sidebarJFX);
        Scene scene = new Scene(pane, 300, prefHeight);
        setScene(scene);
        setVisible(true);
    }

    @Override
    public void configure(SidebarConfiguration configuration)
    {
        sidebarJFX.configure(configuration);
    }

    @Override
    public CompletableFuture<String> checkGlobal(String documentContent, CheckOptions options)
    {
        return sidebarJFX.checkGlobal(documentContent, options);
    }

    @Override
    public void onGlobalCheckRejected()
    {
        sidebarJFX.onGlobalCheckRejected();
    }

    @Override
    public void invalidateRanges(List<CheckedDocumentPart> invalidCheckedDocumentRanges)
    {
        sidebarJFX.invalidateRanges(invalidCheckedDocumentRanges);
    }

    @Override
    public void loadSidebarFromServerLocation(String serverAddress) {
        sidebarJFX.loadSidebarFromServerLocation(serverAddress);
    }

}
