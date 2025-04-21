package questionBankList;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import extentReports.ExtentTestManager;
import login.loginPage;
import projectBase.Base;
import utils.ReadExcel;

public class QuestionBankListCases {
	WebDriver driver;
	loginPage loginpage;
	QuestionBankListPage QuestionBankListPage;
	WebDriverWait wait;

	@BeforeClass
	@Parameters({ "adminUserName", "adminPassword" })
	public void openbrowserandLogin(String email, String password) {
		driver = Base.getDriver();
		loginpage = new loginPage(driver);
		QuestionBankListPage = new QuestionBankListPage(driver);
		wait = new WebDriverWait(driver, 15);
		Base.loadadminUrl(driver);
		loginpage.login(email, password);
		wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionBankListPage.getQuestionBankListTableBy()));
	}

	@AfterClass
	public void closeBrowser() {
		QuestionBankListPage.logout();
	}

	@Test(dataProvider = "search", priority = 10) // TC_26
	public void qbSearch(String TCID, String TCDescription, String searchTerm, String isResult) {
		boolean resultExist = false;
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		if (isResult.equalsIgnoreCase("TRUE")){
			resultExist = true;
		}else if(isResult.equalsIgnoreCase("FALSE")) {
			resultExist = false;
		}
		List<String> result = QuestionBankListPage.search(searchTerm, resultExist);
		if (result.size() >= 1 && resultExist) {
			for (String searchResult : result) {
				// Assert.assertEquals(searchResult, searchTerm, "Result does not match the
				// search term");
				Assert.assertTrue(searchResult.contains(searchTerm),
						"ActuaResult: " + searchResult + ", does not match the search term");
			}
		} else {
			if (!resultExist) {
				Assert.assertEquals(result.get(0), "åüçıåãâ Ç™å©Ç¬Ç©ÇËÇ‹ÇπÇÒ",
						"The text shown for blank result is not as expected");
			}
		}
	}

	@Test(dataProvider = "delete", priority = 11) // TC_24
	public void qbDelete(String TCID, String TCDescription, String QBankName, String Message, String isDeletable) {
		boolean deletable = false;
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		if (isDeletable.equalsIgnoreCase("TRUE")){
			deletable = true;
		}else if(isDeletable.equalsIgnoreCase("FALSE")) {
			deletable = false;
		}
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionBankListPage.getQuestionBankListTableBy()));
		QuestionBankListPage.deleteQbank(QBankName);
		String Alert = QuestionBankListPage.getReactAlertMessage();
		Assert.assertEquals(Alert, Message, "Alert Messages does not match");
		if (deletable) {
			Assert.assertFalse(QuestionBankListPage.IsQbankPresent(QBankName), "Qbank is not deleted");
		}
	}

// ---------------------- DATAPROVIDERS-------------------	

	@DataProvider(name = "search")
	public Object[][] searchInput() {
		Object[][] searchInput = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "qbsearch", 4);
		return searchInput;
	}

	@DataProvider(name = "delete")
	public Object[][] deleteInput() {
		Object[][] deleteInput = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "qbDelete", 5);
		return deleteInput;
	}
}
