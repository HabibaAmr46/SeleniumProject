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
		
		new LoginPage(driver)
			.OpenLoginPage()
			.login(prop.getProperty("validLoginEmail"), prop.getProperty("validLoginPassword"));
		Assert.assertTrue(driver.getTitle().contains("My Account"));
	}
	
	
	@Test()
	public void invalidLogin()
	{	
		boolean errorDisplayed=new LoginPage(driver)
			.OpenLoginPage()
			.login(prop.getProperty("validLoginEmail"), prop.getProperty("invalidLoginPassword"))
			.checkifErrorMessageIsDisplayed();
		Assert.assertTrue(errorDisplayed);
		Assert.assertTrue(driver.getTitle().contains("Account Login"));
	}
	
	

}
