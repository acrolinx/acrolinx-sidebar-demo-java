package com.acrolinx.sidebar.swing;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.jfx.AcrolinxSidebarJFX;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class AcrolinxSidebarSwing extends JFXPanel
{
    private VBox pane = new VBox();
    private int prefHeight;

    private AcrolinxIntegration integration;

    public AcrolinxSidebarSwing(AcrolinxIntegration integration, int prefHeight)
    {
        pane.setAlignment(Pos.CENTER);
        this.integration = integration;
        this.prefHeight = prefHeight;
        Platform.runLater(this::createScene);
    }

    private void createScene()
    {
        AcrolinxSidebarJFX sidebarJFX = new AcrolinxSidebarJFX(integration, this.prefHeight);
        pane.getChildren().addAll(sidebarJFX);
        Scene scene = new Scene(pane, 300, prefHeight);
        setScene(scene);
    }
}
