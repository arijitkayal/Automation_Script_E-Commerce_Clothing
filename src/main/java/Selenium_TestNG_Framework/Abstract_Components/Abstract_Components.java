package Selenium_TestNG_Framework.Abstract_Components;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Selenium_TestNG_Framework.PageObjects.OrdersPage;

public class Abstract_Components {
	

	WebDriver driver;
	public Abstract_Components(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartNavMenu;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderNavMenu;

	public void waitUntilElementAppear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitUntilElementDisappear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void waitUntilElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(locator));

	}
	public void clickOnCart() {
		cartNavMenu.click();
	}
	
	public OrdersPage clickOnOrders() {
		orderNavMenu.click();
		return new OrdersPage(driver);
	}
}
