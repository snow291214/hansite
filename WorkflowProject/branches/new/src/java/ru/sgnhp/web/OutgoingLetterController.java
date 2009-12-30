package ru.sgnhp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.view.RedirectView;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class OutgoingLetterController extends AbstractWizardFormController {

//    @Override
//    protected int getTargetPage(HttpServletRequest request, Object command, Errors errors, int currentPage) {
//        if (currentPage == 0) {
//            return 1;
//        } else {
//            return 2;
//        }
//    }

    @Override
    protected ModelAndView processCancel(HttpServletRequest req,
            HttpServletResponse resp, Object command,
            BindException errors) throws Exception {
        return new ModelAndView(new RedirectView("index.htm"));
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
