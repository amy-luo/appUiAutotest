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
import org.testng.annotations.*;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;


public class SpecificCaseStarter {
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
    @BeforeTest
    public void setUp() throws Exception {
        GetAppiumDriver.driver=GetAppiumDriver.setUp();
    }

    @AfterTest
    public void tearDown() throws Exception {
        GetAppiumDriver.tearDown();
    }

    @DataProvider(name = "myDataProvider")
    public Iterator<Object[]> batchDataProvider(Method method) {
        return new MyDataProvider(this.loadSpecificTestCase());
    }

    @Test(dataProvider = "myDataProvider",description = "运行listCase.yaml中的测试用例")
    public void test(String testCaseName) {
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
            //加载listCase.yaml中的testcases，存入添加进List<String>
            public ArrayList<String> loadSpecificTestCase () {
                Yaml yaml = new Yaml();
                InputStream stream = ResourceUtils.getResourceAsStream(this, new Resource(fileName));
                Set<String> testCases = new HashSet<String>();
                try {
                    String o = yaml.loadAs(stream, String.class);
                    if (null == o) {
                        return null;
                    }
                    String[] data = o.split(" ");
                    for (int i = 0; i < data.length; i++) {
                        testCases.add(data[i]);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return new ArrayList<String>(testCases);
            }
    }