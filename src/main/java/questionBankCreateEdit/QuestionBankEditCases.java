package questionBankCreateEdit;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import extentReports.ExtentTestManager;
import login.loginPage;
import projectBase.Base;
import questionBankList.QuestionBankListPage;
import utils.ReadExcel;

public class QuestionBankEditCases {
	WebDriver driver = Base.chromeSetup();
	// WebDriver driver = Base.firefoxSetup();
	loginPage loginpage = new loginPage(driver);
	QuestionBankListPage QuestionBankListPage = new QuestionBankListPage(driver);
	QuestionBankCreatePage QuestionBankCreatePage = new QuestionBankCreatePage(driver);
	WebDriverWait wait = new WebDriverWait(driver, 15);

	@BeforeTest
	@Parameters({ "adminUserName", "adminPassword" })
	public void openbrowserandLogin(String email, String password) {
		Base.loadadminUrl(driver);
		loginpage.login(email, password);
		wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionBankListPage.getQuestionBankListTableBy()));
	}

	@AfterTest
	public void closeBrowser() {
		QuestionBankListPage.logout();
		driver.quit();
	}

	@Test(dataProvider = "Edit-QBank-Valid") // TC_12,TC_15,TC_17,TC_19, TC_22,TC_23
	public void Edit_Qbank_Valid_Cases(String TCID, String TCDescription, String ExistingQbankName, String newQbankName,
			String newgroupData, String newcategoryData, String Description, String addQuestions,
			String removeQuestion) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		// Note: Enter the same name in Existing and New if Qbank name and description
		// should not be changed. group and category leave blank if need not be edited
		QuestionBankListPage.clickQuestionListInMenu();
		wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionBankListPage.getQuestionBankListTableBy()));
		Assert.assertTrue(QuestionBankListPage.IsQbankPresent(ExistingQbankName), "Qbank is not present");
		QuestionBankListPage.navigateToQbankEdit(ExistingQbankName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionBankCreatePage.submitButtonBy()));
		wait.until(ExpectedConditions.attributeToBe(QuestionBankCreatePage.getTitle(), "value", ExistingQbankName));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String[] groupSplit = newgroupData.split(",");
		String[] categorySplit = newcategoryData.split(",");
		String[] questionSplit = addQuestions.split(",");
		if (!ExistingQbankName.equals(newQbankName)) {
			QuestionBankCreatePage.setTitle(newQbankName);
		}
		if (!"".equals(newgroupData)) {
			QuestionBankCreatePage.clearGroupEntries();
			QuestionBankCreatePage.setGroups(groupSplit);
		}
		if (!"".equals(newcategoryData)) {
			QuestionBankCreatePage.clearCateEntries();
			QuestionBankCreatePage.setCategories(categorySplit);
		}
		if (!QuestionBankCreatePage.getDescription().equals(Description)) {
			QuestionBankCreatePage.setDescription(Description);
		}

		if (!"".equals(addQuestions)) {
			QuestionBankCreatePage.navigateToAddQuestion();
			QuestionBankCreatePage.selectQuestionsinEdit(questionSplit);
		}
		if (!"".equals(removeQuestion)) {
			String[] removeQuestions = new String[1];
			removeQuestions[0] = removeQuestion;
			QuestionBankCreatePage.deleteAddedQuestionsinEdit(removeQuestion);
			QuestionBankListPage.getReactAlertMessage();
			QuestionBankListPage.clickQuestionListInMenu();
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(QuestionBankListPage.getQuestionBankListTableBy()));
			Assert.assertTrue(QuestionBankListPage.IsQbankPresent(ExistingQbankName), "Qbank is not present");
			QuestionBankListPage.navigateToQbankEdit(ExistingQbankName);
			wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionBankCreatePage.submitButtonBy()));
			wait.until(ExpectedConditions.attributeToBe(QuestionBankCreatePage.getTitle(), "value", ExistingQbankName));
			QuestionBankCreatePage.verifyAddedQuestions(removeQuestions);
		}
		QuestionBankCreatePage.clickSaveButton();
		String alert = QuestionBankListPage.getReactAlertMessage();
		Assert.assertEquals(alert, "–â‘èW‚ð³í‚ÉXV‚µ‚Ü‚µ‚½", "Alert message does not match");
		if (!"".equals(newQbankName)) {
			Assert.assertTrue(QuestionBankListPage.IsQbankPresent(newQbankName), "QuestionBank is not present");
		}

	}

	@Test(dataProvider = "Edit-QBank-invalid") // TC_13,TC_14,TC_16,TC_18,TC_19,TC_20,TC_21
	public void Edit_Qbank_Invalid_Cases(String TCID, String TCDescription, String ExistingQbankName,
			String newQbankName, String groupData, String categoryData, String Description, String Error) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		// Note: Leave the data in the data provider as "", if the data need be removed.
		// Note: Enter the same name in Existing and New if Qbank name and description
		// should not be changed
		QuestionBankListPage.clickQuestionListInMenu();
		wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionBankListPage.getQuestionBankListTableBy()));
		Assert.assertTrue(QuestionBankListPage.IsQbankPresent(ExistingQbankName), "Qbank is not present");
		QuestionBankListPage.navigateToQbankEdit(ExistingQbankName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionBankCreatePage.submitButtonBy()));
		wait.until(ExpectedConditions.attributeToBe(QuestionBankCreatePage.getTitle(), "value", ExistingQbankName));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (!ExistingQbankName.equals(newQbankName)) {
			QuestionBankCreatePage.setTitle(newQbankName);
		}
		if ("".equals(groupData)) {
			QuestionBankCreatePage.clearGroupEntries();
		}
		if ("".equals(categoryData)) {
			QuestionBankCreatePage.clearCateEntries();
		}
		if (!QuestionBankCreatePage.getDescription().equals(Description)) {
			QuestionBankCreatePage.setDescription(Description);
		}

		QuestionBankCreatePage.clickSaveButton();

		if ("".equals(newQbankName) || newQbankName.length() > 100) {
			Assert.assertEquals(QuestionBankCreatePage.getTitleError(), Error, "Error message does not match");
		} else if ("".equals(groupData)) {
			Assert.assertEquals(QuestionBankCreatePage.getGroupError(), Error, "Error message does not match");
		} else if ("".equals(categoryData)) {
			Assert.assertEquals(QuestionBankCreatePage.getCategoryError(), Error, "Error message does not match");
		} else if (Description.length() > 2000) {
			Assert.assertEquals(QuestionBankCreatePage.getDescriptionError(), Error, "Error message does not match");
		}
		String Alert = QuestionBankListPage.getReactAlertMessage();
		if (!"".equals(Alert)) {
			Assert.fail(Alert);
		}
	}

	// ---------------------- DATAPROVIDERS-------------------

	@DataProvider(name = "Edit-QBank-Valid")
	public Object[][] editQbankValidData() {
		Object[][] editQbankValidData = ReadExcel.ReadTestData(this.getClass().getSimpleName(),
				"Edit_Qbank_Valid_Cases", 9);
		return editQbankValidData;
	}

	@DataProvider(name = "Edit-QBank-invalid")
	public Object[][] editQbankInvalidData() {
		Object[][] editQbankInvalidData = ReadExcel.ReadTestData(this.getClass().getSimpleName(),
				"Edit_Qbank_Invalid_Cases", 8);
		return editQbankInvalidData;
	}

}
