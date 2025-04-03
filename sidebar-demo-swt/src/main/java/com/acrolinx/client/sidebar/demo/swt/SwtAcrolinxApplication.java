/* Copyright (c) 2018 Acrolinx GmbH */
package com.acrolinx.client.sidebar.demo.swt;

import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter.AcrolinxSidebarInitParameterBuilder;
import com.acrolinx.sidebar.pojo.settings.InputFormat;
import com.acrolinx.sidebar.pojo.settings.SoftwareComponent;
import com.acrolinx.sidebar.pojo.settings.SoftwareComponentCategory;
import com.acrolinx.sidebar.swt.AcrolinxSidebarSWT;
import com.acrolinx.sidebar.swt.adapter.TextAdapter;
import com.acrolinx.sidebar.utils.IconUtils;
import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SwtAcrolinxApplication {
  public static void main(String[] args) {
    new SwtAcrolinxApplication().open();
  }

  private static AcrolinxSidebarInitParameter createAcrolinxSidebarInitParameter() {
    return new AcrolinxSidebarInitParameterBuilder(
            "SW50ZWdyYXRpb25EZXZlbG9wbWVudERlbW9Pbmx5", createSoftwareComponents())
        .withShowServerSelector(true)
        .build();
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
    return List.of(
        new SoftwareComponent(
            "com.acrolinx.client.sidebar.demo.swt",
            "Acrolinx Demo Client SWT",
            "1.0",
            SoftwareComponentCategory.MAIN));
  }

  private static SwtAcrolinxIntegration createSwtAcrolinxIntegration(TextAdapter textAdapter) {
    return new SwtAcrolinxIntegration(createAcrolinxSidebarInitParameter(), textAdapter);
  }

  private static TextAdapter createTextAdapter(Text text) {
    return new TextAdapter(text, InputFormat.TEXT, "samplefilename");
  }

  private final Display display = new Display();
  private final Shell shell = createShell(display);

  SwtAcrolinxApplication() {
    AcrolinxSidebarSWT acrolinxSidebarSwt =
        new AcrolinxSidebarSWT(
            shell, createSwtAcrolinxIntegration(createTextAdapter(createText())));
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
