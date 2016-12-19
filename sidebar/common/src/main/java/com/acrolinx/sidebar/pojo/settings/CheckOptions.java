package com.acrolinx.sidebar.pojo.settings;

import com.google.gson.Gson;

import java.util.Optional;

public class CheckOptions
{
    private InputFormat inputFormat;
    private Boolean base64EncodedGzipped;
    private RequestDescription requestDescription;

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
