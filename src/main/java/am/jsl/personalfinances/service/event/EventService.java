package am.jsl.personalfinances.service.event;

import am.jsl.personalfinances.domain.event.Event;
import am.jsl.personalfinances.domain.event.EventType;
import am.jsl.personalfinances.dto.event.EventListDTO;
import am.jsl.personalfinances.search.EventSearchQuery;
import am.jsl.personalfinances.search.ListPaginatedResult;

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
