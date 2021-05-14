package pages;

import blocks.Product;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Data
public class EditCard extends BasePage{

    private int quantityProducts;
    private double totalPrice;
    private String message;
    private String typeOfPaper;

    private By selectPaperType = By.xpath("//select[@id='group_4']");
    private By popupHeader = By.xpath("//div[@class='modal-header']//h4");

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

    @FindBy(xpath = ".//input[@type='number']")
    private WebElement numberOfQuantity;

    public EditCard() {
        PageFactory.initElements(getDriver(), this);
    }

    public EditCard changePaperType() {
        findElement(selectPaperType).click();
        selectElement(selectPaperType).selectByVisibleText("Doted");
        return this;
    }

    public EditCard changeQuantity() {
        int quantityValue = 0;
        while(quantityValue < 5) {
            touchSpinUp.click();
            quantityValue = Integer.valueOf(numberOfQuantity.getAttribute("value"));
        }
        return this;
    }

    public EditCard addToCard() {
        addToCardButton.click();
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-header']//h4")));
        return this;
    }

    public double checkTotalTax() {
        Double price = Product.getPriceinValidFormat(singlePrice);
        totalPrice = quantityProducts * price;
        return totalPrice;
    }

    public EditCard getFields() {
        quantityProducts = Integer.valueOf(quantity.getText());
        message = headerModalDialog.getText();
        typeOfPaper = paperType.getText();
        totalPrice = checkTotalTax();
        return this;
        }
}
