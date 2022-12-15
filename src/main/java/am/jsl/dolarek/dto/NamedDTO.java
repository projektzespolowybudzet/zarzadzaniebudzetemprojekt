package am.jsl.dolarek.dto;

import java.io.Serializable;

/**
 * Obiekt DTO, który rozszerza BaseDTO i zawiera pole nazwy.
 * Używana jako klasa bazowa dla obiektów wymagających tych właściwości.
 */
public class NamedDTO extends BaseDTO implements Serializable {

  /**
   * Nazwa
   */
  private String name;

  /**
   * Getter for property 'name'.
   *
   * @return Value for property 'name'.
   */
  public String getName() {
    return name;
  }

  /**
   * Setter for property 'name'.
   *
   * @param name Value to set for property 'name'.
   */
  public void setName(String name) {
    this.name = name;
  }
}
