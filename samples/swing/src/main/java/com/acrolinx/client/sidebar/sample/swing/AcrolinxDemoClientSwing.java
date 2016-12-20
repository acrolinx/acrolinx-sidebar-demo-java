package com.acrolinx.client.sidebar.sample.swing;

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
