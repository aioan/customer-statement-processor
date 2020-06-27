package com.alex.customerstatementprocessor.statement.parsers;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import com.alex.customerstatementprocessor.statement.parsers.exceptions.UnsupportedFileTypeException;
import com.alex.customerstatementprocessor.statement.parsers.impl.CsvParser;
import com.alex.customerstatementprocessor.statement.parsers.impl.XmlParser;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ParserFactoryTest {

  @Autowired
  ParserFactory factory;

  @Mock
  MultipartFile mockFile;

  @Test
  void givenXmlFileFormat_returnsCorrectParser() throws IOException {
    Mockito.when(mockFile.getOriginalFilename()).thenReturn("test.xml");
    Mockito.when(mockFile.getInputStream())
        .thenReturn(new ByteArrayInputStream("<xml></xml>".getBytes()));
    Parser parser = factory.getParser(mockFile);

    assertNotNull(parser);
    assertTrue(parser instanceof XmlParser);
  }

  @Test
  void givenCsvFileFormat_returnsCorrectParser() throws IOException {
    Mockito.when(mockFile.getOriginalFilename()).thenReturn("test.csv");
    Mockito.when(mockFile.getInputStream())
        .thenReturn(new ByteArrayInputStream("<xml></xml>".getBytes()));
    Parser parser = factory.getParser(mockFile);

    assertNotNull(parser);
    assertTrue(parser instanceof CsvParser);
  }

  @Test
  void givenMultipleCallsForSupportedFileFormat_returnsNewParserInstance() throws IOException {
    Mockito.when(mockFile.getOriginalFilename()).thenReturn("test.xml");
    Mockito.when(mockFile.getInputStream())
        .thenReturn(new ByteArrayInputStream("<xml></xml>".getBytes()));
    Parser firstParser = factory.getParser(mockFile);
    Parser secondParser = factory.getParser(mockFile);

    assertNotEquals(firstParser, secondParser);
  }

  @Test
  void givenFileWithUnsupportedExtension_willThrowUnsupportedExtensionException() {
    Mockito.when(mockFile.getOriginalFilename()).thenReturn("test.png");

    assertThrows(UnsupportedFileTypeException.class, () -> {
      factory.getParser(mockFile);
    });
  }
  
  @Test
  void givenFileWithNoExtension_willThrowUnsupportedExtensionException() {
    Mockito.when(mockFile.getOriginalFilename()).thenReturn("test");
    
    assertThrows(UnsupportedFileTypeException.class, () -> {
      factory.getParser(mockFile);
    });
  }
  
  @Test
  void givenFileWithMalformedExtension_willThrowUnsupportedExtensionException() {
    Mockito.when(mockFile.getOriginalFilename()).thenReturn("test.");
    
    assertThrows(UnsupportedFileTypeException.class, () -> {
      factory.getParser(mockFile);
    });
  }

}
