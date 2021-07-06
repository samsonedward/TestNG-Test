package pages;

import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;
import cucumber.api.java.en.Then;

public class HomePage extends BaseClass {
	
	public HomePage(ChromeDriver driver,ExtentTest test,ExtentTest node) {
		this.driver = driver;
		this.test = test;
		this.node = node;

	}

	public LoginPage clickLogoutButton() throws IOException {

		try {
			driver.findElementByClassName("decorativeSubmit").click();
			reportStep("logout clicked successfully", "pass");
		} catch (Exception e) {
			reportStep("logout button not clicked", "fail");
		}

		return new LoginPage(driver,test,node);

	}

	@Then("homepage should be displayed")
	public HomePage verifyHomePage() {
		title = driver.getTitle();

		if (title.contains("Leaftaps")) {
			System.out.println("we are in right page");
		} else {
			System.out.println("we are not in right page");
		}

		return this;
	}

}
