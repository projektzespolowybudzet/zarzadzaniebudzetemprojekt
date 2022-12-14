package am.jsl.dolarek.dao.category;

import am.jsl.dolarek.dao.DBUtils;
import am.jsl.dolarek.domain.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Implementacja {@link RowMapper}, która konwertuje wiersz na nową instancję Category.
 */
public class CategoryMapper implements RowMapper<Category> {

  /**
   * Map a row from a ResultSet to a category.
   *
   * @param rs - ResultSet containing the row to map.
   * @param rowNum - the row number to map.
   */
  public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
    Category category = new Category();
    category.setId(rs.getLong(DBUtils.id));
    category.setName(rs.getString(DBUtils.name));
    category.setIcon(rs.getString(DBUtils.icon));
    category.setColor(rs.getString(DBUtils.color));
    category.setDescription(rs.getString(DBUtils.description));
    category.setParentId(rs.getLong(DBUtils.parent_id));
    category.setUserId(rs.getLong(DBUtils.user_id));
    return category;
  }
}
