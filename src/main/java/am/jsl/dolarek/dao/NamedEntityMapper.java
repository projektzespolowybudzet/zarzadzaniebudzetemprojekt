package am.jsl.dolarek.dao;

import am.jsl.dolarek.domain.NamedEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
*Implementacja {@link RowMapper}, która konwertuje wiersz na nową instancję NamedEntity.
*/
public class NamedEntityMapper implements RowMapper<NamedEntity> {

	@Override
	public NamedEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		NamedEntity namedEntity = new NamedEntity();
		namedEntity.setId(rs.getLong(DBUtils.id));
		namedEntity.setName(rs.getString(DBUtils.name));
		return namedEntity;
	}

}
