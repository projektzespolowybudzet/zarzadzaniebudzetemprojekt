package am.jsl.personalfinances.config;

import am.jsl.personalfinances.util.Constants;
import am.jsl.personalfinances.web.converter.StringToLocalDateTimeConverter;
import am.jsl.personalfinances.web.dialect.PersonalFinancesDialect;
import am.jsl.personalfinances.web.format.RoleFormatter;
import am.jsl.personalfinances.web.interceptor.UserInterceptor;
import am.jsl.personalfinances.web.util.I18n;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.resource.*;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
*Spring zarządzana konfiguracja Spring MVC.
*/
@Configuration
@EnableWebMvc
public class SpringWebConfig implements WebMvcConfigurer {

    @Value("${personalfinances.user.img.dir}")
    private String userImgDir;
    @Value("${personalfinances.user.html.dir}")
    private String userHtmlDir;
    @Autowired
    private RoleFormatter roleFormatter;
    
    public SpringWebConfig() {
        super();
    }

/**
*Tworzy {@link StandardServletMultipartResolver}
*@return StandardServletMultipartResolver
*/
    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

/**
*Odwzorowuje kontrolery widoku na ścieżki URL.
*@param rejestruj ViewControllerRegistry
*/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("user-public/login");
        registry.addViewController("/login").setViewName("user-public/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

/**
*Włącza przekazywanie do 'domyślnego' Serwlet.
*@param konfigurator DefaultServletHandlerConfigurer
*/
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable("personalfinancesDefaultServlet");
    }

/**
*Tworzy {@link Java8TimeDialect} do formatowania obiektów Java 8 Time na stronach thymleaf.
*@return Java8TimeDialect
*/
    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

/**
*Tworzy {@link SpringSecurityDialect} do używania wiosennych tagów bezpieczeństwa na stronach thymeleaf.
*@return SpringSecurityDialect
*/
    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

/**
*Tworzy niestandardowe {@link PersonalFinancesDialect} używane na stronach thymeleaf.
*@return Dialekt PersonalFinances
*/
    @Bean
    public PersonalFinancesDialect personalFinancesDialect() {
        return new PersonalFinancesDialect();
    }

/**
*Tworzy {@link ResourceBundleMessageSource} w celu uzyskania dostępu do pakietów zasobów przy użyciu określonych nazw podstawowych.
*@return źródło wiadomości ResourceBundleMessageSource
*/
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("i18n/messages");
        source.setDefaultEncoding(Constants.UTF8);
        return source;
    }

/**
*Tworzy instancję {@link I18n} opakowaną w Spring MessagesSource.
*@return I18n
*/
    @Bean
    public I18n i18n() {
        I18n i18n = new I18n();
        i18n.setMessageSource(messageSource());
        return i18n;
    }

/**
*Tworzy nowy {@link StringToLocalDateTimeConverter}.
*@return StringToLocalDateTimeConverter
*/
    @Bean
    public StringToLocalDateTimeConverter stringToLocalDateTimeConverter(){
        return new StringToLocalDateTimeConverter();
    }

/**
*Konfiguracja dyspozytora do obsługi zasobów statycznych.
*@param zarejestruj plik ResourceHandlerRegistry
*/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/img/**",
                "/css/**",
                "/js/**",
                "/font-awesome/**",
                "/fonts/**",
                "/html/**")
                .addResourceLocations(
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/",
                        "classpath:/static/font-awesome/",
                        "classpath:/static/fonts/",
                        "classpath:/static/html/").setCachePeriod(3600).resourceChain(true).
                addResolver(new GzipResourceResolver()).addResolver(new PathResourceResolver());

        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/", "classpath:/other-resources/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
                .resourceChain(false)
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"))
                .addTransformer(new CssLinkResourceTransformer());

        registry.addResourceHandler(Constants.USER_IMG_PATHPATTERN).addResourceLocations("file:" + userImgDir);
        registry.addResourceHandler(Constants.USER_HTML_PATHPATTERN).addResourceLocations("file:" + userHtmlDir);
    }

/**
*Tworzy instancję {@link User Interceptor}.
*@return przechwytywacz użytkowników
*/
    @Bean
    public UserInterceptor userInterceptor() {
        UserInterceptor userInterceptor = new UserInterceptor();
        return userInterceptor;
    }

/**
*Rejestruje UserInterceptor do wstępnej obsługi żądań dla katalogu userhtml.
*@param zarejestruj InterceptorRegistry
*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //Zarejestruj przechwytywacz użytkowników dla ścieżki userhtml
        registry.addInterceptor(userInterceptor()).addPathPatterns(Constants.USER_HTML_PATHPATTERN);
    }

/**
*Tworzy instancję {@link ResourceUrlEncodingFilter}.
*@return filtr ResourceUrlEncodingFilter
*/
    @Bean
    public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
        ResourceUrlEncodingFilter filter = new ResourceUrlEncodingFilter();
        return filter;
    }

/**
*Tworzy instancję {@link LocaleChangeInterceptor}.
*@return LocaleChangeInterceptor
*/
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName(Constants.LANGUAGE);
        return localeChangeInterceptor;
    }

/**
*Tworzy instancję {@link StringHttpMessageConverter}.
*@return StringHttpMessageConverter
*/
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        return new StringHttpMessageConverter(Charset.forName(Constants.UTF8));
    }

/**
*Rejestruje FormatterRegistry.
*@param registry plik FormatterRegistry
*/
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(roleFormatter);
    }
}
