package net.skytelecom.web.controllers;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.services.ICustomersPricesService;
import net.skytelecom.services.IExcelPriceListService;
import net.skytelecom.utils.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 27.08.2010
 */
public class DownloadExcelPriceList implements Controller {

    private ICustomersPricesService customersPricesServices;
    private IExcelPriceListService excelPriceListService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getParameter("customersPricesUid") == null) {
            return new ModelAndView("customers");
        }
        Long customersPricesUid = Long.parseLong(request.getParameter("customersPricesUid"));
        CustomersPrices customersPrices;
        if (request.getParameter("changesOnly") != null) {
            customersPrices = this.customersPricesServices.findByChangedIndicators(customersPricesUid);
            response.setHeader("Content-Disposition", "attachment; filename=\"update_"
                    + DateUtils.nowString("dd.MM.yyyy") + "_" + customersPrices.getCustomer().getCustomerName()
                    + "_" + customersPrices.getPriceType().getName() + "(changes).xls"
                    + "\"");
        } else {
            customersPrices = getCustomersPricesServices().get(customersPricesUid);
            response.setHeader("Content-Disposition", "attachment; filename=\"update_"
                    + DateUtils.nowString("dd.MM.yyyy") + "_" + customersPrices.getCustomer().getCustomerName()
                    + "_" + customersPrices.getPriceType().getName() + "(full).xls"
                    + "\"");
        }
        response.setContentType("application/vnd.ms-excel");

        HSSFWorkbook wb = excelPriceListService.generateOutputPriceList(customersPrices, request.getSession().getServletContext().getRealPath("files"));
        final ServletOutputStream out = response.getOutputStream();
        try {
            wb.write(out);
        } finally {
            out.close();
        }
        return null;
    }

    /**
     * @return the excelPriceListService
     */
    public IExcelPriceListService getExcelPriceListService() {
        return excelPriceListService;
    }

    /**
     * @param excelPriceListService the excelPriceListService to set
     */
    public void setExcelPriceListService(IExcelPriceListService excelPriceListService) {
        this.excelPriceListService = excelPriceListService;
    }

    /**
     * @return the customersPricesServices
     */
    public ICustomersPricesService getCustomersPricesServices() {
        return customersPricesServices;
    }

    /**
     * @param customersPricesServices the customersPricesServices to set
     */
    public void setCustomersPricesServices(ICustomersPricesService customersPricesServices) {
        this.customersPricesServices = customersPricesServices;
    }
}
