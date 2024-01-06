package Pages;

import Infra.LoadEnv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
    private WebDriver driver;
    @FindBy(name="UserName")
    WebElement userNameField;

    @FindBy(name="Password")
    WebElement passwordField;

    @FindBy(xpath = "//button[contains(text(),'Sign In')]")
    WebElement logInButton;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillField(WebElement field, String value)
    {
        field.sendKeys(value);
    }

    public String setUrl()
    {
        LoadEnv loadEnv = new LoadEnv();
        String url = loadEnv.getUrl();
        return url;
    }

    public String openLoginPage(String url) {
        driver.get(url);
        return url;
    }

    public void fillUsername(String username) throws InterruptedException {
        this.fillField(userNameField,username);
        Thread.sleep(2000);
    }

    public void fillPassword(String password) throws InterruptedException {
        this.fillField(passwordField,password);
        Thread.sleep(2000);
    }

    public void clickOnLogIn() throws InterruptedException {
        logInButton.click();
        Thread.sleep(2000);
    }

    public String getExpectedScreenName()
    {
        String Title = "Tours - RezMagic";
        return Title;
    }

    public String getActualScreenName()
    {
        String Title = driver.getTitle();
        return Title;
    }

}
