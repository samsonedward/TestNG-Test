package base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.management.RuntimeErrorException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import utils.ReadExcel;

public class BaseClass {

	public ChromeDriver driver;
	public static String title;
	public String fileName;
	
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	
	public ExtentTest test,node;
	
	public String testName,testDesc,author,category;
	
	@BeforeSuite
	public void startReport() {
	
				reporter = new ExtentHtmlReporter("./reports/result.html");
				reporter.setAppendExisting(true);
				extent = new ExtentReports();
				extent.attachReporter(reporter);

	}
	
	@BeforeClass
	public void testDetails() {

		test = extent.createTest(testName, testDesc);
		test.assignAuthor(author);
		test.assignCategory(category);

	}
	
	
	
	public long takeSnap() throws IOException {
		
		long number =(long) Math.floor(((Math.random()*90000000L)+10000000L));
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		
		File dest = new File("./snaps/img"+number+".png");
		
		FileUtils.copyFile(source, dest);
		
		return number;
	}
	
	
		
	
	
	public void reportStep(String msg,String status) throws IOException {

		if(status.equalsIgnoreCase("pass")) {
			node.pass(msg,MediaEntityBuilder.createScreenCaptureFromPath(".././snaps/img"+takeSnap()+".png").build());
		}
		else if(status.equalsIgnoreCase("fail")) {
			node.fail(msg,MediaEntityBuilder.createScreenCaptureFromPath(".././snaps/img"+takeSnap()+".png").build());
			throw new RuntimeException();
		}

	}
	
	

		
	@BeforeMethod
	public void preCondition() throws IOException {
		node = test.createNode(testName);
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void postCondition() throws IOException {
		driver.close();

	}

	@DataProvider(name = "fetchData")
	public String[][] sendData() throws IOException {

		return ReadExcel.excelData(fileName);

	}
	
	
	@AfterSuite
	public void endReport() {
		extent.flush();

	}
	
}
