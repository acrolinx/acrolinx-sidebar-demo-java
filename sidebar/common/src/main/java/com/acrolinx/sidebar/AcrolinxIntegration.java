package com.acrolinx.sidebar;

import com.acrolinx.sidebar.pojo.InitResult;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.document.DownloadInfo;
import com.acrolinx.sidebar.pojo.settings.AcrolinxPluginConfiguration;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;

/**
 * This interface needs be implemented to integrate Acrolinx with an editor or editing environment.
 * These methods are called by the Acrolinx Sidebar to interact with the editor.
 */
public interface AcrolinxIntegration
{

    /**
     * Adapter to extract the text to be checked. It needs to be an implementation of InputAdapterInterface
     *
     * @return implementation of InputAdapterInterface
     * @see InputAdapterInterface
     */
    InputAdapterInterface getEditorAdapter();

    /**
     * Gets the parameters used to initialize the Acrolinx Sidebar.
     * @return AcrolinxInitParamter
     * @see AcrolinxSidebarInitParameter
     */
    AcrolinxSidebarInitParameter getInitParameters();

    /**
     * Is needed for JFX and Swing Applications to invoke methods in threads.
     *
     * @return
     */
    InvocationAdapterInterface getInvocationAdapter();

    /**
     * Notifies the Acrolinx Integration about the checks result.
     * @param checkResult
     */
    void onCheckResult(CheckResult checkResult);

    /**
     * Is called to open an URL in a new window.
     * This is needed to display the Acrolinx Scorecard in your integration.
     * @param url
     */
    void openWindow(String url);

    /**
     * Notifies the AcrolinxIntegration about the capabilities of the Acrolinx Sidebar.
     * @param pluginConfiguration
     */
    void configure(AcrolinxPluginConfiguration pluginConfiguration);

    /**
     * Notifies the Acrolinx Integration about the result of the initializing process.
     * @param initResult
     * @see InitResult
     */
    void onInitFinished(InitResult initResult);

    /**
     * Called by the Acrolinx Sidebar to download assets.
     * @param downloadInfo
     * @see DownloadInfo
     */
    void download(DownloadInfo downloadInfo);
}
