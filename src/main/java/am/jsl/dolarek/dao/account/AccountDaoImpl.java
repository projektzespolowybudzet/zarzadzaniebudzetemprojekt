package am.jsl.dolarek.dao.account;

import am.jsl.dolarek.dao.BaseDaoImpl;
import am.jsl.dolarek.dao.DBUtils;
import am.jsl.dolarek.domain.account.Account;
import am.jsl.dolarek.dto.account.AccountListDTO;
import am.jsl.dolarek.dto.account.AdjustBalanceDTO;
import am.jsl.dolarek.util.DateUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Implementacja interfejsu Dao do uzyskiwania dostępu do obiektu domeny {@link Account}.
 */
@Repository("accountDao")
@Lazy
public class AccountDaoImpl extends BaseDaoImpl<Account> implements AccountDao {

  private AccountMapper accountMapper = new AccountMapper();
  private AccountListDTOMapper listDTOMapper = new AccountListDTOMapper();
  private RowMapper<Account> lookupMapper = (rs, rowNum) -> {
    Account entity = new Account();
    entity.setId(rs.getLong(DBUtils.id));
    entity.setName(rs.getString(DBUtils.name));
    entity.setCurrency(rs.getString(DBUtils.currency));
    entity.setSymbol(rs.getString(DBUtils.symbol));
    entity.setIcon(rs.getString(DBUtils.icon));
    entity.setColor(rs.getString(DBUtils.color));
    return entity;
  };

  @Autowired
  public AccountDaoImpl(DataSource dataSource) {
    super(dataSource);
  }

  private static final String getAccountsSql =
    "select a.id, a.name, a.icon, a.color, " +
    "a.account_type, a.balance, a.currency, a.symbol, a.active  " +
    "from account a " +
    "where a.user_id = :user_id order by a.sort_order, a.name asc";

