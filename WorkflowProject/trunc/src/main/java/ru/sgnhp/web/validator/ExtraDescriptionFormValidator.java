package ru.sgnhp.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.sgnhp.dto.WorkflowBeanDto;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class ExtraDescriptionFormValidator implements Validator{

    public boolean supports(Class clazz) {
        return clazz.equals(WorkflowBeanDto.class);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "workflow.extraDescription.empty");
    }

}
