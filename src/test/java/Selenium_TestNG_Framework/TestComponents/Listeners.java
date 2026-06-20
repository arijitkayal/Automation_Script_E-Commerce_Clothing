package Selenium_TestNG_Framework.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Selenium_TestNG_Framework.Resources.ExtentReportsConfig;

public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extentReports = ExtentReportsConfig.configExtentReport();

	ThreadLocal<ExtentTest> extentTestThreadSafe = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		test = extentReports.createTest(result.getMethod().getMethodName());
		extentTestThreadSafe.set(test);// this will create a mapping behind the scene like uniques thread id(who
										// calling createTest() method)-->"test" object
	}

	public void onTestSuccess(ITestResult result) {
	    extentTestThreadSafe.get().log(Status.PASS, result.getMethod().getMethodName() + ": PASSED");
	}

	public void onTestFailure(ITestResult result) {
		extentTestThreadSafe.get().fail(result.getThrowable());
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			String ssPath = takeScreenShot(result.getMethod().getMethodName(), driver);
			extentTestThreadSafe.get().addScreenCaptureFromPath(ssPath, result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onFinish(ITestContext context) {
		extentReports.flush();
	}

}
