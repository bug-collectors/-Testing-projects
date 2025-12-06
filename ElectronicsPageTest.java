package test;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class ElectronicsPageTest extends BeforeTest {
    @Test(priority = 1)
    public void openElectronicsPage() {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.linkText("Electronics")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.titleContains("Electronics"));
        Assert.assertTrue(driver.getTitle().contains("Electronics"),
                "Electronics page did NOT open!");
    }
    @Test(priority = 2)
    public void displayProductsDropdownTest() {
        driver.get("https://demo.nopcommerce.com/camera-photo");

        // إيجاد الـ dropdown الخاص بعدد المنتجات
        WebElement displayDropdown = driver.findElement(By.id("products-pagesize"));
        Select select = new Select(displayDropdown);

        int[] expectedOptions = {3, 6, 9};
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // مدة أطول

        for (int option : expectedOptions) {
            select.selectByVisibleText(String.valueOf(option));

            // انتظر لحد ما يظهر أي عنصر جديد بعد التغيير
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                    By.cssSelector(".product-item"), 0
            ));

            // احصل على العناصر الحالية بعد الانتظار
            List<WebElement> products = driver.findElements(By.cssSelector(".product-item"));

            // Assertion: عدد المنتجات <= الاختيار
            Assert.assertTrue(products.size() <= option,
                    "Number of products displayed (" + products.size() + ") exceeds selected value: " + option);

        }

    }
    @Test(priority = 3)
    public void sortByDropdownTest() {
        driver.get("https://demo.nopcommerce.com/camera-photo");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // انتظار ظهور dropdown
        WebElement sortDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("products-orderby"))
        );
        Select select = new Select(sortDropdown);

        // كل الاختيارات اللي موجودة
        String[] sortOptions = {
                "Position",
                "Name: A to Z",
                "Name: Z to A",
                "Price: Low to High",
                "Price: High to Low",
                "Created on"
        };

        for (String option : sortOptions) {
            select.selectByVisibleText(option);

            // انتظر لحد ما الصفحة تتفاعل مع الاختيار
            // ممكن ننتظر مثلاً ظهور أول عنصر جديد في قائمة المنتجات
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-item")));

            // Assertion بسيط للتأكد إن الاختيار اتحط
            String selectedOption = select.getFirstSelectedOption().getText().trim();
            Assert.assertEquals(selectedOption, option, "Sort by option mismatch for: " + option);
        }
    }
    @Test(priority = 4)
    public void changeLayoutTest() {
        driver.get("https://demo.nopcommerce.com/camera-photo");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement viewModeContainer = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-viewmode"))
        );

        WebElement gridButton = viewModeContainer.findElement(By.cssSelector(".viewmode-icon.grid"));
        WebElement listButton = viewModeContainer.findElement(By.cssSelector(".viewmode-icon.list"));

        // 1️⃣ اختبر Grid
        gridButton.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(listButton, "class", "selected")));
        Assert.assertTrue(gridButton.getAttribute("class").contains("selected"), "Grid layout was NOT selected!");

        // 2️⃣ اختبر List
        listButton.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(gridButton, "class", "selected")));
        Assert.assertTrue(listButton.getAttribute("class").contains("selected"), "List layout was NOT selected!");
    }
    @Test(priority = 5)
    public void navigateFromCameraToCellPhonesTest() {

        driver.get("https://demo.nopcommerce.com/camera-photo");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // نجيب الـ sidebar
        WebElement sidebar = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".listbox"))
        );

        // الضغط على Cell phones بدون Hover — باستخدام normalize-space
        WebElement cellPhonesLink = sidebar.findElement(By.xpath(".//a[normalize-space()='Cell phones']"));
        cellPhonesLink.click();

        // انتظار تحميل الصفحة
        wait.until(ExpectedConditions.urlContains("/cell-phones"));

        // Assertions
        Assert.assertTrue(driver.getCurrentUrl().contains("/cell-phones"),
                "Navigation to Cell phones page FAILED!");

        Assert.assertTrue(driver.getTitle().toLowerCase().contains("cell phones"),
                "Page title does NOT contain 'Cell phones'!");
    }
    @Test(priority = 6)
    public void manufacturersLinksTest() {

        driver.get("https://demo.nopcommerce.com/camera-photo");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Array of manufacturers: {link text, expected h1 text}
        String[][] manufacturers = {
                {"Apple", "Apple"},
                {"HP", "HP"}
        };

        for (String[] mf : manufacturers) {

            String linkName = mf[0];   // Apple or HP
            String expectedTitle = mf[1];

            // نضغط على اللينك من الـ sidebar باستخدام normalize-space
            WebElement link = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[@class='listbox']//a[normalize-space()='" + linkName + "']")
                    )
            );

            link.click();

            // ننتظر ظهور الـ H1
            WebElement header = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.page-title h1"))
            );

            // تحقق من الـ H1
            Assert.assertEquals(header.getText().trim(), expectedTitle,
                    "Wrong page title after clicking " + linkName);

            // نرجع للصفحة السابقة علشان نجرب اللينك اللي بعده
            driver.navigate().back();

            // ننتظر sidebar بعد الـ back
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".listbox")));
        }
    }
    @Test(priority = 7)
    public void tagsNavigationTest() {

        driver.get("https://demo.nopcommerce.com/camera-photo");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Tags to test
        String[] tags = {"awesome", "cool"};

        for (String tag : tags) {

            // نضغط على التاج من الـ sidebar باستخدام normalize-space
            WebElement tagLink = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[@class='tags']//a[normalize-space()='" + tag + "']")
                    )
            );

            tagLink.click();

            // ننتظر ظهور عنوان الصفحة
            WebElement header = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("div.page-title h1")
                    )
            );

            // expected h1 text
            String expectedH1 = "Products tagged with '" + tag + "'";

            // Assertion على الـ H1
            Assert.assertEquals(
                    header.getText().trim(),
                    expectedH1,
                    "Tag page title mismatch for tag: " + tag
            );

            // نرجع للصفحة السابقة لتجربة التاج الثاني
            driver.navigate().back();

            // نتأكد إن الـ sidebar tags ظهرت تاني بعد الرجوع
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tags")));
        }
    }



}
