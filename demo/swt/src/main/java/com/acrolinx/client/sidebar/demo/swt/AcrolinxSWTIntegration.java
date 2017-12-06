/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.client.sidebar.demo.swt;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.pojo.SidebarError;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;

@SuppressWarnings("WeakerAccess")
public class AcrolinxSWTIntegration implements AcrolinxIntegration
{
    final private AcrolinxSidebarInitParameter initParameters;
    final private InputAdapterInterface inputAdapterInterface;

    private final Logger logger = LoggerFactory.getLogger(AcrolinxSWTIntegration.class);

    public AcrolinxSWTIntegration(AcrolinxSidebarInitParameter initParameters,
            InputAdapterInterface inputAdapterInterface)
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
    public void onCheckResult(CheckResult checkResult)
    {
        logger.debug(checkResult.getCheckedDocumentPart().getCheckId());

    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @Override
    public void onInitFinished(Optional<SidebarError> initResult)
    {
        logger.debug(initResult.toString());

    }
}
