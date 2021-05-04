package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.MainPage;
import utilits.MyComparator;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.SoftAssertions;

public class Task8 extends BaseTest{

    @Test
    public void SortProducts() throws InterruptedException {

        MyComparator myComparator = new MyComparator();

        MainPage mainPage = new MainPage();
        mainPage.waitUntilDownloading();
        Boolean isSortRight = mainPage
                .clickAllProductsButton().checkProductsPriceFromLowToHigh();
        SoftAssert softly = new SoftAssert();
        softly.assertTrue(isSortRight);
        softly.assertAll();



    }
}
