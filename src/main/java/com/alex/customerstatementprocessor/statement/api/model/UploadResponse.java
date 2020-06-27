package com.alex.customerstatementprocessor.statement.api.model;

public class UploadResponse {

  private String requestId;

  public UploadResponse(String requestId) {
    this.requestId = requestId;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }
}
