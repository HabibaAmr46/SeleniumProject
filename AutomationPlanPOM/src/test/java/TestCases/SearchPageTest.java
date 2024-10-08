package TestCases;
import java.io.IOException;

import java.time.Duration;
import java.util.Properties;

import org.testng.Assert;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import Base.TestBase;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.SearchPage;
import config.PropertiesFile;
@Listeners({ Base.Listeners.class })
public class SearchPageTest extends TestBase{
	
	HomePage homePage;
	SearchPage searchPage;
	
	public SearchPageTest() throws IOException {
		super();
	}
	
	
	@Test
	public void searchInSubCategories()
	{
		loginPage=new LoginPage(driver).
				OpenLoginPage().
				login("Bi@gmail.com","test123");
		
		homePage=new HomePage(driver)
		        .clickSearchBarButton();
		
		searchPage=new SearchPage(driver)
						.search();
		Assert.assertTrue(searchPage.NoElementInShoppingCart().getText().contains(prop.getProperty("NoElementInShoppingCart")));
		searchPage.search(prop.getProperty("searchItem"));
		Assert.assertTrue(searchPage.getSearchResults().getText().contains(prop.getProperty("searchItemResult")));
		homePage.Logout();
		
	}
	

}
