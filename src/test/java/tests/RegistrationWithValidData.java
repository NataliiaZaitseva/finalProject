package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import utilits.GeneratorEmail;
import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationWithValidData extends BaseTest {
    private String firstName = "Mayaa";
    private String lastName = "Lomovaa";
    private String email = GeneratorEmail.generateEmail("gmail", 3, "Katya1" );
    private String password = "123asdAnn";
    private String birthday = "05/31/1970";

    @Test
    public void RegisterWithValidData() {
        MainPage mainPage = new MainPage();
        String nameOfNewUser = mainPage.waitUntilDownloading()
                .clickSignInButton()
                .goToCreatingNewAccountPage()
                .selectSocialTitle()
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillEmail(email)
                .fillPassword(password)
                .fillBirthday(birthday)
                .checkCustomerDataPrivacy()
                .checkTermsAndConditions()
                .submit()
                .getUserName();

        assertThat(nameOfNewUser)
                .as("Name is not appeared")
                .isEqualTo(firstName + " " + lastName);
    }
}
