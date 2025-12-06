package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class FirefoxUserTests {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    // ---------- Human Delay ----------
    public void humanDelay() {
        try {
            Thread.sleep(600 + (int)(Math.random() * 1200));
        } catch (Exception ignored) {}
    }

    // ---------- Move & Click Like Human ----------
    public void moveAndClick(WebElement element) {
        try {
            actions.moveToElement(element).pause(250 + (int)(Math.random() * 350)).perform();
            humanDelay();
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    @BeforeClass
    public void setup() {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--lang=en-US");

        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);

        driver.get("https://demo.nopcommerce.com/");
        humanDelay();
    }

    // ============================ TC1 ============================
    @Test(priority = 1)
    public void TC1_RegisterNewUser() {
        try {
            WebElement registerTab = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Register")));
            moveAndClick(registerTab);

            driver.findElement(By.id("FirstName")).sendKeys("Moamen");
            driver.findElement(By.id("LastName")).sendKeys("Abdalmanam");
            driver.findElement(By.id("Email")).sendKeys("moamenabdalmanam@gmail.com");
            driver.findElement(By.id("Company")).sendKeys("Moamen");
            driver.findElement(By.id("Password")).sendKeys("Moamen123456789");
            driver.findElement(By.id("ConfirmPassword")).sendKeys("Moamen123456789");

            // Choose gender Male
            driver.findElement(By.id("gender-male")).click();

            moveAndClick(driver.findElement(By.id("register-button")));

            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".result")));
            Assert.assertTrue(successMsg.getText().contains("Your registration completed"), "Registration failed");

            System.out.println("TC1 Passed: Registration completed successfully.");

        } catch (Exception e) {
            System.out.println("TC1 Error: " + e.getMessage());
        }
    }

    // ============================ TC4 ============================
    @Test(priority = 2)
    public void TC4_LoginValidUser() {
        try {
            WebElement loginTab = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log in")));
            moveAndClick(loginTab);

            driver.findElement(By.id("Email")).sendKeys("moamenabdalmanam@gmail.com");
            driver.findElement(By.id("Password")).sendKeys("Moamen123456789");

            moveAndClick(driver.findElement(By.cssSelector("button.login-button")));

            WebElement accountLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("My account")));
            Assert.assertTrue(accountLink.isDisplayed(), "Login failed");

            System.out.println("TC4 Passed: Login successful.");

        } catch (Exception e) {
            System.out.println("TC4 Error: " + e.getMessage());
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
    }
}
