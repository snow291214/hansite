package net.skytelecom.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.skytelecom.services.ICustomerService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * Class: DeleteCustomerController
 * @author Alexey Khudyakov
 * ICQ: 164777039
 *
 */
public class DeleteCustomerController implements Controller {

    private ICustomerService customerService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long customerUid = Long.parseLong(request.getParameter("customerUid"));
        if (customerUid == null){
            return null;
        }
        getCustomerService().remove(customerUid);
        return new ModelAndView(new RedirectView("customers.htm"));
    }

    /**
     * @return the customerService
     */
    public ICustomerService getCustomerService() {
        return customerService;
    }

    /**
     * @param customerService the customerService to set
     */
    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }
}
