/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar.pojo.settings;

import java.util.ArrayList;

import com.google.gson.Gson;

@SuppressWarnings({"FieldCanBeLocal", "CanBeFinal"})
public class AcrolinxSidebarInitParameter
{
    private static final String SIDEBAR_URL = "https://sidebar-classic.acrolinx-cloud.com/v14/prod/index.html";
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

    public void setServerAddress(String serverAddress)
    {
        this.serverAddress = serverAddress;
    }

    public void setShowServerSelector(boolean showServerSelector)
    {
        this.showServerSelector = showServerSelector;
    }

    @Override
    public String toString()
    {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @SuppressWarnings("SameParameterValue")
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

        /**
         * Class to build the parameters to initialize the Acrolinx Sidebar.
         * Two parameters have to be set, all others are optional.
         *
         * @param clientSignature This signature will be provided by Acrolinx once the integration got certified.
         * @param clientComponents This contains id, name and version of the Acrolinx integration as well as
         * other software components that where used.
         * @see SoftwareComponent
         */
        public AcrolinxSidebarInitParameterBuilder(String clientSignature,
                ArrayList<SoftwareComponent> clientComponents)
        {
            this.clientSignature = clientSignature;
            this.clientComponents = clientComponents;
        }

        /**
         * Configure the server that should be used to check the content.
         * If this is not set, set member 'showServerSelector' to true.
         *
         * @param serverAddress Address of the Acrolinx Server that is used to check the content.
         * @return Returns the AcrolinxInitParameterBuilder for chaining.
         */
        public AcrolinxSidebarInitParameterBuilder withServerAddress(String serverAddress)
        {
            this.serverAddress = serverAddress;
            return this;
        }

        /**
         * The url of the Acrolinx Sidebar.
         * If this is not set the publicly available Sidebar will be used by default.
         *
         * @param sidebarUrl
         * @return Returns the AcrolinxInitParameterBuilder for chaining.
         */
        public AcrolinxSidebarInitParameterBuilder withSidebarUrl(String sidebarUrl)
        {
            this.sidebarUrl = sidebarUrl;
            return this;
        }

        /**
         * By default the client locale is set to 'en'. It can be set to "fr", "de", etc.
         *
         * @param clientLocale Should be "en", "fr", "de", "sv", "ja", etc.
         * @return Returns the AcrolinxInitParameterBuilder for chaining.
         */
        public AcrolinxSidebarInitParameterBuilder withClientLocale(String clientLocale)
        {
            this.clientLocale = clientLocale;
            return this;
        }

        /**
         * If this parameter is set to 'true' the Acrolinx Sidebar will provide an input field to set an url
         * for the Acrolinx Server.
         *
         * @param showServerSelector
         * @return Returns the AcrolinxInitParameterBuilder for chaining.
         */
        public AcrolinxSidebarInitParameterBuilder withShowServerSelector(Boolean showServerSelector)
        {
            this.showServerSelector = showServerSelector;
            return this;
        }

        /**
         * This parameter defines the check settings that will apply to all triggered checks.
         * If this parameter is set, then the default check settings and the check settings saved by the user will be ignored.
         *
         * @param checkSettings
         * @return Returns the AcrolinxInitParameterBuilder for chaining.
         */
        public AcrolinxSidebarInitParameterBuilder withCheckSettings(CheckSettings checkSettings)
        {
            this.checkSettings = checkSettings;
            return this;
        }

        /**
         * These check settings will be used as the initial check settings when the Acrolinx Sidebar is used for
         * the first time.
         *
         * @param defaultCheckSettings
         * @return Returns the AcrolinxInitParameterBuilder for chaining.
         */
        public AcrolinxSidebarInitParameterBuilder withDefaultCheckSettings(CheckSettings defaultCheckSettings)
        {
            this.defaultCheckSettings = defaultCheckSettings;
            return this;
        }

        /**
         * If the Acrolinx Server is set up for single sign on, this parameter has to be set to 'true'
         * in order enable single sign on from the integration.
         *
         * @param enableSingleSignOn
         * @return Returns the AcrolinxInitParameterBuilder for chaining.
         */
        public AcrolinxSidebarInitParameterBuilder withEnableSingleSignOn(Boolean enableSingleSignOn)
        {
            this.enableSingleSignOn = enableSingleSignOn;
            return this;
        }

        /**
         * This setting will prevent any connection with an Acrolinx Server except via 'https'.
         *
         * @param enforceHTTPS
         * @return Returns the AcrolinxInitParameterBuilder for chaining.
         */
        public AcrolinxSidebarInitParameterBuilder withEnforceHTTPS(Boolean enforceHTTPS)
        {
            this.enforceHTTPS = enforceHTTPS;
            return this;
        }

        /**
         * Builds the AcrolinxInitParameters.
         *
         * @return Returns the AcrolinxInitParameters.
         */
        public AcrolinxSidebarInitParameter build()
        {
            return new AcrolinxSidebarInitParameter(this);
        }
    }
}
