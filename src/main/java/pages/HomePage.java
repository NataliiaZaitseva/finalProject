package pages;

import blocks.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilits.MyComparator;
import java.util.List;

public class HomePage extends BasePage {

    private By productLocator = By.xpath("//div[@itemprop='itemListElement']");

    @FindBy(xpath = "//div[@itemprop='itemListElement']")
    private List<WebElement> productContainers;

    @FindBy(xpath = "//button[contains(@class, 'select-title')]")
    private WebElement sortButton;

    @FindBy(xpath = "//a[contains(@href, 'price.asc')]")
    private WebElement sortButtonByPriceLowToHigh;

    @FindBy(xpath = "//a[contains(@href, 'price.desc')]")
    private WebElement sortButtonByPriceHighToLow;

    @FindBy(xpath = "//a[contains(@href, 'name.asc')]")
    private WebElement sortButtonByNameAtoZ;

    @FindBy(xpath = "//a[contains(@href, 'name.desc')]")
    private WebElement sortButtonByNameZtoA;

    @FindBy(xpath = "//a[@rel='next']")
    private WebElement nextPageLink;

    @FindBy(xpath = "//nav[@class='pagination']//a[@rel='nofollow']")
    private List<WebElement> pagination;

    public HomePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public List<Product> getProductsOnHomePage() {
        isVisibleElement(productLocator);
        List<Product> products = Product.getProducts(productContainers);
        return products;
    }

    public HomePage sortingProductsByButton(WebElement typeOfSorting) {
        sortButton.click();
        isVisibleElement(By.xpath("//a[contains(@href, 'price.asc')]"));
        typeOfSorting.click();
        isVisibleElement(productLocator);
        this.scrollToWebElement(sortButtonByPriceLowToHigh);
        return this;
    }

    public Boolean checkThatProductsSortedBy(String sortType)  {
        isVisibleElement(productLocator);
        List<Product> productsWithSorting = getProductsOnHomePage();
        List<Product> productsAfterCompares;
        switch (sortType) {
            case "Name from A to Z":
                productsAfterCompares = MyComparator.compareNameFromAtoZ(productsWithSorting);
                break;
            case "Name from Z to A":
                productsAfterCompares = MyComparator.compareNameFromZtoA(productsWithSorting);
                break;
            case "By price from low to high":
                productsAfterCompares = MyComparator.comparePriceFromLowToHigh(productsWithSorting);
                break;
            case "By price from high to low":
                productsAfterCompares = MyComparator.comparePriceFromHighToLow(productsWithSorting);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sortType);
        }
        return  productsWithSorting.equals(productsAfterCompares);
    }

    public HomePage sortProductBy(String sortType) {
        switch (sortType) {
            case "Name from A to Z":
                sortingProductsByButton(sortButtonByNameAtoZ);
                break;
            case "Name from Z to A":
                sortingProductsByButton(sortButtonByNameZtoA);
                break;
            case "By price from low to high":
                sortingProductsByButton(sortButtonByPriceLowToHigh);
                break;
            case "By price from high to low":
                sortingProductsByButton(sortButtonByPriceHighToLow);
                break;
        }
        return this;
    }
}








