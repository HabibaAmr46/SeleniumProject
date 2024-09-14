package Pages;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Base.PageBase;
import io.qameta.allure.Step;

public class ProductPage extends PageBase{

	public ProductPage(WebDriver driver) {
		super(driver);
	}
	
	By CalenderButton=By.xpath("(//span[@class='input-group-btn'])[2]/button");
	

	By newday=By.xpath("//td[text()='8'][@class='day new']");
	
	By AddToCartButton=By.id("button-cart");
	By productName=By.cssSelector("#content h1");
	By productBrand=By.xpath("//li[contains(text(),'Brands')]/a");
	By productCode=By.xpath("//li[contains(text(),'Product Code')]");
	By productPoints=By.xpath("//li[contains(text(),'Points')]");
	By price=By.cssSelector("#content h2:nth-child(1)");
	public static int rowNum=0;
	
		
	@Step("Change the delivery date then add it to the shopping cart")
	public ProductPage AddProductToCart() throws InterruptedException {
		
		clickElement(CalenderButton);
		clickElement(newday);
		clickElement(AddToCartButton);
		
		return this;
	}
	
	public String splitString(String s)
	{
		String[] arrOfStr = s.split(":", 5);
		return arrOfStr[1];
	}

	public boolean CheckIfWebElementExists(By locator)
	{
		 try {
		        driver.findElement(locator);
		        return true;
		    } catch (org.openqa.selenium.NoSuchElementException e) {
		        return false;
		    }	
	}

}
