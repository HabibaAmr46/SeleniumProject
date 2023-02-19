package StepDefinitions;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;

import Base.TestBase;
import Pages.LoginPage;
import io.cucumber.java.*;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginSteps extends TestBase {
	LoginPage loginPage;
	public LoginSteps() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Before
	public void setUp()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@After
	public void tearDown()
	{
		driver.quit();
	}

	@Given("I am on login Page")
	public void i_am_on_login_page() {
		// Write code here that turns the phrase above into concrete actions

		driver.get(prop.getProperty("url"));
		loginPage=new LoginPage(driver);
		loginPage.OpenLoginPage();
	}

	@When("Enter User Credentials Successfully {string} and {string}")
	public void enter_user_credentials_successfully(String username,String password) {
		// Write code here that turns the phrase above into concrete actions

		loginPage.login(username, password);
	}

	@Then("The account page is displayed")
	public void the_account_page_is_displayed() {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(driver.getTitle().contains("My Account"));
	}
}
