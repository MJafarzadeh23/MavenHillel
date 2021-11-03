package UIDemoTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BrowserDevTestHW17 {
// Создать несколько positive и negative тест-кейсов для тестирования манипуляций с веб драйвером.
// Выберите себе любые сайты для тестирования.
//
//Открыть ссылку на сайт и проверить, что title соответствует ожидаемому.
//Открыть ссылку и проверить, что тайтл не соответствует ожидаемому (проверка на невалидный тайтл, негативный кейс).
//Открыть сначала ссылку из предыдущих тестов, затем открыть новую вкладку, получить урлу и сравнить с урлой вашего сайта (негативный кейс).
//Открыть сначала ссылку из предыдущих тестовб потом в новой вкладке открыть другой сайт, проверить что урл соответствует тому что вы открыли вторым.
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void browserActionsTest() {
        String googleMeetURL = "https://meet.google.com/";
        String wikipediaURL = "https://en.wikipedia.org/wiki/Main_Page";

        driver.get(googleMeetURL);
        //Assert.assertEquals(driver.getTitle(), "Google Meet","This is some other page...");
        Assert.assertTrue(driver.getTitle().contains("Google Meet"),"This is some other page...");

        // title="Wikipedia, the free encyclopedia"
        driver.get(wikipediaURL);
        Assert.assertNotEquals(driver.getTitle(),"Wikipedia","This is some other page...");
        Assert.assertEquals(driver.getTitle(), "Wikipedia, the free encyclopedia","This is some other page...");

        driver.get(googleMeetURL);
        String googleMeetHandle = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        Assert.assertNotEquals(driver.getCurrentUrl(), googleMeetURL, "This is some other page...");

        driver.get(wikipediaURL);  //1st LINK
        driver.switchTo().newWindow(WindowType.TAB).get("https://github.com/"); //2nd LINK
        Assert.assertEquals(driver.getCurrentUrl(),"https://github.com/","This is some other page...");
        driver.switchTo().window(googleMeetHandle);  //3rd LINK
        Assert.assertNotEquals(driver.getCurrentUrl(),wikipediaURL,"This is some other page...");   // not 1st link
        Assert.assertNotEquals(driver.getCurrentUrl(), "https://github.com/","This is some other page..."); // not 2nd link
        Assert.assertTrue(driver.getCurrentUrl().contains("meet"),"This is some other page...");    // 3rd link
    }
}
