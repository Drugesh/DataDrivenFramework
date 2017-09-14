package com.dj.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.dj.base.TestBase;

public class LoginTest extends TestBase {
	
	
	
	public void navigateToHome(){
		driver.get(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("timeout")), TimeUnit.SECONDS);
		log.debug("Navigate to Home Page Successfully");
		Reporter.log("Navigate to Home Page Successfully");
		
	}

	@Test
	public void login(){
		navigateToHome();
		click("bmlbtn");
		Assert.assertFalse(isElementPresent(By.cssSelector(OR.getProperty("addCustomer"))));
		
		
		
	
	}
	@Test
	public void loginInvalid(){
		navigateToHome();
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustomer"))));
		
		
		
	
	}
	
	
}
