package com.acrolinx.sidebar.settings;

import com.google.gson.Gson;

public class SidebarConfiguration
{
    private final Boolean readOnlySuggestion;

    public SidebarConfiguration(Boolean readOnlySuggestion)
    {
        this.readOnlySuggestion = readOnlySuggestion;
    }

    @Override public String toString()
    {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
