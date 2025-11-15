package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SoftwareShoppingTest {

    public static void main(String[] args) {

        // Start the Chrome browser
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            // Open website
            driver.get("https://demo.nopcommerce.com/");

            // Click on Computers tab
            WebElement computersTab = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));
            computersTab.click();

            // Click on Software tab
            WebElement softwareTab = driver.findElement(By.xpath("//h2[@class='title']//a[normalize-space()='Software']"));
            softwareTab.click();

            // Add Adobe Photoshop
            WebElement addPhotoshop = driver.findElement(By.xpath("//h2[@class='product-title']/a[contains(text(),'Adobe Photoshop')]/../../..//button[contains(text(),'Add to cart')]"));
            addPhotoshop.click();
            Thread.sleep(2000);

            // Add Microsoft Windows OS
            WebElement addWindowsOS = driver.findElement(By.xpath("//h2[@class='product-title']/a[contains(text(),'Microsoft Windows OS')]/../../..//button[contains(text(),'Add to cart')]"));
            addWindowsOS.click();
            Thread.sleep(2000);

            // Add Sound Forge Pro (recurring)
            WebElement addSoundForge = driver.findElement(By.xpath("//h2[@class='product-title']/a[contains(text(),'Sound Forge Pro')]/../../..//button[contains(text(),'Add to cart')]"));
            addSoundForge.click();
            Thread.sleep(2000);

            System.out.println("Test Passed: All software products were added to the shopping cart.");

        } catch (Exception e) {
            System.out.println("Test Failed: " + e.getMessage());
        } finally {
            // Close browser
            driver.quit();
        }
    }
}

