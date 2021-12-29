package utilities;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;
import utilities.ExcelMaganer;
import utilities.ExtentManager;
import utilities.ExtentTestMaganer;

import java.io.File;

public class Listeners extends BaseTest implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log4jManager.info("==============="+"Logging started for"+" "+iTestResult.getMethod().getMethodName()+"==================");
        Log4jManager.info(iTestResult.getMethod().getMethodName()+":"+"STARTED");
            ExtentTestMaganer.startTest(iTestResult.getMethod().getMethodName(),"Test Başladı");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log4jManager.info(iTestResult.getMethod().getMethodName()+":"+"PASSED");
        Log4jManager.info("==============="+"Logging ended for"+" "+iTestResult.getMethod().getMethodName()+"==================");
        ExcelMaganer.setCellData("PASSED", ExcelMaganer.rowNumber, ExcelMaganer.columnNumber);
        ExtentTestMaganer.getTest().log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ExtentTestMaganer.getTest().log(Status.FAIL, "Test Failed");
        try
        {
            TakesScreenshot ts=(TakesScreenshot)driver;
            File source=ts.getScreenshotAs(OutputType.FILE);

            try{
                FileHandler.copy(source, new File("./ScreenShots/"+iTestResult.getName()+".png"));
                System.out.println("Screenshot taken");
            }
            catch (Exception e){
                System.out.println(e.getMessage());

            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        ExcelMaganer.setCellData("FAILED", 1, 3);
        Log4jManager.info(iTestResult.getMethod().getMethodName()+":"+"FAILED");
        Log4jManager.error("Falied error thrown", iTestResult.getThrowable());
        Log4jManager.info("==============="+"Logging ended for"+" "+iTestResult.getMethod().getMethodName()+"==================");

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        iTestContext.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentManager.extentReports.flush();
    }
}
