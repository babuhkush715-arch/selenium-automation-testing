package com.parabank.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrangeHRMLoginTest {

    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(baseUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
    }

    @Test(priority = 1)
    public void testValidLogin() {
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement dashboardHeader = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard should be visible after valid login");
    }

    @Test(priority = 2)
    public void testInvalidLogin() {
        driver.findElement(By.name("username")).sendKeys("WrongUser");
        driver.findElement(By.name("password")).sendKeys("WrongPass");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement errorMsg = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Invalid credentials']")));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message should be shown for invalid login");
    }

    @Test(priority = 3)
    public void testEmptyUsername() {
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement requiredMsg = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Required']")));
        Assert.assertTrue(requiredMsg.isDisplayed(), "Required field message should appear for empty username");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}