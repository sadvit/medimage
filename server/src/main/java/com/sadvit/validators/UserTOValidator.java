package com.sadvit.validators;

import com.sadvit.to.UserTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * Created by sadvit on 4/22/16.
 */
@Component
public class UserTOValidator implements Validator {

    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public boolean supports(Class<?> clazz) {
        return UserTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserTO userTO = (UserTO) target;
        String username = userTO.getUsername();
        if (username != null && !pattern.matcher(username).matches()) {
            errors.reject("Incorrect user email");
        }
    }

}
