package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class CurrencyTestCase {
    public static void main(String[] args) {

        // 1. مسار ChromeDriver
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Hamed\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // 2. إعدادات ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");                 // fully automated
        options.addArguments("window-size=1920,1080");          // لضمان ظهور العناصر
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        // استخدام profile مؤق
        options.addArguments("user-data-dir=C:/Temp/TempProfile");
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // 3. افتح الموقع
            driver.get("https://demo.nopcommerce.com/");

            // 4. انتظار ظهور الـ dropdown
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement currencyDropdown = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("customerCurrency"))
            );

            // 5. scroll للـ dropdown لو محتاج
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", currencyDropdown);

            // 6. اختيار Euro باستخدام value
            Select select = new Select(currencyDropdown);
            select.selectByValue("https://demo.nopcommerce.com/changecurrency/6?returnUrl=%2Fcamera-photo");

            // 7. انتظار أول سعر بعد تغيير العملة
            WebElement firstPrice = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='prices'])[1]"))
            );

            String priceText = firstPrice.getText();

            // 8. تحقق من ظهور رمز اليورو
            if (priceText.contains("€")) {
                System.out.println("PASS ― Currency changed successfully to EUR");
            } else {
                System.out.println("FAIL ― Currency did NOT change to EUR");
                System.out.println("Price text was: " + priceText);
            }

        } catch (Exception e) {
            System.out.println("Test Failed due to error: " + e.getMessage());

        } finally {
            driver.quit();
        }
    }
}
