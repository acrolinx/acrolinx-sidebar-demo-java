/* Copyright (c) 2018 Acrolinx GmbH */
package com.acrolinx.client.sidebar.demo.swing;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.pojo.SidebarError;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;
import com.acrolinx.sidebar.pojo.settings.BatchCheckRequestOptions;
import com.acrolinx.sidebar.pojo.settings.CheckOptions;
import com.acrolinx.sidebar.pojo.settings.InputFormat;
import com.acrolinx.sidebar.swing.adapter.TextAreaAdapter;
import java.util.List;
import java.util.Optional;
import javax.swing.JTextArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class AcrolinxSwingIntegration implements AcrolinxIntegration {
  private static final Logger logger = LoggerFactory.getLogger(AcrolinxSwingIntegration.class);

  private final AcrolinxSidebarInitParameter acrolinxSidebarInitParameter;
  private final JTextArea jTextArea;

  AcrolinxSwingIntegration(
      AcrolinxSidebarInitParameter acrolinxSidebarInitParameter, JTextArea jTextArea) {
    this.acrolinxSidebarInitParameter = acrolinxSidebarInitParameter;
    this.jTextArea = jTextArea;
  }

  @Override
  public List<BatchCheckRequestOptions> extractReferences() {
    throw new UnsupportedOperationException();
  }

  @Override
  public CheckOptions getCheckOptionsForDocument(String documentIdentifier) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getContentForDocument(String documentIdentifier) {
    throw new UnsupportedOperationException();
  }

  @Override
  public InputAdapterInterface getEditorAdapter() {
    return new TextAreaAdapter(jTextArea, InputFormat.TEXT, "sampleFileName");
  }

  @Override
  public AcrolinxSidebarInitParameter getInitParameters() {
    return acrolinxSidebarInitParameter;
  }

  @Override
  public void onCheckResult(CheckResult checkResult) {
    logger.debug("Got check result");
  }

  @Override
  public void onInitFinished(Optional<SidebarError> initResult) {
    logger.debug("Finished init!");
  }

  @Override
  public boolean openDocumentInEditor(String documentIdentifier) {
    throw new UnsupportedOperationException();
  }
}
