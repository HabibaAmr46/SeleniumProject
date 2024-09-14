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
	public CheckOutPage fillPaymentDetails(String FN,String LN,String adress,String city,String coun,String stat) throws InterruptedException
	{
		Thread.sleep(3000);
		clickElement(newPaymentAdressButton);
		setText(firstNamePayment, FN);
		setText(lastNamePayment, LN);
		setText(adressPayment, adress);
		setText(cityPayment, city);
		wait.until(ExpectedConditions.elementToBeClickable(countryPayment));
		Select country=new Select(driver.findElement(countryPayment));
		country.selectByVisibleText(coun);
		Thread.sleep(2000);
		Select state = new Select(driver.findElement(statePayment));
		state.selectByVisibleText(stat);
		Thread.sleep(2000);

		clickElement(continuePayment);
		
		return this;
	}
	
	@Step("Fill Shipping Details")
	public CheckOutPage fillShippingDetails(String FN,String LN,String adress,String city,String postal,String coun,String stat) throws InterruptedException
	{
		Thread.sleep(3000);
		
		clickElement(newShippingAdressButton);
		setText(firstNameShipping, FN);
		setText(lastNameShipping,LN);
		setText(adressShipping, adress);
		setText(cityShipping, city);
		setText(postalCode, postal);
		Thread.sleep(2000);
		Select country=new Select(driver.findElement(countryShipping));
		
		country.selectByVisibleText(coun);
		Thread.sleep(2000);
		
		
		Select state = new Select(driver.findElement(stateShipping));
		scrollElement(driver.findElement(stateShipping));
		state.selectByVisibleText(stat);
		
		clickElement(continueShipping);
		
		return this;
		
	}
	By shippingMethodButton=By.id("button-shipping-method");
	@Step("Adding the comment")
	public CheckOutPage addComment(String comm)
	{
		setText(comment, comm);
		clickElement(shippingMethodButton);
		return this;
	}

	By terms_conditions=By.xpath("//input[@name='agree']");

	By PaymentMethodContinue=By.id("button-payment-method");
	
	
	public CheckOutPage proceedPaymentMethod()
	{
		
		clickElement(cashOnDelivery);
		clickElement(terms_conditions);
		clickElement(PaymentMethodContinue);
		return this;
		
		
	}
	By checkOutSubTotal=By.xpath("//tfoot/tr[1]/td[2]");
	By FlatShippingRate=By.xpath("//tfoot/tr[2]/td[2]");
	public double getCheckOutSubTotal()
	{
		
		return convertTextPriceToDouble(getText(checkOutSubTotal));
	}
	
	public double getExpectedCheckOutTotal()
	{

		double subtotal=convertTextPriceToDouble(getText(checkOutSubTotal));
		double FlatShippingNo=convertTextPriceToDouble(getText(FlatShippingRate));
		
		
		return FlatShippingNo+subtotal;
	}
	
	By checkOutTotal=By.xpath("//tfoot/tr[3]/td[2]");
	public double getCheckOutTotal()
	{
		String totalS=getText(checkOutTotal);
		return convertTextPriceToDouble(totalS);
	}
	
	By confirmOrderButton=By.id("button-confirm");
	
	@Step("Confirming Order")
	public CheckOutPage confirmOrder()
	{
		clickElement(confirmOrderButton);
		return this;
	}
	
	
	
	
}
