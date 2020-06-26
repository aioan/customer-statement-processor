package com.alex.customerstatementprocessor.request.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.alex.customerstatementprocessor.statement.model.Statement;
import com.alex.customerstatementprocessor.statement.model.StatementError;

@Entity
@Table(name = "requests")
public class Request {

	@Id
	private String id;
	private Double processedEntries;
	private Date createdDate;
	private Date finishedDate;
	
	@OneToMany
	@JoinColumn(name = "requestId")
	private List<Statement> statements;
	
	@OneToMany
	@JoinColumn(name = "requestId")
	private List<StatementError> statementErrors;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getProcessedEntries() {
		return processedEntries;
	}

	public void setProcessedEntries(Double processedEntries) {
		this.processedEntries = processedEntries;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

	public List<StatementError> getStatementErrors() {
		return statementErrors;
	}

	public void setStatementErrors(List<StatementError> statementErrors) {
		this.statementErrors = statementErrors;
	}

}
