package com.alex.customerstatementprocessor.statement.parsers.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.alex.customerstatementprocessor.statement.model.Statement;
import org.junit.jupiter.api.Assertions;

class XmlParserTest {

  @Test
  public void givenCorrectXmlFile_returnsExpectedOutput() throws IOException {
    XmlParser underTest = new XmlParser();
    
    underTest.initialise(getValidXmlFile());
    
    Assertions.assertTrue(underTest.hasNext());
    
    Statement firstRecord = underTest.next();
    
    Assertions.assertEquals(108366, firstRecord.getTransactionReference());
    Assertions.assertEquals("NL27SNSB0917829871", firstRecord.getAccountNumber());
    Assertions.assertEquals("Candy from Willem de Vries", firstRecord.getDescription());
    
    List<Statement> statements = new ArrayList<>();
    statements.add(firstRecord);
    while(underTest.hasNext()) {
      statements.add(underTest.next());
    }
    
    Assertions.assertEquals(10, statements.size());
  }
  
  private static InputStream getValidXmlFile() {
    String validXmlFile = "<records>\r\n" + 
        "  <record reference=\"108366\">\r\n" + 
        "    <accountNumber>NL27SNSB0917829871</accountNumber>\r\n" + 
        "    <description>Candy from Willem de Vries</description>\r\n" + 
        "    <startBalance>30.77</startBalance>\r\n" + 
        "    <mutation>+22.38</mutation>\r\n" + 
        "    <endBalance>53.15</endBalance>\r\n" + 
        "  </record>\r\n" + 
        "  <record reference=\"108338\">\r\n" + 
        "    <accountNumber>NL91RABO0315273637</accountNumber>\r\n" + 
        "    <description>Clothes from Rik Bakker</description>\r\n" + 
        "    <startBalance>5429</startBalance>\r\n" + 
        "    <mutation>-939</mutation>\r\n" + 
        "    <endBalance>6368</endBalance>\r\n" + 
        "  </record>\r\n" + 
        "  <record reference=\"104254\">\r\n" + 
        "    <accountNumber>NL91RABO0315273637</accountNumber>\r\n" + 
        "    <description>Candy from Daniël Dekker</description>\r\n" + 
        "    <startBalance>37.04</startBalance>\r\n" + 
        "    <mutation>-45.75</mutation>\r\n" + 
        "    <endBalance>-8.71</endBalance>\r\n" + 
        "  </record>\r\n" + 
        "  <record reference=\"198725\">\r\n" + 
        "    <accountNumber>NL91RABO0315273637</accountNumber>\r\n" + 
        "    <description>Candy for Vincent Theuß</description>\r\n" + 
        "    <startBalance>82.95</startBalance>\r\n" + 
        "    <mutation>-47.96</mutation>\r\n" + 
        "    <endBalance>34.99</endBalance>\r\n" + 
        "  </record>\r\n" + 
        "  <record reference=\"173553\">\r\n" + 
        "    <accountNumber>NL27SNSB0917829871</accountNumber>\r\n" + 
        "    <description>Candy for Daniël King</description>\r\n" + 
        "    <startBalance>92.73</startBalance>\r\n" + 
        "    <mutation>+25.9</mutation>\r\n" + 
        "    <endBalance>118.63</endBalance>\r\n" + 
        "  </record>\r\n" + 
        "  <record reference=\"117057\">\r\n" + 
        "    <accountNumber>NL74ABNA0248990274</accountNumber>\r\n" + 
        "    <description>Flowers from Rik de Vries</description>\r\n" + 
        "    <startBalance>39.59</startBalance>\r\n" + 
        "    <mutation>+27.8</mutation>\r\n" + 
        "    <endBalance>67.39</endBalance>\r\n" + 
        "  </record>\r\n" + 
        "  <record reference=\"176709\">\r\n" + 
        "    <accountNumber>NL46ABNA0625805417</accountNumber>\r\n" + 
        "    <description>Tickets from Jan Theuß</description>\r\n" + 
        "    <startBalance>10.1</startBalance>\r\n" + 
        "    <mutation>-0.3</mutation>\r\n" + 
        "    <endBalance>9.8</endBalance>\r\n" + 
        "  </record>\r\n" + 
        "  <record reference=\"192105\">\r\n" + 
        "    <accountNumber>NL91RABO0315273637</accountNumber>\r\n" + 
        "    <description>Flowers for Rik Bakker</description>\r\n" + 
        "    <startBalance>21.79</startBalance>\r\n" + 
        "    <mutation>+3.63</mutation>\r\n" + 
        "    <endBalance>25.42</endBalance>\r\n" + 
        "  </record>\r\n" + 
        "  <record reference=\"196839\">\r\n" + 
        "    <accountNumber>NL90ABNA0585647886</accountNumber>\r\n" + 
        "    <description>Tickets for Vincent Dekker</description>\r\n" + 
        "    <startBalance>3980</startBalance>\r\n" + 
        "    <mutation>+1000</mutation>\r\n" + 
        "    <endBalance>4981</endBalance>\r\n" + 
        "  </record>\r\n" + 
        "  <record reference=\"190018\">\r\n" + 
        "    <accountNumber>NL74ABNA0248990274</accountNumber>\r\n" + 
        "    <description>Flowers for Jan Theuß</description>\r\n" + 
        "    <startBalance>25.2</startBalance>\r\n" + 
        "    <mutation>+2.75</mutation>\r\n" + 
        "    <endBalance>27.95</endBalance>\r\n" + 
        "  </record>\r\n" + 
        "</records>";
    
      return new ByteArrayInputStream(validXmlFile.getBytes(Charset.forName("UTF-8")));
  }

}
