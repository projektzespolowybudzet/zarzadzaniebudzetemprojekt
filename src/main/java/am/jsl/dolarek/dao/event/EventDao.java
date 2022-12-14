package am.jsl.dolarek.dao.event;

import am.jsl.dolarek.domain.event.Event;
import am.jsl.dolarek.dto.event.EventListDTO;
import am.jsl.dolarek.search.EventSearchQuery;
import am.jsl.dolarek.search.ListPaginatedResult;

/**
 * Interfejs Dao do uzyskiwania dostępu do obiektu domeny {@link Event}.
 */
public interface EventDao {
  /**
   * Zapisuje podane zdarzenie.
   *
   * @param event zdarzenie
   */
  void saveEvent(Event event);

  /**
   * Pobiera wynik podzielony na strony dla podanego zapytania.
   *
   * @param searchQuery {@link EventSearchQuery} zawierający opcje zapytania
   * @return wynik {@link ListPaginatedResult}
   */
  ListPaginatedResult<EventListDTO> search(EventSearchQuery searchQuery);
}
