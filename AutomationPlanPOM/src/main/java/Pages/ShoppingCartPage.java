package Pages;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Base.PageBase;
import io.qameta.allure.Step;

public class ShoppingCartPage extends PageBase {

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
	}
	
	By shoppingCartLink=By.cssSelector("a[title='Shopping Cart']");	
	
	By purchasedItemsRows=By.xpath("(//table[contains(@class,'table table-bordered')])[2]/tbody/tr");

	By total=By.xpath("(//table[contains(@class,'table table-bordered')])[3]/tbody/tr[2]/td[2]");

	By checkOutButton=By.linkText("Checkout");
	
	@Step("Open Shopping Cart Page")
	public void OpenShoppingCart()
	{
		driver.findElement(shoppingCartLink).click();
	}
	
	@Step("Validate Product :{0} is added to the shopping cart")
	public boolean validateIfProductExists(String name,String price)
	{
		List <WebElement> purchasedItemsRowsList=driver.findElements(purchasedItemsRows);
		for(WebElement p:purchasedItemsRowsList) {
			
			if(p.getText().contains(name) && p.getText().contains(price)) {
				return true;
			}

		}
		return false;
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
		List<String> totalNumbers=new ArrayList<String>();
		double totalSum=0.0;
		for(WebElement p:totalElements)
		{
			totalNumbers.add(p.getText());
		}
		for(String n:totalNumbers)
		{
			n=n.replace('$', ' ');
			n=n.replaceAll(",","");
			totalSum+=Double.parseDouble(n);
		}
	
		return totalSum;
	}
	
	@Step("Getting the actual total")
	public double getActualTotal()
	{
		String totalS=driver.findElement(total).getText();
		totalS=totalS.replace('$', ' ');
		totalS=totalS.replaceAll(",", "");
		double totalNo=Double.parseDouble(totalS);
		return totalNo;
		
	}
	public void checkOut()
	{
		driver.findElement(checkOutButton).click();
	}

}
