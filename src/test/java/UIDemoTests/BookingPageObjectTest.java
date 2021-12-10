package UIDemoTests;

import PageObjectPageFactory.BookingPages.BookingMainPage;
import PageObjectPageFactory.BookingPages.BookingSearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class BookingPageObjectTest extends BaseBrowserTest {

    String url = "https://www.booking.com";
    String destination = "London";
    BookingMainPage bookingMainPage;
    BookingSearchResultsPage bookingSearchResultsPage;

    // test valid for UK format
    @Test
    public void checkSearchTest() {
        String[] checkIn = {"2022", "May", "30"};
        String[] checkOut = {"2022", "June", "15"};

        driver.get(url);
        bookingMainPage = new BookingMainPage(driver);
        bookingSearchResultsPage = bookingMainPage.selectLanguage("English")
                .setDestination(destination)
                .selectDate(checkIn,checkOut).clickSearchButton();

        Assert.assertEquals(bookingSearchResultsPage.getDestinationValue(),destination);

        Assert.assertTrue(bookingSearchResultsPage.getInDateDisplayed().contains((checkIn[2]).concat(" ").concat(checkIn[1])));
        Assert.assertTrue(bookingSearchResultsPage.getOutDateDisplayed().contains((checkOut[2]).concat(" ").concat(checkOut[1])));

        bookingSearchResultsPage.selectSuperbScore();

        List<Double> strHomeScores = bookingSearchResultsPage.getHomeScoresList();
        for (Double strHomeScore : strHomeScores) {
            Assert.assertTrue(strHomeScore >= 9);
        }
    }
}
