package test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class AllCartTest extends BeforeTest {
    // 1 – Apparel → Shoes
    @Test(priority = 1)
    public void testApparelShoes() throws InterruptedException {
        // Click Apparel
        driver.get("https://demo.nopcommerce.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(By.linkText("Apparel")).click();
        Thread.sleep(1000);

        // Click Shoes
        driver.findElement(By.linkText("Shoes")).click();
        Thread.sleep(1000);

        // Blue and Green Sneaker (size 8)
        WebElement blueGreenSneaker = driver.findElement(By.linkText("Blue and Green Sneaker"));
        blueGreenSneaker.click();
        driver.findElement(By.id("product_attribute_9")).sendKeys("8");
        driver.findElement(By.id("add-to-cart-button-28")).click();
        Thread.sleep(1000);
        driver.navigate().back();

        // Nike Floral Trainer
        driver.findElement(By.linkText("Nike Floral Trainer")).findElement(
                By.xpath("../../..//button[contains(@class,'add-to-cart-button')]")).click();
        Thread.sleep(1000);

        // Adidas Consortium Campus 80s Running Shoes
        driver.findElement(By.linkText("adidas Consortium Campus 80s Running Shoes")).findElement(
                By.xpath("../../..//button[contains(@class,'add-to-cart-button')]")).click();
        Thread.sleep(1000);
    }

    // 2 – Digital Downloads Quantity Test
    @Test(priority = 2)
    public void testDigitalDownloadsQuantity() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(By.linkText("Digital downloads")).click();
        Thread.sleep(1000);

        driver.findElement(By.linkText("Science & Faith")).click();
        driver.findElement(By.id("add-to-cart-button-37")).click();
        Thread.sleep(1000);

        driver.findElement(By.linkText("Shopping cart")).click();
        WebElement quantityInput = driver.findElement(By.xpath("//input[contains(@name,'itemquantity')]"));
        quantityInput.clear();
        quantityInput.sendKeys("2");
        driver.findElement(By.name("updatecart")).click();
        Thread.sleep(1000);
    }

    // 3 – Books
    @Test(priority = 3)
    public void testBooks() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(By.linkText("Books")).click();
        String[] books = {"Fahrenheit 451 by Ray Bradbury", "First Prize Pies", "Pride and Prejudice"};
        for (String book : books) {
            driver.findElement(By.xpath("//h2[@class='product-title']/a[contains(text(),'" + book + "')]/../../..//button[contains(text(),'Add to cart')]")).click();
            Thread.sleep(1000);
        }
    }

    // 4 – Computers Shopping
    @Test(priority = 4)
    public void testComputers() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(By.linkText("Computers")).click();
        driver.findElement(By.linkText("Desktops")).click();
        driver.findElement(By.linkText("Build your own computer")).click();
        driver.findElement(By.id("product_attribute_2")).sendKeys("2 GB");
        driver.findElement(By.id("product_attribute_3_6")).click();
        driver.findElement(By.id("add-to-cart-button-1")).click();
        Thread.sleep(1000);
        driver.navigate().back();
        driver.navigate().back();

        driver.findElement(By.linkText("Digital Storm VANQUISH 3 Custom Performance PC")).click();
        driver.findElement(By.id("add-to-cart-button-2")).click();
        driver.navigate().back();

        driver.findElement(By.linkText("Lenovo IdeaCentre 600 All-in-One PC")).click();
        driver.findElement(By.id("add-to-cart-button-3")).click();
    }

    // 5 – Apparel → Clothing
    @Test(priority = 5)
    public void testApparelClothing() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(By.linkText("Apparel")).click();
        driver.findElement(By.linkText("Clothing")).click();
        driver.findElement(By.linkText("Custom T-Shirt")).click();
        driver.findElement(By.id("product_attribute_12")).sendKeys("Ahmed");
        driver.findElement(By.id("add-to-cart-button-29")).click();
        Thread.sleep(1000);
        driver.navigate().back();

        driver.findElement(By.linkText("Levi’s 511 Jeans")).click();
        driver.findElement(By.id("product_attribute_10")).sendKeys("32");
        driver.findElement(By.id("add-to-cart-button-30")).click();
        Thread.sleep(1000);
        driver.navigate().back();

        driver.findElement(By.linkText("Casual Belt")).findElement(By.xpath("../../..//button[contains(@class,'add-to-cart-button')]")).click();
        Thread.sleep(1000);
    }

    // 6 – Apparel → Accessories
    @Test(priority = 6)
    public void testApparelAccessories() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(By.linkText("Apparel")).click();
        driver.findElement(By.linkText("Accessories")).click();

        driver.findElement(By.linkText("Ray-Ban Aviator Sunglasses")).click();
        driver.findElement(By.id("add-to-cart-button-33")).click();
        Thread.sleep(1000);
        driver.navigate().back();

        driver.findElement(By.linkText("Leather Wallet")).click();
        driver.findElement(By.id("add-to-cart-button-34")).click();
        Thread.sleep(1000);
        driver.navigate().back();

        driver.findElement(By.linkText("Smart Watch Series 5")).click();
        driver.findElement(By.id("add-to-cart-button-35")).click();
        Thread.sleep(1000);
    }

    // 7 – Gift Cards
    @Test(priority = 7)
    public void testGiftCards() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(By.linkText("Gift Cards")).click();

        // $25 Virtual
        driver.findElement(By.linkText("$25 Virtual Gift Card")).click();
        driver.findElement(By.id("giftcard_43_RecipientName")).sendKeys("moamen");
        driver.findElement(By.id("giftcard_43_RecipientEmail")).sendKeys("moamenabdalmanam@gmail.com");
        driver.findElement(By.id("giftcard_43_SenderName")).sendKeys("Moamen");
        driver.findElement(By.id("giftcard_43_SenderEmail")).sendKeys("moamenabdalmanam@gmail.com");
        driver.findElement(By.id("giftcard_43_Message")).sendKeys("Hi\nthis is for first product.");
        driver.findElement(By.id("add-to-cart-button-43")).click();
        Thread.sleep(1000);
        driver.navigate().back();

        // $50 Physical
        driver.findElement(By.linkText("$50 Physical Gift Card")).click();
        driver.findElement(By.id("giftcard_44_RecipientName")).sendKeys("Moamen");
        driver.findElement(By.id("giftcard_44_SenderName")).sendKeys("moamen");
        driver.findElement(By.id("giftcard_44_Message")).sendKeys("Hi");
        driver.findElement(By.id("add-to-cart-button-44")).click();
        Thread.sleep(1000);
        driver.navigate().back();

        // $100 Physical
        driver.findElement(By.linkText("$100 Physical Gift Card")).click();
        driver.findElement(By.id("giftcard_45_RecipientName")).sendKeys("moamen");
        driver.findElement(By.id("giftcard_45_SenderName")).sendKeys("moamen");
        driver.findElement(By.id("giftcard_45_Message")).sendKeys("hi");
        driver.findElement(By.id("add-to-cart-button-45")).click();
        Thread.sleep(1000);
    }

    // 8 – Empty Shopping Cart
    @Test(priority = 8)
    public void testEmptyCart() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(By.linkText("Computers")).click();
        driver.findElement(By.linkText("Desktops")).click();
        driver.findElement(By.linkText("Build your own computer")).click();
        driver.findElement(By.id("add-to-cart-button-1")).click();
        Thread.sleep(1000);

        driver.findElement(By.linkText("Shopping cart")).click();
        driver.findElement(By.name("removefromcart")).click();
        driver.findElement(By.name("updatecart")).click();
        Thread.sleep(1000);
    }

    // 9 – Electronics
    @Test(priority = 9)
    public void testElectronics() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(By.linkText("Electronics")).click();
        driver.findElement(By.linkText("Camera & photo")).click();

        driver.findElement(By.linkText("Nikon D5500 DSLR")).click();
        driver.findElement(By.id("product_attribute_14_43")).click();
        driver.findElement(By.id("add-to-cart-button-14")).click();
        driver.findElement(By.id("product_attribute_14_44")).click();
        driver.findElement(By.id("add-to-cart-button-14")).click();
        driver.navigate().back();

        driver.findElement(By.linkText("Apple iCam")).click();
        driver.findElement(By.id("add-to-cart-button-11")).click();
        driver.navigate().back();

        driver.findElement(By.linkText("Leica T Mirrorless Digital Camera")).click();
        driver.findElement(By.id("add-to-cart-button-16")).click();
    }

    // 10 – Software
    @Test(priority = 10)
    public void testSoftware() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(By.linkText("Computers")).click();
        driver.findElement(By.linkText("Software")).click();

        driver.findElement(By.linkText("Adobe Photoshop")).findElement(By.xpath("../../..//button[contains(text(),'Add to cart')]")).click();
        Thread.sleep(1000);

        driver.findElement(By.linkText("Microsoft Windows OS")).findElement(By.xpath("../../..//button[contains(text(),'Add to cart')]")).click();
        Thread.sleep(1000);

        driver.findElement(By.linkText("Sound Forge Pro (recurring)")).findElement(By.xpath("../../..//button[contains(text(),'Add to cart')]")).click();
        Thread.sleep(1000);
    }

    // 11 – Notebooks
    @Test(priority = 11)
    public void testNotebooks() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(By.linkText("Computers")).click();
        driver.findElement(By.linkText("Notebooks")).click();

        String[] notebookNames = {
                "Apple MacBook Pro 13-inch",
                "Asus N551JK-XO076H Laptop",
                "HP Envy 6-1180ca 15.6-Inch Sleekbook",
                "HP Spectre XT Pro UltraBook",
                "Lenovo Thinkpad X1 Carbon Laptop",
                "Samsung Series 9 NP900X4C Premium Ultrabook"
        };

        for (String product : notebookNames) {
            driver.findElement(By.xpath("//h2[@class='product-title']/a[contains(text(),'" + product + "')]/../../..//button[contains(text(),'Add to cart')]")).click();
            Thread.sleep(1000);

            if (product.contains("MacBook")) {
                boolean warningAppeared = driver.getPageSource().contains("The minimum quantity allowed for purchase is 2");
                if (warningAppeared) {
                    System.out.println("Test FAILED: Apple MacBook Pro cannot be added with quantity less than 2.");
                    return;
                }
            }
        }
    }

    // 12 – Jewelry
    @Test(priority = 12)
    public void testJewelry() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(By.linkText("Jewelry")).click();

        // Rental product
        driver.findElement(By.linkText("Elegant Gemstone Necklace")).click();
        driver.findElement(By.id("rental_start_date")).clear();
        driver.findElement(By.id("rental_start_date")).sendKeys("10/10/2025");
        driver.findElement(By.id("rental_end_date")).clear();
        driver.findElement(By.id("rental_end_date")).sendKeys("11/10/2025");
        driver.findElement(By.id("add-to-cart-button-40")).click();
        Thread.sleep(1000);
        driver.navigate().back();

        // Other products
        String[] products = {"Flower Girl Bracelet", "Vintage Style Engagement Ring"};
        for (String p : products) {
            driver.findElement(By.xpath("//h2[@class='product-title']/a[contains(text(),'" + p + "')]/../../..//button[contains(text(),'Add to cart')]")).click();
            Thread.sleep(1000);
        }
    }
}