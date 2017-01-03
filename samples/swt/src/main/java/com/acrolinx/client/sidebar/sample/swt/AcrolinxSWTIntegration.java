package com.acrolinx.client.sidebar.sample.swt;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.InvocationAdapterInterface;
import com.acrolinx.sidebar.pojo.InitResult;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @Override public InputAdapterInterface getEditorAdapter()
    {
        return this.inputAdapterInterface;
    }

    // Invocation adapter is not needed for SWT-Applications
    @Override public InvocationAdapterInterface getInvocationAdapter()
    {
        return null;
    }

    @Override public AcrolinxSidebarInitParameter getInitParameters()
    {
        return this.initParameters;
    }

    @Override public void onCheckResult(CheckResult checkResult)
    {
        logger.debug(checkResult.getCheckedDocumentPart().getCheckId());

    }

    @Override public void openWindow(String url)
    {
        // SidebarUtils.openWebpageInDefaultBrowser(url);
    }

    @Override public void onInitFinished(InitResult initResult)
    {
        logger.debug(initResult.toString());

    }
}
