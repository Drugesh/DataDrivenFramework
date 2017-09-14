package com.dj.listners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.dj.base.TestBase;
import com.dj.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListners extends TestBase implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(LogStatus.PASS, result.getName().toUpperCase()+"Pass");
		report.endTest(test);
		report.flush();
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("Capturing Screenshot");
		TestUtil.captureScreenShot();
		test.log(LogStatus.FAIL, "Failed with exception" +result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenShotpath));
		report.endTest(test);
		report.flush();

		
//		Reporter.log(TestUtil.screenShotpath);
//		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenShotpath+">Screenshot</a>");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		test=report.startTest(context.getName().toUpperCase());
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
