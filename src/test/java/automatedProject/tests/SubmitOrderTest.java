package automatedProject.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automatedProject.pageObjects.CartPage;
import automatedProject.pageObjects.CheckOutPage;
import automatedProject.pageObjects.LandingPage;
import automatedProject.pageObjects.OrderPage;
import automatedProject.pageObjects.ConfirmationPage;
import automatedProject.pageObjects.ProductCatalogue;
import automatedProject.testComponents.BaseTest;
import junit.framework.Assert;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		String countryName = "India";

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
	
		productCatalogue.addProductToCar(input.get("product"));

		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("product"));

		Assert.assertTrue(match);

		CheckOutPage checkOutPage = cartPage.goToCheckOut();

		checkOutPage.selectCountry(countryName);

		ConfirmationPage confirmationPage = checkOutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmMessageText();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	// To verify QWERTY is diaplaying in orders page
	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() throws InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication("dagoscr@gmail.com", "Derbys05");
		
		OrderPage orderPage = productCatalogue.goToOrdersPage();

		Boolean match = orderPage.verifyOrderDisplay(productName);

		Assert.assertTrue(match);
	}
	
	//Proveedor de datos para realizar pruebas parametrizadas
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\automatedProject\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0)}, { data.get(1) } };
	}
	
	
}
