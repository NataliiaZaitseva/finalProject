package pages;

import blocks.Product;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;

public class EditCard extends BasePage{

    private int quantityProducts = 0;
    private double totalPrice = 0;
    private String message = "";
    private String typeOfPaper = "";

    public String getTypeOfPaper() {
        return typeOfPaper;
    }

    public String getMessage() {
        return message;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getQuantityProducts() {
        return quantityProducts;
    }

    private By selectPaperType = By.xpath("//select[@id='group_4']");
    private By popapHeader = By.xpath("//div[@class='modal-header']//h4");

    @FindBy(xpath = "//i[contains(@class, 'touchspin-up')]")
    private WebElement touchSpinUp;

    @FindBy(xpath = "//div[@class='add']")
    private WebElement addToCardButton;

    @FindBy(xpath = "//div[@class='modal-header']//h4")
    private WebElement headerModalDialog;

    @FindBy(xpath = "//span[contains(@class, 'paper')]/strong")
    private WebElement paperType;

    @FindBy(xpath = "//span[contains(@class, 'quantity')]/strong")
    private WebElement quantity;

    @FindBy(xpath = "//p[@class='product-price']")
    private WebElement singlePrice;

    public EditCard() {
        PageFactory.initElements(getDriver(), this);
    }

    public EditCard changePaperType() {
        getDriver().findElement(selectPaperType).click();
        selectElement(selectPaperType).selectByVisibleText("Doted");
        return this;
    }

    public EditCard changeQuantity() {
        for (int i = 1; i <= 5; i++) {
            touchSpinUp.click();
        }
        return this;
    }

    public EditCard addToCard() {
        addToCardButton.click();
        return this;
    }

    public double checkTotalTax() {
        Double price = Product.getRegularPriceFromOldPrice(singlePrice);
        totalPrice = quantityProducts * price;
        return totalPrice;
    }

    public double openPopap() {

        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-header']//h4")));
        quantityProducts = Integer.valueOf(quantity.getText());
        message = headerModalDialog.getText();
        typeOfPaper = paperType.getText();
        Double totalPrice = checkTotalTax();
        return totalPrice;

        }


}
