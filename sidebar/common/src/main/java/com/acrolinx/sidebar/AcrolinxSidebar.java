/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar;

import com.acrolinx.sidebar.pojo.document.CheckedDocumentPart;
import com.acrolinx.sidebar.pojo.settings.CheckOptions;
import com.acrolinx.sidebar.pojo.settings.SidebarConfiguration;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * These commands are available to interact with the Acrolinx Sidebar.
 */
public interface AcrolinxSidebar
{
    /**
     * Pushed the configuration to the Acrolinx Sidebar.
     *
     * @param configuration
     * @see SidebarConfiguration
     */
    void configure(SidebarConfiguration configuration);

    /**
     * Pushes a check request to the Acrolinx Sidebar.
     *
     * @param documentContent
     * @param options
     * @return String containing the checkId assigned with the requested check
     */
    CompletableFuture<String> checkGlobal(String documentContent, CheckOptions options);

    /**
     * Notifies the sidebar that the check was canceled.
     */
    void onGlobalCheckRejected();

    /**
     * Notifies the sidebar about invalid ranges of the current document.
     * The sidebar will then invalidate all cards containing issues within this text range.
     *
     * @param invalidCheckedDocumentRanges
     */
    void invalidateRanges(List<CheckedDocumentPart> invalidCheckedDocumentRanges);
}
