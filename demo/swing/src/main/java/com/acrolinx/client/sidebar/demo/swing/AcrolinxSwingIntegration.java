/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.client.sidebar.demo.swing;

import java.util.Optional;

import javax.swing.JTextArea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.pojo.SidebarError;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;
import com.acrolinx.sidebar.pojo.settings.InputFormat;
import com.acrolinx.sidebar.swing.adapter.TextAreaAdapter;

class AcrolinxSwingIntegration implements AcrolinxIntegration
{
    private final Logger logger = LoggerFactory.getLogger(AcrolinxSwingIntegration.class);
    private final JTextArea textArea;
    private final AcrolinxSidebarInitParameter initParameter;

    AcrolinxSwingIntegration(final AcrolinxSidebarInitParameter initParameter, final JTextArea textArea)
    {
        this.initParameter = initParameter;
        this.textArea = textArea;
    }

    @Override
    public InputAdapterInterface getEditorAdapter()
    {
        return new TextAreaAdapter(this.textArea, InputFormat.TEXT, "sampleFileName");
    }

    @Override
    public AcrolinxSidebarInitParameter getInitParameters()
    {
        return this.initParameter;
    }

    @Override
    public void onCheckResult(final CheckResult checkResult)
    {
        logger.debug("Got check result");
    }

    @Override
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public void onInitFinished(final Optional<SidebarError> initResult)
    {
        logger.debug("Finished init!");
    }

}
