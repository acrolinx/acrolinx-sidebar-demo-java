/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar.jfx.adapter;

import java.util.List;
import javafx.scene.control.TextArea;

import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatch;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatchWithReplacement;
import com.acrolinx.sidebar.pojo.settings.InputFormat;
import com.acrolinx.sidebar.utils.MatchComparator;

@SuppressWarnings("unused")
public class TextAreaAdapter implements InputAdapterInterface
{
    private final TextArea textArea;
    private InputFormat inputFormat;
    private String documentReference;

    public TextAreaAdapter(TextArea textArea)
    {
        this.textArea = textArea;
        this.inputFormat = InputFormat.TEXT;
    }

    public TextAreaAdapter(TextArea textArea, @SuppressWarnings("SameParameterValue") InputFormat inputFormat)
    {
        this.textArea = textArea;
        this.inputFormat = inputFormat;
    }

    public TextAreaAdapter(TextArea textArea, InputFormat inputFormat, String documentReference)
    {
        this.textArea = textArea;
        this.inputFormat = inputFormat;
        this.documentReference = documentReference;
    }

    @Override
    public synchronized InputFormat getInputFormat()
    {
        return inputFormat;
    }

    public synchronized void setInputFormat(InputFormat inputFormat)
    {
        this.inputFormat = inputFormat;
    }

    @Override
    public synchronized String getContent()
    {
        return textArea.getText();
    }

    @Override
    public synchronized String getDocumentReference()
    {
        return documentReference;
    }

    public synchronized void setDocumentReference(String documentReference)
    {
        this.documentReference = documentReference;
    }

    @Override
    public synchronized void selectRanges(String checkId, List<AcrolinxMatch> matches)
    {
        int minRange = matches.get(0).getRange().getMinimumInteger();
        int maxRange = matches.get(matches.size() - 1).getRange().getMaximumInteger();
        textArea.selectRange(minRange, maxRange);
    }

    @Override
    public synchronized void replaceRanges(String checkId, List<AcrolinxMatchWithReplacement> matches)
    {
        matches.stream().sorted(new MatchComparator().reversed()).forEach(match -> textArea.replaceText(
                match.getRange().getMinimumInteger(), match.getRange().getMaximumInteger(), match.getReplacement()));
    }
}
