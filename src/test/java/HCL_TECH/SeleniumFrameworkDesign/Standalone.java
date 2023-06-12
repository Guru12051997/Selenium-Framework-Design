package HCL_TECH.SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseClassTestComponents.BaseTest;
import pageObjects.Cartpage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.OrderPage;
import pageObjects.ProductCatalogue;

public class Standalone extends BaseTest {

	String productName = "ZARA COAT 3";
	// String productName1 = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void SubmitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

		ProductCatalogue productCatalogue = landingpage.LoginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getproductList();

		productCatalogue.addproductToCart(input.get("product"));

		Thread.sleep(4000);
		Cartpage cartpage = productCatalogue.goToCartPage();

		boolean match = cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.Gotocheckout();

		checkoutPage.selectCountry("India");
		ConfirmationPage ConfirmationPage = checkoutPage.submitOrder();

		String confirmationPage = ConfirmationPage.getverifyConfirmationMessage();
		Assert.assertTrue(confirmationPage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "SubmitOrder" })
	public void orderHistory() {
		ProductCatalogue productCatalogue = landingpage.LoginApplication("guruprasad.das@hcl.com", "Munu@1234");
		OrderPage ordersPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}

	

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJasonDataToHashmap(
				System.getProperty("user.dir") + "\\src\\test\\java\\JsonData\\data.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
//return new Object[][] { { "guruprasad.das@hcl.com", "Munu@1234", "ZARA COAT
// 3" },
// { "gurupdas11@gmail.com", "Guru@1234", "ADIDAS ORIGINAL" } };

//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "guruprasad.das@hcl.com");
//		map.put("password", "Munu@1234");
//		map.put("product", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "gurupdas11@gmail.com");
//		map1.put("password", "Guru@1234");
//		map1.put("product", "ADIDAS ORIGINAL");
