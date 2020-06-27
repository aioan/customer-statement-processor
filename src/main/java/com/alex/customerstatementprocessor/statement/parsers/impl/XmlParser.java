package com.alex.customerstatementprocessor.statement.parsers.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.alex.customerstatementprocessor.statement.model.Statement;
import com.alex.customerstatementprocessor.statement.parsers.Parser;
import com.alex.customerstatementprocessor.statement.parsers.exceptions.InvalidFileStructureException;

@Component
@Scope("prototype")
public class XmlParser implements Parser {

  private static final String INVALID_XML_FILE = "Invalid XML file";

  private final XMLInputFactory factory = XMLInputFactory.newInstance();

  private XMLEventReader reader;

  private XMLEvent cachedEvent;

  @Override
  public Boolean supports(String fileExtension) {
    return "xml".equalsIgnoreCase(fileExtension);
  }

  @Override
  public void initialise(InputStream stream) {
    try {
      reader = factory.createXMLEventReader(stream, "UTF-8");
    } catch (XMLStreamException e) {
      throw new InvalidFileStructureException(INVALID_XML_FILE, e);
    }
  }

  @Override
  public boolean hasNext() {
    try {
      while (reader.hasNext()) {
        XMLEvent event = reader.nextEvent();
        if (isRecordStartEvent(event)) {
          cachedEvent = event;
          return true;
        }
      }
      return false;
    } catch (XMLStreamException e) {
      throw new InvalidFileStructureException(INVALID_XML_FILE, e);
    }
  }

  @Override
  public Statement next() {
    while (reader.hasNext()) {
      try {
        XMLEvent event;
        if (cachedEvent != null) {
          event = cachedEvent;
          cachedEvent = null;
        } else {
          event = reader.nextEvent();
        }
        if (isRecordStartEvent(event)) {
          Attribute referenceAttribute = getReferenceAttribute(event);
          if (referenceAttribute == null) {
            throw new InvalidFileStructureException(INVALID_XML_FILE + " - missing reference");
          }
          return parseRecord(reader, referenceAttribute.getValue());
        }
      } catch (XMLStreamException e) {
        throw new InvalidFileStructureException(INVALID_XML_FILE, e);
      } catch (NumberFormatException e) {
        throw new InvalidFileStructureException("Invalid amount", e);
      }
    }

    throw new InvalidFileStructureException(INVALID_XML_FILE + " - Unexpected end of file");
  }

  private static Attribute getReferenceAttribute(XMLEvent event) {
    return event.asStartElement().getAttributeByName(new QName("reference"));
  }

  private static boolean isRecordStartEvent(XMLEvent event) {
    return event.isStartElement()
        && event.asStartElement().getName().getLocalPart().equals("record");
  }

  private static Statement parseRecord(XMLEventReader reader, String reference)
      throws XMLStreamException {
    Statement statement = new Statement();

    statement.setTransactionReference(Long.valueOf(reference));

    while (reader.hasNext()) {
      XMLEvent event = reader.nextEvent();

      if (isEndOfStatement(event)) {
        return statement;
      }
      if (event.isStartElement()) {
        String elementName = event.asStartElement().getName().getLocalPart();
        String elementText = reader.getElementText();

        switch (elementName) {
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

  private static boolean isEndOfStatement(XMLEvent event) {
    return event.isEndElement() && event.asEndElement().getName().getLocalPart().equals("record");
  }

}
