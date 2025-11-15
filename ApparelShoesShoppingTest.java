package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApparelShoesShoppingTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://demo.nopcommerce.com/");

            // Click Apparel
            WebElement apparelTab = driver.findElement(
                    By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Apparel']")
            );
            apparelTab.click();
            Thread.sleep(1000);

            // Click Shoes
            WebElement shoesTab = driver.findElement(
                    By.xpath("//ul[@class='sublist first-level']//a[contains(text(),'Shoes')]")
            );
            shoesTab.click();
            Thread.sleep(1000);

            // ========================
            // 1) Blue and Green Sneaker
            // ========================
            WebElement blueGreenSneaker = driver.findElement(
                    By.xpath("//h2[@class='product-title']/a[contains(text(),'Blue and Green Sneaker')]")
            );
            blueGreenSneaker.click();

            // Select size 8
            WebElement sizeDropdown = driver.findElement(By.id("product_attribute_9"));
            sizeDropdown.sendKeys("8");

            // Add to cart
            WebElement addSneaker = driver.findElement(By.id("add-to-cart-button-28"));
            addSneaker.click();
            Thread.sleep(1500);

            driver.navigate().back();
            Thread.sleep(1000);

            // ========================
            // 2) Nike Floral Trainer
            // ========================
            WebElement nikeFloral = driver.findElement(
                    By.xpath("//h2[@class='product-title']/a[contains(text(),'Nike Floral Trainer')]")
            );

            nikeFloral.findElement(
                    By.xpath("../../..//button[contains(@class,'add-to-cart-button')]")
            ).click();

            Thread.sleep(1500);

            // ========================
            // 3) Adidas Consortium Campus 80s Running Shoes
            // ========================
            WebElement adidasShoes = driver.findElement(
                    By.xpath("//h2[@class='product-title']/a[contains(text(),'adidas Consortium Campus 80s Running Shoes')]")
            );

            adidasShoes.findElement(
                    By.xpath("../../..//button[contains(@class,'add-to-cart-button')]")
            ).click();

            Thread.sleep(1500);

            // Go to shopping cart
            WebElement cartLink = driver.findElement(
                    By.xpath("//span[@class='cart-label']")
            );
            cartLink.click();

            System.out.println("Test PASSED: All shoes added successfully to the shopping cart.");

        } catch (Exception e) {
            System.out.println("Test FAILED: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
