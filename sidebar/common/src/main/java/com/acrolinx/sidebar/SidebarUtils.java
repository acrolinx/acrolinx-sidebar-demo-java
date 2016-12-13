package com.acrolinx.sidebar;

import java.awt.*;
import java.net.URI;

public class SidebarUtils
{
    public static void openWebpageInDefaultBrowser(String url)
    {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                URI uri = new URI(url);
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
