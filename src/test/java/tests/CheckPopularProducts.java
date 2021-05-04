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
    public void checkPopularProducts()throws InterruptedException {

        MainPage mainPage = new MainPage();
        mainPage.waitUntilDownloading();
        List<Product> allProductsOnPage = mainPage.getAllProductsOnPage();
        int numbersPriceBiggerNull = Product.checkPriceBiggerNull();

        /*List<Double> regularPrices = new ArrayList<>();
        List<String> productNames = new ArrayList<>();

       for (Product product : allProductsOnPage) {
           regularPrices.add(product.getRegularPrice());
           productNames.add(product.getProductName());

            //System.out.println(product.getRegularPrice());
           //System.out.println(product.getProductName());
       }*/

        SoftAssertions softly = new SoftAssertions();
        assertThat(allProductsOnPage)
                .as("There are not 8 products on the page")
                .hasSize(8);
       assertThat(Product.getRegularPrices())
               .as("There are not 8 prices on the page")
               .hasSize(8);
       assertThat(Product.getProductNames())
               .as("There are not 8 product names on the page")
               .hasSize(8);
       assertThat(numbersPriceBiggerNull)
               .as("Not all prices bigger 0")
               .isEqualTo(8);

        softly.assertAll();
    }

}
