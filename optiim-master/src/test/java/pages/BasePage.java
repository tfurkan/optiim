package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class BasePage {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static SoftAssert sa;
    public BasePage (WebDriver driver, WebDriverWait wait, SoftAssert sa){
        this.driver = driver;
        this.wait = wait;
        this.sa = sa;
    }

    public void click(WebElement clickElement){
        wait.until(ExpectedConditions.elementToBeClickable(clickElement));
        clickElement.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void writeText(WebElement sendKeysElement, String text){
        wait.until(ExpectedConditions.elementToBeClickable(sendKeysElement));
        sendKeysElement.sendKeys(text);
    }

    public void softAssertText(String actual, String expectedText){

        sa.assertEquals(actual, expectedText);
    }
    public void softAssertAll(){
        sa.assertAll();
    }


    public String readText(WebElement getTextElement){
        wait.until(ExpectedConditions.visibilityOf(getTextElement));
        return getTextElement.getText();
    }


    public  void assertText(WebElement actual, String expectedText){
        wait.until(ExpectedConditions.visibilityOf(actual));
        Assert.assertEquals(actual.getText(), expectedText);
    }
    public  void assertTrue(WebElement actual){
        wait.until(ExpectedConditions.visibilityOf(actual));
        Assert.assertTrue(actual.isDisplayed());
    }

    
}
