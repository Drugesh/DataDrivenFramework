package com.dj.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dj.base.TestBase;
import com.dj.utilities.ExcelUtility;

public class AddCustomerTest extends TestBase{
	
	public void navigateToHome(){
		driver.get(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("timeout")), TimeUnit.SECONDS);
		log.debug("Navigate to Home Page Successfully");
		Reporter.log("Navigate to Home Page Successfully");
		
	}
	
	@Test(dataProvider="customerDetails")
	public void addCustomer(String firstName, String lastName,String pincode,String alertText) throws InterruptedException{
		navigateToHome();
		
		click("bmlbtn_CSS");
		click("addCustomer_CSS");
		type("firstName_CSS", firstName);
		type("lastName_CSS", lastName);
		type("pincode_CSS", pincode);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(OR.getProperty("addCustomerbutton_CSS"))).click();
		Thread.sleep(3000);
		WebDriverWait wait=new WebDriverWait(driver, 10);
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		String text=alert.getText();
		Assert.assertTrue(text.contains(alertText));
		alert.accept();
		
	}
	
	@DataProvider(name="customerDetails")
	public Object[][] customerData(){
		
		ExcelUtility excelUtility=new ExcelUtility(System.getProperty("user.dir")+config.getProperty("testDataPath"));
		int rowCount=excelUtility.getRowCount(this.getClass().getSimpleName());
		int cellCount=excelUtility.getCellCount(this.getClass().getSimpleName());
		Object[][] data= new Object[rowCount-1][cellCount];
 		for(int i=1;i<rowCount;i++){
 			for(int j=0;j<cellCount;j++){
 				data[i-1][j]=excelUtility.getCellData(this.getClass().getSimpleName(),i,j);
 			}
 			
 		}
		
		return data;
		
	}
	

}
