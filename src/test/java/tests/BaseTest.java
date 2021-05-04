package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;

public class BaseTest {
    @BeforeMethod
    public synchronized void createDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30L);
        driver.manage().window().maximize();
        BasePage.setDriverThreadLocal(driver);
        driver.get("https://demo.prestashop.com/");
        //BasePage.setDriver(driver);
        BasePage.setWait(wait);
        driver.switchTo().frame("framelive");
    }


    @AfterMethod
    public void quite() {
        BasePage.getDriver().quit();
    }
}
