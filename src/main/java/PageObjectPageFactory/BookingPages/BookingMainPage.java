package PageObjectPageFactory.BookingPages;

import additional.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.ArrayList;
import java.util.List;

public class BookingMainPage extends BasePage {

    @FindBy(xpath = "//button[@data-modal-id='language-selection']")
    private WebElement languageSelectionButton;

    @FindBys(@FindBy(css = ".bui-inline-container__main"))
    private List<WebElement> languageContainer;

    @FindBy(xpath = "//input[@data-component='search/destination/input-placeholder']")
    private WebElement destinationInput;

    @FindBy(xpath = "//div[@class='xp__dates-inner']")
    private WebElement datepicker;

    @FindBy(xpath = "//div[@class='bui-calendar__control bui-calendar__control--next']")
    private WebElement controlNextOfDatepicker;

    @FindBy(xpath = "//div[@class='bui-calendar__month']")
    private WebElement headerDatepicker;

    @FindBys(@FindBy(xpath = "//td[@class='bui-calendar__date' or @class='bui-calendar__date bui-calendar__date--today']"))
    private List<WebElement> datesContainer;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@data-component='search/dates/dates-errors']//div[@class='fe_banner__message ']")
    private WebElement invalidDateMessage;

    public BookingMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public BookingMainPage selectLanguage(String lang) {
        languageSelectionButton.click();
        desirableLanguage(lang).click();
        return this;
    }

    public BookingMainPage setDestination(String destination) {
        destinationInput.sendKeys(destination);
        return this;
    }

    public BookingMainPage selectDate(String[] checkIn, String[] checkOut) {
        openDatepicker();
        selectDesirableDate(checkIn);
        selectDesirableDate(checkOut);
        return this;
    }

    public BookingSearchResultsPage clickSearchButton() {
        try {
            searchButton.click();
            if (invalidDateMessage.isEnabled()) {
                throw new Exception("Reservations longer than 30 (or 45 or else) nights are not possible.");
            }
        } catch (Exception e) {
            System.out.println("Reservations longer than 30 (or 45 or else) nights are not possible.");
            e.printStackTrace();
        }
        return new BookingSearchResultsPage(driver);
    }

    private WebElement desirableLanguage(String language) {
        for (WebElement lang : languageContainer) {
            if (lang.getText().contains(language)) {
                return lang;
            }
        }
        return null;
    }

    private void openDatepicker() {
        datepicker.click();
    }

    private void selectDesirableDate(String[] date) {
        // String[] date = {year, month, date};
        // click next button of Datepicker till we get desirable Check-In Year
        while (!headerDatepicker.getText().contains(date[0])) {
            controlNextOfDatepicker.click();
        }
        // click next button of Datepicker till we get desirable Check-In Month
        while (!headerDatepicker.getText().contains(date[1])) {
            controlNextOfDatepicker.click();
        }
        // create List of dates from Datepicker. Dates include "today" and all visible dates
        List<WebElement> datesInList = datesContainer;
        // select Check-in Date
        selectDate(datesInList,date[2]);
    }

    private void selectDate(List<WebElement> datesList, String dateGiven) {
        for(WebElement date : datesList) {
            String numDate = splitDate(date);
            if(numDate.matches(dateGiven)) {
                date.click();
                break;
            }
        }
    }

    private String splitDate(WebElement date) {
        List<String> strDates = new ArrayList<>();
        String strDate = date.getAttribute("data-date");
        strDates.add(strDate);
        String[] splitDate = strDate.split("-");
        String numDate = splitDate[2];
        return numDate;
    }
}
