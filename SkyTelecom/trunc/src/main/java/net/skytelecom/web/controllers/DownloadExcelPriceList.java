package net.skytelecom.web.controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.entity.Price;
import net.skytelecom.services.ICustomersPricesService;
import net.skytelecom.services.IExcelPriceListService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.DateUtils;

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
        CustomersPrices customersPrices = getCustomersPricesServices().get(customersPricesUid);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"update_"
                + DateUtils.nowString("dd.MM.yyyy") + "_" + customersPrices.getCustomer().getCustomerName()
                + "_" + customersPrices.getPriceType().getName() + ".xls"
                + "\"");

        Properties pro = new Properties();
        pro.load(this.getClass().getResourceAsStream("/general.properties"));
        String fp = pro.getProperty("reports.reportFilesFolder");
        InputStream inp = new FileInputStream(fp + "template.xls");
        Workbook wb = WorkbookFactory.create(inp);
        CreationHelper createHelper = wb.getCreationHelper();
        Row row = null;
        Cell cell = null;
        CellStyle cellStyleDate = wb.createCellStyle();
        CellStyle cellStyleNumeric = wb.createCellStyle();
        CellStyle cellStyleCenter = wb.createCellStyle();
        CellStyle cellStyleGeneral = wb.createCellStyle();
        CellStyle cellStyleDateHeader = wb.createCellStyle();

        cellStyleNumeric.setDataFormat(wb.createDataFormat().getFormat("#,##0.00000"));
        cellStyleNumeric.setAlignment(CellStyle.ALIGN_RIGHT);

        cellStyleCenter.setAlignment(CellStyle.ALIGN_CENTER);

        cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
        cellStyleDate.setAlignment(CellStyle.ALIGN_RIGHT);

        cellStyleDateHeader.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
        cellStyleDateHeader.setAlignment(CellStyle.ALIGN_CENTER);
        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setItalic(true);
        cellStyleDateHeader.setFont(font);

        //Cover page
        Sheet sheet = wb.getSheetAt(0);

        row = sheet.getRow(3);
        cell = row.getCell(1);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue(customersPrices.getCustomer().getCustomerName());

        row = sheet.getRow(4);
        cell = row.getCell(1);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue(customersPrices.getCustomer().getCustomerId());

        row = sheet.getRow(5);
        cell = row.getCell(1);
        cell.setCellStyle(cellStyleDateHeader);
        cell.setCellValue(new Date());

        //Data page
        sheet = wb.getSheetAt(1);
        sheet.setDisplayGridlines(false);
        int i = 1;
        for (Price price : customersPrices.getPrices()) {
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(price.getDestination());
            cell.setCellStyle(makeCellBorder(cellStyleGeneral, wb));

            cell = row.createCell(1);
            cell.setCellValue(price.getPhoneCode());
            cell.setCellStyle(makeCellBorder(cellStyleCenter, wb));
            cell.setCellType(Cell.CELL_TYPE_STRING);

            cell = row.createCell(2);
            cell.setCellValue(price.getRatePeak());
            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            cell.setCellStyle(makeCellBorder(cellStyleNumeric, wb));

            cell = row.createCell(3);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(price.getCurrency());
            cell.setCellStyle(makeCellBorder(cellStyleCenter, wb));

            cell = row.createCell(4);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(formatQoS(price.getQos()));
            cell.setCellStyle(makeCellBorder(cellStyleGeneral, wb));

            cell = row.createCell(5);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(price.getPriceIndicator());
            cell.setCellStyle(makeCellBorder(cellStyleCenter, wb));

            cell = row.createCell(6);
            cell.setCellValue(price.getActivationDate());
            cell.setCellStyle(makeCellBorder(cellStyleDate, wb));

            i++;
        }
        final ServletOutputStream out = response.getOutputStream();
        try {
            wb.write(out);
        } finally {
            out.close();
        }
        return null;
    }

    private CellStyle makeCellBorder(CellStyle style, Workbook wb) {
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        return style;
    }

    private String formatQoS(String qos) {
        if (qos.equals("transit")) {
            qos = "";
        }
        if (qos.equals("cli")) {
            qos = "Premium, CLI";
        }
        if (qos.equals("direct")) {
            qos = "Direct";
        }
        if (qos.equals("premium")) {
            qos = "Premium";
        }
        return qos;
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
