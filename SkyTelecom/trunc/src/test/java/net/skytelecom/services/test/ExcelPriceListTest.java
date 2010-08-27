package net.skytelecom.services.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.entity.Price;
import net.skytelecom.services.ICustomersPricesService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 24.08.2010
 */
public class ExcelPriceListTest extends AbstractTransactionalDataSourceSpringContextTests {

    private ICustomersPricesService customersPricesService;

    public ExcelPriceListTest() {
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

    @Test
    public void testGenerateExcelPriceList() throws IOException, Exception {
        Properties pro = new Properties();
        pro.load(this.getClass().getResourceAsStream("/general.properties"));
        String fp = pro.getProperty("reports.reportFilesFolder");
        CustomersPrices customersPrices = customersPricesService.get(5L);
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

        FileOutputStream fileOut = new FileOutputStream(fp + "template-out.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
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
