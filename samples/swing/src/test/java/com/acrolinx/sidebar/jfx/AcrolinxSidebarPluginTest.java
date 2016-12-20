package com.acrolinx.sidebar.jfx;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AcrolinxSidebarPluginTest
{
    @Test public void testInitPluginWithNull()
    {
        Throwable e = null;

        try {
            new AcrolinxSidebarPlugin(null, null);
        } catch (Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof NullPointerException);
    }

}