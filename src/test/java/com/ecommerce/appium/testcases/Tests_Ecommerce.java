package com.ecommerce.appium.testcases;

import com.ecommerce.appium.helper.BaseTest;
import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

public class Tests_Ecommerce extends BaseTest {
    @Test
    public void fill_form() throws InterruptedException {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Sayan Dasgupta");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioMale")).click();
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        scrollTillFound("Argentina");
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Argentina\"]")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(3000);
    }
}
