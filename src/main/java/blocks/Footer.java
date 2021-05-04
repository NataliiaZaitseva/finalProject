package blocks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.OnSale;

public class Footer {

    @FindBy(xpath = "//a[@id='link-product-page-prices-drop-1']")
    private WebElement pricesDropLink;

    public Footer(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public OnSale clickPricesDropLink() {
        pricesDropLink.click();
        return new OnSale();
    }
}
