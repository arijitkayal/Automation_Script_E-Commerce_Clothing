package Cucumber.StepDefination;

import java.io.IOException;

import org.testng.Assert;

import Selenium_TestNG_Framework.PageObjects.CartPage;
import Selenium_TestNG_Framework.PageObjects.CheckOutPage;
import Selenium_TestNG_Framework.PageObjects.ConfirmationPage;
import Selenium_TestNG_Framework.PageObjects.LandingPage;
import Selenium_TestNG_Framework.PageObjects.Product_Lists;
import Selenium_TestNG_Framework.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImplementation extends BaseTest{
	LandingPage landingPage;
	Product_Lists productListPage;
	CartPage cartPage;
	CheckOutPage checkOutPage;
	ConfirmationPage confirmationPage;
	
	@Given("i landed on ecommerce home page")
	public void i_landed_on_ecommerce_home_page() throws IOException {
		landingPage=launchApplication();
	}
	
	@Given("^Logged in with (.+) and (.+)$")
	public void logged_in_with_email_password(String email,String password) {
		productListPage=landingPage.login(email,password);
	}

	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) {
		cartPage= productListPage.addToCart(productName);
		productListPage.clickOnCart();
		
	}
	
	@And("^checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		Assert.assertTrue(cartPage.findInCartByName(productName));
	    checkOutPage=cartPage.checkOut();
	   
	    //select the country from dropdown and click on CONFIRM ORDER
	    checkOutPage.selectCountry("india");
	    confirmationPage=checkOutPage.clickSubmit();
	}
	
	@Then("{string} message will be displayed")
	public void confirmation_msg_will_be_displayed(String string) {
	    String confirmationText=confirmationPage.verifyConfirmationMsg();
	    
		Assert.assertTrue(confirmationText.equalsIgnoreCase(string));
		driver.quit();
	}
}
