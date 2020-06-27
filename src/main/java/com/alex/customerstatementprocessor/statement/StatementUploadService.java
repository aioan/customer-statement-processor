package com.alex.customerstatementprocessor.statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alex.customerstatementprocessor.statement.model.ProcessingResult;
import com.alex.customerstatementprocessor.statement.model.Statement;
import com.alex.customerstatementprocessor.statement.model.StatementError;
import com.alex.customerstatementprocessor.statement.parsers.Parser;
import com.alex.customerstatementprocessor.statement.parsers.ParserFactory;
import com.alex.customerstatementprocessor.statement.repo.StatementErrorRepository;
import com.alex.customerstatementprocessor.statement.repo.StatementRepository;

@Service
public class StatementUploadService {

  @Autowired
  StatementValidator validator;

  @Autowired
  ParserFactory parserFactory;
  
  @Autowired
  StatementRepository statementRepository;

  @Autowired
  StatementErrorRepository statementErrorRepository;
  
  @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
  Integer batchSize;

  public ProcessingResult processFile(MultipartFile file, String requestId) {
	Date startDate = new Date();
    Parser parser = parserFactory.getParser(file);

    List<Statement> statementBuffer = new ArrayList<>(batchSize);
    List<StatementError> errorBuffer = new ArrayList<>(batchSize);
    
    Integer processedEntries = 0;
    while(parser.hasNext()) {
      Statement statement = parser.next();
      if(!statementBuffer.contains(statement) && validator.isValid(statement)) {
    	statementBuffer.add(statement);
    	if(statementBuffer.size() >= batchSize) {
    		statementRepository.saveAll(statementBuffer);
    		statementBuffer.clear();
    	}
      } else {
    	errorBuffer.add(new StatementError(statement.getTransactionReference(), statement.getDescription(), requestId));
    	if(errorBuffer.size() >= batchSize) {
    		statementErrorRepository.saveAll(errorBuffer);
    		errorBuffer.clear();
    	}
      }
      processedEntries++;
    }
    
    if(!errorBuffer.isEmpty()) {
    	statementErrorRepository.saveAll(errorBuffer);
    }
    if(!statementBuffer.isEmpty()) {
    	statementRepository.saveAll(statementBuffer);
    }
    
    ProcessingResult result = new ProcessingResult();
    result.setRequestId(requestId);
    result.setStartDate(startDate);
    result.setEndDate(new Date());
    result.setProcessedEntriesCount(processedEntries);

    return result;
  }
}
