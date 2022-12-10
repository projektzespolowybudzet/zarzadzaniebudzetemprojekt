package am.jsl.dolarek.dto;

/**
*DescriptiveDTO rozszerza NamedEntity i zawiera pole opisu.
*Używana jako klasa bazowa dla obiektów wymagających tych właściwości.
*/
public class DescriptiveDTO extends NamedDTO {

    private String description;
  
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
