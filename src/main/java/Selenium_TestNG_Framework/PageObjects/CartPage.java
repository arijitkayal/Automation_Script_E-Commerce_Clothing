package Selenium_TestNG_Framework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import Selenium_TestNG_Framework.Abstract_Components.Abstract_Components;

public class CartPage extends Abstract_Components{

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	By checkOutButton=By.xpath("//button[text()='Checkout']");
	
	public List<WebElement> getAllCartProducts() {
		return cartProducts;
	}
	
	public boolean findInCartByName(String productName) {
		
		Boolean match= getAllCartProducts().stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage checkOut() {
		waitUntilElementClickable(checkOutButton);
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(checkOutButton)).click().build().perform();
		return new CheckOutPage(driver);
	}
}
