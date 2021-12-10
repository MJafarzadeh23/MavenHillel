package UIDemoTests;

import PageObjectPageFactory.GooglePages.GoogleSearchMainPage;
import PageObjectPageFactory.GooglePages.GoogleSearchResultsPage;
import org.testng.annotations.Test;

public class GoogleSearchTest extends BaseBrowserTest {

    String googleUrl = "https://www.google.com/";
    String text = "iphone kyiv buy";
    String desiredUrl = "https://allo.ua";
    String farDesiredUrl = "https://lun.ua";
    GoogleSearchMainPage googleSearchMainPage;
    GoogleSearchResultsPage googleSearchResultsPage;

    @Test
    public void googleTextSearchTest() {
        driver.get(googleUrl);
        googleSearchMainPage = new GoogleSearchMainPage(driver);
        googleSearchResultsPage = googleSearchMainPage.setInput(text);
        googleSearchResultsPage.checkForResult(desiredUrl);
    }
}
