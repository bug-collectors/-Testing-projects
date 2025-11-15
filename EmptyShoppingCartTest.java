package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class EmptyShoppingCartTest {

    public static void main(String[] args) {

        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-features=OptimizationGuideModelDownloading");
       


        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            // Step 1: Open website
            driver.get("https://demo.nopcommerce.com/");

            // Step 2: Add a product to cart (example: "Build your own computer")
            driver.findElement(By.linkText("Computers")).click();
            driver.findElement(By.linkText("Desktops")).click();
            driver.findElement(By.linkText("Build your own computer")).click();
            driver.findElement(By.id("add-to-cart-button-1")).click();
            Thread.sleep(1500);

            // Step 3: Go to Shopping Cart
            driver.findElement(By.linkText("Shopping cart")).click();

            // Step 4: Remove the product
            driver.findElement(By.name("removefromcart")).click();
            driver.findElement(By.name("updatecart")).click();
            Thread.sleep(1000);

            // Step 5: Verify empty cart message
            String emptyMessage = driver.findElement(By.cssSelector("div.order-summary-content")).getText();
            if (emptyMessage.contains("Your shopping cart is empty!")) {
                System.out.println("Product removed and empty cart message displayed");
            } else {
                System.out.println("Empty cart message not displayed");
            }

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

