package pages;

import blocks.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilits.MyComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatArgumentException;

public class HomePage1 extends BasePage{

    private List<Product> sortingProductsByComparator = new ArrayList<>();
    private List<Product> productsWithSorting = new ArrayList<>();
    private List<Product> productsWithSortingWWW = new ArrayList<>();




    private By productLocator = By.xpath("//div[@itemprop='itemListElement']");

    @FindBy(xpath = "//div[@itemprop='itemListElement']")
    private List<WebElement> productContainers;

    @FindBy(xpath = "//button[contains(@class, 'select-title')]")
    private WebElement sortButton;

    @FindBy(xpath = "//a[contains(@href, 'price.asc')]")
    private WebElement sortButtonByPriceLowToHigh;

    @FindBy(xpath = "//a[@rel='next']")
    private WebElement nextPageLink;

    @FindBy(xpath = "//nav[@class='pagination']//a[@rel='nofollow']")
    private List<WebElement> pagination;


    public HomePage1() {
        PageFactory.initElements(getDriver(), this);
    }

    public List<Product> getProductsOnHomePage() {
            isVisibleElement(productLocator);
            List<Product> products = Product.getProducts(productContainers);
            return products;

    }


    public HomePage1 sortProducts(WebElement asSortLink) throws InterruptedException {
        sortButton.click();
        isVisibleElement(By.xpath("//a[contains(@href, 'price.asc')]"));
        asSortLink.click();
        isVisibleElement(productLocator);
        this.scrollToWebElement(sortButtonByPriceLowToHigh);
        return this;
    }

    public Boolean checkProductsPriceFromLowToHigh() throws InterruptedException {
        productsWithSortingWWW = getProductsOnHomePage();
        System.out.println(productsWithSortingWWW.get(0).getProductName());
        Boolean isSorting = false;
        int numberNotSortingProducts = 0;
        int numberOfPages = pagination.size();
        for (int i = 0; i < numberOfPages; i++) {

            if (i != numberOfPages - 1) {
            sortProducts(sortButtonByPriceLowToHigh);
            }
            isVisibleElement(productLocator);
            productsWithSorting = getProductsOnHomePage();
            for (Product product : productsWithSorting) {
                System.out.println("");
                System.out.println("Comparator sortByButton:");
                System.out.println(product.getRegularPrice());
            }
            List<Product> productsWithoutSorting = getProductsOnHomePage();
            MyComparator myComparator = new MyComparator();
            sortingProductsByComparator = myComparator.comparePriceFromLowToHigh(productsWithoutSorting);
            for (Product product : sortingProductsByComparator) {
                System.out.println("Comparator sort:");
                System.out.println(product.getProductName());
            }
            //isSorting = productsWithoutSorting.equals(productsWithSorting);
            for (int j = 0; j < productsWithoutSorting.size(); j++) {
                System.out.println(sortingProductsByComparator.get(j).getProductName());
                System.out.println(productsWithSorting.get(j).getProductName());
                if (sortingProductsByComparator.get(j).equals(productsWithSorting.get(j))) {
                    isSorting = true;
                }
                else
                {
                    isSorting = false;
                    numberNotSortingProducts++;
                }
            }
            System.out.println(productsWithSortingWWW.get(0).getProductName());
            System.out.println(productsWithSorting.get(0).getProductName());
            System.out.println("!!!!!!!!!!!!!!!!!!" + productsWithSortingWWW.equals(productsWithSorting));
            if (i != numberOfPages - 1) {
                nextPageLink.click();
                isVisibleElement(productLocator);
                scrollToWebElement(sortButton);
            }
        }

        return isSorting;

    }

}
