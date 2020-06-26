package com.alex.customerstatementprocessor.statement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alex.customerstatementprocessor.statement.model.Statement;
import com.alex.customerstatementprocessor.statement.model.StatementError;
import com.alex.customerstatementprocessor.statement.model.StatementErrorRepository;
import com.alex.customerstatementprocessor.statement.model.StatementRepository;
import com.alex.customerstatementprocessor.statement.parsers.Parser;
import com.alex.customerstatementprocessor.statement.parsers.ParserFactory;

@Service
public class StatementService {

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

  public void process(MultipartFile file, String requestId) throws IOException {
    Parser parser = parserFactory.getParser(extractExtension(file.getOriginalFilename()));

    parser.initialise(file.getInputStream());
    List<Statement> statementBuffer = new ArrayList<>();
    List<StatementError> errorBuffer = new ArrayList<>();
    
    while(parser.hasNext()) {
      Statement statement = parser.next();
      if(validator.isValid(statement)) {
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
    }
    
    if(!errorBuffer.isEmpty()) {
    	statementErrorRepository.saveAll(errorBuffer);
    }
    if(!statementBuffer.isEmpty()) {
    	statementRepository.saveAll(statementBuffer);
    }
  }

  private static String extractExtension(String filename) {
    // TODO make this pretty
    return filename.substring(filename.lastIndexOf('.') + 1);
  }

}
