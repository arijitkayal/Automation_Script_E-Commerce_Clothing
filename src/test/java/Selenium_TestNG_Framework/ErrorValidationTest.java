package Selenium_TestNG_Framework;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import Selenium_TestNG_Framework.TestComponents.BaseTest;

public class ErrorValidationTest extends BaseTest{
	@Test(groups = {"errorValidation"},retryAnalyzer = Selenium_TestNG_Framework.TestComponents.Retry.class)
	public void invalidLogin() {
		landingPage.login("arijit@kayal.com", "arijit123");
		String errMsg=landingPage.getLoginErrorMsg();
		Assert.assertEquals("Incorrect email or password.", errMsg);
		
	}
}
