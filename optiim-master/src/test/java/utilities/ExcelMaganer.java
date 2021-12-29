package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelMaganer {
    public static final String       testDataExcelFileName = "testdata.xlsx";
    public static final String       currentDir            = System.getProperty("user.dir");
    public static       String       testDataExcelPath     = null;
    private static      XSSFWorkbook excelWBook;
    private static      XSSFSheet    excelWSheet;
    private static      XSSFCell     cell;
    private static      XSSFRow      row;
    public static       int          rowNumber;
    public static       int          columnNumber;
    @SneakyThrows
    public static void setExcelFileSheet(String sheetName) {

            testDataExcelPath = currentDir + "\\src\\main\\resources\\";

        FileInputStream ExcelFile = new FileInputStream(testDataExcelPath + testDataExcelFileName);
        System.out.println(testDataExcelPath + testDataExcelFileName);
        excelWBook = new XSSFWorkbook(ExcelFile);
        excelWSheet = excelWBook.getSheet(sheetName);
    }
    public static String getCellData(int rowNum, int colNum) {
        cell = excelWSheet.getRow(rowNum).getCell(colNum);
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }
    public static XSSFRow getRowData(int rowNum) {
        row = excelWSheet.getRow(rowNum);
        return row;
    }
    @SneakyThrows
    public static void setCellData(String value, int rowNum, int colNum) {
        row = excelWSheet.getRow(rowNum);
        cell = row.getCell(colNum);
        if (cell == null) {
            cell = row.createCell(colNum);
            cell.setCellValue(value);
        } else {
            cell.setCellValue(value);
        }
        FileOutputStream fileOut = new FileOutputStream(testDataExcelPath + testDataExcelFileName);
        excelWBook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }
}

