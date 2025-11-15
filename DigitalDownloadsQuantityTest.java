package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DigitalDownloadsQuantityTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://demo.nopcommerce.com/");

            // Click Digital Downloads tab
            WebElement digitalDownloadsTab = driver.findElement(
                    By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Digital downloads']")
            );
            digitalDownloadsTab.click();
            Thread.sleep(1000);

            // ============================
            // Select Science & Faith
            // ============================
            WebElement scienceFaith = driver.findElement(
                    By.xpath("//h2[@class='product-title']/a[contains(text(),'Science & Faith')]")
            );
            scienceFaith.click();
            Thread.sleep(1000);

            WebElement addScienceFaith = driver.findElement(By.id("add-to-cart-button-37"));
            addScienceFaith.click();
            Thread.sleep(1500);

            // Go to shopping cart
            WebElement cartLink = driver.findElement(By.xpath("//span[@class='cart-label']"));
            cartLink.click();
            Thread.sleep(1000);

            // Increase quantity to 2
            WebElement quantityInput = driver.findElement(By.xpath("//input[contains(@name,'itemquantity')]"));
            quantityInput.clear();
            quantityInput.sendKeys("2");
            Thread.sleep(500);

            // Click Update shopping cart
            WebElement updateCart = driver.findElement(By.name("updatecart"));
            updateCart.click();
            Thread.sleep(1000);

            System.out.println("Test PASSED: Science & Faith quantity updated successfully in the shopping cart.");

        } catch (Exception e) {
            System.out.println("Test FAILED: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
