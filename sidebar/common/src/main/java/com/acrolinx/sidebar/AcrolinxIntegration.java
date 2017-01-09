package com.acrolinx.sidebar;

import com.acrolinx.sidebar.pojo.SidebarError;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;

import java.util.Optional;

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
     *
     * @return AcrolinxInitParameter
     * @see AcrolinxSidebarInitParameter
     */
    AcrolinxSidebarInitParameter getInitParameters();

    /**
     * Notifies the Acrolinx Integration about the checks result.
     *
     * @param checkResult
     */
    void onCheckResult(CheckResult checkResult);

    /**
     * Is called to open an URL in a new window.
     * This is needed to display the Acrolinx Scorecard in your integration.
     *
     * @param url
     */
    void openWindow(String url);

    /**
     * Notifies the Acrolinx Integration about the result of the initializing process.
     *
     * @param initResult
     */
    void onInitFinished(@SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<SidebarError> initResult);
}
