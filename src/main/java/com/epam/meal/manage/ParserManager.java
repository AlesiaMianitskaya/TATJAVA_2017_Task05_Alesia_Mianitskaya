package com.epam.meal.manage;

import com.epam.meal.parser.dom.MenuDOMParser;
import com.epam.meal.parser.sax.MenuSAXParser;
import com.epam.meal.parser.stax.MenuStAXParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Start point of the program
 */
public class ParserManager {
  private final static Logger LOG = LogManager.getRootLogger();

  public static void main(String[] args) {
    runSAX();
    runStAX();
    runDOM();
  }

  private static void runSAX(){
    try {
      MenuSAXParser saxParser = new MenuSAXParser();
      saxParser.parse();
      LOG.info("Successful SAX parsing.");
    } catch (SAXException | IOException e) {
      LOG.error("Problem with SAX parser", e);
    }
  }

  private static void runStAX(){
    try {
      MenuStAXParser stAXParser = new MenuStAXParser();
      stAXParser.parse();
      LOG.info("Successful StaX parsing.");
    } catch (FileNotFoundException | XMLStreamException e) {
      LOG.error("Problem with StAX parser", e);
    }
  }

  private static void runDOM(){
    try {
      MenuDOMParser domParser = new MenuDOMParser();
      domParser.parse();
      LOG.info("Successful DOM parsing.");
    } catch (SAXException | IOException e) {
      LOG.error("Problem with DOM parser");
    }
  }
}
