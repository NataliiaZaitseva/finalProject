package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.MainPage;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckLanguages extends BaseTest {

    @Test
    public void checkNumberLanguages() throws InterruptedException {
        MainPage mainPage = new MainPage();
        List<String> langs = mainPage.waitUntilDownloading()
                .clickOnLanguagesButton()
                .findLanguage();

            assertThat(langs).
                    as("this language is not found")
                    .containsAnyOf("Українська")
                    .as("Wrong number of languages")
                    .hasSize(44);
    }

}
