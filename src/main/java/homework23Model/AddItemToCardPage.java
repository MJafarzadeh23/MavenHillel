package homework23Model;

import additional.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class AddItemToCardPage extends BasePage {

    @FindBys({@FindBy(css = "[data-control-type='color']")})
    private List<WebElement> colors;

    @FindBy(xpath = "//div[@class='product-detail__select']")
    private WebElement colorContainer;

    @FindBys({@FindBy(css = "[data-control-type='size']")})
    private List<WebElement> sizes;

    @FindBy(xpath = "//div[@class='option-tiles__items']")
    private WebElement sizeContainer;

    @FindBy(id = "qty-selector")
    private WebElement quantity;

    @FindBy(xpath = "//div[@class='container']/button")
    private WebElement closeFooterButton;

    @FindBy(css = "[class^='add-cart']")
    private WebElement addToCartButton;

    @FindBy(css = ".drawer-ui .header-cart__confirmation-message_text")
    private WebElement confirmationMessage;

    @FindBy(css = ".cart-item__title")
    private WebElement cartItemTitle;

    @FindBy(css = ".cart-item__info .cart-item__detail__description")
    private WebElement cartItemQuantity;

    @FindBy(css = "[class^='header-cart__trigger']")
    private WebElement cart;

    private String itemQuantity;

    public AddItemToCardPage (WebDriver webDriver) {
        super(webDriver);
    }

    public AddItemToCardPage chooseColor() {
        wait.until(ExpectedConditions.visibilityOf(closeFooterButton));
        closeFooter(closeFooterButton);
        WebElement colorToChoose = randomElementSelection(colors, colorContainer);
        if (isColorSelected(colorToChoose)) {
            System.out.println("Color is selected");
        } else {
            colorToChoose.findElement(By.xpath("./img")).click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='product-detail__color-option  selected']")));
        return this;
    }

    public AddItemToCardPage chooseItemSize()/*(List<WebElement> sizes, WebElement sizeContainer)*/ {
        for (int i = 1; i <= 10; i++) {
            WebElement sizeToChoose = randomElementSelection(sizes,sizeContainer);
            actions.moveToElement(sizeToChoose).perform();
            if (isSizeAvailable(sizeToChoose)) {
                sizeToChoose.findElement(By.xpath("./span")).click();
                break;
            }
        }
        return this;
    }

    public AddItemToCardPage setItemQuantity() {
        int min = 1;
        int max = 6;
        actions.moveToElement(quantity).perform();
        quantity.click();
        clearWebField(quantity);
        itemQuantity = String.valueOf(getRandomNumber(min,max));
        quantity.sendKeys(itemQuantity);
        return this;
    }

    public void addToCart() {
        actions.moveToElement(addToCartButton).perform();
        addToCartButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public String  getConfirmationMessage() {
        wait.until(ExpectedConditions.visibilityOf(confirmationMessage));
        return confirmationMessage.getText();
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public String getCartItemQuantity() {
        return cartItemQuantity.getText();
    }

    public String getCartItemTitle() {
        return cartItemTitle.getText();
    }

    public void cartHover() {
        wait.until(ExpectedConditions.invisibilityOf(confirmationMessage));
        actions.moveToElement(cart).perform();
        wait.until(ExpectedConditions.visibilityOf(cart));
    }

    public boolean containsWordsArray(String inputString, String[] words) {
        List<String> inputStringList = Arrays.asList(inputString.split(" "));
        List<String> wordsList = Arrays.asList(words);

        return inputStringList.containsAll(wordsList);
    }

    private boolean isColorSelected(WebElement colorToChoose) {
        if (colorToChoose.getAttribute("class").contains("selected")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isSizeAvailable(WebElement sizeToChoose) {
        if (sizeToChoose.getAttribute("class").contains("item--out-of-stock")) {
            return false;
        } else {
            return true;
        }
    }

    private WebElement randomElementSelection(List<WebElement> elements, WebElement elementContainer) {
        int elementsNumber = getRandomNumber(1, elements.size());
        WebElement elementToChoose = elementContainer.findElement(By.xpath(".//a[" + String.valueOf(elementsNumber) + "]"));
        return elementToChoose;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private void clearWebField(WebElement element){
        while(!element.getAttribute("value").equals("")){
            element.sendKeys(Keys.BACK_SPACE);
            element.sendKeys(Keys.DELETE);
        }
    }

    private void closeFooter(WebElement footer) {
        footer.click();
    }
}
