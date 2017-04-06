/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar.swing.adapter;

import java.util.List;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatch;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatchWithReplacement;
import com.acrolinx.sidebar.pojo.settings.InputFormat;
import com.acrolinx.sidebar.utils.MatchComparator;

@SuppressWarnings("WeakerAccess")
public class TextAreaAdapter implements InputAdapterInterface
{
    private final JTextArea textArea;
    private InputFormat inputFormat;
    private String documentReference;
    final private Logger logger = LoggerFactory.getLogger(TextAreaAdapter.class);

    public TextAreaAdapter(JTextArea textArea)
    {
        this.textArea = textArea;
        this.inputFormat = InputFormat.TEXT;
    }

    public TextAreaAdapter(JTextArea textArea, InputFormat inputFormat)
    {
        this.textArea = textArea;
        this.inputFormat = inputFormat;
    }

    public TextAreaAdapter(JTextArea textArea, InputFormat inputFormat, String documentReference)
    {
        this.textArea = textArea;
        this.inputFormat = inputFormat;
        this.documentReference = documentReference;
    }

    @Override
    public InputFormat getInputFormat()
    {
        return inputFormat;
    }

    public void setInputFormat(InputFormat inputFormat)
    {
        this.inputFormat = inputFormat;
    }

    @Override
    public String getContent()
    {
        return textArea.getText();
    }

    @Override
    public String getDocumentReference()
    {
        return documentReference;
    }

    public void setDocumentReference(String documentReference)
    {
        this.documentReference = documentReference;
    }

    @Override
    public void selectRanges(String checkId, List<AcrolinxMatch> matches)
    {
        int minRange = matches.get(0).getRange().getMinimumInteger();
        int maxRange = matches.get(matches.size() - 1).getRange().getMaximumInteger();
        Highlighter h = textArea.getHighlighter();
        h.removeAllHighlights();
        try {
            h.addHighlight(minRange, maxRange, DefaultHighlighter.DefaultPainter);
        } catch (BadLocationException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void replaceRanges(String checkId, List<AcrolinxMatchWithReplacement> matches)
    {
        matches.stream().sorted(new MatchComparator().reversed()).forEach(match -> {
            int minRange = match.getRange().getMinimumInteger();
            int maxRange = match.getRange().getMaximumInteger();
            String replacement = match.getReplacement();
            Highlighter h = textArea.getHighlighter();
            h.removeAllHighlights();
            textArea.replaceRange(replacement, minRange, maxRange);
        });
    }
}
