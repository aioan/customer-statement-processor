package com.alex.customerstatementprocessor.report.model;

import java.util.Date;
import java.util.List;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.ForeignKey;

import com.alex.customerstatementprocessor.statement.model.StatementError;

@Entity
@Table(name = "reports")
public class Report {

  @Id
  private String id;
  private Date startDate;
  private Date finishDate;
  private Integer processedEntriesCount;

  @OneToMany
  @JoinColumn(name = "requestId",
      foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
  private List<StatementError> statementErrors;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getFinishDate() {
    return finishDate;
  }

  public void setFinishDate(Date finishDate) {
    this.finishDate = finishDate;
  }

  public Integer getProcessedEntriesCount() {
    return processedEntriesCount;
  }

  public void setProcessedEntriesCount(Integer processedEntriesCount) {
    this.processedEntriesCount = processedEntriesCount;
  }

  public List<StatementError> getStatementErrors() {
    return statementErrors;
  }

  public void setStatementErrors(List<StatementError> statementErrors) {
    this.statementErrors = statementErrors;
  }

}
