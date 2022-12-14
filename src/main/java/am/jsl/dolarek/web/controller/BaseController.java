package am.jsl.dolarek.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import am.jsl.dolarek.domain.user.User;
import am.jsl.dolarek.log.AppLogger;
import am.jsl.dolarek.service.user.UserService;
import am.jsl.dolarek.web.util.I18n;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * The base class for all controllers.
 * Contains common fields and methods.
 */
public class BaseController implements Serializable {
    protected static final AppLogger log = new AppLogger(BaseController.class);

    /**
     * The user service
     */
    @Autowired
    protected transient UserService userService;

    /**
     * The internationalization message wrapper.
     * @see I18n
     */
    @Autowired
    protected I18n i18n;

    /**
     * Returns the current user from Spring Security Context.
     * @return the User
     */
    protected User getUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user != null && user.getId() == 0) {
            user = (User) userService.loadUserByUsername(user.getLogin());
        }
        return user;
    }

    /**
     * Extracts and returns application url from request.
     * @param request the HttpServletRequest
     * @return the application url
     */
    protected String getAppUrl(HttpServletRequest request) {
        return "https://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}
