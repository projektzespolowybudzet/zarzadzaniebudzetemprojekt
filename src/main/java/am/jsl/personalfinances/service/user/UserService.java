package am.jsl.personalfinances.service.user;

import am.jsl.personalfinances.domain.user.User;
import am.jsl.personalfinances.domain.user.VerificationToken;
import am.jsl.personalfinances.domain.user.VerificationTokenType;
import am.jsl.personalfinances.dto.user.PasswordResetDTO;
import am.jsl.personalfinances.ex.InvalidTokenException;
import am.jsl.personalfinances.ex.UserNotFoundException;
import am.jsl.personalfinances.search.ListPaginatedResult;
import am.jsl.personalfinances.search.user.UserSearchQuery;
import am.jsl.personalfinances.service.BaseService;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.mail.MessagingException;
import java.util.Locale;

/**
 *Interfejs usługi, który definiuje wszystkie metody pracy z obiektem domeny {@link User}.
 */
public interface UserService extends UserDetailsService, BaseService<User> {

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
	 *Aktualizuje profil danego użytkownika.
	 *@param user użytkownik
	 *@throws Exception zostanie zgłoszony, jeśli wystąpi wyjątek
	 */
	void updateProfile(User user) throws Exception;

    /**
     *Zwraca użytkownika o podanym id.
     *Wyrzuci {@link UserNotFoundException}, jeśli użytkownik nie zostanie znaleziony.
     *@param userId identyfikator użytkownika
     *@return użytkownika
     *@throws UserNotFoundException, jeśli użytkownik nie został znaleziony
     */
	User getUser(long userId) throws UserNotFoundException;

    /**
     *Zwraca użytkownika o podanej nazwie użytkownika.
     *Wyrzuci {@link UserNotFoundException}, jeśli użytkownik nie zostanie znaleziony.
     *@param name nazwa użytkownika
     *@return użytkownika
     *@throws UserNotFoundException, jeśli użytkownik nie został znaleziony
     */
	User getUser(String name) throws UserNotFoundException;

    /**
	 *Szyfruje podane hasło i aktualizuje użytkownika.
     *Zmienia hasło użytkownika z podanym encryptedPassword.
     *@param newPassword nowe hasło
     *@param id identyfikator użytkownika
     */
	void changePassword(String newPassword, long id);

	/**
	 *Aktualizuje ikonę dla danego użytkownika.
	 *@param user użytkownik
	 */
    void updateIcon(User user);

	/**
	 *Usuwa użytkownika o podanym identyfikatorze użytkownika
	 *@param user użytkownik
	 */
	void deleteUser(User user);

	/**
	*Generuje i wysyła wiadomość e-mail dotyczącą resetowania hasła na podany adres e-mail i lokalizację.
	*@param contextPath ścieżka kontekstu aplikacji
	*@param email Adres e-mail 
	*@param locale ustawienia regionalne
	*@throws MessagingException, jeśli wystąpi błąd
	*/
    void sendPasswordResetMail(String contextPath, String email, Locale locale)
			throws MessagingException;

	/**
	 *Sprawdź, czy podany token jest ważny dla danego użytkownika
	 *i zwraca obiekt VerificationToken.
	 *Wyrzuci wyjątek InvalidTokenException, jeśli token jest nieprawidłowy.
	 *@param userId identyfikator użytkownika
	 *@param token token
	 *@param tokenType typ tokena
	 *@return obiekt VerificationToken
	 *@throws InvalidTokenException, jeśli token jest nieprawidłowy.
	 */
	VerificationToken checkToken(long userId, String token, VerificationTokenType tokenType)
			throws InvalidTokenException;

	/**
	 *Zwraca VerificationToken z identyfikatorem użytkownika i tokenType.
	 *@param userId identyfikator użytkownika
	 *@param token Wpisz typ tokena
	 *@return token weryfikacyjny
	 */
	VerificationToken getToken(long userId, VerificationTokenType tokenType);
	
	/**
	 *Resetuje hasło użytkownika na podstawie obiektu {@link PasswordResetDTO}.
	 *@param passwordResetDTO dla PasswordResetDTO
	 *@throws InvalidTokenException, jeśli token jest nieprawidłowy
	 */
    void resetPassword(PasswordResetDTO passwordResetDTO) throws InvalidTokenException;

	/**
	 *Tworzy użytkownika ze statusem nieaktywnym i wysyła potwierdzenie rejestracji z linkiem aktywacyjnym.
	 *@param user użytkownik
	 *@param local ustawienia regionalne
	 *@param contextPath ścieżka kontekstu aplikacji
	 *@throws Exception jeśli wystąpi błąd
	 */
    void register(User user, Locale locale, String contextPath) throws Exception;

	/**
	 *Potwierdza rejestrację użytkownika. Jeśli token rejestracji jest ważny, ustawia status danego użytkownika na aktywny
	 *i wygasa token weryfikacyjny.
	 *Wyrzuci {@link InvalidTokenException}, jeśli token jest nieprawidłowy lub wygasł
	 *i {@link UserNotFoundException}, jeśli nie znaleziono użytkownika.
	 *@param userId identyfikator użytkownika
	 *@param token token
	 *@throws InvalidTokenException, jeśli token jest nieprawidłowy lub wygasł
	 *@throws UserNotFoundException, jeśli użytkownik nie został znaleziony
	 */
    void confirmRegistration(Long userId, String token) throws InvalidTokenException, UserNotFoundException;
}
