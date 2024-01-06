package Model;

import Infra.BasePage;
import Infra.DataProviders;
import Pages.AvailabilityReportPage;
import Pages.ComprehensiveSalesReportPage;
import Pages.LoginPage;
import Pages.ReportPage;
import Validations.AvailabilityReportValidation;
import Validations.ComprehensiveSalesReportValidation;
import Validations.LoginValidation;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class DownloadReports extends BasePage {

    private void login(String UserID, String username, String password) throws InterruptedException {
        logger.info("-------------------------------------------------------");
        logger.info("LOG IN");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        LoginPage loginPage = new LoginPage(driver);
        LoginValidation loginValidation = new LoginValidation(driver);

        loginPage.openLoginPage(loginPage.setUrl());
        logger.info("REDIRECT TO: " +loginPage.setUrl());
        Thread.sleep(2000);

        //Enter User Details

        logger.info("USER ID: "+UserID);
        Thread.sleep(2000);

        loginPage.fillUsername(username);
        logger.info("ENTER USERNAME: "+username);
        Thread.sleep(2000);

        loginPage.fillPassword(password);
        logger.info("ENTER PASSWORD: "+password);
        Thread.sleep(2000);

        logger.info("CLICK ON LOG IN BUTTON \n");
        loginPage.clickOnLogIn();
        Thread.sleep(2000);

        //Validate Login Page

        loginValidation.LoginSuccess();
        Thread.sleep(2000);

        logger.info("-------------------------------------------------------");
        logger.info("LOG IN SUCCESS");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);
    }

    private void navigateBack(int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            driver.navigate().back();
            Thread.sleep(2000);
        }
    }

    private void handleComprehensiveSalesReport(String Status) throws InterruptedException {
        if (Status.equals("Enable")) {
            logger.info("-------------------------------------------------------");
            logger.info("LOAD REPORTS SCREEN");
            logger.info("-------------------------------------------------------\n");
            Thread.sleep(2000);

            ReportPage reportPage = new ReportPage(driver);
            ComprehensiveSalesReportValidation comprehensiveSalesReportValidation = new ComprehensiveSalesReportValidation(driver);

            logger.info("CLICK ON REPORTS BUTTON \n");
            reportPage.ViewReports();
            Thread.sleep(2000);

            //Validate Report Page

            comprehensiveSalesReportValidation.ReportPage();
            Thread.sleep(2000);

            logger.info("-------------------------------------------------------");
            logger.info("REPORT SCREEN LOADED");
            logger.info("-------------------------------------------------------\n");
            Thread.sleep(2000);

            logger.info("-------------------------------------------------------");
            logger.info("LOAD COMPREHENSIVE SALES REPORT");
            logger.info("-------------------------------------------------------\n");
            Thread.sleep(2000);

            logger.info("CLICK ON VIEW COMPREHENSIVE SALES REPORT BUTTON \n");
            reportPage.LoadComprehensiveSalesReport();
            Thread.sleep(2000);

            //Validate Comprehensive Sales Report

            comprehensiveSalesReportValidation.LoadComprehensiveSalesReportPage();
            Thread.sleep(2000);

            ComprehensiveSalesReportPage comprehensiveSalesReportPage = new ComprehensiveSalesReportPage(driver);

            comprehensiveSalesReportPage.switchFrame();
            Thread.sleep(2000);

            comprehensiveSalesReportPage.selectSeason();
            logger.info("SELECT SEASONS");
            Thread.sleep(2000);

            comprehensiveSalesReportPage.selectVenue();
            logger.info("SELECT ALL VENUES");
            Thread.sleep(2000);

            comprehensiveSalesReportPage.selectTour();
            logger.info("SELECT ALL TOURS");
            Thread.sleep(2000);

            comprehensiveSalesReportPage.includeCancel();
            logger.info("SELECT INCLUDE CANCELLED: TRUE");
            Thread.sleep(2000);

            comprehensiveSalesReportPage.ViewReport();
            logger.info("CLICK ON VIEW REPORT BUTTON \n");
            Thread.sleep(5000);


            //Validate Report

            comprehensiveSalesReportValidation.ViewComprehensiveSalesReport();
            Thread.sleep(2000);

            logger.info("-------------------------------------------------------");
            logger.info("COMPREHENSIVE SALES REPORT LOADED");
            logger.info("-------------------------------------------------------\n");
            Thread.sleep(2000);

            logger.info("-------------------------------------------------------");
            logger.info("DOWNLOAD COMPREHENSIVE REPORT");
            logger.info("-------------------------------------------------------\n");
            Thread.sleep(10000);

            comprehensiveSalesReportPage.selectDownloadFormat();
            logger.info("CLICK ON SAVE REPORT BUTTON");
            Thread.sleep(8000);

            comprehensiveSalesReportPage.downloadCSV();
            logger.info("CLICK ON DOWNLOAD CSV REPORT BUTTON \n");
            Thread.sleep(20000);

            // Validate Availability Report

            comprehensiveSalesReportValidation.ComprehensiveSalesReportDownloadSuccess();
            Thread.sleep(2000);

            navigateBack(3);

            logger.info("-------------------------------------------------------");
            logger.info("COMPREHENSIVE REPORT DOWNLOADED");
            logger.info("-------------------------------------------------------\n");
            Thread.sleep(2000);

        } else if (Status.equals("Disable")) {
            throw new SkipException("COMPREHENSIVE SALES REPORT DOWNLOAD SKIPPED");
        }
    }

    private void handleAvailabilityReport(String Status) throws InterruptedException {
        if (Status.equals("Enable")) {
            logger.info("-------------------------------------------------------");
            logger.info("LOAD REPORTS SCREEN");
            logger.info("-------------------------------------------------------\n");
            Thread.sleep(2000);

            ReportPage reportPage = new ReportPage(driver);
            AvailabilityReportValidation availabilityReportValidation = new AvailabilityReportValidation(driver);

            logger.info("CLICK ON REPORTS BUTTON \n");
            reportPage.ViewReports();
            Thread.sleep(2000);

            //Validate Report Page

            availabilityReportValidation.ReportPage();
            Thread.sleep(2000);

            logger.info("-------------------------------------------------------");
            logger.info("REPORT SCREEN LOADED");
            logger.info("-------------------------------------------------------\n");
            Thread.sleep(2000);

            logger.info("-------------------------------------------------------");
            logger.info("LOAD AVAILABILITY SALES REPORT");
            logger.info("-------------------------------------------------------\n");
            Thread.sleep(2000);

            logger.info("CLICK ON VIEW AVAILABILITY SALES REPORT BUTTON \n");
            reportPage.LoadAvailabilityReport();
            Thread.sleep(2000);

            //Validate Comprehensive Sales Report

            availabilityReportValidation.LoadAvailabilityReportPage();
            Thread.sleep(2000);

            AvailabilityReportPage availabilityReportPage = new AvailabilityReportPage(driver);

            availabilityReportPage.switchFrame();
            Thread.sleep(2000);

            availabilityReportPage.selectSeason();
            logger.info("SELECT SEASONS");
            Thread.sleep(2000);

            availabilityReportPage.selectVessel();
            logger.info("SELECT ALL VESSEL");
            Thread.sleep(2000);

            availabilityReportPage.selectTour();
            logger.info("SELECT ALL TOURS");
            Thread.sleep(2000);

            availabilityReportPage.ViewReport();
            logger.info("CLICK ON VIEW REPORT BUTTON \n");
            Thread.sleep(5000);


            //Validate Report

            availabilityReportValidation.ViewAvailabilityReport();
            Thread.sleep(2000);

            logger.info("-------------------------------------------------------");
            logger.info("AVAILABILITY REPORT LOADED");
            logger.info("-------------------------------------------------------\n");
            Thread.sleep(2000);

            logger.info("-------------------------------------------------------");
            logger.info("DOWNLOAD AVAILABILITY REPORT");
            logger.info("-------------------------------------------------------\n");
            Thread.sleep(2000);

            availabilityReportPage.selectDownloadFormat();
            logger.info("CLICK ON SAVE REPORT BUTTON");
            Thread.sleep(2000);

            availabilityReportPage.downloadCSV();
            logger.info("CLICK ON DOWNLOAD CSV REPORT BUTTON \n");
            Thread.sleep(10000);

            // Validate Availability Report

            availabilityReportValidation.AvailabilityReportDownloadSuccess();
            Thread.sleep(2000);

            navigateBack(3);

            logger.info("-------------------------------------------------------");
            logger.info("AVAILABILITY REPORT DOWNLOADED");
            logger.info("-------------------------------------------------------\n");
            Thread.sleep(2000);
        } else if (Status.equals("Disable")) {
            throw new SkipException("AVAILABILITY REPORT DOWNLOAD SKIPPED");
        }
    }

    @Test(priority = 1, description = "Log In to Rez Magic", dataProvider = "loginCredentials", dataProviderClass = DataProviders.class)
    public void LogIntoRezMagic(String UserID, String username, String password) throws InterruptedException {
        login(UserID, username, password);
    }

    @Test(priority = 2, description = "Download Reports CSV", dataProvider = "reportData", dataProviderClass = DataProviders.class)
    public void DownloadReportCSV(String ReportName, String Status) throws InterruptedException {
        if (ReportName.equals("Comprehensive Sales Report Detailed")) {
            handleComprehensiveSalesReport(Status);
        } else if (ReportName.equals("Availability Report")) {
            handleAvailabilityReport(Status);
        }
    }
}