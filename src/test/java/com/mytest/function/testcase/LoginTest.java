package com.mytest.function.testcase;

//import com.mytest.function.Utils.LoadConfig;
import com.mytest.function.Utils.GetAppiumDriver;
import com.mytest.function.baseStater.CCPrepare;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginTest{
    protected static final Logger logger= LoggerFactory.getLogger(LoginTest.class);
    AppiumDriver driver=GetAppiumDriver.driver;
    @CCPrepare(id="LoginTest")
    public void loginTest() throws InterruptedException {
        //加线程等待
        Thread.sleep(5000);
        //打开地图后点击同意继续
        driver.findElementById("com.baidu.BaiduMap:id/ok_btn").click();
        //点击进入地图
        driver.findElementById("com.baidu.BaiduMap:id/btn_enter_map").click();
    }
}

