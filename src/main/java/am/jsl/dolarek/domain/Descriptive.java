package am.jsl.dolarek.domain;

import java.util.Objects;

/**
 * Obiekt Domain, który rozszerza NamedEntity i zawiera pole opisu.
 * Używana jako klasa bazowa dla obiektów wymagających tych właściwości.
 */
public class Descriptive extends NamedEntity {

  protected String description;

  /**
   * Gets the value of the description property.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of the message.
   *
   * @param description - The description of the message.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Returns the hash code of this object.
   */
  @Override
  public int hashCode() {
    return 31 * super.hashCode() + Objects.hash(description);
  }

  /**
   * Compares this object to the given object.
   *
   * @param obj - the object to compare this object with
   */
  @Override
  public boolean equals(Object obj) {
    /**
     *Returns true if this object is the same as this object.
     */
    if (this == obj) {
      return true;
    }
    /**
     *Returns true if the object is null or if the class is not the same as the object.
     */
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    /**
     *Returns true if this object is equal to the object.
     */
    if (!super.equals(obj)) {
      return false;
    }
    final Descriptive other = (Descriptive) obj;
    boolean equals = Objects.equals(this.description, other.description);
    return equals;
  }
}
