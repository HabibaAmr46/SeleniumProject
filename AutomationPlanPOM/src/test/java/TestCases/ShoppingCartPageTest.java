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
	public void testWriteToExcel() throws IOException
	{
		loginPage=new LoginPage(driver);
		loginPage.OpenLoginPage();
		loginPage.login("Bi@gmail.com","test123");
		driver.findElement(By.xpath("//a[text()='Cameras']")).click();
		driver.findElement(By.xpath("//h4/a[contains(text(),'Canon')]")).click();
		ProductPage p=new ProductPage(driver);
		p.writeDataToExcel();
		driver.findElement(By.xpath("//ul[@class=\"nav navbar-nav\"]/li/a[text()='Cameras']")).click();
		driver.findElement(By.xpath("//h4/a[contains(text(),'Nikon')]")).click();
		p.writeDataToExcel();
		System.out.println(productPage.rowNum);
		
		
	}
	
	@Test
	public void addToShoppingCart() throws InterruptedException, IOException
	{
		loginPage=new LoginPage(driver);
		loginPage.OpenLoginPage();
		loginPage.login("Bi@gmail.com","test123");
		
		homePage=new HomePage(driver);
		homePage.ClickTablets();
		homePage.ClickAddToCartTabletFunc();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.successAddToCartMessage));
		Assert.assertTrue(driver.findElement(homePage.successAddToCartMessage).isDisplayed());
		shoppingCartPage=new ShoppingCartPage(driver);
		Thread.sleep(2000);
		shoppingCartPage.OpenShoppingCart();
		
		Assert.assertTrue(shoppingCartPage.validateIfProductExists(prop.getProperty("TabletName"),prop.getProperty("TabletPrice")));
		homePage.ShowAllLaptops();
		homePage.ViewLaptopDetails();
		productPage= new ProductPage(driver);
		
		
		Thread.sleep(2000);
		productPage.writeDataToExcel();
		
		productPage.AddProductToCart();
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.successAddToCartMessage));
		Assert.assertTrue(driver.findElement(homePage.successAddToCartMessage).isDisplayed());
		Thread.sleep(2000);
		shoppingCartPage.OpenShoppingCart();
		
		Assert.assertTrue(shoppingCartPage.validateIfProductExists(prop.getProperty("LaptopName"), prop.getProperty("LaptopPrice"),prop.getProperty("LaptopDeliveryDate")));
		
		double expectedTotal=shoppingCartPage.checkTotal();
		Assert.assertEquals(expectedTotal, shoppingCartPage.getActualTotal());
		homePage.Logout();
	}

}
