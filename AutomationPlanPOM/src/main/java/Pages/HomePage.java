package Pages;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import Base.PageBase;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class HomePage extends PageBase{

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	By accountLink=By.xpath("//*[@id='top-links']/ul/li[2]");
	
	By logOutLink=By.linkText("Logout");
	
	By DesktopsLink=By.linkText("Desktops");
	
	By ShowAllDesktops=By.linkText("Show All Desktops");
	
	
	
	By Product_Prices=By.xpath("//p[@class='price']");
	
	By currencyButton=By.xpath("//*[@id=\"form-currency\"]/div/button");
	
	By EuroButton=By.xpath("//button[@name='EUR']");
	
	public By LatestBreadCrumbElement=By.xpath("//ul[@class='breadcrumb']/li[2]/a");
	
	By Tablets=By.linkText("Tablets");
	
	By AddtoCartTablet=By.xpath("//div[@class='button-group']/button[1]");
	
	By Phones=By.linkText("Phones & PDAs");
	
	By sort=By.id("input-sort");

	public By ActiveSideBarElement=By.xpath("//a[contains(@class,'active')]");
	
	By searchBar=By.name("search");
	
	By searchBarButton=By.xpath("//*[@id=\"search\"]/span/button");
	
	public By successAddToCartMessage=By.xpath("//div[contains(@class,'alert')]");

	By Laptops_notebooks=By.linkText("Laptops & Notebooks");
	
	By ShowAllLaptops=By.linkText("Show All Laptops & Notebooks");
	
	By AddToCartLaptop=By.xpath("(//div[@class='button-group'])[1]/button[1]");
	
	By MP3_Players=By.linkText("MP3 Players");
	
	By showAllMP3=By.linkText("Show All MP3 Players");
	

	By AddToCartIPODShuffle=By.xpath(" (//div[@class='button-group'])[3]/button[1]");
	
	
	
	By purchasedItemsSubMenuLocator=By.xpath("//table[contains(@class,'table table-striped')]/tbody/tr");
	
	public By itemsSubMenuButton=By.xpath("//div[@id='cart']/button");
	
	By viewCartSubMenu=By.cssSelector("p[class='text-right']>a:nth-child(1)");
	
	public By confirmOrderResult=By.xpath("//div[@id='content']/h1");
	
	public void ClickViewCartSubMenu()
	{
		
		driver.findElement(viewCartSubMenu).click();
	}
	
	@Step("Add \"Samsung Galaxy Tab 10.1\" to the cart ")
	public void ClickAddToCartTabletFunc()
	{
		driver.findElement(AddtoCartTablet).click();
	}
	public void clickSearchBarButton()
	{
		driver.findElement(searchBarButton).click();
	}
	@Step("Show All DeskTops")
	public void ShowAllDesktops()
	{
		driver.findElement(DesktopsLink).click();
		driver.findElement(ShowAllDesktops).click();
	}
	
	@Step("Show All the Laptops")
	public void ShowAllLaptops()
	{
		driver.findElement(Laptops_notebooks).click();
		driver.findElement(ShowAllLaptops).click();
	}
	public void ShowAllMP3()
	{
		driver.findElement(MP3_Players).click();
		driver.findElement(showAllMP3).click();
	}
	
	public void addIPODToCart()
	{
		driver.findElement(AddToCartIPODShuffle).click();
	}
	@Step("View Laptop Details")
	public void ViewLaptopDetails()
	{
		driver.findElement(AddToCartLaptop).click();
	}
	
	@Step("Click on Euro Currency")
	public void ClickEuro()
	{
		driver.findElement(currencyButton).click();
		driver.findElement(EuroButton).click();
	}
	
	@Step("Click On All Tablets")
	public void ClickTablets()
	{
		driver.findElement(Tablets).click();
	}
	
	
	public void addToCartTablet()
	{
		driver.findElement(AddtoCartTablet).click();
	}
	public boolean CheckIFProductsContainsCurrency(String currency)
	{
		List<WebElement> Product_PricesList=driver.findElements(Product_Prices);
		/*
		for(int i=0;i<Product_PricesList.size();i++)
		{
			if(!Product_PricesList.get(i).getText().contains(currency))
			{
				return false;
			}
		}
		*/
		
		
		long sum= Product_PricesList.stream().filter(s->s.getText().contains(currency)).count();
		return sum==Product_PricesList.size();
	}
	public List<String> GetALLProductsNames()
	{
		List<WebElement> Product_NamesW= driver.findElements(By.tagName("h4"));
		List<String> Product_NamesS=new ArrayList<String>();
		for(int i=0;i<Product_NamesW.size();i++)
		{
			Product_NamesS.add(Product_NamesW.get(i).getText());
		}
		return Product_NamesS;
	}
	
	@Step("Search for product:{0}")
	public void search(String product)
	{
		driver.findElement(searchBar).sendKeys(product);
		driver.findElement(searchBarButton).click();
	}
	public boolean CheckIFProductExistsInSubMenu(String Product) throws InterruptedException
	{
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(itemsSubMenuButton));
		driver.findElement(itemsSubMenuButton).click();
		List<WebElement> purchasedItemsSubMenu=driver.findElements(purchasedItemsSubMenuLocator);
		/*
		for(WebElement product:purchasedItemsSubMenu)
		{
			if(product.getText().contains(Product))	
				return true;
		}
		return false;
		*/
		
		long count=purchasedItemsSubMenu.stream().filter(s->s.getText().contains(Product)).count();
		return count==1;
			
		
		
	}
	
	public boolean CheckIFProductExists(String Product)
	{
		
		List<String> Names=GetALLProductsNames();
		/*
		boolean contain=true;
		for(String name:Names)
		{
			if(!name.contains(Product)) {
				contain=false;
				break;
			}			
			
		}
		return contain;
		*/
		
		return Names.stream().filter(s->!s.contains(Product)).count()==0;
		
	}
	
	@Step("Sorting the products from A-Z")
	public void sortAsc()
	{
		Select sortOption=new Select(driver.findElement(sort));
		sortOption.selectByIndex(1);
	}
	@Step("Sorting the products from Z-A")
	public void sortDesc()
	{
		Select sortOption=new Select(driver.findElement(sort));
		sortOption.selectByIndex(2);
	}
	
	public void SelectAllPhones()
	{
		driver.findElement(Phones).click();
	}
	
	@Step("Logout")
	public void Logout()
	{
		driver.findElement(accountLink).click();
		driver.findElement(logOutLink).click();
	}

}
