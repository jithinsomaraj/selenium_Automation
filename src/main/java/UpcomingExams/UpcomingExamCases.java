package UpcomingExams;

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
import studentDashboard.StudentDashboardPage;
import utils.ReadExcel;

public class UpcomingExamCases {
	WebDriver driver;
	loginPage loginpage;
	UpcomingExampage UpcomingExampage;
	WebDriverWait wait;
	QuestionBankListPage QuestionBankListPage;

	@BeforeClass
	@Parameters({ "studentUserName", "studentPassword" })
	public void openbrowserandLogin(String email, String password) {
		driver = Base.getDriver();
		loginpage = new loginPage(driver);
		UpcomingExampage = new UpcomingExampage(driver);
		QuestionBankListPage = new QuestionBankListPage(driver);
		wait = new WebDriverWait(driver, 15);
		Base.loadstudentUrl(driver);
		loginpage.login(email, password);
		wait.until(ExpectedConditions.visibilityOfElementLocated(UpcomingExampage.PageTitleBy()));
	}

	@AfterClass
	public void closeBrowser() {
		QuestionBankListPage.logout();
		//driver.quit();
	}

	@Test(dataProvider = "Upcoming_exam_page")
	public void Verifyupcomingpage(String TCID, String TCDescription, String expWelcomeText)
			throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		System.out.println("check");
		UpcomingExampage.clickupcomingbutton();
		System.out.println("chek2");
		String actText = UpcomingExampage.getWelocmeText();
		wait.until(ExpectedConditions.visibilityOfElementLocated(UpcomingExampage.WelcomeTextElementBy()));
		Assert.assertEquals(actText.trim(), expWelcomeText, "Welcome text does not match");
		Thread.sleep(2000);
		System.out.println("test1");
	}

	@Test(dataProvider = "Search_exam")
	public void examsearch(String TCID, String TCDescription, String expresult) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		UpcomingExampage.Searchbar();
		UpcomingExampage.Searcvalid();
		String actresultt = UpcomingExampage.getsearchresults();
		Assert.assertEquals(actresultt.trim(), expresult, "Search is not working properly");
		System.out.println("test2");

	}

	@Test(dataProvider = "UpcomingExam_colomns")
	public void upcomingconfirm(String TCID, String TCDescription, String title, String duration, String start,
			String end, String apply, String showall) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		UpcomingExampage.upcomingtitlenBy();
		UpcomingExampage.upcomingdurtnBy();
		UpcomingExampage.upcomingstartBy();
		UpcomingExampage.upcomingendBy();
		UpcomingExampage.upcomingapplyBy();
		String acttitle = UpcomingExampage.getupcomingtitle();
		Assert.assertEquals(acttitle.trim(), title, "Title is not Displayed");
		String actdurn = UpcomingExampage.getupcomingdurtn();
		Assert.assertEquals(actdurn.trim(), duration, "Duration is not Displayed");
		String actstart = UpcomingExampage.getupcomingstart();
		Assert.assertEquals(actstart.trim(), start, "Start is not Displayed");
		String actend = UpcomingExampage.getupcomingend();
		Assert.assertEquals(actend.trim(), end, "End is not Displayed");
		String actapply = UpcomingExampage.getupcomingapply();
		Assert.assertEquals(actapply.trim(), apply, "Apply is not Displayed");
		System.out.println("test3");
		driver.navigate().refresh();

	}

	@Test(dataProvider = "Apply_and_register")
	public void applyRegister(String TCID, String TCDescription, String exptxt, String expt2) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		wait.until(ExpectedConditions.visibilityOfElementLocated(UpcomingExampage.UpcomingExambuttonBy()));
		UpcomingExampage.clickapplybutton();
		UpcomingExampage.clickcancelbutton();
		wait.until(ExpectedConditions.visibilityOfElementLocated(UpcomingExampage.WelcomeTextElementBy()));
		String actText2 = UpcomingExampage.getWelocmeText();
		Assert.assertEquals(actText2.trim(), expt2, "Cancel button not working");
		UpcomingExampage.clickapplybutton();
		UpcomingExampage.clickapplybuttonconfirm();
		wait.until(ExpectedConditions.visibilityOfElementLocated(UpcomingExampage.WelcomeTextElementBy()));
		String actText = UpcomingExampage.getWelocmeText();
		Assert.assertEquals(actText.trim(), exptxt, "Exam registration failed");
		System.out.println("test4");

	}

//---------------------- DATAPROVIDERS-------------------
	@DataProvider(name = "Upcoming_exam_page")
	public Object[][] Upcompage() {
		Object[][] Weltext = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "Verifyupcomingpage", 3);
		return Weltext;
	}

	@DataProvider(name = "Search_exam")
	public Object[][] searchresult() {
		Object[][] Resultext = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "examsearch", 3);
		return Resultext;
	}

	@DataProvider(name = "UpcomingExam_colomns")
	public Object[][] ExamElements() {
		Object[][] elements = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "upcomingconfirm", 8);
		return elements;
	}

	@DataProvider(name = "Apply_and_register")
	public Object[][] applyreg() {
		Object[][] elemen = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "applyRegister", 4);
		return elemen;
	}

}
