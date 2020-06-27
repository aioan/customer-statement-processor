package com.alex.customerstatementprocessor.report.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.customerstatementprocessor.report.model.Report;

public interface ReportRepository extends JpaRepository<Report, String>{

}
