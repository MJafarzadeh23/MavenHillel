package RozetkaTests;

import UIDemoTests.BaseBrowserTest;
import UIPages.rozetkaPages.RozetkaMainPage;
import UIPages.rozetkaPages.RozetkaProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ReadyForDeliveryTest extends BaseBrowserTest {

    String url = "https://rozetka.com.ua/";
    String samsungFilter = "Samsung";
    RozetkaMainPage rozetkaMainPage;
    RozetkaProductsPage rozetkaProductsPage;

    @Test
    public void priceVerification() throws InterruptedException {
        driver.get(url);

        rozetkaMainPage = new RozetkaMainPage(driver);
        rozetkaProductsPage = rozetkaMainPage.runSearch(samsungFilter);
        rozetkaProductsPage.clickMobilePhonesFilter();
        rozetkaProductsPage.clickReadyForDeliveryFilter();
        List<String> productAvailabilityList = rozetkaProductsPage.getProductsAvailability();
        for (String productAvailability : productAvailabilityList) {
            Assert.assertTrue(productAvailability.contains("Готов к отправке"));
        }
    }
}
