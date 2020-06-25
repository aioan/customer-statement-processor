package com.alex.customerstatementprocessor.api;

import java.io.IOException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.alex.customerstatementprocessor.api.model.UploadResponse;
import com.alex.customerstatementprocessor.statement.StatementService;
import com.alex.customerstatementprocessor.statement.model.StatementErrorRepository;

@RestController
@RequestMapping("/statements")
public class StatementController {

  @Autowired
  private StatementService statementService;
  
  @Autowired // TODO don't call repository from controller
  private StatementErrorRepository statementErrorRepository;

  @PostMapping // TODO check thrown exceptions
  public ResponseEntity<UploadResponse> uploadStatement(@RequestParam("file") MultipartFile file) throws IOException {
    String requestId = UUID.randomUUID().toString();
    statementService.process(file, requestId);
    
    UploadResponse response = new UploadResponse();
    response.setErrors(statementErrorRepository.findByRequestId(requestId));
    
    return ResponseEntity.ok(response);
  }
}
