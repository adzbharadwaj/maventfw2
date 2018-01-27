package com.expedia.adnatebeta.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.expedia.adnatebeta.commonutils.Basepage;



public class Homepage extends Basepage{
	WebDriver driver;
	
	public Homepage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	
	@FindBy(name="userName")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(name="login")
	WebElement login;
	
	@FindBy(linkText="REGISTER")
	WebElement register;
	
	public String getPageTitle() {
		return super.getPageTitle();
	}
	
	public void sendUsername(String un) {
		username.sendKeys(un);
	}
	
	public void sendPassword(String pw) {
		password.sendKeys(pw);
	}
	
	public ReservationPage clickSubmit() {
		login.click();
		return PageFactory.initElements(driver, ReservationPage.class);
		}
	public RegistrationPage clickRegister() {
		register.click();
		return PageFactory.initElements(driver, RegistrationPage.class);
		}
}
