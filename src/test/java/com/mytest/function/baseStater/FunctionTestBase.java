/*
* Copyright (c) 2016 xiaoyouzi.com. All Rights Reserved.
*/
package com.mytest.function.baseStater;

import com.mytest.function.Utils.CCPrepare;
import com.mytest.function.Utils.GetAppiumDriver;
import com.mytest.function.Utils.MyDataProvider;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.lf5.util.Resource;
import org.apache.log4j.lf5.util.ResourceUtils;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * Created by LIMSHEN
 * Date: 20/1/5
 * Time: 21:48
 */
public class FunctionTestBase {
    org.slf4j.Logger logger = LoggerFactory.getLogger(SpecificCaseStarter.class);
    static String fileName = "listCase.yaml";
    final static String basePackage = "com.mytest.function.testcase";

    @BeforeClass
    public void init() {
        initLog();
    }

    public void initLog() {
        URL url = ResourceUtils.getResourceAsURL(this, new Resource("log4j.properties"));
        System.out.println(url.getPath());
        PropertyConfigurator.configure(url.getPath());
    }
//    @BeforeTest
//    public void setUp() throws Exception {
//    }

    @DataProvider(name = "myDataProvider_all")
    public Iterator<Object[]> batchDataProvider() {
        HashSet<String> testCaseNames=new HashSet<>();
        Reflections f = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage(basePackage)).setScanners(new MethodAnnotationsScanner()));
        Set<Method> methods = f.getMethodsAnnotatedWith(CCPrepare.class);
        for (Method method : methods) {
            CCPrepare ccPrepare = method.getAnnotation(CCPrepare.class);
            testCaseNames.add(ccPrepare.id());
        }
        return new MyDataProvider(new ArrayList<String>(testCaseNames));
    }

    @Test(dataProvider = "myDataProvider_all",description = "运行所有testcases")
    public void test(String testCaseName) throws Exception {
        try {
            Reflections f = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage(basePackage)).setScanners(new MethodAnnotationsScanner()));
            Set<Method> methods = f.getMethodsAnnotatedWith(CCPrepare.class);
            for (Method method : methods) {
                CCPrepare ccPrepare = method.getAnnotation(CCPrepare.class);
                if (ccPrepare.id().equalsIgnoreCase(testCaseName)) {
                    Object object = method.getDeclaringClass().newInstance();
                    try {
                        logger.info("开始执行! 测试用例名称：" + testCaseName);
//                        Class clazz = method.getDeclaringClass();
                        method.invoke(object);
                        logger.info("执行成功! 测试用例名称：" + testCaseName);
                    } catch (InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                        Assert.assertTrue(false);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
