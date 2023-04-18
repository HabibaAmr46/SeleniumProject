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
	
	public void writeDataToExcel() throws IOException
	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\Excel\\data.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		rowNum++;
		
		XSSFRow row=sheet.createRow(rowNum);
		
		
		XSSFCell cell0=row.createCell(0);
		cell0.setCellValue(driver.findElement(productName).getText());
		
		XSSFCell cell1=row.createCell(1);
		if(CheckIfWebElementExists(productBrand)) {
		cell1.setCellValue(driver.findElement(productBrand).getText());
		}
		else
		{
			cell1.setCellValue("");
		}
		
		XSSFCell cell2=row.createCell(2);
		cell2.setCellValue(splitString(driver.findElement(productCode).getText()));
		
		XSSFCell cell3=row.createCell(3);
		if(CheckIfWebElementExists(productPoints)) {
		cell3.setCellValue(splitString(driver.findElement(productPoints).getText()));
		}
		else
		{
			cell3.setCellValue("");
			
		}
		
		XSSFCell cell4=row.createCell(4);
		cell4.setCellValue(driver.findElement(price).getText());
		
		
		
		FileOutputStream fio=new FileOutputStream(System.getProperty("user.dir")+"\\Excel\\data.xlsx");
		workbook.write(fio);
		workbook.close();
	
	}
	
	@Step("Change the delivery date then add it to the shopping cart")
	public void AddProductToCart() throws InterruptedException {
		scrollElement(driver.findElement(CalenderButton));
		driver.findElement(CalenderButton).click();
		driver.findElement(newday).click();
		driver.findElement(AddToCartButton).click();
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
