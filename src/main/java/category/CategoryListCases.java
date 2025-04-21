package category;

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
import group.GroupList;
import login.loginPage;
import projectBase.Base;
import questionBankList.QuestionBankListPage;
import utils.ReadExcel;

public class CategoryListCases {
	
	WebDriver driver ;
	loginPage loginpage;
	CategoryList CategoryList;
	QuestionBankListPage QuestionBankListPage;
	WebDriverWait wait;

	@BeforeClass
	@Parameters({ "adminUserName", "adminPassword" })
	public void openbrowserandLogin(String email, String password) {
		driver = Base.getDriver();
		loginpage = new loginPage(driver);
		CategoryList = new CategoryList(driver);
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

	@Test(dataProvider = "search", priority = 21) // TC_03
	public void searchCategory(String TCID, String TCDescription, String searchTerm, String resultExist) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		Boolean isResult = false;
		if (resultExist.equalsIgnoreCase("TRUE")){
			isResult = true;
		}else if(resultExist.equalsIgnoreCase("FALSE")) {
			isResult = false;
		}
		if (!driver.findElements(CategoryList.closeButtonBy()).isEmpty()) {
			CategoryList.clickCloseButton();
		}
		CategoryList.clickCategoryListInMenu();
		List<String> result = CategoryList.search(searchTerm, isResult);
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

	@Test(dataProvider = "Create-Category-Valid", priority = 22) // TC_04,TC_05,TC_07,TC_08,TC_09,TC_10,TC_11,
	public void createCategoryValid(String TCID, String TCDescription, String name, String description) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
		LocalDateTime now = LocalDateTime.now();
		name = name + "_" + dtf.format(now);
		if (!driver.findElements(CategoryList.closeButtonBy()).isEmpty()) {
			CategoryList.clickCloseButton();
		}
		CategoryList.clickCategoryListInMenu();
		CategoryList.NavigateToCategoryCreate();
		CategoryList.addCategory(name, description);
		String alert = CategoryList.getReactAlertMessage();
		Assert.assertEquals(alert, "カテゴリが正常に作成されました", "Alert message does not match");
		Assert.assertTrue(CategoryList.IsCategoryPresent(name), "Group is not present in list");
	}

	@Test(dataProvider = "Create-Category-Invalid", priority = 23) // TC_06,TC_12,TC_13
	public void createCategoryInvalid(String TCID, String TCDescription, String name, String description, String expError) {
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		if (!driver.findElements(CategoryList.closeButtonBy()).isEmpty()) {
			CategoryList.clickCloseButton();
		}
		String actError = "";
		CategoryList.clickCategoryListInMenu();
		CategoryList.NavigateToCategoryCreate();
		CategoryList.addCategory(name, description);
		if ("".equals(name)) {
			actError = CategoryList.getTitle().getAttribute("validationMessage");
		}
		if (name.length() > 100) {
			actError = CategoryList.getTitleError();
		}
		if (description.length() > 255) {
			actError = CategoryList.getDescError();
		}
		CategoryList.clickCloseButton();
		Assert.assertEquals(actError, expError, "Error message does not match");
	}

	@Test(dataProvider = "Edit-Category-Valid", priority = 24) // TC_14,TC_15,TC_16,TC_17,TC_18,TC_19,TC_21,TC_22
	public void editCategoryValid(String TCID, String TCDescription, String extname, String newName, String description) {
		// Note: Enter the same name in Existing and New if name and description
		// should not be changed
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		if (!driver.findElements(CategoryList.closeButtonBy()).isEmpty()) {
			CategoryList.clickCloseButton();
		}
		CategoryList.clickCategoryListInMenu();
		CategoryList.navigateToCategoryEdit(extname);
		wait.until(ExpectedConditions.textToBePresentInElement(CategoryList.getTitle(), extname));
		if (!extname.equals(newName)) {
			CategoryList.setTitle(newName);
		}
		if (!CategoryList.getDescText().equals(description)) {
			CategoryList.setDesc(description);
		}
		CategoryList.clickSaveButton();
		String alert = CategoryList.getReactAlertMessage();
		Assert.assertEquals(alert, "カテゴリが正常に更新されました", "Alert message does not match");
		if (!extname.equals(newName)) {
			Assert.assertTrue(CategoryList.IsCategoryPresent(newName), "Group is not present");
		}
	}

