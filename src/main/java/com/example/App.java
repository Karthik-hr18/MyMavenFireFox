package com.example;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class App {

    public static void main(String[] args) {

      

        FirefoxOptions options = new FirefoxOptions();

        // Force correct Firefox binary
        options.setBinary("/usr/bin/firefox");

        // Headless mode (important for Jenkins)
        options.addArguments("--headless");

        // Stability fixes for VM/Jenkins
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        // 🔥 VERY IMPORTANT (fix marionette issue)
        options.addPreference("remote.active-protocols", 1);

        // 🔥 ADD THIS (extra stability)
        options.addArguments("--remote-debugging-port=9222");

        WebDriver driver = null;

        try {
            driver = new FirefoxDriver(options);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            Actions actions = new Actions(driver);

            // 1️⃣ SauceDemo Login
            driver.get("https://www.saucedemo.com/");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")))
                    .sendKeys("standard_user");

            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            System.out.println("SauceDemo login successful ✅");

            // 2️⃣ Automation Exercise
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("https://automationexercise.com/products");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_product")))
                    .sendKeys("Men Tshirt");

            driver.findElement(By.id("submit_search")).click();

            WebElement product = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-product-id='2']"))
            );

            product.click();

            WebElement viewCart = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("#cartModal a[href='/view_cart']"))
            );

            viewCart.click();

            System.out.println("Product added to cart ✅");

            // 3️⃣ Practice Test Automation
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("https://practicetestautomation.com/practice-test-login/");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")))
                    .sendKeys("student");

            driver.findElement(By.id("password")).sendKeys("Password123");
            driver.findElement(By.id("submit")).click();

            System.out.println("Practice login successful ✅");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
