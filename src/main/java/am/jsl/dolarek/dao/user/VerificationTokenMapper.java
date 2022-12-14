package am.jsl.dolarek.dao.user;

import am.jsl.dolarek.dao.DBUtils;
import am.jsl.dolarek.domain.user.VerificationToken;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Implementacja {@link RowMapper}, która konwertuje wiersz na nową instancję VerificationToken.
 */
public class VerificationTokenMapper implements RowMapper<VerificationToken> {

  /**
   * Maps a row of the result set to a VerificationToken.
   *
   * @param rs - ResultSet containing the row to map.
   * @param rowNum - the row number to map.
   */
  public VerificationToken mapRow(ResultSet rs, int rowNum)
    throws SQLException {
    VerificationToken token = new VerificationToken();
    token.setId(rs.getLong(DBUtils.id));
    token.setUserId(rs.getLong(DBUtils.user_id));
    token.setToken(rs.getString(DBUtils.token));
    token.setTokenType(rs.getByte(DBUtils.token_type));
    token.setExpiryDate(rs.getDate(DBUtils.expiry_date));
    token.setExpired(rs.getBoolean(DBUtils.expired));
    return token;
  }
}
