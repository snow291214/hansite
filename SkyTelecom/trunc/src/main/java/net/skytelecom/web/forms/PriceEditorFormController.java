package net.skytelecom.web.forms;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.skytelecom.dto.PriceDto;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.Price;
import net.skytelecom.entity.User;
import net.skytelecom.services.ICustomerService;
import net.skytelecom.services.IPriceService;
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
 * Current date: 04.07.2010
 */
public class PriceEditorFormController extends SimpleFormController {

    private IPriceService priceService;
    private ICustomerService customerService;
    private IUserService userService;

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) {
        /*
         *  1. I have to get a customer by Uid from the database.
         *  2. I have to get a list of destinations by name.
         *  3. I have to update destination's currencies, activation dates, rates,
         *      QoS's, indicators and routing.
         */

        Long customersPricesUid = Long.parseLong(request.getParameter("customersPricesUid"));
        String destination = request.getParameter("destination");
        String routing = arrayToString(request.getParameterValues("routes"), ",");
        String currency = request.getParameter("currency");
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date activationDate = null;
        try {
            activationDate = df.parse(request.getParameter("activationDate"));
        } catch (ParseException ex) {
            Logger.getLogger(PriceEditorFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            //priceService.save(price);
            if (i % 50 == 0) {
                priceService.batchSave(price, true);
            } else {
                priceService.batchSave(price, false);
            }
            i++;
        }

        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = getUserService().findByUsername(currentUser).get(0);
        List<Customer> customers = customerService.findByUser(user);
        request.setAttribute("customers", customers);
        String uid = request.getParameter("uid");
        PriceDto priceDto = null;
        if (uid != null) {
            Price price = priceService.get(Long.parseLong(uid));
            priceDto = new PriceDto(price);
        } else {
            priceDto = new PriceDto();
        }
        return priceDto;
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

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
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
}
