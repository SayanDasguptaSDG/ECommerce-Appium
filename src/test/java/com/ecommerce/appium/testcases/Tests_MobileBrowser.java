package com.ecommerce.appium.testcases;

import com.ecommerce.appium.helper.MobileBrowserBaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests_MobileBrowser extends MobileBrowserBaseTest {
    @Test
    public void open_webpage() {
        driver.get("http://google.com");
    }

    @Test
    public void browser_validation() {
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.xpath("//span[@class='navbar-toggler-icon']")).click();
        driver.findElement(By.xpath("//a[@routerlink='/products']")).click();
        scrollDown();
        String productName = driver.findElement(By.cssSelector("a[href*='products/3']")).getText();
        Assert.assertEquals(productName, "Devops");

    }
}
