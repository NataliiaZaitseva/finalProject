package tests;

import org.testng.annotations.Test;
import pages.MainPage;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckIsSubscribe extends BaseTest {
    final private String email = "tttt@ttt";

    @Test
    public void checkSubscribe(){ 
        MainPage mainPage = new MainPage();
        Boolean isErrorInSubscribe = mainPage.waitUntilDownloading()
                .fillInputFieldEmail(email)
                .subscribe()
                .isSubscribe();

        assertThat(isErrorInSubscribe)
                .as("Error is not appeared")
                .isEqualTo(true);
    }
}
