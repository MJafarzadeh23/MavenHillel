package PageObjectPageFactory.BookingPages;

import additional.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class BookingSearchResultsPage extends BasePage {

    @FindBy(xpath = "//input[@data-component='search/destination/input-placeholder']")
    private WebElement destinationField;

    @FindBy(xpath = "//div[@data-placeholder='Check-in date']")
    private WebElement checkInDisplayed;

    @FindBy(xpath = "//div[@data-placeholder='Check-out date']")
    private WebElement checkOutDisplayed;

    @FindBy(xpath = "//div[@data-filters-item='review_score:review_score=90']/label")
    private WebElement superbScoreFilter;

    @FindBy(xpath = "//div[@data-testid='overlay-message-title']")
    private WebElement overlayMessage;

    @FindBy(xpath = "//div[@data-testid='property-card']/div")
    private WebElement propertyContainer;

    @FindBys(@FindBy(xpath = "//div[@data-testid='review-score']/div[1]"))
    private List<WebElement> scoresList;

    public BookingSearchResultsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getDestinationValue() {
        return destinationField.getAttribute("value");
    }

    public String getInDateDisplayed() {
        return checkInDisplayed.getText();
    }

    public String getOutDateDisplayed() {
        return checkOutDisplayed.getText();
    }

    public void selectSuperbScore() {
        actions.moveToElement(driver.findElement(By.xpath("//div[@data-filters-group='review_score']"))).perform();
        superbScoreFilter.click();
    }

    public List<Double> getHomeScoresList() {
        wait.until(ExpectedConditions.invisibilityOf(overlayMessage));
        wait.until(ExpectedConditions.elementToBeClickable(propertyContainer));

        List<Double> strHomeScoreList = new ArrayList<>();
        for(WebElement homeScore : scoresList) {
            double strHomeScore = Double.valueOf(homeScore.getText());
            strHomeScoreList.add(strHomeScore);
            //System.out.println(strHomeScore);
        }
        return strHomeScoreList;
    }
}
