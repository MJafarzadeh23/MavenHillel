package UIPages.rozetkaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class RozetkaComputersNotebooksSectionPage {

    WebDriver driver;
    private Actions actions;

    By monitors = By.cssSelector("a[href*='monitors']");

    public RozetkaComputersNotebooksSectionPage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(this.driver);
    }

    public RozetkaProductsPage clickMonitors() {
        actions.moveToElement(driver.findElement(monitors)).perform();
        driver.findElement(monitors).click();
        return new RozetkaProductsPage(driver);
    }
}
