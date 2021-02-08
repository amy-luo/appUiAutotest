package com.mytest.function.testcase;

//import com.mytest.function.Utils.LoadConfig;
import com.mytest.function.Utils.GetAppiumDriver;
import com.mytest.function.Utils.CCPrepare;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mytest.function.Utils.GetAppiumDriver.driver;

public class LoginTest{
    protected static final Logger logger= LoggerFactory.getLogger(LoginTest.class);

    @CCPrepare(id="LoginTest")
    public void loginTest() throws Exception {
        //加线程等待
        Thread.sleep(5000);
        //打开微信后登录
        driver.findElementByName("登录").click();
        driver.findElementByName("请填写QQ密码").sendKeys("123456");
        driver.findElement(By.id("com.tencent.mm:id/d5n")).click();
        org.testng.Assert.assertEquals(true==driver.findElement(By.name("101")).isDisplayed(),"找不到元素***");

//        driver.findElementsByXPath("");
    }
}

