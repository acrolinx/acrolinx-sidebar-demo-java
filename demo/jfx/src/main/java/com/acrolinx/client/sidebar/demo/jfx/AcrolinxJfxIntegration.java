/* Copyright (c) 2018-present Acrolinx GmbH */
package com.acrolinx.client.sidebar.demo.jfx;

import java.util.List;
import java.util.Optional;

import javafx.scene.control.TextArea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.pojo.SidebarError;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;
import com.acrolinx.sidebar.pojo.settings.BatchCheckRequestOptions;
import com.acrolinx.sidebar.pojo.settings.CheckOptions;

class AcrolinxJfxIntegration implements AcrolinxIntegration
{
    private static final Logger logger = LoggerFactory.getLogger(AcrolinxJfxIntegration.class);

    private final TextArea textArea;
    private final AcrolinxSidebarInitParameter acrolinxSidebarInitParameter;

    AcrolinxJfxIntegration(final TextArea textArea, final AcrolinxSidebarInitParameter acrolinxSidebarInitParameter)
    {
        this.textArea = textArea;
        this.acrolinxSidebarInitParameter = acrolinxSidebarInitParameter;
    }

    @Override
    public InputAdapterInterface getEditorAdapter()
    {
        return new JfxTextAdapterWithLookUp(textArea, AcrolinxDemoClientJfx.inputFormat.get(), "sampleFile.txt");
    }

    @Override
    public AcrolinxSidebarInitParameter getInitParameters()
    {
        return this.acrolinxSidebarInitParameter;
    }

    @Override
    public void onCheckResult(final CheckResult checkResult)
    {
        logger.debug("Got check result for check id: {}", checkResult.getCheckedDocumentPart().getCheckId());
    }

    @Override
    public void onInitFinished(final Optional<SidebarError> sidebarError)
    {
        logger.debug("Sidebar init done: {}", sidebarError);
    }

    @Override
    public boolean openDocumentInEditor(String documentIdentifier)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<BatchCheckRequestOptions> extractReferences()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public CheckOptions getCheckOptionsForDocument(String documentIdentifier)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getContentForDocument(String documentIdentifier)
    {
        throw new UnsupportedOperationException();
    }
}