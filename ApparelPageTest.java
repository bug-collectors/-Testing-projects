package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApparelPageTest extends BeforeTest {

    @Test(priority = 3)
    public void openApparelPage() {
        driver.get("https://demo.nopcommerce.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement apparelLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Apparel"))
        );
        apparelLink.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Apparel']")));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.toLowerCase().contains("apparel"),
                "Apparel page did NOT open!");

        System.out.println("PASS — Apparel page opened successfully!");
    }

    @Test(priority = 2)
    public void apparelSubcategoryNavigationTest() {
        driver.get("https://demo.nopcommerce.com/apparel"); // <- أضف هذا السطر
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // جمع كل الـ subcategories
        List<WebElement> subCategories = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(".sub-category-item .title a")
                )
        );

        List<String> actualSubCategories = new ArrayList<>();
        for (WebElement cat : subCategories) {
            actualSubCategories.add(cat.getText().trim());
        }

        List<String> expectedSubCategories = Arrays.asList("Shoes", "Clothing", "Accessories");
        Assert.assertTrue(actualSubCategories.containsAll(expectedSubCategories),
                "Subcategories list is incorrect.");

        // اضغط على Clothing
        WebElement clothing = driver.findElement(By.linkText("Clothing"));
        clothing.click();

        // انتظر الصفحة الجديدة وتأكد URL و H1
        wait.until(ExpectedConditions.urlContains("/clothing"));
        WebElement clothingH1 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.page-title h1"))
        );
        Assert.assertEquals(clothingH1.getText().trim(), "Clothing", "Clothing page H1 is incorrect.");

        System.out.println("PASS — Clothing page opened successfully!");
    }
    @Test(priority = 1)
    public void clothingProductNavigationTest() {
        driver.get("https://demo.nopcommerce.com/clothing");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // جمع كل المنتجات في صفحة Clothing
        List<WebElement> products = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(".product-item .product-title a")
                )
        );

        WebElement firstProduct = products.get(0);
        String productName = firstProduct.getText().trim();
        firstProduct.click();

        WebElement h1 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.tagName("h1"))
        );

        Assert.assertEquals(h1.getText().trim(), productName,
                "Product page H1 does NOT match the clicked product name!");

        System.out.println("PASS — Product page for '" + productName + "' opened successfully!");
    }

}
