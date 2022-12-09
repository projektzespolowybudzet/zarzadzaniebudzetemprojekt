package am.jsl.dolarek.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import am.jsl.dolarek.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Definiuje metody przekierowywania użytkowników na stronę domową lub administracyjną.
 */
@Controller
public class IndexController {

    /**
     * Mapowanie na stronę główną.
     * Ustawia bieżącą nazwę użytkownika w modelu i zwraca stronę główną.
     * @param model ModelMap
     * @return home page
     */
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        model.addAttribute("user", getCurrentUsername());
        return WebUtils.PAGE_HOME;
    }

    /**
     * Mapowanie strony głównej administratora.
     * @param model ModelMap
     * @return admin home page
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        model.addAttribute("user", getCurrentUsername());
        return WebUtils.PAGE_ADMIN;
    }

    /**
     * Mapowanie dla strony odmowy dostępu.
     * @param model ModelMap
     * @return strona odmowy dostępu
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getCurrentUsername());
        return "accessDenied";
    }

    /**
    * Zaloguj się bieżącym użytkownikiem z kontekstu bezpieczeństwa Spring i przekierowuje na stronę wylogowania.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return strona wylogowania
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    /**
     * Zwraca bieżącą nazwę użytkownika z kontekstu bezpieczeństwa Springs.
     * @return bieżąca nazwa użytkownika
     */
    private String getCurrentUsername() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
