package tests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import bindings.AmazonBind;
import basePages.BuyProductPage;
import basePages.HomePage;
import basePages.LoginPage;
import basePages.ProductPage;

public class AmazonTests extends AmazonBind { 

	//Get input from the testng.xml file which has the device capabilities
	@BeforeClass
	@Parameters({ "Automation", "PlatformName", "PlatformVersion", "DeviceName", "udid", "Application", "Port", "systemPort" })
	public void setData(String automation, String platformName, String platformVer, String deviceNam, String udid,
			String application, String port, String systemPort) {
		
		testCaseName = "Android Test one";
		tcName = testCaseName;
		testDescription = "Started";
		category = "Functional";
		dataSheetName = "DataPool.xlsx";
		applicationType = application;
		authors = "Dipesh";
		capData.put("Automation", automation);
		capData.put("Port", port);
		capData.put("PlatformName", platformName);
		capData.put("PlatformVersion", platformVer);
		capData.put("DeviceName", deviceNam);
		capData.put("udid", udid);
		capData.put("TestCaseName", testCaseName);
		capData.put("systemPort", systemPort);
	}

	
	//TestNG test annotated method which has all the steps to execute. First step of the test case
	@Test(priority = 1, enabled = true)
	public void Amazon_Login_And_Place_Order_TC001() {
		try {
			testCaseName = "Amazon_Login_And_Place_Order_TC001";
			tcName = testCaseName;
			testDescription = "Validate the login and place order scenario in Amazon Android mobile app";
			startTestReport();
			LoginPage login = new LoginPage(driver, test, capData);
			login.signIn();
			
			HomePage home=new HomePage(driver, test, capData);
			home.selectLanguage();
			home.validateLogin();
			home.selectLanguage();
			home.clearCart();
			home.searchProduct();

			ProductPage pdp=new ProductPage(driver, test, capData);
			pdp.validateSearchResultPage();
			home.selectLanguage();
			pdp.validateProductScreen();
			pdp.addToCart();
			pdp.navigateToShoppingCart();

			BuyProductPage checkout=new BuyProductPage(driver, test, capData);
			checkout.validateShoppingCart();
			checkout.proceedtoBuy();
			checkout.selectShippingAddress();
			checkout.selectPayment();
			checkout.validateCheckoutPage();
		} 
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
}
