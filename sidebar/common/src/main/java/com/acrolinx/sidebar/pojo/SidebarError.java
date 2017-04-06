/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar.pojo;

/**
 * Error Object returned if an error occurred within the Acrolinx Sidebar.
 */
@SuppressWarnings("unused") public class SidebarError extends Error
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
