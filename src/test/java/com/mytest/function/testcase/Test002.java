package com.mytest.function.testcase;

import com.mytest.function.Utils.GetAppiumDriver;
import com.mytest.function.Utils.CCPrepare;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import static com.mytest.function.Utils.GetAppiumDriver.driver;

public class Test002 {
    @CCPrepare(id="Test002")
    public void test2() throws Exception {
        driver.findElement(By.name("注册")).click();
        org.testng.Assert.assertEquals(true==driver.findElement(By.name("101")).isDisplayed(),"找不到元素***");
    }
}
