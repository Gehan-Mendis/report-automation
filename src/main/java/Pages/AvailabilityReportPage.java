package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.Properties;

public class AvailabilityReportPage {
    
    private String availabilityReportFileName;
    private String absoluteDownloadLocation;

    private WebDriver driver;

    @FindBy(xpath = "//h1[contains(text(),'Report: Availability Report')]")
    private WebElement reportHeading;

    @FindBy(id="rptViewer_ctl04_ctl03_ddDropDownButton")
    private WebElement season;

    @FindBy(id= "rptViewer_ctl04_ctl03_divDropDown_ctl09")
    private WebElement selectFirstSeason;

    @FindBy(id= "rptViewer_ctl04_ctl03_divDropDown_ctl10")
    private WebElement selectSecondSeason;

    @FindBy(id= "rptViewer_ctl04_ctl03_divDropDown_ctl11")
    private WebElement selectThirdSeason;

    @FindBy(id="rptViewer_ctl04_ctl05_ddDropDownButton")
    private WebElement vessel;

    @FindBy(id= "rptViewer_ctl04_ctl05_divDropDown_ctl00")
    private WebElement selectAllVessel;

    @FindBy(id= "rptViewer_ctl04_ctl07_ddDropDownButton")
    private WebElement tour;

    @FindBy(id= "rptViewer_ctl04_ctl07_divDropDown_ctl00")
    private WebElement selectAllTour;

    @FindBy(name = "rptViewer$ctl04$ctl00")
    private WebElement viewButton;

    @FindBy(id = "rptViewer_ctl09")
    private WebElement viewAvailabilityReport;

    @FindBy(id = "rptViewer_ctl05_ctl04_ctl00_ButtonImgDown")
    private WebElement selectFormat;

    @FindBy(linkText = "CSV (comma delimited)")
    private WebElement downloadCSV;

    public AvailabilityReportPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        loadConfigProperties();
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

    public void selectVessel() throws InterruptedException {
        vessel.click();
        Thread.sleep(2000);

        selectAllVessel.click();
        Thread.sleep(2000);

        vessel.click();
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

    public void ViewReport() throws InterruptedException {
        viewButton.click();
        Thread.sleep(2000);
    }

    public boolean ViewAvailabilityReport()
    {
        boolean viewAvailability = viewAvailabilityReport.isDisplayed();
        return viewAvailability;
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
        System.out.println("configFilePath FFF :" + configFilePath);
        
            Properties properties = ConfigurationLoader.loadConfiguration(configFilePath);
            availabilityReportFileName = properties.getProperty("availabilityReportFileName");
            System.out.println("Availability Report File Name :" + availabilityReportFileName);
            absoluteDownloadLocation = properties.getProperty("absoluteDownloadLocation");
            System.out.println("Absolute Download Location :" + absoluteDownloadLocation);
    }
    
    public String getExpectedReportHeading() {
        return "Report: Availability Report";
    }

    public String getActualReportHeading() {
        return reportHeading.getText();
    }

    public void switchFrame() throws InterruptedException {
        driver.switchTo().frame(0);
        Thread.sleep(2000);
    }

    public boolean getAvailabilityReportFileName() {
        File folder = new File(absoluteDownloadLocation);
        File[] listOfFiles = folder.listFiles();

        boolean isFileAvailable = false;

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();

                if (fileName.matches(availabilityReportFileName)) {
                    isFileAvailable = true;
                }
            }
        }
        return isFileAvailable;
    }
}

