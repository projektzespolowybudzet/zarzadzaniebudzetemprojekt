package am.jsl.personalfinances.dao.user;

import am.jsl.personalfinances.dao.BaseDao;
import am.jsl.personalfinances.domain.user.User;
import am.jsl.personalfinances.domain.user.VerificationToken;
import am.jsl.personalfinances.domain.user.VerificationTokenType;
import am.jsl.personalfinances.ex.UserNotFoundException;
import am.jsl.personalfinances.search.ListPaginatedResult;
import am.jsl.personalfinances.search.user.UserSearchQuery;

/**
*Interfejs Dao do uzyskiwania dostępu do obiektu domeny {@link User}.
*/
public interface UserDao extends BaseDao<User> {
/**
*Pobiera podzielone na strony wyniki użytkowników dla danego zapytania.
*@param userSearchQuery {@link UserSearchQuery} zawierający opcje zapytania
*@return obiekt {@link ListPaginatedResult} zawierający wynik stronicowania
*/
    ListPaginatedResult<User> search(UserSearchQuery userSearchQuery);

    /**
     * Sets last_login field with current date for the given user id.
     * @param userId the user id
     */
    void login(long userId);

/**
*Usuwa użytkownika o podanym identyfikatorze użytkownika
*@param id identyfikator użytkownika
*/
    void deleteUser(long id);

/**
*Sprawdza, czy istnieje użytkownik o podanym loginie i id.
*@param zaloguj login
*@param id identyfikator użytkownika
*@return prawda, jeśli istnieje login użytkownika
*/
    boolean loginExists(String login, long id);

/**
*Sprawdza, czy istnieje użytkownik o podanym adresie e-mail i identyfikatorze.
*@param wyślij e-maile
*@param id identyfikator użytkownika
*@return prawda, jeśli istnieje adres e-mail użytkownika
*/
    boolean emailExists(String email, long id);

/**
*Tworzy użytkownika z podanym użytkownikiem.
*@param użytkownik użytkownik
*/
    void create(User user);

/**
*Aktualizuje użytkownika o podanego użytkownika.
*@param użytkownik użytkownik
*/
    void update(User user);

/**
*Zwraca użytkownika o podanym id.
*Wyrzuci {@link UserNotFoundException}, jeśli użytkownik nie zostanie znaleziony.
*@param userId identyfikator użytkownika
*@return użytkownika
*@throws UserNotFoundException, jeśli użytkownik nie został znaleziony
*/
    User getUser(long userId) throws UserNotFoundException;

/**
*Zmienia hasło użytkownika z podanym encryptedPassword.
*@param encryptedPassword zaszyfrowane hasło
*@param userId identyfikator użytkownika
*/
    void changePassword(String encryptedPassword, long userId);

/**
*Zwraca użytkownika o podanej nazwie użytkownika.
*Wyrzuci {@link UserNotFoundException}, jeśli użytkownik nie zostanie znaleziony.
*@param nazwa użytkownika nazwa użytkownika
*@return użytkownika
*@throws UserNotFoundException, jeśli użytkownik nie został znaleziony
*/
    User getUser(String username) throws UserNotFoundException;

/**
*Aktualizuje ikonę dla danego użytkownika.
*@param użytkownik użytkownik
*/
    void updateIcon(User user);

/**
*Aktualizuje profil danego użytkownika.
*@param użytkownik użytkownik
*/
    void updateProfile(User user) throws Exception;

/**
*Zwraca użytkownika z podanym e-mailem.
*@param wyślij e-maile
*@return użytkownika
*/
    User getUserByEmail(String email);

/**
*Tworzy podany {@link VerificationToken}
*@param weryfikacjaToken tokenu weryfikacyjnego
*/
    void createVerificationToken(VerificationToken verificationToken);

/**
*Aktualizuje podany {@link VerificationToken}
*@param weryfikacjaToken tokenu weryfikacyjnego
*/
    void updateVerificationToken(VerificationToken verificationToken);

/**
*Zwraca VerificationToken z identyfikatorem użytkownika i tokenType.
*@param userId identyfikator użytkownika
*@param token Wpisz typ tokena
*@return token weryfikacyjny
*/
    VerificationToken getToken(long userId, VerificationTokenType tokenType);
}
