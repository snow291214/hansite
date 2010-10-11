package net.skytelecom.web.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.entity.Price;
import net.skytelecom.entity.User;
import net.skytelecom.services.ICustomerService;
import net.skytelecom.services.ICustomersPricesService;
import net.skytelecom.services.IPriceService;
import net.skytelecom.services.IUserService;
import net.skytelecom.utils.DateUtils;
import net.skytelecom.web.forms.PriceEditorFormController;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 24.07.2010
 */
public class PricesEditorController implements Controller {

    private IPriceService priceService;
    private ICustomerService customerService;
    private ICustomersPricesService customersPricesService;
    private IUserService userService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getParameter("customersPricesUid") == null) {
            final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = getUserService().findByUsername(currentUser).get(0);
            List<Customer> customers = getCustomerService().findByUserAndWithPriceList(user);
            request.setAttribute("pendingDate", DateUtils.increaseDateString("dd.MM.yyyy", 7));
            return new ModelAndView("priceEditor", "customers", customers);
        } else {
            Object action = request.getParameter("action");
            if ((request.getParameter("direct") != null) & (action == null)) {
                Long customersPricesUid = Long.parseLong(request.getParameter("customersPricesUid"));
                request.setAttribute("pendingDate", DateUtils.increaseDateString("dd.MM.yyyy", 7));
                return createMAV(customersPricesUid);
            }
            String routing = null;
            Long customersPricesUid = Long.parseLong(request.getParameter("customersPricesUid"));
            String destination = request.getParameter("destination");
            String[] routes = request.getParameterValues("routes");
            if (routes != null) {
                routing = arrayToString(routes, ",");
            }
            String currency = request.getParameter("currency");
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            Date activationDate = null;
            try {
                activationDate = df.parse(request.getParameter("activationDate"));
            } catch (ParseException ex) {
                Logger.getLogger(PriceEditorFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //double oldRate = Double.parseDouble(request.getParameter("oldRate"));
            double newRate = Double.parseDouble(request.getParameter("newRate"));
            String indicator = request.getParameter("indicator");
            String qos = request.getParameter("qos");
            int i = 0;
            List<Price> prices = priceService.findByDestinationName(destination, customersPricesUid);
            for (Price price : prices) {
                price.setCurrency(currency);
                price.setActivationDate(activationDate);
                price.setRatePeak(newRate);
                price.setRateOffpeak(newRate);
                price.setQos(qos);
                price.setRouting(routing);
                price.setPriceIndicator(indicator);
                if (i % 50 == 0) {
                    getPriceService().batchSave(price, true);
                } else {
                    getPriceService().batchSave(price, false);
                }
                i++;
            }
            request.setAttribute("pendingDate", DateUtils.increaseDateString("dd.MM.yyyy", 7));
            return createMAV(customersPricesUid);
        }
    }

    private String arrayToString(String[] a, String separator) {
        StringBuilder result = new StringBuilder();
        if (a.length > 0) {
            result.append(a[0]);
            for (int i = 1; i < a.length; i++) {
                result.append(separator);
                result.append(a[i]);
            }
        }
        return result.toString();
    }

    private ModelAndView createMAV(Long customersPricesUid) {
        CustomersPrices customersPrices = customersPricesService.get(customersPricesUid);
        ModelAndView mav = new ModelAndView("priceEditor");
        mav.addObject("action", "save");
        mav.addObject("customerUid", customersPrices.getCustomer().getUid());
        mav.addObject("customerName", customersPrices.getCustomer().getCustomerName());
        mav.addObject("customersPricesUid", customersPricesUid);
        mav.addObject("priceType", customersPrices.getPriceType().getName());
        mav.addObject("destinations", priceService.findDistinctDestinations(customersPricesUid));
        return mav;
    }

    public IPriceService getPriceService() {
        return priceService;
    }

    public void setPriceService(IPriceService priceService) {
        this.priceService = priceService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
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
