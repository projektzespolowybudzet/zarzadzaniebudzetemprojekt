package am.jsl.dolarek.service.event;

import am.jsl.dolarek.domain.event.EventType;
import am.jsl.dolarek.domain.user.User;
import java.io.Serializable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Klasa do tworzenia zdarzeń.
 */
@Component
public class EventLog implements Serializable, ApplicationContextAware {

  /**
   * Instancja EventLog
   */
  private static EventLog log = null;

  /**
   * ApplicationContext
   */
  private static ApplicationContext context;

  /**
   * EventService
   */
  private EventService eventService;

  /**
   * Prywatny konstruktor
   */
  private EventLog() {}

  /**
   * Zwraca pojedynczą instancję EventLog.
   *
   * @return the EventLog
   */

  /**
   * Ustawia kontekst aplikacji.
   *
   * @param ac ApplicationContext
   * @throws BeansException, jeśli zostanie zgłoszony przez metody kontekstowe aplikacji
   */
  @Override
  public void setApplicationContext(ApplicationContext ac)
    throws BeansException {
    context = ac;
  }

  /**
   * Tworzy wydarzenie z podanymi opcjami
   *
   * @param eventType typ zdarzenia
   * @param message wiadomość
   * @param performedBy identyfikator użytkownika
   * @param user użytkownik
   */
  public void write(EventType eventType, String message, long performedBy) {
    eventService.saveEvent(eventType, message, performedBy);
  }

  /**
   * Tworzy wydarzenie z danymi opcjami.
   * @param eventType typ zdarzenia
   * @param message wiadomość
   * @param user użytkownik
   */
  public void write(EventType eventType, String message, User user) {
    eventService.saveEvent(eventType, message, user.getId());
  }

  /**
   * Tworzy wydarzenie z danymi opcjami.
   * @param eventType typ zdarzenia
   * @param performedBy identyfikator użytkownika
   */
  public void write(EventType eventType, long performedBy) {
    eventService.saveEvent(eventType, "", performedBy);
  }

  /**
   * Tworzy wydarzenie z danymi opcjami.
   * @param eventType typ zdarzenia
   * @param user użytkownik
   */
  public void write(EventType eventType, User user) {
    eventService.saveEvent(eventType, "", user.getId());
  }

  /**
   * Sets the EventService
   *
   * @param eventService the EventService
   */
  public void setEventService(EventService eventService) {
    this.eventService = eventService;
  }
}
