package com.alex.customerstatementprocessor.statement.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.alex.customerstatementprocessor.statement.model.StatementError;

public interface StatementErrorRepository extends CrudRepository<StatementError, Long>{

	List<StatementError> findByRequestId(String string);

}
