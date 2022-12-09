package am.jsl.dolarek.dao.event;

import am.jsl.dolarek.dao.DBUtils;
import am.jsl.dolarek.domain.event.EventType;
import am.jsl.dolarek.dto.event.EventListDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
*Implementacja {@link RowMapper}, która konwertuje wiersz na nową instancję EventListDTO.
*/
public class EventLisBeanMapper implements RowMapper<EventListDTO> {
	
	@Override
	public EventListDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		EventListDTO event = new EventListDTO();
		event.setId(rs.getLong(DBUtils.id));
		byte eventTypeValue = rs.getByte(EventMapper.EVENT_TYPE);
		EventType eventType = EventType.getByValue(eventTypeValue);
		
		if (eventType != null) {
			event.setEventType(eventType.getOperation());
		}
		
		event.setMessage(rs.getString(EventMapper.MESSAGE));
		event.setPerformedBy(rs.getString(EventMapper.PERFORMED_BY));
		event.setCreatedAt(rs.getTimestamp(EventMapper.CREATED_AT));
		return event;
	}

}
