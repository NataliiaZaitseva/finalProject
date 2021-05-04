package tests;

import blocks.UpperMenu;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.MainPage;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckCategories extends BaseTest {

    @Test
    public void CheckSubMenus() throws InterruptedException {
        MainPage mainPage = new MainPage();
        mainPage.waitUntilDownloading();
        List<String> clotheSubMenus = mainPage.getUpperMenu().findSubMenuClothes();
        List<String> accessoriesSubMenus = mainPage.getUpperMenu().findSubMenuAccessories();
        List<String> artSubMenus = mainPage.getUpperMenu().findSubMenuArt();

        SoftAssertions softly = new SoftAssertions();
            softly.assertThat(clotheSubMenus)
                    .as("Submenus of Clothes aren't appeared")
                    .containsAnyOf("MEN")
                    .containsAnyOf("WOMEN");

            softly.assertThat(accessoriesSubMenus)
                    .as("Submenus of Accessories are not appeared")
                    .containsAnyOf("STATIONERY")
                    .containsAnyOf("HOME ACCESSORIES");

            softly.assertThat(artSubMenus)
                    .as("There is submenu of Art")
                    .isEmpty();

        softly.assertAll();
        }

    }

