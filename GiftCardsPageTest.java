package test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class GiftCardsPageTest extends BeforeTest {
    @Test(priority = 2)
    public void navigateToGiftCards() {
        driver.get("https://demo.nopcommerce.com/");
        WebElement giftCardsLink = driver.findElement(By.linkText("Gift Cards"));
        giftCardsLink.click();

        // تحقق من أن الصفحة فتحت
        String expectedTitle = "Gift Cards"; // ممكن تغيري حسب actual title
        String actualTitle = driver.getTitle();

        Assert.assertTrue(actualTitle.contains(expectedTitle), "Gift Cards page did not open correctly");

        // مثال: تحقق من وجود عنصر رئيسي في الصفحة
        WebElement header = driver.findElement(By.tagName("h1"));
        Assert.assertTrue(header.isDisplayed(), "Header not displayed on Gift Cards page");
    }
    @Test(priority = 1)
    public void sendGiftCardEmail() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/100-physical-gift-card");

        // الضغط على زر Email a Friend
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement friendEmailButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.email-a-friend-button")
        ));
        friendEmailButton.click();

        // إدخال بريدك الإلكتروني بعد ظهور الفورم
        WebElement yourEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#YourEmailAddress")
        ));
        yourEmail.sendKeys("your@example.com");

        // إدخال رسالة شخصية (اختياري)
        WebElement personalMessage = driver.findElement(By.id("PersonalMessage"));
        personalMessage.sendKeys("Happy Birthday! Enjoy this gift card.");

        // الضغط على زر الإرسال
        WebElement sendButton = driver.findElement(By.xpath("//button[text()='Send']"));
        sendButton.click();

        // انتظار ظهور رسالة الخطأ أو النجاح
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Only registered accounts can send an email')]")
        ));

        Assert.assertTrue(errorMsg.isDisplayed(), "Error message for unregistered account not displayed");
    }



}
