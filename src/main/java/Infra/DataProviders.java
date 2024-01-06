package Infra;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataProviders
{
	public static Logger logger = LogManager.getLogger(DataProviders.class);
	private final Properties config = new Properties();
	
    private Object[][] getDataFromSheet(String fileName, int sheetIndex) throws IOException {

      //Determine active profile from Maven properties
        String activeProfile = System.getProperty("activeProfile");
        System.out.println("Active Profile : " + activeProfile);

      //Load configuration based on the active profile
        String configFileName = System.getProperty("configFile");
        System.out.println("Config FileName  : " + configFileName);
        if (configFileName == null) {
        	logger.error("Please specify a valid configuration file.");
            return null;
        }
        
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFileName)) {
            if (input == null) {
            	logger.error("Sorry, unable to find config.properties");
                return null;
            }
            config.load(input);
        } catch (Exception e) {
            logger.error("Failed to load configuration", e);
        }

        String resourcePath = config.getProperty("resource.directory");
        System.out.println("Resource Path : " + resourcePath);
        
        String filePath = resourcePath + fileName;
        System.out.println("File Path Updated : " + filePath);
        
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];
        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
            }
        }

        workbook.close();
        fis.close();

        return data;
    }

    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginData() throws IOException {
        return getDataFromSheet("Configuration.xlsm", 1);
    }

    @DataProvider(name = "reportData")
    public Object[][] getReportData() throws IOException {
        return getDataFromSheet("Configuration.xlsm", 2);
    }
}