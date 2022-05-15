package com.parabank.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ShoppingSiteTest {

    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://www.demoblaze.com";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(baseUrl);
    }

    @Test(priority = 1)
    public void testValidLogin() {
        driver.findElement(By.id("login2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
        driver.findElement(By.id("loginusername")).sendKeys("Babusingh");
        driver.findElement(By.id("loginpassword")).sendKeys("Kush@2026");
        driver.findElement(By.cssSelector("button[onclick='logIn()']")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("nameofuser"), "Welcome Babusingh"));

        WebElement welcomeText = driver.findElement(By.id("nameofuser"));
        Assert.assertTrue(welcomeText.getText().contains("Babusingh"),
                "Welcome message should show logged in username");
    }

    @Test(priority = 2)
    public void testInvalidLoginAlert() {
        driver.findElement(By.id("login2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));

        driver.findElement(By.id("loginusername")).sendKeys("WrongUser999");
        driver.findElement(By.id("loginpassword")).sendKeys("WrongPass999");
        driver.findElement(By.cssSelector("button[onclick='logIn()']")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();

        Assert.assertTrue(alertText.length() > 0, "Alert should be shown for invalid login");
    }

    @Test(priority = 3)
    public void testProductNavigationByXPath() {
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='Samsung galaxy s6']")));
        product.click();

        WebElement productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[@class='name']")));
        Assert.assertEquals(productTitle.getText(), "Samsung galaxy s6",
                "Product title should match clicked product");
    }

    @Test(priority = 4)
    public void testCartPageNavigation() {
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='Samsung galaxy s6']")));
        product.click();

        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='Add to cart']")));
        addToCartBtn.click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

        driver.findElement(By.id("cartur")).click();

        WebElement cartTable = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("tbodyid")));
        Assert.assertTrue(cartTable.isDisplayed(), "Cart page should display product table");
    }

    @Test(priority = 5)
    public void testAddToCart() throws InterruptedException {
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='Samsung galaxy s6']")));
        product.click();

        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='Add to cart']")));
        addToCartBtn.click();

        Thread.sleep(1000);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();

        Assert.assertTrue(alertText.contains("Product added"),
                "Alert should confirm product added to cart");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}