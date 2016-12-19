package com.acrolinx.sidebar.swt.adapter;

import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatch;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatchWithReplacement;
import com.acrolinx.sidebar.pojo.settings.InputFormat;
import com.google.common.base.Joiner;
import org.eclipse.swt.widgets.Text;

import java.util.List;
import java.util.stream.Collectors;

public class TextAdapter implements InputAdapterInterface
{
    final private Text textWidget;

    public TextAdapter(Text textWidget)
    {
        this.textWidget = textWidget;
    }

    @Override public InputFormat getInputFormat()
    {
        return InputFormat.TEXT;
    }

    @Override public String getContent()
    {
        return textWidget.getText();
    }

    @Override public void selectRanges(String checkId, List<AcrolinxMatch> matches)
    {
        int minRange = matches.get(0).getRange().getMinimumInteger();
        int maxRange = matches.get(matches.size() - 1).getRange().getMaximumInteger();
        textWidget.clearSelection();
        textWidget.setSelection(minRange, maxRange);
    }

    @Override public void replaceRanges(String checkId, List<AcrolinxMatchWithReplacement> matchesWithReplacement)
    {
        int minRange = matchesWithReplacement.get(0).getRange().getMinimumInteger();
        int maxRange = matchesWithReplacement.get(matchesWithReplacement.size() - 1).getRange().getMaximumInteger();

        String replacement = Joiner.on("").join(
                matchesWithReplacement.stream().map(o -> o.getReplacement()).collect(Collectors.toList()));

        textWidget.clearSelection();
        String text = textWidget.getText();
        String replacedText = text.substring(0, minRange) + replacement + text.substring(maxRange);
        textWidget.setText(replacedText);
    }
}
