package com.acrolinx.sidebar.pojo.settings;

import com.google.gson.Gson;

import java.util.Optional;

public class SoftwareComponent
{
    private final String id;
    private final String name;
    private final String version;
    private SoftwareComponentCategory category;

    public SoftwareComponent(String id, String name, String version, Optional<SoftwareComponentCategory> category)
    {
        this.id = id;
        this.name = name;
        this.version = version;
        if (category.isPresent()) {
            this.category = category.get();
        }
    }

    @Override public String toString()
    {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
