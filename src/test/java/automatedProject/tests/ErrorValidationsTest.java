package automatedProject.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import automatedProject.pageObjects.CartPage;
import automatedProject.pageObjects.ProductCatalogue;
import automatedProject.testComponents.BaseTest;
import automatedProject.testComponents.Retry;


public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		landingPage.loginApplication("dagscr@gmail.com", "Derbys05");

		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	@Test
	public void ProductErrorValidation() throws InterruptedException {
		
		String productName = "ZARA";
		String countryName = "India";
	
		ProductCatalogue productCatalogue = landingPage.loginApplication("dagoscr@gmail.com", "Derbys05"); 
		
		productCatalogue.addProductToCar(productName);

		CartPage cartPage = productCatalogue.goToCartPage();	 

		Boolean match = cartPage.verifyProductDisplay("ZARA");

		Assert.assertFalse(match);
	}

}
