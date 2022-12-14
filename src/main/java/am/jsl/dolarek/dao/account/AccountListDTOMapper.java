package am.jsl.dolarek.dao.account;

import org.springframework.jdbc.core.RowMapper;

import am.jsl.dolarek.dao.DBUtils;
import am.jsl.dolarek.dto.account.AccountListDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
*Implementacja {@link RowMapper}, która konwertuje wiersz na nową instancję AccountListDTO.
*/
public class AccountListDTOMapper implements RowMapper<AccountListDTO> {

	public AccountListDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		AccountListDTO account = new AccountListDTO();
		account.setId(rs.getLong(DBUtils.id));
		account.setName(rs.getString(DBUtils.name));
		account.setIcon(rs.getString(DBUtils.icon));
		account.setColor(rs.getString(DBUtils.color));
		account.setAccountType(rs.getByte(DBUtils.account_type));
		account.setBalance(rs.getDouble(DBUtils.balance));
		account.setCurrency(rs.getString(DBUtils.currency));
		account.setSymbol(rs.getString(DBUtils.symbol));
		account.setActive(rs.getBoolean(DBUtils.active));

		return account;
	}
}
