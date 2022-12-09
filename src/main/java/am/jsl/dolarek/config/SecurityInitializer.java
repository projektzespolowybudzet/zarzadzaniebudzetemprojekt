package am.jsl.dolarek.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
*Rejestruje springSecurityFilterChain przed jakimkolwiek innym zarejestrowanym {@link javax.servlet.Filter}.
*/
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}