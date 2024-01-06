package Infra;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import Pages.ConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.io.InputStream;
import java.util.Properties;

@Listeners(ExtentReportListener.class)
public class BasePage {
    protected WebDriver driver;
    private final Properties config = new Properties();

    public static Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage() {
        loadConfig();
    }

    private void loadConfig() {
    	
      //Determine active profile
        String activeProfile = System.getProperty("activeProfile");
        System.out.println("Active Profile : " + activeProfile);

      //Load configuration based on the active profile
        String configFileName = System.getProperty("configFile");
        System.out.println("Config FileName  : " + configFileName);
        if (configFileName == null) {
        	logger.error("Please specify a valid configuration file.");
        	 return;
        }
        
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFileName)) {
            if (input == null) {
            	logger.error("Sorry, unable to find config.properties");
            	 return;
            }
            config.load(input);
        } catch (Exception e) {
            logger.error("Failed to load configuration", e);
        }
    }

    public ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        Map<String, String> prefs = new HashMap<>();
        System.out.println("Absolute Download Location  vvv :" + config.getProperty("absoluteDownloadLocation"));
        prefs.put("download.default_directory", config.getProperty("absoluteDownloadLocation"));
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");

        return options;
    }

    public void createBackUpFile() {
        // Create a timestamp for the current run in the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh.mm.ss a");
        String timestamp = dateFormat.format(new Date());

        // Create a directory with a timestamp in the relativeDownloadLocation
        File backupDirectory = new File(config.getProperty("relativeDownloadLocation"), "RezMagic_Reports_" + timestamp);

        if (backupDirectory.mkdirs()) {
            logger.info("CREATED BACKUP DIRECTORY: " + backupDirectory);
        } else if (backupDirectory.exists()) {
            logger.info("BACKUP DIRECTORY ALREADY EXISTS: " + backupDirectory);
        } else {
            logger.error("FAILED TO CREATE BACKUP DIRECTORY: " + backupDirectory);
        }

        // Get the list of downloaded CSV files
        File downloadDirectory = new File(config.getProperty("absoluteDownloadLocation"));
        File[] downloadedFiles = downloadDirectory.listFiles();

        if (downloadedFiles != null) {
            for (File file : downloadedFiles) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".csv")) {
                    // Copy the CSV file to the backup directory
                    File newFile = new File(backupDirectory, file.getName());

                    try {
                        FileUtils.copyFile(file, newFile);
                        logger.info("COPIED FILE: " + file.getName() + " TO " + newFile.getAbsolutePath());
                    } catch (IOException e) {
                        logger.error("FAILED TO COPY FILE: " + file.getName() + "\n");
                        e.printStackTrace();
                    }
                }
            }
        }
        logger.info("BACKUP FILES COPIED TO: " + backupDirectory + "\n");
    }

    @BeforeClass
    public void openBrowser() throws InterruptedException {
        logger.info("=======================================================");
        logger.info("START SIMULATOR");
        logger.info("=======================================================\n");
        Thread.sleep(2000);

        driver = new ChromeDriver(getOptions());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeTest
    public void ClearFolders() throws IOException {
    	System.out.println("Absolute Download Location xxx :" + config.getProperty("absoluteDownloadLocation"));
        File dictionary = new File(config.getProperty("absoluteDownloadLocation"));
        FileUtils.cleanDirectory(dictionary);
    }

    @AfterClass
    public void closeBrowser() throws InterruptedException {
        logger.info("=======================================================");
        logger.info("END SIMULATOR");
        logger.info("=======================================================\n");
        Thread.sleep(2000);

        driver.quit();
      //createBackUpFile();
    }
}

