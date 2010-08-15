package net.skytelecom.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.Price;
import net.skytelecom.services.ICustomerService;
import net.skytelecom.services.IPriceService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 04.07.2010
 */
public class PricesController implements Controller {

    private ICustomerService customerService;
    private IPriceService priceService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String customerUid = request.getParameter("customerUid");
        if (customerUid == null) {
            return null;
        }
        Customer customer = customerService.get(Long.parseLong(customerUid));
        List<Price> prices = priceService.findByCustomer(customer);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("customerUid", customerUid);
        model.put("prices", prices);
        return new ModelAndView("prices", "model", model);
    }

    public IPriceService getPriceService() {
        return priceService;
    }

    public void setPriceService(IPriceService priceService) {
        this.priceService = priceService;
    }

    public ICustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }
}
