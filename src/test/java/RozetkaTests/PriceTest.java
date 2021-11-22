package RozetkaTests;

import UIDemoTests.BaseBrowserTest;
import UIPages.rozetkaPages.RozetkaMainPage;
import UIPages.rozetkaPages.RozetkaProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PriceTest extends BaseBrowserTest {

    String url = "https://rozetka.com.ua/";
    String samsungFilter = "Samsung";
    String  minPrice = "5000";
    String  maxPrice = "15000";
    RozetkaMainPage rozetkaMainPage;
    RozetkaProductsPage rozetkaProductsPage;

    @Test
    public void priceVerification() throws InterruptedException {
        driver.get(url);

        rozetkaMainPage = new RozetkaMainPage(driver);
        rozetkaProductsPage = rozetkaMainPage.runSearch(samsungFilter);
        rozetkaProductsPage.clickMobilePhonesFilter();
        rozetkaProductsPage.performSearchByPriceFilter(minPrice, maxPrice);
        List<Double> productsPrices = rozetkaProductsPage.getProductPrice();
        for (Double productPrice : productsPrices) {
            Assert.assertTrue(productPrice >= Double.valueOf(minPrice) && productPrice <= Double.valueOf(maxPrice));
        }
    }
}
