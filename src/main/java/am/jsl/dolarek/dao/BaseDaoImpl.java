package am.jsl.dolarek.dao;

import am.jsl.dolarek.domain.NamedEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Implementacja interfejsu BaseDao z obsługą JDBC Dao Springframework.
 * <p>Wymaga ustawienia {@link javax.sql.DataSource}.
 *
 * @param <T> parametr typu
 */
public class BaseDaoImpl<T> extends AbstractDaoImpl implements BaseDao<T> {

  /**
   * Narzędzie do mapowania wierszy dla ogólnego typu {@link NamedEntity}.
   */
  protected NamedEntityMapper namedMapper = new NamedEntityMapper();

  /**
   * Tworzy nowy BaseDaoImpl dla danego {@link DataSource}.
   *
   * @param dataSource źródło danych JDBC, aby uzyskać dostęp
   */
  public BaseDaoImpl(DataSource dataSource) {
    super(dataSource);
  }

  /**
   * Returns a list of all the users in the database.
   *
   * @param userId - the user id of the user to list
   */
  @Override
  public List<T> list(long userId) {
    return null;
  }

  /**
   * Lista list.
   *
   * @param userId identyfikator użytkownika
   * @param sql plik sql
   * @param rowMapper mapowanie wierszy
   * @return listę
   */
  protected List<T> list(long userId, String sql, RowMapper<T> rowMapper) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.user_id, userId);

    return parameterJdbcTemplate.query(sql, params, rowMapper);
  }

  /**
   * Returns true if the user can delete the given user.
   *
   * @param id - the id of the user to delete
   * @param userId - the user id of the user to delete
   */
  @Override
  public boolean canDelete(long id, long userId) {
    return true;
  }

  /**
   * Delete a user from the database.
   *
   * @param id - the id of the user to delete
   * @param userId - the user id of the user to delete
   */
  @Override
  public void delete(long id, long userId) {}

  /**
   * Returns true if the user exists.
   *
   * @param name - the name of the user
   * @param id - the user id of the user
   * @param userId - the user id of the user
   */
  @Override
  public boolean exists(String name, long id, long userId) {
    return false;
  }

  /**
   * This method is called when the object is created.
   *
   * @param object - The object to create.
   */
  @Override
  public void create(T object) {}

  /**
   * Update the object with the given object.
   *
   * @param object - the object to update.
   */
  @Override
  public void update(T object) {}

  /**
   * Returns the object for the given user.
   *
   * @param id - the id of the user
   * @param userId - the user id of the user
   */
  @Override
  public T get(long id, long userId) {
    return null;
  }

  /**
   * Returns a list of objects that are associated with the given user.
   *
   * @param userId - the user id to lookup.
   */
  @Override
  public List<T> lookup(long userId) {
    return null;
  }

  /**
   * Pobiera podmiot według jego identyfikatora, identyfikatora użytkownika i sql.
   *
   * @param id identyfikator jednostki
   * @param userId identyfikator użytkownika powiązany z jednostką
   * @param sql sql do wysyłania zapytań do jednostki
   * @param mapper program odwzorowujący obiekt mapujący z {@link java.sql.ResultSet}
   * @return obiekt
   */
  protected T get(long id, long userId, String sql, RowMapper<T> mapper) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.id, id);
    params.put(DBUtils.user_id, userId);

    try {
      return parameterJdbcTemplate.queryForObject(sql, params, mapper);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  /**
   * Zwraca wszystkie wystąpienia NamedEntity dla danego użytkownika.
   *
   * @param userId identyfikator użytkownika powiązany z jednostką
   * @param sql sql do wysyłania zapytań do jednostek
   * @return listę
   */
  protected List<NamedEntity> lookup(long userId, String sql) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.user_id, userId);

    return parameterJdbcTemplate.query(sql, params, namedMapper);
  }

  /**
   * Zwraca wszystkie wystąpienia typu dla danego użytkownika.
   *
   * @param userId identyfikator użytkownika powiązany z jednostką
   * @param sql sql do wysyłania zapytań do jednostek
   * @param rowMapper mapowanie do mapowania jednostek z {@link java.sql.ResultSet}
   * @return listę
   */
  protected List<T> lookup(long userId, String sql, RowMapper<T> rowMapper) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.user_id, userId);

    return parameterJdbcTemplate.query(sql, params, rowMapper);
  }

  /**
   * Zwraca ciąg znaków z podanej jednostki.
   *
   * @param id identyfikator jednostki
   * @param userId identyfikator użytkownika powiązany z jednostką
   * @param sql sql do wysyłania zapytań o łańcuch
   * @return ciąg znaków
   */
  protected String getString(long id, long userId, String sql) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.id, id);
    params.put(DBUtils.user_id, userId);

    return parameterJdbcTemplate.queryForObject(sql, params, String.class);
  }

  /**
   * Usuwa podmiot o podanym identyfikatorze i identyfikatorze użytkownika.
   *
   * @param id identyfikator jednostki
   * @param userId identyfikator użytkownika powiązany z jednostką
   * @param sql sql do usunięcia jednostki
   */
  protected void delete(long id, long userId, String sql) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.id, id);
    params.put(DBUtils.user_id, userId);
    parameterJdbcTemplate.update(sql, params);
  }

  /**
   * Sprawdza czy string istnieje w encji o podanym id i czy istnieje id użytkownika.
   *
   * @param name nazwa jednostki
   * @param id identyfikator jednostki
   * @param userId identyfikator użytkownika powiązany z jednostką
   * @param sql sql do wysyłania zapytań do jednostki
   * @return wartość logiczną
   */
  protected boolean exists(String name, long id, long userId, String sql) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.name, name);
    params.put(DBUtils.id, id);
    params.put(DBUtils.user_id, userId);
    List<Long> list = parameterJdbcTemplate.queryForList(
      sql,
      params,
      Long.class
    );

    return list.size() > 0;
  }

  /**
   * Zwraca, czy podmiot o podanym identyfikatorze i identyfikatorze użytkownika może zostać usunięty.
   *
   * @param id identyfikator użytkownika powiązany z jednostką
   * @param userId identyfikator użytkownika
   * @param query zapytanie do sql w celu zapytania o jednostkę
   * @return wartość logiczną
   */
  protected boolean canDelete(long id, long userId, String query) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.id, id);
    params.put(DBUtils.user_id, userId);

    List<Long> list = parameterJdbcTemplate.queryForList(
      query,
      params,
      Long.class
    );

    /**
     * Returns true if the list is empty.
     */
    if (list.size() > 0) {
      return false;
    }
    return true;
  }
}
