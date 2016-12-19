package com.acrolinx.sidebar.pojo;

public class SidebarError extends Error
{
    private final String code;

    public SidebarError(String message, String code)
    {
        super(message);
        this.code = code;
    }

    public String getErrorCode()
    {
        return code;
    }
}
