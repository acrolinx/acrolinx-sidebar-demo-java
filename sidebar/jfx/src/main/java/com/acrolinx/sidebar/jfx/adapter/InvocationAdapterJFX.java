package com.acrolinx.sidebar.jfx.adapter;

import com.acrolinx.sidebar.InvocationAdapterInterface;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvocationAdapterJFX implements InvocationAdapterInterface
{
    private final Logger logger = LoggerFactory.getLogger(InvocationAdapterJFX.class);

    @Override public void invokeSave(Runnable runnable)
    {
        try {
            Platform.runLater(runnable);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

}
