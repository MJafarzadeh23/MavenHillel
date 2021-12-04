package SelenidePageStream26;

import SelenidePageStream.JqueryBootgridPage.EnableDisableColumnDropDown;
import SelenidePageStream.JqueryBootgridPage.ShowRowsDropDown;
import SelenidePageStream.JqueryBootgridPage.JqueryBootgridMainPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JqueryBootgridTest {
    //protected WebDriver driver;
    JqueryBootgridMainPage mainPage = new JqueryBootgridMainPage();

    @BeforeClass
    public void setUp() {
        Configuration.baseUrl = "http://www.jquery-bootgrid.com";
        /*System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\IdeaProjects\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();*/
    }

    @Test
    public void tableColumnVisibility() {

        mainPage.open()
                .startExampleClick()
                .showTableRowsDropDown(ShowRowsDropDown.ALL)
                .columnEnableDisable(EnableDisableColumnDropDown.SENDER)
                .isColumnVisible("Sender");
    }

    @Test
    public void sortByID() {

        mainPage.open()
                .startExampleClick()
                .showTableRowsDropDown(ShowRowsDropDown.ALL)
                .inputSearchField("1115")
                .isRowSortedByID("1115");
    }

    @Test
    public void columnDataSortedAscendingOrder() throws InterruptedException {

        mainPage.open()
                .startExampleClick()
                .showTableRowsDropDown(ShowRowsDropDown.ALL).sortInAscendingOrder("received");

        Assert.assertTrue(mainPage.isReceivedColumnSorted(mainPage.receivedColumnData()));
    }
}
