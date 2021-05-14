package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;
import pages.SearchResultsPage;

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

    @FindBy(xpath = "//input[@type='text']")
    private WebElement searchField;

    private By menAndWomenSubMenu = By.xpath("//li[@id='category-3']//li//a");
    private By stationeryAndHomeAccessoriesSubmenus = By.xpath("//li[@id='category-6']//li//a");
    private By artSubMenu = By.xpath("//li[@id='category-9']//li//a");

    public UpperMenu(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<String> findSubMenus(WebElement menuTab, By locatorSubMenus) {
        BasePage.hoverMenu(menuTab);
        List<WebElement> menAndWomen = getDriver().findElements(locatorSubMenus);
        if(locatorSubMenus == menAndWomenSubMenu || locatorSubMenus == stationeryAndHomeAccessoriesSubmenus) {
            BasePage.isVisibleElement(locatorSubMenus);
        }
        List<String> subMenusText = new ArrayList<>();
        for (WebElement subMenu: menAndWomen) {
            subMenusText.add(subMenu.getText().trim());
        }
        return subMenusText;
    }

    public List<String> findSubMenuClothes() {
        List<String> clothesSubmenus = findSubMenus(clothesTab, menAndWomenSubMenu);
        return clothesSubmenus;
    }

    public List<String> findSubMenuAccessories() {
        List<String> accessoriesSubmenus = findSubMenus(accessoriesTab, stationeryAndHomeAccessoriesSubmenus);
        return accessoriesSubmenus;
    }

    public List<String> findSubMenuArt() {
        List<String> artSubmenus = findSubMenus(artTab, artSubMenu);
        return  artSubmenus;
    }

    public SearchResultsPage searchElement() {
        searchField.click();
        searchField.sendKeys("Bear");
        Actions actions = new Actions(getDriver());
        actions.sendKeys(Keys.ENTER)
                .build()
                .perform();
        return new SearchResultsPage();
    }
}
