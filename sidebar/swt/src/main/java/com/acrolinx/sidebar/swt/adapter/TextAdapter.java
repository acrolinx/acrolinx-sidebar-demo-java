/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar.swt.adapter;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.swt.widgets.Text;

import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatch;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatchWithReplacement;
import com.acrolinx.sidebar.pojo.settings.InputFormat;
import com.acrolinx.sidebar.utils.MatchComparator;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class TextAdapter implements InputAdapterInterface
{
    final private Text textWidget;
    private String documentReference;
    private InputFormat inputFormat;

    public TextAdapter(Text textWidget)
    {
        this.textWidget = textWidget;
    }

    public TextAdapter(Text textWidget, InputFormat inputFormat)
    {

        this.textWidget = textWidget;
        this.inputFormat = inputFormat;
    }

    public TextAdapter(Text textWidget, InputFormat inputFormat, String documentReference)
    {

        this.textWidget = textWidget;
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
        return textWidget.getText();
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
        textWidget.clearSelection();
        textWidget.setSelection(minRange, maxRange);
    }

    @Override
    public void replaceRanges(String checkId, List<AcrolinxMatchWithReplacement> matches)
    {
        AtomicReference<String> text = new AtomicReference<>(textWidget.getText());
        matches.stream().sorted(new MatchComparator().reversed()).forEach(match -> {
            int minRange = match.getRange().getMinimumInteger();
            int maxRange = match.getRange().getMaximumInteger();
            String replacement = match.getReplacement();
            String t = text.get();
            text.set(t.substring(0, minRange) + replacement + t.substring(maxRange));
        });
        textWidget.clearSelection();
        textWidget.setText(text.get());
    }
}
