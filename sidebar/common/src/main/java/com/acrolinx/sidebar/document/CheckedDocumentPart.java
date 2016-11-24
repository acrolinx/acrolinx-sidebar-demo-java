package com.acrolinx.sidebar.document;

import org.apache.commons.lang.math.IntRange;

public class CheckedDocumentPart
{
    private final String checkId;
    private final IntRange range;

    public CheckedDocumentPart(String checkId, IntRange range)
    {
        this.checkId = checkId;
        this.range = range;
    }

    public String getCheckId()
    {
        return checkId;
    }

    public IntRange getRange()
    {
        return range;
    }
}
