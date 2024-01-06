package Infra;

import Pages.LoginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class ExtentReportListener implements ITestListener
{
    private static ExtentReports extent;
    public static String URL;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private WebDriver driver;
    public void onStart(ITestContext context) {

        URL = "https://antarctica21.rezmagic.com/Staff/Account/Login?ReturnUrl=%2FStaff%2F";

        LoginPage loginPage = new LoginPage(driver);
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("Reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Description", "A21 Report Download Automation");
        extent.setSystemInfo("Environment", "Antarctica 21 Rez-Magic");
        extent.setSystemInfo("APP URL", URL);
        extent.setSystemInfo("OS", System.getProperty("os.name"));

        sparkReporter.config().setDocumentTitle("Report");
        sparkReporter.config().setReportName("CSV Download");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }

    public void onTestStart(ITestResult result) {

        String methodName = result.getMethod().getMethodName();
        String Scenario = String.format(Arrays.toString(result.getParameters()));

        String RemoveOpenBracket = Scenario.replace("[","");
        String RemoveCloseBracket = RemoveOpenBracket.replace("]", "");

        String[] ScenarioParts = RemoveCloseBracket.split(",");

        String ReportName = ScenarioParts[0];

        if(Scenario.equals("[]"))
        {
            String testName = methodName;

            ExtentTest extentTest = extent.createTest(testName);
            test.set(extentTest);
        }
        else
        {
            String testName = methodName + " - " + ReportName;

            ExtentTest extentTest = extent.createTest(testName);
            test.set(extentTest);
        }
    }

    public void onTestSuccess(ITestResult result) {

        String methodName = result.getMethod().getMethodName();;
        test.get().info("<b>"+methodName+"</b>");
        test.get().pass(MarkupHelper.createLabel("SUCCESS", ExtentColor.GREEN));
    }

    public void onTestFailure(ITestResult result) {

        String methodName = result.getMethod().getMethodName();

        test.get().info("<b>"+methodName+"</b>");
        test.get().fail(MarkupHelper.createLabel("FAILED", ExtentColor.RED));
        test.get().fail(result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {

        String methodName = result.getMethod().getMethodName();

        test.get().info("<b>"+methodName+"</b>");
        test.get().skip(MarkupHelper.createLabel("SKIPPED", ExtentColor.ORANGE));
        test.get().skip(result.getThrowable());
    }
}
