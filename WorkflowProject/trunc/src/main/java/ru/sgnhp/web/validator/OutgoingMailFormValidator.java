package ru.sgnhp.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.sgnhp.dto.OutgoingMailDto;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class OutgoingMailFormValidator implements Validator {

    public boolean supports(Class clazz) {
        return clazz.equals(OutgoingMailDto.class);
    }

    public void validate(Object commandClass, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "OutgoingMailDto.description.empty");
    }
}
