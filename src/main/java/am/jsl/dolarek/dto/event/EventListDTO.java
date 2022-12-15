package am.jsl.dolarek.dto.event;

import java.io.Serializable;
import java.util.Date;

/**
 *EventListDTO służy do przekształcania danych zdarzeń na strone
 */
public class EventListDTO implements Serializable {

  /**
   * Wewnętrzny identyfikator.
   */
  private long id;

  /**
   * Typ zdarzenia
   */
  private String eventType;

  /**
   * Wiadomość
   */
  private String message;

  /**
   * Identyfikator użytkownika
   */
  private String performedBy;

  /**
   * Docelowy identyfikator obiektu
   */
  private String performedOn;

  /**
   * Data utworzenia
   */
  private Date createdAt = null;

  /**
   * Gets id.
   *
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets event type.
   *
   * @return the event type
   */
  public String getEventType() {
    return eventType;
  }

  /**
   * Sets event type.
   *
   * @param eventType the event type
   */
  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  /**
   * Gets message.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets message.
   *
   * @param message the message
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Gets performed by.
   *
   * @return the performed by
   */
  public String getPerformedBy() {
    return performedBy;
  }

  /**
   * Sets performed by.
   *
   * @param performedBy the performed by
   */
  public void setPerformedBy(String performedBy) {
    this.performedBy = performedBy;
  }

  /**
   * Gets performed on.
   *
   * @return the performed on
   */
  public String getPerformedOn() {
    return performedOn;
  }

  /**
   * Sets performed on.
   *
   * @param performedOn the performed on
   */
  public void setPerformedOn(String performedOn) {
    this.performedOn = performedOn;
  }

  /**
   * Gets created at.
   *
   * @return the created at
   */
  public Date getCreatedAt() {
    return createdAt;
  }

  /**
   * Sets created at.
   *
   * @param createdAt the created at
   */
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
