package Base;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	
	public PageBase(WebDriver driver)
	{
		this.driver=driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	public void scrollElement(By continueShipping)
	{
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueShipping);
	}
	public void scrollElement(Select continueShipping)
	{
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueShipping);
	}

}
