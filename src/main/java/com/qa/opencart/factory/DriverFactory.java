package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exception.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	
	//create thread local instance:
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();		
	public static String highlight;
	public OptionsManager optionsManager;
	
	
/*
 * this method is used to initialize the driver on basis of browser name
 * @param browser
 * @return this will return the driver instance
 */
	
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser").toLowerCase();
		System.out.println("browser name is:" +browserName);	
		
		highlight = prop.getProperty("highlight").trim();
		optionsManager = new OptionsManager(prop);
		
		
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver =  new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equals("firefox")){
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
		}
		else if(browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());	
		}
		else {
			System.out.println("Please pass right browser name");
			throw new FrameworkException(AppError.BROWSER_NOT_FOUND);
		}
		
		//driver.manage().deleteAllCookies();
		//driver.manage().window().maximize();
		//driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		//driver.get(prop.getProperty("url"));
	
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	/* this method is used to initialize the config properties
	 * @return
	 */
	
	public Properties initProperties() {
		prop = new Properties();
		FileInputStream ip = null;
		
		//we have to pass these maven commands on mvn to execute multi-environment execution
		
		//mvn clean install -Denv="dev"
		//mvn clean install    --if no environment is given, hence running on QA by default
		
		String envName = System.getenv("env"); //uat,qa,dev,stage,prod
		System.out.println("------>Running test cases on environment: ----->" +envName);
		
		if(envName == null) {
			System.out.println("No env is given.. hence running it on QA env..");
		
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
			
			else {
				try {
				switch (envName) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/prod.config.properties");
					break;
				
				default:
					System.out.println("please pass the right env name..");
					throw new FrameworkException(AppError.ENV_NOT_FOUND);
					//break;
				}
				}
				catch(FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			
				try {
					prop.load(ip);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return prop;
	
	}


		
		
//		try {
//			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
//			prop.load(ip);
//		} catch (FileNotFoundException e) {
//
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
}
	
	

