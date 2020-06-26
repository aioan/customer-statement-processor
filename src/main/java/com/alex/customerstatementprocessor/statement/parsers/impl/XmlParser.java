package com.alex.customerstatementprocessor.statement.parsers.impl;

import java.io.InputStream;
import java.math.BigDecimal;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alex.customerstatementprocessor.statement.model.Statement;
import com.alex.customerstatementprocessor.statement.parsers.Parser;
import com.alex.customerstatementprocessor.statement.parsers.exceptions.InvalidFileStructureException;

@Component
@Scope("prototype")
public class XmlParser implements Parser {
  
  private final XMLInputFactory factory = XMLInputFactory.newInstance();
  
  private XMLEventReader reader;

  private XMLEvent cachedEvent;
  
  @Override
  public void initialise(InputStream stream) {
    try {
      reader = factory.createXMLEventReader(stream, "UTF-8");
    } catch (XMLStreamException e) {
      throw new InvalidFileStructureException("Invalid XML file", e);
    }
  }

  @Override
  public Boolean supports(String fileExtension) {
    return "xml".equalsIgnoreCase(fileExtension);
  }

  @Override
  public boolean hasNext() {
    while(reader.hasNext()) {
      try {
        XMLEvent event = reader.nextEvent();
        if(isRecordStartEvent(event)) {
          cachedEvent = event;
          return true;
        }
      } catch (XMLStreamException e) {
    	  throw new InvalidFileStructureException("Invalid XML file", e);
      }
      
    }
    return false;
  }

  @Override
  public Statement next() {
    while (reader.hasNext()) {
      try {
        XMLEvent event;
        if(cachedEvent != null) {
          event = cachedEvent;
          cachedEvent = null;
        } else {
          event = reader.nextEvent();
        }
        if (isRecordStartEvent(event)) {
          Attribute referenceAttribute = getReferenceAttribute(event);
          return parseRecord(reader, referenceAttribute.getValue());
        }
      } catch (XMLStreamException e) {
    	  throw new InvalidFileStructureException("Invalid XML file", e);
      }
    }

    throw new InvalidFileStructureException("Invalid XML file: Unexpected end of file");
  }

  private static Attribute getReferenceAttribute(XMLEvent event) {
    return event.asStartElement().getAttributeByName(new QName("reference"));
  }

  private static boolean isRecordStartEvent(XMLEvent event) {
    return event.isStartElement() && event.asStartElement().getName().getLocalPart().equals("record");
  }
  
  private static Statement parseRecord(XMLEventReader reader, String reference) {
    Statement statement = new Statement();
    statement.setTransactionReference(Long.valueOf(reference));
    
    while(reader.hasNext()) {
      XMLEvent event;
	try {
		event = reader.nextEvent();
	} catch (XMLStreamException e) {
		throw new InvalidFileStructureException("Unable to parse XML file", e);
	}
      if(event.isEndElement() && event.asEndElement().getName().getLocalPart().equals("record")) {
        return statement;
      }
      if(event.isStartElement()) {
        StartElement element = event.asStartElement();
        String elementName = element.getName().getLocalPart();
        String elementText;
		try {
			elementText = reader.getElementText();
		} catch (XMLStreamException e) {
			throw new InvalidFileStructureException("Unable to parse XML file", e);
		}
        switch(elementName) {
          case "accountNumber":
            statement.setAccountNumber(elementText);
            break;
          case "description":
            statement.setDescription(elementText);
            break;
          case "startBalance":
            statement.setStartingBalance(new BigDecimal(elementText));
            break;
          case "mutation":
            statement.setMutation(new BigDecimal(elementText));
            break;
          case "endBalance":
            statement.setEndBalance(new BigDecimal(elementText));
            break;
          default:
            throw new InvalidFileStructureException("Unknown element: " + elementName);
        }
      }
    }
    
    return statement;
    
  }

}
