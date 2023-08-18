package E_ShopWell.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import E_ShopWell.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;
	Actions actions;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".action__submit")
	WebElement submit;

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement seleCountry;

	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {
		actions = new Actions(driver);
		actions.sendKeys(country, countryName).build().perform();
		waitElementToAppear(results);
		seleCountry.click();
	}

	public ConfirmationPage placeOrder() {
		actions = new Actions(driver);
		actions.scrollByAmount(0, 200).build().perform();
		submit.click();
		return new ConfirmationPage(driver);
	}
}
