package ExamScheduler;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

import AttendExam.AttendExamPage;
import ExamHistory.ExamHistoryPage;
import login.loginPage;
import projectBase.Base;
import questionBankList.QuestionBankListPage;
import utils.ReadExcel;
import ExamScheduler.ExamSchedulePage;
import extentReports.ExtentTestManager;

public class ExamScheduleCases {

	WebDriver driver;
	loginPage loginpage;
	ExamSchedulePage ExamSchedulePage;
	QuestionBankListPage QuestionBankListPage;
	WebDriverWait wait;

	@BeforeClass
	@Parameters({ "adminUserName", "adminPassword" })
	public void openbrowserandLogin(String email, String password) {
		driver = Base.getDriver();
		loginpage = new loginPage(driver);
		ExamSchedulePage = new ExamSchedulePage(driver);
		QuestionBankListPage = new QuestionBankListPage(driver);
		wait = new WebDriverWait(driver, 15);
		Base.loadadminUrl(driver);
		loginpage.login(email, password);
		wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionBankListPage.getQuestionBankListTableBy()));
		ExamSchedulePage.clickSchedlueButton();
		ExamSchedulePage.clickSchedlueButton();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamSchedulePage.ExamSchedlueListBy()));
	}

	@AfterClass
	public void closeBrowser() {
		 QuestionBankListPage.logout();
		 //driver.quit();
	}

	@Test(dataProvider = "CreateSchedule", priority = 0)
	public void createSchedule(String TCID, String TCDescription, String Exam, String ExpStartMonth, String ExpStartDay,
			String ExpStartTimeHH, String ExpStartMM, String ExpAMPM, String ExpEndMonth, String ExpEndDay,
			String ExpEndTimeHH, String ExpEndMM, String ExpAmPm1, String ExpExam) throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		ExamSchedulePage.clickCreateScheduleButton();
		Thread.sleep(5000);
		ExamSchedulePage.clickDropDown();
		ExamSchedulePage.SelectDropDownValue(Exam);

		ExamSchedulePage.clickStartcalenderButton();
		String StartMonth = ExpStartMonth;
		while (true) {
			ExamSchedulePage.getCalenderMonth();
			
			if (StartMonth.equals(ExamSchedulePage.getCalenderMonth())) {

				break;

			} else {
				ExamSchedulePage.clickRightArrowButton();
				Thread.sleep(1500);
			}
		}

		ExamSchedulePage.clickStartDay(ExpStartDay);
		ExamSchedulePage.clickOK();
		ExamSchedulePage.clickStartTime();
		ExamSchedulePage.selectStartHour(ExpStartTimeHH);

		ExamSchedulePage.selectStartMinute(ExpStartMM);
		if (ExpAMPM.equals("am")) {
			ExamSchedulePage.clickAM();
		} else {
			ExamSchedulePage.clickPM();
		}

		ExamSchedulePage.clickOK();
		ExamSchedulePage.clickEndCalenderButton();
		String EndMonth = ExpEndMonth;
		while (true) {
			ExamSchedulePage.getCalenderMonth();
			if (EndMonth.equals(ExamSchedulePage.getCalenderMonth()))

			{

				break;

			} else {
				ExamSchedulePage.clickRightArrowButton();
				Thread.sleep(1500);
			}
		}
		ExamSchedulePage.clickEndDay(ExpEndDay);
		ExamSchedulePage.clickOK();
		ExamSchedulePage.clickEndTime();
		ExamSchedulePage.selectEndHour(ExpEndTimeHH);
		ExamSchedulePage.selectEndMinute(ExpEndMM);
		if (ExpAmPm1.equals("am")) {
			ExamSchedulePage.clickAM();
		} else {
			ExamSchedulePage.clickPM();
		}
		ExamSchedulePage.clickOK();
		ExamSchedulePage.clickCreateScheduleBtn();
		String alert = QuestionBankListPage.getReactAlertMessage();
		Assert.assertEquals(alert, "試験を正常にスケジュールしました", "Alert message does not matchSCHEDULE NOT CREATED properly");

	}

	@Test(dataProvider = "CreateScheduleValidation", priority = 1)
	public void createScheduleValidations(String TCID, String TCDescription, String Exam, String ExpStartMonth,
			String ExpStartDay, String ExpStartTimeHH, String ExpStartMM, String ExpAMPM, String ExpEndMonth,
			String ExpEndDay, String ExpEndTimeHH, String ExpEndMM, String ExpAmPm1, String ExpExam)
			throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamSchedulePage.ExamSchedlueListBy()));
		ExamSchedulePage.clickCreateScheduleButton();
		Thread.sleep(5000);
		ExamSchedulePage.clickDropDown();
		ExamSchedulePage.SelectDropDownValue(Exam);

		ExamSchedulePage.clickStartcalenderButton();
		String StartMonth = ExpStartMonth;
		while (true) {
			ExamSchedulePage.getCalenderMonth();
			
			if (StartMonth.equals(ExamSchedulePage.getCalenderMonth())) {

				break;

			} else {
				ExamSchedulePage.clickRightArrowButton();
				Thread.sleep(1500);
			}
		}

		ExamSchedulePage.clickStartDay(ExpStartDay);
		ExamSchedulePage.clickOK();
		ExamSchedulePage.clickStartTime();
		ExamSchedulePage.selectStartHour(ExpStartTimeHH);
		ExamSchedulePage.selectStartMinute(ExpStartMM);
		if (ExpAMPM.equals("am")) {
			ExamSchedulePage.clickAM();
		} else {
			ExamSchedulePage.clickPM();
		}

		ExamSchedulePage.clickOK();
		ExamSchedulePage.clickEndCalenderButton();
		String EndMonth = ExpEndMonth;
		while (true) {
			ExamSchedulePage.getCalenderMonth();
			if (EndMonth.equals(ExamSchedulePage.getCalenderMonth()))

			{

				break;

			} else {
				ExamSchedulePage.clickRightArrowButton();
				Thread.sleep(1500);
			}
		}
		ExamSchedulePage.clickEndDay(ExpEndDay);
		ExamSchedulePage.clickOK();
		ExamSchedulePage.clickEndTime();
		ExamSchedulePage.selectEndHour(ExpEndTimeHH);
		ExamSchedulePage.selectEndMinute(ExpEndMM);
		if (ExpAmPm1.equals("am")) {
			ExamSchedulePage.clickAM();
		} else {
			ExamSchedulePage.clickPM();
		}

		ExamSchedulePage.clickOK();
		ExamSchedulePage.clickCreateScheduleBtn();
		if (ExamSchedulePage.isendTimeGreaterPresent()) {
			String ActEndmessage = ExamSchedulePage.getendDateGreater();
			Assert.assertEquals(ActEndmessage.trim(), "終了時刻は開始時刻より後に設定してください", "Time Validation not showing properly");
			driver.navigate().refresh();
		} else {
			String ActTimemessage = ExamSchedulePage.getendTimeGreater();
			Assert.assertEquals(ActTimemessage.trim(), "終了日は開始日より後に設定してください", "End date validation failed");
			driver.navigate().refresh();
		}
	}

	@Test(dataProvider = "EditSchedule", priority = 2)
	public void EditSchedule(String TCID, String TCDescription, String Exam, String ExpStartMonth, String ExpStartDay,
			String ExpStartTimeHH, String ExpStartMM, String ExpAMPM, String ExpEndMonth, String ExpEndDay,
			String ExpEndTimeHH, String ExpEndMM, String ExpAmPm1, String ExpExam) throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamSchedulePage.ExamSchedlueListBy()));
		Thread.sleep(5000);
		ExamSchedulePage.clickExamEditButton(Exam);
		ExamSchedulePage.clickStartcalenderButton();
		String StartMonth = ExpStartMonth;
		while (true) {
			ExamSchedulePage.getCalenderMonth();
			
			if (StartMonth.equals(ExamSchedulePage.getCalenderMonth())) {

				break;

			} else {
				ExamSchedulePage.clickRightArrowButton();
				Thread.sleep(1500);
			}
		}

		ExamSchedulePage.clickStartDay(ExpStartDay);
		ExamSchedulePage.clickOK();
		ExamSchedulePage.clickStartTime();
		ExamSchedulePage.selectStartHour(ExpStartTimeHH);
		ExamSchedulePage.selectStartMinute(ExpStartMM);
		if (ExpAMPM.equals("am")) {
			ExamSchedulePage.clickAM();
		} else {
			ExamSchedulePage.clickPM();
		}

		ExamSchedulePage.clickOK();
		ExamSchedulePage.clickEndCalenderButton();
		String EndMonth = ExpEndMonth;
		while (true) {
			ExamSchedulePage.getCalenderMonth();
			if (EndMonth.equals(ExamSchedulePage.getCalenderMonth()))

			{

				break;

			} else {
				ExamSchedulePage.clickRightArrowButton();
				Thread.sleep(1500);
			}
		}
		ExamSchedulePage.clickEndDay(ExpEndDay);
		ExamSchedulePage.clickOK();
		ExamSchedulePage.clickEndTime();
		ExamSchedulePage.selectEndHour(ExpEndTimeHH);
		ExamSchedulePage.selectEndMinute(ExpEndMM);
		if (ExpAmPm1.equals("am")) {
			ExamSchedulePage.clickAM();
		} else {
			ExamSchedulePage.clickPM();
		}

		ExamSchedulePage.clickOK();
		ExamSchedulePage.clickupdateScheduleButton();
		String alert3 = QuestionBankListPage.getReactAlertMessage();
		Assert.assertEquals(alert3, "スケジュールを正常に更新しました", "Alert message does not match endtime not assigned properly");
		driver.navigate().refresh();
	} 

	@Test(dataProvider = "EditScheduleCases", priority = 3)
	public void EditScheduleCases(String TCID, String TCDescription, String Exam, String ExpStartMonth,
			String ExpStartDay, String ExpStartTimeHH, String ExpStartMM, String ExpAMPM, String ExpEndMonth,
			String ExpEndDay, String ExpEndTimeHH, String ExpEndMM, String ExpAmPm1, String ExpExam)
			throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamSchedulePage.ExamSchedlueListBy()));
		Thread.sleep(5000);
		ExamSchedulePage.clickExamEditButton(Exam);
		ExamSchedulePage.clickStartcalenderButton();
		String StartMonth = ExpStartMonth;
		while (true) {
			ExamSchedulePage.getCalenderMonth();
			
			if (StartMonth.equals(ExamSchedulePage.getCalenderMonth())) {

				break;

			} else {
				ExamSchedulePage.clickRightArrowButton();
				Thread.sleep(1500);
			}
		}

		ExamSchedulePage.clickStartDay(ExpStartDay);
		ExamSchedulePage.clickOK();
		ExamSchedulePage.clickStartTime();
		ExamSchedulePage.selectStartHour(ExpStartTimeHH);
		ExamSchedulePage.selectStartMinute(ExpStartMM);
		if (ExpAMPM.equals("am")) {
			ExamSchedulePage.clickAM();
		} else {
			ExamSchedulePage.clickPM();
		}

		ExamSchedulePage.clickOK();
		ExamSchedulePage.clickEndCalenderButton();
		String EndMonth = ExpEndMonth;
		while (true) {
			ExamSchedulePage.getCalenderMonth();
			if (EndMonth.equals(ExamSchedulePage.getCalenderMonth()))

			{

				break;

			} else {
				ExamSchedulePage.clickRightArrowButton();
				Thread.sleep(1500);
			}
		}
		ExamSchedulePage.clickEndDay(ExpEndDay);
		ExamSchedulePage.clickOK();
		ExamSchedulePage.clickEndTime();
		ExamSchedulePage.selectEndHour(ExpEndTimeHH);
		ExamSchedulePage.selectEndMinute(ExpEndMM);
		if (ExpAmPm1.equals("am")) {
			ExamSchedulePage.clickAM();
		} else {
			ExamSchedulePage.clickPM();
		}

		ExamSchedulePage.clickOK();
		ExamSchedulePage.clickupdateScheduleButton();
		if (ExamSchedulePage.isendTimeGreaterPresent()) {
			String ActEndmessage = ExamSchedulePage.getendDateGreater();
			Assert.assertEquals(ActEndmessage.trim(), "終了時刻は開始時刻より後に設定してください", "Time Validation not showing properly");
			driver.navigate().refresh();
		} else {
			String ActTimemessage = ExamSchedulePage.getendTimeGreater();
			Assert.assertEquals(ActTimemessage.trim(), "終了日は開始日より後に設定してください", "End date validation failed");
			driver.navigate().refresh();
			Thread.sleep(2500);
		}
	}  

	@Test(dataProvider = "CsvUpload", priority = 4)
	public void CsvUpload(String TCID, String TCDescription, String Exam, String CSV) throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamSchedulePage.ExamSchedlueListBy()));
		Thread.sleep(3000);
		ExamSchedulePage.clickCSVuploadButton(Exam);
		ExamSchedulePage.uploadcsvFile(CSV);
		ExamSchedulePage.clickcsvuploadsubmitButtn();
		String alert = QuestionBankListPage.getReactAlertMessage();
		String ValidcsvAlert = "候補者が正常に割り当てられました";
		String InValidcsvAlert = "CSV 形式が無効です";

		if (alert.equals(ValidcsvAlert)) {
			Assert.assertEquals(alert, ValidcsvAlert, "Alert message does not match1 candidate not assigned properly");

		} else {
			Assert.assertEquals(alert, InValidcsvAlert, "Alert message does not match candidate not assigned properly");

		}

		driver.navigate().refresh();

	}

	@Test(dataProvider = "Delete_Cases1", priority = 5)
	public void CandidateDelete(String TCID, String TCDescription, String Exam, String CandFirstName)
			throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamSchedulePage.ExamSchedlueListBy()));
		Thread.sleep(1000);
		ExamSchedulePage.clickExamEditButton(Exam);
		Thread.sleep(1500);
		ExamSchedulePage.clickDeleteButton(CandFirstName);

		ExamSchedulePage.clickAssignDeleteConfrmButton();
		String alert3 = QuestionBankListPage.getReactAlertMessage();
		Assert.assertEquals(alert3, "ユーザーは正常に削除されました", "Alert message does not match candidate not assigned properly");
		ExamSchedulePage.clickupdateScheduleButton();
		driver.navigate().refresh();

	}

	@Test(dataProvider = "Delete_Cases2", priority = 7)
	public void ScheduleDelete(String TCID, String TCDescription, String Exam) throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamSchedulePage.ExamSchedlueListBy()));
		ExamSchedulePage.clickScheduleDeleteButton(Exam);
		ExamSchedulePage.clickconfirmDelScheduleButton();

		String alert = QuestionBankListPage.getReactAlertMessage();
		Assert.assertEquals(alert, "スケジュールを正常に削除しました",
				"Alert message does not match candidate not deleted properly1111");
		driver.navigate().refresh();

	}

	@Test(dataProvider = "Valid_candidate_Assign", priority = 6) // add after schedule
	public void ValidAssign(String TCID, String TCDescription, String Exam, String UserEmail)
			throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamSchedulePage.ExamSchedlueListBy()));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamSchedulePage.DtitleBy()));
		ExamSchedulePage.clickAssignButton(Exam);

		ExamSchedulePage.selectUser(UserEmail);
		ExamSchedulePage.clickAssigncandidatesButton();
		String alert = QuestionBankListPage.getReactAlertMessage();
		Assert.assertEquals(alert, "候補者が正常に割り当てられました", "Alert message does not match candidate not assigned properly");
		System.out.println("test1");
	}

	@Test(dataProvider = "Report", priority = 8)
	public void Report(String TCID, String TCDescription, String Exam, String exprCandTotal, String expAorCand,
			String expTotalTime, String TotalQuestn, String candAboavg, String canBelAvg) throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		driver.navigate().refresh();
		Thread.sleep(4000);
		ExamSchedulePage.selectReport(Exam);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamSchedulePage.ExamReportTitleBy()));
		Thread.sleep(8000);
		String ct = ExamSchedulePage.getcandidateTotal();

		String COA = ExamSchedulePage.getCandORApplicant();

		String TT = ExamSchedulePage.getTotalTime();

		String TQ = ExamSchedulePage.getTotalQuestn();

		String CAA = ExamSchedulePage.getcandAbovAverage();

		String CBA = ExamSchedulePage.getcandBelowAverage();

		ExamSchedulePage.clickSchedlueButton();
		ExamSchedulePage.clickSchedlueButton();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamSchedulePage.ExamSchedlueListBy()));
		System.out.println("test4");
		ExamSchedulePage.clickSchedlueButton();
		Assert.assertEquals(ct.trim(), exprCandTotal, "Candidate Total validatn failed");
		Assert.assertEquals(COA.trim(), expAorCand, "Applicant/candidate validatn failed");
		Assert.assertEquals(TT.trim(), expTotalTime, "Total time validatn failed");
		Assert.assertEquals(TQ.trim(), TotalQuestn, "Total question validatn failed");
		Assert.assertEquals(CAA.trim(), candAboavg, "Candidate above average validatn failed");
		Assert.assertEquals(CBA.trim(), canBelAvg, "Candidate above average validatn failed");
		Assert.assertTrue(ExamSchedulePage.isGraph1present(), "Graph1 is not present");
