package HomeWork21;

import UIDemoTests.BaseBrowserTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropTest extends BaseBrowserTest {
    // Write automation test for drag&drop
    //- Navigate to http://demo.guru99.com/test/drag_drop.html
    //- Drag and drop all possible webelements to their placeholders
    //-Verify that button with 'Perfect!' text appears

    @Test
    public void dragAndDrop() {
        String url = "http://demo.guru99.com/test/drag_drop.html";

        driver.get(url);

        WebElement fivekElement = driver.findElement(By.id("fourth"));
        WebElement bankElement = driver.findElement(By.id("credit2"));
        WebElement salesElement = driver.findElement(By.id("credit1"));
        WebElement amountDebitPlaceholder = driver.findElement(By.id("amt7"));
        WebElement amountCreditPlaceholder = driver.findElement(By.id("amt8"));
        WebElement accountDebitPlaceholder = driver.findElement(By.id("bank"));
        WebElement accountCreditPlaceholder = driver.findElement(By.id("loan"));
        WebElement resultButton = driver.findElement(By.xpath("//div[@class='table4_result']/a"));

        actions.dragAndDrop(fivekElement, amountDebitPlaceholder).perform();
        actions.dragAndDrop(fivekElement, amountCreditPlaceholder).perform();
        actions.dragAndDrop(bankElement, accountDebitPlaceholder).perform();
        actions.dragAndDrop(salesElement, accountCreditPlaceholder).perform();

        Assert.assertEquals(resultButton.getText(), "Perfect!");
    }
}
