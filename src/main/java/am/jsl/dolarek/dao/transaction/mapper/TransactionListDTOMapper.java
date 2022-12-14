package am.jsl.dolarek.dao.transaction.mapper;

import am.jsl.dolarek.dao.DBUtils;
import am.jsl.dolarek.dto.transaction.TransactionListDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Implementacja {@link RowMapper}, która konwertuje wiersz na nową instancję TransactionListDTO.
 */
public class TransactionListDTOMapper implements RowMapper<TransactionListDTO> {

  /**
   * Map a row of a ResultSet into a TransactionListDTO.
   *
   * @param rs ResultSet containing the result set.
   * @param rowNum the row number to map.
   */
  public TransactionListDTO mapRow(ResultSet rs, int rowNum)
    throws SQLException {
    TransactionListDTO listDTO = new TransactionListDTO();
    listDTO.setId(rs.getLong(DBUtils.id));

    listDTO.setSymbol(rs.getString(DBUtils.symbol));
    listDTO.setCategory(rs.getString(DBUtils.category));
    listDTO.setCategoryIcon(rs.getString(DBUtils.category_icon));
    listDTO.setCategoryColor(rs.getString(DBUtils.category_color));

    listDTO.setParentCategory(rs.getString(DBUtils.parent_category));
    listDTO.setParentCategoryIcon(rs.getString(DBUtils.parent_category_icon));
    listDTO.setParentCategoryColor(rs.getString(DBUtils.parent_category_color));

    listDTO.setAmount(rs.getDouble(DBUtils.amount));
    listDTO.setTransactionType(rs.getByte(DBUtils.transaction_type));
    listDTO.setTransactionDate(rs.getTimestamp(DBUtils.transaction_date));
    return listDTO;
  }
}
