package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;

public class communitypoll {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Hamed\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get("https://demo.nopcommerce.com/");

            // Scroll to poll
            WebElement pollSection = driver.findElement(By.id("poll-block-1"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pollSection);

            // Get question text
            String question = driver.findElement(By.cssSelector(".poll-display-text")).getText().trim();

            // Get options
            List<WebElement> options = driver.findElements(By.cssSelector(".poll-options label"));

            // Expected data
            String expectedQuestion = "DO YOU LIKE NOPCOMMERCE?";
            String[] expectedOptions = {"Excellent", "Good", "Poor", "Very bad"};

            boolean pass = true;

            // Check question
            if (!question.equals(expectedQuestion)) {
                pass = false;
            }

            // Check number of options
            if (options.size() != expectedOptions.length) {
                pass = false;
            }

            // Check options text
            for (int i = 0; i < expectedOptions.length; i++) {
                String actual = options.get(i).getText().trim();
                if (!actual.equals(expectedOptions[i])) {
                    pass = false;
                }
            }

            // Final result
            if (pass) {
                System.out.println("Test Case PASS 🎉 Community Poll is correct");
            } else {
                System.out.println("Test Case FAIL ❌ Poll question or options are wrong");
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}


