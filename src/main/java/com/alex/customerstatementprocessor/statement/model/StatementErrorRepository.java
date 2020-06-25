package com.alex.customerstatementprocessor.statement.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface StatementErrorRepository extends CrudRepository<StatementError, Long>{

  List<StatementError> findByRequestId(String requestId);
}
