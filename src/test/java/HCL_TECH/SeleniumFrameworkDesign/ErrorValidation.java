package HCL_TECH.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClassTestComponents.BaseTest;
import BaseClassTestComponents.Retry;
import pageObjects.Cartpage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.ProductCatalogue;

public class ErrorValidation extends BaseTest {
	String productName = "ZARA COAT 3";
//retryAnalyzer= Retry.class
	@Test(groups = { "ErrorHandling" })
	public void LoginError() throws InterruptedException, IOException {

		landingpage.LoginApplication("guruprasad.das@hcl.comm", "Munu@1234");

		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMesssage());
	}

	@Test
	public void productError() throws InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingpage.LoginApplication("guruprasad.das@hcl.com", "Munu@1234");
		List<WebElement> products = productCatalogue.getproductList();

		productCatalogue.addproductToCart(productName);

		// Thread.sleep(4000);
		Cartpage cartpage = productCatalogue.goToCartPage();

		boolean match = cartpage.VerifyProductDisplay("ZARA COAT 3");
		Assert.assertTrue(match);

	}
}
