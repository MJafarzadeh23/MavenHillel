package RozetkaTests;

import UIDemoTests.BaseBrowserTest;
import UIPages.rozetkaPages.RozetkaMainPage;
import UIPages.rozetkaPages.RozetkaProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ManufacturerTest extends BaseBrowserTest {

    String url = "https://rozetka.com.ua/";
    String samsungFilter = "Samsung";
    String appleFilter = "Apple";
    String huaweiFilter = "Huawei";
    RozetkaMainPage rozetkaMainPage;
    RozetkaProductsPage rozetkaProductsPage;

    @Test
    public void manufacturerVerification () throws InterruptedException {
        driver.get(url);

        rozetkaMainPage = new RozetkaMainPage(driver);
        rozetkaProductsPage = rozetkaMainPage.runSearch(samsungFilter);
        rozetkaProductsPage.clickMobilePhonesFilter();
        rozetkaProductsPage.clickApplePhoneFilter();
        rozetkaProductsPage.clickHuaweiPhoneFilter();
        List<String> productTitleList = rozetkaProductsPage.getProductTitle();
        for (String productTitle : productTitleList) {
            Assert.assertTrue(productTitle.contains(samsungFilter) || productTitle.contains(appleFilter) || productTitle.contains(huaweiFilter));
        }
    }
}
