package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\Hamed\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"
        );
        driver = new org.openqa.selenium.chrome.ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void searchBooksTest() {
        driver.get("https://demo.nopcommerce.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // البحث
        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("small-searchterms"))
        );
        searchBox.clear();
        searchBox.sendKeys("books");
        searchBox.sendKeys(Keys.ENTER);

        // تحقق من H1 الصفحة
        WebElement h1 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.tagName("h1"))
        );
        Assert.assertEquals(h1.getText().trim(), "Search", "Search page H1 is incorrect.");

        // *** تحقق من قيمة بوكس البحث في صفحة النتائج ***
        WebElement searchInputResult = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("q"))
        );

        String value = searchInputResult.getAttribute("value");
        Assert.assertEquals(value, "books", "Search box value is incorrect on results page.");
    }

}
