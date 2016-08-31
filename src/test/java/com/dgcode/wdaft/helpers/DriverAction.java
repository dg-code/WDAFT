package com.dgcode.wdaft.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.dgcode.wdaft.utils.Verify.Argument.*;

/**
 * Created by dgjorgievski on 28.08.2016.
 */
public class DriverAction {
    // Maximal time for the driver to wait
    private static final int MAX_WAIT = 1;

    public static boolean verifyPageLoadedByTitle(String title, WebDriver webDriver) {
        isNotNull(webDriver, "Driver");
        isNotNullOrEmpty(title, "Title of the page");

        try {
            (new WebDriverWait(webDriver, MAX_WAIT)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.getTitle().toLowerCase().startsWith(title);
                }
            });
        } catch (WebDriverException ex) {
            return false;
        }
        return true;
    }

    public static boolean verifyElementIsPresent(By byAttribute, WebDriver webDriver) {
        isNotNull(webDriver, "Driver");
        isNotNull(byAttribute, "Attribute of the element");

        try {
            (new WebDriverWait(webDriver, 20)).until(ExpectedConditions.presenceOfElementLocated(byAttribute));
        } catch (WebDriverException ex) {
            return false;
        }
        return true;
    }

    public static boolean verifyElementIsClickable(WebElement webElement, WebDriver webDriver) {
        isNotNull(webDriver, "Driver");
        isNotNull(webElement, "Attribute of the element");

        try {
            new WebDriverWait(webDriver, MAX_WAIT).until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (WebDriverException ex) {
            return false;
        }
        return true;
    }
}