package am.jsl.dolarek.dao.transaction.mapper;

import am.jsl.dolarek.dao.DBUtils;
import am.jsl.dolarek.domain.transaction.Transaction;
import am.jsl.dolarek.util.DateUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Implementacja {@link RowMapper}, która konwertuje wiersz na nową instancję Transaction.
 */
public class TransactionMapper implements RowMapper<Transaction> {

  /**
   * Map a row of data from a ResultSet.
   *
   * @param rs - ResultSet to map the row to a Transaction.
   * @param rowNum - the row number to map.
   */
  public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
    Transaction transaction = new Transaction();
    transaction.setId(rs.getLong(DBUtils.id));
    transaction.setAccountId(rs.getLong(DBUtils.account_id));
    transaction.setCategoryId(rs.getLong(DBUtils.category_id));
    transaction.setAmount(rs.getDouble(DBUtils.amount));
    transaction.setStatus(rs.getByte(DBUtils.status));
    transaction.setTransactionType(rs.getByte(DBUtils.transaction_type));
    transaction.setTransactionDate(
      DateUtils.convert(rs.getTimestamp(DBUtils.transaction_date))
    );
    transaction.setDescription(rs.getString(DBUtils.description));
    transaction.setUserId(rs.getLong(DBUtils.user_id));
    return transaction;
  }
}
