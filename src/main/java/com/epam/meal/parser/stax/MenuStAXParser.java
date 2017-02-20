package com.epam.meal.parser.stax;

import com.epam.meal.parser.entity.Meal;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * StAX parser XML - file
 */
public class MenuStAXParser {
  /**
   * file's name
   */
  private static final String FILE_NAME = "menu.xml";

  /**
   * List for adding information from menu
   */
  private List<Meal> meals;

  /**
   * parse XML file
   * add information to the List
   *
   * @throws XMLStreamException    if problem with parsing
   * @throws FileNotFoundException if file not found
   */
  public void parse() throws FileNotFoundException, XMLStreamException {
    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    InputStream input = new FileInputStream(FILE_NAME);
    XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
    meals = MenuStAXHandler.process(reader);
  }

  public List<Meal> getMeals() {
    return meals;
  }
}
