package com.alex.customerstatementprocessor.statement.parsers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ParserFactory implements ApplicationContextAware {

  @Autowired
  List<Parser> parsers;

  private ApplicationContext applicationContext;

  public Parser getParser(String fileExtension) {
    Parser parser = parsers
        .stream()
        .filter(p -> p.supports(fileExtension))
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException("Unsupported file extension " + fileExtension));

    return applicationContext.getBean(parser.getClass());
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }
}
