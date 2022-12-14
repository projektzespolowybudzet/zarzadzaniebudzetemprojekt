package am.jsl.dolarek.dao.transaction.mapper;

import org.springframework.jdbc.core.RowMapper;

import am.jsl.dolarek.dao.DBUtils;
import am.jsl.dolarek.dto.transaction.TransactionDetailsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
*Implementacja {@link RowMapper}, która konwertuje wiersz na nową instancję TransactionDetailsDTO.
*/
public class TransactionDetailsMapper implements RowMapper<TransactionDetailsDTO> {

	public TransactionDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		TransactionDetailsDTO dto = new TransactionDetailsDTO();
		dto.setId(rs.getLong(DBUtils.id));

		dto.setAccount(rs.getString(DBUtils.account));
		dto.setAccountIcon(rs.getString(DBUtils.account_icon));
		dto.setAccountColor(rs.getString(DBUtils.account_color));
		dto.setSymbol(rs.getString(DBUtils.symbol));

		dto.setCategory(rs.getString(DBUtils.category));
		dto.setCategoryIcon(rs.getString(DBUtils.category_icon));
		dto.setCategoryColor(rs.getString(DBUtils.category_color));

		dto.setParentCategory(rs.getString(DBUtils.parent_category));
		dto.setParentCategoryIcon(rs.getString(DBUtils.parent_category_icon));
		dto.setParentCategoryColor(rs.getString(DBUtils.parent_category_color));
		dto.setAmount(rs.getDouble(DBUtils.amount));
		dto.setTransactionType(rs.getByte(DBUtils.transaction_type));
		dto.setTransactionDate(rs.getTimestamp(DBUtils.transaction_date));
		dto.setDescription(rs.getString(DBUtils.description));
		return dto;
	}
}
