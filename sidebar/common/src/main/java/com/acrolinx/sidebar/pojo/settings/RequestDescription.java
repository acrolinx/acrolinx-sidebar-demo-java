package com.acrolinx.sidebar.pojo.settings;

import com.google.gson.Gson;

class RequestDescription
{
    private final String documentReference;

    public RequestDescription(String documentReference)
    {
        this.documentReference = documentReference;
    }

    @Override public String toString()
    {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
