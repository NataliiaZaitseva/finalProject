package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.MainPage;

public class CheckValidOfSorting extends BaseTest {

    @Test
    public void SortProducts() {

        SoftAssert softly = new SoftAssert();
        MainPage mainPage = new MainPage();
        HomePage homePage = new HomePage();

        mainPage.waitUntilDownloading();
        Boolean isSortRightFromAtoZ = mainPage
                .clickAllProductsButton()
                .sortProductBy("Name from A to Z")
                .checkThatProductsSortedBy("Name from A to Z");
        softly.assertTrue(isSortRightFromAtoZ);

        Boolean isSortRightFromZtoA = homePage
                .sortProductBy("Name from Z to A")
                .checkThatProductsSortedBy("Name from Z to A");
        softly.assertTrue(isSortRightFromZtoA);

        Boolean isSortRightFromLowToHigh = homePage
                .sortProductBy("By price from low to high")
                .checkThatProductsSortedBy("By price from low to high");
        softly.assertTrue(isSortRightFromLowToHigh);

        Boolean isSortRightFromHighToLow = homePage
                .sortProductBy("By price from high to low")
                .checkThatProductsSortedBy("By price from high to low");
        softly.assertTrue(isSortRightFromHighToLow);

        softly.assertAll();
    }
}
