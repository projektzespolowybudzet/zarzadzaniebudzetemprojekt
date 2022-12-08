package am.jsl.personalfinances.dao.currency;

import am.jsl.personalfinances.dao.DBUtils;
import am.jsl.personalfinances.domain.Currency;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
*Implementacja {@link RowMapper}, która konwertuje wiersz na nową instancję Currency.
*/
public class CurrencyMapper implements RowMapper<Currency> {

	public Currency mapRow(ResultSet rs, int rowNum) throws SQLException {
		Currency currency = new Currency();
		currency.setCode(rs.getString(DBUtils.iso_code));
		currency.setName(rs.getString(DBUtils.name));
		currency.setSymbol(rs.getString(DBUtils.symbol));
		return currency;
	}
}
