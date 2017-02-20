package com.epam.meal.parser.entity;

/**
 * Class for food from menu
 */
public class Meal {

  private String id;
  private String category;
  private String photo;
  private String title;
  private String description;
  private String portion;
  private String price;

  public String getId() {
    return id;
  }

  public void setId(String  id) {
    this.id = id;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPortion() {
    return portion;
  }

  public void setPortion(String portion) {
    this.portion = portion;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Meal{" + " " + category + "; " + title + "; " + description +
        "; " + portion + "; " + price + "; " + photo + " }";
  }
}
