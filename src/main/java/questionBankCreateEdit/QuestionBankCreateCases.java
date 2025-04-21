package questionBankCreateEdit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
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

public class QuestionBankCreateCases {
	WebDriver driver;
	loginPage loginpage;
	QuestionBankListPage QuestionBankListPage;
	StudentDashboardPage StudentDashboardPage;
	QuestionBankCreatePage QuestionBankCreatePage;
	WebDriverWait wait;

	@BeforeClass
	@Parameters({ "adminUserName", "adminPassword" })
	public void setup(String email, String password) {
		driver = Base.getDriver();
		loginpage = new loginPage(driver);
		QuestionBankListPage = new QuestionBankListPage(driver);
		QuestionBankCreatePage = new QuestionBankCreatePage(driver);
		wait = new WebDriverWait(driver, 15);
		Base.loadadminUrl(driver);
		loginpage.login(email, password);
		wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionBankListPage.getQuestionBankListTableBy()));
	}

	@AfterClass
	public void clean() {
		QuestionBankListPage.logout();
	}

	@Test(dataProvider = "Create-QBank-Valid", priority = 12) // TC_01,TC_02,TC_08,TC_11,TC_25
	public void Create_Qbank_Valid_Cases(String TCID, String TCDescription, String titleData, String groupData,
			String categoryData, String Description, String questions) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
		LocalDateTime now = LocalDateTime.now();
		titleData = titleData + "_" + dtf.format(now);
		QuestionBankListPage.clickQuestionListInMenu();
		QuestionBankListPage.NavigateToQBankCreate();
		wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionBankCreatePage.submitButtonBy()));
		String[] groupSplit = groupData.split(",");
		String[] categorySplit = categoryData.split(",");
		String[] questionSplit = questions.split(",");
		QuestionBankCreatePage.setTitle(titleData);
		QuestionBankCreatePage.setGroups(groupSplit);
		QuestionBankCreatePage.setCategories(categorySplit);
		if (!"".equals(Description)) {
			QuestionBankCreatePage.setDescription(Description);
		}
		if (!"".equals(questions)) {
			QuestionBankCreatePage.navigateToAddQuestion();
			QuestionBankCreatePage.selectQuestions(questionSplit);
			Assert.assertTrue(QuestionBankCreatePage.verifyAddedQuestions(questionSplit), "Questions not listed");
		}
		QuestionBankCreatePage.clickSaveButton();
		String alert = QuestionBankListPage.getReactAlertMessage();
		Assert.assertEquals(alert, "–â‘èW‚ð³í‚Éì¬‚µ‚Ü‚µ‚½", "Alert message does not match");
		Assert.assertTrue(QuestionBankListPage.IsQbankPresent(titleData), "QuestionBank is not present");
		if (!"".equals(questions)) {
		QuestionBankListPage.navigateToQuesionList(titleData);
		Assert.assertTrue(QuestionBankListPage.IsQuestionNamesPresent(questionSplit), "Question added is not present after saving question bank");
		}
	}

	@Test(dataProvider = "Delete-Questions", priority = 13) // TC_03
	public void Delete_Added_Questions_To_QBank(String TCID, String TCDescription, String titleData, String groupData,
			String categoryData, String Description, String questions) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		QuestionBankListPage.clickQuestionListInMenu();
		QuestionBankListPage.NavigateToQBankCreate();
		wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionBankCreatePage.submitButtonBy()));
		String[] groupSplit = groupData.split(",");
		String[] categorySplit = categoryData.split(",");
		String[] questionSplit = questions.split(",");
		QuestionBankCreatePage.setTitle(titleData);
		QuestionBankCreatePage.setGroups(groupSplit);
		QuestionBankCreatePage.setCategories(categorySplit);
		if (!"".equals(Description)) {
			QuestionBankCreatePage.setDescription(Description);
		}
		if (!"".equals(questions)) {
			QuestionBankCreatePage.navigateToAddQuestion();
			QuestionBankCreatePage.selectQuestions(questionSplit);
		}
		QuestionBankCreatePage.deleteAddedQuestions(questionSplit);
		Assert.assertFalse(QuestionBankCreatePage.verifyAddedQuestions(questionSplit),
				"Questions are listed after delete");
	}

	@Test(dataProvider = "Create-QBank-Invalid", priority = 14) // TC_04,TC_05,TC_06,TC_07,TC_09
	public void Create_QBank_Invalid_Cases(String TCID, String TCDescription, String titleData, String groupData,
			String categoryData, String Description, String questions, String Error) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		QuestionBankListPage.clickQuestionListInMenu();
		QuestionBankListPage.NavigateToQBankCreate();
		wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionBankCreatePage.submitButtonBy()));
		String[] groupSplit = groupData.split(",");
		String[] categorySplit = categoryData.split(",");
		String[] questionSplit = questions.split(",");
		if (!"".equals(titleData)) {
			QuestionBankCreatePage.setTitle(titleData);
		}
		if (!"".equals(Description)) {
			QuestionBankCreatePage.setDescription(Description);
		}
		if (!"".equals(groupData)) {
			QuestionBankCreatePage.setGroups(groupSplit);
		}
		if (!"".equals(categoryData)) {
			QuestionBankCreatePage.setCategories(categorySplit);
		}
		if (!"".equals(questions)) {
			QuestionBankCreatePage.navigateToAddQuestion();
			QuestionBankCreatePage.selectQuestions(questionSplit);
		}

		QuestionBankCreatePage.clickSaveButton();

		if (titleData.length() > 100) {
			Assert.assertEquals(QuestionBankCreatePage.getTitleError(), Error, "Error message does not match");
		}
		if ("".equals(titleData)) {
			Assert.assertEquals(QuestionBankCreatePage.getTitle().getAttribute("validationMessage"), Error,
					"Error message does not match");
		}
		if (Description.length() > 2000) {
			Assert.assertEquals(QuestionBankCreatePage.getDescriptionError(), Error, "Error message does not match");
		}
		if ("".equals(groupData)) {
			Assert.assertEquals(QuestionBankCreatePage.getGroupError(), Error, "Error message does not match");
		}
		if ("".equals(categoryData)) {
			Assert.assertEquals(QuestionBankCreatePage.getCategoryError(), Error, "Error message does not match");
		}
		String Alert = QuestionBankListPage.getReactAlertMessage();
		if (!"".equals(Alert)) {
			Assert.fail(Alert);
		}
	}

	public void Cancel_Qbank_Creation(String TCID, String TCDescription, String titleData, String groupData,
			String categoryData, String Description, String questions) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		QuestionBankListPage.clickQuestionListInMenu();
		QuestionBankListPage.NavigateToQBankCreate();
		wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionBankCreatePage.submitButtonBy()));
		String[] groupSplit = groupData.split(",");
		String[] categorySplit = categoryData.split(",");
		String[] questionSplit = questions.split(",");
		QuestionBankCreatePage.setTitle(titleData);
		QuestionBankCreatePage.setGroups(groupSplit);
		QuestionBankCreatePage.setCategories(categorySplit);
		if (!"".equals(Description)) {
			QuestionBankCreatePage.setDescription(Description);
		}
		if (!"".equals(questions)) {
			QuestionBankCreatePage.navigateToAddQuestion();
			QuestionBankCreatePage.selectQuestions(questionSplit);
		}
		Assert.assertTrue(QuestionBankCreatePage.verifyAddedQuestions(questionSplit), "Questions not listed");
		QuestionBankCreatePage.clickCancelButton();
		Assert.assertFalse(QuestionBankListPage.IsQbankPresent(titleData), "QuestionBank is present");
	}

// ---------------------- DATAPROVIDERS-------------------	

	@DataProvider(name = "Create-QBank-Valid")
	public Object[][] createQbankValidData() {
		Object[][] createQbankValidData = ReadExcel.ReadTestData(this.getClass().getSimpleName(),
				"Create_Qbank_Valid_Cases", 7);
		return createQbankValidData;
	}

	@DataProvider(name = "Delete-Questions")
	public Object[][] DeleteQuestions() {
		Object[][] DeleteQuestions = ReadExcel.ReadTestData(this.getClass().getSimpleName(),
				"Delete_Added_Questions_To_Qbank", 7);
		return DeleteQuestions;
	}

	@DataProvider(name = "Create-QBank-Invalid")
	public Object[][] createQbankFieldValidationData() {
		Object[][] createQbankInvalidData = ReadExcel.ReadTestData(this.getClass().getSimpleName(),
				"Create_QBank_Invalid_Cases", 8);
		return createQbankInvalidData;
	}
}
