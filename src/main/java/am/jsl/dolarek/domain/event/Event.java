package am.jsl.dolarek.domain.event;

import am.jsl.dolarek.domain.BaseEntity;

/**
 * Obiekt domeny zdarzeń.
 */
public class Event extends BaseEntity {

  /**
   * Rodzaj zdarzenia
   * @see EventType
   */
  private byte eventType;

  /**
   * Wiadomość wydarzenia
   */
  private String message;

  /**
   * Identyfikator użytkownika utworzony przez zdarzenie
   */
  private long performedBy;

  /**
   * Konstruuje nowe zdarzenie z podanymi polami.
   *
   * @param eventType typ zdarzenia
   * @param message komunikat o zdarzeniu
   * @param performedBy identyfikator użytkownika, który utworzył tę wiadomość
   */
  public Event(EventType eventType, String message, long performedBy) {
    super();
    this.eventType = eventType.getValue();
    this.message = message;
    this.performedBy = performedBy;
  }

  /**
   * Domyślny konstruktor
   */
  public Event() {
    super();
  }

  /**
   * Gets the value of the eventType property.
   */
  public byte getEventType() {
    return eventType;
  }

  /**
   * Sets the event type of the event.
   *
   * @param eventType - The event type of the event.
   */
  public void setEventType(byte eventType) {
    this.eventType = eventType;
  }

  /**
   * Gets the value of the message property.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message of the message.
   *
   * @param message - The message to set.
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Gets the value of the performedBy property.
   */
  public long getPerformedBy() {
    return performedBy;
  }

  /**
   * Sets the amount of time the action was performed.
   *
   * @param performedBy - The amount of time the action was performed.
   */
  public void setPerformedBy(long performedBy) {
    this.performedBy = performedBy;
  }
}
