package com.dgcode.wdaft.configuration;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

/**
 * Created by dgjorgievski on 27.08.2016.
 */
public enum DriverType implements DriverAdapter {
    CHROME {

        @Override
        public WebDriver getWebDriver(DesiredCapabilities desiredCapabilities) {
            return new ChromeDriver(desiredCapabilities);
        }

        @Override
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySetup) {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();

            return addProxySettings(desiredCapabilities, proxySetup);
        }
    };

    private static DesiredCapabilities addProxySettings(DesiredCapabilities desiredCapabilities, Proxy proxySetup) {
        if (null != proxySetup) {
            desiredCapabilities.setCapability(PROXY, proxySetup);
        }

        return desiredCapabilities;
    }
}