package net.skytelecom.services;

import java.io.IOException;
import net.skytelecom.entity.CustomersPrices;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 24.08.2010
 */
public interface IExcelPriceListService {

    Workbook generateOutputPriceList(CustomersPrices customersPrices) throws IOException, Exception;
}
