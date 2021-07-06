package testcases;

import org.testng.annotations.BeforeTest;

import base.BaseClass;
import cucumber.api.CucumberOptions;

@CucumberOptions(features="src/main/java/features",
				 glue="pages",
				 monochrome=true)
public class RunLoginUsingCucumber extends BaseClass {
	
	@BeforeTest
	public void setInfo() {
	
		testName="LoginAndLogout using cucumber";
		testDesc="Login and logout functionality for leaftaps";
		author="Hari";
		category="smoke";
		

	}

}
