/* Copyright (c) 2017-present Acrolinx GmbH */


package com.acrolinx.client.sidebar.demo.jfx;

import java.util.Optional;

import javafx.scene.control.TextArea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.pojo.SidebarError;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;

class AcrolinxJFXIntegration implements AcrolinxIntegration
{

    private final Logger logger = LoggerFactory.getLogger(AcrolinxJFXIntegration.class);
    private final TextArea textArea;
    private final AcrolinxSidebarInitParameter initParameter;

    AcrolinxJFXIntegration(final TextArea textArea, final AcrolinxSidebarInitParameter initParameter)
    {
        this.textArea = textArea;
        this.initParameter = initParameter;
    }

    @Override
    public InputAdapterInterface getEditorAdapter()
    {
        return new JFXTextAdapterWithLookUp(textArea, AcrolinxDemoClientJFX.inputFormat.get(), "sampleFile.txt");
    }

    @Override
    public AcrolinxSidebarInitParameter getInitParameters()
    {
        return this.initParameter;
    }

    @Override
    public void onCheckResult(final CheckResult checkResult)
    {
        logger.debug("Got check result for check id: " + checkResult.getCheckedDocumentPart().getCheckId());
        // Do nothing for now;
    }

    @Override
    public void onInitFinished(
            @SuppressWarnings("OptionalUsedAsFieldOrParameterType") final Optional<SidebarError> initResult)
    {
        logger.debug("Sidebar init done: " + initResult.toString());
        // Do nothing for now;
    }
}
