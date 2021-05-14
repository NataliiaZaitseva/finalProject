package pages;

import lombok.ToString;
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
import org.openqa.selenium.support.ui.Select;

@ToString
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

    protected static WebDriverWait wait;

    public static void setWait(WebDriverWait webWait) {
        wait = webWait;
    }

    public static WebDriverWait getWait() {
        return wait;
    }

   public static void scrollToWebElement(WebElement element) {
       JavascriptExecutor executor = ((JavascriptExecutor) getDriver());
       executor.executeScript("arguments[0].scrollIntoView(true);", element);
       try {
           Thread.sleep(3000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
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

    public static Select selectElement(By locator) {
        Select select = new Select(getDriver().findElement(locator));
        return select;
    }
}
