package com.alex.customerstatementprocessor.statement;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.alex.customerstatementprocessor.statement.model.Statement;
import com.alex.customerstatementprocessor.statement.model.StatementRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StatementValidatorTest {

  @MockBean
  StatementRepository repo;
  
  @Autowired
  StatementValidator statementValidator;
  
  @Test
  void givenDuplicateTransaction_isValidReturnsFalse() {
    Mockito.when(repo.existsById(123L)).thenReturn(true);
    
    Boolean isValid = statementValidator.isValid(getMockInvalidStatement());
    
    assertFalse(isValid);
  }
  
  @Test
  void givenUniqueTransactionWithInvalidBalance_isValidReturnsFalse() {
    Mockito.when(repo.existsById(123L)).thenReturn(false);
    
    Boolean isValid = statementValidator.isValid(getMockInvalidStatement());
    
    assertFalse(isValid);
  }
  
  @Test
  void givenUniqueTransactionWithValidBalance_isValidReturnsTrue() {
    Mockito.when(repo.existsById(123L)).thenReturn(false);
    
    Boolean isValid = statementValidator.isValid(getMockValidStatement());
    
    assertTrue(isValid);
  }
  
  private Statement getMockInvalidStatement() {
    Statement s = new Statement();
    s.setTransactionReference(123L);
    s.setStartingBalance(new BigDecimal("200"));
    s.setMutation(new BigDecimal("-100"));
    s.setEndBalance(BigDecimal.TEN);
    return s;
  }
  
  private Statement getMockValidStatement() {
    Statement s = new Statement();
    s.setTransactionReference(123L);
    s.setStartingBalance(new BigDecimal("200"));
    s.setMutation(new BigDecimal("-100"));
    s.setEndBalance(new BigDecimal("100"));
    return s;
  }

}
