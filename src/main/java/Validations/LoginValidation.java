package Validations;

import Infra.BasePage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginValidation extends BasePage
{
    private WebDriver driver;

    public LoginValidation(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void LoginSuccess() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);

        String ExpectedScreen = loginPage.getExpectedScreenName();
        String ActualScreen = loginPage.getActualScreenName();

        if(ExpectedScreen.equals(ActualScreen))
        {
            logger.info("USER LOGIN - SUCCESS\n");
            Thread.sleep(2000);
        }
        else
        {
            logger.error("USER LOG IN - FAILED\n");
            Thread.sleep(2000);
        }
        Assert.assertEquals(ExpectedScreen, ActualScreen, "USER LOG IN - FAILED | " + ActualScreen);

    }
}
