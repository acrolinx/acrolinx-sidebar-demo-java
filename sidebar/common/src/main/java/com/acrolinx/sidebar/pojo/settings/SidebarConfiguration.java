package com.acrolinx.sidebar.pojo.settings;

import com.google.gson.Gson;

/**
 * Available configuration for the Acrolinx Sidebar
 */
public class SidebarConfiguration
{
    private final Boolean readOnlySuggestion;

    /**
     * The Acrolinx Sidebar can be configured to only show suggestion but to not be clickable.
     * This can be useful if the checked context should not be modified by the Acrolinx Sidebar.
     *
     * @param readOnlySuggestion If set to `true` the suggestions won't be clickable and not modify the content.
     */
    public SidebarConfiguration(Boolean readOnlySuggestion)
    {

        this.readOnlySuggestion = readOnlySuggestion;
    }

    @Override
    public String toString()
    {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
