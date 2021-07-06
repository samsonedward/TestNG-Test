package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class LoginAndVerify extends BaseClass {
	@BeforeTest
	public void setFile() {
		testName="LoginAndVerify";
		testDesc="Login and verify functionality for leaftaps";
		author="Hari";
		category="smoke";
		fileName = "credentials2";

	}
	
	@Test(dataProvider="fetchData")
	public void runLoginVerify(String username,String password) throws InterruptedException, IOException {
		
	//	LoginPage lp = new LoginPage();
		new LoginPage(driver,test,node)
		.enterUserName(username)
		.enterPassword(password)
		.clickLoginButton()
		.verifyHomePage();
		
		
	}

}
