package com.acrolinx.sidebar;

import com.acrolinx.sidebar.document.CheckResult;
import com.acrolinx.sidebar.settings.AcrolinxPluginConfiguration;
import com.acrolinx.sidebar.settings.AcrolinxSidebarInitParemeters;

public interface AcrolinxIntegration
{

    InputAdapterInterface getEditorAdapter();

    InvocationAdapterInterface getInvocationAdapter();

    AcrolinxSidebarInitParemeters getInitParameters();

    void onCheckResult(CheckResult checkResult);

    void openWindow(String url);

    void configure(AcrolinxPluginConfiguration pluginConfiguration);

    void onInitFinished(InitResult initResult);

    //void requestGlobalCheck();

    //void requestInit();

    //void openWindow(URL url);*/

    //download()
    // configure()
}
