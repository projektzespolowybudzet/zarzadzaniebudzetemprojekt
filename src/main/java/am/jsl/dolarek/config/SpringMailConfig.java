package am.jsl.dolarek.config;

import am.jsl.dolarek.util.Constants;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import org.springframework.beans.BeansException;
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

/**
 * Konfiguracja poczty e-mail zarządzanej przez Spring.
 */
@Configuration
@ConfigurationProperties(prefix = "dolarek.mail")
public class SpringMailConfig implements ApplicationContextAware {

  /**
   * Stałą przechowująca ścieżkę do javamail.properties file.
   */
  private static final String JAVA_MAIL_FILE =
    "classpath:mail/javamail.properties";

  /**
   * Prywatna zmienna przechowująca hosta
   */
  private String host;

  /**
   * Prywatna zmienna przechowująca port
   */
  private Integer port;

  /**
   * Prywatna zmienna przechowująca protokół
   */
  private String protocol;

  /**
   * Prywatna zmienna przechowująca nazwe użytkownika
   */
  private String username;

  /**
   * Prywatna zmienna przechowująca hasło
   */
  private String password;

  /**
   * Prywatna zmienna przechowująca applicationContext
   */
  private ApplicationContext applicationContext;

  /**
   * Tworzy {@link JavaMailSenderImpl} i konfiguruje ustawienia poczty e-mail.
   *
   * @return JavaMailSender
   * @throws IOException, jeśli wystąpi błąd
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
    javaMailProperties.load(
      this.applicationContext.getResource(JAVA_MAIL_FILE).getInputStream()
    );
    mailSender.setJavaMailProperties(javaMailProperties);

    return mailSender;
  }

  /**
   * Tworzy {@link ResourceBundleMessageSource} do ładowania wiadomości e-mail z pliku właściwości.
   *
   * @return źródło wiadomości ResourceBundleMessageSource
   */
  @Bean
  public ResourceBundleMessageSource emailMessageSource() {
    final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("mail/mail_messages");
    return messageSource;
  }

  /**
   * Tworzy {@link TemplateEngine} Thymeleaf do generowania e-maili z szablonu HTML.
   *
   * @return TemplateEngine
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
   * Tworzy {@link ITemplateResolver} Thymeleaf do rozwiązywania szablonów wiadomości e-mail.
   *
   * @return ITemplateResolver
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

  /**
   * Sets the application context to use for the application.
   *
   * @param applicationContext - the application context to use.
   */
  @Override
  public void setApplicationContext(
    final ApplicationContext applicationContext
  ) throws BeansException {
    this.applicationContext = applicationContext;
  }

  /**
   * Sets the host of the server.
   *
   * @param host - The host to connect to the server.
   */
  public void setHost(String host) {
    this.host = host;
  }

  /**
   * Sets the port number of the server to connect to.
   *
   * @param port - The port number to connect to the server.
   */
  public void setPort(Integer port) {
    this.port = port;
  }

  /**
   * Sets the protocol of the connection.
   *
   * @param protocol - The protocol to use for the connection.
   */
  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  /**
   * Sets the username of the user.
   *
   * @param username - The username to set.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Sets the password for the user.
   *
   * @param password - The password to use for the authentication.
   */
  public void setPassword(String password) {
    this.password = password;
  }
}
