package com.acrolinx.sidebar.pojo.settings;

import com.google.gson.Gson;

import java.util.Optional;

/**
 * Check options define how the Acrolinx Server handles document to check.
 */
public class CheckOptions
{
    private InputFormat inputFormat;
    private Boolean base64EncodedGzipped;
    private RequestDescription requestDescription;

    // TODO (fp) refactor optionals

    /**
     * @param inputFormat Check InputFormat for valid formats.
     * @param base64EncodedGzipped Check AcrolinxPluginConfiguration to see if this option is available.
     * @param requestDescription Contains the document reference.
     */
    public CheckOptions(Optional<InputFormat> inputFormat, Optional<Boolean> base64EncodedGzipped,
            Optional<RequestDescription> requestDescription)
    {
        if (inputFormat.isPresent()) {
            this.inputFormat = inputFormat.get();
        }
        if (base64EncodedGzipped.isPresent()) {
            this.base64EncodedGzipped = base64EncodedGzipped.get();
        }
        if (requestDescription.isPresent()) {
            this.requestDescription = requestDescription.get();
        }
    }

    @Override public String toString()
    {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
