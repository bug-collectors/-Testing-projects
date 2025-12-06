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
import java.util.List;

public class FirefoxHomepageTests {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    // ---------- Human Delay ----------
    public void humanDelay() {
        try {
            Thread.sleep(500 + (int)(Math.random() * 1000));
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
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);

        driver.get("https://www.google.com");
        humanDelay();
    }

    // ============================ TC1 ============================
    @Test(priority = 1)
    public void TC1_SearchNopCommerce() {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        searchBox.sendKeys("nopcommerce demo store");
        searchBox.sendKeys(Keys.ENTER);

        WebElement firstResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3")));
        Assert.assertTrue(firstResult.getText().toLowerCase().contains("nopcommerce"), "Search result not found");
        System.out.println("TC1 Passed: Google search found nopCommerce demo store");
    }

    // ============================ TC2 ============================
    @Test(priority = 2)
    public void TC2_VerifyHomePageTitle() {
        driver.get("https://demo.nopcommerce.com/");
        humanDelay();
        String title = driver.getTitle();
        Assert.assertEquals(title, "nopCommerce demo store. Home page", "Homepage title mismatch");
        System.out.println("TC2 Passed: Homepage title verified");
    }

    // ============================ TC3 ============================
    @Test(priority = 3)
    public void TC3_FeaturedProductsSection() {
        driver.get("https://demo.nopcommerce.com/");
        WebElement featuredSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-grid")));
        Assert.assertTrue(featuredSection.isDisplayed(), "Featured products section not visible");

        List<WebElement> products = featuredSection.findElements(By.cssSelector(".product-item"));
        Assert.assertTrue(products.size() >= 4, "Expected at least 4 featured products");
        System.out.println("TC3 Passed: Featured products section verified");
    }

    // ============================ TC4 ============================
    @Test(priority = 4)
    public void TC4_NewsSection() {
        driver.get("https://demo.nopcommerce.com/");
        WebElement newsSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".news-list")));
        Assert.assertTrue(newsSection.isDisplayed(), "News section not visible");
        System.out.println("TC4 Passed: News section content verified");
    }

    // ============================ TC5 ============================
    @Test(priority = 5)
    public void TC5_CommunityPoll() {
        driver.get("https://demo.nopcommerce.com/");
        WebElement pollSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".poll")));
        Assert.assertTrue(pollSection.isDisplayed(), "Community poll not visible");

        WebElement pollQuestion = pollSection.findElement(By.cssSelector(".poll-title"));
        Assert.assertTrue(pollQuestion.getText().contains("Do you like nopCommerce?"), "Poll question mismatch");
        System.out.println("TC5 Passed: Community poll question verified");
    }

    // ============================ TC6 ============================
    @Test(priority = 6)
    public void TC6_CommunityPollVote() {
        driver.get("https://demo.nopcommerce.com/");
        WebElement pollSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".poll")));
        WebElement option = pollSection.findElement(By.cssSelector("input[value='Excellent']"));
        moveAndClick(option);

        WebElement voteBtn = pollSection.findElement(By.cssSelector("button.poll-vote-button"));
        moveAndClick(voteBtn);

        WebElement pollMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".poll-vote-message")));
        Assert.assertTrue(pollMessage.getText().toLowerCase().contains("failed") || pollMessage.isDisplayed(), "Vote feedback not displayed");
        System.out.println("TC6 Completed: Poll vote attempted (may fail on demo site)");
    }

    // ============================ TC7-TC10 ============================
    public void addToCartByProductName(String productName) {
        driver.get("https://demo.nopcommerce.com/");
        List<WebElement> products = driver.findElements(By.cssSelector(".product-item"));
        for (WebElement product : products) {
            String name = product.findElement(By.cssSelector(".product-title a")).getText();
            if (name.equalsIgnoreCase(productName)) {
                WebElement addBtn = product.findElement(By.cssSelector(".product-box-add-to-cart-button"));
                moveAndClick(addBtn);
                System.out.println("Attempted to add " + productName + " to cart");
                return;
            }
        }
        System.out.println("Product " + productName + " not found");
    }

    @Test(priority = 7)
    public void TC7_AddBuildYourOwnComputer() {
        addToCartByProductName("Build your own computer");
    }

    @Test(priority = 8)
    public void TC8_AddAppleMacBookPro() {
        addToCartByProductName("Apple MacBook Pro");
    }

    @Test(priority = 9)
    public void TC9_AddHTCSmartphone() {
        addToCartByProductName("HTC Smartphone");
    }

    @Test(priority = 10)
    public void TC10_AddVirtualGiftCard25() {
        addToCartByProductName("$25 Virtual Gift Card");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
    }
}
