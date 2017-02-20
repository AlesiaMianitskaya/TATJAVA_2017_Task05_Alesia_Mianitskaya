package com.epam.meal.parser.dom;

import com.epam.meal.parser.entity.Meal;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DOM parser XML-file
 */
public class MenuDOMParser {
  /**
   * file's name
   */
  private static final String FILE_NAME = "menu.xml";
  /**
   * tags' and attributes' names
   */
  private static final String PART = "part";
  private static final String CATEGORY = "category";
  private static final String MEAL = "meal";
  private static final String ID = "id";
  private static final String PHOTO = "photo";
  private static final String TITLE = "title";
  private static final String DESCRIPTION = "description";
  private static final String PORTION = "portion";
  private static final String PRICE = "price";
  /**
   * List for adding information from menu
   */
  private List<Meal> mealList = new ArrayList<>();

  public List<Meal> getMealList() {
    return mealList;
  }

  /**
   * parse XML-file
   *
   * @throws SAXException if problem with parsing
   * @throws IOException  if problem with file
   */
  public void parse() throws SAXException, IOException {
    Document document = getDocument();
    Element root = document.getDocumentElement();
    NodeList partNodes = root.getElementsByTagName(PART);
    Meal meal = null;
    for (int i = 0; i < partNodes.getLength(); i++) {
      meal = new Meal();
      Element partElement = (Element) partNodes.item(i);
      meal.setCategory(partElement.getAttribute(CATEGORY));
      setParameter(meal, partElement);
    }
  }

  /**
   * get DOM document
   *
   * @return document
   * @throws SAXException if problem with parsing
   * @throws IOException  if problem with file
   */
  private static Document getDocument() throws SAXException, IOException {
    DOMParser parserDOM = new DOMParser();
    parserDOM.parse(FILE_NAME);
    Document document = parserDOM.getDocument();
    return document;
  }

  /**
   * set meals' parameters
   *
   * @param meal        - meal
   * @param partElement - element "part" from XML-file
   */
  private void setParameter(Meal meal, Element partElement) {
    Element mealElement = getChild(partElement, MEAL);
    NodeList mealNode = partElement.getElementsByTagName(MEAL);
    for (int j = 0; j < mealNode.getLength(); j++) {
      meal.setId(mealElement.getAttribute(ID));
      meal.setPhoto(getChild(mealElement, PHOTO).getTextContent());
      meal.setTitle(getChild(mealElement, TITLE).getTextContent());
      meal.setDescription(getChild(mealElement, DESCRIPTION).getTextContent());
      meal.setPortion(getChild(mealElement, PORTION).getTextContent());
      meal.setPrice(getChild(mealElement, PRICE).getTextContent());
      mealList.add(meal);
    }
  }

  /***
   *  get child element
   *
   * @param element - from which get a child
   * @param childName - child's name
   * @return child element
   */
  private static Element getChild(Element element, String childName) {
    NodeList childList = element.getElementsByTagName(childName);
    Element child = (Element) childList.item(0);
    return child;
  }
}
