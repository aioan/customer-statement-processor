package com.alex.customerstatementprocessor.statement.parsers.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.alex.customerstatementprocessor.statement.model.Statement;
import com.alex.customerstatementprocessor.statement.parsers.Parser;
import com.alex.customerstatementprocessor.statement.parsers.exceptions.InvalidFileStructureException;

@Component
@Scope("prototype")
public class CsvParser implements Parser {

  private static final String UNABLE_TO_PARSE_CSV_FILE = "Unable to parse CSV file";

  private BufferedReader reader;

  private String cachedLine;

  @Override
  public void initialise(InputStream stream) {
    reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
    // Skip header line
    try {
      reader.readLine();
    } catch (IOException e) {
      throw new InvalidFileStructureException(UNABLE_TO_PARSE_CSV_FILE, e);
    }
  }

  @Override
  public Boolean supports(String fileExtension) {
    return "csv".equalsIgnoreCase(fileExtension);
  }

  @Override
  public boolean hasNext() {
    try {
      if (StringUtils.hasText(cachedLine)) {
        return true;
      }
      cachedLine = reader.readLine();
      // Processing stops on first empty or null line
      return StringUtils.hasText(cachedLine);
    } catch (IOException e) {
      throw new InvalidFileStructureException(UNABLE_TO_PARSE_CSV_FILE, e);
    }
  }

  @Override
  public Statement next() {
    if(!hasNext()) {
      throw new NoSuchElementException();
    }
    String nextLine;
    if (cachedLine != null) {
      nextLine = cachedLine;
      cachedLine = null;
    } else {
      try {
        nextLine = reader.readLine();
      } catch (IOException e) {
        throw new InvalidFileStructureException(UNABLE_TO_PARSE_CSV_FILE, e);
      }
    }
    return parseFromCsvLine(nextLine);
  }

  private Statement parseFromCsvLine(String csvLine) {
    String[] csvData = csvLine.split(",");
    if (csvData.length != 6) {
      throw new InvalidFileStructureException("Invalid structure at line: " + csvLine);
    }
    try {
      Statement statement = new Statement();
      statement.setTransactionReference(Long.valueOf(csvData[0]));
      statement.setAccountNumber(csvData[1]);
      statement.setDescription(csvData[2]);
      statement.setStartingBalance(new BigDecimal(csvData[3]));
      statement.setMutation(new BigDecimal(csvData[4]));
      statement.setEndBalance(new BigDecimal(csvData[5]));

      return statement;
    } catch (NumberFormatException e) {
      throw new InvalidFileStructureException("Invalid amount", e);
    }
  }
}
