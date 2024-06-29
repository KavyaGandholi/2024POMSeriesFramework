package com.qa.opencart.pages;

import java.lang.invoke.LambdaConversionException;
import java.lang.invoke.LambdaMetafactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class ProdInformationPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String,String> productInfoMap;
	
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	
	public ProdInformationPage(WebDriver driver) {
		this.driver= driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeader(String mainProductName) {
		String xpath = "//h1[text()='"+mainProductName+"']";
		String productHeader = eleUtil.doGetText(By.xpath(xpath));
		System.out.println("product header is : " +productHeader);
		return productHeader;
	}
	
	public int getProductImagesCount() {
		return eleUtil.waitForElementsToBeVisible(productImages, AppConstants.DEFAULT_TIMEOUT).size();
	}
	
	public String getProductPageTitle(String productTitle) {
		return eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIMEOUT, productTitle);
	}
	
	public String getProductPageUrl(String searchKey) {
		return eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIMEOUT, searchKey);
	}
	
	
	
	//to enter this data:
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	
		public Map<String, String> getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		productInfoMap = new HashMap<String,String>();
		
		for(WebElement e : metaList) {
			String metaText = e.getText();
			String meta[] = metaText.split(":"); //here we are splitting on the basis of ":"
			String metaKey = meta[0].trim(); //if any spaces are available at Brand or Apple make trim()
			String metaValue = meta[1].trim();	
			productInfoMap.put(metaKey, metaValue);
		}
		//to print in random order of details of metadata on product page
		productInfoMap.forEach((k,v) -> System.out.println(k+ ":" +v));
	return productInfoMap;
	
	}
	
}
