package am.jsl.dolarek.web.controller.user;

import am.jsl.dolarek.domain.user.User;
import am.jsl.dolarek.dto.user.PasswordChangeDTO;
import am.jsl.dolarek.dto.user.UserDTO;
import am.jsl.dolarek.ex.DuplicateEmailException;
import am.jsl.dolarek.ex.DuplicateUserException;
import am.jsl.dolarek.ex.UserNotFoundException;
import am.jsl.dolarek.util.Constants;
import am.jsl.dolarek.util.GenerateShortUUID;
import am.jsl.dolarek.util.ImageFileFilter;
import am.jsl.dolarek.util.ImageUtils;
import am.jsl.dolarek.util.TextUtils;
import am.jsl.dolarek.web.controller.BaseController;
import am.jsl.dolarek.web.form.validator.UserValidator;
import am.jsl.dolarek.web.util.I18n;
import am.jsl.dolarek.web.util.WebUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static am.jsl.dolarek.web.util.WebUtils.USER;

/**
 * UserProfileController definiuje metody zarządzania profilem użytkownika:
 * przeglądanie/edytowanie danych profilu, zmiana hasła, dołączanie/odłączanie obrazów profilowych.
 */
@Controller
@RequestMapping(value = "/profile")
public class UserProfileController extends BaseController {

    /**
     * Szablony profilu użytkownika
     */
    public static final String FORWARD_PROFILE_VIEW = "profile/profile-view";
    public static final String REDIRECT_PROFILE_VIEW = "redirect:view";
    public static final String FORWARD_PASSWORD_CHANGE = "profile/password-change";
    public static final String REDIRECT_PASSWORD_CHANGE = "redirect:profile/passwordchange?id=";
    public static final String PASSWORD_CHANGE_DTO = "passwordChangeDTO";

    /**
     * Katalog, w którym przesyłane są obrazy użytkowników.
     */
    @Value("${personalfinances.user.img.dir}")
    private String userImgDir;

    @Autowired
    private transient PasswordEncoder passwordEncoder;

    @Autowired
    private transient UserValidator userFormValidator;

    /**
     * Rejestruje walidator użytkownika.
     *
     * @param binder WebDataBinder
     */
    @InitBinder("userDTO")
    public void initUserBinder(WebDataBinder binder) {
        binder.addValidators(userFormValidator);
    }

    /**
     * Nazywany, gdy użytkownik otwiera stronę profilu użytkownika.
     *
     * @param model Model
     * @return profile/profile-view
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(Model model) {
        try {
            if (!model.containsAttribute(PASSWORD_CHANGE_DTO)) {
                User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User user = userService.getUser(userDetails.getId());
                model.addAttribute(USER, UserDTO.from(user));
            }
            return FORWARD_PROFILE_VIEW;
        } catch (UserNotFoundException e) {
            log.error(e.getMessage(), e);
            return WebUtils.PAGE_HOME;
        }
    }

    /**
     * Wywołane, gdy użytkownik kliknął link do zmiany hasła ze strony profilu.
     *
     * @param model Model
     * @return profile/password-change
     */
    @RequestMapping(value = "/passwordchange", method = RequestMethod.GET)
    public String passwordChangePage(Model model) {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PasswordChangeDTO passwordChangeDTO = new PasswordChangeDTO();
        passwordChangeDTO.setId(userDetails.getId());
        model.addAttribute(PASSWORD_CHANGE_DTO, passwordChangeDTO);
        return FORWARD_PASSWORD_CHANGE;
    }

    /**
     * Wywołane, gdy użytkownicy klikną przycisk Zmień ze strony zmiany hasła.
     *
     * @param request           HttpServletRequest
     * @param passwordChangeDTO PasswordChangeDTO
     * @param redirectAttrs     RedirectAttributes
     * @return profile/passwordchange?id=
     */
    @RequestMapping(value = "/passwordchange", method = RequestMethod.POST)
    public String passwordChange(HttpServletRequest request,
                                 @ModelAttribute PasswordChangeDTO passwordChangeDTO,
                                 RedirectAttributes redirectAttrs) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean error = false;
        String message = null;
        String oldPassword = passwordChangeDTO.getOldPassword();
        String newPassword = passwordChangeDTO.getNewPassword();
        String rePassword = passwordChangeDTO.getRePassword();

