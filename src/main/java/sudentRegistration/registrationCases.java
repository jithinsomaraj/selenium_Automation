package sudentRegistration;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
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

public class registrationCases {
	
	WebDriver driver;
	registrationPage registrationobj;
	
	@BeforeClass
	//@Parameters({ "adminUserName", "adminPassword" })
	public void setup() {		
		
		driver = Base.getDriver();
		Base.loadstudentUrl(driver);
		registrationobj = new registrationPage(driver);	
	}

	@AfterClass
	public void closeBrowser() {
	     driver.quit();
	}
	
	@AfterMethod
	public void gotohome() {
		driver.get("https://dev.shiken.online/");
	}

	
	@Test (dataProvider = "Register")
	public void register_tc01( String TCID, String TCDescription, String firstName, String lastname, String emailid, String password, String confirmPassword, String successMessage  ) throws InterruptedException 
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		Thread.sleep(5000);
		WebElement signupPage = registrationobj.get_signupLink();
		signupPage.click();
		Thread.sleep(5000);
		WebElement fname= registrationobj.get_firstName();
		WebElement lname= registrationobj.get_lastName();
		WebElement email= registrationobj.get_emailId();
		WebElement pwd= registrationobj.get_password();
		WebElement confirmpwd= registrationobj.get_confirmPassword();
		fname.sendKeys(firstName);
		lname.sendKeys(lastname);
		registrationobj.selectorg();
		email.sendKeys(emailid);
		pwd.sendKeys(password);
		confirmpwd.sendKeys(confirmPassword);
		registrationobj.selectgroup();
		registrationobj.selectdob();
		registrationobj.clickSignup();
		
		String actError = registrationobj.getReactAlertMessage();
		actError= actError.replace("\n", "");
		Assert.assertEquals(actError, successMessage, "Success messages does not match");
		
