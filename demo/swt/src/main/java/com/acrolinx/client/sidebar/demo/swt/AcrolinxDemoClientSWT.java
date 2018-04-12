/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

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

import com.acrolinx.sidebar.pojo.settings.*;
import com.acrolinx.sidebar.swt.AcrolinxSidebarSWT;
import com.acrolinx.sidebar.swt.adapter.TextAdapter;
import com.acrolinx.sidebar.utils.IconUtils;
import com.acrolinx.sidebar.utils.LoggingUtils;

@SuppressWarnings("WeakerAccess")
public class AcrolinxDemoClientSWT
{

    final Display display = new Display();

    AcrolinxDemoClientSWT()
    {
        try {
            LoggingUtils.setupLogging("AcrolinxDemoClientSWT");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE);
        shell.setSize(930, 620);
        shell.setText("Acrolinx Demo Client SWT");

        GridLayout gridLayout = new GridLayout(2, true);
        shell.setLayout(gridLayout);
        Image small = new Image(display, IconUtils.getAcrolinxIcon_16_16_AsStream());
        shell.setImage(small);

        Text textArea = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP);
        GridData left = new GridData();
        left.widthHint = 600;
        left.heightHint = 600;
        left.grabExcessVerticalSpace = true;

        textArea.setLayoutData(left);

        GridData right = new GridData();
        right.widthHint = 300;
        right.heightHint = 600;
        right.grabExcessVerticalSpace = true;

        ArrayList<SoftwareComponent> softwareComponents = new ArrayList<>();
        softwareComponents.add(new SoftwareComponent("com.acrolinx.client.sidebar.demo.swt", "Acrolinx Demo Client SWT",
                "1.0", SoftwareComponentCategory.MAIN));
        AcrolinxSidebarInitParameter initParameters = new AcrolinxSidebarInitParameter.AcrolinxSidebarInitParameterBuilder(
                "SW50ZWdyYXRpb25EZXZlbG9wbWVudERlbW9Pbmx5", softwareComponents).withShowServerSelector(true).build();

        TextAdapter textAdapter = new TextAdapter(textArea, InputFormat.TEXT, "samplefilename");

        AcrolinxSWTIntegration client = new AcrolinxSWTIntegration(initParameters, textAdapter);

        AcrolinxSidebarSWT sidebarSWT = new AcrolinxSidebarSWT(shell, client);
        sidebarSWT.getSidebarBrowser().setLayoutData(right);

        MessageBox warning = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
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
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();

    }

    public static void main(String[] args)
    {
        new AcrolinxDemoClientSWT();
    }
}
