package E_ShopWell.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import E_ShopWell.TestComponents.BaseTest;
import E_ShopWell.TestComponents.Retry;
import E_ShopWell.pageobject.CartPage;
import E_ShopWell.pageobject.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException {
		// Input Data
		String loginEmail = "milansahu1@gmail.com";
		String password = "Milansahu22@";

		// login to the application
		landingPage.loginApplication(loginEmail, password);
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());

	}

	@Test
	public void productErrorValidation() throws IOException {
		// Input Data
		String loginEmail = "automation.test9619@gmail.com";
		String password = "Milansahu2@";
		String expectedItem = "zara coat 3";

		// login to the application
		ProductCatalogue productCatalogue = landingPage.loginApplication(loginEmail, password);
		// List<WebElement> products = productCatalogue.getProductList();

		// add product to cart
		productCatalogue.addProductToCart(expectedItem);

		// navigate to the cart page and checking the cart product with the expected
		// product, and validating both are matching or not
		CartPage cartPage = productCatalogue.gotoCartPage();
		Boolean match = cartPage.verifyProductDisplay("zara coat 33");
		Assert.assertFalse(match);

	}

}
