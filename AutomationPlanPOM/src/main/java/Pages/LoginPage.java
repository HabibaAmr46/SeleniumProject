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
	public By errorMessage=By.xpath("//div[contains(@class,'alert')]");
	
	
	public void OpenLoginPage()
	{
		driver.findElement(accountLink).click();
		driver.findElement(loginLink).click();
		/*
		driver.findElement(Details_Button).click();
		driver.findElement(Proceed_Link).click();
		*/
	}
	@Step("User Login with username: {0} and password :{1}")
	public void login(String em,String pass)
	{
		driver.findElement(email).sendKeys(em);
		driver.findElement(password).sendKeys(pass);
		driver.findElement(loginButton).click();
	}
	

}
