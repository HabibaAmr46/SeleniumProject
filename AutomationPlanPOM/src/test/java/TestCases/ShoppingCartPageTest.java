package TestCases;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import Base.TestBase;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ProductPage;
import Pages.ShoppingCartPage;
import config.PropertiesFile;
@Listeners({ Base.Listeners.class })
public class ShoppingCartPageTest extends TestBase {
	
	HomePage homePage;
	ShoppingCartPage shoppingCartPage;
	
	ProductPage productPage;
	
	public ShoppingCartPageTest() throws IOException {
		super();
	}
	
	
	@Test
	public void addToShoppingCart() throws InterruptedException, IOException
	{
		loginPage=new LoginPage(driver)
			.OpenLoginPage()
			.login("Bi@gmail.com","test123");
		
		homePage=new HomePage(driver)
				.ClickTablets()
				.ClickAddToCartTabletFunc();
		
		
		
		Assert.assertTrue(homePage.checkAddToCartMessageIsDisplayed());
		
		Thread.sleep(2000);
		
		shoppingCartPage=new ShoppingCartPage(driver)
						.OpenShoppingCart();
		
		Assert.assertTrue(shoppingCartPage.validateIfProductExists(prop.getProperty("TabletName"),prop.getProperty("TabletPrice")));
		homePage.ShowAllLaptops()
		       .ViewLaptopDetails();
		
		productPage= new ProductPage(driver)
				.AddProductToCart();
		
		Assert.assertTrue(homePage.checkAddToCartMessageIsDisplayed());
		
		shoppingCartPage.OpenShoppingCart();
		
		Assert.assertTrue(shoppingCartPage.validateIfProductExists(prop.getProperty("LaptopName"), prop.getProperty("LaptopPrice"),prop.getProperty("LaptopDeliveryDate")));
		
		double expectedTotal=shoppingCartPage.checkTotal();
		Assert.assertEquals(expectedTotal, shoppingCartPage.getActualTotal());
		homePage.Logout();
	}

}
