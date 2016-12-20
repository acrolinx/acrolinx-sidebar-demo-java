package com.acrolinx.sidebar;

import com.acrolinx.sidebar.pojo.InitResult;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.document.DownloadInfo;
import com.acrolinx.sidebar.pojo.settings.AcrolinxPluginConfiguration;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;

public interface AcrolinxIntegration
{

    InputAdapterInterface getEditorAdapter();

    InvocationAdapterInterface getInvocationAdapter();

    AcrolinxSidebarInitParameter getInitParameters();

    void onCheckResult(CheckResult checkResult);

    void openWindow(String url);

    void configure(AcrolinxPluginConfiguration pluginConfiguration);

    void onInitFinished(InitResult initResult);

    void download(DownloadInfo downloadInfo);
}
