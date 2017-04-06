/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar.pojo.document;

@SuppressWarnings("unused") public class CheckResultFromJSON
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
