package com.acrolinx.sidebar.pojo.settings;

import com.google.gson.Gson;

public class SoftwareComponent
{
    private final String id;
    private final String name;
    private final String version;
    private SoftwareComponentCategory category;

    /**
     * @param id
     * @param name
     * @param version
     */
    public SoftwareComponent(String id, String name, String version)
    {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    /**
     * @param id
     * @param name
     * @param version
     * @param category
     */
    public SoftwareComponent(String id, String name, String version, SoftwareComponentCategory category)
    {
        this.id = id;
        this.name = name;
        this.version = version;
        this.category = category;
    }

    @Override
    public String toString()
    {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
