package com.acrolinx.sidebar.swt.adapter;

import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatch;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatchWithReplacement;
import com.acrolinx.sidebar.pojo.settings.InputFormat;
import com.google.common.base.Joiner;
import org.apache.commons.lang.math.IntRange;
import org.eclipse.swt.widgets.Text;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType") public class TextAdapter implements InputAdapterInterface
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
    public void selectRanges(String checkId, List<AcrolinxMatch> matches, Optional<IntRange> correctedRange)
    {
        correctedRange.ifPresent(range -> {
            textWidget.clearSelection();
            textWidget.setSelection(range.getMinimumInteger(), range.getMaximumInteger());
        });
    }

    @Override
    public void replaceRanges(String checkId, List<AcrolinxMatchWithReplacement> matchesWithReplacement,
            Optional<IntRange> correctedRange)
    {
        correctedRange.ifPresent(range -> {
            int minRange = range.getMinimumInteger();
            int maxRange = range.getMaximumInteger();
            String replacement = Joiner.on("").join(
                    matchesWithReplacement.stream().map(AcrolinxMatchWithReplacement::getReplacement).collect(
                            Collectors.toList()));

            textWidget.clearSelection();
            String text = textWidget.getText();
            String replacedText = text.substring(0, minRange) + replacement + text.substring(maxRange);
            textWidget.setText(replacedText);
        });
    }
}
