package net.skytelecom.web.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.entity.Price;
import net.skytelecom.services.ICustomersPricesService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 04.07.2010
 */
public class PricesController implements Controller {

    private ICustomersPricesService customersPricesService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String customersPricesUid = request.getParameter("customersPricesUid");
        if (customersPricesUid == null) {
            return null;
        }
        CustomersPrices customersPrices = customersPricesService.get(Long.parseLong(customersPricesUid));
        Collection<Price> prices = customersPrices.getPrices();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("customersPricesUid", customersPricesUid);
        model.put("prices", prices);
        return new ModelAndView("prices", "model", model);
    }

    /**
     * @return the customersPricesService
     */
    public ICustomersPricesService getCustomersPricesService() {
        return customersPricesService;
    }

    /**
     * @param customersPricesService the customersPricesService to set
     */
    public void setCustomersPricesService(ICustomersPricesService customersPricesService) {
        this.customersPricesService = customersPricesService;
    }
}
