package am.jsl.dolarek.dao.transaction.mapper;

import am.jsl.dolarek.dao.DBUtils;
import am.jsl.dolarek.dto.transaction.TransactionByCategoryDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Implementacja {@link RowMapper}, która konwertuje wiersz na nową instancję TransactionByCategoryDTO.
 */
public class TransactionByCategoryDTOMapper
  implements RowMapper<TransactionByCategoryDTO> {

  /**
   * Map a row of a row.
   *
   * @param rs - ResultSet containing the row to map.
   * @param rowNum - the row number to map.
   */
  @Override
  public TransactionByCategoryDTO mapRow(ResultSet rs, int rowNum)
    throws SQLException {
    TransactionByCategoryDTO dto = new TransactionByCategoryDTO();

    dto.setCategory(rs.getString(DBUtils.category));
    dto.setCategoryIcon(rs.getString(DBUtils.category_icon));
    dto.setCategoryColor(rs.getString(DBUtils.category_color));

    dto.setParentCategory(rs.getString(DBUtils.parent_category));
    dto.setParentCategoryIcon(rs.getString(DBUtils.parent_category_icon));
    dto.setParentCategoryColor(rs.getString(DBUtils.parent_category_color));

    dto.setTotal(rs.getDouble(DBUtils.total));
    dto.setSymbol(rs.getString(DBUtils.symbol));
    return dto;
  }
}
