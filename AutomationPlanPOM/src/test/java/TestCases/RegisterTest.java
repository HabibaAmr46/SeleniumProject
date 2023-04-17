package TestCases;
import java.io.IOException;

import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import Base.TestBase;
import Pages.RegisterPage;
import io.qameta.*;
import utils.ExcelReader;
import io.qameta.allure.Allure;
@Listeners({ Base.Listeners.class })
public class RegisterTest extends TestBase {
	public RegisterTest() throws IOException {
		super();
	}




	RegisterPage registerPage;
	By LogOutButton=By.linkText("Logout");
	
	
	@DataProvider(name = "excelData")
	public  Object[][] excelDataProvider() throws IOException {
		
		ExcelReader ex=new ExcelReader();
		return ex.getExcelData();
	}
	
	@DataProvider(name = "excelData2")
	public  Object[][] excelDataProvider2() throws IOException {
		
		ExcelReader ex=new ExcelReader();
		return ex.getExcelData2();
	}
	
	@Test(dataProvider = "excelData2")
	public void ValidRegister(String firstName,String LastName,String email,String telephone,String password,String confirmPassword)
	{
		registerPage=new RegisterPage(driver);
		registerPage.openRegistrationPage();

		registerPage.register(firstName,LastName,email,telephone,password,confirmPassword);
		String expected="Your new account has been successfully created!";
		String actual=driver.findElement(By.id("content")).getText();
		Allure.step("Assert 'New Account Created' Message is displayed");
		Assert.assertTrue(actual.contains(expected));
	
		registerPage.OpenAccountLink();
		Allure.step("Assert LogOut button is displayed");
		Assert.assertTrue(driver.findElement(LogOutButton).isDisplayed());
		
		driver.findElement(LogOutButton).click();
	}
	
	
	
	
	@Test(dataProvider="excelData")
	public void invalidRegistration(String firstName,String LastName,String email,String telephone,String password,String confirmPassword)
	{
		registerPage=new RegisterPage(driver);
		registerPage.openRegistrationPage();
		
		registerPage.register(firstName,LastName,email,telephone,password,confirmPassword);
		if(firstName.equals(""))
		{
		
			Assert.assertTrue(registerPage.FindIfErrorExists("First Name must be between 1 and 32 characters!"));
		}
		if(LastName.equals(""))
		{
			Assert.assertTrue(registerPage.FindIfErrorExists("Last Name must be between 1 and 32 characters!!"));
		}
		if(email.equals(""))
		{
			Assert.assertTrue(registerPage.FindIfErrorExists("E-Mail Address does not appear to be valid!"));
		}
		if(telephone.equals(""))
		{
			Assert.assertTrue(registerPage.FindIfErrorExists("Telephone must be between 3 and 32 characters!"));
		}
		if(password.length()< 4 || password.length()>20 || password.equals(""))
		{
			Assert.assertTrue(registerPage.FindIfErrorExists("Password must be between 4 and 20 characters!"));
		}
	
		
	}
	
}


