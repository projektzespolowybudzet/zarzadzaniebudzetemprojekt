package am.jsl.dolarek.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import am.jsl.dolarek.util.Constants;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

/**
*Konfiguracja poczty e-mail zarządzanej przez Spring.
*/
@Configuration
@ConfigurationProperties(prefix = "dolarek.mail")
public class SpringMailConfig implements ApplicationContextAware {

    private static final String JAVA_MAIL_FILE = "classpath:mail/javamail.properties";

    private String host;
    private Integer port;
    private String protocol;
    private String username;
    private String password;
    private String fromAddress;
    private String contactEmail;

    private ApplicationContext applicationContext;

/**
*Tworzy {@link JavaMailSenderImpl} i konfiguruje ustawienia poczty e-mail.
*@return JavaMailSender
*@throws IOException, jeśli wystąpi błąd
*/
    @Bean
    public JavaMailSender mailSender() throws IOException {

        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setProtocol(protocol);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        //Konfiguracja nadawcy poczty specyficzna dla JavaMail, oparta na javamail.properties
        final Properties javaMailProperties = new Properties();
        javaMailProperties.load(this.applicationContext.getResource(JAVA_MAIL_FILE).getInputStream());
        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }

/**
*Tworzy {@link ResourceBundleMessageSource} do ładowania wiadomości e-mail z pliku właściwości.
*@return źródło wiadomości ResourceBundleMessageSource
*/
    @Bean
    public ResourceBundleMessageSource emailMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("mail/mail_messages");
        return messageSource;
    }

/**
*Tworzy {@link TemplateEngine} Thymeleaf do generowania e-maili z szablonu HTML.
*@return TemplateEngine
*/
    @Bean(name = "emailTemplateEngine")
    public TemplateEngine emailTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        //Źródło wiadomości, internacjonalizacja specyficzna dla e-maili
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
        return templateEngine;
    }

/**
*Tworzy {@link ITemplateResolver} Thymeleaf do rozwiązywania szablonów wiadomości e-mail.
*@return ITemplateResolver
*/
    private ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(2);
        templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
        templateResolver.setPrefix("/mail/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding(Constants.UTF8);
        templateResolver.setCacheable(true);
        return templateResolver;
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
