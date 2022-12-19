package am.jsl.dolarek.search.user;

import am.jsl.dolarek.domain.user.User;
import am.jsl.dolarek.search.Query;

/**
 * Niestandardowe {@link Query} do wyszukiwania elementów {@link User}.
 */
public class UserSearchQuery extends Query<User> {

  /**
   * Tworzy instancję nowego zapytania wyszukiwania użytkownika.
   *
   * @param page strona
   * @param pageSize rozmiar strony
   */
  public UserSearchQuery(int page, int pageSize) {
    super(page, pageSize);
  }
}
