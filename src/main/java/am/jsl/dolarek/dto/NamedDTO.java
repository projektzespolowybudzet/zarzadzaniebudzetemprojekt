package am.jsl.dolarek.dto;

import java.io.Serializable;

/**
 *Obiekt DTO, który rozszerza BaseDTO i zawiera pole nazwy.
 *Używana jako klasa bazowa dla obiektów wymagających tych właściwości.
 */
public class NamedDTO  extends BaseDTO implements Serializable {
  
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
