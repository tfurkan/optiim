package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.Set;

public class ProductsPage extends BasePage{
    public ProductsPage(WebDriver driver, WebDriverWait wait, SoftAssert sa) {
        super(driver, wait, sa);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='favoriteContainer']//*[contains(text(),'Beğen')]")
    public WebElement btnLike;
    @FindBy(xpath = "//*[@class='hb-toast-content-holder']//*[@class='hb-toast-text']")
    public WebElement popupLiked;
    @FindBy(id = "product-name")
    public WebElement productName;
    @FindBy(id = "myAccount")
    public WebElement myAccount;
    @FindBy(xpath = "//a[contains(text(),'Beğendiklerim')]")
    public  WebElement myFavorites;

    public String clickLikeAndGetProductName() throws InterruptedException {
        Set<String> handles = driver.getWindowHandles();
        String currentHandle = driver.getWindowHandle();
        for (String handle : handles) {

            if (!handle .equals(currentHandle))
            {
                driver.switchTo().window(handle);
            }
        }
        btnLike.click();
        return readText(productName);
    }
    public ProductsPage assertLikedPopup(String str) {
        assertTrue(popupLiked);
        assertText(popupLiked, str);
        return this;
    }
    public ProductsPage clickMyFavorites(){
        Actions actions = new Actions(driver);
        actions.moveToElement(myAccount);
        actions.perform();
        click(myFavorites);
        return this;
    }
}
