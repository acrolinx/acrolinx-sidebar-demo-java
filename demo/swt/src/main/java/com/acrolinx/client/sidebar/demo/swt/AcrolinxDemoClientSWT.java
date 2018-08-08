/* Copyright (c) 2016-2018 Acrolinx GmbH */

package com.acrolinx.client.sidebar.demo.swt;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;
import com.acrolinx.sidebar.pojo.settings.InputFormat;
import com.acrolinx.sidebar.pojo.settings.SoftwareComponent;
import com.acrolinx.sidebar.pojo.settings.SoftwareComponentCategory;
import com.acrolinx.sidebar.swt.AcrolinxSidebarSWT;
import com.acrolinx.sidebar.swt.adapter.TextAdapter;
import com.acrolinx.sidebar.utils.IconUtils;
import com.acrolinx.sidebar.utils.LoggingUtils;

@SuppressWarnings("WeakerAccess")
public class AcrolinxDemoClientSWT
{

    private final Display display = new Display();
    private final Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE);

    AcrolinxDemoClientSWT()
    {
        try {
            LoggingUtils.setupLogging("AcrolinxDemoClientSWT");
        } catch (final Exception e) {
            e.printStackTrace();
        }

        shell.setSize(930, 620);
        shell.setText("Acrolinx Demo Client SWT");

        final GridLayout gridLayout = new GridLayout(2, true);
        shell.setLayout(gridLayout);
        final Image small = new Image(display, IconUtils.getAcrolinxIcon_16_16_AsStream());
        shell.setImage(small);

        final Text textArea = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP);
        final GridData left = new GridData();
        left.widthHint = 600;
        left.heightHint = 600;
        left.grabExcessVerticalSpace = true;

        textArea.setLayoutData(left);

        final GridData right = new GridData();
        right.widthHint = 300;
        right.heightHint = 600;
        right.grabExcessVerticalSpace = true;

        final ArrayList<SoftwareComponent> softwareComponents = new ArrayList<>();
        softwareComponents.add(new SoftwareComponent("com.acrolinx.client.sidebar.demo.swt", "Acrolinx Demo Client SWT",
                "1.0", SoftwareComponentCategory.MAIN));
        final AcrolinxSidebarInitParameter initParameters = new AcrolinxSidebarInitParameter.AcrolinxSidebarInitParameterBuilder(
                "SW50ZWdyYXRpb25EZXZlbG9wbWVudERlbW9Pbmx5", softwareComponents).withShowServerSelector(true).build();

        final TextAdapter textAdapter = new TextAdapter(textArea, InputFormat.TEXT, "samplefilename");

        final AcrolinxSWTIntegration client = new AcrolinxSWTIntegration(initParameters, textAdapter);

        final AcrolinxSidebarSWT sidebarSWT = new AcrolinxSidebarSWT(shell, client);
        sidebarSWT.getSidebarBrowser().setLayoutData(right);

        open();
    }

    public void open()
    {
        final MessageBox warning = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
        warning.setText("Warning");
        warning.setMessage(
                "This is not a feature complete demo of the Java SDK, please have a look into the Java FX Demo.");

        /*
         * Floating Sidebar is here: ChildShell childShell = new ChildShell(shell, display);
         * AcrolinxSidebarSWT sidebarSWT = new AcrolinxSidebarSWT(childShell.getShell(), 600);
         * childShell.getShell().open();
         */

        shell.layout();
        shell.open();
        warning.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();

    }

    public static void main(final String[] args)
    {
        final AcrolinxDemoClientSWT demo = new AcrolinxDemoClientSWT();
        demo.open();
    }
}
