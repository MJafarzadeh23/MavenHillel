package UIDemoTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SearchBrowserHW18 extends BaseBrowserTest {
    String urlSauceDemo = "https://www.saucedemo.com/";
    String standardLoginSauceDemo = "standard_user";
    String passwordSauceDemo = "secret_sauce";
    int expectedItemQuantity = 6;

    String urlPriceUa = "https://price.ua/ua";

    @Test
    public void itemQuantityOnPage() {
        //Открыть сайт https://www.saucedemo.com/
        driver.get(urlSauceDemo);

        //Залогиниться со стандартным юзером
        WebElement loginFieldSauce = driver.findElement(By.id("user-name"));
        loginFieldSauce.sendKeys(standardLoginSauceDemo);

        WebElement passwordFieldSauce = driver.findElement(By.id("password"));
        passwordFieldSauce.sendKeys(passwordSauceDemo);

        driver.findElement(By.name("login-button")).click();

        //Получить список товаров на странице
        List<WebElement> itemList = driver.findElements(By.cssSelector(".inventory_item"));

        //Проверить, что кол-во отображенных товаров соответствует ожидаемому
        Assert.assertEquals(itemList.size(), expectedItemQuantity, "Item quantity doesn't match");
    }

    @Test
    public void checkItemOnPage() throws InterruptedException {
        String expectedItemOnPage = "Oculus Quest 2 256 Gb";

        //Открыть сайт https://price.ua/ua
        driver.get(urlPriceUa);

        //В поисковом поле ввести oculus
        WebElement searchField = driver.findElement(By.id("SearchForm_searchPhrase"));
        searchField.sendKeys("oculus");

        //Кликнуть иконку поиска
        WebElement searchButtonSubmit = driver.findElement(By.id("search-block-submit"));
        searchButtonSubmit.click();

        //Отсортировать результаты от дорогих к дешевым
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='link ga_cat_sort hight-to-low']")));
        //WebElement highToLowButton = driver.findElement(By.xpath("//a[@class='link ga_cat_sort hight-to-low']"));
        WebElement highToLowButton = driver.findElement(By.cssSelector("#sorters-filters :last-child"));
        highToLowButton.click();

        //Проверить, что товар Oculus Quest 2 256 Gb присутствует в результатах выдачи
        List<WebElement> searchedItemElements = driver.findElements(By.cssSelector(".photo-wrap img[alt]"));
        wait.until(ExpectedConditions.visibilityOfAllElements(searchedItemElements));
        List<String> searchedItemList = new ArrayList<>();

        for(WebElement item : searchedItemElements) {
            String searchedItem = item.getAttribute("alt");
            searchedItemList.add(searchedItem);
            System.out.println(searchedItem);
        }

        System.out.println(searchedItemList.size());

        for(String itemStr : searchedItemList) {
            if (itemStr.toLowerCase().contains(expectedItemOnPage.toLowerCase())) {
                Assert.assertTrue(itemStr.contains(expectedItemOnPage));
                System.out.println("Found: " + expectedItemOnPage);
            } else {
                Assert.assertFalse(itemStr.contains(expectedItemOnPage));
                /*System.out.println("Not Found");*/
            }
        }
    }
}
