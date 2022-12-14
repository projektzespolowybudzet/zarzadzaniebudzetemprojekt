package am.jsl.dolarek.domain;

import java.util.Objects;

/**
 * Obiekt Domain, który rozszerza BaseEntity i zawiera pole name.
 * Używana jako klasa bazowa dla obiektów wymagających tych właściwości.
 */
public class NamedEntity extends BaseEntity {

  private String name;

  /**
   * Gets the value of the name property.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the object.
   *
   * @param name - The name of the new name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Compares this entity with the given object.
   *
   * @param o - the object to compare with this one
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
     *Checks if the entity is a NamedEntity.
     */
    if (o instanceof NamedEntity) {
      final NamedEntity other = (NamedEntity) o;
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
