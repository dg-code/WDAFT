package com.dgcode.wdaft.config;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by dgjorgievski on 22.08.2016.
 */
public enum DriverType implements DriverSetup{
    FIREFOX {
        public WebDriver getWebDriververObject(DesiredCapabilities desiredCapabilities) {
            // TODO: 22.08.2016  
            return null;
        }

        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            // TODO: 22.08.2016
            return null;
        }
    }
}
