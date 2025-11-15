package org.example;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class ElectronicsPageTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hamed\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
            // تشغيل عادي (مش Headless)
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                // 1. افتح الموقع
            driver.get("https://demo.nopcommerce.com/");

                // 2. اضغط على Electronics
            WebElement electronicsLink = driver.findElement(By.linkText("Electronics"));
            electronicsLink.click();

                // 3. استنى الصفحة تفتح فعلياً
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h1[text()='Electronics']")
                ));

                // 4. تحقق إنك فعلاً على صفحة Electronics
            String currentUrl = driver.getCurrentUrl();
                if (currentUrl.contains("electronics")) {
                    System.out.println("PASS — Electronics page opened successfully!");
                } else {
                    System.out.println("FAIL — Electronics page did not open.");
                }

            } catch (Exception e) {
                System.out.println("Test Failed: " + e.getMessage());

            } finally {
                driver.quit();
            }
        }
    }


