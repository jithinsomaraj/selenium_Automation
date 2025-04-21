package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import extentReports.ExtentTestManager;
import projectBase.Base;
import questionBankCreateEdit.QuestionBankCreatePage;
import questionBankList.QuestionBankListPage;
import studentDashboard.StudentDashboardPage;
import utils.ReadExcel;

public class loginCases {
	WebDriver driver ;
	// WebDriver driver = Base.firefoxSetup();
	loginPage loginpage;
	QuestionBankListPage QuestionBankListPage;
	StudentDashboardPage StudentDashboardPage;
	WebDriverWait wait;
	
	@BeforeClass
	public void setup() {
		driver = Base.getDriver();
		loginpage = new loginPage(driver);
		QuestionBankListPage = new QuestionBankListPage(driver);
		StudentDashboardPage = new StudentDashboardPage(driver);
		wait =  new WebDriverWait(driver, 15);
	}

	@Test(dataProvider = "valid-Admin-users", priority = 1)
	public void adminValidLogin(String TCID, String TCDescription, String email, String password, String expWelcomeText) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		Base.loadadminUrl(driver);
		loginpage.login(email, password);
		wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionBankListPage.getQuestionBankListTableBy()));
		String actWelcomeText = QuestionBankListPage.getWelocmeText();
		QuestionBankListPage.logout();
		Assert.assertEquals(actWelcomeText.trim(), expWelcomeText, "Welcome text does not match");
	}

	@Test(dataProvider = "valid-Student-users", priority = 2)
	public void studentValidLogin(String TCID, String TCDescription, String email, String password, String expWelcomeText) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		Base.loadstudentUrl(driver);
		loginpage.login(email, password);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(@class,'MuiPaper-root MuiTableContainer-root')]")));
		String actWelcomeText = StudentDashboardPage.getWelocmeText();
		StudentDashboardPage.logout();
		Assert.assertEquals(actWelcomeText.trim(), expWelcomeText, "Welcome text does not match");
	}

	@Test(dataProvider = "invalid-Admin-users", priority = 3)
	public void adminInvalidLoginSrvrValidation(String TCID, String TCDescription, String email, String password, String expError) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		Base.loadadminUrl(driver);
		loginpage.login(email, password);
		String actError = loginpage.getReactAlertMessage();
		// System.out.println("actual: " + actError + "\n" + "Expected: " + expError);
		Assert.assertEquals(driver.getCurrentUrl(), Base.adminUrl, "Current Url is: " + driver.getCurrentUrl());
		Assert.assertEquals(actError, expError, "Error messages does not match");
	}

	@Test(dataProvider = "Html5-invalid-Admin", priority = 4)
	public void adminInvalidLoginhtml5Validation(String TCID, String TCDescription, String email, String password, String expError) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		String actError = "";
		Base.loadadminUrl(driver);
		loginpage.login(email, password);
		if (email == "") {
			actError = loginpage.getEmail().getAttribute("validationMessage");
		} else if (password == "") {
			actError = loginpage.getPassword().getAttribute("validationMessage");
		} else if (email != "") {
			actError = loginpage.getEmail().getAttribute("validationMessage");
		}
		Assert.assertEquals(actError, expError, "Error messages does not match");
	}

	@Test(dataProvider = "invalid-Student-users", priority = 5)
	public void studentInvalidLoginSrvrValidation(String TCID, String TCDescription, String email, String password, String expError) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		Base.loadstudentUrl(driver);
		loginpage.login(email, password);
		String actError = loginpage.getReactAlertMessage();
		// System.out.println("actual: " + actError + "\n" + "Expected: " + expError);
		Assert.assertEquals(driver.getCurrentUrl(), Base.studentUrl, "URL mismatch");
		Assert.assertEquals(actError, expError, "Error messages does not match");
	}

	@Test(dataProvider = "Html5-invalid-Student", priority = 6)
	public void studentInvalidLoginhtml5Validation(String TCID, String TCDescription, String email, String password, String expError) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		String actError = "";
		Base.loadstudentUrl(driver);
		loginpage.login(email, password);
		if (password == "") {
			actError = loginpage.getPassword().getAttribute("validationMessage");
		} else  {
			actError = loginpage.getEmail().getAttribute("validationMessage");
		} 
		Assert.assertEquals(actError, expError, "Error messages does not match");
	}

	@Test(dataProvider = "ForgotPassword-valid", priority = 7)
	public void forgotPasswordValid(String TCID, String TCDescription, String email, String expMessage) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		Base.loadadminUrl(driver);
		loginpage.forgotPassword(email);
		String actMessage = loginpage.getReactAlertMessage();
		loginpage.returntoLoginPage();
		Assert.assertEquals(actMessage, expMessage, "Success messages does not match");
	}

	@Test(dataProvider = "ForgotPassword-invalid-srvr", priority = 8)
	public void forgotPasswordInvalidsrvr(String TCID, String TCDescription, String email, String expMessage) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		Base.loadadminUrl(driver);
		loginpage.forgotPassword(email);
		String actMessage = loginpage.getReactAlertMessage();
		loginpage.returntoLoginPage();
		Assert.assertEquals(actMessage, expMessage, "Error messages does not match");
	}

	@Test(dataProvider = "ForgotPassword-invalid-html5", priority = 9)
	public void forgotPasswordInvalidhtml5(String TCID, String TCDescription, String email, String expMessage) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		String actMessage = "";
		Base.loadadminUrl(driver);
		loginpage.forgotPassword(email);
		if (email == "") {
			actMessage = loginpage.getForgotPasswordEmail().getAttribute("validationMessage");
		} else if (email != "") {
			actMessage = loginpage.getForgotPasswordEmail().getAttribute("validationMessage");
		}
		Assert.assertEquals(actMessage, expMessage, "Error messages does not match");
	}

