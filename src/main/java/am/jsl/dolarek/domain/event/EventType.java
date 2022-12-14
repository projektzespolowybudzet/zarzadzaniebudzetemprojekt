package am.jsl.dolarek.domain.event;

import java.util.Arrays;

/**
 * Wyliczenie zawierające możliwe typy zdarzeń.
 */
public enum EventType {
  LOGIN((byte) 1, "Logowanie"),
  CREATE_ROLE((byte) 2, "Nadanie dostpępu"),
  UPDATE_ROLE((byte) 3, "Edycja dostępu"),
  DELETE_ROLE((byte) 4, "Usunięcie dostępu"),
  CREATE_USER((byte) 5, "Tworzenie użytkownika"),
  UPDATE_USER((byte) 6, "Edycja użytkownika"),
  DELETE_USER((byte) 7, "Usunięcie użytkownika"),
  CHANGE_PASSWORD((byte) 8, "Zmiana hasła"),
  REGISTER_USER((byte) 9, "Rejestracja użytkownika");

  private byte value;
  private String operation;

  EventType(byte value, String operation) {
    this.value = value;
    this.operation = operation;
  }

  /**
   * Returns the event type by value.
   *
   * @param value - the value to search for
   */
  public static EventType getByValue(byte value) {
    EventType[] values = values();

    return Arrays
      .stream(values)
      .filter(eventType -> eventType.getValue() == value)
      .findFirst()
      .orElse(null);
  }

  /**
   * Gets the value of the value property.
   */
  public byte getValue() {
    return value;
  }

  /**
   * Sets the value of the value property.
   *
   * @param value - allowed object is { @link byte }
   */
  public void setValue(byte value) {
    this.value = value;
  }

  /**
   * Gets the value of the operation property.
   */
  public String getOperation() {
    return operation;
  }

  /**
   * Sets the operation attribute value.
   *
   * @param operation - The operation to set.
   */
  public void setOperation(String operation) {
    this.operation = operation;
  }
}
