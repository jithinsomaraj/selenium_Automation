package extentReports;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import projectBase.Base;

public class TestListener implements ITestListener {

	public void onStart(ITestContext iTestContext) {
		iTestContext.getName();
	}

	public void onFinish(ITestContext context) {
		ExtentTestManager.endTest();
		ExtentManager.getReporter().flush();
	}

	public void onTestStart(ITestResult result) {
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().log(Status.PASS, MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {
		WebDriver webDriver = Base.getDriver();
		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
		
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed, Error Log :-  " + result.getThrowable());
		ExtentTestManager.getTest().log(Status.INFO, "Error Screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
	}

	public void onTestSkipped(ITestResult result) {
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped, Log :- " + result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}
}
