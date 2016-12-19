package com.acrolinx.sidebar.pojo.settings;

public class AcrolinxPluginConfiguration
{

    private final boolean base64EncodedGzippedDocumentContent;

    public AcrolinxPluginConfiguration(boolean base64EncodedGzippedDocumentContent)
    {
        this.base64EncodedGzippedDocumentContent = base64EncodedGzippedDocumentContent;
    }

    public boolean getBase64EncodedGzippedDocumentContent()
    {
        return base64EncodedGzippedDocumentContent;
    }

}
