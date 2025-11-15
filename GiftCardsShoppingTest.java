package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GiftCardsShoppingTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://demo.nopcommerce.com/");

            // Click Gift Cards tab
            WebElement giftCardsTab = driver.findElement(
                    By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Gift Cards']")
            );
            giftCardsTab.click();
            Thread.sleep(1000);

            // ================================
            // 1) $25 Virtual Gift Card
            // ================================
            WebElement virtual25 = driver.findElement(
                    By.xpath("//h2[@class='product-title']/a[contains(text(),'$25 Virtual Gift Card')]")
            );
            virtual25.click();

            driver.findElement(By.id("giftcard_43_RecipientName")).sendKeys("moamen");
            driver.findElement(By.id("giftcard_43_RecipientEmail")).sendKeys("moamenabdalmanam@gmail.com");
            driver.findElement(By.id("giftcard_43_SenderName")).sendKeys("Moamen");
            driver.findElement(By.id("giftcard_43_SenderEmail")).sendKeys("moamenabdalmanam@gmail.com");
            driver.findElement(By.id("giftcard_43_Message")).sendKeys("Hi\nthis is for first product.");

            driver.findElement(By.id("add-to-cart-button-43")).click();
            Thread.sleep(1500);

            driver.navigate().back();
            Thread.sleep(1000);

            // ================================
            // 2) $50 Physical Gift Card
            // ================================
            WebElement physical50 = driver.findElement(
                    By.xpath("//h2[@class='product-title']/a[contains(text(),'$50 Physical Gift Card')]")
            );
            physical50.click();

            driver.findElement(By.id("giftcard_44_RecipientName")).sendKeys("Moamen");
            driver.findElement(By.id("giftcard_44_SenderName")).sendKeys("moamen");
            driver.findElement(By.id("giftcard_44_Message")).sendKeys("Hi");

            driver.findElement(By.id("add-to-cart-button-44")).click();
            Thread.sleep(1500);

            driver.navigate().back();
            Thread.sleep(1000);

            // ================================
            // 3) $100 Physical Gift Card
            // ================================
            WebElement physical100 = driver.findElement(
                    By.xpath("//h2[@class='product-title']/a[contains(text(),'$100 Physical Gift Card')]")
            );
            physical100.click();

            driver.findElement(By.id("giftcard_45_RecipientName")).sendKeys("moamen");
            driver.findElement(By.id("giftcard_45_SenderName")).sendKeys("moamen");
            driver.findElement(By.id("giftcard_45_Message")).sendKeys("hi");

            driver.findElement(By.id("add-to-cart-button-45")).click();
            Thread.sleep(1500);

            System.out.println("Test PASSED: All gift cards added successfully to the shopping cart.");

        } catch (Exception e) {
            System.out.println("Test FAILED: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
