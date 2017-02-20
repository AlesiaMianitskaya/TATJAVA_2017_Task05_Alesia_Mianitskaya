package com.epam.meal.parser.sax;

import com.epam.meal.parser.entity.Meal;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

/**
 * SAX parser xml-file
 */
public class MenuSAXParser {
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
   * @throws SAXException if problem with parsing
   * @throws IOException  if problem with file
   */
  public void parse() throws SAXException, IOException {
    XMLReader reader = XMLReaderFactory.createXMLReader();
    MenuSaxHandler handler = new MenuSaxHandler();
    reader.setContentHandler(handler);
    reader.parse(new InputSource(FILE_NAME));
    meals = handler.getMealList();
  }

  public List<Meal> getMeals() {
    return meals;
  }

}
