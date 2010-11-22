package net.skytelecom.services.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.entity.Price;
import net.skytelecom.services.IExcelPriceListService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 24.08.2010
 */
public class ExcelPriceListServiceImpl implements IExcelPriceListService {

    @Override
    public HSSFWorkbook generateOutputPriceList(CustomersPrices customersPrices, String fp) throws IOException, Exception {
//        Properties pro = new Properties();
//        pro.load(this.getClass().getResourceAsStream("/general.properties"));
//        String fp = pro.getProperty("reports.reportFilesFolder");
        InputStream inp = new FileInputStream(fp + "/template.xls");
        HSSFWorkbook wb = (HSSFWorkbook) WorkbookFactory.create(inp);
        CreationHelper createHelper = wb.getCreationHelper();
        Row row = null;
        Cell cell = null;

        HSSFPalette palette = wb.getCustomPalette();
        //Increase
        palette.setColorAtIndex(IndexedColors.BROWN.getIndex(),
                (byte) 250, // RGB red (0 - 255)
                (byte) 192, // RGB green
                (byte) 144 // RGB blue
                );
        //Decrease
        palette.setColorAtIndex(IndexedColors.AQUA.getIndex(),
                (byte) 204, // RGB red (0 - 255)
                (byte) 255, // RGB green
                (byte) 204 // RGB blue
                );
        //Terminated
        palette.setColorAtIndex(IndexedColors.LAVENDER.getIndex(),
                (byte) 255, // RGB red (0 - 255)
                (byte) 153, // RGB green
                (byte) 204 // RGB blue
                );
        //New
        palette.setColorAtIndex(IndexedColors.YELLOW.getIndex(),
                (byte) 255, // RGB red (0 - 255)
                (byte) 255, // RGB green
                (byte) 153 // RGB blue
                );
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

        // It's the worst part of code I've ever written.

        //Increase
        CellStyle cellStyleDateIncrease = wb.createCellStyle();
        CellStyle cellStyleNumericIncrease = wb.createCellStyle();
        CellStyle cellStyleCenterIncrease = wb.createCellStyle();
        CellStyle cellStyleGeneralIncrease = wb.createCellStyle();

        cellStyleNumericIncrease.setDataFormat(wb.createDataFormat().getFormat("#,##0.00000"));
        cellStyleNumericIncrease.setAlignment(CellStyle.ALIGN_RIGHT);
        cellStyleNumericIncrease.setFillForegroundColor(HSSFColor.BROWN.index);
        cellStyleNumericIncrease.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyleCenterIncrease.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyleCenterIncrease.setFillForegroundColor(HSSFColor.BROWN.index);
        cellStyleCenterIncrease.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        cellStyleDateIncrease.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
        cellStyleDateIncrease.setAlignment(CellStyle.ALIGN_RIGHT);
        cellStyleDateIncrease.setFillForegroundColor(HSSFColor.BROWN.index);
        cellStyleDateIncrease.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyleGeneralIncrease.setFillForegroundColor(HSSFColor.BROWN.index);
        cellStyleGeneralIncrease.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //Decrease
        CellStyle cellStyleDateDecrease = wb.createCellStyle();
        CellStyle cellStyleNumericDecrease = wb.createCellStyle();
        CellStyle cellStyleCenterDecrease = wb.createCellStyle();
        CellStyle cellStyleGeneralDecrease = wb.createCellStyle();

        cellStyleNumericDecrease.setDataFormat(wb.createDataFormat().getFormat("#,##0.00000"));
        cellStyleNumericDecrease.setAlignment(CellStyle.ALIGN_RIGHT);
        cellStyleNumericDecrease.setFillForegroundColor(HSSFColor.AQUA.index);
        cellStyleNumericDecrease.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyleCenterDecrease.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyleCenterDecrease.setFillForegroundColor(HSSFColor.AQUA.index);
        cellStyleCenterDecrease.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        cellStyleDateDecrease.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
        cellStyleDateDecrease.setAlignment(CellStyle.ALIGN_RIGHT);
        cellStyleDateDecrease.setFillForegroundColor(HSSFColor.AQUA.index);
        cellStyleDateDecrease.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyleGeneralDecrease.setFillForegroundColor(HSSFColor.AQUA.index);
        cellStyleGeneralDecrease.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //Terminated
        CellStyle cellStyleDateTerminated = wb.createCellStyle();
        CellStyle cellStyleNumericTerminated = wb.createCellStyle();
        CellStyle cellStyleCenterTerminated = wb.createCellStyle();
        CellStyle cellStyleGeneralTerminated = wb.createCellStyle();

        cellStyleNumericTerminated.setDataFormat(wb.createDataFormat().getFormat("#,##0.00000"));
        cellStyleNumericTerminated.setAlignment(CellStyle.ALIGN_RIGHT);
        cellStyleNumericTerminated.setFillForegroundColor(HSSFColor.LAVENDER.index);
        cellStyleNumericTerminated.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyleCenterTerminated.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyleCenterTerminated.setFillForegroundColor(HSSFColor.LAVENDER.index);
        cellStyleCenterTerminated.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        cellStyleDateTerminated.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
        cellStyleDateTerminated.setAlignment(CellStyle.ALIGN_RIGHT);
        cellStyleDateTerminated.setFillForegroundColor(HSSFColor.LAVENDER.index);
        cellStyleDateTerminated.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyleGeneralTerminated.setFillForegroundColor(HSSFColor.LAVENDER.index);
        cellStyleGeneralTerminated.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //New
        CellStyle cellStyleDateNew = wb.createCellStyle();
        CellStyle cellStyleNumericNew = wb.createCellStyle();
        CellStyle cellStyleCenterNew = wb.createCellStyle();
        CellStyle cellStyleGeneralNew = wb.createCellStyle();

        cellStyleNumericNew.setDataFormat(wb.createDataFormat().getFormat("#,##0.00000"));
        cellStyleNumericNew.setAlignment(CellStyle.ALIGN_RIGHT);
        cellStyleNumericNew.setFillForegroundColor(HSSFColor.YELLOW.index);
        cellStyleNumericNew.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyleCenterNew.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyleCenterNew.setFillForegroundColor(HSSFColor.YELLOW.index);
        cellStyleCenterNew.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        cellStyleDateNew.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
        cellStyleDateNew.setAlignment(CellStyle.ALIGN_RIGHT);
        cellStyleDateNew.setFillForegroundColor(HSSFColor.YELLOW.index);
        cellStyleDateNew.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyleGeneralNew.setFillForegroundColor(HSSFColor.YELLOW.index);
        cellStyleGeneralNew.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

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

        if (customersPrices.getPrices() == null) {
            return wb;
        }
        //Data page
        sheet = wb.getSheetAt(1);
        sheet.setDisplayGridlines(false);
        int i = 1;
        for (Price price : customersPrices.getPrices()) {
            row = sheet.createRow(i);
            if (price.getPriceIndicator().toUpperCase().contains("INCREASE")) {
                cell = row.createCell(0);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(price.getDestination());
                cell.setCellStyle(createCellBorder(cellStyleGeneralIncrease));

                cell = row.createCell(1);
                cell.setCellValue(price.getPhoneCode());
                cell.setCellStyle(createCellBorder(cellStyleCenterIncrease));
                cell.setCellType(Cell.CELL_TYPE_STRING);

                cell = row.createCell(2);
                cell.setCellValue(price.getRatePeak());
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(createCellBorder(cellStyleNumericIncrease));

                cell = row.createCell(3);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(price.getCurrency());
                cell.setCellStyle(createCellBorder(cellStyleCenterIncrease));

                cell = row.createCell(4);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(formatQoS(price.getQos()));
                cell.setCellStyle(createCellBorder(cellStyleGeneralIncrease));

                cell = row.createCell(5);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(price.getPriceIndicator());
                cell.setCellStyle(createCellBorder(cellStyleCenterIncrease));

                cell = row.createCell(6);
                cell.setCellValue(price.getActivationDate());
                cell.setCellStyle(createCellBorder(cellStyleDateIncrease));

                cell = row.createCell(7);
                cell.setCellValue(price.getRouting());
                cell.setCellStyle(createCellBorder(cellStyleGeneralIncrease));

            } else if (price.getPriceIndicator().toUpperCase().contains("DECREASE")) {
                cell = row.createCell(0);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(price.getDestination());
                cell.setCellStyle(createCellBorder(cellStyleGeneralDecrease));

                cell = row.createCell(1);
                cell.setCellValue(price.getPhoneCode());
                cell.setCellStyle(createCellBorder(cellStyleCenterDecrease));
                cell.setCellType(Cell.CELL_TYPE_STRING);

                cell = row.createCell(2);
                cell.setCellValue(price.getRatePeak());
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(createCellBorder(cellStyleNumericDecrease));

                cell = row.createCell(3);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(price.getCurrency());
                cell.setCellStyle(createCellBorder(cellStyleCenterDecrease));

                cell = row.createCell(4);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(formatQoS(price.getQos()));
                cell.setCellStyle(createCellBorder(cellStyleGeneralDecrease));

                cell = row.createCell(5);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(price.getPriceIndicator());
                cell.setCellStyle(createCellBorder(cellStyleCenterDecrease));

                cell = row.createCell(6);
                cell.setCellValue(price.getActivationDate());
                cell.setCellStyle(createCellBorder(cellStyleDateDecrease));

                cell = row.createCell(7);
                cell.setCellValue(price.getRouting());
                cell.setCellStyle(createCellBorder(cellStyleGeneralDecrease));

            } else if (price.getPriceIndicator().toUpperCase().contains("TERMINATED")) {
                cell = row.createCell(0);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(price.getDestination());
                cell.setCellStyle(createCellBorder(cellStyleGeneralTerminated));

                cell = row.createCell(1);
                cell.setCellValue(price.getPhoneCode());
                cell.setCellStyle(createCellBorder(cellStyleCenterTerminated));
                cell.setCellType(Cell.CELL_TYPE_STRING);

                cell = row.createCell(2);
                cell.setCellValue(price.getRatePeak());
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(createCellBorder(cellStyleNumericTerminated));

                cell = row.createCell(3);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(price.getCurrency());
                cell.setCellStyle(createCellBorder(cellStyleCenterTerminated));

                cell = row.createCell(4);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(formatQoS(price.getQos()));
                cell.setCellStyle(createCellBorder(cellStyleGeneralTerminated));

                cell = row.createCell(5);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(price.getPriceIndicator());
                cell.setCellStyle(createCellBorder(cellStyleCenterTerminated));

                cell = row.createCell(6);
                cell.setCellValue(price.getActivationDate());
                cell.setCellStyle(createCellBorder(cellStyleDateTerminated));

                cell = row.createCell(7);
                cell.setCellValue(price.getRouting());
                cell.setCellStyle(createCellBorder(cellStyleGeneralTerminated));

            } else if (price.getPriceIndicator().toUpperCase().contains("NEW")) {
                cell = row.createCell(0);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(price.getDestination());
                cell.setCellStyle(createCellBorder(cellStyleGeneralNew));

                cell = row.createCell(1);
                cell.setCellValue(price.getPhoneCode());
                cell.setCellStyle(createCellBorder(cellStyleCenterNew));
                cell.setCellType(Cell.CELL_TYPE_STRING);

                cell = row.createCell(2);
                cell.setCellValue(price.getRatePeak());
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(createCellBorder(cellStyleNumericNew));

                cell = row.createCell(3);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(price.getCurrency());
                cell.setCellStyle(createCellBorder(cellStyleCenterNew));

                cell = row.createCell(4);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(formatQoS(price.getQos()));
                cell.setCellStyle(createCellBorder(cellStyleGeneralNew));

                cell = row.createCell(5);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(price.getPriceIndicator());
                cell.setCellStyle(createCellBorder(cellStyleCenterNew));

                cell = row.createCell(6);
                cell.setCellValue(price.getActivationDate());
                cell.setCellStyle(createCellBorder(cellStyleDateNew));

                cell = row.createCell(7);
                cell.setCellValue(price.getRouting());
                cell.setCellStyle(createCellBorder(cellStyleGeneralNew));

            } else if (price.getPriceIndicator().toUpperCase().contains("CURRENT")) {
                cell = row.createCell(0);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(price.getDestination());
                cell.setCellStyle(createCellBorder(cellStyleGeneral));

                cell = row.createCell(1);
                cell.setCellValue(price.getPhoneCode());
                cell.setCellStyle(createCellBorder(cellStyleCenter));
                cell.setCellType(Cell.CELL_TYPE_STRING);

                cell = row.createCell(2);
                cell.setCellValue(price.getRatePeak());
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(createCellBorder(cellStyleNumeric));

                cell = row.createCell(3);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(price.getCurrency());
                cell.setCellStyle(createCellBorder(cellStyleCenter));

                cell = row.createCell(4);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(formatQoS(price.getQos()));
                cell.setCellStyle(createCellBorder(cellStyleGeneral));

                cell = row.createCell(5);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(price.getPriceIndicator());
                cell.setCellStyle(createCellBorder(cellStyleCenter));

                cell = row.createCell(6);
                cell.setCellValue(price.getActivationDate());
                cell.setCellStyle(createCellBorder(cellStyleDate));

                cell = row.createCell(7);
                cell.setCellValue(price.getRouting());
                cell.setCellStyle(createCellBorder(cellStyleGeneral));
            }
            i++;
        }
        return wb;
    }

    private CellStyle createCellBorder(CellStyle style) {
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
}
