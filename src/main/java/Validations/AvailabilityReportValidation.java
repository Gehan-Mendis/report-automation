package Validations;

import Infra.BasePage;
import Pages.AvailabilityReportPage;
import Pages.ReportPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AvailabilityReportValidation extends BasePage {

    private WebDriver driver;

    public AvailabilityReportValidation(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Report Page Loading Method
    public void ReportPage() throws InterruptedException {

        ReportPage reportPage = new ReportPage(driver);

        String ExpectedScreen = reportPage.getExpectedPageHeading();
        String ActualScreen = reportPage.getActualScreenName();

        if(ExpectedScreen.equals(ActualScreen))
        {
            logger.info("REPORTS PAGE LOAD - SUCCESS\n");
            Thread.sleep(2000);
        }
        else
        {
            logger.error("REPORTS PAGE LOAD - FAILED\n");
            Thread.sleep(2000);
        }
        Assert.assertEquals(ExpectedScreen, ActualScreen, "REPORTS PAGE LOAD - FAILED | " + ActualScreen);

    }

    //Availability Report Page Loading Method
    public void LoadAvailabilityReportPage() throws InterruptedException {
        AvailabilityReportPage availabilityReportPage = new AvailabilityReportPage(driver);

        String ExpectedHeading = availabilityReportPage.getExpectedReportHeading();
        String ActualHeading = availabilityReportPage.getActualReportHeading();

        if(ExpectedHeading.equals(ActualHeading))
        {
            logger.info("AVAILABILITY REPORT PAGE LOAD - SUCCESS\n");
            Thread.sleep(2000);
        }
        else
        {
            logger.error("AVAILABILITY REPORT PAGE LOAD - FAILED\n");
            Thread.sleep(2000);
        }
        Assert.assertEquals(ExpectedHeading, ActualHeading, "AVAILABILITY REPORT PAGE LOAD - FAILED | " + ActualHeading);
    }

    //Availability Report Page View Method
    public void ViewAvailabilityReport() throws InterruptedException {
        AvailabilityReportPage availabilityReportPage = new AvailabilityReportPage(driver);

        boolean isReportLoad = availabilityReportPage.ViewAvailabilityReport();
        Assert.assertTrue(isReportLoad, "REPORT NOT VISIBLE");

        logger.info("AVAILABILITY REPORT LOAD - SUCCESS\n");
        Thread.sleep(2000);
    }

    //Availability Report Page Download Method
    public void AvailabilityReportDownloadSuccess() throws InterruptedException {
        AvailabilityReportPage availabilityReportPage = new AvailabilityReportPage(driver);

        boolean isFileAvailable = availabilityReportPage.getAvailabilityReportFileName();

        if (isFileAvailable) {
            logger.info("AVAILABILITY REPORT DOWNLOAD - SUCCESS \n");
            Thread.sleep(2000);
        } else {
            logger.error("AVAILABILITY REPORT DOWNLOAD - FAILED \n");
            Thread.sleep(2000);
        }
        Assert.assertTrue(isFileAvailable, "AVAILABILITY REPORT DOWNLOAD - FAILED");
    }

}
