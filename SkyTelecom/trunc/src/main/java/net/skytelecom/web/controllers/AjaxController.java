package net.skytelecom.web.controllers;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.entity.Price;
import net.skytelecom.entity.Routing;
import net.skytelecom.services.ICustomerService;
import net.skytelecom.services.ICustomersPricesService;
import net.skytelecom.services.IPriceService;
import net.skytelecom.services.IRoutingService;
import net.skytelecom.utils.EscapeChars;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 04.07.2010
 */
public class AjaxController implements Controller {

    private ICustomersPricesService customersPricesService;
    private ICustomerService customerService;
    private IPriceService priceService;
    private IRoutingService routingService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int requestType = Integer.parseInt(request.getParameter("requestType"));
        Long customerUid = null;
        switch (requestType) {
            //In this case we are getting customer's prices from the database
            case 0:
                if(request.getParameter("customerUid").equals("-")){
                    return null;
                }
                customerUid = Long.parseLong(request.getParameter("customerUid"));
                Collection<CustomersPrices> collection = customerService.get(customerUid).getCustomersPrices();
                response.setContentType("text/xml");
                response.setCharacterEncoding("utf-8");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<root>");
                for (CustomersPrices customersPrices : collection) {
                    response.getWriter().write("<PriceType>");
                    response.getWriter().write("<Uid>");
                    response.getWriter().write(customersPrices.getUid().toString());
                    response.getWriter().write("</Uid>");
                    response.getWriter().write("<Name>");
                    response.getWriter().write(customersPrices.getPriceType().getName());
                    response.getWriter().write("</Name>");
                    response.getWriter().write("</PriceType>");
                }
                response.getWriter().write("</root>");
                break;
            //In this case we are getting distinct destinations from the database
            case 1:
                if(request.getParameter("customersPricesUid").equals("-")){
                    return null;
                }
                Long customersPricesUid = Long.parseLong(request.getParameter("customersPricesUid"));
                List<String> destinations = priceService.findDistinctDestinations(customersPricesUid);
                response.setContentType("text/xml");
                response.setCharacterEncoding("utf-8");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<root>");
                for (String destination : destinations) {
                    response.getWriter().write("<Destination>" + EscapeChars.forXML(destination) + "</Destination>");
                }
                response.getWriter().write("</root>");
                break;
            //In this case we are getting distinct destinations from the database
            case 2:
                if(request.getParameter("customersPricesUid").equals("-")){
                    return null;
                }
                customerUid = Long.parseLong(request.getParameter("customersPricesUid"));
                String destinationName = request.getParameter("destinationName");
                Price price = priceService.getOldDestinationRateByDestinationName(destinationName, customerUid);
                List<Price> prices = priceService.findByDestinationName(destinationName, customerUid);
                response.setContentType("text/xml");
                response.setCharacterEncoding("utf-8");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<root>");
                response.getWriter().write("<QoS>" + price.getQos() + "</QoS>");
                response.getWriter().write("<Currency>" + price.getCurrency() + "</Currency>");
                response.getWriter().write("<Value>" + price.getRatePeak().toString() + "</Value>");
                Format formatter = new SimpleDateFormat("dd.MM.yyyy");
                String s = formatter.format(new java.util.Date());
                response.getWriter().write("<ActivationDate>" + s + "</ActivationDate>");
                response.getWriter().write("<AreaCodes>");
                for (Price p : prices) {
                    response.getWriter().write("<AreaCode>" + p.getPhoneCode() + "</AreaCode>");
                }
                response.getWriter().write("</AreaCodes>");
                response.getWriter().write("</root>");
                break;
            //In this case we are getting routing customers from the database
            case 3:
                List<Routing> routings = routingService.getAll();
                response.setContentType("text/xml");
                response.setCharacterEncoding("utf-8");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<root>");
                response.getWriter().write("<RoutingProviders>");
                for (Routing routing : routings) {
                    response.getWriter().write("<Provider>" + EscapeChars.forXML(routing.getName()) + "</Provider>");
                }
                response.getWriter().write("</RoutingProviders>");
                response.getWriter().write("</root>");
                break;
        }
        return null;
    }

    public IPriceService getPriceService() {
        return priceService;
    }

    public void setPriceService(IPriceService priceService) {
        this.priceService = priceService;
    }

    public IRoutingService getRoutingService() {
        return routingService;
    }

    public void setRoutingService(IRoutingService routingService) {
        this.routingService = routingService;
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
