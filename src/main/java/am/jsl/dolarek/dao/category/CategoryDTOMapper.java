package am.jsl.dolarek.dao.category;

import am.jsl.dolarek.dao.DBUtils;
import am.jsl.dolarek.dto.CategoryDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Implementacja {@link RowMapper}, która konwertuje wiersz na nową instancję CategoryDTO.
 */
public class CategoryDTOMapper implements RowMapper<CategoryDTO> {

  /**
   * Map a row from a ResultSet to a category.
   *
   * @param rs - ResultSet containing the row to map.
   * @param rowNum - the row number to map.
   */
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
