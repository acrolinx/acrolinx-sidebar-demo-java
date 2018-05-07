
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

@SuppressWarnings("unchecked")
class JFXTextAdapterWithLookUp extends TextAreaAdapter
{

    JFXTextAdapterWithLookUp(final TextArea textArea, final InputFormat inputFormat, final String documentReference)
    {
        super(textArea, inputFormat, documentReference);
    }

    private List<? extends AbstractMatch> lookupRanges(final List<? extends AbstractMatch> matches)
    {
        final String lastCheckedDocument = AcrolinxDemoClientJFX.sidebar.get().getLastCheckedDocument();
        final LookupRangesDiff lookup = new LookupRangesDiff();
        final Optional<List<? extends AbstractMatch>> correctedRanges = lookup.getMatchesWithCorrectedRanges(
                lastCheckedDocument, this.getContent(), matches);
        if (correctedRanges.isPresent()) {
            return correctedRanges.get();
        } else {
            AcrolinxDemoClientJFX.sidebar.get().invalidateRangesForMatches(matches);
            return null;
        }
    }

    @Override
    public void selectRanges(final String checkId, final List<AcrolinxMatch> matches)
    {
        final List<? extends AbstractMatch> correctedMatches = lookupRanges(matches);
        if (correctedMatches != null) {
            super.selectRanges(checkId, (List<AcrolinxMatch>) correctedMatches);
        }

    }

    @Override
    public void replaceRanges(final String checkId, final List<AcrolinxMatchWithReplacement> matches)
    {
        final List<? extends AbstractMatch> correctedMatches = lookupRanges(matches);
        if (correctedMatches != null) {
            super.replaceRanges(checkId, (List<AcrolinxMatchWithReplacement>) correctedMatches);
        }
    }
}
