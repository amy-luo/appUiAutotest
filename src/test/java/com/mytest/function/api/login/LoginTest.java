package com.mytest.function.api.login;

//import com.mytest.function.Utils.LoadConfig;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginTest extends TestCase {
    protected static final Logger logger= LoggerFactory.getLogger(LoginTest.class);
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        // TODO Auto-generated method stub
        //1.添加配置，创建DesiredCapabilities对象
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //指定测试设备的名称
        desiredCapabilities.setCapability("deviceName", "127.0.0.1:62001");
        //添加操作系统配置
        desiredCapabilities.setCapability("platformName", "Android");
        //添加操作系统版本设置
        desiredCapabilities.setCapability("platformVersion", "4.4.2");
        //指定想要测试应用的包名
        desiredCapabilities.setCapability("appPackage", "com.baidu.BaiduMap");
        //指定想要测试应用的入口activity
        desiredCapabilities.setCapability("appActivity", "com.baidu.baidumaps.WelcomeScreen");
        //2.创建驱动...URL是appium的固定地址；指定appium通讯的地址，将相对应的配置传入到驱动里边
        AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities);
        //加线程等待
        //Thread.sleep(5000);
        //打开地图后点击同意继续
        androidDriver.findElementById("com.baidu.BaiduMap:id/ok_btn").click();
        //点击进入地图
        androidDriver.findElementById("com.baidu.BaiduMap:id/btn_enter_map").click();
        //退出
        androidDriver.quit();
    }
}

