package com.ecommerce.appium.testcases;

import com.ecommerce.appium.helper.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Tests_Ecommerce extends BaseTest {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @Test
    public void fill_form() throws InterruptedException {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Sayan Dasgupta");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioMale")).click();
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        scrollTillFound("Argentina");
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Argentina\"]")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
    }

    @Test
    public void verify_toast_message() throws InterruptedException {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioMale")).click();
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        scrollTillFound("Argentina");
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Argentina\"]")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String toastMessage = driver.findElement(AppiumBy.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage, "Please enter your name");
    }

    @Test(dependsOnMethods = {"fill_form"})
    public void add_to_cart() throws InterruptedException {
        List<WebElement> products;
        String productName = "Jordan 6 Rings";
        scrollTillFound(productName);
        products = (driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")));
        for(int i=0;i<products.size();i++) {
            productName = products.get(i).getText();
            if(products.get(i).getText().equals(productName)) {
                driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            }
        }
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        try {
            wait.until(ExpectedConditions.attributeContains(
                    driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is stale. Handle appropriately.");
        }
        Assert.assertEquals(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName")).getText(), productName);
    }
    
    @Test(dependsOnMethods = {"fill_form"})
    public void cart_page() throws InterruptedException {
        driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(0).click();
        //index will remain same because text is dynamic
        driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(1).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        try {
            wait.until(ExpectedConditions.attributeContains(
                    driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is stale. Handle appropriately.");
        }
        List<WebElement> products = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
        int numberOfItems = products.size();
        double purchaseAmount = 0.00;
        double totalPurchaseAmount = Double.parseDouble(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl"))
                .getText().substring(1));
        for(int i = 0; i<numberOfItems;i++) {
            purchaseAmount += Double.parseDouble(products.get(i).getText().substring(1));
        }
        Assert.assertEquals(totalPurchaseAmount, purchaseAmount);
    }
}
