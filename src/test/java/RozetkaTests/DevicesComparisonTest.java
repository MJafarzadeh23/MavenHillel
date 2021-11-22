package RozetkaTests;

import UIDemoTests.BaseBrowserTest;
import UIPages.rozetkaPages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DevicesComparisonTest extends BaseBrowserTest {

    String url = "https://rozetka.com.ua/";
    RozetkaMainPage rozetkaMainPage;
    RozetkaProductsPage rozetkaProductsPage;
    RozetkaComputersNotebooksSectionPage rozetkaComputersNotebooksSectionPage;
    RozetkaProductDetailsPage rozetkaProductDetailsPage;
    RozetkaComparisonPage rozetkaComparisonPage;

    @Test
    public void devicesComparisonVerification() throws InterruptedException {
        driver.get(url);

        rozetkaMainPage = new RozetkaMainPage(driver);
        rozetkaComputersNotebooksSectionPage = rozetkaMainPage.clickComputersNotebooksFilter();
        rozetkaProductsPage = rozetkaComputersNotebooksSectionPage.clickMonitors();
        rozetkaProductDetailsPage = rozetkaProductsPage.findProductByPrice(5000.0);
        rozetkaProductDetailsPage.clickCompareButton();

        String productTitle1 = rozetkaProductDetailsPage.getProductTitle();
        Double productPrice1 = rozetkaProductDetailsPage.getProductPrice();
        System.out.println(productTitle1 + " " + productPrice1);
        Assert.assertFalse(rozetkaProductDetailsPage.isCartEmpty());
        Assert.assertEquals(rozetkaProductDetailsPage.getCartLabelText(), "1");

        rozetkaProductsPage = rozetkaProductDetailsPage.backToProductsPage();
        rozetkaProductsPage.findProductByPrice(productPrice1);
        rozetkaProductDetailsPage.clickCompareButton();

        String productTitle2 = rozetkaProductDetailsPage.getProductTitle();
        Double productPrice2 = rozetkaProductDetailsPage.getProductPrice();
        System.out.println(productTitle2 + " " + productPrice2);
        Assert.assertEquals(rozetkaProductDetailsPage.getCartLabelText(), "2");

        rozetkaComparisonPage = rozetkaProductDetailsPage.proceedToComparisonPage();

        Assert.assertEquals(rozetkaComparisonPage.getComparingProductsList().size(), 2);

        List<String> productTitleList = rozetkaComparisonPage.getProductsTitle();
        for (String productTitle : productTitleList) {
            Assert.assertTrue(productTitle.contains(productTitle1) || productTitle.contains(productTitle2));
        }

        List<Double> productPriceList = rozetkaComparisonPage.getProductsPrice();
        for (Double productPrice : productPriceList) {
            Assert.assertTrue(productPrice.equals(productPrice1) || productPrice.equals(productPrice2));
        }
    }
}
