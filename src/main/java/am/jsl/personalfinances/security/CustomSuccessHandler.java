package am.jsl.personalfinances.security;

import am.jsl.personalfinances.domain.user.Role;
import am.jsl.personalfinances.log.AppLogger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
*Niestandardowy {@link SimpleUrlAuthenticationSuccessHandler}, który przekierowuje do
*Adres URL specyficzny dla roli użytkownika, do którego użytkownicy powinni zostać wysłani po pomyślnym uwierzytelnieniu.
*/
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private static AppLogger logger = new AppLogger(AccessDeniedExceptionHandler.class);

    /**
     * The RedirectStrategy
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            logger.error("Can't redirect");
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

/**
*Określa adres URL dla danego uwierzytelnienia.
*Adres URL będzie miał postać „/home” dla ról UŻYTKOWNIKA, „/admin” dla ról ADMINISTRATORA,
*w przeciwnym razie „/odmowa dostępu”.
*@param authentication uwierzytelnianie
*@return pomyślny adres URL
*/
    protected String determineTargetUrl(Authentication authentication) {
        String url = "";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        if (isUser(roles)) {
            url = "/home";
        } else if (isAdmin(roles)) {
            url = "/admin";
        } else {
            url = "/accessDenied";
        }

        return url;
    }

/**
*Sprawdza, czy dana lista zawiera rolę UŻYTKOWNIKA.
*@param roles na liście ról
*@return true, jeśli role zawierają rolę UŻYTKOWNIKA
*/
    private boolean isUser(List<String> roles) {
        if (roles.contains(Role.USER.name())) {
            return true;
        }
        return false;
    }

/**
*Sprawdza, czy dana lista zawiera rolę ADMIN.
*@param roles na liście ról
*@return true, jeśli role zawierają rolę ADMIN
*/
    private boolean isAdmin(List<String> roles) {
        if (roles.contains(Role.ADMIN.name())) {
            return true;
        }
        return false;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}