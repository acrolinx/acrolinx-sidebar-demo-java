package com.acrolinx.sidebar;

import com.acrolinx.sidebar.pojo.document.CheckedDocumentPart;
import com.acrolinx.sidebar.pojo.settings.CheckOptions;
import com.acrolinx.sidebar.pojo.settings.SidebarConfiguration;

import java.util.concurrent.CompletableFuture;

public interface AcrolinxSidebar
{
    void configure(SidebarConfiguration configuration);

    CompletableFuture<String> checkGlobal(String documentContent, CheckOptions options);

    void onGlobalCheckRejected();

    void invalidateRanges(CheckedDocumentPart[] invalidCheckedDocumentRanges);

    void onVisibleRangesChanged(CheckedDocumentPart[] checkedDocumentRanges);
}
