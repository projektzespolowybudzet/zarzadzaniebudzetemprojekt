package am.jsl.dolarek.dao.user;

import am.jsl.dolarek.dao.BaseDao;
import am.jsl.dolarek.domain.user.User;
import am.jsl.dolarek.domain.user.VerificationToken;
import am.jsl.dolarek.domain.user.VerificationTokenType;
import am.jsl.dolarek.ex.UserNotFoundException;
import am.jsl.dolarek.search.ListPaginatedResult;
import am.jsl.dolarek.search.user.UserSearchQuery;

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
*Ustawia pole last_login z aktualną datą dla podanego identyfikatora użytkownika.
*@param userId identyfikator użytkownika
*/
    void login(long userId);

/**
*Usuwa użytkownika o podanym identyfikatorze użytkownika
*@param id identyfikator użytkownika
*/
    void deleteUser(long id);

/**
*Sprawdza, czy istnieje użytkownik o podanym loginie i id.
*@param login login
*@param id identyfikator użytkownika
*@return true, jeśli istnieje login użytkownika
*/
    boolean loginExists(String login, long id);

/**
*Sprawdza, czy istnieje użytkownik o podanym adresie e-mail i identyfikatorze.
*@param email e-mail
*@param id identyfikator użytkownika
*@return true, jeśli istnieje adres e-mail użytkownika
*/
    boolean emailExists(String email, long id);

/**
*Tworzy użytkownika z podanym użytkownikiem.
*@param user użytkownik
*/
    void create(User user);

/**
*Aktualizuje użytkownika o podanego użytkownika.
*@param user użytkownik
*/
    void update(User user);

/**
*Zwraca użytkownika o podanym id.
*Wyrzuci {@link UserNotFoundException}, jeśli użytkownik nie zostanie znaleziony.
*@param userId identyfikator użytkownika
*@return użytkownik
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
*@param username użytkownika nazwa użytkownika
*@return użytkownik
*@throws UserNotFoundException, jeśli użytkownik nie został znaleziony
*/
    User getUser(String username) throws UserNotFoundException;

/**
*Aktualizuje ikonę dla danego użytkownika.
*@param user użytkownik
*/
    void updateIcon(User user);

/**
*Aktualizuje profil danego użytkownika.
*@param user użytkownik
*/
    void updateProfile(User user) throws Exception;

/**
*Zwraca użytkownika z podanym e-mailem.
*@param email e-mail
*@return użytkownik
*/
    User getUserByEmail(String email);

/**
*Tworzy podany {@link VerificationToken}
*@param verificationToken tokenu weryfikacyjnego
*/
    void createVerificationToken(VerificationToken verificationToken);

/**
*Aktualizuje podany {@link VerificationToken}
*@param verificationToken tokenu weryfikacyjnego
*/
    void updateVerificationToken(VerificationToken verificationToken);

/**
*Zwraca VerificationToken z identyfikatorem użytkownika i tokenType.
*@param userId identyfikator użytkownika
*@param tokenType Wpisz typ tokena
*@return token weryfikacyjny
*/
    VerificationToken getToken(long userId, VerificationTokenType tokenType);
}
