package com.acrolinx.sidebar.pojo.settings;

import com.google.gson.Gson;

import java.util.ArrayList;

public class AcrolinxSidebarInitParameter
{
    public static final String SIDEBAR_URL = "https://sidebar-classic.acrolinx-cloud.com/v14/prod/index.html";
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

    private AcrolinxSidebarInitParameter(AcrolinxSidebarInitParameterBuilder builder)
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

    public static class AcrolinxSidebarInitParameterBuilder
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

        public AcrolinxSidebarInitParameterBuilder(String clientSignature,
                ArrayList<SoftwareComponent> clientComponents)
        {
            this.clientSignature = clientSignature;
            this.clientComponents = clientComponents;
        }

        public AcrolinxSidebarInitParameterBuilder withServerAddress(String serverAddress)
        {
            this.serverAddress = serverAddress;
            return this;
        }

        public AcrolinxSidebarInitParameterBuilder withSidebarUrl(String sidebarUrl)
        {
            this.sidebarUrl = sidebarUrl;
            return this;
        }

        public AcrolinxSidebarInitParameterBuilder withClientLocale(String clientLocale)
        {
            this.clientLocale = clientLocale;
            return this;
        }

        public AcrolinxSidebarInitParameterBuilder withShowServerSelector(Boolean showServerSelector)
        {
            this.showServerSelector = showServerSelector;
            return this;
        }

        public AcrolinxSidebarInitParameterBuilder withCheckSettings(CheckSettings checkSettings)
        {
            this.checkSettings = checkSettings;
            return this;
        }

        public AcrolinxSidebarInitParameterBuilder withDefaultCheckSettings(CheckSettings defaultCheckSettings)
        {
            this.defaultCheckSettings = defaultCheckSettings;
            return this;
        }

        public AcrolinxSidebarInitParameterBuilder withEnableSingleSignOn(Boolean enableSingleSignOn)
        {
            this.enableSingleSignOn = enableSingleSignOn;
            return this;
        }

        public AcrolinxSidebarInitParameterBuilder withEnforceHTTPS(Boolean enforceHTTPS)
        {
            this.enforceHTTPS = enforceHTTPS;
            return this;
        }

        public AcrolinxSidebarInitParameter build()
        {
            return new AcrolinxSidebarInitParameter(this);
        }
    }
}


