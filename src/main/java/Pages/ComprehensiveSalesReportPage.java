package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.Properties;

public class ComprehensiveSalesReportPage {

    private String comprehensiveReportFileName;
    private String absoluteDownloadLocation;

    private WebDriver driver;

    @FindBy(xpath = "//h1[contains(text(),'Report: Comprehensive Sales Report Detailed')]")
    WebElement reportHeading;

    @FindBy(id="rptViewer_ctl04_ctl03_ddDropDownButton")
    WebElement season;

    @FindBy(id= "rptViewer_ctl04_ctl03_divDropDown_ctl11")
    WebElement selectFirstSeason;

    @FindBy(id= "rptViewer_ctl04_ctl03_divDropDown_ctl12")
    WebElement selectSecondSeason;

    @FindBy(id= "rptViewer_ctl04_ctl03_divDropDown_ctl13")
    WebElement selectThirdSeason;

    @FindBy(id="rptViewer_ctl04_ctl05_ddDropDownButton")
    WebElement venue;

    @FindBy(id= "rptViewer_ctl04_ctl05_divDropDown_ctl00")
    WebElement selectAllVenue;

    @FindBy(id= "rptViewer_ctl04_ctl07_ddDropDownButton")
    WebElement tour;

    @FindBy(id= "rptViewer_ctl04_ctl07_divDropDown_ctl00")
    WebElement selectAllTour;

    @FindBy(id = "rptViewer_ctl04_ctl09_rbTrue")
    WebElement includeCancel;

    @FindBy(name = "rptViewer$ctl04$ctl00")
    WebElement viewButton;

    @FindBy(id = "rptViewer_ctl09")
    WebElement viewComprehensiveReport;

    @FindBy(id = "rptViewer_ctl05_ctl04_ctl00_ButtonImgDown")
    WebElement selectFormat;

    @FindBy(linkText = "CSV (comma delimited)")
    WebElement downloadCSV;
    
    private final String COMPREHENSIVE_REPORT_NAME = "Report: Comprehensive Sales Report Detailed";

    public ComprehensiveSalesReportPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        loadConfigProperties();
    }

    public String getExpectedReportHeading()
    {
        String Title = "Report: Comprehensive Sales Report Detailed";
        return Title;
    }

    public String getActualReportHeading()
    {
        String Title = reportHeading.getText();
        return Title;
    }

    public void switchFrame() throws InterruptedException {
        driver.switchTo().frame(0);
        Thread.sleep(2000);
    }

    public void selectSeason() throws InterruptedException {
        season.click();
        Thread.sleep(2000);

        selectFirstSeason.click();
        Thread.sleep(2000);

        selectSecondSeason.click();
        Thread.sleep(2000);

        selectThirdSeason.click();
        Thread.sleep(2000);

        season.click();
        Thread.sleep(2000);
    }

    public void selectVenue() throws InterruptedException {
        venue.click();
        Thread.sleep(2000);

        selectAllVenue.click();
        Thread.sleep(2000);

        venue.click();
        Thread.sleep(2000);
    }

    public void selectTour() throws InterruptedException {
        tour.click();
        Thread.sleep(2000);

        selectAllTour.click();
        Thread.sleep(2000);

        tour.click();
        Thread.sleep(2000);
    }

    public void includeCancel() throws InterruptedException {
        includeCancel.click();
        Thread.sleep(2000);
    }

    public void ViewReport() throws InterruptedException {
        viewButton.click();
        Thread.sleep(2000);
    }

    public boolean ViewComprehensiveReport()
    {
        boolean viewComprehensive = viewComprehensiveReport.isDisplayed();
        return viewComprehensive;
    }

    public void selectDownloadFormat() throws InterruptedException {
        selectFormat.click();
        Thread.sleep(2000);
    }

    public void downloadCSV() throws InterruptedException {
        downloadCSV.click();
        Thread.sleep(2000);
    }

    private void loadConfigProperties() {
        Properties applicationProperties = ConfigurationLoader.loadApplicationProperties();
        String configFilePath = applicationProperties.getProperty("configFilePath", "src/main/resources/config.properties");
        System.out.println("configFilePath ccc :" + configFilePath);

        Properties properties = ConfigurationLoader.loadConfiguration(configFilePath);
        comprehensiveReportFileName = properties.getProperty("comprehensiveReportFileName");
        System.out.println("comprehensiveReportFileName :" + comprehensiveReportFileName);
        
        absoluteDownloadLocation = properties.getProperty("absoluteDownloadLocation");
        System.out.println("absoluteDownloadLocation :" + absoluteDownloadLocation);
    }
    
    public boolean getComprehensiveReportFileName() {
        File folder = new File(absoluteDownloadLocation);
        System.out.println("Absolute Download Location GGGGG :" + absoluteDownloadLocation);
        File[] listOfFiles = folder.listFiles();

        boolean isFileAvailable = false;

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                System.out.println("fileName comparison :" + fileName);
                
                if (fileName.matches(comprehensiveReportFileName)) {
                    isFileAvailable = true;
                }
            }
        }
        System.out.println("isFileAvailable :" + isFileAvailable);
        return isFileAvailable;
    }
}
