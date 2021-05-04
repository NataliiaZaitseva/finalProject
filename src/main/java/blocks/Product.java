package blocks;

import com.google.common.base.CharMatcher;
import lombok.ToString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

import java.util.ArrayList;
import java.util.List;

import static pages.BasePage.*;


@ToString
public class Product {

    private WebElement productName;
    private WebElement regularPrice;
    private WebElement oldPrice;
    private WebElement discount;
    static List<Product> allProduct = new ArrayList<>();

    //private By discountLocator = By.xpath(".//ul[@class='product-flags']/li");
    //private By oldPriceLocator = By.xpath(".//span[@class='regular-price']");
    //private By priceLocator = By.xpath(".//h2[@itemprop='name']//a");
    //private By nameLocator = By.xpath(".//span[@class='price']");

    public double getRegularPrice() {
        String priceWithoutE  = regularPrice.getText().substring(1);
        return Double.valueOf(priceWithoutE);
    }

    public Product() {
        PageFactory.initElements(getDriver(), this);
    }

    public Product(WebElement container) {

        /*this.image = container.findElement(By.xpath(".//div[@class='image']//img[@class='img-responsive']"));
        this.productName = container.findElement(By.xpath(".//div[@class='caption']//h4"));*/
//        this.description = description;
 //       this.regularPrice = container.findElement(By.xpath(".//p[@class='price']")).getText();

        if (container.getAttribute("innerHTML").contains("product-flag discount")) {
            this.discount = container.findElement(By.xpath(".//ul[@class='product-flags']/li"));
        } else {
            this.discount = null;
        }
//        this.tax = tax;
//        this.addToCartButton = addToCartButton;
//        this.addToWishListButton = addToWishListButton;
//        this.compareButton = compareButton;*/

        if (container.getAttribute("innerHTML").contains("regular-price")) {
            this.oldPrice = container.findElement(By.xpath(".//span[@class='regular-price']"));
        }
        else {
            this.oldPrice = null;
        }
        //System.out.println(oldPrice.getText());
        isVisibleElement(By.xpath(".//h2[@itemprop='name']//a"));
        this.productName = container.findElement(By.xpath(".//h2[@itemprop='name']//a"));
        isVisibleElement(By.xpath(".//span[@class='price']"));
        this.regularPrice = container.findElement(By.xpath(".//span[@class='price']"));
    }


    public static List<Product> getProducts(List<WebElement> containers) {
        allProduct.clear();
        for (WebElement container : containers) {
            Product product = new Product(container);
            allProduct.add(product);

        }
        return allProduct;
    }

    public static double getRegularPriceFromOldPrice(WebElement priceElement) {
        String price = priceElement.getText();
        String priceWithoutE = price.substring(1);
        return Double.valueOf(priceWithoutE);
    }

    public Double getDiscount() {
        String discountText = discount.getText();
        Double value = Double.parseDouble(discountText.replaceAll("[^0-9]", ""));
        System.out.println(value);
        return value;
    }

    public static Boolean isTwoPricesAvailableInProductsOnSale() {
        int count = 0;
        for (Product product: allProduct) {
            if (product.regularPrice.isDisplayed() && product.oldPrice.isDisplayed()) {
                count++;
            }
        }
        if (count == allProduct.size()) {
            return true;
        }
        else return false;
    }

    public static Boolean isNewPriceCalculateRight() {
        //double oldPriceDouble = getRegularPrice(product.oldPrice);
        //List<Double> listCalculatedNewPrices = new ArrayList<>();

        int i = 0;
        for (Product product: allProduct) {
            double oldPriceDouble = getRegularPriceFromOldPrice(product.oldPrice);
            Double newPrice = oldPriceDouble * (100 - product.getDiscount()) /100;
            Double newRightPrice = round(newPrice, 2);
            System.out.println("NewRightPrice " + newRightPrice);
            System.out.println("RegularPrice " + product.getRegularPrice());

            if (product.getRegularPrice() == newRightPrice) {
                i++;
            }
            //listCalculatedNewPrices.add(newRightPrice);
        }
        if (allProduct.size() == i) {
            return true;
        }
        else return false;
    }

    public String getProductName() {
        return productName.getText();
    }

    public static List<String> getProductNames() {
        List<String> productNames = new ArrayList<>();

        for (Product product : allProduct) {
            productNames.add(product.getProductName());
            //System.out.println(product.getRegularPrice());
            System.out.println(product.getProductName());
        }
        return productNames;
    }

    public static List<Double> getRegularPrices() {
        List<Double> regularPrices = new ArrayList<>();

        for (Product product : allProduct) {
            regularPrices.add(product.getRegularPrice());
            System.out.println(product.getRegularPrice());
            //System.out.println(product.getProductName());
        }
        return regularPrices;
    }

    public static int checkPriceBiggerNull() {
        int number = 0;
        for (Product product: allProduct) {
            if (product.getRegularPrice() > 0) {
                number++;
            }
        }
        return number;
    }
}
