package com.acrolinx.sidebar.document;

public class CheckResult
{
    private final CheckedDocumentPart checkedDocumentPart;
    private CheckError checkError;

    public CheckResult(CheckedDocumentPart checkedDocumentPart)
    {
        this.checkedDocumentPart = checkedDocumentPart;
    }

    public CheckResult(CheckedDocumentPart checkedDocumentPart, CheckError checkError)
    {
        this.checkedDocumentPart = checkedDocumentPart;
    }

    public CheckedDocumentPart getCheckedDocumentPart()
    {
        return checkedDocumentPart;
    }

    public CheckError getCheckError()
    {
        return checkError;
    }
}
