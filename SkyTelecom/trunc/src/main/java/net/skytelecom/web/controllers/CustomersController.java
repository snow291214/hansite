package net.skytelecom.web.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.User;
import net.skytelecom.services.ICustomerService;
import net.skytelecom.services.IUserService;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 28.06.2010
 */
public class CustomersController implements Controller {

    private ICustomerService customerService;
    private IUserService userService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = getUserService().findByUsername(currentUser).get(0);
        List<Customer> customers = customerService.findByUser(user);
        return new ModelAndView("customers", "customers", customers);
    }

    public ICustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
