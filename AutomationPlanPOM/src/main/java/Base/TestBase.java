package Base;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import Pages.LoginPage;
import config.PropertiesFile;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;

public class TestBase {
	
	public static WebDriver driver;
	protected LoginPage loginPage;
	public static WebDriverWait wait;
	protected Properties prop;	
	
	public TestBase() throws IOException {
		prop=PropertiesFile.getProperties();
	}
	@BeforeMethod
	public void SetUpDriver()
	{
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--ignore-ssl-errors=yes");
		options.addArguments("--ignore-certificate-errors");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
	}
	public String getScreenShot(String testCaseName,WebDriver driver2) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver2;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File destination=new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
	}
	@AfterMethod()
	public void tearDown(ITestResult result)
	{
		driver.close();

		/*
		if(ITestResult.FAILURE==result.getStatus())
		{
			Allure.addAttachment(result.getMethod().getMethodName(), new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
		}
		*/	
		
	}
	
	@AfterSuite
	public void runReport() throws IOException
	{
		//String command2 ="allure.bat serve "+prop.getProperty("Path");
		String command2 ="allure.bat serve "+System.getProperty("user.dir")+"\\allure-results";
		
		runWindowsCommand(command2);
	}
	
	
	@BeforeSuite
	public void removeOLDTests()
	{
		String command=System.getProperty("user.dir")+"\\allure-results\\delete.bat";
		
		runWindowsCommand(command);
	}
	
	
	
	public static void runWindowsCommand(String command) {
        try {



           System.out.println("command : [" + command + "]");
            Runtime rt = Runtime.getRuntime();
			Process pr = rt.exec(command);

           BufferedReader stdInput = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(pr.getErrorStream()));

           // read the output from the command
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println("Here is the standard output of the command:\n");
                System.out.println(s);
            }
            // read any errors from the attempted command
            while ((s = stdError.readLine()) != null) {
                System.out.println("Here is the standard error of the command (if any):\n");
                System.out.println(s);
            }
            pr.destroy();
        } catch (IOException e) {
            System.out.println("Here is the standard error of the command (if any):\n");
            System.out.println(e.getMessage());
        }



   
}
}