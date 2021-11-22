package UIPages.rozetkaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class RozetkaComparisonPage {

    WebDriver driver;

    By comparingProducts = By.cssSelector("[class='product ng-star-inserted']");

    public RozetkaComparisonPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getComparingProductsList() throws InterruptedException {
        Thread.sleep(1200);
        //new RozetkaProductsPage(driver).waitForJsToLoad();
        List<WebElement> comparingProductsList = driver.findElements(comparingProducts);
        return comparingProductsList;
    }

    public List<String> getProductsTitle() throws InterruptedException {
        Thread.sleep(1200);
        //new RozetkaProductsPage(driver).waitForJsToLoad();
        List<String> productsTitleList = new ArrayList<>();
        for (WebElement prodTitle : getComparingProductsList()) {
            String productTitle = prodTitle.findElement(By.cssSelector(" a")).getText();
            productsTitleList.add(productTitle);
        }
        return productsTitleList;
    }
    public List<Double> getProductsPrice() throws InterruptedException {
        Thread.sleep(1200);
        //new RozetkaProductsPage(driver).waitForJsToLoad();
        List<Double> productsPriceList = new ArrayList<>();
        for (WebElement prodPrice : getComparingProductsList()) {
            String productPrice = prodPrice.findElement(By.cssSelector(" [class*='product__price product__price']")).getText();
            productsPriceList.add(Double.valueOf(getTransformedPrice(productPrice)));
        }
        return productsPriceList;
    }

    private Double getTransformedPrice(String pricesBlock) {
        String[] arr = pricesBlock.split("₴");
        double desiredPrice = Double.valueOf(arr[1].trim().replaceAll(" ", ""));
        return desiredPrice;
    }
}
