package Selenium_TestNG_Framework.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsConfig {
	
	public static ExtentReports configExtentReport() {
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Ecommerce_Web_Automation_Report");
		reporter.config().setDocumentTitle("Ecommerce_Web_Automation_Test_Result");
		
		ExtentReports extentReport=new ExtentReports();
		extentReport.attachReporter(reporter);
		extentReport.setSystemInfo("Tester", "Arijit Kayal");
		return extentReport;
	}
	

}
