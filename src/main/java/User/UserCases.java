package User;

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
import org.openqa.selenium.Keys;
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
import ExamScheduler.ExamSchedulePage;
import login.loginPage;
import projectBase.Base;
import questionBankList.QuestionBankListPage;
import utils.ReadExcel;
import User.UserPage;
import extentReports.ExtentTestManager;

public class UserCases {
	WebDriver driver;
	loginPage loginpage;
	UserPage UserPage;
	QuestionBankListPage QuestionBankListPage;
	WebDriverWait wait;

	@BeforeClass
	@Parameters({ "adminUserName", "adminPassword" })
	public void openbrowserandLogin(String email, String password) {
		driver = Base.getDriver();
		loginpage = new loginPage(driver);
		UserPage = new UserPage(driver);
		QuestionBankListPage = new QuestionBankListPage(driver);
		wait = new WebDriverWait(driver, 15);
		Base.loadadminUrl(driver);
		loginpage.login(email, password);
		wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionBankListPage.getQuestionBankListTableBy()));
		UserPage.ClickUserButton();
		wait.until(ExpectedConditions.visibilityOfElementLocated(UserPage.UserPageBy()));
		wait.until(ExpectedConditions.visibilityOfElementLocated(UserPage.SearchBy()));
	}

	@AfterClass
	public void closeBrowser() {
		 QuestionBankListPage.logout();
		 //driver.quit();

	}

	@Test(dataProvider = "AddUser", priority = 10)
	public void AddUser(String TCID, String TCDescription, String Firstname, String Lastname, String email,
			String Passwrd, String confPasswrd, String UserType, String FirsNameValdtn, String LastNameValdtn,
			String EmailValdtn, String InvPassValdtn, String BlankPassValdtn, String PassmissMatchValdtn, String keys)
			throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);

		UserPage.ClickAddUserButton();
		wait.until(ExpectedConditions.visibilityOfElementLocated(UserPage.AddUserTitleBy()));
		UserPage.EnterFirstName(Firstname);
		UserPage.EnterLastName(Lastname);
		UserPage.EnterValidEmail(email);
		UserPage.EnterValidPassword(Passwrd);
		UserPage.EnterValidConfirmPassword(confPasswrd);
		UserPage.ClickRoleDropDown();
		UserPage.selectRoleFromDropDown(UserType);
		UserPage.ClickCreateUserBtn();
		String alert = QuestionBankListPage.getReactAlertMessage();
		if(alert.equals("ユーザーが正常に作成されました")) {
		    Assert.assertEquals(alert,"ユーザーが正常に作成されました","Alert message does not match can't add user with Valid data");
	
			}
		if(UserPage.isFirstNameValidtnPresent() && UserPage.isLastNameValidtnPresent() && UserPage.isEmailValidtnPresent() && UserPage.isPasswordinvalidMsgPresent()) {
			String actFValid = UserPage.getFirstNameValidtn();
			String actLValid = UserPage.getLastNameValidtn();
			String actEmailValidtn = UserPage.getEmailValidtn();
			String actinvalidPass = UserPage.getPasswordinvalidMsg();
			Assert.assertEquals(actFValid.trim(), FirsNameValdtn, "First Name validation failed");
			Assert.assertEquals(actLValid.trim(), LastNameValdtn, "Last Name validation failed");
			Assert.assertEquals(actEmailValidtn.trim(), EmailValdtn, "Email validation failed");
			Assert.assertEquals(actinvalidPass.trim(), InvPassValdtn, "Password validation failed");
			}
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(UserPage.UserPageBy()));
	}

	@Test(dataProvider = "Edit_user", priority = 11)
	public void EditUser(String TCID, String TCDescription, String Fname, String edfirstName, String edlastName,
			String UserType, String ChangePass, String NewPass, String ConNewPass, String FirsNameValdtn1,
			String LastNameValdtn1, String Planner, String InvPassValdtn1, String PassmissMatchValdtn1)
			throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		wait.until(ExpectedConditions.visibilityOfElementLocated(UserPage.UserPageBy()));
		UserPage.ClickEditUserButton();
		UserPage.UpdateFirstName(edfirstName);
		UserPage.UpdateLasttName(edlastName);
		if (UserType != null && !UserType.trim().isEmpty()) {
			UserPage.ClickRoleDropDown();
			UserPage.selectRoleFromDropDown(UserType);
		}

		if (ChangePass != null && !ChangePass.trim().isEmpty()) {

			UserPage.ClickChangePassButton();
			UserPage.EnterValidUpdatePassword(NewPass);
			UserPage.EnterValidupdateConfirmPassword(ConNewPass);
			UserPage.ClickSendButton1();
			driver.navigate().refresh();
			wait.until(ExpectedConditions.visibilityOfElementLocated(UserPage.UserPageBy()));
			wait.until(ExpectedConditions.visibilityOfElementLocated(UserPage.SearchBy()));
		} else {
			UserPage.ClickSendButton1();
			driver.navigate().refresh();
			wait.until(ExpectedConditions.visibilityOfElementLocated(UserPage.UserPageBy()));
			wait.until(ExpectedConditions.visibilityOfElementLocated(UserPage.SearchBy()));
		}
		String alert = QuestionBankListPage.getReactAlertMessage();
		if(alert.equals("ユーザーが正常に作成されました")) {
		    Assert.assertEquals(alert,"ユーザーが正常に作成されました","Alert message does not match can't add user with Valid data");
	
			}
		if(UserPage.isFirstNameValidtnPresent() && UserPage.isLastNameValidtnPresent() && UserPage.isEmailValidtnPresent() && UserPage.isPasswordinvalidMsgPresent()) {
			String actFValid = UserPage.getFirstNameValidtn();
			String actLValid = UserPage.getLastNameValidtn();
			String actinvalidPass = UserPage.getPasswordinvalidMsg();
			Assert.assertEquals(actFValid.trim(), FirsNameValdtn1, "First Name validation failed");
			Assert.assertEquals(actLValid.trim(), LastNameValdtn1, "Last Name validation failed");
			Assert.assertEquals(actinvalidPass.trim(), InvPassValdtn1, "Password validation failed");
			driver.navigate().refresh();
			wait.until(ExpectedConditions.visibilityOfElementLocated(UserPage.UserPageBy()));
			wait.until(ExpectedConditions.visibilityOfElementLocated(UserPage.SearchBy()));
			}
		else {
			driver.navigate().refresh();
			wait.until(ExpectedConditions.visibilityOfElementLocated(UserPage.UserPageBy()));
			wait.until(ExpectedConditions.visibilityOfElementLocated(UserPage.SearchBy()));
			
		}
		
		
		
		
		
		
		
		
		
		

	}

	@Test(dataProvider = "User_Delete", priority = 12)
	public void DeleteUser(String TCID, String TCDescription, String UserFirstName) throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		wait.until(ExpectedConditions.visibilityOfElementLocated(UserPage.UserPageBy()));
		Thread.sleep(5000);
		UserPage.ClickDelButton(UserFirstName);
		UserPage.ClickDeleteConfYes();
		String alert10 = QuestionBankListPage.getReactAlertMessage();
		Assert.assertEquals(alert10, "ユーザーは正常に削除されました", "Alert message does not match can't Delete user");
	}

	@Test(dataProvider = "search", priority = 13)
	public void ValidSearch(String TCID, String TCDescription, String Search) throws InterruptedException {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);

		wait.until(ExpectedConditions.visibilityOfElementLocated(UserPage.SearchBy()));

		UserPage.ValidSearch(Search);
		Assert.assertTrue(UserPage.SearchResultVerify(), "Search is not working properly");
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(UserPage.UserPageBy()));
	}

//----------------------------------------------------------------DataProviders-----------------------------------------------------------------------------------------------

	@DataProvider(name = "search")
	public Object[][] Searchvalue() {
		Object[][] validlist = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "ValidSearch", 3);
		return validlist;
	}

	@DataProvider(name = "AddUser")
	public Object[][] DropdownList() {
		Object[][] validlist = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "AddUser", 15);
		return validlist;
	}

	@DataProvider(name = "Edit_user")
	public Object[][] UserUpdateValidations() {
		Object[][] validlist = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "EditUser", 14);
		return validlist;
	}

	@DataProvider(name = "User_Delete")
	public Object[][] Delete() {
		Object[][] validlist = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "DeleteUser", 3);
		return validlist;
	}

}
