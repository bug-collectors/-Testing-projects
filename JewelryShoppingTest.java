package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JewelryShoppingTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://demo.nopcommerce.com/");

            // Click on Jewelry tab
            WebElement jewelryTab = driver.findElement(
                    By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Jewelry']")
            );
            jewelryTab.click();

            // Product names
            String rentalProduct = "Elegant Gemstone Necklace";
            String[] normalProducts = {
                    "Flower Girl Bracelet",
                    "Vintage Style Engagement Ring"
            };

            // Handle rental product (set rental dates)
            WebElement rentalLink = driver.findElement(
                    By.xpath("//h2[@class='product-title']/a[contains(text(),'" + rentalProduct + "')]")
            );
            rentalLink.click();

            // Set rental from October 10 → November 10
            WebElement startDate = driver.findElement(By.id("rental_start_date"));
            startDate.clear();
            startDate.sendKeys("10/10/2025");

            WebElement endDate = driver.findElement(By.id("rental_end_date"));
            endDate.clear();
            endDate.sendKeys("11/10/2025");

            // Add rental product to cart
            WebElement rentalAdd = driver.findElement(By.id("add-to-cart-button-40"));
            rentalAdd.click();

            Thread.sleep(1500);

            // Return back to Jewelry page
            driver.navigate().back();
            Thread.sleep(1000);

            // Add all the normal products
            for (String product : normalProducts) {
                WebElement addButton = driver.findElement(
                        By.xpath("//h2[@class='product-title']/a[contains(text(),'" + product + "')]/../../..//button[contains(text(),'Add to cart')]")
                );
                addButton.click();
                Thread.sleep(1500);
            }

            System.out.println("Test PASSED: All jewelry products added successfully (including rental item).");

        } catch (Exception e) {
            System.out.println("Test FAILED: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
