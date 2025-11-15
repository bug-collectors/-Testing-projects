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

public class ComputersShoppingTest {

    public static void main(String[] args) {

        // Setup driver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        // IMPORTANT FIXES FOR CHROME 142 (Blank page / infinite loading)
        options.addArguments("--disable-features=OptimizationGuideModelDownloading");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

        try {
            driver.get("https://demo.nopcommerce.com/");

            // Computers → Desktops
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Computers"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Desktops"))).click();

            // Build your own computer
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Build your own computer"))).click();

            // RAM 2GB
            wait.until(ExpectedConditions.elementToBeClickable(By.id("product_attribute_2"))).sendKeys("2 GB");

            // HDD 320GB
            wait.until(ExpectedConditions.elementToBeClickable(By.id("product_attribute_3_6"))).click();

            // Add to cart
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button-1"))).click();

            // Back to products
            driver.navigate().back();
            driver.navigate().back();

            // Add Digital Storm
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.linkText("Digital Storm VANQUISH 3 Custom Performance PC"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button-2"))).click();
            driver.navigate().back();

            // Add Lenovo IdeaCentre
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.linkText("Lenovo IdeaCentre 600 All-in-One PC"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button-3"))).click();

            // Go to cart
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Shopping cart"))).click();

            System.out.println("Test completed successfully.");

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
