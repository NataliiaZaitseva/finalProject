package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckIsSubscribe extends BaseTest {
    private String email = "tttt@ttt";

    @Test
    public void checkSubscribe() throws InterruptedException {
        MainPage mainPage = new MainPage();
        Boolean isErrorInSubscribe = mainPage.waitUntilDownloading()
                .fillInputFieldEmail(email)
                .subscribe()
                .checkIsSubscribe();

        assertThat(isErrorInSubscribe)
                .as("Error is not appeared")
                .isEqualTo(true);





    }
}
