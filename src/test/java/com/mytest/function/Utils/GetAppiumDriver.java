package com.mytest.function.Utils;
import java.net.URL;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.w3c.dom.html.HTMLInputElement;

public class GetAppiumDriver {
    public static AppiumDriver driver;
    public static DesiredCapabilities getCapabilities(){
        // TODO Auto-generated method stub
        //1.添加配置，创建DesiredCapabilities对象
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        //指定测试设备的名称
        capabilities.setCapability("deviceName", LoadConfig.getConfig("deviceName"));
        //添加操作系统配置
        capabilities.setCapability("platformName", LoadConfig.getConfig("platformName"));
        //添加操作系统版本设置
        capabilities.setCapability("platformVersion", LoadConfig.getConfig("platformVersion"));
        //指定想要测试应用的包名
        capabilities.setCapability("appPackage", LoadConfig.getConfig("appPackage"));
        //指定想要测试应用的入口activity
        capabilities.setCapability("appActivity", LoadConfig.getConfig("appActivity"));
        return capabilities;

    }
    public static AppiumDriver setUp() throws Exception {
        //2.创建驱动...URL是appium的固定地址；指定appium通讯的地址，将相对应的配置传入到驱动里边
        if(LoadConfig.getConfig("platformName").equals("Android")) {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), GetAppiumDriver.getCapabilities());
        }
        else
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),GetAppiumDriver.getCapabilities());
        return driver;
    }

    public static void tearDown() throws Exception {
        driver.quit();
    }

    public static void main(String[] args) throws Exception {
        GetAppiumDriver.driver= GetAppiumDriver.setUp();
        GetAppiumDriver.tearDown();
    }
}
