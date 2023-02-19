package TestCases;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import Base.TestBase;
import Pages.CheckOutPage;
import Pages.HomePage;
import Pages.LoginPage;

import Pages.ProductPage;
import Pages.ShoppingCartPage;
import config.PropertiesFile;
import io.qameta.allure.Allure;

@Listeners({ Base.Listeners.class })
public class NormalCheckOutTest extends TestBase{
	
	HomePage homePage;
	ShoppingCartPage shoppingCartPage;
	CheckOutPage checkoutPage;
	LoginPage loginPage;

	
	 public NormalCheckOutTest() throws IOException {
		 super();
	}
	
	@Test
	public void normalCheckOut() throws InterruptedException
	{
		loginPage=new LoginPage(driver);
		loginPage.OpenLoginPage();
		loginPage.login("Bi@gmail.com","test123");
		
		homePage=new HomePage(driver);
		homePage.ShowAllMP3();
		homePage.addIPODToCart();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.successAddToCartMessage));
		Assert.assertTrue(driver.findElement(homePage.successAddToCartMessage).isDisplayed());
		
		String IPODName=prop.getProperty("IPODName");
		String IPODPrice=prop.getProperty("IPODPrice");
		Assert.assertTrue(homePage.CheckIFProductExistsInSubMenu(IPODName));
		homePage.ClickViewCartSubMenu();

		shoppingCartPage=new ShoppingCartPage(driver);
		
		shoppingCartPage.validateIfProductExists(IPODName, IPODPrice);
		double shoppingCartTotal=shoppingCartPage.getActualTotal();
		Thread.sleep(2000);
		shoppingCartPage.checkOut();
		
		checkoutPage=new CheckOutPage(driver);
		wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.newPaymentAdressButton));
		
		checkoutPage.fillPaymentDetails("test", "test", "test", "Cairo", "Egypt", "Al Jizah");
		checkoutPage.fillShippingDetails("test", "test", "test", "Cairo", "12345","Egypt", "Al Jizah");
		checkoutPage.addComment("add comment");
		checkoutPage.proceedPaymentMethod();
		Allure.step("Assert Shopping Cart total equals CheckOut Subtotal");
		Assert.assertEquals(shoppingCartTotal, checkoutPage.getCheckOutSubTotal());
		Allure.step("Assert Actual CheckOut  total equals Expected CheckOut total");
		Assert.assertEquals(checkoutPage.getCheckOutTotal(), checkoutPage.getExpectedCheckOutTotal());
		checkoutPage.confirmOrder();
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.confirmOrderResult));
		Thread.sleep(2000);
		
		Assert.assertTrue(driver.findElement(homePage.confirmOrderResult).getText().contains(prop.getProperty("SucessOrderCheckOutMessage")));
		Allure.step("Assert No element in the cart");
		Assert.assertTrue(driver.findElement(homePage.itemsSubMenuButton).getText().contains(prop.getProperty("NoElementInShoppingCartSubMenu")));
		
		homePage.Logout();
		
	}

}
