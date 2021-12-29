package tests;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import utilities.ExcelMaganer;
import utilities.ExtentManager;

import java.io.File;
import java.util.concurrent.TimeUnit;



public class BaseTest {
    public static WebDriver driver;
    public WebDriverWait wait;
    public SoftAssert sa;

    String browser = "chrome";

    @BeforeTest
    public void setupTestData(){

        System.out.println("*********Setup Test Level Data*********");
        ExcelMaganer.setExcelFileSheet("LoginData");
    }

    @BeforeMethod
    public void setUp() {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 15);
            sa = new SoftAssert();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
            wait = new WebDriverWait(driver, 15);
            sa = new SoftAssert();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 15);
            sa = new SoftAssert();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}


