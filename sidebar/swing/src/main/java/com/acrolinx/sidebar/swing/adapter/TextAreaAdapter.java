package com.acrolinx.sidebar.swing.adapter;

import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatch;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatchWithReplacement;
import com.acrolinx.sidebar.pojo.settings.InputFormat;
import com.google.common.base.Joiner;
import org.apache.commons.lang.math.IntRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override public String getDocumentReference()
    {
        return documentReference;
    }

    public void setDocumentReference(String documentReference)
    {
        this.documentReference = documentReference;
    }

    @Override public void selectRanges(String checkId, List<AcrolinxMatch> matches, Optional<IntRange> correctedRange)
    {
        correctedRange.ifPresent(range -> {
            int minRange = range.getMinimumInteger();
            int maxRange = range.getMaximumInteger();
            Highlighter h = textArea.getHighlighter();
            h.removeAllHighlights();
            try {
                h.addHighlight(minRange, maxRange, DefaultHighlighter.DefaultPainter);
            } catch (BadLocationException e) {
                logger.error(e.getMessage());
            }
        });
    }

    @Override public void replaceRanges(String checkId, List<AcrolinxMatchWithReplacement> matchesWithReplacement,
            Optional<IntRange> correctedRange)
    {
        correctedRange.ifPresent(range -> {
            int minRange = range.getMinimumInteger();
            int maxRange = range.getMaximumInteger();

            String replacement = Joiner.on("").join(
                    matchesWithReplacement.stream().map(AcrolinxMatchWithReplacement::getReplacement).collect(
                            Collectors.toList()));
            textArea.replaceRange(replacement, minRange, maxRange);
            Highlighter h = textArea.getHighlighter();
            h.removeAllHighlights();
            try {
                h.addHighlight(minRange, minRange + replacement.length(), DefaultHighlighter.DefaultPainter);
            } catch (BadLocationException e) {
                logger.error(e.getMessage());
            }
        });
    }
}
