package UIDemoTests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class EasyjetTest {
    //protected WebDriver driver;

    @BeforeTest
    public void setUp() {
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://en.tripmydream.com/";
        /*System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\IdeaProjects\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();*/
    }

    @Test
    public void selectTickets() throws InterruptedException {
        String departureCity = "Berlin";
        String arrivalCity = "London";

        open("");

        $x("//div[@class='s-form__place-btn hc-ac-tag-close']").click();

        $x("//input[starts-with(@placeholder,'Origin city')]").setValue(departureCity);
        $x("//label[starts-with(@class,'autocomplete__item')]").shouldHave(text(departureCity)).click();

        $x("//input[starts-with(@placeholder,'Destination city')]").setValue(arrivalCity);
        $x("//span[@class='autocomplete__city']").shouldHave(text(arrivalCity)).click();

        $("[data-date='2021-12-15']").click();
        $("[data-date='2021-12-19']").hover().click();

        $("[class='select__head-people hc-ps-passenger']").click();

        SelenideElement adultPassengersBlock = $x("//div[@class='people__row hc-ps-row']");
        adultPassengersBlock.shouldHave(text("Adult")).$x(".//button[@class='people__btn people__btn_plus hc-ps-row-btn']").click();
        adultPassengersBlock.$x(".//div[@class='people__cell hc-ps-row-val']").shouldHave(text("2"));

        $x("//div[@class='presearch__cell']/div").click();

        switchTo().window(1);

        if($x("//div[starts-with(@class,'popup__close')]").isDisplayed()) {
            $x("//div[starts-with(@class,'popup__close')]").click();
        }

        $("[class='flight__price-btn-text']").shouldBe(visible);

        SelenideElement flightsContainer = $x("(//div[@class='flight__content'])[1]");
        String forwardRoute = flightsContainer.$$x("./div[@class='flight__row'][1]//span[@class='hidden-mob']").stream()
                .map(e -> e.getText())
                .collect(Collectors.joining(""));

        String backRoute = flightsContainer.$$x("./div[@class='flight__row'][2]//span[@class='hidden-mob']").stream()
                .map(e -> e.getText())
                .collect(Collectors.joining(""));

        Assert.assertEquals(forwardRoute, departureCity + " () —" + arrivalCity + " ()");
        Assert.assertEquals(backRoute, arrivalCity + " () —" + departureCity + " ()");

        flightsContainer.$$x(".//div[@class='flight__info-date hidden-mob']/div[@class='flight__info-dateValue']")
                .shouldHave(CollectionCondition.texts("15 Dec, Wed", "19 Dec, Sun"));
    }
}
