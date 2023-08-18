package E_ShopWell.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import E_ShopWell.TestComponents.BaseTest;
import E_ShopWell.pageobject.CartPage;
import E_ShopWell.pageobject.CheckOutPage;
import E_ShopWell.pageobject.ConfirmationPage;
import E_ShopWell.pageobject.OrderPage;
import E_ShopWell.pageobject.ProductCatalogue;

public class OrderProductTest extends BaseTest {
	// Input Data
//	String loginEmail = "milansahu@gmail.com";
//	String password = "Milansahu22@";
//	String expectedItem = "zara coat 3";
//	String countryName = "India";
//	String actualMessage = "THANKYOU FOR THE ORDER.";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {

		// login to the application
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("loginEmail"),
				input.get("password"));
		// List<WebElement> products = productCatalogue.getProductList();

		// add product to cart
		productCatalogue.addProductToCart(input.get("expectedItem"));

		// navigate to the cart page and checking the cart product with the expected
		// product, and validating both are matching or not
		CartPage cartPage = productCatalogue.gotoCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("expectedItem"));
		Assert.assertTrue(match);

		// redirecting to the checkout page and select country and place the order
		CheckOutPage checkOutPage = cartPage.gotoCheckOutPage();
		checkOutPage.selectCountry(input.get("countryName"));
		ConfirmationPage confirmationPage = checkOutPage.placeOrder();

		// redirecting to the confirmation page and check if the confirmation message is
		// showing as axpected
		String confirmMsg = confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(input.get("actualMessage")));
	}

	@Test(dataProvider = "getData", dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest(HashMap<String, String> input) {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("loginEmail"),
				input.get("password"));
		OrderPage orderPage = productCatalogue.gotoOrderPage();
		Boolean match = orderPage.verifyOrderDisplay(input.get("expectedItem"));
		Assert.assertTrue(match);
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		/*
		 * HashMap<String, String> map = new HashMap<String, String>();
		 * map.put("loginEmail", "milansahu@gmail.com"); map.put("password",
		 * "Milansahu22@"); map.put("expectedItem", "zara coat 3");
		 * map.put("countryName", "India"); map.put("actualMessage",
		 * "THANKYOU FOR THE ORDER.");
		 * 
		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("loginEmail", "automation.test9619@gmail.com"); map1.put("password",
		 * "Milansahu2@"); map1.put("expectedItem", "ADIDAS ORIGINAL");
		 * map1.put("countryName", "India"); map1.put("actualMessage",
		 * "THANKYOU FOR THE ORDER."); return new Object[][] { { map }, { map1 } };
		 */
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src/test//java//E_ShopWell//data//PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

//		return new Object[][] {
//				{ "milansahu@gmail.com", "Milansahu22@", "zara coat 3", "India", "THANKYOU FOR THE ORDER." },
//				{ "automation.test9619@gmail.com", "Milansahu2@", "ADIDAS ORIGINAL", "India",
//						"THANKYOU FOR THE ORDER." } };
	}

	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {
//		{ "milansahu@gmail.com", "Milansahu22@", "zara coat 3", "India", "THANKYOU FOR THE ORDER." },
//		{ "automation.test9619@gmail.com", "Milansahu2@", "ADIDAS ORIGINAL", "India",
//				"THANKYOU FOR THE ORDER." } };
//	}
}
