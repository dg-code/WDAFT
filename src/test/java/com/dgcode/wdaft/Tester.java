package com.dgcode.wdaft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Starting point for executing the tests
 * <br>
 * <br>
 * Created on August 29, 2016
 * @author dgcode
 */
public class Tester {

    private static Map<ITestResult, List<Throwable>> verificationFailuresMap = new HashMap<ITestResult, List<Throwable>>();
    private static List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<DriverFactory>());
    private static ThreadLocal<DriverFactory> driverFactory;

    @BeforeSuite(alwaysRun = true)
    public static void instantiateDriverObject() {
        driverFactory = new ThreadLocal<DriverFactory>() {
            @Override
            protected DriverFactory initialValue() {
                DriverFactory driverFactory = new DriverFactory();
                webDriverThreadPool.add(driverFactory);
                return driverFactory;
            }
        };
    }

    public static WebDriver getDriver() throws Exception {
        return driverFactory.get().getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public static void clearCookies() throws Exception {
        getDriver().manage().deleteAllCookies();
    }

    @AfterSuite(alwaysRun = true)
    public static void closeDriverInstances() {
        for (DriverFactory driverFactory : webDriverThreadPool) {
            driverFactory.quitDriver();
        }
    }

    public static void assertTrue(boolean condition) {
        Assert.assertTrue(condition);
    }

    public static void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    public static void assertFalse(boolean condition) {
        Assert.assertFalse(condition);
    }

    public static void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    public static void assertEquals(boolean actual, boolean expected) {
        Assert.assertEquals(actual, expected);
    }

    public static void assertEquals(boolean actual, boolean expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    public static void assertEquals(int actual, int expected) {
        Assert.assertEquals(actual, expected);
    }

    public static void assertEquals(int actual, int expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    public static void verifyTrue(boolean condition) {
        try {
            assertTrue(condition);
        } catch (Throwable e) {
            addVerificationFailure(e);
        }
    }

    public static void verifyTrue(boolean condition, String message) {
        try {
            assertTrue(condition, message);
        } catch (Throwable e) {
            addVerificationFailure(e);
        }
    }

    public static void verifyFalse(boolean condition) {
        try {
            assertFalse(condition);
        } catch (Throwable e) {
            addVerificationFailure(e);
        }
    }

    public static void verifyFalse(boolean condition, String message) {
        try {
            assertFalse(condition, message);
        } catch (Throwable e) {
            addVerificationFailure(e);
        }
    }

    public static void verifyEquals(boolean actual, boolean expected) {
        try {
            assertEquals(actual, expected);
        } catch (Throwable e) {
            addVerificationFailure(e);
        }
    }

    public static void fail(String message) {
        Assert.fail(message);
    }

    public static List<Throwable> getVerificationFailures() {
        List<Throwable> verificationFailures = verificationFailuresMap.get(Reporter.getCurrentTestResult());
        return verificationFailures == null ? new ArrayList<Throwable>() : verificationFailures;
    }

    private static void addVerificationFailure(Throwable e) {
        List<Throwable> verificationFailures = getVerificationFailures();
        verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
        verificationFailures.add(e);
    }
}