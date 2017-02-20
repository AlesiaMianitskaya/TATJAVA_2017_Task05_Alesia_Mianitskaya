package com.epam.meal.parser.stax;

import com.epam.meal.parser.entity.Meal;
import com.epam.meal.parser.entity.MenuTagName;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Handler for StAX parsing XML-file
 */
class MenuStAXHandler {
  /**
   * attributes' names
   */
  private static final String CATEGORY = "category";
  private static final String ID = "id";

  /**
   * process XML-file
   *
   * @param reader - XMLStreamReader reader
   * @return List contains information from XML - file
   * @throws XMLStreamException if problem with parsing
   */
  static List<Meal> process(XMLStreamReader reader) throws XMLStreamException {
    List<Meal> mealList = new ArrayList<>();
    Meal meal = null;
    MenuTagName tagName = null;
    String category = null;

    while (reader.hasNext()) {
      int type = reader.next();
      switch (type) {
        case XMLStreamConstants.START_ELEMENT:
          tagName = getName(reader);
          switch (tagName) {
            case PART:
              category = reader.getAttributeValue(null, CATEGORY);
              meal = new Meal();
              meal.setCategory(category);
              break;
            case MEAL:
              String id = reader.getAttributeValue(null, ID);
              meal.setId(id);
              break;
          }
          break;
        case XMLStreamConstants.CHARACTERS:
          String text = reader.getText().trim();
          if (text.isEmpty()) {
            break;
          }
          setParameter(meal, tagName, text);
          break;
        case XMLStreamConstants.END_ELEMENT:
          tagName = getName(reader);
          addMeal(meal, tagName, mealList);
      }
    }
    return mealList;
  }

  /**
   * add meal to List if tag's name - meal
   *
   * @param meal     - meal
   * @param tagName  tag's name
   * @param mealList - List for adding
   */
  private static void addMeal(Meal meal, MenuTagName tagName, List<Meal> mealList) {
    switch (tagName) {
      case MEAL:
        mealList.add(meal);
    }
  }

  /**
   * set meal's parameter
   *
   * @param meal    - meal
   * @param tagName - parameter's name
   * @param text    - value of parameter
   */
  private static void setParameter(Meal meal, MenuTagName tagName, String text) {
    switch (tagName) {
      case TITLE:
        meal.setTitle(text);
        break;
      case DESCRIPTION:
        meal.setDescription(text);
        break;
      case PORTION:
        meal.setPortion(text);
        break;
      case PHOTO:
        meal.setPhoto(text);
        break;
      case PRICE:
        meal.setPrice(text);
        break;
    }
  }

  /**
   * get tag's name from enum
   *
   * @param reader - XMLStreamReader reader
   * @return tag's name
   */
  private static MenuTagName getName(XMLStreamReader reader) {
    MenuTagName name = MenuTagName.valueOf(reader.getLocalName().toUpperCase().replace('-', '_'));
    return name;
  }
}
