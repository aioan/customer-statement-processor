package com.alex.customerstatementprocessor.statement.parsers.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.alex.customerstatementprocessor.statement.model.Statement;
import com.alex.customerstatementprocessor.statement.parsers.exceptions.InvalidFileStructureException;
import org.junit.jupiter.api.Assertions;

class CsvParserTest {

  @Test
  void givenCorrectCsvFile_returnsExpectedOutput() throws IOException {
    CsvParser underTest = new CsvParser();
    
    underTest.initialise(getValidCsvFile());
    
    Assertions.assertTrue(underTest.hasNext());
    
    Statement firstRecord = underTest.next();
    
    Assertions.assertEquals(132843, firstRecord.getTransactionReference());
    Assertions.assertEquals("NL56RABO0149876948", firstRecord.getAccountNumber());
    Assertions.assertEquals("Flowers for Erik Bakker", firstRecord.getDescription());
    
    List<Statement> statements = new ArrayList<>();
    statements.add(firstRecord);
    while(underTest.hasNext()) {
      statements.add(underTest.next());
    }
    
    Assertions.assertEquals(10, statements.size());
    
    Statement lastRecord = statements.get(9);
    
    Assertions.assertEquals(187948, lastRecord.getTransactionReference());
    Assertions.assertEquals("NL56RABO0149876948", lastRecord.getAccountNumber());
    Assertions.assertEquals("Clothes for Rik Theuß", lastRecord.getDescription());
  }
  
  @Test
  void givenCsvFileWithWrongNumberOfColumns_throwInvalidFileStructureException() {
    CsvParser underTest = new CsvParser();

    underTest.initialise(getWrongNumberOfColumnsCsvFile());

    assertThrows(InvalidFileStructureException.class, () -> {
      underTest.next();
    });
  }
  
  @Test
  void givenCsvFileWithInvalidNumberValues_throwInvalidFileStructureException() {
    CsvParser underTest = new CsvParser();

    underTest.initialise(getInvalidNumberValueCsvFile());

    assertThrows(InvalidFileStructureException.class, () -> {
      underTest.next();
    });
  }
  
  private static InputStream getValidCsvFile() {
    String validCsvFile = "Reference,Account Number,Description,Start Balance,Mutation,End Balance\r\n" + 
        "132843,NL56RABO0149876948,Flowers for Erik Bakker,90.68,-45.33,45.35\r\n" + 
        "112806,NL32RABO0195610843,Subscription from Erik de Vries,80.48,+25.19,105.67\r\n" + 
        "107934,NL56RABO0149876948,Clothes for Jan Dekker,68.71,-15.75,52.96\r\n" + 
        "191708,NL74ABNA0248990274,Tickets for Erik de Vries,102.5,-20.49,82.01\r\n" + 
        "112806,NL46ABNA0625805417,Flowers for Willem Theuß,14.81,-11.99,2.82\r\n" + 
        "112806,NL32RABO0195610843,Subscription from Peter Theuß,49.25,+23.07,72.32\r\n" + 
        "191476,NL69ABNA0433647324,Clothes for Vincent de Vries,74.23,-48.76,25.47\r\n" + 
        "139812,NL56RABO0149876948,Tickets for Peter King,73.44,-30.11,43.33\r\n" + 
        "192851,NL90ABNA0585647886,Tickets for Richard de Vries,10.2,-28.74,-18.54\r\n" + 
        "187948,NL56RABO0149876948,Clothes for Rik Theuß,83.04,-2.51,80.53\r\n" + 
        "";
    
      return new ByteArrayInputStream(validCsvFile.getBytes(Charset.forName("UTF-8")));
  }
  
  private static InputStream getWrongNumberOfColumnsCsvFile() {
    return new ByteArrayInputStream("\r\n1,2,3,4,5".getBytes(Charset.forName("UTF-8")));
  }
  
  private static InputStream getInvalidNumberValueCsvFile() {
    return new ByteArrayInputStream("\r\n1,2,3,z,y,x".getBytes(Charset.forName("UTF-8")));
  }

}
