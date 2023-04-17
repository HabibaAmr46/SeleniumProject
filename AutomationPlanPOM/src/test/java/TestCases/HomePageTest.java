package TestCases;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import Base.TestBase;
import Pages.HomePage;
import Pages.LoginPage;
import config.PropertiesFile;
import io.qameta.allure.Allure;


@Listeners({Base.Listeners.class})
public class HomePageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;

	
	public HomePageTest() throws IOException {
		super();
	}


	@Test
	public void ChangeCurrency()
	{	
		loginPage=new LoginPage(driver);
		loginPage.OpenLoginPage();
		loginPage.login("Bi@gmail.com","test123");

		

		homePage=new HomePage(driver);
		homePage.ShowAllDesktops();
		Allure.step("Ensure that all products contains $ as a currency");
		Assert.assertTrue(homePage.CheckIFProductsContainsCurrency("$"));
		homePage.ClickEuro();
		Allure.step("Ensure that all products contains € as a currency");
		Assert.assertTrue(homePage.CheckIFProductsContainsCurrency("€"));
		homePage.Logout();
	}
	@Test
	public void BreadCrumb()
	{
		loginPage=new LoginPage(driver);
		loginPage.OpenLoginPage();
		loginPage.login("Bi@gmail.com","test123");

		homePage=new HomePage(driver);
		homePage.ClickTablets();
		
		Allure.step("Ensure that the latest Bread Crumb element is Tablets");
		Assert.assertTrue(driver.findElement(homePage.LatestBreadCrumbElement).getText().contains("Tablets"));
		Allure.step("Ensure that the Active Side Bar Element contains Tablets");
		Assert.assertTrue(driver.findElement(homePage.ActiveSideBarElement).getText().contains("Tablets"));
			
		homePage.Logout();
	}

	@Test
	public void sortByName()
	{
		loginPage=new LoginPage(driver);
		loginPage.OpenLoginPage();
		loginPage.login("Bi@gmail.com","test123");

		homePage=new HomePage(driver);
		homePage.SelectAllPhones();
		List<String> products=homePage.GetALLProductsNames();
		Collections.sort(products,String.CASE_INSENSITIVE_ORDER);
		homePage.sortAsc();
		
		Allure.step("Ensure that all products are sorted ascendingly");
		Assert.assertEquals(products,homePage.GetALLProductsNames()); //check ascending
		Collections.sort(products,String.CASE_INSENSITIVE_ORDER.reversed());
		homePage.sortDesc();
		Allure.step("Ensure that all products are sorted Descndingly");
		Assert.assertEquals(products,homePage.GetALLProductsNames()); //check Descending
		homePage.Logout();
	}

	@Test
	public void searchByName()
	{
		loginPage=new LoginPage(driver);
		loginPage.OpenLoginPage();
		loginPage.login("Bi@gmail.com","test123");

		homePage=new HomePage(driver);
		String searchItem=prop.getProperty("searchItemName");
		homePage.search(searchItem);
		
		Allure.step("Ensure that the product exists");
		Assert.assertTrue(homePage.CheckIFProductExists(searchItem));

		homePage.Logout();
	}

}
