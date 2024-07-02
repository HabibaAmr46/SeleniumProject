package Pages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Base.PageBase;
import io.qameta.allure.Step;

public class RegisterPage extends PageBase {

	public RegisterPage(WebDriver driver) {
		super(driver);
	}
	
	
	By accountLink=By.xpath("//*[@id='top-links']/ul/li[2]");
	
	By registrationLink=By.linkText("Register");
	

	By firstName=By.id("input-firstname");
	
	By lastName=By.id("input-lastname");
	
	
	By email=By.id("input-email");
	
	By telephone=By.id("input-telephone");
	

	By password=By.id("input-password");

	By confirmPassword=By.id("input-confirm");
	
	By agreementCheckBox=By.name("agree");
	
	By continueButton= By.xpath("//input[@type='submit']");
	By text=By.id("content");
	
	By logout= By.linkText("Logout");

	By Details_Button=By.id("details-button");
	
	By Proceed_Link=By.id("proceed-link");
	@Step("Open Account Menu")
	public RegisterPage OpenAccountLink()
	{
		clickElement(accountLink);
		return this;
	}
	@Step("LogOut")
	public void LogOut()
	{
		driver.findElement(logout).click();
	}
	public boolean CheckLogOutButtonIsDisplayed()
	{
		return driver.findElement(logout).isDisplayed();
	}
	
	@Step("Open Registration Page")
	public RegisterPage openRegistrationPage()
	{
		
		clickElement(accountLink);
		clickElement(registrationLink);
		return this;
		
	}
	
	@Step("Fill the values: {0}, {1},{2},{3},{4},{5}")
	public RegisterPage register(String FN,String LN,String em,String telephone2,String pass,String confirmPass)
	{
		
		setText(firstName, FN);
		setText(lastName, LN);
		setText(email, em+System.currentTimeMillis());
		setText(telephone,telephone2);
		setText(password, pass);
		setText(confirmPassword, confirmPass);
		clickElement(agreementCheckBox);
		clickElement(continueButton);
		return this;
		
		
	}
	
	@Step("Check for Error: {0} exists")
	public boolean FindIfErrorExists(String expected)
	{
		
		List<WebElement> errorTexts=driver.findElements(By.cssSelector("div.text-danger"));
		return errorTexts.stream().map(e-> e.getText()).filter(e->e.contains(expected)).count()==1;
		
	}
	
	@Step("Get the message")
	public String getTheMessage()
	{
		return getText(text);
	}
	
	@Step("Click on Log Out Button")
	public RegisterPage clickOnLogOut()
	{
		clickElement(logout);
		return this;	
	}
	
	@Step("Check Log Out button is displayed")
	public boolean logOutButtonIsDisplayed()
	{
		
		return isDisplayed(logout);
		
	}
	
	

}
