package am.jsl.dolarek.web.interceptor;

import am.jsl.dolarek.domain.user.User;
import am.jsl.dolarek.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

import static am.jsl.dolarek.util.Constants.USER_HTML_PATH;

/**
 * Rozszerzona Spring {@link HandlerInterceptor}, który zostanie wywołany przed wywołaniem metody obsługi.
 * Sprawdza, czy bieżący identyfikator użytkownika może uzyskać dostęp do folderu statycznego wewnątrz katalogu /userhtml/ zawierającego foldery
 * z identyfikatorami użytkowników.
 */
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
                             Object handler) throws Exception {
        String uri = req.getRequestURI();
        String path = req.getContextPath() + USER_HTML_PATH;

        // pre handle user html file
        if (uri.startsWith(path)) {
            Principal principal = req.getUserPrincipal();

            if (!(principal instanceof UsernamePasswordAuthenticationToken)) {
                return false;
            }
            User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

            if (user != null && user.getId() == 0) {
                user = (User) userService.loadUserByUsername(user.getLogin());
            }

            return  (user != null && uri.startsWith(path + user.getId() + "/"));
        }

        return true;
    }
}