package com.alex.customerstatementprocessor.report;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.customerstatementprocessor.report.model.Report;
import com.alex.customerstatementprocessor.report.repo.ReportRepository;
import com.alex.customerstatementprocessor.statement.model.ProcessingResult;

@Service
public class ReportService {

	@Autowired
	ReportRepository repository;

	public Report createReport(ProcessingResult result) {
		Report r = new Report();
		r.setId(result.getRequestId());
		r.setStartDate(result.getStartDate());
		r.setFinishDate(result.getEndDate());
		r.setProcessedEntriesCount(result.getProcessedEntriesCount());
		return repository.save(r);
	}
	
	public Optional<Report> getReportByRequestId(String id) {
		return repository.findById(id);
	}

	public List<Report> getAll() {
		return repository.findAll();
	}

}
