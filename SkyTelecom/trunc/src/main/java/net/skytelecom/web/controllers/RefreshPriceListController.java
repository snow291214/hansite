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
import net.skytelecom.services.ICustomersPricesService;
import net.skytelecom.services.IPriceService;
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
 * Current date: 15.08.2010
 */
public class RefreshPriceListController implements Controller {

    private IPriceService priceService;
    private ICustomersPricesService customersPricesService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!(request instanceof MultipartHttpServletRequest)) {
            return new ModelAndView("refreshPriceList");
        }
        if (request.getParameter("customersPricesUid") == null) {
            return new ModelAndView("customers");
        }
        Logger logger = Logger.getLogger("Prices");
        final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        final Map files = multiRequest.getFileMap();
        if (files.size() > 0) {
            CustomersPrices customersPrices = customersPricesService.get(Long.parseLong(request.getParameter("customersPricesUid")));
            logger.warn(priceService.deleteByCustomersPrices(customersPrices) + " records were deleted");
            for (Object file : files.values()) {
                InputStream inputStream = ((MultipartFile) file).getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
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
        }
        logger.warn(new Date());
        return new ModelAndView(new RedirectView("customers.htm"));
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
}
