package com.dj.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.dj.utilities.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static WebDriver driver;
	public static FileInputStream fis;
	public static FileInputStream fis1;
	public static Properties config=new Properties();
	public static Properties OR=new Properties();
	public ExtentReports report=ExtentManager.getInstance();
	public static ExtentTest test;
	
	@BeforeSuite
	public void initializeDriver(){
		try {
			fis=new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//Config.properties");
		
		fis1=new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//OR.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			config.load(fis);
			log.debug("Loaded Config File");
			OR.load(fis1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String browser=config.getProperty("browser");
		
		switch(browser){
		case "chrome":
		   System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//test//resources//executibles//chromedriver");
			driver=new ChromeDriver();
			log.debug("Initialized Chrome Browser");
			break;
		case "firefox":
			driver=new FirefoxDriver();
			break;
		case "ie": 
			driver=new InternetExplorerDriver();
			break;
			
		default:
			System.out.println("Invalid browserType");
			
		}		
			
		}
		
		
		public boolean isElementPresent(By by){
			try{
				driver.findElement(by);
				return true;
			}catch(NoSuchElementException e){
				return false;
			}
		
		
	}
	@AfterSuite
	public void tearDown(){
		if(driver!=null){
			driver.quit();
			log.debug("Test Complete");
			log.debug("Closing dirver");
		}
	}
	
	public WebElement getElement(String locator){
		WebElement element;
		if(locator.endsWith("_CSS")){
			element=driver.findElement(By.cssSelector(OR.getProperty(locator)));
			
		}else if(locator.endsWith("_XPath")){
			element=driver.findElement(By.xpath(OR.getProperty(locator)));
		}
		else{
			element=driver.findElement(By.id(OR.getProperty(locator)));
		}
		return element;
	}
	
	public void click(String locator ){
		test.log(LogStatus.INFO,"Clicking on "+locator);
		getElement(locator).click();
		
		
	}
	public void type(String locator,String text){
		test.log(LogStatus.INFO, "Typing text: "+text+" to " +locator);
		getElement(locator).sendKeys(text);
		
		
		
	}

}
