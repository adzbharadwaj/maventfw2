package com.expedia.adnatebeta.testrunner;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.expedia.adnatebeta.commonutils.BrowserFactory;
import com.expedia.adnatebeta.commonutils.Commonutil;
import com.expedia.adnatebeta.commonutils.ExcelRW;
import com.expedia.adnatebeta.commonutils.FailRerun;
import com.expedia.adnatebeta.pageobjects.ConfirmationPage;
import com.expedia.adnatebeta.pageobjects.Homepage;
import com.expedia.adnatebeta.pageobjects.RegistrationPage;
import com.expedia.adnatebeta.pageobjects.ReservationPage;

public class NTRunnerTest {
	String bn = null;
	String rm = null;
	String url =null;
	WebDriver driver =null;
	Homepage hp =null;
	ReservationPage rsp =null;
	RegistrationPage rp= null;
	ConfirmationPage cp =null;
	
	@Parameters({"bn","rm"})
	@BeforeClass
	public void getTestInput(@Optional("chrome") String b, @Optional("local") String r) throws IOException {
		this.bn =b;
		this.rm =r;
		this.url= Commonutil.getPropertyValue("config", "url");
		System.out.println(this.bn +" "+this.rm + " "+ this.url);
		
	}
	
	@BeforeMethod
	public void preCondition() throws IOException {
		this.driver =BrowserFactory.getBrowser(this.bn, this.rm);
		System.out.println(driver);
		BrowserFactory.openUrl(url);
		this.hp= PageFactory.initElements(driver, Homepage.class);

	}
	
	@Test(dataProvider="tdp",dataProviderClass=ExcelRW.class ,retryAnalyzer = FailRerun.class)
	public void VerifyPageTitle(String title, String rm) throws Throwable {
		System.out.println(title);
		String result ="fail";
		String error = " ";
		try {
			String actual = this.hp.getPageTitle();
			Assert.assertEquals(actual, title, "Not Matching");
			
		} catch (Throwable e) {
			error = e.getMessage();
			System.out.println("errrrr");
			Commonutil.takeScreenshot(driver);
			throw e;
		}
	}
	
	@Test(dataProvider="tdp",dataProviderClass=ExcelRW.class)
	public void VerifyFlightDetailsHeading(String... fltdtls) throws Throwable {
		String result ="fail";
		String error = " ";
		
		try {
			this.hp.sendUsername(fltdtls[0]);
			this.hp.sendPassword(fltdtls[1]);
			this.rsp=this.hp.clickSubmit();
			String actual= rsp.getFlightDetailsHeader();
			Assert.assertEquals(actual, fltdtls[2], "Done");
			
		} catch (Throwable e) {
			error = e.getMessage();
			Commonutil.takeScreenshot(driver);
			throw e;
			
		}
	
		
	}
	
	@Test(dataProvider="tdp",dataProviderClass=ExcelRW.class)
	public void VerifyRegistrationProcess(String... rd) throws Throwable {
		String result ="fail";
		String error = " ";
		
		try {
			this.rp=this.hp.clickRegister();
			rp.sendFirstName(rd[0]);
			rp.sendLastName(rd[1]);
			rp.sendPhoneNumber(rd[2]);
			rp.sendEmail(rd[3]);
			rp.sendAddress1(rd[4]);
			rp.sendCity(rd[5]);
			rp.sendState(rd[6]);
			rp.sendPostalCode(rd[7]);
			rp.selectCountry(rd[8]);
			rp.sendUserName(rd[9]);
			rp.sendPassword(rd[10]);
			rp.sendConfirmPassword(rd[11]);
			cp = rp.clickSubmit();
			String actual = cp.getConfirmText();
			
			Assert.assertEquals(actual, rd[12], "Enjoy");
			
		} catch (Throwable e) {
			error = e.getMessage();
			Commonutil.takeScreenshot(driver);
			throw e;
			
		}
	
		
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
	
}
