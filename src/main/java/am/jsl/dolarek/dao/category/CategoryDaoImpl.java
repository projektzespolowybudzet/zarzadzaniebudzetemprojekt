package am.jsl.dolarek.dao.category;

import am.jsl.dolarek.dao.BaseDaoImpl;
import am.jsl.dolarek.dao.DBUtils;
import am.jsl.dolarek.domain.Category;
import am.jsl.dolarek.dto.CategoryDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Implementacja interfejsu Dao do uzyskiwania dostępu do obiektu domeny {@link Category}.
 */
@Repository("categoryDao")
@Lazy
public class CategoryDaoImpl
  extends BaseDaoImpl<Category>
  implements CategoryDao {

  private CategoryMapper categoryMapper = new CategoryMapper();
  private CategoryDTOMapper categoryDTOMapper = new CategoryDTOMapper();

  private RowMapper<CategoryDTO> parentLookupMapper = (rs, rowNum) -> {
    CategoryDTO entity = new CategoryDTO();
    entity.setId(rs.getLong(DBUtils.id));
    entity.setName(rs.getString(DBUtils.name));
    entity.setIcon(rs.getString(DBUtils.icon));
    return entity;
  };

  private RowMapper<Category> lookupMapper = (rs, rowNum) -> {
    Category entity = new Category();
    entity.setId(rs.getLong(DBUtils.id));
    entity.setName(rs.getString(DBUtils.name));
    return entity;
  };

  @Autowired
  CategoryDaoImpl(DataSource dataSource) {
    super(dataSource);
  }

  private static final String getCategoriesSql =
    "select c.id, c.name, c.icon, c.color, c.parent_id " +
    "from category c " +
    "where c.user_id = :user_id order by c.parent_id, c.name";

  /**
   * Zwraca listę kategorii dla określonego użytkownika.
   *
   * @param userId - identyfikator użytkownika
   */
  @Override
  public List<CategoryDTO> getCategories(long userId) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.user_id, userId);

    return parameterJdbcTemplate.query(
      getCategoriesSql,
      params,
      categoryDTOMapper
    );
  }

  private static final String canDeleteSql =
    "select c.id  from category c " +
    "inner join transaction tr on tr.category_id = c.id " +
    "where c.id = :id and c.user_id = :user_id";

  /**
   * Zwraca true, jeśli użytkownik może usunąć danego użytkownika.
   *
   * @param id - identyfikator użytkownika do usunięcia
   * @param userId - identyfikator użytkownika do sprawdzenia usunięcia
   */
  @Override
  public boolean canDelete(long id, long userId) {
    return canDelete(id, userId, canDeleteSql);
  }

  private static final String deleteSql =
    "delete from category where user_id = :user_id and id = :id";

  /**
   * Usuń użytkownika z bazy danych.
   *
   * @param id - the id of the user to delete
   * @param userId - the user id of the user to delete
   */
  @Override
  public void delete(long id, long userId) {
    delete(id, userId, deleteSql);
  }

  private static final String existsSql =
    "select id from category where user_id = :user_id and LOWER(name) = :name and id != :id";

  /**
   * Sprawdza, czy istnieje podany użytkownik.
   *
   * @param name - the name of the table to check
   * @param id - the user id of the user
   * @param userId - The user id of the user.
   */
  @Override
  public boolean exists(String name, long id, long userId) {
    return exists(name, id, userId, existsSql);
  }

  private static final String createSql =
    "insert into category " +
    "(id, name, icon, color, description, parent_id, user_id) " +
    "values(:id, :name, :icon, :color, :description, :parent_id, :user_id)";

  /**
   * Tworzy nową kategorię.
   *
   * @param category - The category to be created.
   */
  @Override
  public void create(Category category) {
    long id = DBUtils.getNextId(getJdbcTemplate(), "category");
    category.setId(id);
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.id, category.getId());
    params.put(DBUtils.name, category.getName());
    params.put(DBUtils.icon, category.getIcon());
    params.put(DBUtils.color, category.getColor());
    params.put(DBUtils.description, category.getDescription());
    params.put(DBUtils.parent_id, category.getParentId());
    params.put(DBUtils.user_id, category.getUserId());
    parameterJdbcTemplate.update(createSql, params);
  }

  private static final String updateSql =
    "update category " +
    "set name = :name, icon = :icon, color = :color, " +
    "description = :description, parent_id = :parent_id " +
    "where user_id = :user_id and id = :id";

  /**
   * Zaktualizuj określoną kategorię w bazie danych.
   *
   * @param category - The category to update.
   */
  @Override
  public void update(Category category) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.id, category.getId());
    params.put(DBUtils.name, category.getName());
    params.put(DBUtils.icon, category.getIcon());
    params.put(DBUtils.color, category.getColor());
    params.put(DBUtils.description, category.getDescription());
    params.put(DBUtils.parent_id, category.getParentId());
    params.put(DBUtils.user_id, category.getUserId());
    parameterJdbcTemplate.update(updateSql, params);
  }

  private static final String getSql =
    "select * from category where user_id = :user_id and id = :id";

  /**
   * Zwraca kategorię z podanym identyfikatorem i identyfikatorem użytkownika.
   *
   * @param id - the id of the category to retrieve
   * @param userId - the user id of the category
   */
  @Override
  public Category get(long id, long userId) {
    return get(id, userId, getSql, categoryMapper);
  }

  private static final String lookupParentCategoriesSql =
    "select id, name, icon from category where user_id = :user_id and parent_id = 0 " +
    "order by name asc";

  /**
   * Wyszukiwanie kategorii nadrzędnych dla danego użytkownika.
   *
   * @param userId - the user id of the user
   */
  @Override
  public List<CategoryDTO> lookupParentCategories(long userId) {
    Map<String, Object> params = new HashMap<>();
    params.put(DBUtils.user_id, userId);

    return parameterJdbcTemplate.query(
      lookupParentCategoriesSql,
      params,
      parentLookupMapper
    );
  }

  private static final String lookupSql =
    "select id, name  " +
    "from category where user_id = :user_id order by name";

  /**
   * Kategoria wyszukiwania dla danego użytkownika.
   *
   * @param userId - the user id to lookup.
   */
  @Override
  public List<Category> lookup(long userId) {
    return lookup(userId, lookupSql, lookupMapper);
  }
}
