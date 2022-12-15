package am.jsl.dolarek.dto.transaction;

import java.io.Serializable;

/**
 * Zawiera transakcje pogrupowane według kategorii i konta.
 */
public class TransactionByCategoryDTO implements Serializable {

  /**
   * Nazwa kategorii
   */
  private String category;

  /**
   * Ikona kategorii
   */
  private String categoryIcon;

  /**
   * Kolor kategorii
   */
  private String categoryColor;

  /**
   * Nazwa kategorii nadrzędnej
   */
  private String parentCategory;

  /**
   * Ikona kategorii nadrzędnej
   */
  private String parentCategoryIcon;

  /**
   * Kolor kategorii nadrzędnej
   */
  private String parentCategoryColor;

  /**
   * Całkowita kwota
   */
  private double total;

  /**
   * Sformatowana całkowita kwota
   */
  private String totalStr;

  /**
   * Całkowity procent tej kwoty
   */
  private String percent;

  /**
   * Symbol waluty natywnej
   */
  private String symbol;

  /**
   * Getter for property 'category'.
   *
   * @return Value for property 'category'.
   */
  public String getCategory() {
    return category;
  }

  /**
   * Setter for property 'category'.
   *
   * @param category Value to set for property 'category'.
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * Getter for property 'categoryIcon'.
   *
   * @return Value for property 'categoryIcon'.
   */
  public String getCategoryIcon() {
    return categoryIcon;
  }

  /**
   * Setter for property 'categoryIcon'.
   *
   * @param categoryIcon Value to set for property 'categoryIcon'.
   */
  public void setCategoryIcon(String categoryIcon) {
    this.categoryIcon = categoryIcon;
  }

  /**
   * Getter for property 'categoryColor'.
   *
   * @return Value for property 'categoryColor'.
   */
  public String getCategoryColor() {
    return categoryColor;
  }

  /**
   * Setter for property 'categoryColor'.
   *
   * @param categoryColor Value to set for property 'categoryColor'.
   */
  public void setCategoryColor(String categoryColor) {
    this.categoryColor = categoryColor;
  }

  /**
   * Getter for property 'parentCategory'.
   *
   * @return Value for property 'parentCategory'.
   */
  public String getParentCategory() {
    return parentCategory;
  }

  /**
   * Setter for property 'parentCategory'.
   *
   * @param parentCategory Value to set for property 'parentCategory'.
   */
  public void setParentCategory(String parentCategory) {
    this.parentCategory = parentCategory;
  }

  /**
   * Getter for property 'parentCategoryIcon'.
   *
   * @return Value for property 'parentCategoryIcon'.
   */
  public String getParentCategoryIcon() {
    return parentCategoryIcon;
  }

  /**
   * Setter for property 'parentCategoryIcon'.
   *
   * @param parentCategoryIcon Value to set for property 'parentCategoryIcon'.
   */
  public void setParentCategoryIcon(String parentCategoryIcon) {
    this.parentCategoryIcon = parentCategoryIcon;
  }

  /**
   * Getter for property 'parentCategoryColor'.
   *
   * @return Value for property 'parentCategoryColor'.
   */
  public String getParentCategoryColor() {
    return parentCategoryColor;
  }

  /**
   * Setter for property 'parentCategoryColor'.
   *
   * @param parentCategoryColor Value to set for property 'parentCategoryColor'.
   */
  public void setParentCategoryColor(String parentCategoryColor) {
    this.parentCategoryColor = parentCategoryColor;
  }

  /**
   * Getter for property 'total'.
   *
   * @return Value for property 'total'.
   */
  public double getTotal() {
    return total;
  }

  /**
   * Setter for property 'total'.
   *
   * @param total Value to set for property 'total'.
   */
  public void setTotal(double total) {
    this.total = total;
  }

  /**
   * Getter for property 'percent'.
   *
   * @return Value for property 'percent'.
   */
  public String getPercent() {
    return percent;
  }

  /**
   * Setter for property 'percent'.
   *
   * @param percent Value to set for property 'percent'.
   */
  public void setPercent(String percent) {
    this.percent = percent;
  }

  /**
   * Getter for property 'symbol'.
   *
   * @return Value for property 'symbol'.
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * Setter for property 'symbol'.
   *
   * @param symbol Value to set for property 'symbol'.
   */
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  /**
   * Getter for property 'totalStr'.
   *
   * @return Value for property 'totalStr'.
   */
  public String getTotalStr() {
    return totalStr;
  }

  /**
   * Setter for property 'totalStr'.
   *
   * @param totalStr Value to set for property 'totalStr'.
   */
  public void setTotalStr(String totalStr) {
    this.totalStr = totalStr;
  }
}
