package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElectronicsShoppingTest {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        // FIX FOR CHROME 142 (Blank page / infinite loading)
        options.addArguments("--disable-features=OptimizationGuideModelDownloading");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

        try {

            driver.get("https://demo.nopcommerce.com/");

            // Step 1 – Electronics tab
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Electronics"))).click();

            // Step 2 – Camera & photo
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Camera & photo"))).click();

            // Step 3 – Nikon D5500 DSLR
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Nikon D5500 DSLR"))).click();

            // Step 4 – Choose color (Black)
            wait.until(ExpectedConditions.elementToBeClickable(By.id("product_attribute_14_43"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button-14"))).click();

            // Choose color (Red)
            wait.until(ExpectedConditions.elementToBeClickable(By.id("product_attribute_14_44"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button-14"))).click();

            // Step 5 – Back
            driver.navigate().back();

            // Apple iCam
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Apple iCam"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button-11"))).click();
            driver.navigate().back();

            // Step 6 – Leica T Mirrorless Digital Camera
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Leica T Mirrorless Digital Camera"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button-16"))).click();

            // Go to cart
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Shopping cart"))).click();

            System.out.println("Electronics test completed successfully.");

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
