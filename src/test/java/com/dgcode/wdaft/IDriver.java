package com.dgcode.wdaft;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Interface holding the Web Driver and the desired capabilities
 * <br>
 * <br>
 * Created on August 29, 2016
 * @author dgcode
 */
public interface IDriver {

	WebDriver getWebDriver(DesiredCapabilities desiredCapabilities);

	DesiredCapabilities getDesiredCapabilities(Proxy proxySetup);
}