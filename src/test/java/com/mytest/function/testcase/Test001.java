package com.mytest.function.testcase;

import com.mytest.function.Utils.GetAppiumDriver;
import com.mytest.function.Utils.CCPrepare;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class Test001{
    @CCPrepare(id="Test001")
    public void test1() throws Exception {
        AppiumDriver driver=new GetAppiumDriver().setUp();
        driver.findElement(By.name("微信")).click();
        driver.findElement(By.name("登录")).click();
        org.testng.Assert.assertEquals(true==driver.findElement(By.name("101")).isDisplayed(),"找不到元素***");
        driver.quit();
    }
}
