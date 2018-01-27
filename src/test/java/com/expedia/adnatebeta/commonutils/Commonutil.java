package com.expedia.adnatebeta.commonutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Commonutil {
	public static String getPropertyValue(String filename, String key) throws IOException {
		FileInputStream fis = new FileInputStream("./TestData/" + filename + ".properties");
		Properties p = new Properties();
		p.load(fis);
		return p.getProperty(key);
	}

	public static String getCurrentDate() {
		DateFormat df = new SimpleDateFormat(NTConstants.DATE_FORMAT);
		Date d = new Date();
		System.out.println(df.format(d));
		return df.format(d);

	}

	public static void takeScreenshot(WebDriver driver) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//System.out.println("./ScreenShots/" + getCurrentDate() + ".png");
		File dest = new File("./ScreenShots/" + getCurrentDate() + ".png");
		FileUtils.copyFile(src, dest);
	}

}
