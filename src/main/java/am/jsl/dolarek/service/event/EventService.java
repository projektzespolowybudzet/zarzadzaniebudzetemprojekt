package am.jsl.dolarek.service.event;

import am.jsl.dolarek.domain.event.Event;
import am.jsl.dolarek.domain.event.EventType;
import am.jsl.dolarek.dto.event.EventListDTO;
import am.jsl.dolarek.search.EventSearchQuery;
import am.jsl.dolarek.search.ListPaginatedResult;

/**
*Interfejs usługi, który definiuje wszystkie metody pracy z obiektem domeny {@link Event}.
*/
public interface EventService {
/**
*Zapisuje podane zdarzenie.
*@param event zdarzenie
*/
    void saveEvent(Event event);

/**
*Tworzy wydarzenie z podanymi opcjami.
*@param eventType typ zdarzenia
*@param message wiadomość
*@param performedBy identyfikator użytkownika
*/
    void saveEvent(EventType eventType, String message, long performedBy);

/**
*Pobiera wynik podzielony na strony dla podanego zapytania.
*@param searchQuery {@link EventSearchQuery} zawierający opcje zapytania
*@return wynik {@link ListPaginatedResult}
*/
    ListPaginatedResult<EventListDTO> search(EventSearchQuery searchQuery);
}
