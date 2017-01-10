/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.client.sidebar.demo.swing;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.pojo.SidebarError;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;
import com.acrolinx.sidebar.pojo.settings.SoftwareComponent;
import com.acrolinx.sidebar.pojo.settings.SoftwareComponentCategory;
import com.acrolinx.sidebar.swing.AcrolinxSidebarSwing;
import com.acrolinx.sidebar.swing.adapter.TextAreaAdapter;
import com.acrolinx.sidebar.utils.SidebarUtils;
import javafx.embed.swing.JFXPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

class AcrolinxSwingIntegration implements AcrolinxIntegration
{
    private final Logger logger = LoggerFactory.getLogger(AcrolinxSwingIntegration.class);
    private final JTextArea textArea = new JTextArea();
    private final JFrame frame = new JFrame("Acrolinx Demo Client Swing");

    AcrolinxSwingIntegration()
    {
        //
    }

    void initAcrolinxIntegration()
    {
        this.textArea.setPreferredSize(new Dimension(550, 600));

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Container contentPanel = frame.getContentPane();
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new FlowLayout());
        innerPanel.setPreferredSize(new Dimension(870, 600));
        innerPanel.add(this.textArea);

        JFXPanel sidebar = new AcrolinxSidebarSwing(this, 600);
        innerPanel.add(sidebar);

        innerPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        contentPanel.add(innerPanel);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public InputAdapterInterface getEditorAdapter()
    {
        return new TextAreaAdapter(this.textArea);
    }

    @Override
    public AcrolinxSidebarInitParameter getInitParameters()
    {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        ArrayList<SoftwareComponent> softwareComponents = new ArrayList<>();
        softwareComponents.add(
                new SoftwareComponent("com.acrolinx.client.sidebar.demo.swing", "Acrolinx Demo Client Swing", "1.0",
                SoftwareComponentCategory.MAIN));
        return new AcrolinxSidebarInitParameter.AcrolinxSidebarInitParameterBuilder(
                "SW50ZWdyYXRpb25EZXZlbG9wbWVudERlbW9Pbmx5", softwareComponents).
                withShowServerSelector(true).withServerAddress("https://integration.acrolinx-cloud.com:443").build();
    }

    @Override
    public void onCheckResult(CheckResult checkResult)
    {
        logger.debug("Got check result");
    }

    @Override
    public void openWindow(String url)
    {
        SidebarUtils.openWebPageInDefaultBrowser(url);
    }

    @Override
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public void onInitFinished(Optional<SidebarError> initResult)
    {
        logger.debug("Finished init!");
    }

}
