package group;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

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
import questionBankList.QuestionBankListPage;
import utils.ReadExcel;

public class GroupListCases {
	WebDriver driver ;
	loginPage loginpage;
	GroupList GroupList;
	QuestionBankListPage QuestionBankListPage;
	WebDriverWait wait;

	@BeforeClass
	@Parameters({ "adminUserName", "adminPassword" })
	public void setup(String email, String password) {
		driver = Base.getDriver();
		loginpage = new loginPage(driver);
		GroupList = new GroupList(driver);
		wait = new WebDriverWait(driver, 15);
		QuestionBankListPage = new QuestionBankListPage(driver);
		Base.loadadminUrl(driver);
		loginpage.login(email, password);
		wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionBankListPage.getQuestionBankListTableBy()));
	}

	@AfterClass
	public void closeBrowser() {
		QuestionBankListPage.logout();
	}

	@Test(dataProvider = "search", priority = 15) // TC_03
	public void groupSearch(String TCID, String TCDescription, String searchTerm, String resultExist ) {
		Boolean isResult = false;
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		if (resultExist.equalsIgnoreCase("TRUE")){
			isResult = true;
		}else if(resultExist.equalsIgnoreCase("FALSE")) {
			isResult = false;
		}
		if (!driver.findElements(GroupList.closeButtonBy()).isEmpty()) {
			GroupList.clickCloseButton();
		}
		GroupList.clickGroupListInMenu();
		List<String> result = GroupList.search(searchTerm, isResult);
		if (result.size() >= 1 && isResult) {
			for (String searchResult : result) {
				// Assert.assertEquals(searchResult, searchTerm, "Result does not match the
				// search term");
				Assert.assertTrue(searchResult.contains(searchTerm),
						"ActuaResult: " + searchResult + ", does not match the search term");
			}
		} else {
			if (!isResult) {
				Assert.assertEquals(result.get(0), "検索結果が見つかりません",
						"The text shown for blank result is not as expected");
			}
		}
	}

	@Test(dataProvider = "Create-Group-Valid", priority = 16) // TC_04,TC_05,TC_07,TC_08,TC_09,TC_10,TC_11,
	public void createGroupValid(String TCID, String TCDescription, String name, String description) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
		LocalDateTime now = LocalDateTime.now();
		name = name + "_" + dtf.format(now);
		if (!driver.findElements(GroupList.closeButtonBy()).isEmpty()) {
			GroupList.clickCloseButton();
		}
		GroupList.clickGroupListInMenu();
		GroupList.NavigateToGroupCreate();
		GroupList.addGroup(name, description);
		String alert = GroupList.getReactAlertMessage();
		Assert.assertEquals(alert, "グループが正常に作成されました", "Alert message does not match");
		Assert.assertTrue(GroupList.IsGroupPresent(name), "Group is not present in list");
	}

	@Test(dataProvider = "Create-Group-Invalid", priority = 17) // TC_06,TC_12,TC_13
	public void createGroupInvalid(String TCID, String TCDescription, String name, String description,
			String expError) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		if (!driver.findElements(GroupList.closeButtonBy()).isEmpty()) {
			GroupList.clickCloseButton();
		}
		String actError = "";
		GroupList.clickGroupListInMenu();
		GroupList.NavigateToGroupCreate();
		GroupList.addGroup(name, description);
		if ("".equals(name)) {
			actError = GroupList.getTitle().getAttribute("validationMessage");
		}
		if (name.length() > 100) {
			actError = GroupList.getTitleError();
		}
		if (description.length() > 255) {
			actError = GroupList.getDescError();
		}
		GroupList.clickCloseButton();
		Assert.assertEquals(actError, expError, "Error message does not match");
	}

	@Test(dataProvider = "Edit-Group-Valid", priority = 18) // TC_14,TC_15,TC_16,TC_17,TC_18,TC_19,TC_21,TC_22
	public void editGroupValid(String TCID, String TCDescription, String extname, String newName, String description) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		// Note: Enter the same name in Existing and New if name and description
		// should not be changed
		if (!driver.findElements(GroupList.closeButtonBy()).isEmpty()) {
			GroupList.clickCloseButton();
		}
		GroupList.clickGroupListInMenu();
		GroupList.navigateToGroupEdit(extname);
		wait.until(ExpectedConditions.textToBePresentInElement(GroupList.getTitle(), extname));
		if (!extname.equals(newName)) {
			GroupList.setTitle(newName);
		}
		if (!GroupList.getDescText().equals(description)) {
			GroupList.setDesc(description);
		}
		GroupList.clickSaveButton();
		String alert = GroupList.getReactAlertMessage();
		Assert.assertEquals(alert, "グループが正常に更新されました", "Alert message does not match");
		if (!extname.equals(newName)) {
			Assert.assertTrue(GroupList.IsGroupPresent(newName), "Group is not present");
		}
	}

	@Test(dataProvider = "Edit-Group-inValid", priority = 19) // TC_20
	public void editGroupInvalid(String TCID, String TCDescription, String extname, String newName, String description,
			String expError) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		// Note: Leave the data in the data provider as "", if the data need be removed.
		// Note: Enter the same name in Existing and New if name and description
		// should not be changed
		if (!driver.findElements(GroupList.closeButtonBy()).isEmpty()) {
			GroupList.clickCloseButton();
		}
		String actError = "";
		GroupList.clickGroupListInMenu();
		GroupList.navigateToGroupEdit(extname);
		wait.until(ExpectedConditions.textToBePresentInElement(GroupList.getTitle(), extname));
		if (!extname.equals(newName)) {
			GroupList.setTitle(newName);
		}
		if (!GroupList.getDescText().equals(description)) {
			GroupList.setDesc(description);
		}
		GroupList.clickSaveButton();

		if ("".equals(newName) || newName.length() > 100) {
			actError = GroupList.getTitleError();
		}
		if (description.length() > 255) {
			actError = GroupList.getDescError();
		}
		GroupList.clickCloseButton();
		Assert.assertEquals(actError, expError, "Error message does not match");
		String Alert = QuestionBankListPage.getReactAlertMessage();
		if (!"".equals(Alert)) {
			Assert.fail(Alert);
		}
	}

	@Test(dataProvider = "delete", priority = 20) // TC_23,TC_24,TC_25
	public void groupDelete(String TCID, String TCDescription, String name, String Message, String Deletable) {
		Boolean isDeletable = false;
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		if (Deletable.equalsIgnoreCase("TRUE")){
			isDeletable = true;
		}else if(Deletable.equalsIgnoreCase("FALSE")) {
			isDeletable = false;
		}
		if (!driver.findElements(GroupList.closeButtonBy()).isEmpty()) {
			GroupList.clickCloseButton();
		}
		GroupList.clickGroupListInMenu();
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(GroupList.getGroupTableBy()));
		GroupList.deleteGroup(name);
		String Alert = QuestionBankListPage.getReactAlertMessage();
		Assert.assertEquals(Alert, Message, "Alert Messages does not match");
		if (isDeletable) {
			Assert.assertFalse(GroupList.IsGroupPresent(name), "group is not deleted");
		}

	}

// ---------------------- DATAPROVIDERS-------------------	

	@DataProvider(name = "search")
	public Object[][] searchInput() {
		Object[][] searchInput = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "groupSearch", 4);
		return searchInput;
	}

	@DataProvider(name = "Create-Group-Valid")
	public Object[][] groupValidData() {
		Object[][] groupValidData = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "createGroupValid", 4);
		return groupValidData;
	}

	@DataProvider(name = "Create-Group-Invalid")
	public Object[][] groupinValidData() {
		Object[][] groupInvalidData = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "createGroupInvalid", 5);
		return groupInvalidData;
	}

	@DataProvider(name = "Edit-Group-Valid")
	public Object[][] editGroupValidData() {
		Object[][] groupValidData = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "editGroupValid", 5);
		return groupValidData;
	}

	@DataProvider(name = "Edit-Group-inValid")
	public Object[][] editGroupInvalidData() {
		Random rd = new Random();
		Object[][] groupInvalidData = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "editGroupInvalid", 6);
		return groupInvalidData;
	}

	@DataProvider(name = "delete")
	public Object[][] deleteData() {
		Object[][] deleteData = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "groupDelete", 5);
		return deleteData;
	}
}