//	Assert.assertTrue(ExamSchedulePage.isGraph2present(), "Graph2 is not present");
//	Assert.assertTrue(ExamSchedulePage.isGraph3present(), "Graph3 is not present");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamSchedulePage.ExamSchedlueListBy()));

	}

	@Test(dataProvider = "Search", priority = 9)
	public void Search(String TCID, String TCDescription, String Exam, String SearchInput) throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		ExamSchedulePage.clickSchedlueButton();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamSchedulePage.ExamSchedlueListBy()));
		ExamSchedulePage.Search(SearchInput);
		ExamSchedulePage.clickSchedlueButton();
		Thread.sleep(4000);
		String ExpResult = Exam;
		String ActResult = ExamSchedulePage.getsearchResult();
		Assert.assertEquals(ExpResult, ActResult, "Search not working properly");
	} 

	// ----------------------------------------------------------------DataProviders-----------------------------------------------------------------------------------------------
	@DataProvider(name = "Valid_candidate_Assign")
	public Object[][] RemainingTimeText() {
		Object[][] validRTT = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "ValidAssign", 4);
		return validRTT;
	}

	@DataProvider(name = "CreateSchedule")
	public Object[][] EndDateValidtions() {
		Object[][] validmsg = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "createSchedule", 14);
		return validmsg;
	}

	@DataProvider(name = "CreateScheduleValidation")
	public Object[][] Validtions() {
		Object[][] validmsg = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "createScheduleValidations", 14);
		return validmsg;
	}

	@DataProvider(name = "EditSchedule")
	public Object[][] EditValidtions() {
		Object[][] validmsg = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "EditSchedule", 14);
		return validmsg;
	}

	@DataProvider(name = "EditScheduleCases")
	public Object[][] EditScheduleValidtions() {
		Object[][] validmsg = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "EditScheduleCases", 14);
		return validmsg;
	}

	@DataProvider(name = "CsvUpload")
	public Object[][] CsvUploadCases() {
		Object[][] validmsg = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "CsvUpload", 4);
		return validmsg;
	}

	@DataProvider(name = "Delete_Cases1")
	public Object[][] Delete() {
		Object[][] validmsg = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "CandidateDelete", 4);
		return validmsg;
	}

	@DataProvider(name = "Delete_Cases2")
	public Object[][] Delete2() {
		Object[][] validmsg = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "ScheduleDelete", 3);
		return validmsg;
	}

	@DataProvider(name = "Report")
	public Object[][] Reportverify() {
		Object[][] validmsg = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "Report", 9);
		return validmsg;
	}

	@DataProvider(name = "Search")
	public Object[][] SearchTest() {
		Object[][] validmsg = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "Search", 4);
		return validmsg;
	}
}
