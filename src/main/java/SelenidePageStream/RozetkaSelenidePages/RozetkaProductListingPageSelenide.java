package SelenidePageStream.RozetkaSelenidePages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;

public class RozetkaProductListingPageSelenide {

    private ElementsCollection productsContainerList = $$x("//li[contains(@class,'catalog-grid__cell')]");

    //a method that will return List<Integer> of all products' prices
    public List<Integer> productsPricesList() throws InterruptedException {
        List<Integer> productsPrices = receivedColumnData().stream()
                .map(e -> e.$x(".//span[@class='goods-tile__price-value']").getText().trim().replaceAll(" ",""))
                .mapToInt(value -> Integer.parseInt(value))
                .mapToObj(e -> e).collect(Collectors.toList());
        return productsPrices;
    }

    //a method that will return a list of product elements with price > 8000
    public List<SelenideElement> productElementsByPrice(int priceFilter) throws InterruptedException {
        List<SelenideElement> productsElementsByPrice = receivedColumnData().stream()
                .filter(e -> Integer.valueOf(e.$x(".//span[@class='goods-tile__price-value']").getText().trim().replaceAll(" ","")) > priceFilter)
                .collect(Collectors.toList());
        return productsElementsByPrice;
    }

    //a method that will return a q-ty of products with price < 8000
    public long productsQuantityByPrice(int priceFilter) throws InterruptedException {
        long productsQuantityByPrice = receivedColumnData().stream()
                .filter(e -> Integer.valueOf(e.$x(".//span[@class='goods-tile__price-value']").getText().trim().replaceAll(" ","")) < priceFilter)
                .count();
        return productsQuantityByPrice;
    }

    //a method that will return max price found on page
    public double maxProductPrice() throws InterruptedException {
        double maxPrice = receivedColumnData().stream()
                .map(e -> e.$x(".//span[@class='goods-tile__price-value']").getText().trim().replaceAll(" ",""))
                .mapToDouble(Double::valueOf)
                .max().orElseThrow(() -> new NoSuchElementException("It appears we don't have a value..."));
        return maxPrice;
    }

    //a method that will return List<String> of all products' titles
    public List<String> productsTitlesList() throws InterruptedException {
        List<String> productsTitles = receivedColumnData().stream()
                .map(e -> e.$x(".//span[@class='goods-tile__title']").getText())
                .collect(Collectors.toList());
        return productsTitles;
    }

    //a method that will return List<Integer> of prices for those elements that have partial text 'IdeaPad' in their title
    public List<Integer> pricesByTitle(String title) throws InterruptedException {
        List<Integer> productsPricesByTitle = receivedColumnData().stream()
                .filter(e -> e.$x(".//span[@class='goods-tile__title']").getText().contains(title))
                .map(e -> e.$x(".//span[@class='goods-tile__price-value']").getText().trim().replaceAll(" ",""))
                .mapToInt(value -> Integer.parseInt(value))
                .mapToObj(e -> e).collect(Collectors.toList());
        return productsPricesByTitle;
    }

    private ElementsCollection receivedColumnData() throws InterruptedException {
        Thread.sleep(1800);
        return productsContainerList;
    }
}
