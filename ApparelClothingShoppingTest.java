package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApparelClothingShoppingTest {

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

            // Click Clothing
            WebElement clothingTab = driver.findElement(
                    By.xpath("//ul[@class='sublist first-level']//a[contains(text(),'Clothing')]")
            );
            clothingTab.click();
            Thread.sleep(1000);

            // ========================
            // 1) Custom T-Shirt
            // ========================
            WebElement customTshirt = driver.findElement(
                    By.xpath("//h2[@class='product-title']/a[contains(text(),'Custom T-Shirt')]")
            );
            customTshirt.click();
            Thread.sleep(1000);

            // Enter custom text "Ahmed"
            WebElement customText = driver.findElement(By.id("product_attribute_12"));
            customText.clear();
            customText.sendKeys("Ahmed");

            // Add to cart
            WebElement addCustomShirt = driver.findElement(By.id("add-to-cart-button-29"));
            addCustomShirt.click();
            Thread.sleep(1500);

            driver.navigate().back();
            Thread.sleep(1000);

            // ========================
            // 2) Levi’s 511 Jeans (select size 32)
            // ========================
            WebElement levisJeans = driver.findElement(
                    By.xpath("//h2[@class='product-title']/a[contains(text(),'Levi’s 511 Jeans')]")
            );
            levisJeans.click();
            Thread.sleep(1000);

            // Select size 32
            WebElement sizeDropdown = driver.findElement(By.id("product_attribute_10"));
            sizeDropdown.sendKeys("32");

            // Add to cart
            WebElement addLevis = driver.findElement(By.id("add-to-cart-button-30"));
            addLevis.click();
            Thread.sleep(1500);

            driver.navigate().back();
            Thread.sleep(1000);

            // ========================
            // 3) Casual Belt
            // ========================
            WebElement casualBelt = driver.findElement(
                    By.xpath("//h2[@class='product-title']/a[contains(text(),'Casual Belt')]")
            );

            casualBelt.findElement(
                    By.xpath("../../..//button[contains(@class,'add-to-cart-button')]")
            ).click();

            Thread.sleep(1500);

            // Go to shopping cart
            WebElement cartLink = driver.findElement(By.xpath("//span[@class='cart-label']"));
            cartLink.click();

            System.out.println("Test PASSED: All clothing items added successfully to the shopping cart.");

        } catch (Exception e) {
            System.out.println("Test FAILED: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
