package am.jsl.dolarek.dto;

/**
 * DescriptiveDTO rozszerza NamedEntity i zawiera pole opisu.
 * Używana jako klasa bazowa dla obiektów wymagających tych właściwości.
 */
public class DescriptiveDTO extends NamedDTO {

  /**
   * Opis
   */
  private String description;

  /**
   * Getter for property 'description'.
   *
   * @return Value for property 'description'.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Setter for property 'description'.
   *
   * @param description Value to set for property 'description'.
   */
  public void setDescription(String description) {
    this.description = description;
  }
}
