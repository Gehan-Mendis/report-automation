package Validations;

import Infra.BasePage;
import Pages.ComprehensiveSalesReportPage;
import Pages.ReportPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ComprehensiveSalesReportValidation extends BasePage {

    private WebDriver driver;

    public ComprehensiveSalesReportValidation(WebDriver driver)
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

    //Comprehensive Sales Report Page Loading Method
    public void LoadComprehensiveSalesReportPage() throws InterruptedException {
        ComprehensiveSalesReportPage comprehensiveSalesReportPage = new ComprehensiveSalesReportPage(driver);

        String ExpectedHeading = comprehensiveSalesReportPage.getExpectedReportHeading();
        String ActualHeading = comprehensiveSalesReportPage.getActualReportHeading();

        if(ExpectedHeading.equals(ActualHeading))
        {
            logger.info("COMPREHENSIVE SALES REPORT PAGE LOAD - SUCCESS\n");
            Thread.sleep(2000);
        }
        else
        {
            logger.error("COMPREHENSIVE SALES REPORT PAGE LOAD - FAILED\n");
            Thread.sleep(2000);
        }
        Assert.assertEquals(ExpectedHeading, ActualHeading, "COMPREHENSIVE SALES REPORT PAGE LOAD - FAILED | " + ActualHeading);
    }

    //Comprehensive Sales Report Page View Method
    public void ViewComprehensiveSalesReport() throws InterruptedException {
        ComprehensiveSalesReportPage comprehensiveSalesReportPage = new ComprehensiveSalesReportPage(driver);

        boolean isReportLoad = comprehensiveSalesReportPage.ViewComprehensiveReport();
        Assert.assertTrue(isReportLoad, "REPORT NOT VISIBLE");

        logger.info("COMPREHENSIVE SALES REPORT LOAD - SUCCESS\n");
        Thread.sleep(2000);
    }

    //Comprehensive Sales Report Page Download Method
    public void ComprehensiveSalesReportDownloadSuccess() throws InterruptedException {
        ComprehensiveSalesReportPage comprehensiveSalesReportPage = new ComprehensiveSalesReportPage(driver);

        boolean isFileAvailable = comprehensiveSalesReportPage.getComprehensiveReportFileName();

        if (isFileAvailable) {
            logger.info("COMPREHENSIVE REPORT DOWNLOAD - SUCCESS \n");
            Thread.sleep(2000);
        } else {
            logger.error("COMPREHENSIVE REPORT DOWNLOAD - FAILED \n");
            Thread.sleep(6000);
        }
        
         System.out.println("ComprehensiveSalesReportDownloadSuccess isFileAvailable  : " + isFileAvailable);
         
         //Assert.assertTrue(isFileAvailable, "COMPREHENSIVE REPORT DOWNLOAD - FAILED");
    }
}
