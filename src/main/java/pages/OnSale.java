package pages;

import blocks.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OnSale extends BasePage{

    @FindBy(xpath = "//div[@class='product']")
    private List<WebElement> productContainersOnSale;

    private By firstProduct = By.xpath("//div[@class='product'][1]");

    public OnSale() {
        PageFactory.initElements(getDriver(), this);
    }

    public List<Product> getAllProductsOnPage() {
        List<Product> products = Product.getProducts(productContainersOnSale);
        return products;
    }

    public OnSale waitUntilDownloading() {
        isVisibleElement(firstProduct);
        return this;
    }


}
