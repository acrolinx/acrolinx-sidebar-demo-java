package com.acrolinx.sidebar.pojo.document;

import org.apache.commons.lang.math.IntRange;

public class AcrolinxMatchWithReplacement extends AcrolinxMatch
{
    private final String replacement;

    public AcrolinxMatchWithReplacement(String content, IntRange range, String replacement)
    {
        super(range, content);
        this.replacement = replacement;
    }

    public AcrolinxMatchWithReplacement(IntRange range, IntRange extractedRange, String content, String replacement)
    {
        super(range, extractedRange, content);
        this.replacement = replacement;
    }

    public String getReplacement()
    {
        return replacement;
    }
}
