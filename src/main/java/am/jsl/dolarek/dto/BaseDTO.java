package am.jsl.dolarek.dto;

import java.util.Objects;

/**
 * BaseDTO jest korzeniem klas DTO.
 * Klasy DTO służą do przenoszenia obiektów między warstwami aplikacji.
 */
public class BaseDTO {

  /**
   * Wewnętrzny identyfikator
   */
  private long id;

  /**
   * Getter for property 'id'.
   *
   * @return Value for property 'id'.
   */
  public long getId() {
    return id;
  }

  /**
   * Setter for property 'id'.
   *
   * @param id Value to set for property 'id'.
   */
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
