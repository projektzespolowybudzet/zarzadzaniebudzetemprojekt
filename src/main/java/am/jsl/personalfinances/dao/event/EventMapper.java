package am.jsl.personalfinances.dao.event;

import am.jsl.personalfinances.dao.DBUtils;
import am.jsl.personalfinances.domain.event.Event;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
*Implementacja {@link RowMapper}, która konwertuje wiersz na nową instancję zdarzenia.
*/
public class EventMapper implements RowMapper<Event> {
	public static final String EVENT_TYPE = "event_type";
	public static final String MESSAGE = "message";
	public static final String PERFORMED_BY = "performed_by";
	public static final String CREATED_AT = "created_at";
	
	@Override
	public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
		Event event = new Event();
		event.setId(rs.getLong(DBUtils.id));
		return event;
	}

}
