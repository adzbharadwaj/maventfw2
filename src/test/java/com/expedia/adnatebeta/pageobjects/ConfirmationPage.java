package com.expedia.adnatebeta.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.expedia.adnatebeta.commonutils.Basepage;


public class ConfirmationPage extends Basepage {
WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@FindBy(xpath="/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[2]/font")
	WebElement text;
	
	public String getConfirmText() {
		return text.getText();
	}
}
