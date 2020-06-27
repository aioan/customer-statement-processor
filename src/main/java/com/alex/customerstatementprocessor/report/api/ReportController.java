package com.alex.customerstatementprocessor.report.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.customerstatementprocessor.report.ReportService;
import com.alex.customerstatementprocessor.report.model.Report;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

  @Autowired
  private ReportService reportService;
  
  @GetMapping("{requestId}")
  public ResponseEntity<Report> getReportForRequest(@PathVariable("requestId") String requestId) {
	Optional<Report> request = reportService.getReportByRequestId(requestId);
	
	if(request.isPresent()) {
		return ResponseEntity.ok(request.get());
	} else {
		return ResponseEntity.notFound().build();
	}
  }
  
  @GetMapping
  public ResponseEntity<List<Report>> getAllRequests() {
	  return ResponseEntity.ok(reportService.getAll());
  }

}
