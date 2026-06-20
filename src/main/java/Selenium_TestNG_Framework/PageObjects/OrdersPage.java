package Selenium_TestNG_Framework.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr //td[2]")
	List<WebElement> productsNamesCol;
	
	public boolean findByNameInOrderHistory(String productName) {
		boolean match=productsNamesCol.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
}
