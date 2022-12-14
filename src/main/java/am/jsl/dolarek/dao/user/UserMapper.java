package am.jsl.dolarek.dao.user;

import am.jsl.dolarek.dao.DBUtils;
import am.jsl.dolarek.domain.user.Role;
import am.jsl.dolarek.domain.user.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Implementacja {@link RowMapper}, która konwertuje wiersz na nową instancję użytkownika.
 */
public class UserMapper implements RowMapper<User> {

  public static final String LOGIN = "login";
  public static final String PASSWORD = "password";
  public static final String FIRST_NAME = "first_name";
  public static final String LAST_NAME = "last_name";
  public static final String LAST_LOGIN = "last_login";
  public static final String LAST_PASSWORD_CHANGE = "last_password_change";
  public static final String ROLE = "role";

  /**
   * Map a row from a ResultSet to a User object.
   *
   * @param rs - ResultSet containing the row to map.
   * @param rowNum - the row number to map.
   */
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    User user = new User();
    user.setId(rs.getLong(DBUtils.id));
    user.setLogin(rs.getString(LOGIN));
    user.setFirstName(rs.getString(FIRST_NAME));
    user.setLastName(rs.getString(LAST_NAME));
    user.setEmail(rs.getString(DBUtils.email));
    user.setPhone(rs.getString(DBUtils.phone));
    user.setIcon(rs.getString(DBUtils.icon));
    user.setEnabled(rs.getBoolean(DBUtils.enabled));
    user.setRole(Role.valueOf(rs.getString(ROLE)));
    return user;
  }
}
