# Selenium Automation Testing — Java + TestNG

A complete Selenium WebDriver automation framework built with Java, Maven,
and TestNG, covering login automation, alert handling, XPath-based
navigation, and end-to-end cart workflows across two live web applications.

---

## Applications Under Test

| Application | URL | Focus Area |
|-------------|-----|-----------|
| OrangeHRM | opensource-demo.orangehrmlive.com | Login automation, dynamic wait handling |
| Demoblaze | demoblaze.com | Alert handling, XPath locators, cart workflow |

---

## Project Structure

```
selenium-automation-testing/
├── pom.xml                          # Maven dependencies
├── testng.xml                       # Test suite configuration
└── src/
    └── test/
        └── java/
            └── com/parabank/automation/
                ├── OrangeHRMLoginTest.java
                └── ShoppingSiteTest.java
```

---

## Tech Stack

| Tool | Version | Purpose |
|------|---------|---------|
| Java | 21 | Programming language |
| Maven | 3.9.16 | Build and dependency management |
| Selenium WebDriver | 4.21.0 | Browser automation |
| TestNG | 7.10.2 | Test framework and execution |
| WebDriverManager | 5.9.2 | Automatic browser driver management |

---

## Test Coverage

### OrangeHRMLoginTest.java
| # | Test | Description |
|---|------|-------------|
| 1 | testValidLogin | Verifies successful login with valid credentials |
| 2 | testInvalidLogin | Verifies error message for invalid credentials |
| 3 | testEmptyUsername | Verifies required field validation |

### ShoppingSiteTest.java
| # | Test | Description |
|---|------|-------------|
| 1 | testValidLogin | Verifies login and welcome message display |
| 2 | testInvalidLoginAlert | Verifies JavaScript alert handling for invalid login |
| 3 | testProductNavigationByXPath | Verifies product navigation using XPath locators |
| 4 | testCartPageNavigation | Verifies end-to-end add-to-cart and cart page workflow |
| 5 | testAddToCart | Verifies add-to-cart confirmation alert |

**Total: 8 test cases — all passing**

---

## Key Selenium Concepts Demonstrated

- **Locator strategies:** ID, Name, CSS Selector, XPath
- **Explicit waits:** `WebDriverWait` with `ExpectedConditions` for
  dynamic JavaScript-rendered pages
- **JavaScript Alert handling:** `driver.switchTo().alert()`
- **TestNG annotations:** `@BeforeMethod`, `@AfterMethod`, `@Test` with priorities
- **Assertions:** `Assert.assertTrue`, `Assert.assertEquals`
- **WebDriverManager:** automatic ChromeDriver version matching
- **Page interaction:** form filling, clicking, navigation

---

## How to Run

### Prerequisites
- Java JDK 17+
- Maven 3.9+
- Google Chrome browser

### Setup
```bash
git clone https://github.com/babuhkush715-arch/selenium-automation-testing.git
cd selenium-automation-testing
```

### Run all tests
```bash
mvn test
```

### Run a specific test class
```bash
mvn test -Dtest=OrangeHRMLoginTest
mvn test -Dtest=ShoppingSiteTest
```

Test execution report is generated at:
`target/surefire-reports/`

---

## Sample Code Snippet

```java
@Test(priority = 1)
public void testValidLogin() {
    driver.findElement(By.name("username")).sendKeys("Admin");
    driver.findElement(By.name("password")).sendKeys("admin123");
    driver.findElement(By.cssSelector("button[type='submit']")).click();

    WebElement dashboardHeader = wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
    Assert.assertTrue(dashboardHeader.isDisplayed(),
        "Dashboard should be visible after valid login");
}
```

---

## Lessons Applied

This project demonstrates handling real-world automation challenges:
- Modern JavaScript single-page apps (Vue.js) require explicit waits
  rather than relying on default implicit timing
- JavaScript alerts must be explicitly switched to and handled before
  continuing test execution
- XPath locators provide reliable element targeting when IDs or names
  are unavailable

---

## Author

**Babusingh Kushwaha**
Project Associate, CSIR-NEERI Nagpur
Software Testing (Manual + Automation) | Data Analytics

[![GitHub](https://img.shields.io/badge/GitHub-babuhkush715--arch-blue)](https://github.com/babuhkush715-arch)

---

## Related Projects

- [Manual Testing — ParaBank](https://github.com/babuhkush715-arch/manual-testing-parabank)
- [Hostel ERP SQL Database](https://github.com/babuhkush715-arch/hostel-erp-sql)
- [India Air Quality Analytics](https://github.com/babuhkush715-arch/india-air-quality-analytics)
