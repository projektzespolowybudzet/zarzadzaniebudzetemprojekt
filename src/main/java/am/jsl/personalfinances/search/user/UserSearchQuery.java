package am.jsl.personalfinances.search.user;

import am.jsl.personalfinances.domain.user.User;
import am.jsl.personalfinances.search.Query;

/**
*Niestandardowe {@link Query} do wyszukiwania elementów {@link User}.
*/
public class UserSearchQuery extends Query<User> {

/**
*Tworzy instancję nowego zapytania wyszukiwania użytkownika.
*
*@param strona strona
*@param pageSize rozmiar strony
*/
    public UserSearchQuery(int page, int pageSize) {
        super(page, pageSize);
    }
}
