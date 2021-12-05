package SelenidePageStream.RozetkaSelenidePages;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class RozetkaMainPageSelenide {

    private SelenideElement searchField = $("[name='search']");
    private SelenideElement searchButton = $(".button_color_green");

    public RozetkaMainPageSelenide open() {
        Selenide.open("");
        return this;
    }

    public RozetkaProductListingPageSelenide runSearch(String searchItem) {
        searchField.sendKeys(searchItem);
        searchButton.click();
        return new RozetkaProductListingPageSelenide();
    }
}
