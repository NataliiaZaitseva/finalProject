package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import static org.assertj.core.api.Assertions.assertThat;

public class RegisterWithInvalidData extends BaseTest {

    @Test
    public void RegisterUserWithInvalidData() throws InterruptedException {
        MainPage mainPage = new MainPage();
        String error = mainPage.waitUntilDownloading()
                .clickSignInButton()
                .createNewAccount()
                .selectSocialTitle()
                .fillFirstName("James8")
                .fillLastName("Zaya")
                .fillEmail("toto12@gmail.com")
                .fillPassword("123asdA")
                .fillBirthday("05/31/1970")
                .checkCustomerDataPrivacy()
                .checkTermsAndConditions()
                .submitWithNotValidData();

        assertThat(error)
                .as("There is no error")
                .as("Invalid format. rgba(255, 76, 76, 1)");

    }
}
