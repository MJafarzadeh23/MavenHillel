package UIDemoTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class BookingTest extends BaseBrowserTest {

    String url = "https://www.booking.com";
    String destination = "London";

    @Test
    public void checkSearch() {
        String checkInDateGiven = "30";
        String checkInMonthGiven = "May";
        String checkInYearGiven = "2022";

        String checkOutDateGiven = "15";
        String checkOutMonthGiven = "June";

        driver.get(url);

        WebElement languageSelectionButton = driver.findElement(By.xpath("//button[@data-modal-id='language-selection']"));
        languageSelectionButton.click();

        WebElement engLangButton = driver.findElement(By.xpath("//div[@lang='en-us']"));
        engLangButton.click();

        WebElement destinationInput = driver.findElement(By.xpath("//input[@data-component='search/destination/input-placeholder']"));
        destinationInput.sendKeys(destination);

        // open Datepicker
        driver.findElement(By.xpath("//div[@class='xp__dates-inner']")).click();

        // click next button of Datepicker till we get desirable Check-In Year
        while (!driver.findElement(By.xpath("//div[@class='bui-calendar__month']")).getText().contains(checkInYearGiven)) {
            driver.findElement(By.xpath("//div[@class='bui-calendar__control bui-calendar__control--next']")).click();
        }

        // click next button of Datepicker till we get desirable Check-In Month
        while (!driver.findElement(By.xpath("//div[@class='bui-calendar__month']")).getText().contains(checkInMonthGiven)) {
            driver.findElement(By.xpath("//div[@class='bui-calendar__control bui-calendar__control--next']")).click();
        }

        // create List of dates from Datepicker. Dates include "today" and all visible dates
        List<WebElement> datesInList = driver.findElements(By.xpath("//td[@class='bui-calendar__date' or @class='bui-calendar__date bui-calendar__date--today']"));
        // select Check-in Date
        selectDate(datesInList,checkInDateGiven);

        // if Check-Out Month = Check-in Month, select Check-Out Date on 1st half of Datepicker
        if (driver.findElement(By.xpath("//div[@class='bui-calendar__content']/div[1]/div")).getText().contains(checkOutMonthGiven)) {
            selectDate(datesInList,checkOutDateGiven);

          // if Check-Out Month matches month on 2nd half of Datepicker, select Check-Out Date on 2nd half of Datepicker
        } else if (driver.findElement(By.xpath("//div[@class='bui-calendar__content']/div[2]/div")).getText().contains(checkOutMonthGiven)) {
            List<WebElement> datesOutList = driver.findElements(By.xpath("//div[@class='bui-calendar__content']/div[2]//td[@class='bui-calendar__date']"));
            selectDate(datesOutList, checkOutDateGiven);
        } else {

            // if not all above, click next button of Datepicker till we get desirable Check-Out Month
            do {
                driver.findElement(By.xpath("//div[@class='bui-calendar__control bui-calendar__control--next']")).click();
            } while (!driver.findElement(By.xpath("//div[@class='bui-calendar__content']/div[2]/div")).getText().contains(checkOutMonthGiven));
            List<WebElement> datesOutList = driver.findElements(By.xpath("//div[@class='bui-calendar__content']/div[2]//td[@class='bui-calendar__date']"));
            selectDate(datesOutList,checkOutDateGiven);
        }

        try {
            //click search button
            WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
            searchButton.click();
            if (driver.findElement(By.xpath("//div[@data-component='search/dates/dates-errors']//div[@class='fe_banner__message ']")).isDisplayed()) {
                throw new Exception("Reservations longer than 30 (or 45 or else) nights are not possible.");
            }
        } catch (Exception e) {
            System.out.println("Reservations longer than 30 (or 45 or else) nights are not possible.");
            e.printStackTrace();
        }

        // test destination
        WebElement destinationDisplayed = driver.findElement(By.xpath("//input[@data-component='search/destination/input-placeholder']"));
        Assert.assertEquals(destinationDisplayed.getAttribute("value"),destination);

        // test selected Check-In & Check-Out Dates
        WebElement checkInDisplayed = driver.findElement(By.xpath("//div[@data-placeholder='Check-in Date']"));
        WebElement checkOutDisplayed = driver.findElement(By.xpath("//div[@data-placeholder='Check-out Date']"));
        int inDateGiven = Integer.valueOf(checkInDateGiven);
        int outDateGiven = Integer.valueOf(checkOutDateGiven);
        Assert.assertTrue(checkInDisplayed.getText().contains(checkInMonthGiven.concat(" ").concat(String.valueOf(inDateGiven))));
        Assert.assertTrue(checkOutDisplayed.getText().contains(checkOutMonthGiven.concat(" ").concat(String.valueOf(outDateGiven))));

        // down will use for waiter until.invisibilityOf
        WebElement propertyDisplayed = driver.findElement(By.xpath("//div[@data-testid='property-card']/div[1]"));

        // move to select score filter
        WebElement scoreFilter = driver.findElement(By.xpath("//div[@data-filters-item='review_score:review_score=90']/label"));
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//div[@data-filters-group='review_score']"))).perform();
        scoreFilter.click();

        //check score of found properties
        wait.until(ExpectedConditions.invisibilityOf(propertyDisplayed));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-testid='property-card']/div[1]")));
        List<WebElement> homesScoreList = driver.findElements(By.xpath("//div[@data-testid='review-score']/div[1]"));

        for(WebElement homeScore : homesScoreList) {
            List<Double> strHomeScoreList = new ArrayList<>();
            double strHomeScore = Double.valueOf(homeScore.getText());
            strHomeScoreList.add(strHomeScore);
            System.out.println(strHomeScore);

            // test that all found properties have score >=9
            Assert.assertTrue(strHomeScore >= 9);
        }
    }

    private String splitDate(WebElement date) {
        List<String> strDates = new ArrayList<>();
        String strDate = date.getAttribute("data-date");
        strDates.add(strDate);
        /*System.out.println(strDate);*/
        String[] splitDate = strDate.split("-");
        String numDate = splitDate[2];
        return numDate;
    }

    private void selectDate(List<WebElement> datesList, String dateGiven) {
        for(WebElement outDate : datesList) {
            String numOutDate = splitDate(outDate);

            if(numOutDate.matches(dateGiven)) {
                outDate.click();
                break;
            }
        }
    }
}
