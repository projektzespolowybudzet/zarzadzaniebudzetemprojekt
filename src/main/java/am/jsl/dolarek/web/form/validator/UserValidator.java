package am.jsl.dolarek.web.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import am.jsl.dolarek.dto.user.UserDTO;
import am.jsl.dolarek.util.TextUtils;

/**
 * Spring managed class for validating user fields such as login, email, passwords.
 */
@Component
public class UserValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO user = (UserDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "error.field.is.required", new Object[]{"Login"});
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.field.is.required", new Object[]{"Email"});

        if (user.getId() == 0) { // validate passwords
            boolean passwordsEntered = true;
            String password = user.getPassword();
            String confirmPassword = user.getConfirmPassword();

            if (TextUtils.isEmpty(password)) {
                passwordsEntered = false;
                errors.rejectValue("password", "error.field.is.required", new Object[]{"Password"}, null);
            }

            if (TextUtils.isEmpty(confirmPassword)) {
                passwordsEntered = false;
                errors.rejectValue("confirmPassword", "error.field.is.required", new Object[]{"ConfirmPassword"}, null);
            }

            if (passwordsEntered && !user.getPassword().equals(user.getConfirmPassword())) {
                errors.rejectValue("password", "user.password.doesnt.match.confirm.password");
            }
        }
    }

}