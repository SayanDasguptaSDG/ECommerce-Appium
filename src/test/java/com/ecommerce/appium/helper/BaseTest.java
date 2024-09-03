package com.ecommerce.appium.helper;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class BaseTest {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public DesiredCapabilities capabilities;

    @BeforeClass
    public void setup() throws URISyntaxException, MalformedURLException {
        service = new AppiumServiceBuilder().
                withAppiumJS(new File("C:\\Users\\sayan\\AppData\\Roaming" +
                        "\\npm\\node_modules\\appium\\build\\lib\\main.js")).
                withIPAddress("127.0.0.1").
                usingPort(4723).
                build();
        service.start();

        capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:deviceName", "TestPhone");
        capabilities.setCapability("appium:udid", "emulator-5554");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "com.androidsample.generalstore");
        capabilities.setCapability("appium:appActivity", "com.androidsample.generalstore.MainActivity");

        driver = new AndroidDriver(
                new URI("http://127.0.0.1:4723").toURL(), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void teardown() {
        driver.quit();
        service.stop();
    }

    public void scrollTillFound(String element) {
        driver.findElement(AppiumBy.androidUIAutomator
                ("new UiScrollable(new UiSelector())." +
                        "scrollIntoView(text(\"" + element + "\"));"));
    }
}
