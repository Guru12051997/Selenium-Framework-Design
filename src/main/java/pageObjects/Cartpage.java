package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponent.AbstractReusableCode;

public class Cartpage extends AbstractReusableCode {
	WebDriver driver;
	// Constructor

	public Cartpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locators
	@FindBy(css = ".totalRow button")
	WebElement checkoutele;

	@FindBy(xpath = "//div[@class=\"cartSection\"]/h3")
	List<WebElement> cartProducts;

	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		return match;
	}

	public CheckoutPage Gotocheckout() {
		checkoutele.click();
		return new CheckoutPage(driver);

	}
}
