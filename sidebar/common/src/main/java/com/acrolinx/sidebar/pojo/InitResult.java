package com.acrolinx.sidebar.pojo;

import java.util.Optional;

public class InitResult
{
    private final Optional<SidebarError> sidebarError;

    public InitResult(Optional<SidebarError> sidebarError)
    {
        this.sidebarError = sidebarError;
    }

    public Optional<SidebarError> getSidebarError()
    {
        return sidebarError;
    }
}
