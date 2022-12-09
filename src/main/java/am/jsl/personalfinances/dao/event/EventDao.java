package am.jsl.personalfinances.dao.event;

import am.jsl.personalfinances.domain.event.Event;
import am.jsl.personalfinances.dto.event.EventListDTO;
import am.jsl.personalfinances.search.EventSearchQuery;
import am.jsl.personalfinances.search.ListPaginatedResult;

/**
*Interfejs Dao do uzyskiwania dostępu do obiektu domeny {@link Event}.
*/
public interface EventDao {
/**
*Zapisuje podane zdarzenie.
*@param event zdarzenie
*/
	void saveEvent(Event event);

/**
*Pobiera wynik podzielony na strony dla podanego zapytania.
*@param searchQuery {@link EventSearchQuery} zawierający opcje zapytania
*@return wynik {@link ListPaginatedResult}
*/
	ListPaginatedResult<EventListDTO> search(EventSearchQuery searchQuery);
}
