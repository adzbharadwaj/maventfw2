package com.expedia.adnatebeta.commonutils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Basepage {
	private WebDriver driver;
	
	public Basepage(WebDriver driver) {
		this.driver = driver;
	}

	public String getAlertText() {
		return this.driver.switchTo().alert().getText();
	}

	public String getAlertText(int waitTime) {
		WebDriverWait wait = new WebDriverWait(this.driver, waitTime);
		Alert a = wait.until(ExpectedConditions.alertIsPresent());
		return a.getText();

	}
	
	public String getPageTitle() {
		return this.driver.getTitle();
	}
	
	public void clickAccept() {
		this.driver.switchTo().alert().accept();
	}
	public void clickReject() {
		this.driver.switchTo().alert().dismiss();
	}
	
}
