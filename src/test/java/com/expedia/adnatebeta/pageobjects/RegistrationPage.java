package com.expedia.adnatebeta.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.expedia.adnatebeta.commonutils.Basepage;



public class RegistrationPage extends Basepage {
WebDriver driver;
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@FindBy(name="firstName")
	WebElement fn;
	
	@FindBy(name="lastName")
	WebElement ln;
	
	@FindBy(name="phone")
	WebElement phn;
	
	@FindBy(name="userName")
	WebElement eml;
	
	@FindBy(name="address1")
	WebElement add1;
	
	@FindBy(name="address2")
	WebElement add2;
	
	@FindBy(name="city")
	WebElement cty;
	
	@FindBy(name="state")
	WebElement sp;
	
	@FindBy(name="postalCode")
	WebElement pc;
	
	@FindBy(name="country")
	WebElement cd;
	

	
	@FindBy(name="email")
	WebElement un;
	
	@FindBy(name="password")
	WebElement pw;
	
	@FindBy(name="confirmPassword")
	WebElement cpw;
	
	@FindBy(name="register")
	WebElement sb;
	
	public void sendFirstName(String f) {
		fn.sendKeys(f);
	}
	
	public void sendLastName(String l) {
		ln.sendKeys(l);
	}
	
	public void sendPhoneNumber(String p) {
		phn.sendKeys(p);
	}
	
	public void sendEmail(String e) {
		eml.sendKeys(e);
	}
	
	public void sendAddress1(String a1) {
		add1.sendKeys(a1);
	}
	
	public void sendAddress2(String a2) {
		add2.sendKeys(a2);
	}
	
	public void sendCity(String c) {
		cty.sendKeys(c);
	}
	
	public void sendState(String st) {
		sp.sendKeys(st);
	}
	
	public void sendPostalCode(String p) {
		pc.sendKeys(p);
	}
	
	public void selectCountry(String n) {
		String n1 = n.toUpperCase();
		Select nation = new Select(cd);
		nation.selectByVisibleText(n1);
	}
	
	public void sendUserName(String u) {
		un.sendKeys(u);
	}
	
	public void sendPassword(String p) {
		pw.sendKeys(p);
	}
	
	public void sendConfirmPassword(String c) {
		cpw.sendKeys(c);
	}
	
	public ConfirmationPage clickSubmit() {
		sb.click();
		return PageFactory.initElements(driver, ConfirmationPage.class);
	}
	
	
}
