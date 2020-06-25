package com.alex.customerstatementprocessor.statement.model;

import org.springframework.data.repository.Repository;

public interface StatementRepository extends Repository<Statement, Long> {

  Statement findByTransactionReference(Long reference);

  Statement save(Statement statement);
  
}
