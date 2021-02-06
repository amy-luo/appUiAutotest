package com.mytest.function.testcase;

import com.mytest.function.Utils.GetAppiumDriver;
import com.mytest.function.baseStater.CCPrepare;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class Test002 {
    AppiumDriver driver= GetAppiumDriver.driver;
    @CCPrepare(id="Test002")
    public void test2() {
        driver.findElement(By.name("9")).click();
        driver.findElement(By.name("5")).click();
        driver.findElement(By.name("+")).click();
        driver.findElement(By.name("7")).click();
        driver.findElement(By.name("=")).click();
        org.testng.Assert.assertEquals(true==driver.findElement(By.name("101")).isDisplayed(),"找不到元素***");
    }
}
