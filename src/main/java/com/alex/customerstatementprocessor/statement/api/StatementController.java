package com.alex.customerstatementprocessor.statement.api;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.alex.customerstatementprocessor.report.ReportService;
import com.alex.customerstatementprocessor.statement.StatementUploadService;
import com.alex.customerstatementprocessor.statement.api.model.UploadResponse;
import com.alex.customerstatementprocessor.statement.model.ProcessingResult;
import com.alex.customerstatementprocessor.statement.parsers.exceptions.InvalidFileStructureException;
import com.alex.customerstatementprocessor.statement.parsers.exceptions.UnsupportedFileTypeException;

@RestController
@RequestMapping("/api/statements")
public class StatementController {

  @Autowired
  private StatementUploadService statementService;

  @Autowired
  private ReportService reportService;

  @PostMapping
  public ResponseEntity<UploadResponse> uploadStatement(@RequestParam("file") MultipartFile file) {
    String requestId = UUID.randomUUID().toString();

    ProcessingResult result = statementService.processFile(file, requestId);

    reportService.createReport(result);

    return ResponseEntity.ok(new UploadResponse(requestId));
  }

  @ExceptionHandler({UnsupportedFileTypeException.class})
  public ResponseEntity<Map<String, String>> handleUnsupportedFileType(UnsupportedFileTypeException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("code", "UNSUPPORTED_FILE_TYPE");
    error.put("message", ex.getMessage());
    return ResponseEntity.badRequest().body(error);
  }

  @ExceptionHandler({InvalidFileStructureException.class})
  public ResponseEntity<Map<String, String>> handleInvalidFileStructure(InvalidFileStructureException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("code", "INVALID_FILE_STRUCTURE");
    error.put("message", ex.getMessage());
    return ResponseEntity.badRequest().body(error);
  }
}
