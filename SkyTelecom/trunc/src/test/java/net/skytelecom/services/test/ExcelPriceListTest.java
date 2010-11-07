package net.skytelecom.services.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.services.ICustomersPricesService;
import net.skytelecom.services.IExcelPriceListService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


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
    private IExcelPriceListService excelPriceListService;

    public ExcelPriceListTest() {
    }

    @Test
//    public void testGenerateExcelPriceList() throws IOException, Exception {
//        CustomersPrices customersPrices = customersPricesService.get(5L);
//        Properties pro = new Properties();
//        pro.load(this.getClass().getResourceAsStream("/general.properties"));
//        String fp = pro.getProperty("reports.reportFilesFolder");
//        HSSFWorkbook wb = excelPriceListService.generateOutputPriceList(customersPrices);
//        FileOutputStream fileOut = new FileOutputStream(fp + "template-out.xls");
//        wb.write(fileOut);
//        fileOut.close();
//    }

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
}
