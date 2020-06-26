package com.alex.customerstatementprocessor.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.customerstatementprocessor.request.RequestService;
import com.alex.customerstatementprocessor.request.model.Request;

@RestController
@RequestMapping("/requests")
public class RequestController {

  @Autowired
  private RequestService requestService;
  
  @GetMapping("{requestId}")
  public ResponseEntity<Request> getRequest(@PathVariable("requestId") String requestId) {
	Optional<Request> request = requestService.getRequestById(requestId);
	
	if(request.isPresent()) {
		return ResponseEntity.ok(request.get());
	} else {
		return ResponseEntity.notFound().build();
	}
  }
  
  @GetMapping
  public ResponseEntity<List<Request>> getAllRequests() {
	  return ResponseEntity.ok(requestService.getAll());
  }

}
