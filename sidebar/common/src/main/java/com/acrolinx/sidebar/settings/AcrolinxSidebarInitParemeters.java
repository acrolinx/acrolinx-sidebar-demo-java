package com.acrolinx.sidebar.settings;

import com.google.gson.Gson;

import java.util.ArrayList;

public class AcrolinxSidebarInitParemeters
{
    private static String SIDEBAR_URL = "https://acrolinx-sidebar-classic.s3.amazonaws.com/v14/prod/index.html";
    private String serverAddress;
    private final String clientSignature;
    private String sidebarUrl;
    private String clientLocale;
    private final ArrayList<SoftwareComponent> clientComponents;
    private Boolean showServerSelector;
    private CheckSettings checkSettings;
    private CheckSettings defaultCheckSettings;
    private Boolean enableSingleSignOn;
    private Boolean enforceHTTPS;

    private AcrolinxSidebarInitParemeters(AcrolinxSidebarInitParemetersBuilder builder)
    {
        this.serverAddress = builder.serverAddress;
        this.clientSignature = builder.clientSignature;
        this.sidebarUrl = builder.sidebarUrl;
        this.clientLocale = builder.clientLocale;
        this.clientComponents = builder.clientComponents;
        this.showServerSelector = builder.showServerSelector;
        this.checkSettings = builder.checkSettings;
        this.defaultCheckSettings = builder.defaultCheckSettings;
        this.enableSingleSignOn = builder.enableSingleSignOn;
        this.enforceHTTPS = builder.enforceHTTPS;
    }

    public String getServerAddress()
    {
        return serverAddress;
    }

    public String getClientSignature()
    {
        return clientSignature;
    }

    public String getSidebarUrl()
    {
        if (sidebarUrl != null) {
            return sidebarUrl;
        } else
            return SIDEBAR_URL;
    }

    @Override public String toString()
    {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toJSString()
    {
        return this.toString();
    }

    public static class AcrolinxSidebarInitParemetersBuilder
    {
        private String serverAddress;
        private final String clientSignature;
        private String sidebarUrl;
        private String clientLocale;
        private final ArrayList<SoftwareComponent> clientComponents;
        private Boolean showServerSelector;
        private CheckSettings checkSettings;
        private CheckSettings defaultCheckSettings;
        private Boolean enableSingleSignOn;
        private Boolean enforceHTTPS;

        public AcrolinxSidebarInitParemetersBuilder(String clientSignature,
                ArrayList<SoftwareComponent> clientComponents)
        {
            this.clientSignature = clientSignature;
            this.clientComponents = clientComponents;
        }

        public AcrolinxSidebarInitParemetersBuilder withServerAddress(String serverAddress)
        {
            this.serverAddress = serverAddress;
            return this;
        }

        public AcrolinxSidebarInitParemetersBuilder withSidebarUrl(String sidebarUrl)
        {
            this.sidebarUrl = sidebarUrl;
            return this;
        }

        public AcrolinxSidebarInitParemetersBuilder withClientLocale(String clientLocale)
        {
            this.clientLocale = clientLocale;
            return this;
        }

        public AcrolinxSidebarInitParemetersBuilder withShowServerSelector(Boolean showServerSelector)
        {
            this.showServerSelector = showServerSelector;
            return this;
        }

        public AcrolinxSidebarInitParemetersBuilder withCheckSettings(CheckSettings checkSettings)
        {
            this.checkSettings = checkSettings;
            return this;
        }

        public AcrolinxSidebarInitParemetersBuilder withDefaultCheckSettings(CheckSettings defaultCheckSettings)
        {
            this.defaultCheckSettings = defaultCheckSettings;
            return this;
        }

        public AcrolinxSidebarInitParemetersBuilder withEnableSingleSignOn(Boolean enableSingleSignOn)
        {
            this.enableSingleSignOn = enableSingleSignOn;
            return this;
        }

        public AcrolinxSidebarInitParemetersBuilder withEnforceHTTPS(Boolean enforceHTTPS)
        {
            this.enforceHTTPS = enforceHTTPS;
            return this;
        }

        public AcrolinxSidebarInitParemeters build()
        {
            return new AcrolinxSidebarInitParemeters(this);
        }
    }
}


