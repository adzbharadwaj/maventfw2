package com.expedia.adnatebeta.commonutils;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;



public class BrowserFactory {
	private static WebDriver driver =null;
	
	public static WebDriver getBrowser(String br_name, String run_mode) throws IOException {
		if (run_mode.equalsIgnoreCase("remote")) {
			if (br_name.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", "..//drivers/chromedriver");
				//DesiredCapabilities dc = DesiredCapabilities.chrome();
				ChromeOptions options = new ChromeOptions();
				options.setCapability(CapabilityType.PLATFORM_NAME, Platform.SIERRA);
				options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
				options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				options.setAcceptInsecureCerts(true);
				options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
				//dc.setBrowserName("Chrome");
				//dc.setPlatform(Platform.SIERRA);
				String nod1 = Commonutil.getPropertyValue("config", "node1");
				BrowserFactory.driver=new RemoteWebDriver(new URL(nod1), options);
				
				
			}
			if (br_name.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", "..//drivers/geckodriver");
				DesiredCapabilities dc = DesiredCapabilities.firefox();
				dc.setBrowserName("Firefox");
				dc.setPlatform(Platform.SIERRA);
				String nod2 = Commonutil.getPropertyValue("config", "node2");
				BrowserFactory.driver=new RemoteWebDriver(new URL(nod2), dc);
				
				
			}

		} else if (run_mode.equalsIgnoreCase("Local")) {
			if (br_name.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", "/Users/adityabharadwaj/MissionJob/Tools/drivers/chromedriver");
				BrowserFactory.driver = new ChromeDriver();
			}
			if (br_name.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", "..//drivers/geckodriver");
				BrowserFactory.driver = new FirefoxDriver();
			}
		}
		return BrowserFactory.driver;
		

		
	}
	
	private static WebDriver getBrowser() {
		return BrowserFactory.driver;
		
	}

	public static void openUrl(String url) {
		BrowserFactory.driver.get(url);
		BrowserFactory.driver.manage().window().fullscreen();
		BrowserFactory.driver.manage().timeouts().implicitlyWait(NTConstants.PAGE_LOAD_TIME_IN_SEC, TimeUnit.SECONDS);
	}
	
	
}
