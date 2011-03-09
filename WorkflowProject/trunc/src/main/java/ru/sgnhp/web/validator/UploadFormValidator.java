package ru.sgnhp.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.sgnhp.domain.TaskBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class UploadFormValidator implements Validator {

    public boolean supports(Class clazz) {
        return clazz.equals(TaskBean.class);
    }

    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "task", "task.save.empty");
        ValidationUtils.rejectIfEmpty(errors, "session", "task.session.empty");
    }
}
