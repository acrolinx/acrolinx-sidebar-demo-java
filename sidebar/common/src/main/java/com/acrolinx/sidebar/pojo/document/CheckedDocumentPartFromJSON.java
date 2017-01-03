package com.acrolinx.sidebar.pojo.document;

import org.apache.commons.lang.math.IntRange;

public class CheckedDocumentPartFromJSON
{
    private String checkId;
    private int[] range;

    public CheckedDocumentPartFromJSON()
    {
        //
    }

    public CheckedDocumentPart getAsCheckResult()
    {
        return new CheckedDocumentPart(checkId, new IntRange(range[0], range[1]));
    }
}
