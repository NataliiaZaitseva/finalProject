package blocks;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.*;
import static pages.BasePage.getDriver;
import static pages.BasePage.round;

@Data
@Slf4j
public class Product {

    private WebElement productNameWe;
    private String productName;
    private WebElement regularPriceWe;
    private WebElement oldPriceWe;
    private WebElement discountWe;
    private Double discount;
    private Double regularPrice;
    private Double oldPrice;

    private By discountLocator = By.xpath(".//ul[@class='product-flags']//li[contains(@class, 'discount')]");
    private By oldPriceLocator = By.xpath(".//span[@class='regular-price']");
    private By priceLocator = By.xpath(".//span[@class='price']");
    private By nameLocator = By.xpath(".//div[@class='product-description']//a");

    public Product(WebElement container) {
        if (container.getAttribute("innerHTML").contains("product-flag discount")) {
            this.discountWe = container.findElement(discountLocator);
            this.discount = Double.parseDouble(discountWe.getText().replaceAll("[^0-9]", ""));
        } else {
            this.discount = null;
        }

        if (container.getAttribute("innerHTML").contains("regular-price")) {
            this.oldPriceWe = container.findElement(oldPriceLocator);
            this.oldPrice = Double.valueOf(oldPriceWe.getText().substring(1));
        } else {
            this.oldPrice = null;
        }

        this.productNameWe = container.findElement(nameLocator);
        this.productName = productNameWe.getText();

        this.regularPriceWe = container.findElement(priceLocator);
        this.regularPrice = Double.valueOf(regularPriceWe.getText().substring(1));
    }

    public static List<Product> getProducts(List<WebElement> containers) {
        List<Product> productsOnPage = new ArrayList<>();
        log.info("Getting products on the page");
        for (WebElement container : containers) {
            Product product = new Product(container);
            productsOnPage.add(product);
        }
        return productsOnPage;
    }

    public static double getPriceinValidFormat(WebElement priceElement) {
        String price = priceElement.getText();
        String priceWithoutE = price.substring(1);
        return Double.valueOf(priceWithoutE);
    }

    public static Map<Product, Boolean> isNewPriceCalculateRightForProducts(List<Product> products) {
        Map<Product, Boolean> map = new HashMap<>();
        for (Product product : products) {
            double oldPriceOnPage = product.oldPrice;
            Double newPrice = oldPriceOnPage * (100 - product.getDiscount()) / 100;
            Double newRightPrice = round(newPrice, 2);
            log.info("Calculate new price with discount {}", newRightPrice);
            if (product.regularPrice.equals(newRightPrice)) {
                map.put(product, true);
            } else {
                log.info("Finding is new price on the page {} is calculated right with discount", product.regularPrice);
                map.put(product, false);
            }
        }
        return map;
    }

    public static List<String> getProductNames(List<Product> products) {
        List<String> productNames = new ArrayList<>();
        for (Product product : products) {
            if (product.productName != null) {
                productNames.add(product.productName);
            }
        }
        return productNames;
    }

    public static List<Double> getRegularPrices(List<Product> products) {
        List<Double> regularPrices = new ArrayList<>();

        for (Product product : products) {
            if (product.regularPrice > 0) {
                regularPrices.add(product.regularPrice);
            }
        }
        return regularPrices;
    }

    public static int getNumberOfPricesBiggerZero(List<Product> products) {
        int number = 0;
        for (Product product : products) {
            if (product.regularPrice > 0) {
                number++;
            }
        }
        return number;
    }
}

