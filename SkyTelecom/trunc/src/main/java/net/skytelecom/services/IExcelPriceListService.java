package net.skytelecom.services;

import java.io.IOException;
import net.skytelecom.entity.CustomersPrices;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 24.08.2010
 */
public interface IExcelPriceListService {

    HSSFWorkbook generateOutputPriceList(CustomersPrices customersPrices, String fp) throws IOException, Exception;
}
