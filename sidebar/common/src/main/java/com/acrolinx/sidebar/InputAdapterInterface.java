/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar;

import com.acrolinx.sidebar.pojo.document.AcrolinxMatch;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatchWithReplacement;
import com.acrolinx.sidebar.pojo.settings.InputFormat;

import java.util.List;

/**
 * This interface serves to interact with the current editor.
 */
public interface InputAdapterInterface
{

    /**
     * Receives the current input format. If this method returns null,
     * the check will be canceled by the sidebar.
     *
     * @return Returns the current input format.
     * @see InputFormat
     */
    InputFormat getInputFormat();

    /**
     * Receives the current text from the editor.
     *
     * @return Returns the text to be checked.
     */
    String getContent();

    /**
     * The path or filename of the document to check. In a CMS it can be the id that is used to look up the document.
     */
    String getDocumentReference();

    /**
     * Is called by the Acrolinx Integration to highlight current issues in the text editor.
     *
     * @param checkId The current check id.
     * @param matches The ranges to be highlighted as sent by the AcrolinxServer.
     */
    void selectRanges(String checkId, List<AcrolinxMatch> matches);

    /**
     * Is called by the Acrolinx Integration to replace found issues with suggestions from the Acrolinx Sidebar.
     * If the Acrolinx Sidebar is configured as read only this method wont be called.
     *
     * @param checkId The current check id.
     * @param matchesWithReplacement The ranges to be replaced.
     */
    void replaceRanges(String checkId, List<AcrolinxMatchWithReplacement> matchesWithReplacement);
}
