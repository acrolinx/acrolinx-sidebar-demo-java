/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.net.URI;

public class SidebarUtils
{
    private static final Logger logger = LoggerFactory.getLogger(SidebarUtils.class);

    /**
     * Opens the given URL in the default Browser of the current OS.
     * Note that this method is likely to cause JVM crashes within swt based applications!
     *
     * @param url
     */
    public static void openWebPageInDefaultBrowser(String url)
    {

        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            new Thread(() -> {
                try {
                    URI uri = new URI(url);
                    Desktop.getDesktop().browse(uri);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }).start();
        } else {
            logger.error("Desktop is not available to get systems default browser.");
        }
    }
}
