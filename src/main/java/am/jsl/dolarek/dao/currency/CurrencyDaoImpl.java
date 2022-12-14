package am.jsl.dolarek.dao.currency;

import am.jsl.dolarek.dao.BaseDaoImpl;
import am.jsl.dolarek.dao.DBUtils;
import am.jsl.dolarek.domain.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

/**
 * Implementacja interfejsu Dao do uzyskiwania dostępu do obiektu domeny {@link Currency}.
 */
@Repository("currencyDao")
@Lazy
public class CurrencyDaoImpl
  extends BaseDaoImpl<Currency>
  implements CurrencyDao {

  private CurrencyMapper currencyMapper = new CurrencyMapper();

  @Autowired
  CurrencyDaoImpl(DataSource dataSource) {
    super(dataSource);
  }

  private static final String listSql =
    "select * from currency order by name, iso_code";

  private static final String getByCodeSql =
    "select * from currency where iso_code = :iso_code";

  /**
   * Returns the currency object for the given ISO code.
   *
   * @param isoCode - the ISO code to retrieve
   */
  @Override
  public Currency getByCode(String isoCode) {
    try {
      Map<String, Object> params = new HashMap<>();
      params.put(DBUtils.iso_code, isoCode);
      return parameterJdbcTemplate.queryForObject(
        getByCodeSql,
        params,
        currencyMapper
      );
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  /**
   * Returns a list of all currencies.
   */
  @Override
  public List<Currency> list() {
    return parameterJdbcTemplate.query(listSql, currencyMapper);
  }

  private static final String deleteSql =
    "delete from currency where iso_code = :iso_code";

  /**
   * Delete a code from the database.
   *
   * @param code - the ISO code to delete.
   */
  @Override
  public void delete(String code) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.iso_code, code);
    parameterJdbcTemplate.update(deleteSql, params);
  }

  private static final String createSql =
    "insert into currency " +
    "(iso_code, name, symbol) values(:iso_code, :name, :symbol)";

  /**
   * Creates a new currency.
   *
   * @param currency - The currency to create.
   */
  @Override
  public void create(Currency currency) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.iso_code, currency.getCode());
    params.put(DBUtils.name, currency.getName());
    params.put(DBUtils.symbol, currency.getSymbol());
    parameterJdbcTemplate.update(createSql, params);
  }

  private static final String updateSql =
    "update currency " +
    "set name = :name, symbol = :symbol where iso_code = :iso_code";

  /**
   * Update the specified currency in the database.
   *
   * @param currency - The currency to update.
   */
  @Override
  public void update(Currency currency) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.iso_code, currency.getCode());
    params.put(DBUtils.name, currency.getName());
    params.put(DBUtils.symbol, currency.getSymbol());
    parameterJdbcTemplate.update(updateSql, params);
  }
}
