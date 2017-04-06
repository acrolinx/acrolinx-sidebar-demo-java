/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar;

import java.util.Optional;

import com.acrolinx.sidebar.pojo.SidebarError;
import com.acrolinx.sidebar.pojo.document.CheckResult;
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
     * Returns the lookup implementation to correct ranges in edited text.
     * @return Implementation of LookupRanges class
     */
    LookupRanges getLookup();

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

    /**
     * This method will be called from the sidebar, when a check was requested.
     * If it returns true the check will be done, otherwise it will be canceled.
     *
     * @return
     */
    @SuppressWarnings("SameReturnValue")
    boolean canCheck();
}
