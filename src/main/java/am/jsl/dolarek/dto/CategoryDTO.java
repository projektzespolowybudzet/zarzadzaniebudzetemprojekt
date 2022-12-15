package am.jsl.dolarek.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Zawiera informacje o kategorii
 */
public class CategoryDTO extends DescriptiveDTO {

  /**
   * Ikona kategorii
   */
  private String icon;

  /**
   * Kolor kategorii
   */
  private String color;

  /**
   * Identyfikator nadrzędny kategorii
   */
  private long parentId;

  /**
   * Kategorie dzieci
   */
  private List<CategoryDTO> childs;

  /**
   * Getter for property 'icon'.
   *
   * @return Value for property 'icon'.
   */
  public String getIcon() {
    return icon;
  }

  /**
   * Setter for property 'icon'.
   *
   * @param icon Value to set for property 'icon'.
   */
  public void setIcon(String icon) {
    this.icon = icon;
  }

  /**
   * Getter for property 'color'.
   *
   * @return Value for property 'color'.
   */
  public String getColor() {
    return color;
  }

  /**
   * Setter for property 'color'.
   *
   * @param color Value to set for property 'color'.
   */
  public void setColor(String color) {
    this.color = color;
  }

  /**
   * Getter for property 'parentId'.
   *
   * @return Value for property 'parentId'.
   */
  public long getParentId() {
    return parentId;
  }

  /**
   * Setter for property 'parentId'.
   *
   * @param parentId Value to set for property 'parentId'.
   */
  public void setParentId(long parentId) {
    this.parentId = parentId;
  }

  /**
   * Getter for property 'childs'.
   *
   * @return Value for property 'childs'.
   */
  public List<CategoryDTO> getChilds() {
    return childs;
  }

  /**
   * Setter for property 'childs'.
   *
   * @param childs Value to set for property 'childs'.
   */
  public void setChilds(List<CategoryDTO> childs) {
    this.childs = childs;
  }

  /**
   * Dodaje dziecko do listy dzieci
   *
   * @param category Kategoria do dodania jako dziecko
   */
  public void addChild(CategoryDTO category) {
    if (childs == null) {
      childs = new ArrayList<>();
    }
    childs.add(category);
  }
}
