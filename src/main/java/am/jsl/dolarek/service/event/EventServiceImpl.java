package am.jsl.dolarek.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import am.jsl.dolarek.dao.event.EventDao;
import am.jsl.dolarek.domain.event.Event;
import am.jsl.dolarek.domain.event.EventType;
import am.jsl.dolarek.dto.event.EventListDTO;
import am.jsl.dolarek.search.EventSearchQuery;
import am.jsl.dolarek.search.ListPaginatedResult;

/**
*Implementacja usługi {@link EventService}.
*/
@Service("eventService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class EventServiceImpl implements EventService {
	
	@Autowired
	@Qualifier("eventDao")
	private EventDao eventDao;

	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void saveEvent(Event event) {
		eventDao.saveEvent(event);
	}

	@Override
	public void saveEvent(EventType eventType, String message, long performedBy) {
		Event event = new Event();
		event.setEventType(eventType.getValue());
		event.setMessage(message);
		event.setPerformedBy(performedBy);
		saveEvent(event);
	}

	@Override
	public ListPaginatedResult<EventListDTO> search(EventSearchQuery searchQuery) {
		return eventDao.search(searchQuery);
	}
}
