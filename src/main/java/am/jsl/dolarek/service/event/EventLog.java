package am.jsl.dolarek.service.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import am.jsl.dolarek.domain.event.EventType;
import am.jsl.dolarek.domain.user.User;

import java.io.Serializable;

/**
*Klasa do tworzenia zdarzeń.
*/
@Component
public class EventLog implements Serializable, ApplicationContextAware {

    private static EventLog log = null;
    private static ApplicationContext context;
    private EventService eventService;
    private EventLog() {
    }

    public static EventLog getInstance() {
        if (log == null) {
            log = new EventLog();
            EventService eventService = (EventService) context.getBean("eventService");
            log.setEventService(eventService);
        }
        return log;
    }

/**
*Ustawia kontekst aplikacji.
*@param ac ApplicationContext
*@throws BeansException, jeśli zostanie zgłoszony przez metody kontekstowe aplikacji
*/
    @Override
    public void setApplicationContext(ApplicationContext ac)
            throws BeansException {
        context = ac;
    }

/**
*Tworzy wydarzenie z podanymi opcjami + przeładowania
*@param eventType typu zdarzenia
*@param message wiadomość
*@param performedBy identyfikator użytkownika
*@param user użytkownik
*/
    public void write(EventType eventType, String message, long performedBy) {
        eventService.saveEvent(eventType, message, performedBy);
    }

    public void write(EventType eventType, String message, User user) {
        eventService.saveEvent(eventType, message, user.getId());
    }
    public void write(EventType eventType, long performedBy) {
        eventService.saveEvent(eventType, "", performedBy);
    }
    public void write(EventType eventType, User user) {
        eventService.saveEvent(eventType, "", user.getId());
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
}
