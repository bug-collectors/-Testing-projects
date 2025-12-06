package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BeforeTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {

        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\Hamed\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"
        );

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");

        driver = new ChromeDriver(options);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
