package HomeWork21;

import UIDemoTests.BaseBrowserTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IFrameTest extends BaseBrowserTest {
    //-Navigate to http://the-internet.herokuapp.com/iframe
    //-Click Italic button in the editor
    //-Verify that the button is selected
    //-Enter text into the text field
    //-Verify that text in the textfield is exactly as you entered
    //-Switch to main content
    //-Verify that page header is as expected "An iFrame containing the TinyMCE WYSIWYG Editor"

    @Test
    public void frameActions() {
        String url = "http://the-internet.herokuapp.com/iframe";
        String text = "LLFD";
        String pageHeader = "An iFrame containing the TinyMCE WYSIWYG Editor";

        driver.get(url);

        WebElement italicButton = driver.findElement(By.cssSelector("button[title='Italic']"));
        italicButton.click();
        Assert.assertEquals(italicButton.getAttribute("aria-pressed"),"true");

        driver.switchTo().frame("mce_0_ifr");
        WebElement textField = driver.findElement(By.xpath("//body[@id='tinymce']/p"));
        textField.clear();
        textField.sendKeys(text);
        //wait.until(x -> driver.findElement(By.xpath("//body[@id='tinymce']/p")).getText().length() != 0);
        System.out.println("Entered text: " + driver.findElement(By.xpath("//body[@id='tinymce']/p")).getText());
        Assert.assertEquals(driver.findElement(By.xpath("//body[@id='tinymce']/p")).getText(), text);

        driver.switchTo().defaultContent();
        WebElement header = driver.findElement(By.xpath("//div[@class='example']/h3"));
        Assert.assertEquals(header.getText(), pageHeader);
    }
}
