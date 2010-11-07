package net.skytelecom.web.forms;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.skytelecom.dto.PriceDto;
import net.skytelecom.services.IPriceService;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * Class: DeleteDestinationFormController
 * @author Alexey Khudyakov
 * ICQ: 164777039
 *
 */
public class DeleteDestinationsFormController extends SimpleFormController {

    private IPriceService priceService;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        Long customersPricesUid = Long.parseLong(request.getParameter("customersPricesUid"));
        request.setAttribute("destinations", priceService.findDistinctDestinations(customersPricesUid));
        PriceDto priceDto = new PriceDto();
        priceDto.setCustomersPricesUid(customersPricesUid);
        return priceDto;
    }

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) {
        String[] destinations = (String[]) request.getParameterValues("checks");
        PriceDto priceDto = (PriceDto)command;
        priceService.deleteByDestinationsNames(destinations, priceDto.getCustomersPricesUid());
        return new ModelAndView(new RedirectView(getSuccessView()));
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
