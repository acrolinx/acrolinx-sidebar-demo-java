package com.acrolinx.sidebar.document;

import com.acrolinx.sidebar.SidebarError;

public class CheckError extends SidebarError
{
    private final String checkId;

    public CheckError(String message, String code, String checkId)
    {
        super(message, code);
        this.checkId = checkId;
    }

    public String getCheckId()
    {
        return checkId;
    }

}
