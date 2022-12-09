package am.jsl.personalfinances.config;

import am.jsl.personalfinances.domain.user.Role;
import am.jsl.personalfinances.security.AccessDeniedExceptionHandler;
import am.jsl.personalfinances.security.CustomSuccessHandler;
import am.jsl.personalfinances.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

import static am.jsl.personalfinances.web.util.WebUtils.TARGET_URL;

/**
*Konfiguracja zabezpieczeń Spring.
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

/**
*CustomSuccessHandler.
*/
    @Autowired
    private CustomSuccessHandler customSuccessHandler;

/**
*AccessDeniedExceptionHandler.
*/
    @Autowired
    private AccessDeniedExceptionHandler accessDeniedExceptionHandler;

    public SpringSecurityConfig() {
        super();
    }

/**
*Tworzy {@link PasswordEncoder} wspierany przez {@link BCryptPasswordEncoder}.
*
*@return koder hasła
*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

/**
*Konfiguruje {@link UserDetailsService} z passwordEncoder.
*
*@param userDetailsService usługę UserDetails
*@param uwierzytelnia AuthenticationManagerBuilder
*@throws Exception, jeśli wystąpi wyjątek
*/
    @Autowired
    public void configureGlobal(UserDetailsService userDetailsService, AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

/**
*Konfiguruje {@link WebSecurity} do ignorowania zasobów statycznych.
*
*@param web WebSecurity
*@throws Exception, jeśli wystąpi wyjątek
*/
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/font-awesome/**", "/fonts/**", "/img/**", "/js/**");
    }

/**
*Konfiguruje {@link HttpSecurity}.
*
*@param http HttpSecurity
*@throws Exception, jeśli wystąpi wyjątek
*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding(Constants.UTF8);
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        http.authorizeRequests()
                .antMatchers("/", "/static/**", "/css/**", "/js/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/error/**").permitAll()
                .antMatchers("/user-public/**").permitAll()
                .antMatchers("/user/**").hasAuthority(Role.ADMIN.name())
                .antMatchers("/event/**").hasAuthority(Role.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(customSuccessHandler)
                .permitAll()
                .and()
                .logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").and()
                .csrf()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedExceptionHandler);
    }

/**
*Tworzy {@link SavedRequestAwareAuthenticationSuccessHandler}.
*
*@return SavedRequestAwareAuthenticationSuccessHandler
*/
    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler
    savedRequestAwareAuthenticationSuccessHandler() {

        SavedRequestAwareAuthenticationSuccessHandler auth
                = new SavedRequestAwareAuthenticationSuccessHandler();
        auth.setTargetUrlParameter(TARGET_URL);
        return auth;
    }

/**
*Tworzy {@link AccessDeniedExceptionHandler}.
*@return AccessDeniedExceptionHandler
*/
    @Bean
    AccessDeniedExceptionHandler accessDeniedExceptionHandler() {
        AccessDeniedExceptionHandler accessDeniedExceptionHandler = new AccessDeniedExceptionHandler();
        accessDeniedExceptionHandler.setErrorPage("/error/accessDeniedPage");
        return accessDeniedExceptionHandler;
    }
}