package net.skytelecom.web.controllers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.entity.Price;
import net.skytelecom.services.ICustomersPricesService;
import net.skytelecom.services.IPriceService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.DateUtils;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 15.08.2010
 */
public class NewPriceUploadController implements Controller {

    private IPriceService priceService;
    private ICustomersPricesService customersPricesService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!(request instanceof MultipartHttpServletRequest)) {
            return new ModelAndView("newPriceUpload");
        }
        if (request.getParameter("customersPricesUid") == null) {
            return new ModelAndView("customers");
        }
        final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        final Map files = multiRequest.getFileMap();
        Price price = null;
        if (files.size() > 0) {
            CustomersPrices customersPrices = customersPricesService.get(Long.parseLong(request.getParameter("customersPricesUid")));
            priceService.deleteByCustomersPrices(customersPrices);
            for (Object file : files.values()) {
                InputStream inputStream = ((MultipartFile) file).getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                int i = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    if (i == 0){
                        i++;
                        continue;
                    }
                    String[] a = line.split(";");
                    price = new Price();
                    price.setCustomersPrices(customersPrices);
                    price.setPhoneCode(a[2].trim());
                    price.setDestination(a[3]);
                    price.setRatePeak(Double.parseDouble(a[5]));
                    price.setRateOffpeak(Double.parseDouble(a[5]));
                    price.setQos(a[4]);
                    //Date date = DateUtils.stringToDate(a[6], "dd.MM.yyyy");
                    price.setActivationDate(null);
                    price.setConnectRateOffpeak(null);
                    price.setConnectRatePeak(null);
                    price.setCurrency(a[7]);
                    price.setFreeOffpeak(0D);
                    price.setFreePeak(0D);
                    price.setInitPeak(1D);
                    price.setInitOffpeak(1D);
                    price.setLastFieldIgnore(Short.parseShort("0"));
                    price.setPriceIndicator("current");
                    price.setQuantPeak(1D);
                    price.setQuantOffpeak(1D);
                    priceService.save(price);
                }
            }
        }
        return new ModelAndView(new RedirectView("customers.htm"));
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
