package Base;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
	public void scrollElement(WebElement element)
	{
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	public void scrollElement(Select continueShipping)
	{
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueShipping);
	}

	public void clickElement(By locator)
	{
		
		try {
		commonWaits(locator);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		driver.findElement(locator).click();	
		}catch(ElementClickInterceptedException e)
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(locator));
		}
	}
	
	public void setText(By locator,String text)
	{
		commonWaits(locator);
		driver.findElement(locator).sendKeys(text);
	}
	
	public String getText(By locator)
	{
		commonWaits(locator);
		return driver.findElement(locator).getText();
	}
	
	public boolean isDisplayed(By locator)
	{
		return driver.findElements(locator).size()>0;
	}
	private void commonWaits(By locator)
	{
		
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
	}
	
	public boolean checkIfTextExistInLocator(By locator, String text)
	{
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
		
		return getText(locator).contains(text);
		
	}
	public double convertTextPriceToDouble(String price)
	{
		return Double.parseDouble(price.replace('$', ' ').replaceAll(",", ""));
	}
}
