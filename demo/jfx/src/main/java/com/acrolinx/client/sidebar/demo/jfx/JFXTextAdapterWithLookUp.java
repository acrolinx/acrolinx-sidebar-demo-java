
package com.acrolinx.client.sidebar.demo.jfx;

import java.util.List;
import java.util.Optional;
import javafx.scene.control.TextArea;

import com.acrolinx.sidebar.jfx.adapter.TextAreaAdapter;
import com.acrolinx.sidebar.lookup.LookupRangesDiff;
import com.acrolinx.sidebar.pojo.document.AbstractMatch;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatch;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatchWithReplacement;
import com.acrolinx.sidebar.pojo.settings.InputFormat;

public class JFXTextAdapterWithLookUp extends TextAreaAdapter
{

    public JFXTextAdapterWithLookUp(TextArea textArea, InputFormat inputFormat, String documentReference)
    {
        super(textArea, inputFormat, documentReference);
    }

    private List<? extends AbstractMatch> lookupRanges(List<? extends AbstractMatch> matches)
    {
        String lastCheckedDocument = AcrolinxDemoClientJFX.sidebar.get().getLastCheckedDocument();
        LookupRangesDiff lookup = new LookupRangesDiff();
        Optional<List<? extends AbstractMatch>> correctedRanges = lookup.getMatchesWithCorrectedRanges(
                lastCheckedDocument, this.getContent(), matches);
        if (correctedRanges.isPresent()) {
            return correctedRanges.get();
        } else {
            AcrolinxDemoClientJFX.sidebar.get().invalidateRangesForMatches(matches);
            return null;
        }
    }

    public void selectRanges(String checkId, List<AcrolinxMatch> matches)
    {
        List<? extends AbstractMatch> correctedMatches = lookupRanges(matches);
        if (correctedMatches != null)
            super.selectRanges(checkId, (List<AcrolinxMatch>) correctedMatches);

    }

    public void replaceRanges(String checkId, List<AcrolinxMatchWithReplacement> matches)
    {
        List<? extends AbstractMatch> correctedMatches = lookupRanges(matches);
        if (correctedMatches != null) {
            super.replaceRanges(checkId, (List<AcrolinxMatchWithReplacement>) correctedMatches);
        }
    }
}
