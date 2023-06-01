/* Copyright (c) 2018-present Acrolinx GmbH */
package com.acrolinx.client.sidebar.demo.swt;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.pojo.SidebarError;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;
import com.acrolinx.sidebar.pojo.settings.BatchCheckRequestOptions;
import com.acrolinx.sidebar.pojo.settings.CheckOptions;

public class AcrolinxSWTIntegration implements AcrolinxIntegration
{
    private static final Logger logger = LoggerFactory.getLogger(AcrolinxSWTIntegration.class);

    private final AcrolinxSidebarInitParameter initParameters;
    private final InputAdapterInterface inputAdapterInterface;

    public AcrolinxSWTIntegration(final AcrolinxSidebarInitParameter initParameters,
            final InputAdapterInterface inputAdapterInterface)
    {
        this.initParameters = initParameters;
        this.inputAdapterInterface = inputAdapterInterface;
    }

    @Override
    public InputAdapterInterface getEditorAdapter()
    {
        return this.inputAdapterInterface;
    }

    @Override
    public AcrolinxSidebarInitParameter getInitParameters()
    {
        return this.initParameters;
    }

    @Override
    public void onCheckResult(final CheckResult checkResult)
    {
        logger.debug(checkResult.getCheckedDocumentPart().getCheckId());
    }

    @Override
    public void onInitFinished(final Optional<SidebarError> initResult)
    {
        logger.debug("{}", initResult);
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
