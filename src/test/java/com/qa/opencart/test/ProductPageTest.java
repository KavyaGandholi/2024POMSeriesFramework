package com.qa.opencart.test;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.LoginPage;

public class ProductPageTest extends BaseTest {

	@BeforeClass
	public void prodInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));	
	}
	
	@DataProvider
	public Object[] getImageDetails() {
		return new Object[][] {
			
			{ "Macbook" , "MacBook Pro"},
			{ "iMac" , "iMac" },
			{"Macbook","MacBook Air" },			
			};
	}
	
	@Test(dataProvider = "getImageDetails")
	public void productHeaderTest(String productKey, String productName) {
		searchResultsPage = accPage.performSearch(productKey);
		prodInformationPage = searchResultsPage.selectProduct(productName);
		String actProdHeader = prodInformationPage.getProductHeader(productName);
		Assert.assertEquals(actProdHeader, productName);
	}
	
	@DataProvider
	public Object[][] getProductInfoData() {
		return new Object[][] {
			
			{ "Macbook", "MacBook Pro" , AppConstants.MACBOOK_PRO_IMAGES_COUNT},
			{ "Macbook" , "MacBook Air" , AppConstants.MACBOOK_AIR_IMAGES_COUNT},
			{"iMAc" , "iMac" , AppConstants.IMAC_IMAGES_COUNT},			
			};

	}
	
	@Test(dataProvider = "getProductInfoData")
	public void ProductImagesCountTest(String searchKey, String productName, int imagesCount) {
		searchResultsPage = accPage.performSearch(searchKey);
		prodInformationPage = searchResultsPage.selectProduct(productName);
		int actprodImages = prodInformationPage.getProductImagesCount();
		System.out.println("actual product images : " +actprodImages);
		Assert.assertEquals(actprodImages, imagesCount);
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	
	@Test
	public void productMetaDataTest() {
		searchResultsPage = accPage.performSearch("Macbook");
		prodInformationPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actMetaDataMap = prodInformationPage.getProductMetaData();
	Assert.assertEquals(actMetaDataMap.get("Brand") , " Apple");
	Assert.assertEquals(actMetaDataMap.get("Product Code") , "Product 18");
	Assert.assertEquals(actMetaDataMap.get("Reward Points") , "800");
	Assert.assertEquals(actMetaDataMap.get("Availability") , "In Stock");
	}
}
