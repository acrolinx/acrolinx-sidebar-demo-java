/* Copyright (c) 2018 Acrolinx GmbH */
package com.acrolinx.client.sidebar.demo.swt;

import ch.qos.logback.core.joran.spi.JoranException;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter.AcrolinxSidebarInitParameterBuilder;
import com.acrolinx.sidebar.pojo.settings.InputFormat;
import com.acrolinx.sidebar.pojo.settings.SoftwareComponent;
import com.acrolinx.sidebar.pojo.settings.SoftwareComponentCategory;
import com.acrolinx.sidebar.swt.AcrolinxSidebarSWT;
import com.acrolinx.sidebar.swt.adapter.TextAdapter;
import com.acrolinx.sidebar.utils.IconUtils;
import com.acrolinx.sidebar.utils.LoggingUtils;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AcrolinxDemoClientSwt {
  public static void main(String[] args) throws IOException, JoranException {
    new AcrolinxDemoClientSwt().open();
  }

  private static AcrolinxSidebarInitParameter createAcrolinxSidebarInitParameter() {
    return new AcrolinxSidebarInitParameterBuilder(
            "SW50ZWdyYXRpb25EZXZlbG9wbWVudERlbW9Pbmx5", createSoftwareComponents())
        .withShowServerSelector(true)
        .build();
  }

  private static AcrolinxSwtIntegration createAcrolinxSwtIntegration(TextAdapter textAdapter) {
    return new AcrolinxSwtIntegration(createAcrolinxSidebarInitParameter(), textAdapter);
  }

  private static Image createImage(Display display) {
    return new Image(display, IconUtils.getAcrolinxIcon_16_16_AsStream());
  }

  private static GridData createLeftGridData() {
    GridData gridData = new GridData();
    gridData.widthHint = 600;
    gridData.heightHint = 600;
    gridData.grabExcessVerticalSpace = true;
    return gridData;
  }

  private static GridData createRightGridData() {
    GridData gridData = new GridData();
    gridData.widthHint = 300;
    gridData.heightHint = 600;
    gridData.grabExcessVerticalSpace = true;
    return gridData;
  }

  private static Shell createShell(Display display) {
    Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE);
    shell.setSize(930, 620);
    shell.setText("Acrolinx Demo Client SWT");
    shell.setLayout(new GridLayout(2, true));
    shell.setImage(createImage(display));
    return shell;
  }

  private static List<SoftwareComponent> createSoftwareComponents() {
    return Collections.singletonList(
        new SoftwareComponent(
            "com.acrolinx.client.sidebar.demo.swt",
            "Acrolinx Demo Client SWT",
            "1.0",
            SoftwareComponentCategory.MAIN));
  }

  private static TextAdapter createTextAdapter(Text text) {
    return new TextAdapter(text, InputFormat.TEXT, "samplefilename");
  }

  private final Display display = new Display();
  private final Shell shell = createShell(display);

  AcrolinxDemoClientSwt() throws IOException, JoranException {
    LoggingUtils.setupLogging("AcrolinxDemoClientSwt");

    AcrolinxSidebarSWT acrolinxSidebarSwt =
        new AcrolinxSidebarSWT(
            shell, createAcrolinxSwtIntegration(createTextAdapter(createText())));
    acrolinxSidebarSwt.getSidebarBrowser().setLayoutData(createRightGridData());

    open();
  }

  private Text createText() {
    Text text = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP);
    text.setLayoutData(createLeftGridData());
    return text;
  }

  private void open() {
    MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
    messageBox.setText("Warning");
    messageBox.setMessage(
        "This is not a feature complete demo of the Java SDK, please have a look into the Java FX Demo.");

    shell.layout();
    shell.open();
    messageBox.open();

    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }

    display.dispose();
  }
}
