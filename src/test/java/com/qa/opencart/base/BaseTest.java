package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProdInformationPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {

	
	DriverFactory df;  //creating driver factory refernce variable
	
	public Properties prop;
	
	public WebDriver driver; //driver reference to store in the driver
	
	public LoginPage loginPage; 
	public AccountsPage accPage;
	public SearchResultsPage searchResultsPage;
	public ProdInformationPage prodInformationPage;
	
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProperties();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		
	}
	
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();  //if we don't initialize the driver we will get NPE 
	}
	
}
