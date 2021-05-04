package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationWithValidData extends BaseTest {
    private String firstName = "Maya";
    private String lastName = "Lomova";

    @Test
    public void RegisterWithValidData() throws InterruptedException {
        MainPage mainPage = new MainPage();
        String nameOfNewUser = mainPage.waitUntilDownloading()
                .clickSignInButton()
                .createNewAccount()
                .selectSocialTitle()
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillEmail("toto1278@gmail.com")
                .fillPassword("123asdAnn")
                .fillBirthday("05/31/1970")
                .checkCustomerDataPrivacy()
                .checkTermsAndConditions()
                .submit()
                .findUserName();

        assertThat(nameOfNewUser)
                .as("Name is not appeared")
                .isEqualTo(firstName + " " + lastName);



    }
}
