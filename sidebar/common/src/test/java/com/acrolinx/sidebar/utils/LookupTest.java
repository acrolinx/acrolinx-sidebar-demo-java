/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar.utils;

import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.junit.Test;

import java.util.LinkedList;

import static com.acrolinx.sidebar.utils.Lookup.createOffsetMappingArray;
import static com.acrolinx.sidebar.utils.Lookup.getDiffs;
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
}