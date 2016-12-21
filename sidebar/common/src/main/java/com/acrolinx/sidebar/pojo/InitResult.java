package com.acrolinx.sidebar.pojo;

import java.util.Optional;

/**
 * These object will be returned after the Acrolinx Sidebar initialized.
 * If everything worked the error object will be empty.
 */
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
