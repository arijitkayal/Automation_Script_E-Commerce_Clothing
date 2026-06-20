package Selenium_TestNG_Framework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Selenium_TestNG_Framework.PageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties props=new Properties();
		FileInputStream fip=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Selenium_TestNG_Framework\\Resources\\Globaldata.properties");
		props.load(fip);
		
		String browser=System.getProperty("browser")!=null?System.getProperty("browser"):props.getProperty("browser");
		
		if(browser.toLowerCase().contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			
			if(browser.toLowerCase().contains("headless")) {
				options.addArguments("headless");
				
			}

			options.addArguments("--disable-notifications");
			options.addArguments("--disable-save-password-bubble");

			// Disable password manager
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);

			// Disable password leak detection
			prefs.put("profile.password_manager_leak_detection", false);

			options.setExperimentalOption("prefs", prefs);

			driver = new ChromeDriver(options);
		}else if(browser.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		
		if(browser.toLowerCase().contains("headless")) {
			driver.manage().window().setSize(new Dimension(1440, 900));
		}else {
			driver.manage().window().maximize();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
		return driver;
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		WebDriver driver=initializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun = true)
	public void closeApplication() {
		driver.quit();
	}
	
	public List<HashMap<String, String>> getJsonDataToHashMap(String path) throws IOException {
		String jsonContent=FileUtils.readFileToString(new File(path),StandardCharsets.UTF_8);
	
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
	
		return data;
	}
	
	public String takeScreenShot(String testCaseName,WebDriver driver) throws IOException {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
		FileUtils.copyFile(src, dest);
		
		return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
	}
}