  /**
   * Zwraca listę kont określonego użytkownika.
   *
   * @param userId - identyfikator użytkownika
   */
  @Override
  public List<AccountListDTO> getAccounts(long userId) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.user_id, userId);

    return parameterJdbcTemplate.query(getAccountsSql, params, listDTOMapper);
  }

  private static final String getActiveAccountsSql =
    "select a.id, a.name, a.icon, a.color, " +
    "a.account_type, a.balance, a.currency, a.symbol, a.active  " +
    "from account a " +
    "where a.user_id = :user_id and a.active = 1 order by a.sort_order, a.name asc";

  /**
   * Zwraca listę aktywnych kont użytkownika.
   *
   * @param userId - identyfikator użytkownika
   */
  @Override
  public List<AccountListDTO> getActiveAccounts(long userId) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.user_id, userId);

    return parameterJdbcTemplate.query(
      getActiveAccountsSql,
      params,
      listDTOMapper
    );
  }

  private static final String canDeleteSql =
    "select a.id  from account a " +
    "inner join transaction tr on tr.account_id = a.id " +
    "where a.id = :id and a.user_id = :user_id";

  /**
   * Zwraca true, jeśli użytkownik może usunąć danego użytkownika.
   *
   * @param id - identyfikator użytkownika
   * @param userId - identyfikator użytkownika do sprawdzenia usunięcia
   */
  @Override
  public boolean canDelete(long id, long userId) {
    return canDelete(id, userId, canDeleteSql);
  }

  private static final String deleteSql =
    "delete from account where user_id = :user_id and id = :id";

  /**
   * Usuń użytkownika z bazy danych.
   *
   * @param id - identyfikator użytkownika
   * @param userId - identyfikator użytkownika do usunięnia
   */
  @Override
  public void delete(long id, long userId) {
    delete(id, userId, deleteSql);
  }

  private static final String existsSql =
    "select id from account where user_id = :user_id and LOWER(name) = :name and id != :id";

  /**
   * Sprawdza, czy istnieje podany użytkownik.
   *
   * @param name - nazwa użytkownika
   * @param id - identyfikator
   * @param userId - Identyfikator użytkownika.
   */
  @Override
  public boolean exists(String name, long id, long userId) {
    return exists(name, id, userId, existsSql);
  }

  private static final String createSql =
    "insert into account " +
    "(id, name, description, balance, account_type, currency, symbol, active, sort_order, " +
    "icon, color, user_id, created_at) " +
    "values(:id, :name, :description, :balance, :account_type, :currency, :symbol, :active, :sort_order, " +
    ":icon, :color, :user_id, :created_at)";

  /**
   * Stwórz nowe konto.
   *
   * @param account - Konto, które ma zostać utworzone
   */
  @Override
  public void create(Account account) {
    long id = DBUtils.getNextId(getJdbcTemplate(), "account");
    account.setId(id);
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.id, account.getId());
    params.put(DBUtils.name, account.getName());
    params.put(DBUtils.description, account.getDescription());

    params.put(DBUtils.balance, account.getBalance());
    params.put(DBUtils.account_type, account.getAccountType());
    params.put(DBUtils.currency, account.getCurrency());
    params.put(DBUtils.symbol, account.getSymbol());
    params.put(DBUtils.active, account.isActive());
    params.put(DBUtils.sort_order, account.getSortOrder());

    params.put(DBUtils.icon, account.getIcon());
    params.put(DBUtils.color, account.getColor());

    params.put(DBUtils.user_id, account.getUserId());
    params.put(DBUtils.created_at, DateUtils.convert(account.getCreatedAt()));
    parameterJdbcTemplate.update(createSql, params);
  }

  private static final String updateSql =
    "update account " +
    "set name = :name, description = :description, balance = :balance, account_type = :account_type, " +
    "currency = :currency, symbol = :symbol, active = :active, sort_order = :sort_order, icon = :icon, color = :color " +
    "where user_id = :user_id and id = :id";

  /**
   * Zaktualizuj określone konto w bazie danych.
   *
   * @param account - Konto do aktualizacji.
   */
  @Override
  public void update(Account account) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.id, account.getId());
    params.put(DBUtils.name, account.getName());
    params.put(DBUtils.description, account.getDescription());

    params.put(DBUtils.balance, account.getBalance());
    params.put(DBUtils.account_type, account.getAccountType());
    params.put(DBUtils.currency, account.getCurrency());
    params.put(DBUtils.symbol, account.getSymbol());
    params.put(DBUtils.active, account.isActive());
    params.put(DBUtils.sort_order, account.getSortOrder());

    params.put(DBUtils.icon, account.getIcon());
    params.put(DBUtils.color, account.getColor());

    params.put(DBUtils.user_id, account.getUserId());
    params.put(DBUtils.created_at, DateUtils.convert(account.getCreatedAt()));
    parameterJdbcTemplate.update(updateSql, params);
  }

  private static final String getSql =
    "select * from account where user_id = :user_id and id = :id";

  /**
   * Zwraca konto z podanym identyfikatorem i identyfikatorem użytkownika.
   *
   * @param id - identyfikator konta
   * @param userId - identyfikator użytkownika
   */
  @Override
  public Account get(long id, long userId) {
    return get(id, userId, getSql, accountMapper);
  }

  private static final String lookupSql =
    "select id, name, currency, symbol, icon, color " +
    "from account where user_id = :user_id and active = 1 order by sort_order, name asc";

  /**
   * Zwraca listę kont danego użytkownika.
   *
   * @param userId - identyfikator użytkownika do wyszukiwania.
   */
  @Override
  public List<Account> lookup(long userId) {
    return lookup(userId, lookupSql, lookupMapper);
  }

  private static final String adjustBalanceSql =
    "update account " +
    "set balance = :balance where user_id = :user_id and id = :id";

  /**
   * Zaktualizuj saldo konta.
   *
   * @param adjustBalance - { @link AdjustBalanceDTO }
   */
  @Override
  public void updateBalance(AdjustBalanceDTO adjustBalance) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.id, adjustBalance.getId());
    params.put(DBUtils.balance, adjustBalance.getBalance());
    params.put(DBUtils.user_id, adjustBalance.getUserId());
    parameterJdbcTemplate.update(adjustBalanceSql, params);
  }

  private static final String decreaseBalanceSql =
    "update account " +
    "set balance = (balance - :amount) where user_id = :user_id and id = :id";

  /**
   * Zmniejszyć równowagę określonego użytkownika.
   *
   * @param id - identyfikator konta
   * @param userId - identyfikator użytkownika
   * @param amount - kwota zmniejszenia salda użytkownika
   */
  @Override
  public void decreaseBalance(long id, long userId, double amount) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.id, id);
    params.put(DBUtils.amount, amount);
    params.put(DBUtils.user_id, userId);
    parameterJdbcTemplate.update(decreaseBalanceSql, params);
  }

  private static final String increaseBalanceSql =
    "update account " +
    "set balance = (balance + :amount) where user_id = :user_id and id = :id";

  /**
   * Zwiększa ilość salda określonego użytkownika.
   *
   * @param id - identyfikator konta
   * @param userId - identyfikator użytkownika
   * @param amount - kwota zwiększenia salda użytkownika
   */
  @Override
  public void increaseBalance(long id, long userId, double amount) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.id, id);
    params.put(DBUtils.amount, amount);
    params.put(DBUtils.user_id, userId);
    parameterJdbcTemplate.update(increaseBalanceSql, params);
  }
}
