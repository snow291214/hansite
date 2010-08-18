package net.skytelecom.web.forms;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.skytelecom.dto.PriceTypeDto;
import net.skytelecom.entity.PriceType;
import net.skytelecom.services.IPriceTypeService;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 18.08.2010
 */

public class priceTypeEditorFormController extends SimpleFormController{

    private IPriceTypeService priceTypeService;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        String priceTypeUid = request.getParameter("priceTypeUid");
        List<PriceType> priceTypes = priceTypeService.getAll();
        request.setAttribute("priceTypes", priceTypes);
        if(priceTypeUid != null){
            PriceType priceType = priceTypeService.get(Long.parseLong(priceTypeUid));
            PriceTypeDto priceTypeDto = new PriceTypeDto();
            priceTypeDto.setUid(priceType.getUid());
            priceTypeDto.setName(priceType.getName());
            priceTypeDto.setCustomersPrices(priceType.getCustomersPrices());
            return priceTypeDto;
        }
        return new PriceTypeDto();
    }

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) {
        PriceTypeDto priceTypeDto = (PriceTypeDto)command;
        PriceType priceType = new PriceType();
        priceType.setUid(priceTypeDto.getUid());
        priceType.setName(priceTypeDto.getName());
        priceType.setCustomersPrices(priceTypeDto.getCustomersPrices());
        priceTypeService.save(priceType);
        return new ModelAndView(new RedirectView(getSuccessView()));
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

}
