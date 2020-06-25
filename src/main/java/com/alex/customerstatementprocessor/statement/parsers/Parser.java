package com.alex.customerstatementprocessor.statement.parsers;

import java.io.IOException;
import java.io.InputStream;
import com.alex.customerstatementprocessor.statement.model.Statement;

public interface Parser {

  void initialise(InputStream stream) throws IOException;
  
  boolean hasNext() throws IOException;
  
  Statement next() throws IOException;

  Boolean supports(String fileExtension);
}
