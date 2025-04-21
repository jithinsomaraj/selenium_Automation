package studentDashboard;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import extentReports.ExtentTestManager;
import login.loginPage;
import projectBase.Base;
import questionBankList.QuestionBankListPage;
import utils.ReadExcel;

public class studentDashboardcases {
	WebDriver driver;
	loginPage loginpage;
	StudentDashboardPage StudentDashboardPage;
	QuestionBankListPage QuestionBankListPage;
	WebDriverWait wait;

	@BeforeClass
	@Parameters({ "studentUserName", "studentPassword" })
	public void openbrowserandLogin(String email, String password) {
		driver = Base.getDriver();
		loginpage = new loginPage(driver);
		StudentDashboardPage = new StudentDashboardPage(driver);
		QuestionBankListPage = new QuestionBankListPage(driver);
		wait = new WebDriverWait(driver, 15);
		Base.loadstudentUrl(driver);
		loginpage.login(email, password);
		wait.until(ExpectedConditions.visibilityOfElementLocated(StudentDashboardPage.PageTitleBy()));
		System.out.println("Reached here");
	}

	@AfterClass
	public void closeBrowser() {
		 QuestionBankListPage.logout();
		 //driver.quit();
	}

	@Test(dataProvider = "valid-Access") // TC_01
	public void DashboardPage(String TCID, String TCDescription, String expWelcomeText) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		String actTodayText = StudentDashboardPage.getTodaysexam();
		Assert.assertEquals(actTodayText.trim(), expWelcomeText,
				"Welcome text does not match user unable to acces deashboard");
		System.out.println("test1");

	}

	@Test(dataProvider = "Verify_elements") // TC_02
	public void verifyDashboard(String TCID, String TCDescription, String element1, String element2) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		String actTodayText = StudentDashboardPage.getTodaysexam();
		String actupcomText = StudentDashboardPage.getupcomingexam();
		Assert.assertEquals(actTodayText.trim(), element1, "Today exam text does not match");
		Assert.assertEquals(actupcomText.trim(), element2, "Upcoming text does not match");
		System.out.println("test2");
	}

	@Test(dataProvider = "My_exams") // TC_03,TC_04
	public void verifyexam(String TCID, String TCDescription, String MyExam, String startdate, String EndDate,
			String TimeDur) throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		wait.until(ExpectedConditions.visibilityOfElementLocated(StudentDashboardPage.exam1By()));
		Assert.assertTrue(StudentDashboardPage.isExamPresent(MyExam));
		String actstartdate = StudentDashboardPage.getstartdate();
		String actenddate = StudentDashboardPage.getenddate();
		String actdur = StudentDashboardPage.gettimeduration();
		Assert.assertEquals(actstartdate.trim(), startdate, "Start date does not match");
		Assert.assertEquals(actenddate.trim(), EndDate, "End date does not match");
		Assert.assertEquals(actdur.trim(), TimeDur, "Time does not match");
		System.out.println("test4");
	}

	@Test(dataProvider = "ExamRegistration") // TC_9,10 //work only if the exam is unregistered
	public void ExamRegistration(String TCID, String TCDescription, String Exam, String AttendBtnText)
			throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(@class,'MuiPaper-root MuiTableContainer-root')]")));
		StudentDashboardPage.RegisterExam(Exam);
		Thread.sleep(1000);
		Assert.assertEquals((StudentDashboardPage.getBttntext()), "参加", "Exam not registered");
	}

	@Test(dataProvider = "UpcomingExam_Elements")
	public void upcomingExams(String TCID, String TCDescription, String Exam, String title, String duration,
			String start, String end, String apply, String showall) throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(StudentDashboardPage.upcomingexamBy()));

		StudentDashboardPage.upcomingtitlenBy();
		StudentDashboardPage.upcomingdurtnBy();
		StudentDashboardPage.upcomingstartBy();
		StudentDashboardPage.upcomingendBy();
		StudentDashboardPage.upcomingapplyBy();
		StudentDashboardPage.upcomingshowallBy();
		String acttitle = StudentDashboardPage.getupcomingtitle();
		Assert.assertEquals(acttitle.trim(), title, "Title is not Displayed");
		String actdurn = StudentDashboardPage.getupcomingdurtn();
		Assert.assertEquals(actdurn.trim(), duration, "Duration is not Displayed");
		String actstart = StudentDashboardPage.getupcomingstart();
		Assert.assertEquals(actstart.trim(), start, "Start is not Displayed");
		String actend = StudentDashboardPage.getupcomingend();
		Assert.assertEquals(actend.trim(), end, "End is not Displayed");
		String actapply = StudentDashboardPage.getupcomingapply();
		Assert.assertEquals(actapply.trim(), apply, "Apply is not Displayed");
		Thread.sleep(2000);
		StudentDashboardPage.ApplyExam(Exam);
		wait.until(ExpectedConditions.visibilityOfElementLocated(StudentDashboardPage.upcomingexamBy()));
		Assert.assertTrue(StudentDashboardPage.istickpresent(), "tick button is not present");
		System.out.println("test8");

	}

//---------------------- DATAPROVIDERS-------------------
	@DataProvider(name = "valid-Access")
	public Object[][] validStudentUsers() {
		Object[][] validStudentUsers = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "DashboardPage", 3);
		return validStudentUsers;
	}

	@DataProvider(name = "Verify_elements")
	public Object[][] validStudentUsers1() {
		Object[][] validStudentUsers1 = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "verifyDashboard", 4);
		return validStudentUsers1;
	}

	@DataProvider(name = "My_exams")
	public Object[][] myexams() {
		Object[][] validexams = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "verifyexam", 6);
		return validexams;
	}

	@DataProvider(name = "Unregistered_exams")
	public Object[][] unregisteredexam() {
		Object[][] unregisterexam = { { "This is an exam for jithin-2" } };
		return unregisterexam;
	}

	@DataProvider(name = "start&enddate")
	public Object[][] startnEnddate() {
		Object[][] startenddates = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "startandenddate", 5);
		return startenddates;
	}

	@DataProvider(name = "ExamRegistration")
	public Object[][] RegButton() {
		Object[][] rebtn = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "ExamRegistration", 4);
		return rebtn;
	}

	@DataProvider(name = "confirmn_reg")
	public Object[][] attButton() {
		Object[][] attnbtn = { { "å�‚åŠ  " } };
		return attnbtn;
	}

	@DataProvider(name = "Time_Duration")
	public Object[][] TimeDurtn() {
		Object[][] durtn = { { "03:00:00" } };
		return durtn;
	}

	@DataProvider(name = "Pagination")
	public Object[][] count() {
		Object[][] pcount = { { "1-5 of 6" } };
		return pcount;
	}

	@DataProvider(name = "Exam_details")
	public Object[][] detail() {
		Object[][] alldetails = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "pageconfirm", 6);
		return alldetails;
	}

	@DataProvider(name = "UpcomingExam_Elements")
	public Object[][] ExamElements() {
		Object[][] elements = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "upcomingExams", 9);
		return elements;
	}

	@DataProvider(name = "UpcomingExam_Applybutton_presence")
	public Object[][] applbtton() {
		Object[][] Apply = { { "Apply" } }; // crsscheck with japanese
		return Apply;
	}

	@DataProvider(name = "Show_content")
	public Object[][] showoption() {
		Object[][] shw = { { "1-5 of 6" } }; // crsscheck with japanese
		return shw;

	}
}
