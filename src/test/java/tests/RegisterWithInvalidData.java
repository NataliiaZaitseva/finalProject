package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import static org.assertj.core.api.Assertions.assertThat;

public class RegisterWithInvalidData extends BaseTest {
    private String name = "James9";
    private String lastName = "Zaya";
    private String email = "toto14@gmail.com";
    private String password = "123asdA";
    private String birthday = "05/31/1970";

    @Test
    public void RegisterUserWithInvalidData() {
        MainPage mainPage = new MainPage();
        String errorMessage = mainPage.waitUntilDownloading()
                .clickSignInButton()
                .goToCreatingNewAccountPage()
                .selectSocialTitle()
                .fillFirstName(name)
                .fillLastName(lastName)
                .fillEmail(email)
                .fillPassword(password)
                .fillBirthday(birthday)
                .checkCustomerDataPrivacy()
                .checkTermsAndConditions()
                .submitWithNotValidData();

        assertThat(errorMessage)
                .as("There is no error")
                .contains("Invalid format")
                .as("Invalid format not rgba(255, 76, 76, 1)")
                .contains("rgba(255, 76, 76, 1)");
    }
}
