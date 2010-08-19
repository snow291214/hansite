package net.skytelecom.web.forms;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.skytelecom.dto.CustomerDto;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.User;
import net.skytelecom.services.ICustomerService;
import net.skytelecom.services.IUserService;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 28.06.2010
 */
public class CustomerEditorFormController extends SimpleFormController {

    private ICustomerService customerService;
    private IUserService userService;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        String uid = request.getParameter("customerUid");
        CustomerDto customerDto = null;
        if(uid != null){
            Customer customer = customerService.get(Long.parseLong(uid));
            customerDto = new CustomerDto(customer);
        }else{
            customerDto = new CustomerDto();
        }
        return customerDto;
    }

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) {
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = getUserService().findByUsername(currentUser).get(0);

        Customer customer = new Customer();
        CustomerDto customerDto = (CustomerDto)command;
        customer.setUid(customerDto.getUid());
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setCurrency(customerDto.getCurrency());
        customer.setContactPerson(customerDto.getContactPerson());
        customer.setEmail(customerDto.getEmail());
        customer.setHiddenEmail(customerDto.getHiddenEmail());
        customer.setEmailSubject(customerDto.getEmailSubject());
        customer.setBlockedDestinations(customerDto.getBlockedDestinations());
        customer.setLatestReport(customerDto.getLatestReport());
        customer.setSender(customerDto.getSender());
        customer.setUser(user);

        this.getCustomerService().save(customer);

        return new ModelAndView(new RedirectView(getSuccessView()));
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
