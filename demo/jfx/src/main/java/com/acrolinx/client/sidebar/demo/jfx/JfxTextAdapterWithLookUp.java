/* Copyright (c) 2018-present Acrolinx GmbH */
package com.acrolinx.client.sidebar.demo.jfx;

import com.acrolinx.sidebar.jfx.adapter.TextAreaAdapter;
import com.acrolinx.sidebar.lookup.LookupRangesDiff;
import com.acrolinx.sidebar.pojo.document.AbstractMatch;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatch;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatchWithReplacement;
import com.acrolinx.sidebar.pojo.settings.InputFormat;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.TextArea;

class JfxTextAdapterWithLookUp extends TextAreaAdapter
{
    JfxTextAdapterWithLookUp(final TextArea textArea, final InputFormat inputFormat, final String documentReference)
    {
        super(textArea, inputFormat, documentReference);
    }

    @Override
    public synchronized void replaceRanges(final String checkId, final List<AcrolinxMatchWithReplacement> matches)
    {
        final List<? extends AbstractMatch> correctedMatches = lookupRanges(matches);

        if (correctedMatches != null) {
            super.replaceRanges(checkId, (List<AcrolinxMatchWithReplacement>) correctedMatches);
        }
    }

    @Override
    public synchronized void selectRanges(final String checkId, final List<AcrolinxMatch> acrolinxMatches)
    {
        final List<? extends AbstractMatch> correctedMatches = lookupRanges(acrolinxMatches);

        if (correctedMatches != null) {
            super.selectRanges(checkId, (List<AcrolinxMatch>) correctedMatches);
        }
    }

    private List<? extends AbstractMatch> lookupRanges(final List<? extends AbstractMatch> abstractMatches)
    {
        final String lastCheckedDocument = AcrolinxDemoClientJfx.acrolinxSidebar.get().getLastCheckedDocument();
        final LookupRangesDiff lookupRangesDiff = new LookupRangesDiff();
        final Optional<List<? extends AbstractMatch>> correctedRanges = lookupRangesDiff.getMatchesWithCorrectedRanges(
                lastCheckedDocument, getContent(), abstractMatches);

        if (correctedRanges.isPresent()) {
            return correctedRanges.get();
        }

        AcrolinxDemoClientJfx.acrolinxSidebar.get().invalidateRangesForMatches(abstractMatches);
        return null;
    }
}
