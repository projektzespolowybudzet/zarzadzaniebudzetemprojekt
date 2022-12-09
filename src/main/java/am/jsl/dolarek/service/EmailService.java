package am.jsl.dolarek.service;

import javax.mail.MessagingException;
import java.util.Locale;

/**
 *Interfejs usługi, który definiuje wszystkie metody wysyłania wiadomości e-mail.
 */
public interface EmailService {
    /**
     *Wysyła wiadomość e-mail dotyczącą resetowania hasła do podanego odbiorcy E-mail z linkiem do resetowania hasła.
     *
     *@param recipientEmail odbiorca e-mail
     *@param resetPasswordLink link resetowania hasła
     *@param locale regionalne ustawienia
     *@throws MessagingException, jeśli wystąpi błąd
     */
    void sendPasswordResetMail(String recipientEmail, String resetPasswordLink, Locale locale)
            throws MessagingException;

    /**
     *Wysyła e-mail potwierdzający rejestrację do podanego odbiorcy E-mail z linkiem potwierdzającym.
     *
     *@param recipientEmail odbiorca e-mail
     *@param RegistrationConfirmLink link potwierdzający rejestrację
     *@param locale regionalne ustawienia
     *@throws MessagingException, jeśli wystąpi błąd
     */
    void sendRegistrationMail(String recipientEmail, String registrationConfirmLink, Locale locale)
            throws MessagingException;

    /**
     *Wysyła email z podanymi parametrami.
     *
     *@param email adres e-mail
     *@param subject temat wiadomości e-mail
     *@param emailText tekst wiadomości e-mail
     *@throws MessagingException, jeśli wystąpi błąd
     */
    void sendEmail(String email, String subject, String emailText)
            throws MessagingException;
}
