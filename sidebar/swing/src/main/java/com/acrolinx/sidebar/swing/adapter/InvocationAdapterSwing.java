package com.acrolinx.sidebar.swing.adapter;

import com.acrolinx.sidebar.InvocationAdapterInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class InvocationAdapterSwing implements InvocationAdapterInterface
{
    final private Logger logger = LoggerFactory.getLogger(InvocationAdapterSwing.class);

    @Override public void invokeSave(Runnable runnable)
    {
        try {
            SwingUtilities.invokeLater(runnable);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
