/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.client.sidebar.demo.swt;

import java.util.Optional;

import org.eclipse.swt.program.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.LookupRanges;
import com.acrolinx.sidebar.pojo.SidebarError;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;
import com.acrolinx.sidebar.utils.LookupRangesDiff;

@SuppressWarnings("WeakerAccess")
public class AcrolinxSWTIntegration implements AcrolinxIntegration
{
    final private AcrolinxSidebarInitParameter initParameters;
    final private InputAdapterInterface inputAdapterInterface;
    final private LookupRangesDiff lookup = new LookupRangesDiff();

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
    public LookupRanges getLookup()
    {
        return lookup;
    }

    @Override
    public void onCheckResult(CheckResult checkResult)
    {
        logger.debug(checkResult.getCheckedDocumentPart().getCheckId());

    }

    @Override
    public void openWindow(String url)
    {
        try {
            Program.launch(url);
        } catch (Exception e) {
            logger.debug("Could not open URL: " + url);
            logger.debug(e.getMessage());
        }
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @Override
    public void onInitFinished(Optional<SidebarError> initResult)
    {
        logger.debug(initResult.toString());

    }

    @Override
    public boolean canCheck()
    {
        return true;
    }
}
