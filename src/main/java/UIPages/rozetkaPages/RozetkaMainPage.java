package UIPages.rozetkaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RozetkaMainPage {

    WebDriver driver;
    private WebDriverWait wait;

    By searchField = By.cssSelector("[name=search]");
    By searchButton = By.cssSelector(".button_color_green");
    By computersNotebooksFilter = By.cssSelector("div[class*='menu-wrapper_state_static'] a[href*='computers-notebooks']");

    public RozetkaMainPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    public RozetkaProductsPage runSearch(String searchText) {
        enterTextInSearchField(searchText);
        clickSearchButton();
        return new RozetkaProductsPage(driver);
    }

    public RozetkaComputersNotebooksSectionPage clickComputersNotebooksFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(computersNotebooksFilter));
        driver.findElement(computersNotebooksFilter).click();
        return new RozetkaComputersNotebooksSectionPage(driver);
    }

    private void enterTextInSearchField(String searchText) {
        driver.findElement(searchField).sendKeys(searchText);
    }

    private void clickSearchButton() {
        driver.findElement(searchButton).click();
    }
}
