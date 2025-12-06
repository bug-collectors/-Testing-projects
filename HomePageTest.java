package test;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePageTest extends BeforeTest {
    @Test(priority = 1)
        public void featuredProductsTest() throws InterruptedException {
            driver.get("https://demo.nopcommerce.com/");

            WebElement featuredSection = driver.findElement(By.xpath("//h2[text()='Featured products']/following-sibling::div"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", featuredSection);
            Thread.sleep(2000);

            List<WebElement> productElements = driver.findElements(By.cssSelector(".product-title a"));
            List<String> actualProducts = new ArrayList<>();
            for (WebElement product : productElements) {
                actualProducts.add(product.getText().trim());
            }

            List<String> expectedProducts = Arrays.asList(
                    "computer",
                    "Apple MacBook Pro 13-inch",
                    "HTC One M8 Android L 5.0 Lollipop",
                    "$25 Virtual Gift Card"
            );

            Assert.assertTrue(actualProducts.containsAll(expectedProducts),
                    "Featured Products section does NOT show the correct product list.");
        }
    @Test(priority = 2)
        public void communityPollTest() {
            driver.get("https://demo.nopcommerce.com/");

            WebElement pollSection = driver.findElement(By.id("poll-block-1"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pollSection);

            String question = driver.findElement(By.cssSelector(".poll-display-text")).getText().trim();
            List<WebElement> options = driver.findElements(By.cssSelector(".poll-options label"));

            String expectedQuestion = "DO YOU LIKE NOPCOMMERCE?";
            String[] expectedOptions = {"Excellent", "Good", "Poor", "Very bad"};

            Assert.assertEquals(question, expectedQuestion, "Poll question is incorrect.");
            Assert.assertEquals(options.size(), expectedOptions.length, "Poll options count mismatch.");

            for (int i = 0; i < expectedOptions.length; i++) {
                Assert.assertEquals(options.get(i).getText().trim(), expectedOptions[i],
                        "Option " + (i+1) + " text mismatch.");
            }
        }

    @Test(priority = 3)
    public void currencyTest() {
        driver.get("https://demo.nopcommerce.com/");

        // اختار اليورو من الـ dropdown
        org.openqa.selenium.support.ui.Select select =
                new org.openqa.selenium.support.ui.Select(driver.findElement(By.id("customerCurrency")));
        select.selectByVisibleText("Euro");

        // انتظر لحد ما عنصر السعر يظهر
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement price = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".prices"))
        );

        // Assertion بسيط
        Assert.assertTrue(price.getText().contains("€"), "Currency did NOT change to Euro.");
    }
    @Test(priority = 4)
    public void newsSectionTest() {
        driver.get("https://demo.nopcommerce.com/");

        // Scroll to News section
        WebElement newsHeader = driver.findElement(By.cssSelector("h2.title"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newsHeader);

        // Find all news items
        List<WebElement> newsItems = driver.findElements(By.cssSelector("div.news-items article.news-item"));
        Assert.assertTrue(newsItems.size() > 0, "No news items found on the homepage.");

        // Optional: تحقق من أن كل عنصر فيه عنوان ونص
        for (WebElement item : newsItems) {
            String title = item.findElement(By.cssSelector("h3")).getText();
            String body = item.findElement(By.cssSelector("section.news-body")).getText();
            Assert.assertFalse(title.isEmpty(), "News item title is empty.");
            Assert.assertFalse(body.isEmpty(), "News item body is empty.");
        }
    }
        }



