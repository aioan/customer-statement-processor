package com.alex.customerstatementprocessor.statement;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alex.customerstatementprocessor.statement.model.Statement;
import com.alex.customerstatementprocessor.statement.model.StatementRepository;

@Component
public class StatementValidator {

  @Autowired
  StatementRepository repo;
  
  public boolean isValid(Statement statement) {
    return isUnique(statement) && hasCorrectEndBalance(statement);
  }

  private boolean isUnique(Statement statement) {
    return repo.findByTransactionReference(statement.getTransactionReference()) == null;
  }
  // TODO: fix possible NPEs
  private boolean hasCorrectEndBalance(Statement statement) {
    BigDecimal startingBalance = statement.getStartingBalance();
    BigDecimal mutationAmount = statement.getMutation();
    BigDecimal endBalance = statement.getEndBalance();

    BigDecimal computedEndBalance = startingBalance.add(mutationAmount);
    
    return computedEndBalance.compareTo(endBalance) == 0;
  }

}
