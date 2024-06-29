package com.qa.opencart.test;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest{

	@BeforeClass	
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void accPageTitleTest() {
		String accPagetitle = accPage.getAccPageTitle();
		Assert.assertEquals(accPagetitle, AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void accPageUrlTest() {
		 Assert.assertTrue(accPage.getAccPageUrl());
	}
	
	@Test(priority = 3)
	public void searchExistsTest() {
		Assert.assertTrue(accPage.isSearchExists());
	}
	
	@Test(priority = 4)
	public void logoutLinkExistsTest() {
		Assert.assertTrue(accPage.isLogoutLinkExists());
	}
	
	@Test(priority = 5)
	public void accPageHeadersListTest() {
		ArrayList<String> actualHeadersList = accPage.getAccSectionHeadersList();
		System.out.println("Actual AccPage headers:" +actualHeadersList);
		Assert.assertEquals(actualHeadersList, AppConstants.ACC_PAGE_SECTIONS_HEADERS);
	}
	
	@DataProvider
	public Object[] getProductKey() {
		return new Object[][] {
			
			{ "Macbook" },
			{ "iMac" },
			{"Samsung" },			
			};
	}
	
	@Test(dataProvider = "getProductKey" , priority=6)
	public void searchCheckTest(String productKey) {
		searchResultsPage = accPage.performSearch(productKey);
		Assert.assertTrue(searchResultsPage.isSearchSuccessfull());
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			
			{ "Macbook", "MacBook Pro" },
			{ "iMac" , "iMac"},
			{"Samsung" , "Samsung SyncMaster 941BW"},
			{ "Macbook","MacBook Air"},
			{"Samsung" , "Samsung Galaxy Tab 10.1"}
			};

	}
	
	
	@Test(dataProvider = "getProductData" ,  priority=7)
	public void searchTest(String searchKey , String productName) {
		searchResultsPage = accPage.performSearch(searchKey);
		//Assert.assertTrue(searchResultsPage.isSearchSuccessfull());	
		if(searchResultsPage.isSearchSuccessfull()) {
			prodInformationPage = searchResultsPage.selectProduct(productName);
			String actProductHeader = prodInformationPage.getProductHeader(productName);
			Assert.assertEquals(actProductHeader, productName);
		}
	}
	
	
	
}
