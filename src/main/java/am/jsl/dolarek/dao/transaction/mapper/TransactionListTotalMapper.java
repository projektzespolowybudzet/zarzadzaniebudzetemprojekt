package am.jsl.dolarek.dao.transaction.mapper;

import org.springframework.jdbc.core.RowMapper;

import am.jsl.dolarek.dao.DBUtils;
import am.jsl.dolarek.dto.transaction.TransactionListTotalDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
*Implementacja {@link RowMapper}, która konwertuje wiersz na nową instancję TransactionListTotalDTO.
*/
public class TransactionListTotalMapper implements RowMapper<TransactionListTotalDTO> {
    @Override
    public TransactionListTotalDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        TransactionListTotalDTO total = new TransactionListTotalDTO();
        total.setTransactionType(rs.getByte(DBUtils.transaction_type));
        total.setTotal(rs.getDouble(DBUtils.total));
        total.setSymbol(rs.getString(DBUtils.symbol));
        return total;
    }
}