        if (!TextUtils.hasText(oldPassword)
                || !TextUtils.hasText(newPassword)
                || !TextUtils.hasText(rePassword)) {
            message = i18n.msg(request, "error.enter.required.fields");
            error = true;
        } else {
            if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
                message = i18n.msg(request, "user.old_password_incorrect");
                error = true;
            } else if (!rePassword.equals(newPassword)) {
                message = i18n.msg(request, "user.passwords_does_not_match");
                error = true;
            }
        }

        if (!error) {
            userService.changePassword(newPassword, user.getId());
            message = i18n.msg(request, "user.password.change_success.msg");
            redirectAttrs.addFlashAttribute(I18n.MESSAGE, message);

            return REDIRECT_PROFILE_VIEW;
        } else {
            redirectAttrs.addFlashAttribute(I18n.ERROR, message);
            return REDIRECT_PASSWORD_CHANGE;
        }
    }

    /**
     * Wywołane ze strony profilu użytkownika do dołączania obrazu do profilu użytkownika.
     *
     * @param request       HttpServletRequest
     * @param uploadedFile  MultipartFile
     * @param redirectAttrs RedirectAttributes
     * @return przekierowanie redirect:view
     * @throws IOException gdy nie przesyłano obrazu
     */
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public String uploadImage(HttpServletRequest request, @RequestParam("file") MultipartFile uploadedFile,
                              RedirectAttributes redirectAttrs) throws IOException {

        if (uploadedFile.isEmpty()) {
            return REDIRECT_PROFILE_VIEW;
        }

        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            long userId = user.getId();
            String fileName = uploadedFile.getOriginalFilename();
            String extension = FilenameUtils.getExtension(fileName);

            if (!ImageFileFilter.isValidImageExtension(extension)) {
                String message = i18n.msg(request, "error.select.valid.image");
                redirectAttrs.addFlashAttribute(I18n.ERROR, message);
                return REDIRECT_PROFILE_VIEW;
            }

            String uploadPath = userImgDir + userId;
            File imageUploadDir = new File(uploadPath);

            if (!imageUploadDir.isDirectory()) {
                imageUploadDir.mkdir();
            }

            // scale image if needs
            BufferedImage image = ImageUtils.toBufferedImage(uploadedFile.getBytes());
            int imgWidth = image.getWidth();
            int imgHeight = image.getHeight();

            if (imgWidth > Constants.PROFILE_IMG_WIDTH
                    || imgHeight > Constants.PROFILE_IMG_HEIGHT) {
                image = ImageUtils.resizeImage(image,
                        Constants.PROFILE_IMG_WIDTH,
                        Constants.PROFILE_IMG_HEIGHT, true);
            }

            User dbUser = userService.getUser(user.getId());
            String icon = dbUser.getIcon();
            File imageFile = null;

            // remove old icon
            if (!TextUtils.isEmpty(icon)) {
                imageFile = new File(imageUploadDir, icon);
                imageFile.delete();
            }

            icon = GenerateShortUUID.next() + "." + extension;

            imageFile = new File(imageUploadDir, icon);
            ImageIO.write(image, extension, imageFile);

            user.setIcon(icon);
            userService.updateIcon(user);
        } catch (UserNotFoundException e) {
            log.error(e.getMessage(), e);
        }

        return REDIRECT_PROFILE_VIEW;
    }

    /**
     * Wywołał, gdy użytkownik kliknie usuń przy obrazie.
     *
     * @return przekierowanie redirect:view
     */
    @RequestMapping(value = "/removeImage", method = RequestMethod.POST)
    public String removeImage() {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User dbUser = userService.getUser(user.getId());
            String icon = dbUser.getIcon();

            if (TextUtils.isEmpty(icon)) {
                return REDIRECT_PROFILE_VIEW;
            }

            // remove icon
            String uploadPath = userImgDir + user.getId();
            File imageUploadDir = new File(uploadPath);

            if (imageUploadDir.isDirectory()) {
                File imageFile = new File(imageUploadDir, icon);
                imageFile.delete();
            }

            user.setIcon(null);
            userService.updateIcon(user);
        } catch (UserNotFoundException e) {
            log.error(e.getMessage(), e);
        }

        return REDIRECT_PROFILE_VIEW;
    }

    /**
     * Wywołane, gdy użytkownik kliknie przycisk Zapisz ze strony profilu użytkownika.
     *
     * @param request       HttpServletRequest
     * @param userDTO       UserDTO
     * @param result        BindingResult
     * @param redirectAttrs RedirectAttributes
     * @return przekierowanie redirect:view
     * @throws Exception jeżeli wystąpi wyjątek
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request, @Valid @ModelAttribute UserDTO userDTO,
                       BindingResult result, RedirectAttributes redirectAttrs) throws Exception {
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redirectAttrs.addFlashAttribute(USER, userDTO);
            return REDIRECT_PROFILE_VIEW;
        }

        try {
            User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userDTO.toUser();
            user.setId(userDetails.getId());
            user.setChangedAt(LocalDateTime.now());
            user.setChangedBy(userDetails.getId());
            userService.updateProfile(user);

            String message = i18n.msg(request, I18n.KEY_MESSAGE_SUCCESS_UPDATE,
                    new Object[]{USER, userDTO.getLogin()});
            redirectAttrs.addFlashAttribute(I18n.MESSAGE, message);

        } catch (DuplicateUserException unf) {
            String message = i18n.msg(request, I18n.KEY_ERROR_DUPLICATE,
                    new Object[]{USER, userDTO.getLogin()});
            redirectAttrs.addFlashAttribute(I18n.ERROR, message);
        } catch (DuplicateEmailException ex) {
            String message = i18n.msg(request, I18n.KEY_ERROR_DUPLICATE,
                    new Object[]{USER, userDTO.getEmail()});
            redirectAttrs.addFlashAttribute(I18n.ERROR, message);
        }

        return REDIRECT_PROFILE_VIEW;
    }
}
