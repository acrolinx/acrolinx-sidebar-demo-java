/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar.pojo.document;

import java.util.Optional;

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

    public Optional<CheckError> getCheckError()
    {
        return Optional.ofNullable(checkError);
    }
}
