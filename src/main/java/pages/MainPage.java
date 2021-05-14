package pages;

import blocks.Footer;
import blocks.Product;
import blocks.UpperMenu;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MainPage extends BasePage {

    WebDriver driver;

    private By signInIcon = By.xpath("//div[@class='user-info']//i[@class='material-icons']");
    private By menSubMenu = By.xpath("//a[contains(@href, '4-men')]");
    private By womenSubMenu = By.xpath("//a[contains(@href, '5-women')]");
    private By menAndWomenSubMenu = By.xpath("//li[@id='category-3']//li//a");
    private By accessoriesSubmenus = By.xpath(".//div[@id='top_sub_menu_87076']//ul[@class='top-menu']//li");

    private List<String> languages;

    @FindBy(xpath = "//div[@id='_desktop_cart']")
    private WebElement cart;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement inputFieldEmail;

    @FindBy(xpath = "//input[@value='Subscribe']")
    private WebElement subscribeButton;

    @FindBy(xpath = "//div[@id='_desktop_language_selector']//button")
    private WebElement languageSelectButton;

    @FindBy(xpath = "//div[@id='_desktop_language_selector']//ul//li//a")
    private List<WebElement> listOfLanguages;

    @FindBy(xpath = "//div[@id='_desktop_user_info']//a")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@class='account']//span[@class='hidden-sm-down']")
    private WebElement nameOfUser;

    @FindBy(xpath = "//ul[@id='top-menu']//li[@id='category-3']//a[@class='dropdown-item']")
    private WebElement clothesTab;

    @FindBy(xpath = "//li[@id='category-6']//a[@class='dropdown-item']")
    private WebElement accessoriesTab;

    @FindBy(xpath = "//div[contains(@class, 'thumbnail-container reviews-loaded')]")
    private List<WebElement> productContainers;

    @FindBy(xpath = "//a[contains(@href,'2-home')]")
    private WebElement allProductsButton;

    private UpperMenu upperMenu;
    private Footer footer;


    public MainPage() {
        PageFactory.initElements(getDriver(), this);
        driver = getDriver();
        upperMenu = new UpperMenu(driver);
        footer = new Footer(driver);
    }

    public UpperMenu getUpperMenu() {
        return upperMenu;
    }

    public Footer getFooter() {
        return footer;
    }

    public MainPage waitUntilDownloading() {
        isVisibleElement(signInIcon);
        return this;
    }

    public MainPage fillInputFieldEmail(String email) {
        scrollToWebElement(inputFieldEmail);
        inputFieldEmail.sendKeys(email);
        return this;
    }

    public MainPage subscribe() {
        log.info("Click on the Subscribe button");
        subscribeButton.click();
        return this;
    }

    public Boolean isSubscribe() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        Boolean searchResult = (Boolean) js.executeScript("return arguments[0].checkValidity();", inputFieldEmail);
        return searchResult;
    }

    public MainPage clickOnLanguagesButton() {
        log.info("Click on the Language button");
        languageSelectButton.click();
        return this;
    }

    public List<String> findLanguages() {
        List<String> languages = new ArrayList<>();
        for (WebElement language : listOfLanguages) {
            languages.add(language.getText());
        }
        return languages;
    }

    public LoginPage clickSignInButton() {
        signInButton.click();
        return new LoginPage();
    }

    public String getUserName() {
        return nameOfUser.getText();
    }

    public List<Product> getAllProductsOnPage() {
        List<Product> products = Product.getProducts(productContainers);
        return products;
    }

    public HomePage clickAllProductsButton() {
        allProductsButton.click();
        return new HomePage();
    }
}
