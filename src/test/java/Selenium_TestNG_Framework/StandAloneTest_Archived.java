package Selenium_TestNG_Framework;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest_Archived {

	public static void main(String[] args) throws InterruptedException {
		String wishedProduct="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--disable-notifications");
		options.addArguments("--disable-save-password-bubble");

		// Disable password manager
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);

		// Disable password leak detection
		prefs.put("profile.password_manager_leak_detection", false);

		options.setExperimentalOption("prefs", prefs);

		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		// login
		driver.findElement(By.id("userEmail")).sendKeys("arijit@kayal.com");
		driver.findElement(By.id("userPassword")).sendKeys("Arijit@123");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.cssSelector("div.col-lg-4.col-md-6.col-sm-10.offset-md-0.offset-sm-1.mb-3.ng-star-inserted")));
		// list all product
		List<WebElement> products = driver.findElements(
				By.cssSelector("div.col-lg-4.col-md-6.col-sm-10.offset-md-0.offset-sm-1.mb-3.ng-star-inserted"));

//		for(int i=0;i<products.size();i++) {
//			String pName=products.get(i).findElement(By.tagName("h5")).getText();
//			if(pName.equalsIgnoreCase("ZARA COAT 3")) {
//				System.out.println("found");
//			}
//		}
		// alternative aproach to find the desired product using lamda expression and
		// steams()

		WebElement desiredProduct = products.stream()
				.filter(product -> product.findElement(By.tagName("h5")).getText().equalsIgnoreCase("ZARA COAT 3"))
				.findFirst().orElse(null);

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container"))));
		Actions action = new Actions(driver);
		action.moveToElement(desiredProduct.findElement(By.cssSelector("button.btn.w-10.rounded"))).click().build()
				.perform();

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[@aria-label='Product Added To Cart']"))));

		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//div[@aria-label='Product Added To Cart']"))));

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match= cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(wishedProduct));
		Assert.assertTrue(match);
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[text()='Checkout']"))));
		
		action.moveToElement(driver.findElement(By.xpath("//button[text()='Checkout']"))).click().build().perform();
			
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("section.ta-results.list-group.ng-star-inserted"))));
		
		List<WebElement> countryOptions=driver.findElements(By.cssSelector("section.ta-results.list-group.ng-star-inserted button"));
		
		//WebElement chosenOtion=countryOptions.stream().filter(o->o.getText().equalsIgnoreCase("india")).findFirst().orElse(null);
		//Alternative Methods(Advanced)
		countryOptions.stream().filter(o->o.getText().equalsIgnoreCase("india")).findFirst().ifPresent(WebElement::click);
		
		driver.findElement(By.cssSelector("a.btnn.action__submit.ng-star-inserted")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.hero-primary")));
		
		String confirmationText=driver.findElement(By.cssSelector("h1.hero-primary")).getText();
		Assert.assertTrue(confirmationText.equalsIgnoreCase("Thankyou for the order."));
		
		driver.quit();
		
	}
}
