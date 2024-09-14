package Pages;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Base.PageBase;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class ShoppingCartPage extends PageBase {

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
	}
	
	By shoppingCartLink=By.cssSelector("a[title='Shopping Cart']");	
	By shoppingCartTitle=By.xpath("(//h1)[2]");
	
	By purchasedItemsRows=By.xpath("(//table[contains(@class,'table table-bordered')])[2]/tbody/tr");

	By total=By.xpath("(//table[contains(@class,'table table-bordered')])[3]/tbody/tr[2]/td[2]");

	By checkOutButton=By.linkText("Checkout");
	
	@Step("Open Shopping Cart Page")
	public ShoppingCartPage OpenShoppingCart()
	{
		clickElement(shoppingCartLink);
		return this;
	}
	
	
	@Step("Validate Product :{0} is added to the shopping cart")
	public boolean validateIfProductExists(String name,String price)
	{
		List <WebElement> purchasedItemsRowsList=driver.findElements(purchasedItemsRows);
		/*for(WebElement p:purchasedItemsRowsList) {
			
			if(p.getText().contains(name) && p.getText().contains(price)) {
				return true;
			}

		}
		*/
		long count=purchasedItemsRowsList.stream().filter(element->(element.getText().contains(name)&&element.getText().contains(price)))
					.count();
		
		
		return count>0.0;
				
		
	}
	
	@Step("Validate Product :{0} is added to the shopping cart")
	public boolean validateIfProductExists(String name,String price,String delivery)
	{
		List <WebElement> purchasedItemsRowsList=driver.findElements(purchasedItemsRows);
		for(WebElement p:purchasedItemsRowsList) {
			
			if(p.getText().contains(name) && p.getText().contains(price) && p.getText().contains(delivery)) {
				return true;
			}

		}
		return false;
	}
	
	@Step("Calculating expected total")
	public double checkTotal()
	{
		By totalCol=By.xpath("(//table[contains(@class,'table table-bordered')])[2]/tbody/tr/td[6]");
		List<WebElement> totalElements=driver.findElements(totalCol);
		
		
		return totalElements.stream().map(element->element.getText()).map(element->convertTextPriceToDouble(element)).reduce(0.0, (a, b) -> a+b);
	}
	
	@Step("Getting the actual total")
	public double getActualTotal()
	{
		
		return convertTextPriceToDouble(getText(total));
		
	}
	public void checkOut()
	{
		clickElement(checkOutButton);
	}

}
