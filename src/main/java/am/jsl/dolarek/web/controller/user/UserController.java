package am.jsl.dolarek.web.controller.user;

import am.jsl.dolarek.domain.event.EventType;
import am.jsl.dolarek.domain.user.Role;
import am.jsl.dolarek.domain.user.User;
import am.jsl.dolarek.dto.user.UserDTO;
import am.jsl.dolarek.ex.DuplicateEmailException;
import am.jsl.dolarek.ex.DuplicateUserException;
import am.jsl.dolarek.ex.UserNotFoundException;
import am.jsl.dolarek.search.ListPaginatedResult;
import am.jsl.dolarek.search.PageWrapper;
import am.jsl.dolarek.search.user.UserSearchQuery;
import am.jsl.dolarek.service.event.EventLog;
import am.jsl.dolarek.util.Constants;
import am.jsl.dolarek.web.controller.BaseController;
import am.jsl.dolarek.web.form.validator.UserValidator;
import am.jsl.dolarek.web.util.I18n;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;

import static am.jsl.dolarek.web.util.WebUtils.ROLES;
import static am.jsl.dolarek.web.util.WebUtils.USER;

/**
 * UserController definiuje metody funkcjonalności stron użytkowników 
 * z części administratora takie jak wyszukiwanie, widok, dodaj, edytuj użytkowników.
 */
@Controller
@RequestMapping(value = "/user")
@PreAuthorize("hasAuthority('ADMIN')")
@Lazy
public class UserController extends BaseController {
    /**
     * Ścieżki szablonu użytkownika
     */
    public static final String REDIRECT_USER_LIST = "redirect:list";
    public static final String FORWARD_USER_LIST = "system/user/user-list";
    public static final String FORWARD_USER_RESULT_LIST = "system/user/user-list-result :: userResultsList";
    public static final String FORWARD_USER_EDIT = "system/user/user-edit";
    public static final String FORWARD_USER_ADD = "system/user/user-add";
    public static final String REDIRECT_USER_ADD = "redirect:/user/add";
    public static final String FORWARD_USER_VIEW = "system/user/user-view";

    @Autowired
    private transient UserValidator userFormValidator;

    public UserController() {
        super();
    }

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
     * Wywołany po otwarciu listy użytkowników.
     *
     * @return user/user-list
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String list() {
        return FORWARD_USER_LIST;
    }

    /**
     * Wywołąny metodą AJAX do ładowania użytkowników ze strony Give.
     *
     * @param model Model
     * @param page  strona
     * @return user/user-list
     */
    @RequestMapping(value = {"/loadUsers"}, method = RequestMethod.GET)
    public String loadUsers(Model model, @RequestParam int page) {
        UserSearchQuery query = new UserSearchQuery(page, Constants.PAGE_SIZE);
        ListPaginatedResult<User> result = userService.search(query);

        PageWrapper<User> pageWrapper = new PageWrapper<>(result, page, Constants.PAGE_SIZE);
        model.addAttribute(Constants.PAGE, pageWrapper);

        return FORWARD_USER_RESULT_LIST;
    }

