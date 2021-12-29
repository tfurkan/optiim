package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utilities.Log4jManager;

public class MyFavoritesPage extends BasePage{
    public MyFavoritesPage(WebDriver driver, WebDriverWait wait, SoftAssert sa) {
        super(driver, wait, sa);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//div[contains(text(),'Sepete ekle')]")
    public WebElement btnAddToBasket;
    @FindBy(xpath = "//*[@class='hb-toast-content-holder']//*[@class='hb-toast-text']")
    public WebElement popupAddedToBasket;
    @FindBy(xpath = "//span[contains(text(),'Sepetim')]")
    public WebElement btnBasket;


    public MyFavoritesPage assertLikedProduct(String str){
        assertText(driver.findElement(By.xpath("//*[@data-test-id='product-card-name' and contains(text(),'"+str+"')]")), str);
        return this;
    }
    public MyFavoritesPage clickAddToBasket(String str){
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.xpath("//*[@data-test-id='product-card-name' and contains(text(),'"+str+"')]")));
            actions.perform();
            click(btnAddToBasket);
            Log4jManager.info("Sepete Ekle Butonuna Basıldı");
        } catch (Exception e) {
            Log4jManager.error("Sepete Ekle Butonuna Basılamadı",e);
            throw(e);
        }
        return this;
    }
    public MyFavoritesPage assertAddedBasket(String str) {
        assertTrue(popupAddedToBasket);
        assertText(popupAddedToBasket, str);
        return this;
    }
    public MyFavoritesPage clickBasket(){
        click(btnBasket);
        return this;
    }
}
