package com.acrolinx.sidebar.pojo.document;

public class CheckResultFromJSON
{
    private CheckedDocumentPartFromJSON checkedPart;
    private CheckError error;

    CheckResultFromJSON()
    {
        //
    }

    public CheckResult getAsCheckResult()
    {
        return new CheckResult(checkedPart.getAsCheckResult(), error);
    }
}
