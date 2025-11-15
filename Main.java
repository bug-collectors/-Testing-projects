package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
            // 1. هنا حطي السطر قبل إنشاء الـ ChromeDriver
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hamed\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
        try {
            // 2. افتح الصفحة
            driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

            // 3. تعبئة البيانات
            WebElement firstName = driver.findElement(By.id("FirstName"));
            WebElement lastName = driver.findElement(By.id("LastName"));
            WebElement email = driver.findElement(By.id("Email"));
            WebElement password = driver.findElement(By.id("Password"));
            WebElement confirmPassword = driver.findElement(By.id("ConfirmPassword"));

            firstName.sendKeys("Menna");
            lastName.sendKeys("Hamed");
            email.sendKeys("menna" + System.currentTimeMillis() + "@example.com"); // لتجنب تكرار البريد
            password.sendKeys("Test1234!");
            confirmPassword.sendKeys("Test1234!");

            // 4. اضغط زر Register
            WebElement registerButton = driver.findElement(By.id("register-button"));
            registerButton.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("result")));

            if(successMessage.getText().contains("Your registration completed")) {
                System.out.println("Registration successful!");
            } else {
                System.out.println("Registration failed!");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Something went wrong", e);
        } finally {
            // 6. اغلق المتصفح بعد 3 ثواني
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignored) {
            }
            driver.quit();
        }
        }
    }
