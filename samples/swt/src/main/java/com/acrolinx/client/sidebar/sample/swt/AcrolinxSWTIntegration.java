package com.acrolinx.client.sidebar.sample.swt;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.InvocationAdapterInterface;
import com.acrolinx.sidebar.pojo.InitResult;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.document.DownloadInfo;
import com.acrolinx.sidebar.pojo.settings.AcrolinxPluginConfiguration;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParemeters;
import com.acrolinx.sidebar.pojo.settings.SidebarConfiguration;

public class AcrolinxSWTIntegration implements AcrolinxIntegration
{
    final private AcrolinxSidebarInitParemeters initParemeters;
    final private InputAdapterInterface inputAdapterInterface;

    public AcrolinxSWTIntegration(AcrolinxSidebarInitParemeters initParemeters,
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

    @Override public AcrolinxSidebarInitParemeters getInitParameters()
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

    @Override public SidebarConfiguration configureSidebar(SidebarConfiguration sidebarConfiguration)
    {
        return null;
    }
}
