package Selenium_TestNG_Framework.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	int count=0;
	int maxRetry=1;
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxRetry) {
			count++;
			return true;
			//only if this method returns true then only the coresponding method/testcases will retry execution
		}
		return false;
	}

}
