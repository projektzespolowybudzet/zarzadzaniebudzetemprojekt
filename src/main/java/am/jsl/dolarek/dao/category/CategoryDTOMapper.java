package am.jsl.dolarek.dao.category;

import org.springframework.jdbc.core.RowMapper;

import am.jsl.dolarek.dao.DBUtils;
import am.jsl.dolarek.dto.CategoryDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
*Implementacja {@link RowMapper}, która konwertuje wiersz na nową instancję CategoryDTO.
*/
public class CategoryDTOMapper implements RowMapper<CategoryDTO> {

	public CategoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategoryDTO category = new CategoryDTO();
		category.setId(rs.getLong(DBUtils.id));
		category.setName(rs.getString(DBUtils.name));
		category.setIcon(rs.getString(DBUtils.icon));
		category.setColor(rs.getString(DBUtils.color));
		category.setParentId(rs.getLong(DBUtils.parent_id));
		return category;
	}
}
