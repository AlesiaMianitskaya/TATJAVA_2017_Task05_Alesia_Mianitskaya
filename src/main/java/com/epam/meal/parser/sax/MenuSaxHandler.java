package com.epam.meal.parser.sax;

import com.epam.meal.parser.entity.Meal;
import com.epam.meal.parser.entity.MenuTagName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Handler for SAX parsing XML-file
 */
class MenuSaxHandler extends DefaultHandler {

  /**
   * attributes' names
   */
  private static final String ID = "id";
  private static final String CATEGORY = "category";

  private List<Meal> mealList = new ArrayList<Meal>();
  private Meal meal;
  private String category;
  private StringBuilder text;

  public List<Meal> getMealList() {
    return mealList;
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attribute) throws SAXException {
    MenuTagName tagName = getName(qName);
    text = new StringBuilder();
    if (tagName.equals(MenuTagName.PART)) {
      category = attribute.getValue(CATEGORY);
    }
    if (tagName.equals(MenuTagName.MEAL)) {
      meal = new Meal();
      meal.setId(attribute.getValue(ID));
      meal.setCategory(category);
    }
  }

  @Override
  public void characters(char[] buffer, int start, int length) {
    text.append(buffer, start, length);
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    MenuTagName tagName = getName(qName);
    switch (tagName) {
      case TITLE:
        meal.setTitle(text.toString());
        break;
      case DESCRIPTION:
        meal.setDescription(text.toString());
        break;
      case PORTION:
        meal.setPortion(text.toString());
        break;
      case PRICE:
        meal.setPrice(text.toString());
        break;
      case PHOTO:
        meal.setPhoto(text.toString());
        break;
      case MEAL:
        mealList.add(meal);
        meal = null;
        break;
    }
  }

  /**
   * get tag's name from enum
   *
   * @param qName - tag's name
   * @return tag's name
   */
  private MenuTagName getName(String qName) {
    MenuTagName tagName = MenuTagName.valueOf(qName.toUpperCase().replace('-', '_'));
    return tagName;
  }
}
