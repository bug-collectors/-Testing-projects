package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class testcase2 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hamed\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://demo.nopcommerce.com/");

            // Scroll to News section
            WebElement newsHeader = driver.findElement(By.cssSelector("h2.title"));
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newsHeader);

            // Find all news items
            List<WebElement> newsItems = driver.findElements(By.cssSelector("div.news-items article.news-item"));

            System.out.println("Found " + newsItems.size() + " news items:");

            for(WebElement item : newsItems) {
                String title = item.findElement(By.cssSelector("h3")).getText();
                String body = item.findElement(By.cssSelector("section.news-body")).getText();
                System.out.println("Title: " + title);
                System.out.println("Body: " + body);
                System.out.println("-----");
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

