package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BooksPageTest {

    public static void main(String[] args) {

        // نفس المسار اللي اشتغل معاكي قبل كده بالظبط
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Hamed\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
            // افتحي الموقع
            driver.get("https://demo.nopcommerce.com/");

            // اضغطي على Books
            WebElement booksLink = driver.findElement(By.linkText("Books"));
            booksLink.click();

            // استني الصفحة تتحمل
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h1[text()='Books']")
            ));

            // تأكيد فتح الصفحة
            String url = driver.getCurrentUrl();
            if (url.contains("books")) {
                System.out.println("PASS — Books page opened successfully!");
            } else {
                System.out.println("FAIL — Books page did not open!");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        } finally {
            try { Thread.sleep(20000); } catch (Exception ignored) {}
            driver.quit();
        }
    }
}
