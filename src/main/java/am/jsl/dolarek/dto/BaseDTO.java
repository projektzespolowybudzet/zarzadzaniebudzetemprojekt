package am.jsl.dolarek.dto;

import java.util.Objects;

/**
*BaseDTO jest korzeniem klas DTO.
*Klasy DTO służą do przenoszenia obiektów między warstwami aplikacji.
*/
public class BaseDTO {
    
    private long id;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final BaseDTO other = (BaseDTO) obj;
        return Objects.equals(this.id, other.id);
    }
}
