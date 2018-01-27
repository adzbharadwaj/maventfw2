package com.expedia.adnatebeta.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.expedia.adnatebeta.commonutils.Basepage;



public class ReservationPage extends Basepage{
	
WebDriver driver;
	
	public ReservationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@FindBy(xpath="//font[contains(text(),'Details')]")
	WebElement flightDetails;
	
	public String getFlightDetailsHeader() {
		return flightDetails.getText();
	}

}
