package am.jsl.dolarek.web.controller.user;

import am.jsl.dolarek.domain.event.EventType;
import am.jsl.dolarek.domain.user.User;
import am.jsl.dolarek.domain.user.VerificationTokenType;
import am.jsl.dolarek.dto.user.PasswordResetDTO;
import am.jsl.dolarek.dto.user.UserDTO;
import am.jsl.dolarek.ex.DuplicateEmailException;
import am.jsl.dolarek.ex.DuplicateUserException;
import am.jsl.dolarek.ex.InvalidTokenException;
import am.jsl.dolarek.ex.UserNotFoundException;
import am.jsl.dolarek.service.event.EventLog;
import am.jsl.dolarek.util.TextUtils;
import am.jsl.dolarek.web.controller.BaseController;
import am.jsl.dolarek.web.form.validator.EmailValidator;
import am.jsl.dolarek.web.util.I18n;
import am.jsl.dolarek.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Locale;

/**
 * UserPublicController określa metody funkcjonalności stron publicznych użytkownika
 * takie jak logowanie, rejestr, resetowanie hasła.
 */
@Controller
@RequestMapping(value = "/user-public")
@Lazy
public class UserPublicController extends BaseController implements Serializable {
    /**
     * Szablony publiczne użytkownika
     */
    public static final String REDIRECT_LOGIN_PAGE = "redirect:/login";
    public static final String FORGOT_PASSWORD_PAGE = "user-public/forgot-password";
    public static final String REDIRECT_FORGOT_PASSWORD_PAGE = "redirect:forgot-password";
    public static final String REGISTER_PAGE = "user-public/register";
    public static final String REDIRECT_REGISTER_PAGE = "redirect:register";
    public static final String MESSAGE_PAGE = "user-public/message";
    public static final String RESET_PASSWORD_PAGE = "user-public/reset-password";
    public static final String REDIRECT_MESSAGE_PAGE = "redirect:message";

    private static final String PASSWORD_RESET = "passwordReset";

    @Autowired
    @Qualifier("emailValidator")
    private transient EmailValidator emailValidator;

    /**
     * Wywołane, gdy użytkownik kliknie zapomniałem hasło.
     *
     * @return user-public/forgot-password
     */
    @RequestMapping(value = {"/forgot-password"}, method = RequestMethod.GET)
    public String forgotPasswordPage() {
        return FORGOT_PASSWORD_PAGE;
    }

    /**
     * Wysyła e-mail z resetowaniem hasła do podanego e-maila za pomocą linku do resetowania hasła.
     *
     * @param request       HttpServletRequest
     * @param email         email
     * @param redirectAttrs RedirectAttributes
     * @param locale        Locale
     * @return redirect:message jeśli sukces inaczej redirect:forgot-password
     */
    @RequestMapping(value = {"/sendPasswordResetMail"}, method = RequestMethod.POST)
    public String sendPasswordResetMail(HttpServletRequest request, @RequestParam String email,
                                        RedirectAttributes redirectAttrs, Locale locale) {
        if (!emailValidator.valid(email)) {
            String message = i18n.msg(request, I18n.KEY_ERROR_INVALID_EMAIL);
            redirectAttrs.addFlashAttribute(I18n.ERROR, message);
            return REDIRECT_FORGOT_PASSWORD_PAGE;
        }

        try {
            userService.sendPasswordResetMail(getAppUrl(request), email, locale);

            String message = i18n.msg(request, I18n.KEY_MSG_RESET_PASSWORD_MAIL_SENT);
            redirectAttrs.addFlashAttribute(I18n.MESSAGE, message);
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
        }

        return REDIRECT_MESSAGE_PAGE;
    }

    /**
     * Wywołane, gdy użytkownik kliknie link do hasła z otrzymanego wiadomości e-mail.
     *
     * @param request       HttpServletRequest
     * @param id            id użytkonika
     * @param token         token
     * @param model         Model
     * @param redirectAttrs RedirectAttributes
     * @return user-public/reset-password
     */
    @RequestMapping(value = {"/reset-password"}, method = RequestMethod.GET)
    public String resetPasswordPage(HttpServletRequest request, @RequestParam Long id, @RequestParam String token,
                                    Model model, RedirectAttributes redirectAttrs) {
        try {
            userService.checkToken(id, token, VerificationTokenType.PASSWORD_RESET);

            PasswordResetDTO passwordResetDTO = new PasswordResetDTO();
            passwordResetDTO.setUserId(id);
            passwordResetDTO.setToken(token);
            model.addAttribute(PASSWORD_RESET, passwordResetDTO);
        } catch (InvalidTokenException e) {
            redirectAttrs.addFlashAttribute(I18n.ERROR, i18n.msg(request, e.getMessageCode()));
            return REDIRECT_MESSAGE_PAGE;
        }

        return RESET_PASSWORD_PAGE;
    }

