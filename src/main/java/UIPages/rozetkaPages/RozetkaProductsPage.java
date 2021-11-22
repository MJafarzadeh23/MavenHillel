package UIPages.rozetkaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RozetkaProductsPage {

    WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private JavascriptExecutor jsExecutor;

    By mobilePhonesFilter = By.cssSelector("a[class='categories-filter__link ng-star-inserted']>span:nth-child(1)");
    By applePhoneFilter = By.xpath("//label[@for='Apple']");
    By huaweiPhoneFilter = By.xpath("//label[@for='Huawei']");
    By productCard = By.cssSelector("div[class='goods-tile ng-star-inserted']");
    By productImage = By.cssSelector("a[class='goods-tile__picture ng-star-inserted']");
    By product = By.xpath("//a[@class='goods-tile__picture ng-star-inserted']/img[1]");
    By minPriceFilterField  = By.cssSelector("[formcontrolname=min]");
    By maxPriceFilterField = By.cssSelector("[formcontrolname=max]");
    By priceSubmitButton = By.xpath("//div[@class='slider-filter__inner']/button");
    By productPriceElement = By.cssSelector("span[class='goods-tile__price-value']");
    By readyForDeliveryFilter = By.xpath("//label[@for='Готов к отправке']");
    By productAvailable = By.cssSelector("div[class~='goods-tile__availability--available']");
   /* @FindBys(xpath="//a[@class='goods-tile__picture ng-star-inserted']/img[1]"))
    List<WebElement> products;*/


    public RozetkaProductsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        actions = new Actions(this.driver);
        jsExecutor = (JavascriptExecutor) this.driver;
    }

    public void clickMobilePhonesFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(mobilePhonesFilter));
        driver.findElement(mobilePhonesFilter).click();
    }

    public void clickApplePhoneFilter() {
        actions.moveToElement(driver.findElement(applePhoneFilter)).perform();
        wait.until(ExpectedConditions.elementToBeClickable(applePhoneFilter));
        driver.findElement(applePhoneFilter).click();
    }

    public void clickHuaweiPhoneFilter () {
        actions.moveToElement(driver.findElement(huaweiPhoneFilter)).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(huaweiPhoneFilter));
        driver.findElement(huaweiPhoneFilter).click();
    }

    public void clickReadyForDeliveryFilter () {
        actions.moveToElement(driver.findElement(readyForDeliveryFilter)).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(readyForDeliveryFilter));
        driver.findElement(readyForDeliveryFilter).click();
    }

    public List<String> getProductsAvailability () throws InterruptedException {
        Thread.sleep(1200);
        //waitForJsToLoad();
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productAvailable));
        List<String> productAvailabilityList = new ArrayList<>();
        for (WebElement productCard : findAllProductsCards()) {
            String productAvailability = productCard.findElement(productAvailable).getText();
            productAvailabilityList.add(productAvailability);
        }
        return productAvailabilityList;
    }

    public List<String> getProductTitle () throws InterruptedException {
        Thread.sleep(1200);
        //waitForJsToLoad();
        List<String> productTitleList = new ArrayList<>();
        for (WebElement product : findAllProductsOnPage()) {
            String productTitle = product.getAttribute("title");
            productTitleList.add(productTitle);
        }
        return productTitleList;
    }

    public List<Double> getProductPrice () throws InterruptedException {
        Thread.sleep(1200);
        //waitForJsToLoad();
        List<Double> transformedPricesList = new ArrayList<>();
        for (WebElement price : findAllProductsPricesOnPage()) {
            double transformedPrice = Double.valueOf(price.getText().trim().replaceAll(" ",""));
            transformedPricesList.add(transformedPrice);
        }
        return transformedPricesList;
    }

    public RozetkaProductDetailsPage findProductByPrice(Double priceFilterLessThan) throws InterruptedException {
        Thread.sleep(1200);
        //waitForJsToLoad();
        for (WebElement productCard : findAllProductsCards()) {
            WebElement price = productCard.findElement(productPriceElement);
            double transformedPrice = Double.valueOf(price.getText().trim().replaceAll(" ",""));
            if (transformedPrice < priceFilterLessThan) {
                productCard.findElement(productImage).click();
                break;
            }
        }
        return new RozetkaProductDetailsPage(driver);
    }

    public void performSearchByPriceFilter (String minPrice, String maxPrice) {
        setMinPriceFilterField(minPrice);
        setMaxPriceFilterField(maxPrice);
        wait.until(ExpectedConditions.elementToBeClickable(priceSubmitButton));
        driver.findElement(priceSubmitButton).click();
    }

    protected boolean waitForJsToLoad() {
        final String loadJsScript = "return document.readyState";
        //final String loadJQueryScript = "return jQuery.active==0";

        ExpectedCondition<Boolean> jsLoad = driver ->
                jsExecutor.executeScript(loadJsScript).toString().equals("complete");
        /*ExpectedCondition<Boolean> jQueryLoad = driver ->
                (Boolean) (jsExecutor.executeScript(loadJQueryScript));*/
        return wait.until(jsLoad)/* && wait.until(jQueryLoad)*/;
    }

    private List<WebElement> findAllProductsCards () throws InterruptedException {
        Thread.sleep(1200);
        //waitForJsToLoad();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productCard));
        List<WebElement> cardsList = driver.findElements(productCard);
        System.out.println(cardsList.size());
        return cardsList;
    }

    private List<WebElement> findAllProductsOnPage () throws InterruptedException {
        Thread.sleep(1200);
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(product));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(product), "title"));
        List<WebElement> productsList = driver.findElements(product);
        System.out.println(productsList.size());
        return productsList;
    }

    private List<WebElement> findAllProductsPricesOnPage () throws InterruptedException {
        waitForJsToLoad();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productPriceElement));
        //Thread.sleep(1200);
        List<WebElement> pricesList = driver.findElements(productPriceElement);
        System.out.println(pricesList.size());
        return pricesList;
    }

    private void setMinPriceFilterField(String minPriceFilter) {
        actions.moveToElement(driver.findElement(minPriceFilterField)).perform();
        driver.findElement(minPriceFilterField).clear();
        driver.findElement(minPriceFilterField).sendKeys(minPriceFilter);
    }

    private void setMaxPriceFilterField(String maxPriceFilter) {
        actions.moveToElement(driver.findElement(maxPriceFilterField)).perform();
        driver.findElement(maxPriceFilterField).clear();
        driver.findElement(maxPriceFilterField).sendKeys(maxPriceFilter);
    }
}