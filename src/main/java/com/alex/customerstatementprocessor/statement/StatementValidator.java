package com.alex.customerstatementprocessor.statement;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alex.customerstatementprocessor.statement.model.Statement;
import com.alex.customerstatementprocessor.statement.model.StatementRepository;

@Component
public class StatementValidator {

  @Autowired
  StatementRepository repo;
  
  public boolean isValid(Statement statement) {
	return hasAllRequiredFields(statement) && hasCorrectEndBalance(statement) && isUnique(statement);
  }

  private boolean hasAllRequiredFields(Statement statement) {
	return statement.getTransactionReference() != null &&
		   StringUtils.hasText(statement.getAccountNumber()) &&
		   statement.getEndBalance() != null && 
		   statement.getMutation() != null &&
		   statement.getStartingBalance() != null;
  }
  
  private boolean isUnique(Statement statement) {
	  return !repo.existsById(statement.getTransactionReference());
  }
  
  private boolean hasCorrectEndBalance(Statement statement) {
    BigDecimal startingBalance = statement.getStartingBalance();
    BigDecimal mutationAmount = statement.getMutation();
    BigDecimal endBalance = statement.getEndBalance();

    BigDecimal computedEndBalance = startingBalance.add(mutationAmount);
    
    return computedEndBalance.compareTo(endBalance) == 0;
  }

}
