package HomeWork23;

import UIDemoTests.BaseBrowserTest;
import homework23Model.AddItemToCardPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddItemToCartTest extends BaseBrowserTest {

    String confirmationMessage = "been added to your cart.";
    String[] titleWords = {"champion", "powerblend", "applique"};

    @Test
    public void addWomenTopsItem() {
        AddItemToCardPage item = new AddItemToCardPage(driver);
        driver.get("https://www.sportchek.ca/categories/women/tops/hoodies/product/champion-womens-sportswear-powerblend-applique-hoodie-color-333261000_06-333261000.html#333261000%5Bcolor%5D=333261000_06");

        item.chooseColor()
                .chooseItemSize()
                .setItemQuantity()
                .addToCart();

        // Проверяем сообщение, что This Item has been added to cart
        Assert.assertTrue(item.getConfirmationMessage().toLowerCase().contains(confirmationMessage));

        // Подводим курсор к иконке с корзиной и проверяем, что Ttile товара и его QTY соответствует тому, что Вы выбирали
        item.cartHover();
        System.out.println("Cart Title = " + item.getCartItemTitle());
        Assert.assertTrue(item.containsWordsArray(item.getCartItemTitle().toLowerCase(), titleWords));
        Assert.assertEquals(item.getItemQuantity(), item.getCartItemQuantity());
    }
}
