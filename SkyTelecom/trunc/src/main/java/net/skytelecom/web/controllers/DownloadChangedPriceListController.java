package net.skytelecom.web.controllers;

import java.text.SimpleDateFormat;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.entity.Price;
import net.skytelecom.services.ICustomersPricesService;
import net.skytelecom.services.IPriceService;
import net.skytelecom.utils.DateUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 22.08.2010
 */
public class DownloadChangedPriceListController implements Controller {

    private ICustomersPricesService customersPricesServices;
    private IPriceService priceService;
    static final private String CONTENT_TYPE_CHARSET = "UTF-8";

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*
        update_[дата]_[компания]_[валюта]_[прайс]
        Tel.code
        Destination
        Currency
        ConnectRate Peak
        Rate Peak
        Free Peak
        Init Peak
        Quant Peak
        ConnectRate Offpeak
        Rate Offpeak
        Free Offpeak
        Init Offpeak
        Quant Offpeak
        Activation Date
        QoS Type ( premium, cli, direct, transit)
        Last Field Ignore
         */
        if (request.getParameter("customersPricesUid") == null) {
            return new ModelAndView("customers");
        }
        Long customersPricesUid = Long.parseLong(request.getParameter("customersPricesUid"));
        CustomersPrices customersPrices = customersPricesServices.get(customersPricesUid);
        response.setContentType("text/plain" + "; " + CONTENT_TYPE_CHARSET);
        response.setHeader("Content-Disposition", "attachment; filename=\"update_"
                + DateUtils.nowString("dd.MM.yyyy") + "_" + customersPrices.getCustomer().getCustomerName()
                + "_" + customersPrices.getPriceType().getName() + ".csv"
                + "\"");
        response.setHeader("cache-control", "must-revalidate");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Tel.code\n");
        stringBuilder.append("Destination\n");
        stringBuilder.append("Currency\n");
        stringBuilder.append("ConnectRate Peak\n");
        stringBuilder.append("Rate Peak\n");
        stringBuilder.append("Free Peak\n");
        stringBuilder.append("Init Peak\n");
        stringBuilder.append("Quant Peak\n");
        stringBuilder.append("ConnectRate Offpeak\n");
        stringBuilder.append("Rate Offpeak\n");
        stringBuilder.append("Free Offpeak\n");
        stringBuilder.append("Init Offpeak\n");
        stringBuilder.append("Quant Offpeak\n");
        stringBuilder.append("Activation Date\n");
        stringBuilder.append("QoS Type ( premium, cli, direct, transit)\n");
        stringBuilder.append("Last Field Ignore\n");
        stringBuilder.append("\n");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (Price price : customersPrices.getPrices()) {
            stringBuilder.append(price.getPhoneCode()).append(";");
            stringBuilder.append(price.getDestination().replaceAll("\\s\\s+|\\n|\\r", " ")).append(";");
            stringBuilder.append(price.getCurrency()).append(";");
            stringBuilder.append(price.getConnectRatePeak()).append(";");
            stringBuilder.append(price.getRatePeak()).append(";");
            stringBuilder.append(price.getFreePeak()).append(";");
            stringBuilder.append(price.getInitPeak()).append(";");
            stringBuilder.append(price.getQuantPeak()).append(";");
            stringBuilder.append(price.getConnectRateOffpeak()).append(";");
            stringBuilder.append(price.getRateOffpeak()).append(";");
            stringBuilder.append(price.getFreeOffpeak()).append(";");
            stringBuilder.append(price.getInitOffpeak()).append(";");
            stringBuilder.append(price.getQuantOffpeak()).append(";");
            stringBuilder.append(df.format(price.getActivationDate())).append(";");
            stringBuilder.append(price.getQos()).append(";");
            stringBuilder.append(price.getLastFieldIgnore()).append("\n");
        }

        final byte[] raw = stringBuilder.toString().getBytes(CONTENT_TYPE_CHARSET);
        response.setContentLength(raw.length);

        final ServletOutputStream out = response.getOutputStream();
        try {
            out.write(raw);
        } finally {
            out.close();
        }
        return null;
//        ServletOutputStream outputStream = response.getOutputStream();
//        InputStream in = new ByteArrayInputStream(stringBuilder.toString().getBytes("UTF-8"));
//        byte[] outputByte = new byte[65536];
//        //copy binary contect to output stream
//        while (in.read(outputByte, 0, 65536) != -1) {
//            outputStream.write(outputByte, 0, 65536);
//        }
//        in.close();
//        outputStream.flush();
//        outputStream.close();
//        return null;
    }

    public ICustomersPricesService getCustomersPricesServices() {
        return customersPricesServices;
    }

    public void setCustomersPricesServices(ICustomersPricesService customersPricesServices) {
        this.customersPricesServices = customersPricesServices;
    }

    public IPriceService getPriceService() {
        return priceService;
    }

    public void setPriceService(IPriceService priceService) {
        this.priceService = priceService;
    }
}
