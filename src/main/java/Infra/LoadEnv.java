package Infra;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class LoadEnv {

    private static String getCellValue(XSSFSheet sheet, int rowIndex, int columnIndex) {
        Cell cell = sheet.getRow(rowIndex).getCell(columnIndex);
        return (cell != null) ? cell.getStringCellValue() : "";
    }

    private static XSSFSheet getSheet(String fileName, int sheetIndex) throws IOException {
        FileInputStream fis = new FileInputStream("../report-automation/src/main/resources/" + fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        return workbook.getSheetAt(sheetIndex);
    }

    public static String getUrl() {
        String url = "";
        try {
            XSSFSheet sheet = getSheet("Configuration.xlsm", 0);
            url = getCellValue(sheet, 1, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
}