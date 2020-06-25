package com.alex.customerstatementprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
// @EnableAsync
public class CustomerStatementProcessorApplication {

    // TODO enhance XML validation (check for missing tags etc)
    // TODO add custom exceptions for most common cases
    // TODO add tests for everything
  
	public static void main(String[] args) {
		SpringApplication.run(CustomerStatementProcessorApplication.class, args);
	}

}