		Assert.assertTrue(registrationobj.verifyLoginpage(),"Login page not displayed");
		
		
	}
	
	@Test (dataProvider = "Register existing email")
	public void register_tc02_tc05(String TCID, String TCDescription, String firstName, String lastname, String emailid, String password, String confirmPassword, String experror ) throws InterruptedException {
		
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		Thread.sleep(5000);
		WebElement signupPage = registrationobj.get_signupLink();
		signupPage.click();
		Thread.sleep(5000);
		WebElement fname= registrationobj.get_firstName();
		WebElement lname= registrationobj.get_lastName();
		WebElement email= registrationobj.get_emailId();
		WebElement pwd= registrationobj.get_password();
		WebElement confirmpwd= registrationobj.get_confirmPassword();
		fname.sendKeys(firstName);
		lname.sendKeys(lastname);
		registrationobj.selectorg();
		email.sendKeys(emailid);
		pwd.sendKeys(password);
		confirmpwd.sendKeys(confirmPassword);
		registrationobj.selectgroup();
		registrationobj.selectdob();
		registrationobj.clickSignup();
		
		String actError = registrationobj.getReactAlertMessage();
		Assert.assertEquals(actError, experror, "Error messages does not match");
		
	} 
	
	@Test (dataProvider = "Register invalid format email")
	public void register_tc06(String TCID, String TCDescription, String firstName, String lastname, String emailid, String password, String confirmPassword, String experror ) throws InterruptedException {
		
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		Thread.sleep(5000);
		WebElement signupPage = registrationobj.get_signupLink();
		signupPage.click();
		Thread.sleep(5000);
		WebElement fname= registrationobj.get_firstName();
		WebElement lname= registrationobj.get_lastName();
		WebElement email= registrationobj.get_emailId();
		WebElement pwd= registrationobj.get_password();
		WebElement confirmpwd= registrationobj.get_confirmPassword();
		fname.sendKeys(firstName);
		lname.sendKeys(lastname);
		registrationobj.selectorg();
		email.sendKeys(emailid);
		pwd.sendKeys(password);
		confirmpwd.sendKeys(confirmPassword);
		registrationobj.selectgroup();
		registrationobj.selectdob();
		registrationobj.clickSignup();
		
		String actError = registrationobj.getEmailfieldErrortxt();
		Assert.assertEquals(actError, experror, "Error messages does not match");
		
	}
	
	
	@Test (dataProvider = "Register without mandatory")
    public void register_tc07_tc16(String TCID, String TCDescription, String firstName, String lastname, String emailid, String password, String confirmPassword, String experror, String emailerror, String pwderror, String conpwderror, String doberror, String firstnameError, String lastnameError, boolean selorg, boolean selgrp, boolean seldob ) throws InterruptedException 
	{
		
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		Thread.sleep(5000);
		WebElement signupPage = registrationobj.get_signupLink();
		signupPage.click();
		Thread.sleep(5000);
		WebElement fname= registrationobj.get_firstName();
		WebElement lname= registrationobj.get_lastName();
		WebElement email= registrationobj.get_emailId();
		WebElement pwd= registrationobj.get_password();
		WebElement confirmpwd= registrationobj.get_confirmPassword();
		fname.sendKeys(firstName);
		lname.sendKeys(lastname);
		email.sendKeys(emailid);
		pwd.sendKeys(password);
		confirmpwd.sendKeys(confirmPassword);
		
		if (selorg == true) {
		
			registrationobj.selectorg();	
		}
		
		if (selgrp == true) {
			
			registrationobj.selectgroup();	
		}
		
        if (seldob == true) {
			
        	registrationobj.selectdob();	
		}
		

		
        registrationobj.clickSignup();
		String actError = registrationobj.getsuccessmsg();
		Assert.assertNotEquals(actError, experror, "Success message displayed"); 
		
		
		if(emailid == "")
		{
		String actemailError = registrationobj.getEmailfieldErrortxt();
		Assert.assertEquals(actemailError, emailerror, "Error messages does not match");
		}
		
		if(password == "")
		{
			String actpwdError = registrationobj.getPasswordfieldErrortxt();
			Assert.assertEquals(actpwdError, pwderror, "Error messages does not match");
		}
		
		if(confirmPassword == "")
		{
			String actconpwdError = registrationobj.getconPasswordfieldErrortxt();
			Assert.assertEquals(actconpwdError, conpwderror, "Error messages does not match");
		}
		
		if(seldob == false)
		{
			String actdoberror = registrationobj.getdobfielderror();
			Assert.assertEquals(actdoberror, doberror, "Error messages does not match");	
		}
		
		if(firstName.length()>50) 
		{
			String actfirstnameError = registrationobj.getfirstnameError();
			Assert.assertEquals(actfirstnameError, firstnameError, "Error messages does not match");
			
		}
		
		if(lastname.length()>50) 
		{
			String actlastnameError = registrationobj.getlastnameError();
			Assert.assertEquals(actlastnameError, lastnameError, "Error messages does not match");	
		}
		
		
	}
	
	
	@Test (dataProvider = "Register not meeting password criteria")
	public void register_tc18_tc21(String TCID, String TCDescription, String firstName, String lastname, String emailid, String password, String confirmPassword, String experror ) throws InterruptedException {
		
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		Thread.sleep(5000);
		WebElement signupPage = registrationobj.get_signupLink();
		signupPage.click();
		Thread.sleep(5000);
		WebElement fname= registrationobj.get_firstName();
		WebElement lname= registrationobj.get_lastName();
		WebElement email= registrationobj.get_emailId();
		WebElement pwd= registrationobj.get_password();
		WebElement confirmpwd= registrationobj.get_confirmPassword();
		fname.sendKeys(firstName);
		lname.sendKeys(lastname);
		registrationobj.selectorg();
		email.sendKeys(emailid);
		pwd.sendKeys(password);
		confirmpwd.sendKeys(confirmPassword);
		registrationobj.selectgroup();
		registrationobj.selectdob();
		registrationobj.clickSignup();
		
		if((password.length()<8)&&(password.equals(confirmPassword))) {
			
			String actpwdError = registrationobj.getPasswordfieldErrortxt();
			Assert.assertEquals(actpwdError, experror, "Error messages does not match");
			
		}
		else if((password.length()>30)&&(password.equals(confirmPassword))) {
			String actpwdError = registrationobj.getPasswordfieldErrortxt();
			Assert.assertEquals(actpwdError, experror, "Error messages does not match");
		}
		else if((password.length()>=8)&&(password.length()<=30)&&(password.equals(confirmPassword))){
			String actpwdError = registrationobj.getPasswordfieldErrortxt();
			Assert.assertEquals(actpwdError, experror, "Error messages does not match");
		}
		
		if((password.equals(confirmPassword))== false) {
			String actpwdError = registrationobj.getconPasswordfieldErrortxt();
			Assert.assertEquals(actpwdError, experror, "Error messages does not match");
		}
			
	}
	
	@Test (dataProvider = "Back to login")
	public void register_tc22(String TCID, String TCDescription, String firstName, String lastname, String emailid, String password, String confirmPassword, String successMessage  ) throws InterruptedException {
		
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		Thread.sleep(5000);
		WebElement signupPage = registrationobj.get_signupLink();
		signupPage.click();
		Thread.sleep(5000);
		WebElement fname= registrationobj.get_firstName();
		WebElement lname= registrationobj.get_lastName();
		WebElement email= registrationobj.get_emailId();
		WebElement pwd= registrationobj.get_password();
		WebElement confirmpwd= registrationobj.get_confirmPassword();
		fname.sendKeys(firstName);
		lname.sendKeys(lastname);		
		email.sendKeys(emailid);
		pwd.sendKeys(password);
		confirmpwd.sendKeys(confirmPassword);
		registrationobj.selectorg();
		registrationobj.selectgroup();
		registrationobj.selectdob();
		
		registrationobj.clickBacktoLogin();
		
		Assert.assertTrue(registrationobj.verifyLoginpage(),"Login page not displayed");
			
	}
	
	@Test (dataProvider = "Image upload")
	public void register_imgupld(String TCID, String TCDescription, String firstName, String lastname, String emailid, String password, String confirmPassword, String imgpath, String experror, String successMessage, boolean invalidimg  ) throws InterruptedException, AWTException {
		
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		Thread.sleep(5000);
		WebElement signupPage = registrationobj.get_signupLink();
		signupPage.click();
		Thread.sleep(5000);
		WebElement fname= registrationobj.get_firstName();
		WebElement lname= registrationobj.get_lastName();
		WebElement email= registrationobj.get_emailId();
		WebElement pwd= registrationobj.get_password();
		WebElement confirmpwd= registrationobj.get_confirmPassword();
		
		fname.sendKeys(firstName);
		lname.sendKeys(lastname);		
		email.sendKeys(emailid);
		pwd.sendKeys(password);
		confirmpwd.sendKeys(confirmPassword);
		registrationobj.selectorg();
		registrationobj.selectgroup();
		registrationobj.selectdob();		
		registrationobj.uploadimage(imgpath);
		
		if (invalidimg == true) {
			
			String errmsg= registrationobj.getImgerrormsg();
			Assert.assertEquals(errmsg, experror, "Error messages does not match");
		}
			
		String actError = registrationobj.getReactAlertMessage();
		actError= actError.replace("\n", "");
		Assert.assertEquals(actError, successMessage, "Success messages does not match");
		registrationobj.clickSignup();
		Assert.assertTrue(registrationobj.verifyLoginpage(),"Login page not displayed");	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	// ---------------------- DATAPROVIDERS-------------------
	
	@DataProvider(name = "Register")
	public Object[][] registerd() {
		Object[][] validAdminUsers = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"register_tc01", 8);
		/*
		  Object[][] validAdminUsers = { { "TC-01", "", "Joyan test", "Lastname test", "joyanjacob194@gmail.com", "Admin@123", "Admin@123", "受験者は正常に登録されました"
				+ "アカウントを有効にするには、登録されたEメールをご確認ください" } };
				*/
		return validAdminUsers;
	}
	
	@DataProvider(name = "Register existing email")
	public Object[][] registerd2() {
		Object[][] validAdminUsers = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"register_tc02_tc05", 8);
		/*Object[][] validAdminUsers = { { "Joyan test", "Lastname test", "joyanjacob1994@gmail.com", "Admin@123", "Admin@123", "この電子メールは既に利用されています。" },
				{ "Joyan test", "Lastname test", "examadmin2@gmail.com", "Admin@123", "Admin@123", "この電子メールは既に利用されています。" },
				{ "Joyan test", "Lastname test", "examplaner2@gmail.com", "Admin@123", "Admin@123", "この電子メールは既に利用されています。" },
				{ "Joyan test", "Lastname test", "support@weonline.com", "Admin@123", "Admin@123", "この電子メールは既に利用されています。" }};*/
		return validAdminUsers;
	}
	
	@DataProvider(name = "Register invalid format email")
	public Object[][] registerd3() {
		Object[][] validAdminUsers = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"register_tc06nn", 8);
		//Object[][] validAdminUsers = { { "Joyan test", "Lastname test", "joyanjacob10094gmail.com", "Admin@123", "Admin@123", "無効なEメールです" } };
		return validAdminUsers;
	}
	
	@DataProvider(name = "Register without mandatory")
	public Object[][] registerd4() {
		//Object[][] validAdminUsers = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"register_tc07_tc16a", 17);
		Object[][] validAdminUsers = { { "tc_07", "Without Firstname", "", "Lastname test", "joyanjacob1009@4gmail.com", "Admin@123", "Admin@123", "受験者は正常に登録されました", "Eメールが必要です", "パスワードが必要です", "パスワードを再入力してください", "生年月日が必要です", "First name length must not exceed 50 characters.", "Last name length must not exceed 50 characters.", true, true, true},
				{ "tc_08", "Without lastname", "Joyan test", "", "joyanjacob1009@4gmail.com", "Admin@123", "Admin@123", "受験者は正常に登録されました","Eメールが必要です", "パスワードが必要です", "パスワードを再入力してください", "生年月日が必要です", "First name length must not exceed 50 characters.", "Last name length must not exceed 50 characters.",  true, true, true},
				{ "tc_09", "Without email", "Joyan test", "Lastname test", "", "Admin@123", "Admin@123", "受験者は正常に登録されました", "Eメールが必要です", "パスワードが必要です", "パスワードを再入力してください", "生年月日が必要です", "First name length must not exceed 50 characters.", "Last name length must not exceed 50 characters.",  true, true, true},
				{ "tc_10", "Without passowrd", "Joyan test", "Lastname test", "joyanjacob1009@4gmail.com", "", "Admin@123", "受験者は正常に登録されました", "Eメールが必要です", "パスワードが必要です", "パスワードを再入力してください", "生年月日が必要です", "First name length must not exceed 50 characters.", "Last name length must not exceed 50 characters.",  true, true, true},
				{ "tc_11", "Without confirm password", "Joyan test", "Lastname test", "joyanjacob1009@4gmail.com", "Admin@123", "", "受験者は正常に登録されました","Eメールが必要です", "パスワードが必要です", "パスワードを再入力してください", "生年月日が必要です", "First name length must not exceed 50 characters.", "Last name length must not exceed 50 characters.",  true, true, true},
				{ "tc_12", "TC_desc", "Joyan test", "Lastname test", "joyanjacob1009@4gmail.com", "Admin@123", "Admin@123", "受験者は正常に登録されました","Eメールが必要です", "パスワードが必要です", "パスワードを再入力してください", "生年月日が必要です", "First name length must not exceed 50 characters.", "Last name length must not exceed 50 characters.",  true, false, true},
				{ "tc_13", "TC_desc", "Joyan test", "Lastname test", "joyanjacob1009@4gmail.com", "Admin@123", "Admin@123", "受験者は正常に登録されました","Eメールが必要です", "パスワードが必要です", "パスワードを再入力してください", "生年月日が必要です", "First name length must not exceed 50 characters.", "Last name length must not exceed 50 characters.",  true, true, false},
				{ "tc_14", "More than maximum number of characters in the first name field", "ThisisafiftyonecharacterstringThisisafiftyonecharac", "Lastname test", "joyanjacob1009@4gmail.com", "Admin@123", "Admin@123", "受験者は正常に登録されました","Eメールが必要です", "パスワードが必要です", "パスワードを再入力してください", "生年月日が必要です", "First name length must not exceed 50 characters.", "Last name length must not exceed 50 characters.",  true, true, true},
				{ "tc_15", "More than maximum number of characters in the last name field", "Joyan test", "ThisisafiftyonecharacterstringThisisafiftyonecharac", "joyanjacob1009@4gmail.com", "Admin@123", "Admin@123", "受験者は正常に登録されました","Eメールが必要です", "パスワードが必要です", "パスワードを再入力してください", "生年月日が必要です", "First name length must not exceed 50 characters.", "Last name length must not exceed 50 characters.",  true, true, true}};
		
		return validAdminUsers;
	}
	
	@DataProvider(name = "Register not meeting password criteria")
	public Object[][] registerd5() {
		Object[][] validAdminUsers = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"register_tc18_tc21", 8);
		/*Object[][] validAdminUsers = { { "Joyan test", "Lastname test", "joyanjacob194@gmail.com", "Admin12", "Admin12", "パスワードは 8 文字以上必要です" },
				{ "Joyan test", "Lastname test", "joyanjacob194@gmail.com", "Thisisafiftyone@1aracterstringT1234", "Thisisafiftyone@1aracterstringT1234", "パスワードは 30 文字以内で入力してください" },
				{ "Joyan test", "Lastname test", "joyanjacob194@gmail.com", "adminnocaps", "adminnocaps", "パスワードには、 8 ～ 30 文字、大文字、小文字、数字、および特殊文字を少なくとも 1 文字必要です" },
				{ "Joyan test", "Lastname test", "joyanjacob194@gmail.com", "admin1nocaps", "admin1nocaps", "パスワードには、 8 ～ 30 文字、大文字、小文字、数字、および特殊文字を少なくとも 1 文字必要です" },
				{ "Joyan test", "Lastname test", "joyanjacob194@gmail.com", "admin1@nocaps", "admin1@nocaps", "パスワードには、 8 ～ 30 文字、大文字、小文字、数字、および特殊文字を少なくとも 1 文字必要です" },
				{ "Joyan test", "Lastname test", "joyanjacob194@gmail.com", "Admin@123", "Joyan@1234", "確認用パスワードはパスワードと同じでなければなりません" }};
				*/
		return validAdminUsers;
	}
	
	@DataProvider(name = "Back to login")
	public Object[][] registerd6() {
		Object[][] validAdminUsers = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"register_tc22", 8);
		//Object[][] validAdminUsers = { { "Joyan test", "Lastname test", "joyanjacob19@gft.com", "Admin@123", "Admin@123", "" } };
		return validAdminUsers;
	}
	
	@DataProvider(name = "Image upload")
	public Object[][] registerd7() {
		//Object[][] validAdminUsers = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"register_imgupld", 11);
		Object[][] validAdminUsers = { {"tc23", "Verify whether if the image upload functionality is working as expected.", "Joyan test", "Lastname test", "joyanjacob194@gmail.com", "Admin@123", "Admin@123", "expected.jpg", "画像をアップロードしてください", "受験者は正常に登録されました\" + \"アカウントを有効にするには、登録されたEメールをご確認ください", false },
				{ "tc_24", "Verify whether if proper error message is displayed when an invalid image is uploaded.", "Joyan test", "Lastname test", "joyanjacob194@gmail.com", "Admin@123", "Admin@123", "invalid.txt", "画像をアップロードしてください", "受験者は正常に登録されました" + "アカウントを有効にするには、登録されたEメールをご確認ください", true } };
		return validAdminUsers;
	}
	
	
	


}