// ---------------------- DATAPROVIDERS-------------------

	@DataProvider(name = "valid-Admin-users")
	public Object[][] validAdminUsers() {
		Object[][] validAdminUsers = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Admin_Valid_Login_Cases", 5);
		return validAdminUsers;
	}

	@DataProvider(name = "valid-Student-users")
	public Object[][] validStudentUsers() {
		Object[][] validStudentUsers = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Student_Valid_Login_Cases", 5);
		return validStudentUsers;
	}

	@DataProvider(name = "invalid-Admin-users")
	public Object[][] invalidAdminUsers() {
		Object[][] invalidAdminUsers = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Admin_Invalid_Login_Server_Validations", 5);
		return invalidAdminUsers;
	}

	@DataProvider(name = "Html5-invalid-Admin")
	public Object[][] html5InvalidAdmin() {
		Object[][] html5InvalidAdmin = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Admin_Invalid_Login_html5_Validations", 5);
		return html5InvalidAdmin;
	}

	@DataProvider(name = "invalid-Student-users")
	public Object[][] invalidStudentUsers() {
		Object[][] invalidStudentUsers = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Student_Invalid_Login_Server_Validations", 5);
		return invalidStudentUsers;
	}

	@DataProvider(name = "Html5-invalid-Student")
	public Object[][] html5InvalidStudent() {
		Object[][] html5Invalidtudent = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Student_Invalid_Login_html5_Validations", 5);
		return html5Invalidtudent;
	}

	@DataProvider(name = "ForgotPassword-valid")
	public Object[][] forgotPasswordValidData() {
		Object[][] forgotPasswordValidData = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"ForgotPassword_Valid_Cases", 4);
		return forgotPasswordValidData;
	}

	@DataProvider(name = "ForgotPassword-invalid-srvr")
	public Object[][] forgotPasswordInvalidDatasrvr() {
		Object[][] forgotPasswordInvalidDatasrvr = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"ForgotPassword_Invalid_server_Validations", 4);
		return forgotPasswordInvalidDatasrvr;
	}

	@DataProvider(name = "ForgotPassword-invalid-html5")
	public Object[][] forgotPasswordInvalidDatahtml5() {
		Object[][] forgotPasswordInvalidDatahtml5 = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"ForgotPassword_Invalid_html5_Validations", 4);
		return forgotPasswordInvalidDatahtml5;
	}
}
