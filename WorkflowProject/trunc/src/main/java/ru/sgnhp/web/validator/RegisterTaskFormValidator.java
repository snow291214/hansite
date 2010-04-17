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

    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "task.description.empty");
//        TaskBean taskBean = (TaskBean)object;
//        if(!taskBean.getPrimaveraUid().equals("")){
//            Pattern pattern = Pattern.compile("(^\\d{1,3}-\\d{1,2}-[A-z А-я]{2}\\d{4})|(^\\d{1,3}-\\d{1,2}-\\d{1,4})");
//            Matcher matcher = pattern.matcher(taskBean.getPrimaveraUid());
//            if(!matcher.find()){
//                errors.rejectValue("primaveraUid", "task.primaveraID.templateMismatch", "");
//            }
//        }
    }
}
