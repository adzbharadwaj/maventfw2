package com.expedia.adnatebeta.commonutils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class FailRerun implements IRetryAnalyzer {
	
	int c =0;
	int maxc=2;

	public boolean retry(ITestResult rc) {
		if(c<maxc) {
			c++;
			return true;
		}
		return false;
	}

}
