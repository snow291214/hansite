package ru.sgnhp.web.validator;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.sgnhp.dto.WorkflowBeanDto;
import ru.sgnhp.service.IUserManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 * Created on: 04.03.2010
 *
 *****
 */
public class AssignWorkflowFormValidator implements Validator {

    private IWorkflowManagerService workflowManagerService;
    private IUserManagerService userManagerService;

    public boolean supports(Class clazz) {
        return clazz.equals(WorkflowBeanDto.class);
    }

    public void validate(Object commandClass, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "workflow.description.empty");
        WorkflowBeanDto workflowBeanDto = (WorkflowBeanDto) commandClass;
        Long taskBean = workflowManagerService.get(workflowBeanDto.getUid()).getTaskBean().getUid();
        List<String> skipped = new ArrayList<String>();
        for (String uid : workflowBeanDto.getUserUids()) {
            if (workflowManagerService.isTaskAssignedToUser(taskBean, Long.valueOf(uid))) {
                skipped.add(userManagerService.get(Long.valueOf(uid)).getLastName());
            }
        }
        if (skipped.size() > 0) {
            String errorMessage = "Данное задание было уже назначено пользователям: ";
            for (String lastName : skipped) {
                errorMessage += lastName + ", ";
            }
            errorMessage+= ". <br />Нажмите \"Назад\" и снимите галочки этих с фамилий.";
            errors.rejectValue("description", "", errorMessage);
        }
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }
}
