package Selenium_TestNG_Framework;

import java.io.IOException;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Selenium_TestNG_Framework.PageObjects.CartPage;
import Selenium_TestNG_Framework.PageObjects.CheckOutPage;
import Selenium_TestNG_Framework.PageObjects.ConfirmationPage;
import Selenium_TestNG_Framework.PageObjects.LandingPage;
import Selenium_TestNG_Framework.PageObjects.OrdersPage;
import Selenium_TestNG_Framework.PageObjects.Product_Lists;
import Selenium_TestNG_Framework.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	String wishedProduct="ZARA COAT 3";
	
	@Test(dataProvider = "getData",groups= {"purchaseTestCases"})
	public void submitOrder(HashMap<String,String> input) throws IOException {
	
		// login
		Product_Lists productList=landingPage.login(input.get("email"),input.get("password"));

         
		// list all product - select product-click add to cart - select cart nav menu
		CartPage cartPage= productList.addToCart(input.get("wishedProduct"));
		productList.clickOnCart();

		//go to cart page and check wheather whishedProduct is available inside the cart & click on CHECKOUT
		Assert.assertTrue(cartPage.findInCartByName(input.get("wishedProduct")));
	    CheckOutPage checkOutPage=cartPage.checkOut();
	   
	    //select the country from dropdown and click on CONFIRM ORDER
	    //making a small changes for git push and ci/cd testing
	    checkOutPage.selectCountry("india");
	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    ConfirmationPage confirmationPage=checkOutPage.clickSubmit();
	    
	    //Verify the Confirmation Messege
	    String confirmationText=confirmationPage.verifyConfirmationMsg();
	    
		Assert.assertTrue(confirmationText.equalsIgnoreCase("Thankyou for the order."));
		
	}
	
	@Test(dependsOnMethods = "submitOrder")
	public void orderHistoryValidation() {
		Product_Lists productList=landingPage.login("arijit@kayal.com","Arijit@123");
		OrdersPage ordersPage=productList.clickOnOrders();
		boolean match=ordersPage.findByNameInOrderHistory(wishedProduct);
		Assert.assertTrue(match);
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		Object[][] data= {
//				{"arijit@kayal.com","Arijit@123","ZARA COAT 3"},
//				{"lina@kayal.com","Lina@123","ADIDAS ORIGINAL"}
//		};
//		return data;
//	}
	//------------Alternative approch by using HashMap---------//
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("email", "arijit@kayal.com");
//		map1.put("password", "Arijit@123");
//		map1.put("wishedProduct", "ZARA COAT 3");
//		
//		HashMap<String,String> map2=new HashMap<String,String>();
//		map2.put("email", "lina@kayal.com");
//		map2.put("password", "Lina@123");
//		map2.put("wishedProduct", "ADIDAS ORIGINAL");
		
		//---Alternatively we can use an utility function to retreive json data---//
		List<HashMap<String,String>> listOfData= getJsonDataToHashMap(System.getProperty("user.dir")+"\\src\\test\\java\\Selenium_TestNG_Framework\\data\\data.json");
		
		Object[][] data= {
				{listOfData.get(0)},
				{listOfData.get(1)}
		};
		return data;
	}
}
