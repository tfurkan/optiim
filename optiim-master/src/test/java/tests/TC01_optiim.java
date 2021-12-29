package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utilities.ExcelMaganer;

@Listeners(utilities.Listeners.class)
public class TC01_optiim extends BaseTest{

    HomePage homePage;
    LoginPage loginPage;
    ProductsPage productsPage;
    MyFavoritesPage myFavoritesPage;
    BasketPage basketPage;
    BasePage basePage;


    @Test (priority = 0)
    public void checkBasket() throws InterruptedException {
        homePage = new HomePage(driver, wait, sa);
        loginPage = new LoginPage(driver, wait, sa);
        productsPage = new ProductsPage(driver, wait, sa);
        myFavoritesPage = new MyFavoritesPage(driver, wait, sa);
        basketPage = new BasketPage(driver, wait, sa);
        basePage = new BasePage(driver,wait,sa);

        homePage.goToHepsiBurada()
                .clickSignIn();
        loginPage.oneWayLogin(ExcelMaganer.getRowData(1));
        homePage.searchProduct("samsung")
                .clickMenuCepTelefonu()
                .assertSearched("samsung")
                .scrollDown("sayfa2")
                .clickProduct();
        String productName = productsPage.clickLikeAndGetProductName();
        productsPage.assertLikedPopup("Ürün listenize eklendi.")
                .clickMyFavorites();
        myFavoritesPage.assertLikedProduct(productName)
                .clickAddToBasket(productName)
                .assertAddedBasket("Ürün sepete eklendi")
                .clickBasket();
        basketPage.findElementAndDelete(productName)
                .handleDeletePopup()
                .assertDeletedProduct(productName);
        basePage.softAssertAll();
        homePage.saveTestResult(1,3);
    }
}
