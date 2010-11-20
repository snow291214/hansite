package net.skytelecom.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.skytelecom.entity.Price;
import net.skytelecom.services.IPriceService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * Class: DeleteCurrentDestination
 * @author Alexey Khudyakov
 * ICQ: 164777039
 *
 */
public class DeleteCurrentDestinationController implements Controller {

    private IPriceService priceService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object customersPricesUid = request.getParameter("customersPricesUid");
        Object destinationName = request.getParameter("destinationName");
        if ((customersPricesUid == null) || (destinationName == null)) {
            return null;
        }
        List<Price> prices = priceService.findByDestinationName((String)destinationName,
                Long.parseLong(customersPricesUid.toString()));
        for (Price price : prices){
            priceService.remove(price.getUid());
        }
        ModelAndView mav =  new ModelAndView(new RedirectView("priceEditor.htm"));
        mav.addObject("customersPricesUid", customersPricesUid);
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
}
