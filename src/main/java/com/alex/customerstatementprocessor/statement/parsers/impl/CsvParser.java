package com.alex.customerstatementprocessor.statement.parsers.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.alex.customerstatementprocessor.statement.model.Statement;
import com.alex.customerstatementprocessor.statement.parsers.Parser;

@Component
@Scope("prototype")
public class CsvParser implements Parser {

  private BufferedReader reader;
  
  private String cachedLine;

  @Override
  public void initialise(InputStream stream) throws IOException {
    reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
    // Skip header line
    reader.readLine();
  }

  @Override
  public Boolean supports(String fileExtension) {
    return "csv".equalsIgnoreCase(fileExtension);
  }

  @Override
  public boolean hasNext() throws IOException {
    cachedLine = reader.readLine();
    // Processing stops on first empty or null line
    return StringUtils.hasText(cachedLine);
  }

  @Override
  public Statement next() throws IOException {
    String nextLine;
    if(cachedLine != null) {
      nextLine = cachedLine;
      cachedLine = null;
    } else {
      nextLine = reader.readLine();
    }
    return parseFromCsvLine(nextLine);
  }
  
  private Statement parseFromCsvLine(String csvLine) {
    String[] csvData = csvLine.split(",");
    Statement statement = new Statement();
    statement.setTransactionReference(Long.valueOf(csvData[0]));
    statement.setAccountNumber(csvData[1]);
    statement.setDescription(csvData[2]);
    statement.setStartingBalance(new BigDecimal(csvData[3]));
    statement.setMutation(new BigDecimal(csvData[4]));
    statement.setEndBalance(new BigDecimal(csvData[5]));

    return statement;
  }
}
