package am.jsl.dolarek.service.category;

import am.jsl.dolarek.domain.Category;
import am.jsl.dolarek.dto.CategoryDTO;
import am.jsl.dolarek.service.BaseService;
import java.util.List;

/**
 * Interfejs usługi, który definiuje wszystkie metody pracy z obiektem domeny {@link Category}.
 */
public interface CategoryService extends BaseService<Category> {
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
