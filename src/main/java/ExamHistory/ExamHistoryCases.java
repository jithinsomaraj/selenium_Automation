package ExamHistory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
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

import login.loginPage;
import projectBase.Base;
import questionBankList.QuestionBankListPage;
import utils.ReadExcel;
import ExamHistory.ExamHistoryPage;
import extentReports.ExtentTestManager;

public class ExamHistoryCases {

	WebDriver driver;
	loginPage loginpage;
	ExamHistoryPage ExamHistoryPage;
	QuestionBankListPage QuestionBankListPage;
	WebDriverWait wait;

	@BeforeClass
	@Parameters({ "studentUserName", "studentPassword" })
	public void openbrowserandLogin(String email, String password) {
		driver = Base.getDriver();
		loginpage = new loginPage(driver);
		QuestionBankListPage = new QuestionBankListPage(driver);
		ExamHistoryPage = new ExamHistoryPage(driver);
		wait = new WebDriverWait(driver, 15);
		Base.loadstudentUrl(driver);
		loginpage.login(email, password);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamHistoryPage.HomePageTitleBy()));
		ExamHistoryPage.clickhistorybutton();
	}

	@AfterClass
	public void closeBrowser() {
		QuestionBankListPage.logout();
		//driver.quit();
	}

	@Test(dataProvider = "History_Access")
	public void Historypage(String TCID, String TCDescription, String Exptitle) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamHistoryPage.pageTitleBy()));
		String acttitle = ExamHistoryPage.getpageTitle();
		Assert.assertEquals(acttitle.trim(), Exptitle, "Exam history is not accessible");
		System.out.println("test1");

	}

	@Test(dataProvider = "Verify_elements")
	public void Verifyelements(String TCID, String TCDescription, String Expectitle, String Exppoint,
			String Expschedule, String Expresult) throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription); //
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamHistoryPage.pageTitleBy()));
		String actutitle = ExamHistoryPage.getTitle();
		Assert.assertEquals(actutitle.trim(), Expectitle, "Title is not displayed");
		String actpoint = ExamHistoryPage.getPoint();
		Assert.assertEquals(actpoint.trim(), Exppoint, "Point is not displayed");
		String actschedle = ExamHistoryPage.getschedule();
		Assert.assertEquals(actschedle.trim(), Expschedule, "Schedule is not displayed");
		String actreslt = ExamHistoryPage.getresult();
		Assert.assertEquals(actreslt.trim(), Expresult, "Schedule is not displayed");
		Thread.sleep(8000);
		System.out.println("test2");
	}

	@Test(dataProvider = "Results_page")
	public void examResult(String TCID, String TCDescription, String ExpPagetitle, String Exam)
			throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		ExamHistoryPage.clickResultbutton(Exam);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamHistoryPage.TestResultspagetBy()));
		String actpagetxt = ExamHistoryPage.getTestResultspages();
		Assert.assertEquals(actpagetxt.trim(), ExpPagetitle, "Navigation to Results page is failed");
		System.out.println("test4");
		driver.navigate().refresh();
		Thread.sleep(2000);
		

	}

	@Test(dataProvider = "Results_page_Elements")
	public void examResultspageverify(String TCID, String TCDescription, String Exam, String ExamDetails,
			String ExpectdMark1, String ExpAnswerofAnswerType, String ExpectdMark2, String ExpectdMark3,
			String ExpectdMark4, String ExpectdMark5, String ExpectdMark6, String TotalExamScore, String ExpGreenColor,
			String ExpRedcolor) throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		ExamHistoryPage.clickResultbutton(Exam);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamHistoryPage.TestResultspagetBy()));
		Thread.sleep(5000);
		String actexmname = ExamHistoryPage.getExaminationName();
		actexmname = actexmname.replace("\n", "");
		System.out.println(actexmname);
		System.out.println(ExamDetails);
		String ActDetails = ExamHistoryPage.ExamDetails();
		Assert.assertEquals(ActDetails, ExamDetails, "Examination details are not correctly displayed");
		Assert.assertTrue(ExamHistoryPage.isImagePresent(), "Image is not uploaded properly");
		String Mark1 = ExamHistoryPage.getUploadtypeScore();
		Assert.assertEquals(Mark1, ExpectdMark1, "Fisrt Question score are not correctly displayed");
		String ActAnswer = ExamHistoryPage.getAnswerofAnswerType();
		Assert.assertEquals(ActAnswer, ExpAnswerofAnswerType,
				"Answer type  Question Answer is not correctly displayed");
		String Mark2 = ExamHistoryPage.getAnswerTypeScore();
		Assert.assertEquals(Mark2, ExpectdMark2, "Second Question score are not correctly displayed");
		String MulImageAnswer1 = Color.fromString(ExamHistoryPage.getBackgroundColorGreen1()).asHex();
		System.out.println(MulImageAnswer1);
		Assert.assertEquals(MulImageAnswer1, ExpGreenColor,
				"Answer type fisrt Question Answer is not correctly colored green");
		String MulImageAnswer2 = Color.fromString(ExamHistoryPage.getBackgroundColorGreen()).asHex();
		System.out.println(MulImageAnswer2);
		Assert.assertEquals(MulImageAnswer2, ExpGreenColor,
				"Answer type  Question Second Answer is not correctly colored green");
		String Mark3 = ExamHistoryPage.getMulImageTypeScore();
		Assert.assertEquals(Mark3, ExpectdMark3, "Third Question score are not correctly displayed");
		String MulAnswer1 = Color.fromString(ExamHistoryPage.getBackgroundColorGreen2()).asHex();
		Assert.assertEquals(MulAnswer1, ExpGreenColor,
				"Multiple type  Question firts Answer is not correctly colored green");
		String MulAnswer2 = Color.fromString(ExamHistoryPage.getBackgroundColorGreen3()).asHex();
		Assert.assertEquals(MulAnswer2, ExpGreenColor,
				"Multiple type  Question firts Answer is not correctly colored green");
		String Mark4 = ExamHistoryPage.getMulTypeScore();
		Assert.assertEquals(Mark4, ExpectdMark4, "Fourth Question score are not correctly displayed");
		String SingleAnswer1 = Color.fromString(ExamHistoryPage.getBackgroundColorGreen4()).asHex();
		Assert.assertEquals(SingleAnswer1, ExpGreenColor,
				"Single type  Question firts Answer is not correctly colored green");
		String SingleAnswer2 = Color.fromString(ExamHistoryPage.getBackgroundColorRed()).asHex();
		Assert.assertEquals(SingleAnswer2, ExpRedcolor,
				"Single type  Question firts Answer is not correctly colored Red");
		String Mark5 = ExamHistoryPage.getSingleImgScore();
		Assert.assertEquals(Mark5, ExpectdMark5, "Fifth Question score are not correctly displayed");
		String SingleAnswer3 = Color.fromString(ExamHistoryPage.getBackgroundColorGreen5()).asHex();
		Assert.assertEquals(SingleAnswer3, ExpGreenColor,
				"Single type  Question firts Answer is not correctly colored green");
		String Mark6 = ExamHistoryPage.getSingleImgScore();
		Assert.assertEquals(Mark6, ExpectdMark6, "Sixth Question score are not correctly displayed");
		String TotalMark = ExamHistoryPage.getTotalScore();
		Assert.assertEquals(TotalMark, TotalExamScore, "Total score is not correctly displayed");

		driver.navigate().refresh();
		Thread.sleep(5000);
		System.out.println("test5");

	}

	@Test(dataProvider = "Search_examHistory")
	public void examsearch(String TCID, String TCDescription, String expresult) throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamHistoryPage.searchboxBy()));
		ExamHistoryPage.Searchbar();
		ExamHistoryPage.Searcvalid();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExamHistoryPage.SearchResultBy()));
		String actresultt = ExamHistoryPage.getsearchresults();
		Assert.assertEquals(actresultt.trim(), expresult, "Search is not working properly");
		Thread.sleep(5000);
		driver.navigate().refresh();
		System.out.println("test3");
	}

//---------------------- DATAPROVIDERS-------------------

	@DataProvider(name = "History_Access")
	public Object[][] validStudentUsers() {
		Object[][] validStudentUsers = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "Historypage", 3);
		return validStudentUsers;
	}

	@DataProvider(name = "Verify_elements")
	public Object[][] verifyhistoryelements() {
		Object[][] historyelemnts = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "Verifyelements", 6);
		return historyelemnts;
	}

	@DataProvider(name = "Search_examHistory")
	public Object[][] searchresult() {
		Object[][] Resultext = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "examsearch", 3);
		return Resultext;
	}

	@DataProvider(name = "Results_page")
	public Object[][] pageresult() {
		Object[][] pagetext = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "examResult", 4);
		return pagetext;
	}

	@DataProvider(name = "Results_page_Elements")
	public Object[][] pageelements() {
		Object[][] elements = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "examResultspageverify", 14);
		return elements;
	}}
