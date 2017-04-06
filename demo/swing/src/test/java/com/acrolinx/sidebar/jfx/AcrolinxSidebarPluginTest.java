/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar.jfx;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AcrolinxSidebarPluginTest
{
    @Test
    public void testInitPluginWithNull()
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