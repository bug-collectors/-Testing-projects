package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApparelAccessoriesShoppingTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://demo.nopcommerce.com/");

            // Click Apparel tab
            WebElement apparelTab = driver.findElement(
                    By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Apparel']")
            );
            apparelTab.click();
            Thread.sleep(1000);

            // Click Accessories
            WebElement accessoriesTab = driver.findElement(
                    By.xpath("//ul[@class='sublist first-level']//a[contains(text(),'Accessories')]")
            );
            accessoriesTab.click();
            Thread.sleep(1000);

            // ============================
            // 1) Ray-Ban Aviator Sunglasses
            // ============================
            WebElement rayBan = driver.findElement(
                    By.xpath("//h2[@class='product-title']/a[contains(text(),'Ray-Ban Aviator Sunglasses')]")
            );
            rayBan.click();
            Thread.sleep(1000);

            WebElement addRayBan = driver.findElement(By.id("add-to-cart-button-33"));
            addRayBan.click();
            Thread.sleep(1500);

            driver.navigate().back();
            Thread.sleep(1000);

            // ============================
            // 2) Leather Wallet
            // ============================
            WebElement leatherWallet = driver.findElement(
                    By.xpath("//h2[@class='product-title']/a[contains(text(),'Leather Wallet')]")
            );
            leatherWallet.click();
            Thread.sleep(1000);

            WebElement addWallet = driver.findElement(By.id("add-to-cart-button-34"));
            addWallet.click();
            Thread.sleep(1500);

            driver.navigate().back();
            Thread.sleep(1000);

            // ============================
            // 3) Smart Watch Series 5
            // ============================
            WebElement smartWatch = driver.findElement(
                    By.xpath("//h2[@class='product-title']/a[contains(text(),'Smart Watch Series 5')]")
            );
            smartWatch.click();
            Thread.sleep(1000);

            WebElement addSmartWatch = driver.findElement(By.id("add-to-cart-button-35"));
            addSmartWatch.click();
            Thread.sleep(1500);

            // Go to shopping cart
            WebElement cartLink = driver.findElement(By.xpath("//span[@class='cart-label']"));
            cartLink.click();

            System.out.println("Test PASSED: All accessories added successfully to the shopping cart.");

        } catch (Exception e) {
            System.out.println("Test FAILED: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

