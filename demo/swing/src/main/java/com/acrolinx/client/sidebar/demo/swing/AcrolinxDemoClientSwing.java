/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.client.sidebar.demo.swing;

class AcrolinxDemoClientSwing
{
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(() -> {
            AcrolinxSwingIntegration integration = new AcrolinxSwingIntegration();
            integration.initAcrolinxIntegration();
        });
    }
}
