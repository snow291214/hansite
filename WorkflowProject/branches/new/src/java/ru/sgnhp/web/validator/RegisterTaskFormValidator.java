package ru.sgnhp.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.sgnhp.domain.TaskBean;
import ru.sgnhp.service.ITaskManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class RegisterTaskFormValidator implements Validator{
    private ITaskManagerService taskManagerService;

    public ITaskManagerService getTaskManagerService() {
        return taskManagerService;
    }

    public void setTaskManagerService(ITaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }

    public boolean supports(Class clazz) {
        return clazz.equals(TaskBean.class);
    }

    public void validate(Object arg0, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "task.description.empty");
    }
}
