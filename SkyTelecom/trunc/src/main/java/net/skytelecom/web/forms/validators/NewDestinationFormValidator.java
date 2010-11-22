package net.skytelecom.web.forms.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.skytelecom.dto.PriceDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * Class: NewDestinationFormValidator
 * @author Alexey Khudyakov
 * ICQ: 164777039
 *
 */
public class NewDestinationFormValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return clazz.equals(PriceDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "destination", "newDestinationValidator.destination.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneCode", "newDestinationValidator.phoneCode.empty");
        Pattern p = Pattern.compile("[a-zA-Z;.]");
        Matcher m = p.matcher(((PriceDto) target).getPhoneCode());
        if (m.find()) {
            errors.rejectValue("phoneCode", "newDestinationValidator.phoneCode.incorrectSymbols");
        }
        if (!(((PriceDto)target).getRatePeak() instanceof Double)){
            errors.rejectValue("ratePeak", "newDestinationValidator.rate.incorrectSymbols");
        }
    }
}
