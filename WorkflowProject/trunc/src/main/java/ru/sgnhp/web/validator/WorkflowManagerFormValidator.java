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
public class WorkflowManagerFormValidator implements Validator {

    public boolean supports(Class clazz) {
        return clazz.equals(WorkflowBeanDto.class);
    }

    public void validate(Object commandClass, Errors errors) {
        WorkflowBeanDto workflowBeanDto = (WorkflowBeanDto) commandClass;
        if(workflowBeanDto.getStateUid() == 5){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "workflowNote", "workflow.workflowNote.empty");
        }
    }
}
