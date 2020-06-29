package com.alex.customerstatementprocessor.statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.alex.customerstatementprocessor.report.ReportService;
import com.alex.customerstatementprocessor.statement.model.Statement;
import com.alex.customerstatementprocessor.statement.model.StatementError;
import com.alex.customerstatementprocessor.statement.repo.StatementErrorRepository;
import com.alex.customerstatementprocessor.statement.repo.StatementRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class StatementServiceIntegrationTest {
  
  @Mock
  MultipartFile mockFile;

  @Autowired
  StatementUploadService statementService;
  
  @Autowired
  StatementRepository statementRepository;
  
  @Autowired
  StatementErrorRepository errorRepository;
  
  @Autowired
  ReportService requestService;
  
  @Test
  void givenXmlFileWithValidStatement_willInsertStatementOnlyInStatementsTable() throws IOException {
    Mockito.when(mockFile.getInputStream()).thenReturn(new ByteArrayInputStream(VALID_XML_TEST_FILE.getBytes()));
    Mockito.when(mockFile.getOriginalFilename()).thenReturn("testXml.xml");
    
    statementService.processFile(mockFile, "test-request");
    
    Statement successfulStatement = statementRepository.findById(108366L).get();
    assertNotNull(successfulStatement);
    assertEquals(108366L, successfulStatement.getTransactionReference());
    
    List<StatementError> errors = errorRepository.findByRequestId("test-request");
    assertEquals(0, errors.size());
  }
  
  @Test
  void givenXmlFileWithInvalidStatement_willInsertStatementOnlyInErrorsTable() throws IOException {
    Mockito.when(mockFile.getInputStream()).thenReturn(new ByteArrayInputStream(INVALID_XML_TEST_FILE.getBytes()));
    Mockito.when(mockFile.getOriginalFilename()).thenReturn("testXml.xml");
    
    statementService.processFile(mockFile, "test");
    
    List<StatementError> errors = errorRepository.findByRequestId("test");
    assertEquals(1, errors.size());
    StatementError statementError = errors.get(0);
    assertEquals(108366L, statementError.getTransactionReference());
    assertEquals("Candy from Willem de Vries", statementError.getDescription());
    
    Optional<Statement> successfulStatement = statementRepository.findById(108366L);
    
    assertFalse(successfulStatement.isPresent());
  }
  
  
  private static String VALID_XML_TEST_FILE = "<records>\r\n" + 
      "  <record reference=\"108366\">\r\n" + 
      "    <accountNumber>NL27SNSB0917829871</accountNumber>\r\n" + 
      "    <description>Candy from Willem de Vries</description>\r\n" + 
      "    <startBalance>10.00</startBalance>\r\n" + 
      "    <mutation>+10.00</mutation>\r\n" + 
      "    <endBalance>20.00</endBalance>\r\n" + 
      "  </record></records>";
  
  private static String INVALID_XML_TEST_FILE = "<records>\r\n" + 
      "  <record reference=\"108366\">\r\n" + 
      "    <accountNumber>NL27SNSB0917829871</accountNumber>\r\n" + 
      "    <description>Candy from Willem de Vries</description>\r\n" + 
      "    <startBalance>30.77</startBalance>\r\n" + 
      "    <mutation>+22.38</mutation>\r\n" + 
      "    <endBalance>12</endBalance>\r\n" + 
      "  </record></records>";
}
