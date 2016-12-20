package com.acrolinx.client.sidebar.sample.swt;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.InvocationAdapterInterface;
import com.acrolinx.sidebar.pojo.InitResult;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.document.DownloadInfo;
import com.acrolinx.sidebar.pojo.settings.AcrolinxPluginConfiguration;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;

public class AcrolinxSWTIntegration implements AcrolinxIntegration
{
    final private AcrolinxSidebarInitParameter initParemeters;
    final private InputAdapterInterface inputAdapterInterface;

    public AcrolinxSWTIntegration(AcrolinxSidebarInitParameter initParemeters,
            InputAdapterInterface inputAdapterInterface)
    {
        this.initParemeters = initParemeters;
        this.inputAdapterInterface = inputAdapterInterface;
    }

    @Override public InputAdapterInterface getEditorAdapter()
    {
        return this.inputAdapterInterface;
    }

    @Override public InvocationAdapterInterface getInvocationAdapter()
    {
        return null;
    }

    @Override public AcrolinxSidebarInitParameter getInitParameters()
    {
        return this.initParemeters;
    }

    @Override public void onCheckResult(CheckResult checkResult)
    {

    }

    @Override public void openWindow(String url)
    {

    }

    @Override public void configure(AcrolinxPluginConfiguration pluginConfiguration)
    {

    }

    @Override public void onInitFinished(InitResult initResult)
    {

    }

    @Override public void download(DownloadInfo downloadInfo)
    {

    }
}
