package Selenium_TestNG_Framework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium_TestNG_Framework.Abstract_Components.Abstract_Components;

public class CheckOutPage extends Abstract_Components{
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryInput;
	
	@FindBy(css="section.ta-results.list-group.ng-star-inserted button")
	List<WebElement> countryOptions;
	
	@FindBy(css="a.btnn.action__submit.ng-star-inserted")
	WebElement submitButton;
	
	By listOfCountries=By.cssSelector("section.ta-results.list-group.ng-star-inserted");
	By submitBtnLocator=By.cssSelector("a.btnn.action__submit.ng-star-inserted");
	
	public void selectCountry(String country) {
		countryInput.sendKeys(country);
		waitUntilElementAppear(listOfCountries);

		WebElement chosenOption=countryOptions.stream().filter(o->o.getText().equalsIgnoreCase(country)).findFirst().orElse(null);
		Actions action=new Actions(driver);
		action.moveToElement(chosenOption).click().build().perform();
		//Alternative Methods(Advanced)
		//countryOptions.stream().filter(o->o.getText().equalsIgnoreCase(country)).findFirst().ifPresent(WebElement::click);
	}
	
	public ConfirmationPage clickSubmit() {
		Actions action=new Actions(driver);
		action.moveToElement(submitButton).click().build().perform();
		return new ConfirmationPage(driver);
		
	}
}
