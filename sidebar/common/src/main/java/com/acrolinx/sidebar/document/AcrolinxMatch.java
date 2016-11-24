package com.acrolinx.sidebar.document;

import org.apache.commons.lang.math.IntRange;

public class AcrolinxMatch
{
    private final String content;
    private IntRange extractedRange;
    private final IntRange range;

    public AcrolinxMatch(IntRange range, String content)
    {
        this.content = content;
        this.range = range;
    }

    public AcrolinxMatch(IntRange range, IntRange extractedRange, String content)
    {
        this.range = range;
        this.extractedRange = extractedRange;
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    public IntRange getExtractedRange()
    {
        return extractedRange;
    }

    public IntRange getRange()
    {
        return range;
    }
}
