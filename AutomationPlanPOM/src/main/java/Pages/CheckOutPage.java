package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import Base.PageBase;
import io.qameta.allure.Step;

public class CheckOutPage extends PageBase{

	public CheckOutPage(WebDriver driver) {
		super(driver);
	}
	

	public By newPaymentAdressButton=By.xpath("//input[@name='payment_address'][@value='new']");
	
	By firstNamePayment=By.id("input-payment-firstname");
	
	By lastNamePayment=By.id("input-payment-lastname");
	
	By adressPayment=By.id("input-payment-address-1");
	
	By cityPayment=By.id("input-payment-city");
	
	By countryPayment=By.id("input-payment-country");
	
	By statePayment=By.id("input-payment-zone");
	
	By continuePayment=By.id("button-payment-address");
	
	By newShippingAdressButton=By.xpath("//input[@name='shipping_address'][@value='new']");

	By firstNameShipping=By.id("input-shipping-firstname");
	
	By lastNameShipping=By.id("input-shipping-lastname");
	
	By adressShipping=By.id("input-shipping-address-1");
	
	By cityShipping=By.id("input-shipping-city");
	
	By postalCode=By.id("input-shipping-postcode");

	By countryShipping=By.id("input-shipping-country");

	By stateShipping=By.id("input-shipping-zone");
	
	By continueShipping=By.id("button-shipping-address");
	
	By comment=By.tagName("textarea");
	
	By cashOnDelivery=By.xpath("(//input[@name='payment_method'])[2]");
	
	@Step("Fill the payment details")
	public void fillPaymentDetails(String FN,String LN,String adress,String city,String coun,String stat) throws InterruptedException
	{
		Thread.sleep(3000);
		driver.findElement(newPaymentAdressButton).click();
		driver.findElement(firstNamePayment).sendKeys(FN);
		driver.findElement(lastNamePayment).sendKeys(LN);
		driver.findElement(adressPayment).sendKeys(adress);
		driver.findElement(cityPayment).sendKeys(city);
		wait.until(ExpectedConditions.elementToBeClickable(countryPayment));
		Select country=new Select(driver.findElement(countryPayment));
		country.selectByVisibleText(coun);
		Thread.sleep(2000);
		Select state = new Select(driver.findElement(statePayment));
		state.selectByVisibleText(stat);
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(continuePayment));
		driver.findElement(continuePayment).click();
	}
	
	@Step("Fill Shipping Details")
	public void fillShippingDetails(String FN,String LN,String adress,String city,String postal,String coun,String stat) throws InterruptedException
	{
		Thread.sleep(3000);
		driver.findElement(newShippingAdressButton).click();
		driver.findElement(firstNameShipping).sendKeys(FN);
		driver.findElement(lastNameShipping).sendKeys(LN);
		driver.findElement(adressShipping).sendKeys(adress);
		driver.findElement(cityShipping).sendKeys(city);
		driver.findElement(postalCode).sendKeys(postal);
		Thread.sleep(2000);
		Select country=new Select(driver.findElement(countryShipping));
		
		country.selectByVisibleText(coun);
		Thread.sleep(2000);
		
		
		Select state = new Select(driver.findElement(stateShipping));
		scrollElement(driver.findElement(stateShipping));
		
		state.selectByVisibleText(stat);
		scrollElement(driver.findElement(continueShipping));
		driver.findElement(continueShipping).click();
		
	}
	@Step("Adding the comment")
	public void addComment(String comm)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(comment));
		driver.findElement(comment).sendKeys(comm);
		driver.findElement(By.id("button-shipping-method")).click();
	}

	By terms_conditions=By.xpath("//input[@name='agree']");

	By PaymentMethodContinue=By.id("button-payment-method");
	
	
	public void proceedPaymentMethod()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(cashOnDelivery));
		driver.findElement(cashOnDelivery).click();
		driver.findElement(terms_conditions).click();
		driver.findElement(PaymentMethodContinue).click();
		
		
	}
	By checkOutSubTotal=By.xpath("//tfoot/tr[1]/td[2]");
	By FlatShippingRate=By.xpath("//tfoot/tr[2]/td[2]");
	public double getCheckOutSubTotal()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(checkOutSubTotal));
		String subtotalS=driver.findElement(checkOutSubTotal).getText();
		subtotalS=subtotalS.replace('$', ' ');
		subtotalS=subtotalS.replaceAll(",", "");
		double subtotal=Double.parseDouble(subtotalS);
		return subtotal;
	}
	
	public double getExpectedCheckOutTotal()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(checkOutSubTotal));
		String subtotalS=driver.findElement(checkOutSubTotal).getText();
		subtotalS=subtotalS.replace('$', ' ');
		subtotalS=subtotalS.replaceAll(",", "");
		double subtotal=Double.parseDouble(subtotalS);
		String FlatShipping=driver.findElement(FlatShippingRate).getText();
		FlatShipping=FlatShipping.replace('$', ' ');
		FlatShipping=FlatShipping.replaceAll(",", "");
		double FlatShippingNo=Double.parseDouble(FlatShipping);
		
		
		return FlatShippingNo+subtotal;
	}
	
	By checkOutTotal=By.xpath("//tfoot/tr[3]/td[2]");
	public double getCheckOutTotal()
	{
		String totalS=driver.findElement(checkOutTotal).getText();
		totalS=totalS.replace('$', ' ');
		totalS=totalS.replaceAll(",", "");
		double total=Double.parseDouble(totalS);
		
		return total;
	}
	
	By confirmOrderButton=By.id("button-confirm");
	
	@Step("Confirming Order")
	public void confirmOrder()
	{
		driver.findElement(confirmOrderButton).click();
	}
}
