package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class AddCartPage {

	String name = "iMac";
	
	By locator = By.id("prodcut");
	 
	public void click() {
		System.out.println("click---" +locator);
	}
	
}
