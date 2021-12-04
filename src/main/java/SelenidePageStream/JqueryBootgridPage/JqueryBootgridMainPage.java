package SelenidePageStream.JqueryBootgridPage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static SelenidePageStream.JqueryBootgridPage.EnableDisableColumnDropDown.*;
import static SelenidePageStream.JqueryBootgridPage.ShowRowsDropDown.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class JqueryBootgridMainPage {

    private SelenideElement startExampleButton = $(By.id("init-selection"));
    private SelenideElement basicSelectionBlock = $x("//section[@id='basic-selection']");
    private ElementsCollection showRowsDropDownList = $$x(".//a[@class='dropdown-item dropdown-item-button']");
    private ElementsCollection columnToChooseList = basicSelectionBlock.$$x(".//label[@class='dropdown-item']");
    private ElementsCollection column = basicSelectionBlock.$$x(".//th[@data-column-id]");
    private SelenideElement searchField = basicSelectionBlock.$x(".//input[@class='search-field form-control']");
    private ElementsCollection tableRowsNumber = basicSelectionBlock.$$x(".//tr[@data-row-id]");

    public JqueryBootgridMainPage open() {
        Selenide.open("/examples#selection");
        return this;
    }

    public JqueryBootgridMainPage startExampleClick() {
        startExampleButton.click();
        return this;
    }

    public JqueryBootgridMainPage showTableRowsDropDown(Enum dropDownOption) {
        basicSelectionBlock.$x(".//div[@class='dropdown btn-group'][1]/button[@data-toggle='dropdown']").click();
        chooseNumberOfRowsToShow(dropDownOption);
        return this;
    }

    public JqueryBootgridMainPage columnEnableDisable(Enum dropDownOption) {
        basicSelectionBlock.$x(".//div[@class='dropdown btn-group'][2]/button[@data-toggle='dropdown']").click();
        chooseColumnToHide(dropDownOption);
        return this;
    }

    public void isColumnVisible(String columnName) {
        column.findBy(text(columnName)).shouldNotBe(visible);
    }

    public JqueryBootgridMainPage inputSearchField(String idNumber) {
        searchField.sendKeys(idNumber);
        return this;
    }

    public void isRowSortedByID(String idNumber) {
        basicSelectionBlock.$x(".//div[@class='infos']").shouldHave(text("Showing 1 to 1 of 1 entries"));
        Assert.assertEquals(tableRowsNumber.size(), 1);
        Assert.assertTrue(tableRowsNumber.get(0).getAttribute("data-row-id").contains(idNumber));
    }

    public boolean isReceivedColumnSorted(ElementsCollection columnDataList) {
        List<String> elementsCollectionToList = columnDataList
                .stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());

        return Ordering.natural().isOrdered(elementsCollectionToList);
    }

    public ElementsCollection receivedColumnData() throws InterruptedException {
        Thread.sleep(1800);
        return basicSelectionBlock.$$x(".//tr/td[4]");
    }

    public void sortInAscendingOrder(String columnTitle) {
        column.stream()
                .filter(e -> e.getAttribute("data-column-id").equals(columnTitle))
                .findFirst()
                .ifPresent(SelenideElement::click);
    }

    private void chooseNumberOfRowsToShow(Enum dropDownOption) {
        if (ROWS10.equals(dropDownOption)) {
            showRowsDropDownList.findBy(text("10")).click();
        } else if (ROWS25.equals(dropDownOption)) {
            showRowsDropDownList.findBy(text("25")).click();
        } else if (ROWS50.equals(dropDownOption)) {
            showRowsDropDownList.findBy(text("50")).click();
        } else if (ALL.equals(dropDownOption)) {
            showRowsDropDownList.findBy(text("All")).click();
        }
    }

    private void chooseColumnToHide(Enum dropDownOption) {
        if (ID.equals(dropDownOption)) {
            columnToChooseList.findBy(text(" ID")).click();
        } else if (SENDER.equals(dropDownOption)) {
            columnToChooseList.findBy(text(" Sender")).click();
        } else if (RECEIVED.equals(dropDownOption)) {
            columnToChooseList.findBy(text(" Received")).click();
        } else if (LINK.equals(dropDownOption)) {
            columnToChooseList.findBy(text(" Link")).click();
        }
    }
}
