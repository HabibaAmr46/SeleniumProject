package Base;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;



public class Listeners extends TestBase implements ITestListener {
	public Listeners() throws IOException {
		
		}
	public static ExtentReports getReporterObject()
	{
		
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Habiba");
		
		return extent;
		
	
	}

	ExtentReports extent=getReporterObject();
	ExtentTest test;
	public void onTestStart(ITestResult result) {
		//test=extent.createTest(result.getMethod().getMethodName());
		
	}

	public void onTestSuccess(ITestResult result) {
		//test.log(Status.PASS, "Test Passed");
		
	}

	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		System.out.println("Hello There");
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}
	public void onTestFailure(ITestResult result) {
		/*
		test.fail(result.getThrowable());
		String filePath=null;
		WebDriver driver=null;
		
		try {
			try {
				driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			 filePath=getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		*/
		
		System.out.println("I am in onTestFailure method " + result.getMethod().getMethodName() + " failed");
		
		// Allure ScreenShotRobot and SaveTestLog
		if (driver instanceof WebDriver) {
			System.out.println("Screenshot captured for test case:" + result.getMethod().getMethodName());
			saveScreenshotPNG(driver);
		}
		// Save a log on allure.
		saveTextLog(result.getMethod().getMethodName() + " failed and screenshot taken!");	
		
	}
	public void onFinish(ITestContext context) {
		//extent.flush();
	}

	

	

	




}
