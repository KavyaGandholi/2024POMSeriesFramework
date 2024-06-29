package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1.By Locators
	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By lgnBtn = By.xpath("//input[@type='submit']");
	private By logoImage = By.cssSelector("img[title='naveenopencart']");
	private By forgotPwd = By.linkText("Forgotten Password");

	// 2.Page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	// 2.page actions

	public String getLoginPageTitle() {

		String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIMEOUT, AppConstants.LOGIN_PAGE_TITLE);

		// String title = driver.getTitle(); //instead of using driver.getTitle-- we
		// created elementUtil method
		System.out.println("login page title is:" + title);
		return title;
	}

	public boolean getLoginPageUrl() {

		String url = eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIMEOUT, AppConstants.LOGIN_PAGE_URL);

		// String url = driver.getCurrentUrl();

		System.out.println("login oage url is : " + url);
		if (url.contains(AppConstants.LOGIN_PAGE_URL)) {
			return true;
		}
		return false;
	}

	public boolean isForgotPwdLinkExists() {
		// return driver.findElement(forgotPwd).isDisplayed();
		return eleUtil.doEleIsDisplayed(forgotPwd);
	}

	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("user credentials are : " + username + " : " + pwd);

		eleUtil.doSendKeysWithWait(email, AppConstants.DEFUALT_LARGE_TIMEOUT, username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(lgnBtn);

		// method responsibility to return next page object when landing on new page
		// after click
		return new AccountsPage(driver);
	}

}
