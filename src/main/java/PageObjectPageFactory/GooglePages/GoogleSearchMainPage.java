package PageObjectPageFactory.GooglePages;

import additional.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleSearchMainPage extends BasePage {

    @FindBy(xpath = "//input[@class='gLFyf gsfi']")
    private WebElement searchField;

    @FindBy(xpath = "//center/input")
    private WebElement submitButton;


    public GoogleSearchMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public GoogleSearchResultsPage setInput(String text) {
        searchField.sendKeys(text);
        submitButton.click();
        return new GoogleSearchResultsPage(driver);
    }
}
