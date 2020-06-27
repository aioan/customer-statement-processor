package com.alex.customerstatementprocessor.statement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.customerstatementprocessor.statement.model.Statement;

public interface StatementRepository extends JpaRepository<Statement, Long> {
  
}
