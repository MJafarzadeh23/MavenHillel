package UIDemoTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumDevTest {

    @Test
    public void driverActionsTest() {
        WebDriverManager.firefoxdriver().setup();
        /*System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\IdeaProjects\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();*/

        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com/");
        String googleTab = driver.getWindowHandle();
        /*Assert.assertEquals(driver.getTitle(), "Selenium","This is some other page...");*/
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.selenium.dev/");
        Assert.assertEquals(driver.getTitle(), "Selenium","This is some other page...");

        driver.switchTo().window(googleTab);
        Assert.assertEquals(driver.getTitle(), "Google");

        driver.quit();
    }
}
