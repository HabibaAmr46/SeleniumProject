package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import Base.PageBase;

public class SearchPage extends PageBase {
	
	public SearchPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	By searchKeyword=By.id("input-search");
	
	By searchCategories=By.name("category_id");
	
	By searchButton=By.id("button-search");
	
	By NoProductMessage=By.xpath("//div[@id=\"content\"]/p[2]");
	
	By result=By.tagName("h4");
	
	By searchCategoriesCheckBox=By.xpath("//input[@name='sub_category']");
	
	
	public SearchPage search()
	{
		Select category=new Select(driver.findElement(searchCategories));
		category.selectByVisibleText("Components");
		clickElement(searchButton);
		return this;
		
	}
	public SearchPage search(String product)
	{
		setText(searchKeyword, product);
		Select category=new Select(driver.findElement(searchCategories));
		category.selectByVisibleText("Components");
		clickElement(searchCategoriesCheckBox);
		clickElement(searchButton);
		
		return this;
		
		
	}
	public WebElement NoElementInShoppingCart()
	{
		return driver.findElement(NoProductMessage);
	}
	public WebElement getSearchResults()
	{
		return driver.findElement(result);
	}

}
