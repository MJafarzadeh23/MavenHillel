package UIPages.rozetkaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RozetkaProductDetailsPage {

    WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    By compareButton = By.cssSelector("[class*='compare-button']");
    By cartCounter = By.cssSelector("span.counter");
    By productTitle = By.cssSelector("h1.product__title");
    By productPrice = By.cssSelector("p[class^='product-prices__big']");
    By headerCompareButton = By.cssSelector("[aria-label='Списки сравнения']");
    By comparisonList = By.cssSelector("[class='comparison-modal__link']");
    By filterSidebar = By.cssSelector(".sidebar.ng-star-inserted");

    public RozetkaProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(this.driver);
    }

    public void clickCompareButton() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(filterSidebar));
        driver.findElement(compareButton).click();
    }

    public boolean isCartEmpty() {
        return driver.findElements(cartCounter).isEmpty();
    }

    public String getCartLabelText() {
        return driver.findElement(cartCounter).getText();
    }

    public String getProductTitle() {
        return driver.findElement(productTitle).getText();
    }

    public Double getProductPrice() {
        double transformedPrice = Double.valueOf(driver.findElement(productPrice).getText().trim().replaceAll("[\\s\\₴]",""));
        return transformedPrice;
    }

    public RozetkaProductsPage backToProductsPage() {
        driver.navigate().back();
        return new RozetkaProductsPage(driver);
    }

    public RozetkaComparisonPage proceedToComparisonPage() {
        clickComparisonHeader();
        clickComparisonListCase();
        return new RozetkaComparisonPage(driver);
    }

    private void clickComparisonHeader() {
        driver.findElement(headerCompareButton).click();
    }

    private void clickComparisonListCase() {
        driver.findElement(comparisonList).click();
    }
}
