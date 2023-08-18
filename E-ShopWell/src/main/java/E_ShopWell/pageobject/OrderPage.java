package E_ShopWell.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import E_ShopWell.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productsName;

	@FindBy(css = ".totalRow button")
	WebElement checkOutEle;

	public Boolean verifyOrderDisplay(String expectedItem) {
		Boolean match = productsName.stream()
				.anyMatch(productName -> productName.getText().equalsIgnoreCase(expectedItem));
		return match;
	}


}
