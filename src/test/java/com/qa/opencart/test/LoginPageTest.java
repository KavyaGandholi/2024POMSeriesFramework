package com.qa.opencart.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.LoginPage;

public class LoginPageTest extends BaseTest {

//	WebDriver driver;  //given 'public' webdriver in basetest class
	
	@Test(priority = 1)
	public void loginPageTitleTest() {	
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);		
	}
	
	@Test(priority = 2)
	public void loginPageUrlTest() {
		Boolean actualUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(actualUrl);
	}
	
	@Test(priority = 3)
	public void isForgotPwdLinkExistsTest() {
		Assert.assertEquals(loginPage.isForgotPwdLinkExists() , true);
		
	}
	
	@Test(priority = 4)
	public void logintest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));	
		Assert.assertTrue(accPage.isLogoutLinkExists());
	}
}
