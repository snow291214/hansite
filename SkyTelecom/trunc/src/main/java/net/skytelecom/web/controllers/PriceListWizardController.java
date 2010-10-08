package net.skytelecom.web.controllers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.entity.Price;
import net.skytelecom.services.ICustomerService;
import net.skytelecom.services.ICustomersPricesService;
import net.skytelecom.services.IPriceService;
import net.skytelecom.services.IPriceTypeService;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 18.08.2010
 */
public class PriceListWizardController implements Controller {

    private ICustomerService customerService;
    private IPriceTypeService priceTypeService;
    private IPriceService priceService;
    private ICustomersPricesService customersPricesService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String customerUid = request.getParameter("customerUid");
        if (customerUid == null) {
            return new ModelAndView(new RedirectView("customers.htm"));
        }
        if (request.getParameter("priceTypeUid") == null) {
            request.setAttribute("priceTypes", priceTypeService.getAll());
        } else {
            Logger logger = Logger.getLogger("");
            logger.warn(new Date());
            String priceTypeUid = request.getParameter("priceTypeUid");
            final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            final Map files = multiRequest.getFileMap();
            //Price price = null;
            if (files.size() > 0) {
                CustomersPrices customersPrices = new CustomersPrices();
                customersPrices.setCustomer(customerService.get(Long.parseLong(customerUid)));
                customersPrices.setPriceType(priceTypeService.get(Long.parseLong(priceTypeUid)));
                customersPrices = customersPricesService.save(customersPrices);
                getPriceService().deleteByCustomersPrices(customersPrices);
                for (Object file : files.values()) {
                    InputStream inputStream = ((MultipartFile) file).getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    String[] a;
                    List<Price> prices = new ArrayList<Price>();
                    while ((line = bufferedReader.readLine()) != null) {
                        a = line.split(";");
                        if (a.length > 0) {
                            if (!this.isNumeric(a[2])) {
                                continue;
                            }
                            prices.add(priceService.fillingPricePropertiesFromCsvLine(a, customersPrices));
                        }
                    }
                    getPriceService().batchSaveEx(prices);
                }
                ModelAndView mav = new ModelAndView(new RedirectView("customers.htm"));
                mav.addObject("result", "done");
                return mav;
            }
        }
        return new ModelAndView("priceListWizard");
    }

    private boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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

    /**
     * @return the priceTypeService
     */
    public IPriceTypeService getPriceTypeService() {
        return priceTypeService;
    }

    /**
     * @param priceTypeService the priceTypeService to set
     */
    public void setPriceTypeService(IPriceTypeService priceTypeService) {
        this.priceTypeService = priceTypeService;
    }

    /**
     * @return the priceService
     */
    public IPriceService getPriceService() {
        return priceService;
    }

    /**
     * @param priceService the priceService to set
     */
    public void setPriceService(IPriceService priceService) {
        this.priceService = priceService;
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