	@Test(dataProvider = "Edit-Category-inValid", priority = 25) // TC_20
	public void editCategoryInvalid(String TCID, String TCDescription, String extname, String newName, String description, String expError) {
		// Note: Leave the data in the data provider as "", if the data need be removed.
		// Note: Enter the same name in Existing and New if name and description
		// should not be changed
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		if (!driver.findElements(CategoryList.closeButtonBy()).isEmpty()) {
			CategoryList.clickCloseButton();
		}
		String actError = "";
		CategoryList.clickCategoryListInMenu();
		CategoryList.navigateToCategoryEdit(extname);
		wait.until(ExpectedConditions.textToBePresentInElement(CategoryList.getTitle(), extname));
		if (!extname.equals(newName)) {
			CategoryList.setTitle(newName);
		}
		if (!CategoryList.getDescText().equals(description)) {
			CategoryList.setDesc(description);
		}
		CategoryList.clickSaveButton();

		if ("".equals(newName) || newName.length() > 100) {
			actError = CategoryList.getTitleError();
		}
		if (description.length() > 255) {
			actError = CategoryList.getDescError();
		}
		CategoryList.clickCloseButton();
		Assert.assertEquals(actError, expError, "Error message does not match");
		String Alert = QuestionBankListPage.getReactAlertMessage();
		if (!"".equals(Alert)) {
			Assert.fail(Alert);
		}
	}

	@Test(dataProvider = "delete", priority = 26) // TC_23,TC_24,TC_25
	public void deleteCategory(String TCID, String TCDescription, String name, String Message, String Deletable) {
		Boolean isDeletable = false;
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		if (Deletable.equalsIgnoreCase("TRUE")){
			isDeletable = true;
		}else if(Deletable.equalsIgnoreCase("FALSE")) {
			isDeletable = false;
		}
		if (!driver.findElements(CategoryList.closeButtonBy()).isEmpty()) {
			CategoryList.clickCloseButton();
		}
		CategoryList.clickCategoryListInMenu();
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CategoryList.getCategoryTableBy()));
		CategoryList.deleteCategory(name);
		String Alert = QuestionBankListPage.getReactAlertMessage();
		Assert.assertEquals(Alert, Message, "Alert Messages does not match");
		if (isDeletable) {
			Assert.assertFalse(CategoryList.IsCategoryPresent(name), "group is not deleted");
		}
	}

	// ---------------------- DATAPROVIDERS-------------------

	@DataProvider(name = "search")
	public Object[][] searchInput() {
		Object[][] searchInput = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "searchCategory", 4);
		return searchInput;
	}

	@DataProvider(name = "Create-Category-Valid")
	public Object[][] categoryValidData() {
		Object[][] categoryValidData = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "createCategoryValid", 4);
		return categoryValidData;
	}

	@DataProvider(name = "Create-Category-Invalid")
	public Object[][] categoryinValidData() {
		Object[][] categoryInvalidData = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "createCategoryInvalid", 5);
		return categoryInvalidData;
	}

	@DataProvider(name = "Edit-Category-Valid")
	public Object[][] editCategoryValidData() {
		Object[][] categoryValidData = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "editCategoryValid", 5);
		return categoryValidData;
	}

	@DataProvider(name = "Edit-Category-inValid")
	public Object[][] editCategoryInvalidData() {
		Object[][] categoryInvalidData = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "editCategoryInvalid", 6);
		return categoryInvalidData;
	}

	@DataProvider(name = "delete")
	public Object[][] deleteData() {
		Object[][] deleteData = ReadExcel.ReadTestData(this.getClass().getSimpleName(), "deleteCategory", 5);
		return deleteData;
	}

}
