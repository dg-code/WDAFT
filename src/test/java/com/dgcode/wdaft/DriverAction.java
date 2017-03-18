package com.dgcode.wdaft;

import static com.dgcode.wdaft.Utils.Argument.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created on August 29, 2016
 * @author dgcode
 */
public class DriverAction {
    // Maximal time for the driver to wait in seconds
    private static final int MAX_ELEMENT_WAIT = 10;
    private static final int MAX_PAGE_WAIT = 5;

    public static boolean verifyPageLoadedByTitle(String title, WebDriver webDriver) {
        isNotNull(webDriver, "Driver");
        isNotNullOrEmpty(title, "Title of the page");

        try {
            (new WebDriverWait(webDriver, MAX_PAGE_WAIT)).until(new ExpectedCondition<Boolean>() {
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
            (new WebDriverWait(webDriver, MAX_ELEMENT_WAIT)).until(ExpectedConditions.presenceOfElementLocated(byAttribute));
        } catch (WebDriverException ex) {
            return false;
        }
        return true;
    }

    public static boolean verifyElementIsClickable(WebElement webElement, WebDriver webDriver) {
        isNotNull(webDriver, "Driver");
        isNotNull(webElement, "Attribute of the element");

        try {
            new WebDriverWait(webDriver, MAX_ELEMENT_WAIT).until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (WebDriverException ex) {
            return false;
        }
        return true;
    }
}