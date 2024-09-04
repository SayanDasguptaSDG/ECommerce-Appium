package com.ecommerce.appium.testcases;

import com.ecommerce.appium.helper.MobileBrowserBaseTest;
import org.testng.annotations.Test;

public class Tests_MobileBrowser extends MobileBrowserBaseTest {
    @Test
    public void open_webpage() {
        driver.get("http://google.com");
    }
}
