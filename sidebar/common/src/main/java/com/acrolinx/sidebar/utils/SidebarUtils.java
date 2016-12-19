package com.acrolinx.sidebar.utils;

import com.acrolinx.sidebar.pojo.document.CheckedDocumentPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.net.URI;

public class SidebarUtils
{
    private static final Logger logger = LoggerFactory.getLogger(SidebarUtils.class);

    public static void openWebpageInDefaultBrowser(String url)
    {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                URI uri = new URI(url);
                desktop.browse(uri);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    public static String getCheckedDocumentPartsAsString(CheckedDocumentPart[] checkedDocumentParts)
    {
        String parts = "[";
        for (CheckedDocumentPart part : checkedDocumentParts) {
            parts += checkedDocumentParts.toString();
            parts += ", ";
        }
        String substring = parts.substring(0, parts.length() - 2);
        return substring += "]";
    }
}
