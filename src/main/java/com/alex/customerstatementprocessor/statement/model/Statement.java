package com.alex.customerstatementprocessor.statement.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "statements")
public class Statement {
  @Id
  private Long transactionReference;
  
  private String accountNumber;
  private BigDecimal startingBalance;
  private BigDecimal endBalance;
  private String description;
  private BigDecimal mutation;

  public Long getTransactionReference() {
    return transactionReference;
  }

  public void setTransactionReference(Long transactionReference) {
    this.transactionReference = transactionReference;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public BigDecimal getStartingBalance() {
    return startingBalance;
  }

  public void setStartingBalance(BigDecimal startingBalance) {
    this.startingBalance = startingBalance;
  }

  public BigDecimal getEndBalance() {
    return endBalance;
  }

  public void setEndBalance(BigDecimal endBalance) {
    this.endBalance = endBalance;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getMutation() {
    return mutation;
  }

  public void setMutation(BigDecimal mutationAmount) {
    this.mutation = mutationAmount;
  }

}
