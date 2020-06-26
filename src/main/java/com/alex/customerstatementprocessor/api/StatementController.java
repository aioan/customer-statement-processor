package com.alex.customerstatementprocessor.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alex.customerstatementprocessor.api.model.UploadResponse;
import com.alex.customerstatementprocessor.request.RequestService;
import com.alex.customerstatementprocessor.request.model.Request;
import com.alex.customerstatementprocessor.statement.StatementService;
import com.alex.customerstatementprocessor.statement.parsers.exceptions.InvalidFileStructureException;
import com.alex.customerstatementprocessor.statement.parsers.exceptions.UnsupportedFileTypeException;

@RestController
@RequestMapping("/statements")
public class StatementController {

  @Autowired
  private StatementService statementService;
  
  @Autowired
  private RequestService requestService;

  @PostMapping // TODO check thrown exceptions
  public ResponseEntity<UploadResponse> uploadStatement(@RequestParam("file") MultipartFile file) {
	Request request = requestService.initializeRequest();
	try {
	    
	    statementService.process(file, request.getId());
	    
	    UploadResponse response = new UploadResponse();
	    response.setRequestId(request.getId());
	    
	    requestService.finaliseRequest(request);
	    return ResponseEntity.ok(response);
	} catch (Exception e) {
    	requestService.delete(request);
    	throw e;
    }
  }
  
  @ExceptionHandler({UnsupportedFileTypeException.class})
  public ResponseEntity<Map<String,String>> handleUnsupportedFileType(UnsupportedFileTypeException ex) {
	  Map<String,String> error = new HashMap<>();
	  error.put("code", "UNSUPPORTED_FILE_TYPE");
	  error.put("message", ex.getMessage());
	  return ResponseEntity.badRequest().body(error);
  }
  
  @ExceptionHandler({InvalidFileStructureException.class})
  public ResponseEntity<Map<String,String>> handleInvalidFileStructure(InvalidFileStructureException ex) {
	  Map<String,String> error = new HashMap<>();
	  error.put("code", "INVALID_FILE_STRUCTURE");
	  error.put("message", ex.getMessage());
	  return ResponseEntity.badRequest().body(error);
  }
}
