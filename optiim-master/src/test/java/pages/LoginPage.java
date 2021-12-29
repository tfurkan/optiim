package pages;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver, WebDriverWait wait, SoftAssert sa) {
        super(driver, wait, sa);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="txtUserName")
    public WebElement eMailField;
    @FindBy(id="txtPassword")
    public WebElement passwordField;
    @FindBy(id = "btnLogin")
    public WebElement btnLogin;
    @FindBy(id = "btnEmailSelect")
    public WebElement btnSecondLogin;


    public void clickSignIn(){
        click(btnLogin);
    }
    public void clickSecondSignIn(){
        click(btnSecondLogin);
    }

    public void writeEMail(String eMail){
        writeText(eMailField, eMail);
    }
    public void writePassword(String pwd){
        writeText(passwordField, pwd);
    }

    public LoginPage oneWayLogin(XSSFRow row) throws InterruptedException {
        writeText(eMailField, row.getCell(1).toString());
        click(btnLogin);
        writeText(passwordField, row.getCell(2).toString());
        click(btnSecondLogin);
        softAssertText(driver.getCurrentUrl(),"https://www.hepsiburada.com/");
        return this;
    }
}
