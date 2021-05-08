package tests;

import blocks.Product;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.MainPage;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckPopularProducts extends BaseTest{

    @Test
    public void checkPopularProducts() {

        MainPage mainPage = new MainPage();
        mainPage.waitUntilDownloading();
        List<Product> allProductsOnPage = mainPage.getAllProductsOnPage();
        int numbersOfProductsWithPriceBiggerZero = Product.getNumberOfPricesBiggerZero(allProductsOnPage);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(allProductsOnPage)
                .as("There are not 8 products on the page")
                .hasSize(8);
       softly.assertThat(Product.getRegularPrices(allProductsOnPage))
               .as("There are not 8 prices on the page")
               .hasSize(8);
       softly.assertThat(Product.getProductNames(allProductsOnPage))
               .as("There are not 8 product names on the page")
               .hasSize(8);
       softly.assertThat(numbersOfProductsWithPriceBiggerZero)
               .as("Not all prices bigger 0")
               .isEqualTo(8);

        softly.assertAll();
    }

}
