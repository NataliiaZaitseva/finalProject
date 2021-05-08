package tests;

import blocks.Product;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.MainPage;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckPricesProductsOnSale extends BaseTest {

    @Test
    public void checkPricesOfProducts() {

        MainPage mainPage = new MainPage();
        mainPage.waitUntilDownloading();
        List<Product> productsOnSale = mainPage.getFooter().clickPricesDropLink()
                .waitUntilDownloading().getAllProductsOnPage();
        Map<Product, Boolean> map = Product.isNewPriceCalculateRightForProducts(productsOnSale);

        SoftAssertions softly = new SoftAssertions();

        Set<Map.Entry<Product, Boolean>> entries = map.entrySet();
        for (Map.Entry<Product, Boolean> entry : entries) {
            assertThat(entry.getValue())
                    .as("Products price calculated wrong for " + entry)
                    .isEqualTo(true);
        }

        for (Map.Entry<Product, Boolean> entry : entries) {
            assertThat(entry.getKey().getRegularPrice())
                    .as("Regular price is null")
                   .isNotNull();
            assertThat(entry.getKey().getOldPrice())
                    .as("Old price is null")
                    .isNotNull();
        }
        softly.assertAll();
    }
}