    /**
     * Wywołane, gdy administrator kliknie link użytkownika.
     *
     * @param request   HttpServletRequest
     * @param id        id użytkownika
     * @param model     Model
     * @return user/user-view
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(HttpServletRequest request, @RequestParam(value = "id", required = true)
            long id, Model model) {
        try {
            if (!model.containsAttribute(USER)) {
                User user = userService.getUser(id);
                model.addAttribute(USER, UserDTO.from(user));
            }
            return FORWARD_USER_VIEW;
        } catch (UserNotFoundException e) {
            i18n.addNotFoundError(request, model, new Object[]{"user id", id});
            return REDIRECT_USER_LIST;
        }
    }

    /**
     * Wywołane, gdy administrator kliknie na dodaj ze strony listy użytkowników.
     *
     * @param model the Model
     * @return user/user-add
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(Model model) {
        if (!model.containsAttribute(USER)) {
            model.addAttribute(USER, new UserDTO());
            model.addAttribute(ROLES, Role.values());
        }
        return FORWARD_USER_ADD;
    }

    /**
     * Wywołane, gdy administrator kliknie Dodaj ze strony dodania użytkownika.
     *
     * @param request       HttpServletRequest
     * @param userDTO       HttpServletRequest
     * @param result        BindingResult
     * @param redirectAttrs RedirectAttributes
     * @return user/user/add
     * @throws Exception if exception occurs
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpServletRequest request, @Valid @ModelAttribute UserDTO userDTO,
                      BindingResult result, RedirectAttributes redirectAttrs) throws Exception {

        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redirectAttrs.addFlashAttribute(USER, userDTO);
            redirectAttrs.addFlashAttribute(ROLES, Role.values());
            return REDIRECT_USER_ADD;
        }

        try {
            User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userDTO.toUser();
            user.setCreatedAt(LocalDateTime.now());
            user.setChangedAt(LocalDateTime.now());
            userService.create(user);

            String message = i18n.msg(request, I18n.KEY_MESSAGE_SUCCESS_ADD,
                    new Object[]{USER, userDTO.getLogin()});
            redirectAttrs.addFlashAttribute(I18n.MESSAGE, message);

            EventLog.getInstance().write(EventType.CREATE_USER, user.toString(), userDetails.getId());
        } catch (DuplicateUserException unf) {
            String message = i18n.msg(request, I18n.KEY_ERROR_DUPLICATE,
                    new Object[]{USER, userDTO.getLogin()});
            redirectAttrs.addFlashAttribute(I18n.ERROR, message);
            return REDIRECT_USER_ADD;
        } catch (DuplicateEmailException ex) {
            String message = i18n.msg(request, I18n.KEY_ERROR_DUPLICATE,
                    new Object[]{USER, userDTO.getEmail()});
            redirectAttrs.addFlashAttribute(I18n.ERROR, message);
            return REDIRECT_USER_ADD;
        }

        return REDIRECT_USER_LIST;
    }

    /**
     * Wywołany, gdy użytkownik kliknie na edytuj ze strony listy użytkowników.
     *
     * @param request HttpServletRequest
     * @param id      id użytkownika
     * @param model   Model
     * @return user/user-edit
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPage(HttpServletRequest request, @RequestParam(value = "id", required = true)
            long id, Model model) {
        try {
            if (!model.containsAttribute(USER)) {
                User user = userService.getUser(id);
                model.addAttribute(USER, UserDTO.from(user));
                model.addAttribute(ROLES, Role.values());
            }
            return FORWARD_USER_EDIT;
        } catch (UserNotFoundException e) {
            i18n.addNotFoundError(request, model, new Object[]{"user id", id});
            return REDIRECT_USER_LIST;
        }
    }

    /**
     * Wywołany, gdy użytkownik kliknie zapisz ze strony użytkownika edytuj.
     *
     * @param request       HttpServletRequest
     * @param userDTO       UserDTO
     * @param result        BindingResult
     * @param redirectAttrs RedirectAttributes
     * @return user/user/edit?id=
     * @throws Exception jeżeli wystąpił wyjątek
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(HttpServletRequest request, @Valid @ModelAttribute UserDTO userDTO,
                       BindingResult result, RedirectAttributes redirectAttrs) throws Exception {
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redirectAttrs.addFlashAttribute(USER, userDTO);
            return "redirect:/user/edit?id=" + userDTO.getId();
        }

        try {
            User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userDTO.toUser();
            user.setChangedAt(LocalDateTime.now());
            user.setChangedBy(userDetails.getId());
            userService.update(user);

            String message = i18n.msg(request, I18n.KEY_MESSAGE_SUCCESS_UPDATE,
                    new Object[]{USER, userDTO.getLogin()});
            redirectAttrs.addFlashAttribute(I18n.MESSAGE, message);

            EventLog.getInstance().write(EventType.UPDATE_USER, user.toString(), userDetails.getId());
        } catch (DuplicateUserException unf) {
            String message = i18n.msg(request, I18n.KEY_ERROR_DUPLICATE,
                    new Object[]{USER, userDTO.getLogin()});
            redirectAttrs.addFlashAttribute(I18n.ERROR, message);
            return "redirect:/user/edit?id=" + userDTO.getId();
        } catch (DuplicateEmailException ex) {
            String message = i18n.msg(request, I18n.KEY_ERROR_DUPLICATE,
                    new Object[]{USER, userDTO.getEmail()});
            redirectAttrs.addFlashAttribute(I18n.ERROR, message);
            return "redirect:/user/edit?id=" + userDTO.getId();
        }

        return REDIRECT_USER_LIST;
    }

    /**
     * Wywołane, gdy administrator kliknie usuń użytkownika.
     *
     * @param request       HttpServletRequest
     * @param id            id użytkownika
     * @param model         Model
     * @param redirectAttrs RedirectAttributes
     * @return przekierowanie redirect:list
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request, @RequestParam(value = "id", required = true) long id,
                         Model model, RedirectAttributes redirectAttrs) {

        try {
            if (id != 0) {
                User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User user = userService.getUser(id);
                userService.deleteUser(user);

                String message = i18n.msg(request, I18n.KEY_MESSAGE_SUCCESS_DELETE,
                        new Object[]{USER, user.getLogin()});
                redirectAttrs.addFlashAttribute(I18n.MESSAGE, message);

                EventLog.getInstance().write(EventType.DELETE_USER, user.toString(), userDetails.getId());
            }
        } catch (UserNotFoundException e) {
            i18n.addNotFoundError(request, model, new Object[]{"user id", id});
        }

        return REDIRECT_USER_LIST;
    }
}