package com.alex.customerstatementprocessor.statement.model;

import java.util.Date;

public class ProcessingResult {
  private String requestId;

  private Integer processedEntriesCount;
  private Date startDate;
  private Date endDate;

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public Integer getProcessedEntriesCount() {
    return processedEntriesCount;
  }

  public void setProcessedEntriesCount(Integer processedEntriesCount) {
    this.processedEntriesCount = processedEntriesCount;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
}
