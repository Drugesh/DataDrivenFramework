package com.dj.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.dj.base.TestBase;

public class TestUtil extends TestBase{
	public static String screenShotpath="";
	static String screenshotName="";
	public  static void captureScreenShot(){
		File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			Date date=new Date();
			screenshotName=date.toString().replace(":", "_").replace(" ", "_");
			screenShotpath=System.getProperty("user.dir")+"/target/surefire-reports/html/"+screenshotName+".jpg";
			FileUtils.copyFile(file, new File(screenShotpath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
