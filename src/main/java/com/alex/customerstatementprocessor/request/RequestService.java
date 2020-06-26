package com.alex.customerstatementprocessor.request;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.customerstatementprocessor.request.model.Request;
import com.alex.customerstatementprocessor.request.model.RequestRepository;

@Service
public class RequestService {

	@Autowired
	RequestRepository repository;
	
	public Request initializeRequest() {
		Request request = new Request();
		request.setId(UUID.randomUUID().toString());
		request.setCreatedDate(new Date());
		repository.save(request);
		return request;
	}
	
	public void finaliseRequest(Request r) {
		r.setFinishedDate(new Date());
		repository.save(r);
	}
	
	public Optional<Request> getRequestById(String id) {
		return repository.findById(id);
	}

	public List<Request> getAll() {
		return repository.findAll();
	}

	public void delete(Request request) {
		repository.delete(request);
	}
	
}
