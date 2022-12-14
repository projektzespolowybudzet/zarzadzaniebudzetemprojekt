package am.jsl.dolarek.domain;

import java.util.Objects;

/**
 * Obiekt domeny kategorii.
 */
public class Category extends Descriptive {

  public static final String DEFAULT_COLOR = "#a0a0a0";
  private String icon;
  private String color;
  private long parentId;

  /**
   * Gets the value of the icon property.
   */
  public String getIcon() {
    return icon;
  }

  /**
   * Sets the icon of the image.
   *
   * @param icon - the icon to use for the icon
   */
  public void setIcon(String icon) {
    this.icon = icon;
  }

  /**
   * Gets the value of the color property.
   */
  public String getColor() {
    return color;
  }

  /**
   * Sets the color of the color.
   *
   * @param color - The color of the color.
   */
  public void setColor(String color) {
    this.color = color;
  }

  /**
   * Gets the value of the parentId property.
   */
  public long getParentId() {
    return parentId;
  }

  /**
   * Sets the parentId of the node.
   *
   * @param parentId - The id of the parent node.
   */
  public void setParentId(long parentId) {
    this.parentId = parentId;
  }

  /**
   * Compares this category and its name.
   *
   * @param o - the object to compare this category to the given object.
   */
  @Override
  public boolean equals(Object o) {
    /**
     *Returns true if this object is the same as the receiver.
     */
    if (this == o) return true;
    /**
     *Returns true if the object is null.
     */
    if (o == null) return false;

    /**
     *Returns true if the object is a category.
     */
    if (o instanceof Category) {
      final Category other = (Category) o;
      return (
        Objects.equals(getId(), other.getId()) &&
        Objects.equals(getName(), other.getName())
      );
    }
    return false;
  }

  /**
   * Returns a hash code for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName());
  }
}
