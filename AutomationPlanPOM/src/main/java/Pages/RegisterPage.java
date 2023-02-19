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
	
	By logout= By.linkText("logout");

	By Details_Button=By.id("details-button");
	
	By Proceed_Link=By.id("proceed-link");
	@Step("Open Account Menu")
	public void OpenAccountLink()
	{
		driver.findElement(accountLink).click();
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
	public void openRegistrationPage()
	{
		driver.findElement(accountLink).click();
		driver.findElement(registrationLink).click();
		driver.findElement(Details_Button).click();
		driver.findElement(Proceed_Link).click();
		
	}
	
	@Step("Fill the values: {0}, {1},{2},{3},{4},{5}")
	public void register(String FN,String LN,String em,String telephone2,String pass,String confirmPass)
	{
		driver.findElement(firstName).sendKeys(FN);
		driver.findElement(lastName).sendKeys(LN);
		driver.findElement(email).sendKeys(em);
		driver.findElement(telephone).sendKeys(telephone2);
		driver.findElement(password).sendKeys(pass);
		driver.findElement(confirmPassword).sendKeys(confirmPass);
		driver.findElement(agreementCheckBox).click();
		driver.findElement(continueButton).click();
		
	}
	
	@Step("Check for Error: {0} exists")
	public boolean FindIfErrorExists(String expected)
	{
		
		List<WebElement> errorTexts=driver.findElements(By.cssSelector("div.text-danger"));
		/*
		for(WebElement errorText:errorTexts)
		{
			if(errorText.getText().contains(expected))
				return true;
		}
		
		return false;
		*/
		
		return errorTexts.stream().map(e-> e.getText()).filter(e->e.contains(expected)).count()==1;
		
	}
	
	

}
