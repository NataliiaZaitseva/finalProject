package tests;

import org.testng.annotations.Test;
import pages.MainPage;
import utilits.GeneratorEmail;
import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationWithValidData extends BaseTest {
    final private String firstName = "Mayaa";
    final private String lastName = "Lomovaya";
    final private String email = GeneratorEmail.generateEmail("gmail", 3, "Katya1" );
    final private String password = "123asdAnn";
    final private String birthday = "05/31/1970";

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
