package com.dgcode.wdaft;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Drivers supported by WDAFT
 * <br>
 * <br>
 * Created on August 29, 2016
 * @author dgcode
 */
public enum DriverType implements IDriver {
	CHROME {

		@Override
		public WebDriver getWebDriver(DesiredCapabilities desiredCapabilities) {
			return new ChromeDriver(desiredCapabilities);
		}

		@Override
		public DesiredCapabilities getDesiredCapabilities(Proxy proxySetup) {
			DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
			return desiredCapabilitiesWithProxySetup(desiredCapabilities, proxySetup);
		}
	};

	private static DesiredCapabilities desiredCapabilitiesWithProxySetup(DesiredCapabilities desiredCapabilities, Proxy proxySetup) {
		if (null != proxySetup) {
			desiredCapabilities.setCapability(PROXY, proxySetup);
		}
		return desiredCapabilities;
	}
}