package com.acrolinx.sidebar.jfx.adapter;

import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatch;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatchWithReplacement;
import com.acrolinx.sidebar.pojo.settings.InputFormat;
import com.google.common.base.Joiner;
import javafx.scene.control.TextArea;
import org.apache.commons.lang.math.IntRange;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType") public class TextAreaAdapter implements InputAdapterInterface
{
    private final TextArea textArea;
    private InputFormat inputFormat;
    private String documentReference;

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
    public synchronized void selectRanges(String checkId, List<AcrolinxMatch> matches,
            @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<IntRange> correctedRange)
    {
        correctedRange.ifPresent(range -> {
            int minRange = range.getMinimumInteger();
            int maxRange = range.getMaximumInteger();
            textArea.selectRange(minRange, maxRange);
        });
    }

    @Override
    public synchronized void replaceRanges(String checkId, List<AcrolinxMatchWithReplacement> matchesWithReplacement,
            Optional<IntRange> correctedRange)
    {
        correctedRange.ifPresent(range -> {
            int minRange = range.getMinimumInteger();
            int maxRange = range.getMaximumInteger();

            String replacement = Joiner.on("").join(
                    matchesWithReplacement.stream().map(AcrolinxMatchWithReplacement::getReplacement).collect(
                            Collectors.toList()));
            textArea.replaceText(minRange, maxRange, replacement);
        });
    }
}
