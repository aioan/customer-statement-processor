package com.alex.customerstatementprocessor.statement.parsers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.alex.customerstatementprocessor.statement.parsers.exceptions.UnsupportedFileTypeException;

@Component
public class ParserFactory implements ApplicationContextAware {

  @Autowired
  List<Parser> parsers;

  private ApplicationContext applicationContext;

  public Parser getParser(String filename) {
	String fileExtension = extractExtension(filename);
    Parser parser = parsers
        .stream()
        .filter(p -> p.supports(fileExtension))
        .findAny()
        .orElseThrow(() -> new UnsupportedFileTypeException("Unsupported file extension " + fileExtension));

    return applicationContext.getBean(parser.getClass());
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }
  
  private static String extractExtension(String filename) {
    int extensionStart = filename.lastIndexOf('.');
    
    if(extensionStart > 0 && extensionStart < filename.length()) {
    	return filename.substring(extensionStart + 1);
    } else {
    	throw new UnsupportedFileTypeException("Cannot determine file extension");
    }
  }
}
