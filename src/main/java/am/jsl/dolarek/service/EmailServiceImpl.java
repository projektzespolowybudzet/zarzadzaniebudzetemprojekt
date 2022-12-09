package am.jsl.dolarek.service;

import am.jsl.dolarek.log.AppLogger;
import am.jsl.dolarek.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

/**
 *Implementacja usługi {@link EmailService}.
 */
@Service
public class EmailServiceImpl implements EmailService {
    private static final AppLogger log = new AppLogger(EmailServiceImpl.class);
    private static final String RESET_PASSWORD_TEMPLATE = "html/reset-password";
    private static final String REGISTRATION_TEMPLATE = "html/registration";

    @Value("${dolarek.mail.fromAddress}")
    private String from;

    @Value("${dolarek.mail.enabled}")
    private boolean mailEnabled;

    @Autowired
    private ResourceBundleMessageSource emailMessageSource;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine emailTemplateEngine;

    public void sendPasswordResetMail(String recipientEmail, String resetPasswordLink, Locale locale)
            throws MessagingException {
        if (!mailEnabled) {
            log.debug("Mailing is disabled");
            return;
        }
        // Przygotuj kontekst thymeleaf
        final Context ctx = new Context(locale);
        ctx.setVariable("resetPasswordLink", resetPasswordLink);

        // Przygotuj wiadomość e-mail
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, Constants.UTF8);
        message.setSubject(emailMessageSource.getMessage("password.reset.subject", new Object[]{}, locale));
        message.setFrom(from);
        message.setTo(recipientEmail);

        // Utwórz treść HTML za pomocą thymeleaf
        final String htmlContent = this.emailTemplateEngine.process(RESET_PASSWORD_TEMPLATE, ctx);
        message.setText(htmlContent, true);

        // Wysyła email
        this.mailSender.send(mimeMessage);
    }

    @Override
    public void sendRegistrationMail(String recipientEmail, String registrationConfirmLink, Locale locale)
            throws MessagingException {
        if (!mailEnabled) {
            log.debug("Mailing is disabled");
            return;
        }

        // Przygotuj kontekst thymeleaf
        final Context ctx = new Context(locale);
        ctx.setVariable("registrationConfirmLink", registrationConfirmLink);

        // Przygotuj wiadomość e-mail
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, Constants.UTF8);
        message.setSubject(emailMessageSource.getMessage("registration.subject", new Object[]{}, locale));
        message.setFrom(from);
        message.setTo(recipientEmail);

        // Utwórz treść HTML za pomocą thymeleaf
        final String htmlContent = this.emailTemplateEngine.process(REGISTRATION_TEMPLATE, ctx);
        message.setText(htmlContent, true);

        // Wysyła email
        this.mailSender.send(mimeMessage);
    }

    @Override
    public void sendEmail(String email, String subject, String emailText) throws MessagingException {
        if (!mailEnabled) {
            log.debug("Mailing is disabled");
            return;
        }

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, Constants.UTF8);
        message.setSubject(subject);
        message.setFrom(from);
        message.setTo(email);
        message.setText(emailText);

        // Wysyła email
        this.mailSender.send(mimeMessage);
    }
}
