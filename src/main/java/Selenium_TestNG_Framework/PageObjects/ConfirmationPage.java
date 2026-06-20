package Selenium_TestNG_Framework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Selenium_TestNG_Framework.Abstract_Components.Abstract_Components;

public class ConfirmationPage extends Abstract_Components{
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
	}
	@FindBy(css="h1.hero-primary")
	WebElement confirmationMsg;
	
	By confirmationMsgFindBy=By.cssSelector("h1.hero-primary");
	
	public String verifyConfirmationMsg() {
		waitUntilElementAppear(confirmationMsgFindBy);
		String confirmationText=confirmationMsg.getText();
		return confirmationText;
	}



}
