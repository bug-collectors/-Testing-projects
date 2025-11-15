package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BooksShoppingTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://demo.nopcommerce.com/");

            // Click on Books tab
            WebElement booksTab = driver.findElement(
                    By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Books']")
            );
            booksTab.click();

            // List of book names
            String[] books = {
                    "Fahrenheit 451 by Ray Bradbury",
                    "First Prize Pies",
                    "Pride and Prejudice"
            };

            // Add each book to the cart
            for (String book : books) {
                WebElement addButton = driver.findElement(
                        By.xpath("//h2[@class='product-title']/a[contains(text(),'" + book + "')]/../../..//button[contains(text(),'Add to cart')]")
                );

                addButton.click();
                Thread.sleep(1500);
            }

            System.out.println("Test PASSED: All books added successfully to the shopping cart.");

        } catch (Exception e) {
            System.out.println("Test FAILED: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}


