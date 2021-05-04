package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;
import pages.MainPage;

import java.util.ArrayList;
import java.util.List;

import static pages.BasePage.getDriver;

public class UpperMenu {

    @FindBy(xpath = "//ul[@id='top-menu']//li[@id='category-3']//a[@class='dropdown-item']")
    private WebElement clothesTab;

    @FindBy(xpath = "//ul[@id='top-menu']//li[@id='category-6']//a[@class='dropdown-item']")
    private WebElement accessoriesTab;

    @FindBy(xpath = "//ul[@id='top-menu']//li[@id='category-9']//a[@class='dropdown-item']")
    private WebElement artTab;


    List<String> clothesSubMenus = new ArrayList<>();
    List<String> accessoriesSubMenus = new ArrayList<>();
    List<String> artSubmenusList = new ArrayList<>();


    private By menAndWomenSubMenu = By.xpath("//li[@id='category-3']//li//a");
    private By stationeryAndHomeAccessoriesSubmenus = By.xpath("//li[@id='category-6']//li//a");
    private By artSubMenu = By.xpath("//li[@id='category-9']//li//a");


    public UpperMenu(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<String> findSubMenus(WebElement menuTab, By locatorSubMenus) throws InterruptedException {
        BasePage.hoverMenu(menuTab);

        List<WebElement> menAndWomen = getDriver().findElements(locatorSubMenus);
        BasePage.isVisibleElement(locatorSubMenus);

        List<String> subMenusText = new ArrayList<>();

        for (WebElement subMenu: menAndWomen) {
            subMenusText.add(subMenu.getText().trim());
        }
        return subMenusText;
    }

    public List<String> findSubMenuClothes() throws InterruptedException {
        List<String> clothesSubmenus = findSubMenus(clothesTab, menAndWomenSubMenu);
        return clothesSubmenus;
    }

    public List<String> findSubMenuAccessories() throws InterruptedException {
        List<String> accessoriesSubmenus = findSubMenus(accessoriesTab, stationeryAndHomeAccessoriesSubmenus);
        return accessoriesSubmenus;
    }

    public List<String> findSubMenuArt() throws InterruptedException {
        BasePage.hoverMenu(artTab);

        List<WebElement> menAndWomen = getDriver().findElements(artSubMenu);
        //BasePage.isVisibleElement(locatorSubMenus);

        List<String> subMenusText = new ArrayList<>();

        for (WebElement subMenu: menAndWomen) {
            subMenusText.add(subMenu.getText().trim());
        }
        return subMenusText;
    }
}
