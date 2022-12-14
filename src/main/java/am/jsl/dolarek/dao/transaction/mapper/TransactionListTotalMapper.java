package am.jsl.dolarek.dao.transaction.mapper;

import am.jsl.dolarek.dao.DBUtils;
import am.jsl.dolarek.dto.transaction.TransactionListTotalDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Implementacja {@link RowMapper}, która konwertuje wiersz na nową instancję TransactionListTotalDTO.
 */
public class TransactionListTotalMapper
  implements RowMapper<TransactionListTotalDTO> {

  /**
   * Map a row of the result set to a TransactionListTotalDTO.
   *
   * @param rs the ResultSet to map the row to a TransactionListTotalDTO
   * @param rowNum the row number to map.
   */
  @Override
  public TransactionListTotalDTO mapRow(ResultSet rs, int rowNum)
    throws SQLException {
    TransactionListTotalDTO total = new TransactionListTotalDTO();
    total.setTransactionType(rs.getByte(DBUtils.transaction_type));
    total.setTotal(rs.getDouble(DBUtils.total));
    total.setSymbol(rs.getString(DBUtils.symbol));
    return total;
  }
}
