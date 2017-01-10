/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar.utils;

import org.apache.commons.lang.math.IntRange;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Optional;

import static com.acrolinx.sidebar.utils.Lookup.*;
import static org.junit.Assert.assertEquals;

public class LookupTest
{
    @Test
    public void offsetDiffIsNullForEqualStrings()
    {
        String first = "TEST";
        String second = "TEST";
        LinkedList<DiffMatchPatch.Diff> diffs = getDiffs(first, second);
        LinkedList<OffsetAlign> offsetMappingArray = createOffsetMappingArray(diffs);
        assert offsetMappingArray.size() == 1;
        assertEquals(0, offsetMappingArray.getFirst().getDiffOffset());
        assertEquals(4, offsetMappingArray.getFirst().getOldPosition());
    }

    @Test
    public void offsetDiffIsCalculatedProperlyForInsertion()
    {
        String original = "This is a test.";
        String changed = "This is a manipulated test.";
        LinkedList<DiffMatchPatch.Diff> diffs = getDiffs(original, changed);
        LinkedList<OffsetAlign> offsetMappingArray = createOffsetMappingArray(diffs);
        assert offsetMappingArray.size() == 3;
        assertEquals(0, offsetMappingArray.get(0).getDiffOffset());
        assertEquals(10, offsetMappingArray.get(0).getOldPosition());
        assertEquals(12, offsetMappingArray.get(1).getDiffOffset());
        assertEquals(10, offsetMappingArray.get(1).getOldPosition());
        assertEquals(12, offsetMappingArray.get(2).getDiffOffset());
        assertEquals(15, offsetMappingArray.get(2).getOldPosition());
    }

    @Test
    public void offsetDiffIsCalculatedProperlyForDeletion()
    {
        String original = "This is a test.";
        String changed = "This is test.";
        LinkedList<DiffMatchPatch.Diff> diffs = getDiffs(original, changed);
        LinkedList<OffsetAlign> offsetMappingArray = createOffsetMappingArray(diffs);
        assertEquals(3, offsetMappingArray.size());
        assertEquals(0, offsetMappingArray.get(0).getDiffOffset());
        assertEquals(8, offsetMappingArray.get(0).getOldPosition());
        assertEquals(-2, offsetMappingArray.get(1).getDiffOffset());
        assertEquals(10, offsetMappingArray.get(1).getOldPosition());
        assertEquals(-2, offsetMappingArray.get(2).getDiffOffset());
        assertEquals(15, offsetMappingArray.get(2).getOldPosition());
    }

    @Test
    public void getCorrectedMatchTest()
    {
        String original = "This is a test.";
        String changed = "The manipulated test.";
        String changed2 = "<strong>This was a good test.</strong>";
        String changed3 = "The test is great.";
        assertEquals(Optional.of(new IntRange(0, 2)), getCorrectedMatch(original, changed, 0, 2));
        assertEquals(Optional.empty(), getCorrectedMatch(original, changed, 0, 3));
        assertEquals(Optional.empty(), getCorrectedMatch(original, changed, 5, 7));
        assertEquals(Optional.empty(), getCorrectedMatch(original, changed, 8, 15));
        assertEquals(Optional.of(new IntRange(15, 21)), getCorrectedMatch(original, changed, 9, 15));
        assertEquals(Optional.of(new IntRange(8, 12)), getCorrectedMatch(original, changed2, 0, 4));
        assertEquals(Optional.of(new IntRange(17, 18)), getCorrectedMatch(original, changed2, 8, 9));
        assertEquals(Optional.empty(), getCorrectedMatch(original, changed3, 8, 9));
    }
}