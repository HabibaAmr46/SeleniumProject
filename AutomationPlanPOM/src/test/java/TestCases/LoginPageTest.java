package TestCases;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Base.TestBase;
import Pages.LoginPage;
import config.PropertiesFile;

@Listeners({ Base.Listeners.class })
public class LoginPageTest extends TestBase {
LoginPage loginPage;
	
	String username;
	public LoginPageTest() throws IOException {
		super();
	
	}
	
	@Test
	public void validLogin() throws IOException
	{
		
		loginPage=new LoginPage(driver);
		loginPage.OpenLoginPage();
		loginPage.login(prop.getProperty("validLoginEmail"), prop.getProperty("validLoginPassword"));
		Assert.assertTrue(driver.getTitle().contains("My Account"));
	}
	
	
	@Test()
	public void invalidLogin()
	{	
		loginPage=new LoginPage(driver);
		loginPage.OpenLoginPage();
		loginPage.login(prop.getProperty("validLoginEmail"), prop.getProperty("invalidLoginPassword"));
		Assert.assertTrue(driver.findElement(loginPage.errorMessage).isDisplayed());
		Assert.assertTrue(driver.getTitle().contains("Account Login"));
	}
	
	

}
