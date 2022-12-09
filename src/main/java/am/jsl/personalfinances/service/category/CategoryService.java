package am.jsl.personalfinances.service.category;

import am.jsl.personalfinances.domain.Category;
import am.jsl.personalfinances.dto.CategoryDTO;
import am.jsl.personalfinances.service.BaseService;

import java.util.List;

/**
*Interfejs usługi, który definiuje wszystkie metody pracy z obiektem domeny {@link Category}.
*/
public interface CategoryService extends BaseService<Category> {
/**
*Zwraca kategorie dla podanego identyfikatora użytkownika.
*@param userId identyfikator użytkownika
*@zwróć kategorie
*/
	List<CategoryDTO> getCategories(long userId);

/**
*Zwraca kategorie nadrzędne dla podanego identyfikatora użytkownika.
*@param userId identyfikator użytkownika
*@zwróć kategorie
*/
	List<CategoryDTO> lookupParentCategories(long userId);
}
