/* Copyright (c) 2017-present Acrolinx GmbH */

package com.acrolinx.client.sidebar.demo.jfx;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.pojo.SidebarError;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;
import com.acrolinx.sidebar.pojo.settings.BatchCheckRequestOptions;
import com.acrolinx.sidebar.pojo.settings.CheckOptions;

import javafx.scene.control.TextArea;

class AcrolinxJFXIntegration implements AcrolinxIntegration
{
    private static final Logger logger = LoggerFactory.getLogger(AcrolinxJFXIntegration.class);

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
        logger.debug("Got check result for check id: {}", checkResult.getCheckedDocumentPart().getCheckId());
    }

    @Override
    public void onInitFinished(final Optional<SidebarError> initResult)
    {
        logger.debug("Sidebar init done: {}", initResult);
    }

    @Override
    public boolean openDocumentInEditor(String documentIdentifier)
    {
        throw new NotImplementedException();
    }

    @Override
    public List<BatchCheckRequestOptions> extractReferences()
    {
        throw new NotImplementedException();
    }

    @Override
    public CheckOptions getCheckOptionsForDocument(String documentIdentifier)
    {
        throw new NotImplementedException();
    }

    @Override
    public String getContentForDocument(String documentIdentifier)
    {
        throw new NotImplementedException();
    }
}
