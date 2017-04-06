/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar.pojo.document;

import org.apache.commons.lang.math.IntRange;

@SuppressWarnings("unused")
public class AcrolinxMatch extends AbstractMatch
{
    private final String content;
    private IntRange extractedRange;
    private IntRange range;

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

    @Override
    public void setRange(IntRange range)
    {
        this.range = range;
    }
}
