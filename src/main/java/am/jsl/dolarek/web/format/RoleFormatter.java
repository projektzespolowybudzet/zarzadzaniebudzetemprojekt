package am.jsl.dolarek.web.format;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import am.jsl.dolarek.domain.user.Role;

import java.text.ParseException;
import java.util.Locale;

/**
 * Formatter ról dla obiektów {@link Role}.
 */
@Component
public class RoleFormatter implements Formatter<Role> {
    @Override
    public Role parse(String object, Locale locale) throws ParseException {
        return Role.valueOf(object);
    }

    @Override
    public String print(Role object, Locale locale) {
        return object.name();
    }
}
