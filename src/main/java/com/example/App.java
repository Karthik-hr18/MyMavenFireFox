package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class App {

    public static void main(String[] args) throws InterruptedException {

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/opt/firefox/firefox");

        WebDriver driver = new FirefoxDriver(options);

        driver.manage().window().maximize();

        // First Tab
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(3000);

        // Open Second Tab
        driver.switchTo().newWindow(org.openqa.selenium.WindowType.TAB);
        driver.get("https://automationexercise.com/");
        Thread.sleep(3000);

        // Switch back to first tab (simple way)
        String firstTab = driver.getWindowHandles().iterator().next();
        driver.switchTo().window(firstTab);

        Thread.sleep(3000);

        driver.quit();
    }
}
