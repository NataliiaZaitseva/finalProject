package pages;

import blocks.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class BasePage {

    public static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    private static ThreadLocal<WebDriver> getDriverThreadLocal() {
        return DRIVER_THREAD_LOCAL;
    }

    public static void setDriverThreadLocal(WebDriver webDriver) {
        DRIVER_THREAD_LOCAL.set(webDriver);
    }

    public static WebDriver getDriver() {
        return DRIVER_THREAD_LOCAL.get();
    }

    //protected static WebDriver driver;
    protected static WebDriverWait wait;

    /*public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }*/

    /*public static WebDriver getDriver() {
        return driver;
    }*/

    public static void setWait(WebDriverWait webWait) {
        wait = webWait;
    }

    public static WebDriverWait getWait() {
        return wait;
    }

   public static void scrollToWebElement(WebElement element)throws InterruptedException {
       JavascriptExecutor executor = ((JavascriptExecutor) getDriver());
       executor.executeScript("arguments[0].scrollIntoView(true);", element);
       Thread.sleep(3000);
   }

    public static void hoverMenu(WebElement element) {
        Actions action = new Actions(getDriver());
        action.moveToElement(element).build().perform();

    }

    public static void isVisibleElement(By locator) {

        BasePage.getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    public static WebElement findElement(By locator) {
        WebElement element = getDriver().findElement(locator);
        return element;
    }

    /*public Boolean findSubMenuOfClothes() {
        hoverMenu(clothesTab);
        List<WebElement> elements = getDriver().findElements(menAndWomenSubMenu);
        return ((elements.size() == 2) && elements.get(0).isDisplayed() && elements.get(1).isDisplayed());

    }*/

    public int checkIsDisplayedElements(By by) {
        List<WebElement> elements = getDriver().findElements(by);
        int sizeOfLIst = elements.size();
        int numberSubmenus = 0;
        if (sizeOfLIst > 0) {
            for (WebElement subMenu: elements) {
                if (subMenu.isDisplayed()) {
                    numberSubmenus++;
                }
            }
        }
        return numberSubmenus;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /*public List<Product> getAllProductsOnPage(List<WebElement> containersOfProducts) {
        List<Product> products = Product.getProducts(containersOfProducts);
        return products;
    }*/
}
