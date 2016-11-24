package com.acrolinx.sidebar.jfx.adapter;

import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.document.AcrolinxMatch;
import com.acrolinx.sidebar.document.AcrolinxMatchWithReplacement;
import com.acrolinx.sidebar.settings.InputFormat;
import com.google.common.base.Joiner;
import javafx.scene.control.TextArea;

import java.util.List;
import java.util.stream.Collectors;

public class TextAreaAdapter implements InputAdapterInterface
{
    public final TextArea textArea;
    public InputFormat inputFormat;

    public TextAreaAdapter(TextArea textArea)
    {
        this.textArea = textArea;
        this.inputFormat = InputFormat.TEXT;
    }

    public TextAreaAdapter(TextArea textArea, InputFormat inputFormat)
    {
        this.textArea = textArea;
        this.inputFormat = inputFormat;
    }

    @Override public InputFormat getInputFormat()
    {
        return inputFormat;
    }

    public void setInputFormat(InputFormat inputFormat)
    {
        this.inputFormat = inputFormat;
    }

    @Override public String getContent()
    {
        return textArea.getText();
    }

    @Override public void selectRanges(String checkId, List<AcrolinxMatch> matches)
    {
        int minRange = matches.get(0).getRange().getMinimumInteger();
        int maxRange = matches.get(matches.size() - 1).getRange().getMaximumInteger();
        textArea.selectRange(minRange, maxRange);
    }

    @Override public void replaceRanges(String checkId, List<AcrolinxMatchWithReplacement> matchesWithReplacement)
    {
        int minRange = matchesWithReplacement.get(0).getRange().getMinimumInteger();
        int maxRange = matchesWithReplacement.get(matchesWithReplacement.size() - 1).getRange().getMaximumInteger();

        String replacement = Joiner.on("").join(
                matchesWithReplacement.stream().map(o -> o.getReplacement()).collect(Collectors.toList()));
        textArea.replaceText(minRange, maxRange, replacement);
    }
}
