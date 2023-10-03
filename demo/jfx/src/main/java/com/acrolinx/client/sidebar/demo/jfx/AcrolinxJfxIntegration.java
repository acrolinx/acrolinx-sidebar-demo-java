/* Copyright (c) 2018-present Acrolinx GmbH */
package com.acrolinx.client.sidebar.demo.jfx;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.pojo.SidebarError;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;
import com.acrolinx.sidebar.pojo.settings.BatchCheckRequestOptions;
import com.acrolinx.sidebar.pojo.settings.CheckOptions;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.TextArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class AcrolinxJfxIntegration implements AcrolinxIntegration {
  private static final Logger logger = LoggerFactory.getLogger(AcrolinxJfxIntegration.class);

  private final AcrolinxSidebarInitParameter acrolinxSidebarInitParameter;
  private final TextArea textArea;

  AcrolinxJfxIntegration(
      TextArea textArea, AcrolinxSidebarInitParameter acrolinxSidebarInitParameter) {
    this.textArea = textArea;
    this.acrolinxSidebarInitParameter = acrolinxSidebarInitParameter;
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
    return new JfxTextAdapterWithLookUp(
        textArea, AcrolinxDemoClientJfx.inputFormat.get(), "sampleFile.txt");
  }

  @Override
  public AcrolinxSidebarInitParameter getInitParameters() {
    return acrolinxSidebarInitParameter;
  }

  @Override
  public void onCheckResult(CheckResult checkResult) {
    logger.debug(
        "Got check result for check id: {}", checkResult.getCheckedDocumentPart().getCheckId());
  }

  @Override
  public void onInitFinished(Optional<SidebarError> sidebarError) {
    logger.debug("Sidebar init done: {}", sidebarError);
  }

  @Override
  public boolean openDocumentInEditor(String documentIdentifier) {
    throw new UnsupportedOperationException();
  }
}
