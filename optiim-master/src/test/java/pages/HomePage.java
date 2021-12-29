package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utilities.ExcelMaganer;
import utilities.Log4jManager;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver, WebDriverWait wait, SoftAssert sa) {
        super(driver, wait,sa);
        PageFactory.initElements(driver, this);
    }

    String baseURL = "https://www.hepsiburada.com/";

    @FindBy(xpath = "//span[contains(text(),'Giriş Yap')]")
    public WebElement btnGirisYap;
    @FindBy(id = "login")
    public WebElement btnSubGirisYap;
    @FindBy(xpath = "//input[contains(@type,'text')]")
    public WebElement searchBar;
    @FindBy(xpath = "//div[contains(text(),'ARA')]")
    public WebElement btnAra;
    @FindBy(xpath = "//*[@data-test-id='tree-node-title'and contains(text(),'Telefon')]")
    public WebElement btnNodeTelefon;
    @FindBy(xpath = "//*[@data-test-id='tree-node-title'and text()='Cep Telefonu']")
    public WebElement btnNodeCepTelefonu;
    @FindBy(xpath = "//*[@class='searchResultSummaryBar-bold'][1]")
    public WebElement txtToBeChecked;
    public static final String index = "18";
    @FindBy(xpath = "//li[@class='productListContent-item' and @id='i"+index+"']/div[@type='comfort' and @data-test-id='product-card-container']")
    public WebElement btnSelectProduct;

    public HomePage goToHepsiBurada(){
        try {
            driver.get(baseURL);
            softAssertText(driver.getCurrentUrl(), baseURL);
            Log4jManager.info("Websitesi Açıldı");
        } catch (Exception e) {
            Log4jManager.error("Websitesi açılmadı",null);
            throw(e);
        }
        return this;
    }
    public HomePage searchProduct(String str){
        click(searchBar);
        writeText(searchBar, str);
        click(btnAra);
        return this;
    }
    public HomePage clickSignIn() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(btnGirisYap);
        actions.perform();
        click(btnSubGirisYap);
        String[] url = driver.getCurrentUrl().split("(?<=.com/)");
        softAssertText(url[0],"https://giris.hepsiburada.com/");
        return this;
    }
    public HomePage clickMenuCepTelefonu(){
        click(btnNodeTelefon);
        click(btnNodeCepTelefonu);
        return this;
    }
    public HomePage assertSearched(String str){
        assertText(txtToBeChecked, str);
        Log4jManager.info("Aranan Ürün Doğrulandı");
        return this;
    }
    public HomePage scrollDown(String str) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String pageTwo = "";
        while(!(pageTwo.equals("sayfa=2"))){
            pageTwo = driver.getCurrentUrl();
            js.executeScript("window.scrollBy(0,250)","");
            pageTwo = pageTwo.substring(pageTwo.lastIndexOf('&') + 1);
            Thread.sleep(750);
        }
        softAssertText(driver.getCurrentUrl().substring(driver.getCurrentUrl().lastIndexOf('&') + 1), str);
        return this;
    }

    public HomePage clickProduct() throws InterruptedException {
        try {
            click(btnSelectProduct);
            Log4jManager.info("Ürün Seçildi");
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Log4jManager.error("Ürün Seçilemedi",null);
            throw(e);
        }
        return this;
    }
    public HomePage saveTestResult(int row, int col){
        ExcelMaganer.rowNumber = row;
        ExcelMaganer.columnNumber = col;
        return this;
    }
}
