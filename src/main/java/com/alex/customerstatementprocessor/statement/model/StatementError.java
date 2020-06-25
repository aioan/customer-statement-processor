package com.alex.customerstatementprocessor.statement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "statement_errors", indexes = { @Index(name = "IDX_REQUEST_ID", columnList = "requestId") })
public class StatementError {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO) 
  private Long id;
  
  private Long transactionReference;
  private String description;

  private String requestId;

  public StatementError() {}
  
  public StatementError(Long transactionReference, String description, String requestId) {
    this.transactionReference = transactionReference;
    this.description = description;
    this.requestId = requestId;
  }

  public Long getTransactionReference() {
    return transactionReference;
  }

  public void setTransactionReference(Long transactionReference) {
    this.transactionReference = transactionReference;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

}
