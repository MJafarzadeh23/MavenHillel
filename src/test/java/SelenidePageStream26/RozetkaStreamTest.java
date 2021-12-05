package SelenidePageStream26;

import SelenidePageStream.RozetkaSelenidePages.RozetkaMainPageSelenide;
import SelenidePageStream.RozetkaSelenidePages.RozetkaProductListingPageSelenide;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class RozetkaStreamTest {

    //protected WebDriver driver;
    RozetkaMainPageSelenide mainPage = new RozetkaMainPageSelenide();
    RozetkaProductListingPageSelenide productListingPage;

    @BeforeClass
    public void setUp() {
        Configuration.baseUrl = "https://rozetka.com.ua";
       /* System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\IdeaProjects\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();*/
    }

    @Test
    public void streamTests() throws InterruptedException {

        productListingPage = mainPage.open().runSearch("lenovo");

        List<Integer> prices = productListingPage.productsPricesList();
        prices.forEach(System.out::println);
        Assert.assertEquals(prices.size(), 36);

        List<String> productsTitles = productListingPage.productsTitlesList();
        productsTitles.stream().forEach(product -> System.out.println(product));
        Assert.assertEquals(prices.size(), 36);

        List<SelenideElement> elementsByPrice = productListingPage.productElementsByPrice(8000);
        elementsByPrice.stream().forEach(product -> System.out.println(product));

        long productsQuantityByPrice = productListingPage.productsQuantityByPrice(8000);
        System.out.println("A q-ty of products with price < 8000: " + productsQuantityByPrice);

        double maxPrice = productListingPage.maxProductPrice();
        System.out.println("Max price found on page: " + maxPrice + " UAH");

        List<Integer> pricesByTitle = productListingPage.pricesByTitle("IdeaPad");
        System.out.println("List of prices for those elements that have partial text 'IdeaPad' in their title:");
        pricesByTitle.forEach(System.out::println);
    }
}
