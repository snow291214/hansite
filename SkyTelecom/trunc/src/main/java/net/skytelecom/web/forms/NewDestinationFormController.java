package net.skytelecom.web.forms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.skytelecom.dto.PriceDto;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.entity.Price;
import net.skytelecom.services.ICustomersPricesService;
import net.skytelecom.services.IPriceService;
import net.skytelecom.utils.DateUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * Class: NewPriceFormController
 * @author Alexey Khudyakov
 * ICQ: 164777039
 *
 */
public class NewDestinationFormController extends SimpleFormController {

    private IPriceService priceService;
    private ICustomersPricesService customersPricesService;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        Long customersPricesUid = Long.parseLong(request.getParameter("customersPricesUid"));
        PriceDto priceDto = new PriceDto();
        CustomersPrices customersPrices = customersPricesService.get(customersPricesUid);
        priceDto.setCustomer(customersPrices.getCustomer());
        priceDto.setCurrency(customersPrices.getPriceType().getShortName());
        priceDto.setCustomersPricesUid(customersPricesUid);
        priceDto.setActivationDate(DateUtils.increaseDate(DateUtils.nowDate(), 7));
        return priceDto;
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
        super.initBinder(request, binder);
    }

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) {
        PriceDto priceDto = (PriceDto) command;
        String[] phoneCodes = priceDto.getPhoneCode().split(",");
        CustomersPrices customersPrices = customersPricesService.get(priceDto.getCustomersPricesUid());
        for (String phoneCode : phoneCodes) {
            Price price = new Price();
            price.setDestination(priceDto.getDestination());
            price.setPriceIndicator("new");
            price.setCurrency(priceDto.getCurrency());
            price.setPhoneCode(phoneCode.trim());
            price.setActivationDate(priceDto.getActivationDate());
            price.setRatePeak(priceDto.getRatePeak());
            price.setRateOffpeak(priceDto.getRatePeak());
            price.setCustomersPrices(customersPrices);
            price.setQos(priceDto.getQos());
            //Static fields
            price.setConnectRateOffpeak(0D);
            price.setConnectRatePeak(0D);
            price.setFreeOffpeak(0D);
            price.setFreePeak(0D);
            price.setInitPeak(1D);
            price.setInitOffpeak(1D);
            price.setLastFieldIgnore(Short.parseShort("0"));
            price.setQuantPeak(1D);
            price.setQuantOffpeak(1D);
            priceService.save(price);
        }

        ModelAndView mav = new ModelAndView(new RedirectView(getSuccessView()));
        mav.addObject("customersPricesUid", priceDto.getCustomersPricesUid());
        mav.addObject("direct", "true");
        return mav;
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
