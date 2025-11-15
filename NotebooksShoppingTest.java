package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NotebooksShoppingTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://demo.nopcommerce.com/");

            // Click on Computers tab
            WebElement computersTab = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));
            computersTab.click();

            // Click on Notebooks tab
            WebElement notebooksTab = driver.findElement(By.xpath("//h2[@class='title']//a[normalize-space()='Notebooks']"));
            notebooksTab.click();

            // ============== List of Notebooks to Add ==============
            String[] notebookNames = {
                    "Apple MacBook Pro 13-inch",
                    "Asus N551JK-XO076H Laptop",
                    "HP Envy 6-1180ca 15.6-Inch Sleekbook",
                    "HP Spectre XT Pro UltraBook",
                    "Lenovo Thinkpad X1 Carbon Laptop",
                    "Samsung Series 9 NP900X4C Premium Ultrabook"
            };

            // ================= Add All Products ====================
            for (String product : notebookNames) {

                // Find the Add to Cart button for each product
                WebElement addButton = driver.findElement(
                        By.xpath("//h2[@class='product-title']/a[contains(text(),'" + product + "')]/../../..//button[contains(text(),'Add to cart')]")
                );

                addButton.click();
                Thread.sleep(2000);

                // Special validation for Apple MacBook Pro — this product REQUIRES QTY = 2
                if (product.contains("MacBook")) {
                    // Check if the warning appeared
                    boolean warningAppeared = driver.getPageSource().contains("The minimum quantity allowed for purchase is 2");

                    if (warningAppeared) {
                        System.out.println("Test FAILED: Apple MacBook Pro cannot be added with quantity less than 2.");
                        return;   // stop the test
                    }
                }
            }

            System.out.println("Test PASSED: All notebook products added successfully.");

        } catch (Exception e) {
            System.out.println("Test FAILED: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

