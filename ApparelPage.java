package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ApparelPage {

    public static void main(String[] args) {

        // مسار الـ chromedriver اللي شغّال معاكي
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Hamed\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
            // افتحي الموقع الأساسي
            driver.get("https://demo.nopcommerce.com/");

            // اضغطي على Apparel من القائمة
            WebElement apparelLink = driver.findElement(By.linkText("Apparel"));
            apparelLink.click();

            // استني الصفحة لحد ما Title يظهر
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h1[text()='Apparel']")
            ));

            // تأكيد فتح الصفحة
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("apparel")) {
                System.out.println("PASS — Apparel page opened successfully!");
            } else {
                System.out.println("FAIL — Apparel page did not open!");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        } finally {
            try { Thread.sleep(2000); } catch (Exception ignored) {}
            driver.quit();
        }
    }
}
