package com.alex.customerstatementprocessor.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.customerstatementprocessor.statement.model.StatementError;
import com.alex.customerstatementprocessor.statement.model.StatementErrorRepository;

@RestController
@RequestMapping("/statementErrors")
public class StatementErrorController {

  @Autowired // TODO don't call repository from controller
  private StatementErrorRepository statementErrorRepository;
  
  @GetMapping("{requestId}")
  public ResponseEntity<List<StatementError>> uploadStatement(@PathVariable("requestId") String requestId) {
    return ResponseEntity.ok(statementErrorRepository.findByRequestId(requestId));
  }

}