    /**
     * Wywołany, gdy użytkownik kliknie resetowania hasła w celu przesłania nowego hasła.
     *
     * @param request          HttpServletRequest
     * @param passwordResetDTO PasswordResetDTO
     * @param redirectAttrs    RedirectAttributes
     * @return redirect:/login
     */
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public String resetPassword(HttpServletRequest request,
                                @ModelAttribute PasswordResetDTO passwordResetDTO,
                                RedirectAttributes redirectAttrs) {
        try {
            boolean error = false;
            String message = null;
            String newPassword = passwordResetDTO.getNewPassword();
            String rePassword = passwordResetDTO.getReNewPassword();

            if (!TextUtils.hasText(newPassword)
                    || !TextUtils.hasText(rePassword)) {
                message = i18n.msg(request, "error.enter.required.fields");
                error = true;
            } else if (!rePassword.equals(newPassword)) {
                message = i18n.msg(request, "user.passwords_does_not_match");
            }

            if (!error) {
                User user = userService.getUser(passwordResetDTO.getUserId());
                passwordResetDTO.setLogin(user.getLogin());
                userService.resetPassword(passwordResetDTO);
                message = i18n.msg(request, "user.password.change_success.msg");
                redirectAttrs.addFlashAttribute(I18n.MESSAGE, message);

                return REDIRECT_LOGIN_PAGE;
            } else {
                redirectAttrs.addFlashAttribute(I18n.ERROR, message);
                return RESET_PASSWORD_PAGE;
            }

        } catch (InvalidTokenException e) {
            redirectAttrs.addFlashAttribute(I18n.ERROR, i18n.msg(request, e.getMessageCode()));
            return REDIRECT_MESSAGE_PAGE;
        } catch (UserNotFoundException e) {
            log.error(e.getMessage(), e);
        }

        return RESET_PASSWORD_PAGE;
    }

    /**
     * Wywołane, gdy użytkownik kliknie na stronie rejestru.
     *
     * @param model Model
     * @return user-public/register
     */
    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String registerPage(Model model) {
        if (!model.containsAttribute(WebUtils.USER)) {
            model.addAttribute(WebUtils.USER, new UserDTO());
        }
        return REGISTER_PAGE;
    }

    /**
     * Używane, gdy użytkownik przesyła dane rejestracyjne. Wysyła wiadomość e-mail z potwierdzeniem do użytkownika.
     *
     * @param request       HttpServletRequest
     * @param user          UserDTO
     * @param redirectAttrs RedirectAttributes
     * @param locale        Locale
     * @return redirect:message
     * @throws Exception jeżeli wystąpi wyjątek
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(HttpServletRequest request, @Valid @ModelAttribute UserDTO user,
                           RedirectAttributes redirectAttrs, Locale locale) throws Exception {

        String login = user.getLogin();
        String password = user.getPassword();
        String confirmPassword = user.getConfirmPassword();
        String email = user.getEmail();
        boolean error = false;
        String message = null;

        if (TextUtils.isEmpty(login) || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(confirmPassword) || TextUtils.isEmpty(email)) {
            error = true;
            message = i18n.msg(request, I18n.KEY_ERROR_ENTER_REQUIRED_FIELDS);
        } else if (!password.equals(confirmPassword)) {
            error = true;
            message = i18n.msg(request, I18n.KEY_ERROR_PASSWORDS_DONT_MATCH);
        } else if (!emailValidator.valid(email)) {
            error = true;
            message = i18n.msg(request, I18n.KEY_ERROR_INVALID_EMAIL);
        }

        if (error) {
            redirectAttrs.addFlashAttribute(I18n.ERROR, message);
            redirectAttrs.addFlashAttribute(WebUtils.USER, user);
            return REDIRECT_REGISTER_PAGE;
        }

        try {
            User dbUser = user.toUser();
            userService.register(dbUser, locale, getAppUrl(request));

            EventLog.getInstance().write(EventType.CREATE_USER, dbUser.toString(), dbUser.getId());

            message = i18n.msg(request, "user.register.mail.sent");
            redirectAttrs.addFlashAttribute(I18n.MESSAGE, message);
        } catch (DuplicateUserException e) {
            message = i18n.msg(request, I18n.KEY_ERROR_DUPLICATE_LOGIN,
                    new Object[]{user.getLogin()});
            redirectAttrs.addFlashAttribute(I18n.ERROR, message);
            return REDIRECT_REGISTER_PAGE;
        } catch (DuplicateEmailException ex) {
            message = i18n.msg(request, I18n.KEY_ERROR_DUPLICATE_EMAIL,
                    new Object[]{user.getEmail()});
            redirectAttrs.addFlashAttribute(I18n.ERROR, message);
            return REDIRECT_REGISTER_PAGE;
        }

        return REDIRECT_MESSAGE_PAGE;
    }

    /**
     * Nazywany, gdy użytkownik kliknie link potwierdzający z rejestracji, potwierdź e -mail.
     *
     * @param request       HttpServletRequest
     * @param id            id użytkownika
     * @param token         token
     * @param redirectAttrs RedirectAttributes
     * @return redirect:/login
     */
    @RequestMapping(value = {"/confirm-registration"}, method = RequestMethod.GET)
    public String confirmRegistration(HttpServletRequest request, @RequestParam Long id, @RequestParam String token,
                                      RedirectAttributes redirectAttrs) {
        try {
            userService.confirmRegistration(id, token);
            redirectAttrs.addFlashAttribute(I18n.MESSAGE, i18n.msg(request, "user.register.success"));
            return REDIRECT_LOGIN_PAGE;
        } catch (InvalidTokenException e) {
            redirectAttrs.addFlashAttribute(I18n.ERROR, i18n.msg(request, e.getMessageCode()));
        } catch (UserNotFoundException e) {
            String message = i18n.msg(request, I18n.KEY_ERROR_NOT_FOUND,
                    new Object[]{"user id", id});
            redirectAttrs.addFlashAttribute(I18n.ERROR, message);
        }

        return REDIRECT_MESSAGE_PAGE;
    }

    /**
     * Zwraca stronę z wiadomością.
     *
     * @return user-public/message
     */
    @RequestMapping(value = {"/message"}, method = RequestMethod.GET)
    public String message() {
        return MESSAGE_PAGE;
    }
}