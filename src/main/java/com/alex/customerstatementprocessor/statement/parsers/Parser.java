package com.alex.customerstatementprocessor.statement.parsers;

import java.io.InputStream;
import java.util.Iterator;

import com.alex.customerstatementprocessor.statement.model.Statement;

public interface Parser extends Iterator<Statement> {

  void initialise(InputStream stream);
  
  boolean hasNext();
  
  Statement next();

  Boolean supports(String fileExtension);
}
