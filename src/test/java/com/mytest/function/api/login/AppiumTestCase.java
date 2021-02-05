package com.mytest.function.api.login;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.net.URL;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumTestCase extends TestCase {
    AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platforName", "Android");
        capabilities.setCapability("deviceName", "05157df53de0042b");
        capabilities.setCapability("platformVersion", "6.0.1");
        capabilities.setCapability("appPackage", "com.sec.android.app.popupcalculator");
        capabilities.setCapability("appActivity", ".Calculator");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @SuppressWarnings("deprecation")
    @Test
    public void test1() {
        driver.findElement(By.name("9")).click();
        driver.findElement(By.name("5")).click();
        driver.findElement(By.name("+")).click();
        driver.findElement(By.name("6")).click();
        driver.findElement(By.name("=")).click();

        assertTrue("ok", driver.findElement(By.name("101")).isDisplayed());

    }

    @SuppressWarnings("deprecation")
    @Test
    public void test2() {
        driver.findElement(By.name("9")).click();
        driver.findElement(By.name("5")).click();
        driver.findElement(By.name("+")).click();
        driver.findElement(By.name("7")).click();
        driver.findElement(By.name("=")).click();

        assertTrue("ok", driver.findElement(By.name("102")).isDisplayed());

    }

}
