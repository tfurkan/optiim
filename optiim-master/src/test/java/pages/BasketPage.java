package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utilities.Log4jManager;


public class BasketPage extends BasePage{
    public BasketPage(WebDriver driver, WebDriverWait wait, SoftAssert sa) {
        super(driver, wait, sa);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(@class,'delete_product')]")
    public WebElement btnDelete;
    @FindBy(xpath = "//span[contains(text(),'Ürün sepetinizden silinmiştir')]")
    public WebElement txtDeleted;
    @FindBy(xpath = "//*[@id=\"dd9b1d37-10d0-4ec6-9170-7b7671a53117\"]/div/div/div[1]/div[3]/div/div/div/button[2]")
    public WebElement deletePopup;


    public BasketPage findElementAndDelete(String str){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'"+str+"')]")));
        actions.perform();
        click(btnDelete);
        return this;
    }
    public BasketPage handleDeletePopup(){
        try {
            deletePopup.click();
            Log4jManager.info("Bazen Açılan Popup Kapatıldı");
        }catch (Exception e){
            Log4jManager.warn("Popup Açılmadı");
        }
        return this;
    }
    public BasketPage assertDeletedProduct(String str){
        assertTrue(txtDeleted);
        try {
           driver.findElement(By.xpath("//a[contains(text(),'"+str+"')]"));
            Assert.assertTrue(false);
            Log4jManager.info("Ürünün Silindiği Görüldü");
        }catch (NoSuchElementException e){
            Log4jManager.info("Ürünün Silinmediği Görüldü");
            Assert.assertTrue(true);
        }
        return this;
    }
}
