/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.math.IntRange;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;

import com.acrolinx.sidebar.LookupRanges;
import com.acrolinx.sidebar.pojo.document.AbstractMatch;

public class LookupRangesDiff extends LookupRanges
{

    @Override
    public Optional<List<? extends AbstractMatch>> getMatchesWithCorrectedRanges(String checkedText, String changedText,
            List<? extends AbstractMatch> matches)
    {
        LinkedList<DiffMatchPatch.Diff> diffs = Lookup.getDiffs(checkedText, changedText);
        LinkedList<OffsetAlign> offsetMappingArray = Lookup.createOffsetMappingArray(diffs);
        boolean anyEmpty = matches.stream().anyMatch(match -> {
            Optional<IntRange> correctedMatch = Lookup.getCorrectedMatch(diffs, offsetMappingArray,
                    match.getRange().getMinimumInteger(), match.getRange().getMaximumInteger());
            if (correctedMatch.isPresent()) {
                match.setRange(correctedMatch.get());
                return false;
            } else {
                return true;
            }
        });

        if (anyEmpty) {
            return Optional.empty();
        }
        return Optional.of(matches);
    }
}
