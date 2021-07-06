package testcases;

import java.io.IOException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base.BaseClass;
import pages.LoginPage;

public class LoginAndLogout extends BaseClass {
	
	@BeforeTest
	public void setInfo() {
	
		testName="LoginAndLogout";
		testDesc="Login and logout functionality for leaftaps";
		author="Sam";
		category="smoke";
		fileName = "credentials";		

	}
	
	@Test(dataProvider="fetchData")
	public void runLoginLogout(String username,String password) throws IOException  {
		
	//	LoginPage lp = new LoginPage();
		new LoginPage(driver,test,node)
		.enterUserName(username)
		.enterPassword(password)
		.clickLoginButton()
		.verifyHomePage()
		.clickLogoutButton();
		
		
	}

}
