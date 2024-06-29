package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//every by locators in Page Object Model should be created with private keyword
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By accPageSecHeaders = By.cssSelector("div#content h2");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getAccPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIMEOUT, AppConstants.ACCOUNT_PAGE_TITLE);
		System.out.println("Acc page title is : " + title);
		return title;
	}
	
	public boolean getAccPageUrl() {
		String url = eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIMEOUT, AppConstants.ACC_PAGE_URL);
		System.out.println("Acc page url is : " +url);
		if(url.contains(AppConstants.ACC_PAGE_URL)) {
			return true;
		}
		return false;
	}
		
	public boolean isLogoutLinkExists() {
		return eleUtil.doEleIsDisplayed(logoutLink);
		 
	}
	
	public boolean isSearchExists() {
		return eleUtil.doEleIsDisplayed(search);
	}
	
	
	public SearchResultsPage performSearch(String productKey) {
		System.out.println("Product key is : " +productKey);
		if(isSearchExists()) {
			eleUtil.doSendKeys(search, productKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultsPage(driver);
		}
		else{
			System.out.println("Search field is not present on the page");
			
		}
		return null;
	}
	
	
	public ArrayList<String> getAccSectionHeadersList() {
		
		List<WebElement> secList = eleUtil.waitForElementsToBeVisible(accPageSecHeaders, AppConstants.DEFUALT_LARGE_TIMEOUT);
		
		//List<WebElement> secList = driver.findElements(accPageSecHeaders);
		
		System.out.println("totla sections headers are:" +secList.size());
		
		//to collect all sec headers in particular list
		ArrayList<String> accSecTextList = new ArrayList<String>();
		
		for(WebElement e : secList) {
			String text = e.getText();
			accSecTextList.add(text);
		} 		
		return accSecTextList;	
	}
}
