package am.jsl.dolarek.dao.category;

import am.jsl.dolarek.dao.BaseDao;
import am.jsl.dolarek.domain.Category;
import am.jsl.dolarek.dto.CategoryDTO;
import java.util.List;

/**
 * Interfejs Dao do uzyskiwania dostępu do obiektu domeny {@link Category}.
 */
public interface CategoryDao extends BaseDao<Category> {
  /**
   * Zwraca kategorie dla podanego identyfikatora użytkownika.
   *
   * @param userId identyfikator użytkownika
   * @return kategorie
   */
  List<CategoryDTO> getCategories(long userId);

  /**
   * Zwraca kategorie nadrzędne dla podanego identyfikatora użytkownika.
   *
   * @param userId identyfikator użytkownika
   * @return kategorie
   */
  List<CategoryDTO> lookupParentCategories(long userId);
}
