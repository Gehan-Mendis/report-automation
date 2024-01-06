package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReportPage {


private WebDriver driver;
    @FindBy(xpath="//a[contains(text(),'Reports')]")
    WebElement ReportSection;

    @FindBy(xpath = "//h1[contains(text(),'Reports: All')]")
    WebElement getReportPageHeading;

    @FindBy(xpath = "//a[contains(text(),'Name')]")
    WebElement sortByName;

    @FindBy(xpath = "//tbody/tr[9]/td[3]/a[1]")
    WebElement viewComprehensiveSalesReport;

    @FindBy(xpath = "//tbody/tr[4]/td[3]/a[1]")
    WebElement viewAvailabilityReport;

    public ReportPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void ViewReports() throws InterruptedException {
        ReportSection.click();
        Thread.sleep(2000);
    }

    public String getExpectedPageHeading()
    {
        String Title = "Reports: All";
        return Title;
    }

    public String getActualScreenName()
    {
        String Title = getReportPageHeading.getText();
        return Title;
    }

    public void LoadComprehensiveSalesReport() throws InterruptedException {

        sortByName.click();
        Thread.sleep(5000);

        viewComprehensiveSalesReport.click();
        Thread.sleep(2000);
    }

    public void LoadAvailabilityReport() throws InterruptedException {

        sortByName.click();
        Thread.sleep(2000);

        viewAvailabilityReport.click();
        Thread.sleep(2000);
    }

}
