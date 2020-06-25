package com.alex.customerstatementprocessor.statement.parsers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.alex.customerstatementprocessor.statement.parsers.impl.CsvParser;
import com.alex.customerstatementprocessor.statement.parsers.impl.XmlParser;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ParserFactoryTest {

  @Autowired
  ParserFactory factory;
  
  @Test
  void givenXmlFileFormat_returnsCorrectParser() {
    Parser parser = factory.getParser("xml");
    
    assertNotNull(parser);
    assertTrue(parser instanceof XmlParser);
  }
  
  @Test
  void givenCsvFileFormat_returnsCorrectParser() {
    Parser parser = factory.getParser("csv");
    
    assertNotNull(parser);
    assertTrue(parser instanceof CsvParser);
  }
  
  @Test
  void givenMultipleCallsForSupportedFileFormat_returnsNewParserInstance() {
    Parser firstParser = factory.getParser("xml");
    Parser secondParser = factory.getParser("xml");
    
    assertNotEquals(firstParser, secondParser);
  }

}
