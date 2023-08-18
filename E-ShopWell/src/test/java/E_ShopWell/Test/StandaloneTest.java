package E_ShopWell.Test;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import E_ShopWell.pageobject.CartPage;
import E_ShopWell.pageobject.CheckOutPage;
import E_ShopWell.pageobject.ConfirmationPage;
import E_ShopWell.pageobject.LandingPage;
import E_ShopWell.pageobject.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) {
		// Data of url, email and password
		String loginEmail = "milansahu@gmail.com";
		String password = "Milansahu22@";
		String expectedItem = "zara coat 3";
		String countryName = "India";
		String actualMessage = "THANKYOU FOR THE ORDER.";

		// Initialize chrome driver
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		// Wait for synchronization
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		// opening the url
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();

		// login to the application
		ProductCatalogue productCatalogue = landingPage.loginApplication(loginEmail, password);
//		List<WebElement> products = productCatalogue.getProductList();

		// add product to cart
		productCatalogue.addProductToCart(expectedItem);

		// navigate to the cart page and checking the cart product with the expected
		// product, and validating both are matching or not
		CartPage cartPage = productCatalogue.gotoCartPage();
		Boolean match = cartPage.verifyProductDisplay(expectedItem);
		Assert.assertTrue(match);

		// redirecting to the checkout page and select country and place the order
		CheckOutPage checkOutPage = cartPage.gotoCheckOutPage();
		checkOutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkOutPage.placeOrder();

		// redirecting to the confirmation page and check if the confirmation message is
		// showing as axpected
		String confirmMsg = confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(actualMessage));
		driver.close();
	}

}
