package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractReusableCode;

public class LandingPage extends AbstractReusableCode {

	// Constructor
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locators

	@FindBy(id = "userEmail")
	WebElement emailId;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement button;

	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

//ActionMetods
	public String getErrorMesssage() {
		waitForElementToAppearWebElement(errorMessage);
		return errorMessage.getText();
	}

	public ProductCatalogue LoginApplication(String email, String pass) {
		emailId.sendKeys(email);
		password.sendKeys(pass);
		button.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);

		return productCatalogue;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
