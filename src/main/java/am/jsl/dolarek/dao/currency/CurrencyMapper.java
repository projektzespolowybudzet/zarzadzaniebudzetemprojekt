package am.jsl.dolarek.dao.currency;

import am.jsl.dolarek.dao.DBUtils;
import am.jsl.dolarek.domain.Currency;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Implementacja {@link RowMapper}, która konwertuje wiersz na nową instancję Currency.
 */
public class CurrencyMapper implements RowMapper<Currency> {

  /**
   * Map a row of the result set into a Currency object.
   *
   * @param rs - ResultSet containing the result set.
   * @param rowNum - the row number to map.
   */
  public Currency mapRow(ResultSet rs, int rowNum) throws SQLException {
    Currency currency = new Currency();
    currency.setCode(rs.getString(DBUtils.iso_code));
    currency.setName(rs.getString(DBUtils.name));
    currency.setSymbol(rs.getString(DBUtils.symbol));
    return currency;
  }
}
