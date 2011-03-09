package ru.sgnhp.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.sgnhp.dto.RejectWorkflowDto;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 * Created on: 16.02.2010
 *
 *****
 */
public class RejectWorkflowFormValidator implements Validator {

    public boolean supports(Class clazz) {
        return clazz.equals(RejectWorkflowDto.class);
    }

    public void validate(Object commandClass, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "rejectWorkflow.description.empty");
    }
}
