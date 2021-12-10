package PageObjectPageFactory.GooglePages;

import additional.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.ArrayList;
import java.util.List;

public class GoogleSearchResultsPage extends BasePage {

    @FindBys(@FindBy(xpath = "//cite"))
    private List<WebElement> resultsList;

    @FindBy(xpath = "//span[contains(@style,'display:block;margin-left')]")
    private WebElement nextPageButton;

    @FindBy(xpath = "//td[@class='YyVfkd']")
    private WebElement currentPage;

    public GoogleSearchResultsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkForResult(String expectedUrl) {
        List<String> resultsList = getSearchResults();
        while(!hasDesiredResult(resultsList, expectedUrl)) {
            nextPageButton.click();
            if (Integer.valueOf(currentPage.getText()) == 6) {
                System.out.println(expectedUrl + " not found on first 5 pages");
                return;
            }
            resultsList = getSearchResults();
        }
        System.out.println(expectedUrl + " found on " + currentPage.getText() + " page");
    }

    private List<String> getSearchResults() {
        List<String> strSearchResultsList = new ArrayList<>();
        for (WebElement result : resultsList) {
            String strResult = result.getText();
            strSearchResultsList.add(strResult.split(" ")[0]);
        }
        //System.out.println(strSearchResultsList.size());
        return strSearchResultsList;
    }

    private boolean hasDesiredResult(List<String> resultsList, String expectedUrl) {
        for (String result : resultsList) {
            if (result.contains(expectedUrl)) {
                return true;
            }
        }
        return false;
    }
}
