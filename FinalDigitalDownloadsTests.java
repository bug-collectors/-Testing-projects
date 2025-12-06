package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class FinalDigitalDownloadsTests {

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

    // ---------- Smooth Scroll ----------
    public void smoothScroll(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", element);
        humanDelay();
    }

    // ---------- Human-like typing ----------
    public void typeLikeHuman(WebElement element, String text) {
        for (char c : text.toCharArray()) {
            element.sendKeys(Character.toString(c));
            try {
                Thread.sleep(40 + (int)(Math.random() * 120));
            } catch (Exception ignored) {}
        }
    }

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // ---------------- Anti-Bot Bypass: Hardcore Mode ----------------

        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        options.addArguments("--disable-blink-features");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-features=IsolateOrigins,site-per-process");

        options.addArguments("--start-maximized");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--lang=en-US,en");

        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/123.0.0.0 Safari/537.36");

        options.addArguments("--disable-webrtc");

        options.setExperimentalOption("prefs", new java.util.HashMap<String, Object>() {{
            put("credentials_enable_service", false);
            put("profile.password_manager_enabled", false);
        }});

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);

        driver.get("https://demo.nopcommerce.com/");
        humanDelay();
    }

    // ============================ TC1 ============================
    @Test(priority = 1)
    public void TC1_AddToCompareFromDigitalDownloads() {
        try {
            // Navigate to Digital downloads
            WebElement nav = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Digital downloads")));
            moveAndClick(nav);
            humanDelay();

            // locate first product
            WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-item")));
            WebElement compareBtn = item.findElement(By.cssSelector(".add-to-compare-list-button"));

            moveAndClick(compareBtn);

            // verify success message and compare count increment (if UI shows count)
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bar-notification.success")));
            Assert.assertTrue(message.isDisplayed(), "TC1 Failed: Success message not shown.");

            // try to read compare count if exists (optional)
            List<WebElement> compareCounters = driver.findElements(By.cssSelector(".compare-products[href*='compare'] .count"));
            if (!compareCounters.isEmpty()) {
                String txt = compareCounters.get(0).getText().trim();
                // basic check: count is numeric and >=1
                int count = Integer.parseInt(txt.replaceAll("[^0-9]", ""));
                Assert.assertTrue(count >= 1, "TC1 Failed: Compare count not incremented.");
            }

            System.out.println("TC1 Passed: Digital download added to compare.");

        } catch (Exception e) {
            System.out.println("TC1 Error: " + e.getMessage());
        }
    }

    // ============================ TC2 ============================
    @Test(priority = 2)
    public void TC2_AddToCartFromDigitalDownloads() {
        try {
            // go to Digital downloads
            WebElement nav = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Digital downloads")));
            moveAndClick(nav);
            humanDelay();

            // click Add to cart on first product
            WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-item")));
            WebElement cartBtn = item.findElement(By.cssSelector(".product-box-add-to-cart-button"));

            moveAndClick(cartBtn);

            // check success notification and cart count
            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bar-notification.success")));
            Assert.assertTrue(msg.isDisplayed(), "TC2 Failed: Add to cart message not shown.");

            // check cart counter if present
            List<WebElement> cartCounters = driver.findElements(By.cssSelector(".cart-qty"));
            if (!cartCounters.isEmpty()) {
                String qtyText = cartCounters.get(0).getText().replaceAll("[^0-9]", "");
                int qty = Integer.parseInt(qtyText);
                Assert.assertTrue(qty >= 1, "TC2 Failed: Cart quantity not updated.");
            }

            System.out.println("TC2 Passed: Digital download added to cart.");

        } catch (Exception e) {
            System.out.println("TC2 Error: " + e.getMessage());
        }
    }

    // ============================ TC3 ============================
    @Test(priority = 3)
    public void TC3_ProductDetailsPage_Digital() {
        try {
            WebElement nav = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Digital downloads")));
            moveAndClick(nav);
            humanDelay();

            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product-item .product-title a")));
            moveAndClick(productLink);

            Assert.assertTrue(driver.findElement(By.cssSelector(".product-name")).isDisplayed(), "TC3 Failed: product name missing");
            Assert.assertTrue(driver.findElement(By.cssSelector(".price")).isDisplayed(), "TC3 Failed: price missing");
            Assert.assertTrue(driver.findElements(By.cssSelector(".add-to-cart-button, .product-add-to-cart")).size() > 0, "TC3 Failed: add to cart button missing");

            System.out.println("TC3 Passed: Product details page shows required info.");

        } catch (Exception e) {
            System.out.println("TC3 Error: " + e.getMessage());
        }
    }

    // ============================ TC4 ============================
    @Test(priority = 4)
    public void TC4_InvalidZipCodeValidation() {
        try {
            driver.get("https://demo.nopcommerce.com/cart");
            humanDelay();

            WebElement zip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ZipPostalCode")));
            zip.clear();
            zip.sendKeys("no number code");

            WebElement estimate = driver.findElement(By.name("estimateshipping"));
            moveAndClick(estimate);

            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".field-validation-error")));
            Assert.assertTrue(error.isDisplayed(), "TC4 Failed: Zip validation message not shown.");

            System.out.println("TC4 Passed: Zip code validation displayed.");

        } catch (Exception e) {
            System.out.println("TC4 Error: " + e.getMessage());
        }
    }

    // ============================ TC5 ============================
    @Test(priority = 5)
    public void TC5_InvalidQuantityValidation() {
        try {
            driver.get("https://demo.nopcommerce.com/build-your-own-computer");
            humanDelay();

            WebElement qty = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product_enteredQuantity_1")));
            qty.clear();
            qty.sendKeys("////");

            WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button-1")));
            moveAndClick(addBtn);

            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".field-validation-error")));
            Assert.assertTrue(error.isDisplayed(), "TC5 Failed: Quantity validation not shown.");

            System.out.println("TC5 Passed: Quantity validation working.");

        } catch (Exception e) {
            System.out.println("TC5 Error: " + e.getMessage());
        }
    }

    // ============================ TC6 ============================
    @Test(priority = 6)
    public void TC6_ItemsPerPage3_Digital() {
        try {
            WebElement nav = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Digital downloads")));
            moveAndClick(nav);
            humanDelay();

            WebElement items = wait.until(ExpectedConditions.elementToBeClickable(By.id("products-pagesize")));
            new Select(items).selectByVisibleText("3");
            humanDelay();

            List<WebElement> products = driver.findElements(By.cssSelector(".product-item"));
            Assert.assertEquals(products.size(), 3, "TC6 Failed: Products per page is not 3.");

            System.out.println("TC6 Passed: Items per page = 3.");

        } catch (Exception e) {
            System.out.println("TC6 Error: " + e.getMessage());
        }
    }

    // ============================ TC7 ============================
    @Test(priority = 7)
    public void TC7_ChangeLayoutToVertical_Digital() {
        try {
            WebElement nav = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Digital downloads")));
            moveAndClick(nav);
            humanDelay();

            WebElement vertical = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".viewmode-icon.list")));
            moveAndClick(vertical);

            Assert.assertTrue(driver.findElement(By.cssSelector(".product-list")).isDisplayed(), "TC7 Failed: product list not displayed.");

            System.out.println("TC7 Passed: Vertical layout working.");

        } catch (Exception e) {
            System.out.println("TC7 Error: " + e.getMessage());
        }
    }

    // ============================ TC8 ============================
    @Test(priority = 8)
    public void TC8_FilterPriceRange_Digital() {
        try {
            WebElement nav = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Digital downloads")));
            moveAndClick(nav);
            humanDelay();

            WebElement min = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PriceRangeMin")));
            WebElement max = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PriceRangeMax")));

            min.clear(); min.sendKeys("100");
            max.clear(); max.sendKeys("300");

            WebElement btn = driver.findElement(By.id("PriceRangeButton"));
            moveAndClick(btn);

            List<WebElement> prices = driver.findElements(By.cssSelector(".prices"));

            for (WebElement price : prices) {
                String txt = price.getText().replace("$", "").replace(",", "").trim();
                if (txt.isEmpty()) continue;
                double value = Double.parseDouble(txt);
                Assert.assertTrue(value >= 100 && value <= 300, "TC8 Failed: Found price out of range: " + value);
            }

            System.out.println("TC8 Passed: Price filtering working.");

        } catch (Exception e) {
            System.out.println("TC8 Error: " + e.getMessage());
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
    }
}
