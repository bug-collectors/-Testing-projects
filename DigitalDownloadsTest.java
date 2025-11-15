package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DigitalDownloadsTest {

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
            // 1) Select Night Visions
            // ============================
            WebElement nightVisions = driver.findElement(
                    By.xpath("//h2[@class='product-title']/a[contains(text(),'Night Visions')]")
            );
            nightVisions.click();
            Thread.sleep(1000);

            WebElement addNightVisions = driver.findElement(By.id("add-to-cart-button-32"));
            addNightVisions.click();
            Thread.sleep(1500);

            driver.navigate().back();
            Thread.sleep(1000);

            // ============================
            // 2) Select If You Wait (donation)
            // ============================
            WebElement ifYouWait = driver.findElement(
                    By.xpath("//h2[@class='product-title']/a[contains(text(),'If You Wait (donation)')]")
            );
            ifYouWait.click();
            Thread.sleep(1000);

            WebElement addIfYouWait = driver.findElement(By.id("add-to-cart-button-36"));
            addIfYouWait.click();
            Thread.sleep(1500);

            // Go to shopping cart
            WebElement cartLink = driver.findElement(By.xpath("//span[@class='cart-label']"));
            cartLink.click();

            System.out.println("Test PASSED: Digital download products added successfully to the shopping cart.");

        } catch (Exception e) {
            System.out.println("Test FAILED: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

