/* Copyright (c) 2018 Acrolinx GmbH */
package com.acrolinx.client.sidebar.demo.swt;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.pojo.SidebarError;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;
import com.acrolinx.sidebar.pojo.settings.BatchCheckRequestOptions;
import com.acrolinx.sidebar.pojo.settings.CheckOptions;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SwtAcrolinxIntegration implements AcrolinxIntegration {
  private static final Logger logger = LoggerFactory.getLogger(SwtAcrolinxIntegration.class);

  private final AcrolinxSidebarInitParameter acrolinxSidebarInitParameter;
  private final InputAdapterInterface inputAdapterInterface;

  SwtAcrolinxIntegration(
      AcrolinxSidebarInitParameter acrolinxSidebarInitParameter,
      InputAdapterInterface inputAdapterInterface) {
    this.acrolinxSidebarInitParameter = acrolinxSidebarInitParameter;
    this.inputAdapterInterface = inputAdapterInterface;
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
    return inputAdapterInterface;
  }

  @Override
  public AcrolinxSidebarInitParameter getInitParameters() {
    return acrolinxSidebarInitParameter;
  }

  @Override
  public void onCheckResult(CheckResult checkResult) {
    logger.debug(checkResult.getCheckedDocumentPart().getCheckId());
  }

  @Override
  public void onInitFinished(Optional<SidebarError> sidebarError) {
    logger.debug("{}", sidebarError);
  }

  @Override
  public boolean openDocumentInEditor(String documentIdentifier) {
    throw new UnsupportedOperationException();
  }
}
