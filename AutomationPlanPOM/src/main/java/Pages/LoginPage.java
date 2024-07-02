package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Base.PageBase;
import io.qameta.allure.Step;

 public class LoginPage extends PageBase {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	By accountLink=By.xpath("//*[@id='top-links']/ul/li[2]");
	By loginLink=By.linkText("Login");
	By Details_Button=By.id("details-button");
	By Proceed_Link=By.id("proceed-link");
	By email=By.id("input-email");
	By password=By.id("input-password");
	By loginButton=By.xpath("//input[@value='Login']");
	By errorMessage=By.xpath("//div[contains(@class,'alert')]");
	
	
	public LoginPage OpenLoginPage()
	{
		
		
		clickElement(accountLink);
		clickElement(loginLink);
		return this;
	}
	@Step("User Login with username: {0} and password :{1}")
	public LoginPage login(String em,String pass)
	{
		setText(email, em);
		setText(password, pass);
		clickElement(loginButton);
		return this;
	}
	
	@Step("Check if the error message is displayed")
	public boolean checkifErrorMessageIsDisplayed()
	{
		 
		return isDisplayed(errorMessage);
	}
	

}
