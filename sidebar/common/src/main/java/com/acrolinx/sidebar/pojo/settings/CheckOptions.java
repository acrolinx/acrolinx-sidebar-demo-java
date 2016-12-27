package com.acrolinx.sidebar.pojo.settings;

import com.google.gson.Gson;

/**
 * Check options define how the Acrolinx Server handles document to check.
 */
public class CheckOptions
{
    private InputFormat inputFormat;
    private Boolean base64EncodedGzipped;
    private RequestDescription requestDescription;

    /**
     * @param inputFormat Check InputFormat for valid formats.
     * @param base64EncodedGzipped Check AcrolinxPluginConfiguration to see if this option is available.
     * @param requestDescription Contains the document reference.
     */
    public CheckOptions(RequestDescription requestDescription, Boolean base64EncodedGzipped, InputFormat inputFormat)
    {
        this.requestDescription = requestDescription;
        this.base64EncodedGzipped = base64EncodedGzipped;
        this.inputFormat = inputFormat;
    }

    @Override public String toString()
    {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
