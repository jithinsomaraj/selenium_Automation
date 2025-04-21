package AttendExam;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import login.loginPage;
import projectBase.Base;
import questionBankList.QuestionBankListPage;
import utils.ReadExcel;
import AttendExam.AttendExamPage;
import ExamScheduler.ExamSchedulePage;
import extentReports.ExtentTestManager;

public class AttendExamCases {
	WebDriver driver;
	loginPage loginpage;
	AttendExamPage AttendExamPage;

	QuestionBankListPage QuestionBankListPage;
	WebDriverWait wait;

	@BeforeClass
	@Parameters({ "studentUserName", "studentPassword" })
	public void openbrowserandLogin(String email, String password) throws InterruptedException {
		driver = Base.getDriver();
		loginpage = new loginPage(driver);
		AttendExamPage = new AttendExamPage(driver);
		QuestionBankListPage = new QuestionBankListPage(driver);
		wait = new WebDriverWait(driver, 15);
		Base.loadstudentUrl(driver);
		loginpage.login(email, password);
		wait.until(ExpectedConditions.visibilityOfElementLocated(AttendExamPage.PageTitleBy()));

	}

	@AfterClass
	public void closeBrowser() {
		QuestionBankListPage.logout();
		driver.quit();
	}

	

	@Test(dataProvider = "First_Question")
	public void AttendExam(String TCID, String TCDescription, String Exam, String Image, String ExpAns, String ExpMulAns1,
			String ExpSingleAns, String Passmsg, String ExpMyscore, String ExpTotalscore, String ExpcorrectAnswers,
			String ExpTotalQuestions) throws AWTException, InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		Thread.sleep(1000);
		AttendExamPage.AttendExam(Exam);

		Thread.sleep(3000);

		do{  
			Thread.sleep(5000); 
			}while(driver.getPageSource().contains("試験開始まであと"));
		Thread.sleep(3000);

		AttendExamPage.clickStartButton();

		wait.until(ExpectedConditions.visibilityOfElementLocated(AttendExamPage.RemainingTimeBy()));
		// String actRTtextw = AttendExamPage.getRemainingTime();
		// Assert.assertEquals(actRTtextw.trim(), ExpRTtext2, "Unable to start Exam");

		System.out.println("test1");
		wait.until(ExpectedConditions.visibilityOfElementLocated(AttendExamPage.FinishButtonBy()));
		Thread.sleep(3000);
		do {

			if (AttendExamPage.isElementPresent()) {
				Assert.assertTrue(AttendExamPage.isElementPresent(), "unable to attend Upload type question");
				System.out.println("The is a image type question");
				AttendExamPage.clickUploadButton();
				AttendExamPage.UploadImage(Image);
				Assert.assertTrue(AttendExamPage.CancelbuttonPresent(), "Cancel button is not present in image type");
				Assert.assertTrue(AttendExamPage.finishbuttonPresent(), "finish button is not present in image type");
				Thread.sleep(2000);
			} else if (AttendExamPage.isElementPresent2()) {
				Assert.assertTrue(AttendExamPage.isElementPresent2(), "unable to attend Answer type question");
				System.out.println("The is Answer type question");
				Thread.sleep(2000);
				AttendExamPage.AnswerType(ExpAns);
			}

			else if (AttendExamPage.isElementPresent3()) {
				System.out.println("CHECKBOX");
				Assert.assertTrue(AttendExamPage.isElementPresent3(), "unable to attend Multiple type question");
				System.out.println("This is a Multiple  type question ");

				AttendExamPage.MultipleType(ExpMulAns1);
			} else if (AttendExamPage.isElementPresent4()) {

				Assert.assertTrue(AttendExamPage.isElementPresent4(), "unable to attend Multiple type question");
				System.out.println("This is a Single image type question ");
				AttendExamPage.SingleType(ExpSingleAns);
			}

			Thread.sleep(5000);
			AttendExamPage.clickNextButton();
			Thread.sleep(1000);
			System.out.println("answer");
		} while (AttendExamPage.getNextButtonTry());

		System.out.println("Out of loop");
		AttendExamPage.clickFinishButton();
		AttendExamPage.clickfinishconfirmButton();
		Thread.sleep(3000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(AttendExamPage.examResultsBy()));
		String actPassmessage = AttendExamPage.getPassmessage();
		String actMyscore = AttendExamPage.getmyscore();

		System.out.println("total score ok");
		String actTotalscore = AttendExamPage.getTotalscore();

		String actcorrectAnswers = AttendExamPage.getcorrectAnswers();

		String actTotalQuestions = AttendExamPage.getTotalQuestions();

		AttendExamPage.clickEndButton();
		Assert.assertEquals(actPassmessage.trim(), Passmsg, "Unable to get pass Message");
		Assert.assertEquals(actMyscore.trim(), ExpMyscore, "Unable to get My score");
		Assert.assertEquals(actTotalscore.trim(), ExpTotalscore, "Unable to get Actual score");
		Assert.assertEquals(actcorrectAnswers.trim(), ExpcorrectAnswers, "Unable to get Answers");
		Assert.assertEquals(actTotalQuestions.trim(), ExpTotalQuestions, "Unable to get Total Questions");
	}

//---------------------- DATAPROVIDERS-------------------

	@DataProvider(name = "Verify_FirstPage_by_RT")
	public Object[][] RemainingTimeText() {
		Object[][] validRTT = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "ExamStart", 3);

		return validRTT;
	}

	@DataProvider(name = "First_Question")
	public Object[][] OriginalAnswer() {
		Object[][] validAns = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "AttendExam", 12);
		return validAns;
	}

}
