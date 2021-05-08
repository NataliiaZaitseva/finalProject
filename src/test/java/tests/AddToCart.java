package tests;

import org.testng.annotations.Test;
import pages.EditCard;
import pages.MainPage;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.SoftAssertions;

public class AddToCart extends BaseTest{

    @Test
    public void AddCart() {
        MainPage mainPage = new MainPage();
        mainPage.waitUntilDownloading();
        EditCard editCard = mainPage
                .getUpperMenu()
                .searchElement()
                .openEditCard()
                .changePaperType()
                .changeQuantity()
                .addToCard()
                .getFields();
        Double totalPrice = editCard.getTotalPrice();
        String message = editCard.getMessage();
        String typeOfPaper = editCard.getTypeOfPaper();
        int quantity = editCard.getQuantityProducts();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(totalPrice).as("Total price calculated wrong").isEqualTo(77.40);
        softly.assertThat(message).as("Message is wrong").isEqualTo("\uE876Product successfully added to your shopping cart");
        softly.assertThat(typeOfPaper).as("Wrong Type Of paper").isEqualTo("Doted");
        softly.assertThat(quantity).as("Wrong quantity").isEqualTo(5);
        softly.assertAll();
    }
}
