package UIDemoTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class EasyjetTest {
    // 1) Navigate to airline site
    //2) Input "Berlin" in FROM field
    //3) Input "London" in TO field
    //4) Choose dates: 15-19 Dec
    //5) Choose 2 adults
    //6) Click search
    //7) Verify that there are 2 blocks: Berlin-London, London-Berlin
    //8) Verify dates are 15 Dec and 19 Dec accordingly
    protected WebDriver driver;

    @BeforeTest
    public void setUp() {
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://www.easyjet.com";
        //Configuration.baseUrl = "https://en.tripmydream.com/";
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\IdeaProjects\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();
    }

    @Test
    public void selectTickets() {
        String departureCity = "Berlin";
        String arrivalCity = "London";

        open("/en");

        $("[id='ensOpenModal']").click();

        $("[aria-label='From Airport']").click();
        $("[aria-label='From Airport']").setValue(departureCity);
        $(By.xpath("//li[@class='ui-menu-item']//strong")).shouldHave(text(departureCity)).click();
        $("[aria-label='From Airport']").shouldHave(value(departureCity));
    }
}
