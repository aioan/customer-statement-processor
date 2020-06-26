package com.alex.customerstatementprocessor.statement.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatementRepository extends JpaRepository<Statement, Long> {
  
}
