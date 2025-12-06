package test;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegisterAndLogin extends BeforeTest{
    private final String firstName = "Menna";
    private final String lastName = "Hamed";
    private final String password = "Test1234!";
    private final String email = "menna" + System.currentTimeMillis() + "@example.com";

    @Test(priority = 1)
    public void registrationTest() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

        driver.findElement(By.id("register-button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("result"))
        );

        Assert.assertEquals(successMessage.getText(), "Your registration completed",
                "Registration failed!");
    }

    @Test(priority = 2)
    public void loginTest() {
        driver.get("https://demo.nopcommerce.com/login");

        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.login-button")).click();

        // بعد تسجيل الدخول مباشرة تحقق من وجود رابط "My account"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement accountLink = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.ico-account"))
        );

        Assert.assertTrue(accountLink.isDisplayed(), "Login failed! 'My account' link not visible.");
    }
}
