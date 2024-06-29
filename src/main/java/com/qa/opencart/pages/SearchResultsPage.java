package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productSearchLayout = By.cssSelector("div.product-layout");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);		
	}

	public boolean isSearchSuccessfull() {
	List<WebElement> searchList = 	eleUtil.waitForElementsToBeVisible(productSearchLayout, AppConstants.DEFAULT_TIMEOUT);
		if (searchList.size()>0) {
			System.out.println("search is successfully done");
			return true;
		}
			return false;
	}
	
	public ProdInformationPage selectProduct(String mainProductName) {
		
		By mainProdlinkName = By.linkText(mainProductName);		
		eleUtil.doClick(mainProdlinkName);
		return new ProdInformationPage(driver);
	}
	
	
}
