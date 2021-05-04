package tests;

import blocks.Product;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.OnSale;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

public class CheckPricesProductsOnSale extends BaseTest {

    @Test
    public void checkPricesProducts() throws InterruptedException {

        MainPage mainPage = new MainPage();
        mainPage.waitUntilDownloading();
        List<Product> productsOnSale = mainPage.getFooter().clickPricesDropLink()
                .waitUntilDownloading().getAllProductsOnPage();
        Boolean isNewPricesRight = Product.isNewPriceCalculateRight();
        Boolean isProductsOnSaleHaveTwoPrices = Product.isTwoPricesAvailableInProductsOnSale();


        SoftAssertions softly = new SoftAssertions();
        assertThat(isProductsOnSaleHaveTwoPrices)
                .as("There are no two prices in the each Product")
                .isEqualTo(true);
        assertThat(isNewPricesRight)
                .as("New prices calculated wrong")
                .isEqualTo(true);
        softly.assertAll();
    }
}
