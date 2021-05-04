package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage extends BasePage{

    @FindBy(xpath = "//img[contains(@src, 'brown-bear-notebook')]")
    private WebElement brownBearNotebookProduct;

    public SearchResultsPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public EditCard openEditCard() {
        brownBearNotebookProduct.click();
        return new EditCard();
    }
}
