package UIDemoTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class Guru99TestHW18 extends BaseBrowserTest {

    String urlGuru99 = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";
    String validLoginGuru99 = "1303";
    String validPasswordGuru99 = "Guru99";
    String alertText = "User or Password is not valid";

    @Test
    public void authorizationWithInvalidLogin() {
        String invalidLoginGuru99 = "abcd";
        driver.get(urlGuru99);

        WebElement loginField = driver.findElement(By.name("uid"));
        loginField.sendKeys(invalidLoginGuru99);

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(validPasswordGuru99);

        WebElement loginButton = driver.findElement(By.name("btnLogin"));
        loginButton.click();

        Assert.assertEquals(driver.switchTo().alert().getText(), alertText);

        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.getCurrentUrl(), urlGuru99);
    }

    @Test
    public void resetButton() {
        driver.get(urlGuru99);

        WebElement loginField = driver.findElement(By.name("uid"));
        loginField.sendKeys(validLoginGuru99);

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(validPasswordGuru99);

        WebElement resetButton = driver.findElement(By.name("btnReset"));
        resetButton.click();

        Assert.assertEquals(loginField.getText(),"","Login field is not empty");
        Assert.assertEquals(passwordField.getText(),"","Password field is not empty");
        Assert.assertEquals(driver.getCurrentUrl(), urlGuru99);
    }

    @Test
    public void authorizationWithEmptyCredentials() {
        driver.get(urlGuru99);

        WebElement loginButton = driver.findElement(By.name("btnLogin"));
        loginButton.click();

        Assert.assertEquals(driver.switchTo().alert().getText(), alertText);

        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.getCurrentUrl(), urlGuru99);
    }

    @Test
    public void notBlankLoginField() {
        String notBlankLoginField = "User-ID must not be blank";

        driver.get(urlGuru99);
        loginClick();
        passwordClick();
        Assert.assertEquals(loginMessage().getText(), notBlankLoginField);

        driver.get(urlGuru99);
        loginClick();
        driver.findElement(By.tagName("body")).click();
        Assert.assertEquals(loginMessage().getText(),notBlankLoginField);

        driver.get(urlGuru99);
        loginClick();
        changeTab();
        Assert.assertEquals(loginMessage().getText(),notBlankLoginField);
    }

    @Test
    public void notBlankPasswordField() {
        String notBlankPasswordField = "Password must not be blank";

        driver.get(urlGuru99);
        passwordClick();
        loginClick();
        Assert.assertEquals(passwordMessage().getText(), notBlankPasswordField);

        driver.get(urlGuru99);
        passwordClick();
        driver.findElement(By.className("barone")).click();
        Assert.assertEquals(passwordMessage().getText(),notBlankPasswordField);

        driver.get(urlGuru99);
        passwordClick();
        changeTab();
        Assert.assertEquals(passwordMessage().getText(),notBlankPasswordField);
    }

    private void loginClick() {
        WebElement loginField = driver.findElement(By.name("uid"));
        loginField.click();
    }

    private void passwordClick() {
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.click();
    }

    private WebElement loginMessage() {
        WebElement blankLoginMessage = driver.findElement(By.id("message23"));
        return blankLoginMessage;
    }

    private WebElement passwordMessage() {
        WebElement blankPasswordMessage = driver.findElement(By.id("message18"));
        return blankPasswordMessage;
    }

    private void changeTab() {
        driver.switchTo().newWindow(WindowType.TAB);
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        driver.close();
        driver.switchTo().window(tabs2.get(0));
    }
}
