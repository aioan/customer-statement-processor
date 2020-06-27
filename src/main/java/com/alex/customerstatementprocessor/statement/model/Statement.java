package com.alex.customerstatementprocessor.statement.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "statements")
public class Statement {
  @Id
  private Long transactionReference;

  @Transient
  private String accountNumber;
  @Transient
  private BigDecimal startingBalance;
  @Transient
  private BigDecimal endBalance;
  @Transient
  private String description;
  @Transient
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result =
        prime * result + ((transactionReference == null) ? 0 : transactionReference.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Statement other = (Statement) obj;
    if (transactionReference == null) {
      if (other.transactionReference != null) {
        return false;
      }
    } else if (!transactionReference.equals(other.transactionReference)) {
      return false;
    }
    return true;
  }
}
