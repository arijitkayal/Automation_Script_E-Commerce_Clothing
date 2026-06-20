package Selenium_TestNG_Framework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Selenium_TestNG_Framework.Abstract_Components.Abstract_Components;

public class LandingPage extends Abstract_Components{

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);// here pageFactory initialised each elements declared with @FindBy tag

	}

	@FindBy(id = "userEmail")
	WebElement emailField;// this two line of code is same as WebElement
							// emailField=driver.findElement(By.id("userEmail"));this get converted behind
							// the scene at runtime

	@FindBy(id = "userPassword")
	WebElement passwordField;

	@FindBy(id = "login")
	WebElement loginButton;
	
	By toastError=By.xpath("//div[contains(@class,'flyInOut ')]");
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}

	public Product_Lists login(String email, String password) {
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		loginButton.click();
		return new Product_Lists(driver);
	}
	
	public String getLoginErrorMsg() {
		waitUntilElementAppear(toastError);
		String errMsg=driver.findElement(toastError).getText();
		return errMsg;
	}


}
