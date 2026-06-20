package Selenium_TestNG_Framework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Selenium_TestNG_Framework.Abstract_Components.Abstract_Components;

public class Product_Lists extends Abstract_Components {

	WebDriver driver;
	public Product_Lists(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div.col-lg-4.col-md-6.col-sm-10.offset-md-0.offset-sm-1.mb-3.ng-star-inserted")
	List<WebElement> products;
	
	By allProducts=By.cssSelector("div.col-lg-4.col-md-6.col-sm-10.offset-md-0.offset-sm-1.mb-3.ng-star-inserted");
	By toastLogin=By.id("toast-container");
	By chosenProduct=By.cssSelector("button.btn.w-10.rounded");
	By toastAddToCart=By.xpath("//div[@aria-label='Product Added To Cart']");
	
	public List<WebElement> getAllProducts(){
		waitUntilElementAppear(allProducts);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement desiredProduct = getAllProducts().stream()
				.filter(product -> product.findElement(By.tagName("h5")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return desiredProduct;
	}
	
	public CartPage addToCart(String productName) {
		waitUntilElementDisappear(toastLogin);
		Actions action = new Actions(driver);
		WebElement desiredProduct=getProductByName(productName);
		action.moveToElement(desiredProduct.findElement(chosenProduct)).click().build()
				.perform();
		waitUntilElementAppear(toastAddToCart);
		waitUntilElementDisappear(toastAddToCart);
		return new CartPage(driver);
		
	}




}
