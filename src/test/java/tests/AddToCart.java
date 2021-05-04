package tests;

import org.testng.annotations.Test;
import pages.EditCard;
import pages.MainPage;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.SoftAssertions;

public class AddToCart extends BaseTest{

    @Test
    public void AddCart()throws InterruptedException {
        MainPage mainPage = new MainPage();
        mainPage.waitUntilDownloading();
        Double totalPrice = mainPage.getUpperMenu().searchElement().openEditCard()
                .changePaperType().changeQuantity().addToCard().openPopap();
        System.out.println(totalPrice);

        assertThat(totalPrice).as("").isEqualTo(77.40);

    }
}
