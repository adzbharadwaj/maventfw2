package com.expedia.adnatebeta.testrunner;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SeleniumGrid_Parallel {
	private WebDriver driver;
	private String baseUrl;
	private String nodeURL;
	
	SearchPageFactory searchPage;
	
	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		//System.setProperty("webdriver.chrome.driver", "..//drivers/chromedriver");
		baseUrl = "https://www.expedia.com/";
		nodeURL = "http://192.168.2.14:5555/wd/hub";
//		DesiredCapabilities caps = DesiredCapabilities.firefox();
//		caps.setBrowserName("firefox");
//		caps.setPlatform(Platform.MAC);
		
//		ChromeOptions options = new ChromeOptions();
//		options.setCapability(CapabilityType.PLATFORM_NAME, Platform.SIERRA);
//		options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
//		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//		options.setAcceptInsecureCerts(true);
//		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
		
		FirefoxOptions options = new FirefoxOptions();
		driver = new RemoteWebDriver(new URL(nodeURL), options);
		
		searchPage = new SearchPageFactory(driver);

		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	@Test
	public void searchFlights() throws Exception {
		searchPage.clickFlightsTab();
		searchPage.setOriginCity("New York");
		searchPage.setDestinationCity("San Francisco");
		searchPage.setDepartureDate("10/28/2015");
		searchPage.setReturnDate("10/31/2015");
	}

	@AfterMethod
	@AfterClass
	public void tearDown() throws Exception {
		 driver.quit();
	}
}

