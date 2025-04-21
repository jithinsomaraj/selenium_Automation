package questions;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import utils.ReadExcel;

public class QuestionsCases {
	
	WebDriver driver ;// = Base.chromeSetup();
	Questionlistpage listpageobj; // = new Questionlistpage(driver);
	QuestionCreatepage createpageobj;// = new QuestionCreatepage(driver);
    loginPage loginpage; // = new loginPage(driver);
    QuestionEditpage editpageobj; // = new QuestionEditpage(driver);
	
    @BeforeClass
	@Parameters({ "adminUserName", "adminPassword" })
	public void setup(String email, String password) {
		driver = Base.getDriver();
		loginpage = new loginPage(driver);
		Base.loadadminUrl(driver);
		listpageobj = new Questionlistpage(driver);
		createpageobj = new QuestionCreatepage(driver);
		editpageobj = new QuestionEditpage(driver);
		loginpage.login("examadmin2@gmail.com", "Admin@123");
	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

    
	/*@BeforeTest
	public void setup() {
		
		Base.loadadminUrl(driver);
		loginpage.login("examadmin2@gmail.com", "Admin@123");
		listpageobj.checkLogin();	

	}*/
	
	
	@Test (dataProvider = "Single answer question type")
	public void a_create_SingleAnswer(String TCID, String TCDescription, String qtype, String questionTitle, String questionDescription, String optionCount, String marks) throws InterruptedException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
	    listpageobj.navigate_questionsPage();
		listpageobj.clickCreate();
		createpageobj.selectCategory();
		createpageobj.selectgroup();
		createpageobj.selectQuestionType(qtype);
		createpageobj.selectDifficulty();
		createpageobj.selectQuestionBank();
		createpageobj.enterQuestiontitle(questionTitle);
		createpageobj.enterQuestiondescription(questionDescription);
		
		int optionCountn= Integer.parseInt(optionCount);
		
		if(optionCountn>4) {
			createpageobj.addAnsweroption();
		}
		
		if(optionCountn<4) {
			createpageobj.deleteAnsweroption();
		}
		
		createpageobj.enterAnsweroptions(optionCountn);
		createpageobj.enterMark(marks);
		createpageobj.selectCorrectoption();
		createpageobj.clickCreate();
		
		String actSuccess = createpageobj.getReactAlertMessage();
		Assert.assertEquals(actSuccess, "問題を正常に作成しました", "Success messages does not match");
		
		listpageobj.checkifListpage();
	}
	
	@Test (dataProvider = "Multiple answer question type")
	public void b_create_MultipleAnswer(String TCID, String TCDescription, String qtype, String questionTitle, String questionDescription, String optionCount, String marks) throws InterruptedException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
	    listpageobj.navigate_questionsPage();
		listpageobj.clickCreate();
		createpageobj.selectCategory();
		createpageobj.selectgroup();
		createpageobj.selectQuestionType(qtype);
		createpageobj.selectDifficulty();
		createpageobj.selectQuestionBank();
		createpageobj.enterQuestiontitle(questionTitle);
		createpageobj.enterQuestiondescription(questionDescription);
		
		int optionCountn= Integer.parseInt(optionCount);
		
		if(optionCountn>4) {
			createpageobj.addAnsweroption();
		}
		
		if(optionCountn<4) {
			createpageobj.deleteAnsweroption();
		}
		
		createpageobj.enterAnsweroptions(optionCountn);
		createpageobj.enterMark(marks);
		createpageobj.selectCorrectoption();
		createpageobj.clickCreate();
		
		String actSuccess =  createpageobj.getReactAlertMessage();
		Assert.assertEquals(actSuccess, "問題を正常に作成しました", "Success messages does not match");
		
		listpageobj.checkifListpage();
	}  
	
	@Test (dataProvider = "Single and Multiple answer question type- Cases")
	public void c_create_SingleMultipleAnswer_tc02(String TCID, String TCDescription, String qtype, String questionTitle, String questionDescription, String optionCount, String marks, String blankOption, String correctanswer, String enterCategory, String enterGroup, String enterDifficulty) throws InterruptedException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		boolean blankOption_n = false;
		boolean correctanswer_n= false;
		boolean enterCategory_n= false;
		boolean enterGroup_n= false; 
		boolean enterDifficulty_n= false;
		listpageobj.navigate_questionsPage();
		listpageobj.clickCreate();
		Thread.sleep(2000);
		if (blankOption.equalsIgnoreCase("TRUE")){
			blankOption_n = true;
			}else if(blankOption.equalsIgnoreCase("FALSE")) {
				blankOption_n = false;
			}
		if (correctanswer.equalsIgnoreCase("TRUE")){
			correctanswer_n = true;
			}else if(correctanswer.equalsIgnoreCase("FALSE")) {
				correctanswer_n = false;
			}
		if (enterCategory.equalsIgnoreCase("TRUE")){
			enterCategory_n = true;
			}else if(enterCategory.equalsIgnoreCase("FALSE")) {
				enterCategory_n = false;
			}
		if (enterGroup.equalsIgnoreCase("TRUE")){
			enterGroup_n = true;
			}else if(enterGroup.equalsIgnoreCase("FALSE")) {
				enterGroup_n = false;
			}
		if (enterGroup.equalsIgnoreCase("TRUE")){
			enterGroup_n = true;
			}else if(enterGroup.equalsIgnoreCase("FALSE")) {
				enterGroup_n = false;
			}
		if (enterDifficulty.equalsIgnoreCase("TRUE")){
			enterDifficulty_n = true;
			}else if(enterDifficulty.equalsIgnoreCase("FALSE")) {
				enterDifficulty_n = false;
			}
		
		if(enterCategory_n == true){
		createpageobj.selectCategory();
		}
		
		if(enterGroup_n == true) {
		createpageobj.selectgroup();
		}
		
		if(enterDifficulty_n == true) {
		createpageobj.selectDifficulty();
		}
		
		createpageobj.selectQuestionBank();
		createpageobj.enterQuestiontitle(questionTitle);
		createpageobj.enterQuestiondescription(questionDescription);
		int optionCountn= Integer.parseInt(optionCount);
		if (blankOption_n == true) {
			optionCountn = optionCountn-1;
		}
		
		if(qtype != "") {
	    createpageobj.selectQuestionType(qtype);
		createpageobj.enterAnsweroptions(optionCountn);
		}
		
		createpageobj.enterMark(marks);
		
		if((correctanswer_n == true) && (qtype != "")) {
			createpageobj.selectCorrectoption();
		}
		
		createpageobj.clickCreate();
		
		if (blankOption_n == true) {
		String acterror =  createpageobj.getReactAlertMessage();
		Assert.assertEquals(acterror, "空白の選択肢は使用できません", "Error messages does not match");
		}
		
		if((correctanswer_n == true)||(questionTitle == "")||(qtype == "")||(enterCategory_n == true)||(enterGroup_n == true)||(enterGroup_n == true)) 
		{
			boolean success = createpageobj.checkifsuccessDisp();
			Assert.assertFalse(success);
	 	}
		
		if (marks == "") {
			String acterror =  createpageobj.getReactAlertMessage();
			Assert.assertEquals(acterror, "点数は1以上でなければなりません", "Error messages does not match");
			}
		
		
	}
	
	
	
	@Test (dataProvider = "Single and Multiple answer question type preview")
	public void e_create_Single_Multiple_AnswerPreview(String TCID, String TCDescription, String qtype, String questionTitle, String questionDescription, String optionCount, String marks) throws InterruptedException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		int optionCountn= Integer.parseInt(optionCount);
	    listpageobj.navigate_questionsPage();
		listpageobj.clickCreate();
		createpageobj.selectCategory();
		createpageobj.selectgroup();
		createpageobj.selectQuestionType(qtype);
		createpageobj.selectDifficulty();
		createpageobj.selectQuestionBank();
		createpageobj.enterQuestiontitle(questionTitle);
		createpageobj.enterQuestiondescription(questionDescription);
		createpageobj.enterAnsweroptions(optionCountn);
		createpageobj.enterMark(marks);
		createpageobj.selectCorrectoption();
		createpageobj.clickPreviewbtn_Create();
		Thread.sleep(2000);
		//To check Question title text
		String Act_questiontitle = createpageobj.getPreviewQuestiontitle();
		Assert.assertEquals("Q . "+questionTitle,Act_questiontitle, "Question title does not match");
		
		//To check Question decription text
		String Act_questionDesc = createpageobj.getPreviewQuestionDesc();
		Assert.assertEquals(Act_questionDesc.trim(),questionDescription, "Question description does not match");
		
		//To check answer options text
		String[] actAnsweroptText = new String[4];
		actAnsweroptText = createpageobj.getPreviewAnsweroptions();
		for(int i=0;i<4;i++) {
			Assert.assertEquals(actAnsweroptText[i],"Answer option "+(i+1), "The answer options displayed does not match");
		}
		
		if(qtype == "Single Answer") {
		//To check if radio buttons displayed
		String[] radiobtn = new String[4];
		radiobtn = createpageobj.getInputTypesDisplayed();
		for(int i=0;i<4;i++) {
			Assert.assertEquals(radiobtn[i],"radio", "Radio buttons are not displayed");
		   }
		}
	
		if(qtype == "Multiple Answer") {
			//To check if checkboxes are displayed
			String[] checkboxbtn = new String[4];
			checkboxbtn = createpageobj.getInputTypesDisplayed();
			for(int i=0;i<4;i++) {
				Assert.assertEquals(checkboxbtn[i],"checkbox", "Check boxes are not displayed");	
			}
		}

		createpageobj.clickClosepreview();
		
	}
	
	@Test (dataProvider = "Single image answer question type")
	public void f_create_SingleimageAnswer(String TCID, String TCDescription, String qtype, String questionTitle, String questionDescription, String optionCount, String marks) throws AWTException, InterruptedException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
	    listpageobj.navigate_questionsPage();
		listpageobj.clickCreate();
		createpageobj.selectCategory();
		createpageobj.selectgroup();
		createpageobj.selectQuestionType(qtype);
		createpageobj.selectDifficulty();
		createpageobj.selectQuestionBank();
		createpageobj.enterQuestiontitle(questionTitle);
		createpageobj.enterQuestiondescription(questionDescription);
		int optionCountn= Integer.parseInt(optionCount);
		if(optionCountn>4) {
			createpageobj.addAnsweroption();
		}
		
		if(optionCountn<4) {
			createpageobj.deleteImageAnsweroption();
		}
		
		createpageobj.uploadImageAnsweroption(optionCountn);
		createpageobj.enterMark(marks);
		createpageobj.selectCorrectoption();
		createpageobj.clickCreate();
		
		String actSuccess =  createpageobj.getReactAlertMessage();
		Assert.assertEquals(actSuccess, "問題を正常に作成しました", "Success messages does not match");
		
		listpageobj.checkifListpage();
	}
	
	
	@Test (dataProvider = "Multiple image answer question type")
	public void g_create_MultipleimageAnswer(String TCID, String TCDescription, String qtype, String questionTitle, String questionDescription, String optionCount, String marks) throws AWTException, InterruptedException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
	    listpageobj.navigate_questionsPage();
		listpageobj.clickCreate();
		createpageobj.selectCategory();
		createpageobj.selectgroup();
		createpageobj.selectQuestionType(qtype);
		createpageobj.selectDifficulty();
		createpageobj.selectQuestionBank();
		createpageobj.enterQuestiontitle(questionTitle);
		createpageobj.enterQuestiondescription(questionDescription);
		int optionCountn= Integer.parseInt(optionCount);
		if(optionCountn>4) {
			createpageobj.addAnsweroption();
		}
		
		if(optionCountn<4) {
			createpageobj.deleteImageAnsweroption();
		}
		
		createpageobj.uploadImageAnsweroption(optionCountn);
		createpageobj.enterMark(marks);
		createpageobj.selectCorrectoption();
		createpageobj.clickCreate();
		
		String actSuccess =  createpageobj.getReactAlertMessage();
		Assert.assertEquals(actSuccess, "問題を正常に作成しました", "Success messages does not match");
		
		listpageobj.checkifListpage();
	}
	
	@Test (dataProvider = "Single and Multiple image answer question type- Cases")
	public void h_create_SingleMultiple_imageAnswer_cases(String TCID, String TCDescription, String qtype, String questionTitle, String questionDescription, String optionCount, String marks, String blankOption, String correctanswer,  String enterCategory, String enterGroup, String enterDifficulty) throws InterruptedException, AWTException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		int optionCountn= Integer.parseInt(optionCount);
		boolean blankOption_n = false;
		boolean correctanswer_n= false;
		boolean enterCategory_n= false;
		boolean enterGroup_n= false; 
		boolean enterDifficulty_n= false;
		
		if (blankOption.equalsIgnoreCase("TRUE")){
			blankOption_n = true;
			}else if(blankOption.equalsIgnoreCase("FALSE")) {
				blankOption_n = false;
			}
		if (correctanswer.equalsIgnoreCase("TRUE")){
			correctanswer_n = true;
			}else if(correctanswer.equalsIgnoreCase("FALSE")) {
				correctanswer_n = false;
			}
		if (enterCategory.equalsIgnoreCase("TRUE")){
			enterCategory_n = true;
			}else if(enterCategory.equalsIgnoreCase("FALSE")) {
				enterCategory_n = false;
			}
		if (enterGroup.equalsIgnoreCase("TRUE")){
			enterGroup_n = true;
			}else if(enterGroup.equalsIgnoreCase("FALSE")) {
				enterGroup_n = false;
			}
		if (enterGroup.equalsIgnoreCase("TRUE")){
			enterGroup_n = true;
			}else if(enterGroup.equalsIgnoreCase("FALSE")) {
				enterGroup_n = false;
			}
		if (enterDifficulty.equalsIgnoreCase("TRUE")){
			enterDifficulty_n = true;
			}else if(enterDifficulty.equalsIgnoreCase("FALSE")) {
				enterDifficulty_n = false;
			}

		listpageobj.navigate_questionsPage();
		listpageobj.clickCreate();
		Thread.sleep(2000);
		if(enterCategory_n == true){
			createpageobj.selectCategory();
			}
			
		if(enterGroup_n == true) {
			createpageobj.selectgroup();
			}
			
		if(enterDifficulty_n == true) {
			createpageobj.selectDifficulty();
			}
		
		createpageobj.selectQuestionBank();
		createpageobj.enterQuestiontitle(questionTitle);
		createpageobj.enterQuestiondescription(questionDescription);
		
		if (blankOption_n == true) {
			optionCountn = optionCountn-1;
		}
		
		if(qtype != "") {
	    createpageobj.selectQuestionType(qtype);
		createpageobj.uploadImageAnsweroption(optionCountn);
		}
		
		createpageobj.enterMark(marks);
		
		if((correctanswer_n == true) && (qtype != "")) {
			createpageobj.selectCorrectoption();
		}
		
		createpageobj.clickCreate();
		
		if (blankOption_n == true) {
			boolean success = createpageobj.checkifsuccessDisp();
			Assert.assertFalse(success);
		}
		
		if((correctanswer_n == false)||(questionTitle == "")||(qtype == "")||(enterCategory_n == false)||(enterGroup_n == false)||(enterGroup_n == false)) 
		{
			boolean success1 = createpageobj.checkifsuccessDisp();
			Assert.assertFalse(success1);
	 	}
		
		if (marks == "") {
			String acterror =  createpageobj.getReactAlertMessage();
			Assert.assertEquals(acterror, "点数は1以上でなければなりません", "Error messages does not match");
			}
	
	}
	
	@Test (dataProvider = "Single and multiple image answer question type preview")
	public void i_create_SingleimageAnswer(String TCID, String TCDescription, String qtype, String questionTitle, String questionDescription, String optionCount, String marks) throws AWTException, InterruptedException, IOException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
	    BufferedImage[] actualImage = new BufferedImage[5];
	    BufferedImage[] expectedImage = new BufferedImage[5];
	    int optionCountn= Integer.parseInt(optionCount);
	    listpageobj.navigate_questionsPage();
		listpageobj.clickCreate();
		createpageobj.selectCategory();
		createpageobj.selectgroup();
		createpageobj.selectQuestionType(qtype);
		createpageobj.selectDifficulty();
		createpageobj.selectQuestionBank();
		createpageobj.enterQuestiontitle(questionTitle);
		createpageobj.enterQuestiondescription(questionDescription);
		createpageobj.uploadImageAnsweroption(optionCountn);
		createpageobj.enterMark(marks);
		createpageobj.selectCorrectoption();
		createpageobj.clickPreviewbtn_Create();
		
		Thread.sleep(2000);
		//To check Question title text
		String Act_questiontitle = createpageobj.getPreviewQuestiontitle();
		Assert.assertEquals("Q . "+questionTitle,Act_questiontitle, "Question title does not match");
		
		//To check Question decription text
		String Act_questionDesc = createpageobj.getPreviewQuestionDesc();
		Assert.assertEquals(Act_questionDesc.trim(),questionDescription, "Question description does not match");
		
		if(qtype == "Single Answer") {
		//To check if radio buttons displayed
		String[] radiobtn = new String[4];
		radiobtn = createpageobj.getInputTypesDisplayed();
		for(int i=0;i<4;i++) {
			Assert.assertEquals(radiobtn[i],"radio", "Radio buttons are not displayed");
		   }
		}
	
		if(qtype == "Multiple Answer") {
			//To check if checkboxes are displayed
			String[] checkboxbtn = new String[4];
			checkboxbtn = createpageobj.getInputTypesDisplayed();
			for(int i=0;i<4;i++) {
				Assert.assertEquals(checkboxbtn[i],"checkbox", "Check boxes are not displayed");	
			}
		}

		actualImage = createpageobj.compareImageanswerOpt(qtype);
		
		 int j=1;
		 for(int i=0;i<4;i++) {
			 
			 expectedImage[j] = ImageIO.read(new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Expected_images\\Expected image " +j+ ".png"));
			 ImageDiffer imgDiff = new ImageDiffer();
		     ImageDiff diff = imgDiff.makeDiff(actualImage[j], expectedImage[j]);
		     Assert.assertFalse(diff.hasDiff(),"Images are not Same");
		     j++;
		 }
		 
		 createpageobj.clickClosepreview();
				
	}
	
   
	@Test (dataProvider = "Text answer and image question type")
	public void j_create_Text_ImageAnswer(String TCID, String TCDescription, String qtype, String questionTitle, String questionDescription, String optionCount, String marks, String withDescimg) throws InterruptedException, AWTException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		//int optionCountn= Integer.parseInt(optionCount);
		boolean withDescimg_n = false;
		if (withDescimg.equalsIgnoreCase("TRUE")){
			withDescimg_n = true;
			}else if(withDescimg.equalsIgnoreCase("FALSE")) {
				withDescimg_n = false;
			}
		
	    listpageobj.navigate_questionsPage();
		listpageobj.clickCreate();
		createpageobj.selectCategory();
		createpageobj.selectgroup();
		createpageobj.selectQuestionType(qtype);
		createpageobj.selectDifficulty();
		createpageobj.selectQuestionBank();
		createpageobj.enterQuestiontitle(questionTitle);
		createpageobj.enterQuestiondescription(questionDescription);
		createpageobj.enterMark(marks);
		if (withDescimg_n == true) {
			createpageobj.uploadImgDesc();
		}
		System.out.println(qtype);
		//if(qtype == "Text Input") 
		if(qtype.equals("Text Input")){
		    System.out.println("abc1");
			createpageobj.enterCorrectAnswer();
		}
		
		createpageobj.clickCreate();
		String actSuccess =  createpageobj.getReactAlertMessage();
		Assert.assertEquals(actSuccess, "問題を正常に作成しました", "Success messages does not match");
		
		listpageobj.checkifListpage();
	}
	
	@Test (dataProvider = "Text answer and image question type")
	public void k_create_Text_ImageAnswerPreview(String TCID, String TCDescription, String qtype, String questionTitle, String questionDescription, String optionCount, String marks, String withDescimg) throws InterruptedException, AWTException, IOException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		 BufferedImage actualImagedesc;
		 BufferedImage expectedImagedesc;
		 //int optionCountn= Integer.parseInt(optionCount);
		 boolean withDescimg_n = false;
			if (withDescimg.equalsIgnoreCase("TRUE")){
				withDescimg_n = true;
				}else if(withDescimg.equalsIgnoreCase("FALSE")) {
					withDescimg_n = false;
				}
	    listpageobj.navigate_questionsPage();
		listpageobj.clickCreate();
		createpageobj.selectCategory();
		createpageobj.selectgroup();
		createpageobj.selectQuestionType(qtype);
		createpageobj.selectDifficulty();
		createpageobj.selectQuestionBank();
		createpageobj.enterQuestiontitle(questionTitle);
		createpageobj.enterQuestiondescription(questionDescription);
		createpageobj.enterMark(marks);
		
		if (withDescimg_n == true) {
			createpageobj.uploadImgDesc();
		}
		
		if(qtype == "Text Input") {
			createpageobj.enterCorrectAnswer();
		}
		
		createpageobj.clickPreviewbtn_Create();
		String Act_questiontitle = createpageobj.getPreviewTIQuestiontitle();
		Assert.assertEquals(Act_questiontitle,"Q . "+questionTitle, "Question title does not match");
		
		//To check Question decription text
		String Act_questionDesc = createpageobj.getPreviewTIQuestionDesc();
		Assert.assertEquals(Act_questionDesc.trim(),questionDescription, "Question description does not match");
		
		if(qtype == "Text Input") {
			
			createpageobj.ifTextareaisDisplayed();			
		}
		
		if(qtype == "Image Type") {
			
			String btn = createpageobj.ifUploadbtnDisplayed();
			Assert.assertEquals(btn, "button", "Upload button is not displayed.");
		}
		
		if (withDescimg_n == true) {
			actualImagedesc = createpageobj.compareTIImgDescription();
			expectedImagedesc = ImageIO.read(new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Expected_images\\Expected image desc.png"));
			 ImageDiffer imgDiff = new ImageDiffer();
		     ImageDiff diff = imgDiff.makeDiff(actualImagedesc, expectedImagedesc);
		     Assert.assertFalse(diff.hasDiff(),"Images are not Same");
			
		}
		
		createpageobj.clickClosepreview();
	} 
	
	@Test (dataProvider = "Questions with image description")
	public void l_create_questionIMGdec(String TCID, String TCDescription, String qtype, String questionTitle, String questionDescription, String optionCount, String marks) throws InterruptedException, AWTException, IOException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		 BufferedImage[] actualanswerImage = new BufferedImage[5];
		 BufferedImage[] expectedImage = new BufferedImage[5];
		 BufferedImage actualImagedesc;
		 BufferedImage expectedImagedesc;
		 int optionCountn= Integer.parseInt(optionCount);
	    listpageobj.navigate_questionsPage();
		listpageobj.clickCreate();
		createpageobj.selectCategory();
		createpageobj.selectgroup();
		createpageobj.selectQuestionType(qtype);
		createpageobj.selectDifficulty();
		createpageobj.selectQuestionBank();
		createpageobj.enterQuestiontitle(questionTitle);
		createpageobj.enterQuestiondescription(questionDescription);
		
		createpageobj.uploadImgDesc();

		
		if((qtype == "Single Answer")||(qtype == "Multiple Answer")) {
		createpageobj.enterAnsweroptions(optionCountn);
		createpageobj.selectCorrectoption();
		}
		
		if((qtype =="Single Answer Image")||(qtype == "Multiple Answer Image")) {
			createpageobj.uploadImageAnsweroption(optionCountn);
			createpageobj.selectCorrectoption();
		}
		
		createpageobj.enterMark(marks);
		
		
		
		createpageobj.clickPreviewbtn_Create();
		Thread.sleep(3000);
		//To check Question title text
		String Act_questiontitle = createpageobj.getPreviewQuestiontitle();
		Assert.assertEquals(Act_questiontitle,"Q . "+questionTitle, "Question title does not match");
		
		//To check Question decription text
		String Act_questionDesc = createpageobj.getPreviewQuestionDesc();
		Assert.assertEquals(Act_questionDesc.trim(),questionDescription, "Question description does not match");
		
		//To check answer options text
		if((qtype == "Single Answer")||(qtype == "Multiple Answer")) {
		String[] actAnsweroptText = new String[4];
		actAnsweroptText = createpageobj.getPreviewAnsweroptions();
		for(int i=0;i<4;i++) {
			Assert.assertEquals(actAnsweroptText[i],"Answer option "+(i+1), "The answer options displayed does not match");
		}
		}
		
		//To check img options preview
		if((qtype =="Single Answer Image")||(qtype == "Multiple Answer Image")) {
		actualanswerImage = createpageobj.compareImageanswerOpt((qtype+"2 "));	
		
		 int j=1;
		 for(int i=0;i<4;i++) {
			 
			 expectedImage[j] = ImageIO.read(new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Expected_images\\Expected image " +j+ ".png"));
			 ImageDiffer imgDiff = new ImageDiffer();
		     ImageDiff diff = imgDiff.makeDiff(actualanswerImage[j], expectedImage[j]);
		     Assert.assertFalse(diff.hasDiff(),"Images are not Same");
		     j++;
		 }}
		
		if(qtype == "Single Answer") {
		//To check if radio buttons displayed
		String[] radiobtn = new String[4];
		radiobtn = createpageobj.getInputTypesDisplayed();
		for(int i=0;i<4;i++) {
			Assert.assertEquals(radiobtn[i],"radio", "Radio buttons are not displayed");
		   }
		}
	
		if(qtype == "Multiple Answer") {
			//To check if checkboxes are displayed
			String[] checkboxbtn = new String[4];
			checkboxbtn = createpageobj.getInputTypesDisplayed();
			for(int i=0;i<4;i++) {
				Assert.assertEquals(checkboxbtn[i],"checkbox", "Check boxes are not displayed");	
			}
		}
		
		actualImagedesc = createpageobj.compareImgDescription();
		expectedImagedesc = ImageIO.read(new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Expected_images\\Expected image desc.png"));
		 ImageDiffer imgDiff = new ImageDiffer();
	     ImageDiff diff = imgDiff.makeDiff(actualImagedesc, expectedImagedesc);
	     Assert.assertFalse(diff.hasDiff(),"Images are not Same");

		createpageobj.clickClosepreview();
				
		Thread.sleep(2000);		
			
		createpageobj.clickCreate();
		
		String actSuccess = createpageobj.getReactAlertMessage();
		Assert.assertEquals(actSuccess, "問題を正常に作成しました", "Success messages does not match");
		
		listpageobj.checkifListpage();
	} 
	

	@Test (dataProvider = "Edit cases")
	public void l_EditSingleAnswer(String TCID, String TCDescription, String count, String qtype, String editcat , String editgroup, String editdificulty, 
			String editQB, String changeQuestionttle, String changeQuestiondesc, String changemark, String editansweroptiontxt, String removeAnswer) throws InterruptedException, AWTException, IOException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		int optionCountn= Integer.parseInt(count);
		 listpageobj.navigate_questionsPage();
	     listpageobj.clickCreate();
		 listpageobj.createQuestion(qtype);
		 Thread.sleep(2000);
		 listpageobj.checkifListpage();
		 listpageobj.clickEdit();
		 Thread.sleep(2000);
		 editpageobj.checkEditpage(qtype);		 		 
		 
		 boolean editcat_n = false;
		 boolean editgroup_n = false;
		 boolean editdificulty_n = false;
		 boolean editQB_n = false;
		 boolean changeQuestionttle_n = false;
		 boolean changeQuestiondesc_n = false;
		 boolean changemark_n = false;
		 boolean editansweroptiontxt_n = false;
		 boolean removeAnswer_n = false;
		 
		 
			if (editcat.equalsIgnoreCase("TRUE")){
				editcat_n = true;
				}else if(editcat.equalsIgnoreCase("FALSE")) {
					editcat_n = false;
				}
			if (editgroup.equalsIgnoreCase("TRUE")){
				editgroup_n = true;
				}else if(editgroup.equalsIgnoreCase("FALSE")) {
					editgroup_n = false;
				}
			if (editdificulty.equalsIgnoreCase("TRUE")){
				editdificulty_n = true;
				}else if(editdificulty.equalsIgnoreCase("FALSE")) {
					editdificulty_n = false;
				}
			if (editQB.equalsIgnoreCase("TRUE")){
				editQB_n = true;
				}else if(editQB.equalsIgnoreCase("FALSE")) {
					editQB_n = false;
				}
			if (changeQuestionttle.equalsIgnoreCase("TRUE")){
				changeQuestionttle_n = true;
				}else if(changeQuestionttle.equalsIgnoreCase("FALSE")) {
					changeQuestionttle_n = false;
				}
			if (changeQuestiondesc.equalsIgnoreCase("TRUE")){
				changeQuestiondesc_n = true;
				}else if(changeQuestiondesc.equalsIgnoreCase("FALSE")) {
					changeQuestiondesc_n = false;
				}
			if (changemark.equalsIgnoreCase("TRUE")){
				changemark_n = true;
				}else if(changemark.equalsIgnoreCase("FALSE")) {
					changemark_n = false;
				}
			if (editansweroptiontxt.equalsIgnoreCase("TRUE")){
				editansweroptiontxt_n = true;
				}else if(editansweroptiontxt.equalsIgnoreCase("FALSE")) {
					editansweroptiontxt_n = false;
				}
			if (removeAnswer.equalsIgnoreCase("TRUE")){
				removeAnswer_n = true;
				}else if(removeAnswer.equalsIgnoreCase("FALSE")) {
					removeAnswer_n = false;
				}
		 
		 
		 
		 if(editcat_n == true) {
			 editpageobj.changeCategory();			 
		 }
		 
		 if(editgroup_n == true) {
			 editpageobj.changeGroup();
		 }
		 
		 if(editdificulty_n == true) {
			 editpageobj.changeDifficulty();
		 }
		 
		 if(editQB_n == true) {
			 editpageobj.changeQuestionBank();
		 }
		 
		 if(changeQuestionttle_n == true) {
			 editpageobj.changeQuestiontitle("This is the edited question bank title");
		 }
		 
		 if(changeQuestiondesc_n == true) {
			 editpageobj.changeQuestiondescription("This is the edited question description");
		 }
			 
		 if(changemark_n == true) {
			 editpageobj.changeMark("7");
		 }
		 
		 if(optionCountn == 5) {
			 editpageobj.addAnsweroption();
		 }
		 
		 if (editansweroptiontxt_n == true ) {
			 
			 editpageobj.changeAnsweroptions(optionCountn);
		 }
		 
		 if(removeAnswer_n == true) {
			 
			 editpageobj.deleteAnsweroption();
		 }
		 
		 editpageobj.clickUpdate();
		 
		 String actSuccess = editpageobj.getReactAlertMessage();
			Assert.assertEquals(actSuccess, "問題を正常に更新しました", "Success messages does not match");

	} 
	
	@Test (dataProvider = "Edit cases - Negative")
	public void m_EditSingleAnswer(String TCID, String TCDescription, String count, String qtype, String cleargrp, String cleartitle, 
			String clearAnswerop, String clearMarks) throws InterruptedException, AWTException, IOException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		//int optionCountn= Integer.parseInt(count);
		 listpageobj.navigate_questionsPage();
	     listpageobj.clickCreate();
		 listpageobj.createQuestion(qtype);
		 Thread.sleep(2000);
	     listpageobj.checkifListpage();
		 listpageobj.clickEdit();
		 Thread.sleep(2000);
		 editpageobj.checkEditpage(qtype);		 		 
		
		 boolean cleargrp_n = false;
		 boolean cleartitle_n = false;
		 boolean clearAnswerop_n = false;
		 boolean clearMarks_n = false;
		 
			if (cleargrp.equalsIgnoreCase("TRUE")){
				cleargrp_n = true;
				}else if(cleargrp.equalsIgnoreCase("FALSE")) {
					cleargrp_n = false;
				}
			if (cleartitle.equalsIgnoreCase("TRUE")){
				cleartitle_n = true;
				}else if(cleartitle.equalsIgnoreCase("FALSE")) {
					cleartitle_n = false;
				}
			if (clearAnswerop.equalsIgnoreCase("TRUE")){
				clearAnswerop_n = true;
				}else if(clearAnswerop.equalsIgnoreCase("FALSE")) {
					clearAnswerop_n = false;
				}
			if (clearAnswerop.equalsIgnoreCase("TRUE")){
				clearAnswerop_n = true;
				}else if(clearAnswerop.equalsIgnoreCase("FALSE")) {
					clearAnswerop_n = false;
				}
			
		 
		 
		 if(cleargrp_n == true) {
		 editpageobj.clearGroup();
		 }
		 
		 if(cleartitle_n == true) {
			 editpageobj.clearTitle();		
		 }
		 
		 if(clearAnswerop_n == true) {
			 editpageobj.clearAnswerop();
		 }
		 
		 if(clearMarks_n == true) {
			 editpageobj.clearMarks();			  
		 }
		 
		 editpageobj.clickUpdate();
		 
		 if(cleargrp_n == true) {
		 String actError = editpageobj.getGrouperror();
			Assert.assertEquals(actError, "グループ名は必須です", "Error messages does not match");
		 } 
		 
		 if(clearAnswerop_n == true) {
			 String actSuccess = editpageobj.getReactAlertMessage();
				Assert.assertEquals(actSuccess, "空白の選択肢は使用できません", "Error messages does not match");
		 }
		 
		 if(clearMarks_n == true) {
			 String actError = editpageobj.getMarkerror();
				Assert.assertEquals(actError, "点数は必須です", "Success messages does not match");
		 }

		 if(cleartitle_n == true) {
		 String actError = editpageobj.getTitleError();
			Assert.assertEquals(actError, "問題のタイトルは必須です", "Error messages does not match"); 
		 }
	}

	@Test (dataProvider = "Edit cases image answer-Positive")
	public void n_EditSingleImgAnswer(String TCID, String TCDescription, String count, String qtype, String editcat , String editgroup, String editdificulty, 
			String editQB, String changeQuestionttle, String changeQuestiondesc, String changemark, String editansweroptiontxt, String removeAnswer) throws InterruptedException, AWTException, IOException
	{ 
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		 listpageobj.navigate_questionsPage();
	     listpageobj.clickCreate();
		 listpageobj.createQuestion(qtype);
		 Thread.sleep(2000);
	     listpageobj.checkifListpage();
		 listpageobj.clickEdit();
		 Thread.sleep(2000);
		 editpageobj.checkEditpage(qtype);	
		 int optionCountn= Integer.parseInt(count);
		 boolean editcat_n = false;
		 boolean editgroup_n = false;
		 boolean editdificulty_n = false;
		 boolean editQB_n = false;
		 boolean changeQuestionttle_n = false;
		 boolean changeQuestiondesc_n = false;
		 boolean changemark_n = false;
		 boolean editansweroptiontxt_n = false;
		 boolean removeAnswer_n = false;
		 
		 
			if (editcat.equalsIgnoreCase("TRUE")){
				editcat_n = true;
				}else if(editcat.equalsIgnoreCase("FALSE")) {
					editcat_n = false;
				}
			if (editgroup.equalsIgnoreCase("TRUE")){
				editgroup_n = true;
				}else if(editgroup.equalsIgnoreCase("FALSE")) {
					editgroup_n = false;
				}
			if (editdificulty.equalsIgnoreCase("TRUE")){
				editdificulty_n = true;
				}else if(editdificulty.equalsIgnoreCase("FALSE")) {
					editdificulty_n = false;
				}
			if (editQB.equalsIgnoreCase("TRUE")){
				editQB_n = true;
				}else if(editQB.equalsIgnoreCase("FALSE")) {
					editQB_n = false;
				}
			if (changeQuestionttle.equalsIgnoreCase("TRUE")){
				changeQuestionttle_n = true;
				}else if(changeQuestionttle.equalsIgnoreCase("FALSE")) {
					changeQuestionttle_n = false;
				}
			if (changeQuestiondesc.equalsIgnoreCase("TRUE")){
				changeQuestiondesc_n = true;
				}else if(changeQuestiondesc.equalsIgnoreCase("FALSE")) {
					changeQuestiondesc_n = false;
				}
			if (changemark.equalsIgnoreCase("TRUE")){
				changemark_n = true;
				}else if(changemark.equalsIgnoreCase("FALSE")) {
					changemark_n = false;
				}
			if (editansweroptiontxt.equalsIgnoreCase("TRUE")){
				editansweroptiontxt_n = true;
				}else if(editansweroptiontxt.equalsIgnoreCase("FALSE")) {
					editansweroptiontxt_n = false;
				}
			if (removeAnswer.equalsIgnoreCase("TRUE")){
				removeAnswer_n = true;
				}else if(removeAnswer.equalsIgnoreCase("FALSE")) {
					removeAnswer_n = false;
				}
		 
		 if(editcat_n == true) {
			 editpageobj.changeCategory();			 
		 }
		 
		 if(editgroup_n == true) {
			 editpageobj.changeGroup();
		 }
		 
		 if(editdificulty_n == true) {
			 editpageobj.changeDifficulty();
		 }
		 
		 if(editQB_n == true) {
			 editpageobj.changeQuestionBank();
		 }
		 
		 if(changeQuestionttle_n == true) {
			 editpageobj.changeQuestiontitle("This is the edited question bank title");
		 }
		 
		 if(changeQuestiondesc_n == true) {
			 editpageobj.changeQuestiondescription("This is the edited question description");
		 }
			 
		 if(changemark_n == true) {
			 editpageobj.changeMark("7");
		 }
		 
		 if(optionCountn == 5) {
			 createpageobj.addAnsweroption();
			 createpageobj.uploadImageAnsweroption(optionCountn);
		 }
		 
		 if (editansweroptiontxt_n == true ) {
			 
			 editpageobj.changeImageAnsweroption();
		 }
		 
		 if(removeAnswer_n == true) {
			 
			 createpageobj.deleteImageAnsweroption();
		 }
		 
		 editpageobj.clickUpdate();
		 
		 String actSuccess = editpageobj.getReactAlertMessage();
			Assert.assertEquals(actSuccess, "問題を正常に更新しました", "Success messages does not match");
	} 
	
	@Test (dataProvider = "Edit cases Image type- Negative")
	public void n_EditimgAnswer(String TCID, String TCDescription, String count, String qtype, String cleargrp, String cleartitle, String clearAnswerop, String clearMarks) throws InterruptedException, AWTException, IOException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		 listpageobj.navigate_questionsPage();
	     listpageobj.clickCreate();
		 listpageobj.createQuestion(qtype);
		 Thread.sleep(2000);
	     listpageobj.checkifListpage();
		 listpageobj.clickEdit();
		 Thread.sleep(2000);
		 editpageobj.checkEditpage(qtype);		 		 
		// int optionCountn= Integer.parseInt(count);
		 boolean cleargrp_n = false;
		 boolean cleartitle_n = false;
		 boolean clearAnswerop_n = false;
		 boolean clearMarks_n = false;
		 
			if (cleargrp.equalsIgnoreCase("TRUE")){
				cleargrp_n = true;
				}else if(cleargrp.equalsIgnoreCase("FALSE")) {
					cleargrp_n = false;
				}
			if (cleartitle.equalsIgnoreCase("TRUE")){
				cleartitle_n = true;
				}else if(cleartitle.equalsIgnoreCase("FALSE")) {
					cleartitle_n = false;
				}
			if (clearAnswerop.equalsIgnoreCase("TRUE")){
				clearAnswerop_n = true;
				}else if(clearAnswerop.equalsIgnoreCase("FALSE")) {
					clearAnswerop_n = false;
				}
			if (clearAnswerop.equalsIgnoreCase("TRUE")){
				clearAnswerop_n = true;
				}else if(clearAnswerop.equalsIgnoreCase("FALSE")) {
					clearAnswerop_n = false;
				}
		 
		 
		 
		 if(cleargrp_n == true) {
		 editpageobj.clearGroup();
		 }
		 
		 if(cleartitle_n == true) {
			 editpageobj.clearTitle();		
		 }
		 
		 if(clearAnswerop_n == true) {
			 createpageobj.addAnsweroption();
		 }
		 
		 if(clearMarks_n == true) {
			 editpageobj.clearMarks();			  
		 }
		 
		 editpageobj.clickUpdate();
		 
		 if(cleargrp_n == true) {
		 String actError = editpageobj.getGrouperror();
			Assert.assertEquals(actError, "グループ名は必須です", "Error messages does not match");
		 } 
		 
		 if(clearAnswerop_n == true) {
			 String actSuccess =  createpageobj.getReactAlertMessage();
				Assert.assertNotEquals(actSuccess, "問題を正常に作成しました", "Success messages does not match");
		 }
		 
		 if(clearMarks_n == true) {
			 String actError = editpageobj.getMarkerror();
				Assert.assertEquals(actError, "点数は必須です", "Success messages does not match");
		 }

		 if(cleartitle_n == true) {
		 String actError = editpageobj.getTitleError();
			Assert.assertEquals(actError, "問題のタイトルは必須です", "Error messages does not match"); 
		 }
		 
	} 
	
	@Test (dataProvider = "Edit cases Text and image input answer-Positive")
	public void n_EditSingleImgAnswer(String TCID, String TCDescription, String qtype, String editcat , String editgroup, String editdificulty, 
			String editQB, String changeQuestionttle, String changeQuestiondesc, String changemark, String changeeditcorrect) throws InterruptedException, AWTException, IOException
	{ 
		
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription); 
		 listpageobj.navigate_questionsPage();
	     listpageobj.clickCreate();
		 listpageobj.createQuestion(qtype);
		 Thread.sleep(2000);
	     listpageobj.checkifListpage();
		 listpageobj.clickEdit();
		 Thread.sleep(2000);
		 editpageobj.checkEditpage(qtype);	
		 boolean editcat_n = false;
		 boolean editgroup_n = false;
		 boolean editdificulty_n = false;
		 boolean editQB_n = false;
		 boolean changeQuestionttle_n = false;
		 boolean changeQuestiondesc_n = false;
		 boolean changemark_n = false;
		 boolean changeeditcorrect_n = false;
		 
			if (editcat.equalsIgnoreCase("TRUE")){
				editcat_n = true;
				}else if(editcat.equalsIgnoreCase("FALSE")) {
					editcat_n = false;
				}
			if (editgroup.equalsIgnoreCase("TRUE")){
				editgroup_n = true;
				}else if(editgroup.equalsIgnoreCase("FALSE")) {
					editgroup_n = false;
				}
			if (editdificulty.equalsIgnoreCase("TRUE")){
				editdificulty_n = true;
				}else if(editdificulty.equalsIgnoreCase("FALSE")) {
					editdificulty_n = false;
				}
			if (editQB.equalsIgnoreCase("TRUE")){
				editQB_n = true;
				}else if(editQB.equalsIgnoreCase("FALSE")) {
					editQB_n = false;
				}
			if (changeQuestionttle.equalsIgnoreCase("TRUE")){
				changeQuestionttle_n = true;
				}else if(changeQuestionttle.equalsIgnoreCase("FALSE")) {
					changeQuestionttle_n = false;
				}
			if (changeQuestiondesc.equalsIgnoreCase("TRUE")){
				changeQuestiondesc_n = true;
				}else if(changeQuestiondesc.equalsIgnoreCase("FALSE")) {
					changeQuestiondesc_n = false;
				}
			if (changemark.equalsIgnoreCase("TRUE")){
				changemark_n = true;
				}else if(changemark.equalsIgnoreCase("FALSE")) {
					changemark_n = false;
				}
			if (changeeditcorrect.equalsIgnoreCase("TRUE")){
				changeeditcorrect_n = true;
				}else if(changeeditcorrect.equalsIgnoreCase("FALSE")) {
					changeeditcorrect_n = false;
				}
					
			
		 if(editcat_n == true) {
			 editpageobj.changeCategory();			 
		 }
		 
		 if(editgroup_n == true) {
			 editpageobj.changeGroup();
		 }
		 
		 if(editdificulty_n == true) {
			 editpageobj.changeDifficulty();
		 }
		 
		 if(editQB_n == true) {
			 editpageobj.changeQuestionBank();
		 }
		 
		 if(changeQuestionttle_n == true) {
			 editpageobj.changeQuestiontitle("This is the edited question bank title");
		 }
		 
		 if(changeQuestiondesc_n == true) {
			 editpageobj.changeQuestiondescription("This is the edited question description");
		 }
			 
		 if(changemark_n == true) {
			 editpageobj.changeMark("7");
		 }
		 
		if(changeeditcorrect_n == true) {
			editpageobj.editCorrectAnswer();
		}
		 
		 editpageobj.clickUpdate();
		 
		 String actSuccess = editpageobj.getReactAlertMessage();
			Assert.assertEquals(actSuccess, "問題を正常に更新しました", "Success messages does not match");
	} 
	
	@Test (dataProvider = "Edit cases Text and image input answer-Negative")
	public void n_EditimgAnswer(String TCID, String TCDescription, String qtype, String cleargrp, String cleartitle, String clearcorrectAnswerop, String clearMarks) throws InterruptedException, AWTException, IOException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		 listpageobj.navigate_questionsPage();
	     listpageobj.clickCreate();
		 listpageobj.createQuestion(qtype);
		 Thread.sleep(2000);
	     listpageobj.checkifListpage();
		 listpageobj.clickEdit();
		 Thread.sleep(2000);
		 editpageobj.checkEditpage(qtype);		 		 
		 boolean cleargrp_n = false;
		 boolean cleartitle_n = false;
		 boolean clearcorrectAnswerop_n = false;
		 boolean clearMarks_n = false;
		 
			if (cleargrp.equalsIgnoreCase("TRUE")){
				cleargrp_n = true;
				}else if(cleargrp.equalsIgnoreCase("FALSE")) {
					cleargrp_n = false;
				}
			if (cleartitle.equalsIgnoreCase("TRUE")){
				cleartitle_n = true;
				}else if(cleartitle.equalsIgnoreCase("FALSE")) {
					cleartitle_n = false;
				}
			if (clearcorrectAnswerop.equalsIgnoreCase("TRUE")){
				clearcorrectAnswerop_n = true;
				}else if(clearcorrectAnswerop.equalsIgnoreCase("FALSE")) {
					clearcorrectAnswerop_n = false;
				}
			if (clearMarks.equalsIgnoreCase("TRUE")){
				clearMarks_n = true;
				}else if(clearMarks.equalsIgnoreCase("FALSE")) {
					clearMarks_n = false;
				}
			
			
		 if(cleargrp_n == true) {
		 editpageobj.clearGroup();
		 }
		 
		 if(cleartitle_n == true) {
			 editpageobj.clearTitle();		
		 }
		 
		 if(clearcorrectAnswerop_n == true) {
			 editpageobj.clearCorrectAnswer();
		 }
		 
		 if(clearMarks_n == true) {
			 editpageobj.clearMarks();			  
		 }
		 
		 editpageobj.clickUpdate();
		 
		 if(cleargrp_n == true) {
		 String actError = editpageobj.getGrouperror();
			Assert.assertEquals(actError, "グループ名は必須です", "Error messages does not match");
		 } 
		 
		 if(clearcorrectAnswerop_n == true) {
			 String actSuccess =  createpageobj.getReactAlertMessage();
				Assert.assertNotEquals(actSuccess, "問題を正常に作成しました", "Success messages is displayed");
		 }
		 
		 if(clearMarks_n == true) {
			 String actError = editpageobj.getMarkerror();
				Assert.assertEquals(actError, "点数は必須です", "Error messages does not match");
		 }

		 if(cleartitle_n == true) {
		 String actError = editpageobj.getTitleError();
			Assert.assertEquals(actError, "問題のタイトルは必須です", "Error messages does not match"); 
		 }
		 
	}
	
	
	@Test (dataProvider = "Single and Multiple answer question type preview-List page")
	public void o_create_Single_Multiple_AnswerPreviewListpg(String TCID, String TCDescription, String qtype, String questionTitle, String questionDescription, String optionCount, String marks) throws InterruptedException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		int optionCountn= Integer.parseInt(optionCount);
	    listpageobj.navigate_questionsPage();
		listpageobj.clickCreate();
		createpageobj.selectCategory();
		createpageobj.selectgroup();
		createpageobj.selectQuestionType(qtype);
		createpageobj.selectDifficulty();
		createpageobj.selectQuestionBank();
		createpageobj.enterQuestiontitle(questionTitle);
		createpageobj.enterQuestiondescription(questionDescription);
		createpageobj.enterAnsweroptions(optionCountn);
		createpageobj.enterMark(marks);
		createpageobj.selectCorrectoption();
		createpageobj.clickCreate();		
		Thread.sleep(2000);
		listpageobj.clickPreview();
		Thread.sleep(2000);

		//To check Question title text
		String Act_questiontitle =  listpageobj.getPreviewQuestiontitle();
		Assert.assertEquals("Q . "+questionTitle,Act_questiontitle, "Question title does not match");
		
		//To check Question decription text
		String Act_questionDesc = listpageobj.getPreviewQuestionDesc();
		Assert.assertEquals(Act_questionDesc.trim(),questionDescription, "Question description does not match");
		
		//To check answer options text
		String[] actAnsweroptText = new String[4];
		actAnsweroptText = listpageobj.getPreviewAnsweroptions();
		for(int i=0;i<4;i++) {
			Assert.assertEquals(actAnsweroptText[i],"Answer option "+(i+1), "The answer options displayed does not match");
		}
		
		if(qtype == "Single Answer") {
		//To check if radio buttons displayed
		String[] radiobtn = new String[4];
		radiobtn = listpageobj.getInputTypesDisplayed();
		for(int i=0;i<4;i++) {
			Assert.assertEquals(radiobtn[i],"radio", "Radio buttons are not displayed");
		   }
		}
	
		if(qtype == "Multiple Answer") {
			//To check if checkboxes are displayed
			String[] checkboxbtn = new String[4];
			checkboxbtn = listpageobj.getInputTypesDisplayed();
			for(int i=0;i<4;i++) {
				Assert.assertEquals(checkboxbtn[i],"checkbox", "Check boxes are not displayed");	
			}
		}

		listpageobj.clickClosepreview();
		
	}
	
	@Test (dataProvider = "Single and multiple image answer question type preview-List page")
	public void p_create_SingleimageAnswer(String TCID, String TCDescription, String qtype, String questionTitle, String questionDescription, String optionCount, String marks) throws AWTException, InterruptedException, IOException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
	    BufferedImage[] actualImage = new BufferedImage[5];
	    BufferedImage[] expectedImage = new BufferedImage[5];
	    int optionCountn= Integer.parseInt(optionCount);
	    listpageobj.navigate_questionsPage();
		listpageobj.clickCreate();
		createpageobj.selectCategory();
		createpageobj.selectgroup();
		createpageobj.selectQuestionType(qtype);
		createpageobj.selectDifficulty();
		createpageobj.selectQuestionBank();
		createpageobj.enterQuestiontitle(questionTitle);
		createpageobj.enterQuestiondescription(questionDescription);
		createpageobj.uploadImageAnsweroption(optionCountn);
		createpageobj.enterMark(marks);
		createpageobj.selectCorrectoption();
		createpageobj.clickCreate();		
		Thread.sleep(2000);
		listpageobj.clickPreview();
		Thread.sleep(2000);
		
		Thread.sleep(2000);
		//To check Question title text
		String Act_questiontitle = listpageobj.getPreviewQuestiontitle();
		Assert.assertEquals("Q . "+questionTitle,Act_questiontitle, "Question title does not match");
		
		//To check Question decription text
		String Act_questionDesc = listpageobj.getPreviewQuestionDesc();
		Assert.assertEquals(Act_questionDesc.trim(),questionDescription, "Question description does not match");
		
		if(qtype == "Single Answer") {
		//To check if radio buttons displayed
		String[] radiobtn = new String[4];
		radiobtn = listpageobj.getInputTypesDisplayed();
		for(int i=0;i<4;i++) {
			Assert.assertEquals(radiobtn[i],"radio", "Radio buttons are not displayed");
		   }
		}
	
		if(qtype == "Multiple Answer") {
			//To check if checkboxes are displayed
			String[] checkboxbtn = new String[4];
			checkboxbtn = listpageobj.getInputTypesDisplayed();
			for(int i=0;i<4;i++) {
				Assert.assertEquals(checkboxbtn[i],"checkbox", "Check boxes are not displayed");	
			}
		}

		actualImage = listpageobj.compareImageanswerOpt(qtype);
		
		 int j=1;
		 for(int i=0;i<4;i++) {
			 
			 expectedImage[j] = ImageIO.read(new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Expected_images\\Single Answer List image " +j+ ".png"));
			 ImageDiffer imgDiff = new ImageDiffer();
		     ImageDiff diff = imgDiff.makeDiff(actualImage[j], expectedImage[j]);
		     Assert.assertFalse(diff.hasDiff(),"Images are not Same");
		     j++;
		 }
		 
		 listpageobj.clickClosepreview();
				
	}
	
	@Test (dataProvider = "Text answer and image question type-List Page Preview")
	public void k_create_Text_ImageAnswerPreview(String TCID, String TCDescription, String qtype, String questionTitle, String questionDescription, String optionCount, String marks) throws InterruptedException, AWTException, IOException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		//int optionCountn= Integer.parseInt(optionCount);
	    listpageobj.navigate_questionsPage();
		listpageobj.clickCreate();
		createpageobj.selectCategory();
		createpageobj.selectgroup();
		createpageobj.selectQuestionType(qtype);
		createpageobj.selectDifficulty();
		createpageobj.selectQuestionBank();
		createpageobj.enterQuestiontitle(questionTitle);
		createpageobj.enterQuestiondescription(questionDescription);
		createpageobj.enterMark(marks);
		
		if(qtype == "Text Input") {
			createpageobj.enterCorrectAnswer();
		}
		
		createpageobj.clickCreate();		
		Thread.sleep(2000);
		listpageobj.clickPreview();
		Thread.sleep(2000);
		String Act_questiontitle = listpageobj.getPreviewTIQuestiontitle();
		Assert.assertEquals(Act_questiontitle,"Q . "+questionTitle, "Question title does not match");
		
		//To check Question decription text
		String Act_questionDesc = listpageobj.getPreviewTIQuestionDesc();
		Assert.assertEquals(Act_questionDesc.trim(),questionDescription, "Question description does not match");
		
		if(qtype == "Text Input") {
			
			listpageobj.ifTextareaisDisplayed();			
		}
		
		if(qtype == "Image Type") {
			
			String btn = listpageobj.ifUploadbtnDisplayed();
			Assert.assertEquals(btn, "button", "Upload button is not displayed.");
		}
		
		
		listpageobj.clickClosepreview();
	}
	
	@Test (dataProvider = "Delete question")
	public void p_DeleteQuestion(String TCID, String TCDescription, String qtype, String questionTitle, String questionDescription, String optionCount, String marks) throws AWTException, InterruptedException, IOException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		int optionCountn= Integer.parseInt(optionCount);
	    listpageobj.navigate_questionsPage();
		listpageobj.clickCreate();
		createpageobj.selectCategory();
		createpageobj.selectgroup();
		createpageobj.selectQuestionType(qtype);
		createpageobj.selectDifficulty();
		createpageobj.enterQuestiontitle(questionTitle);
		createpageobj.enterQuestiondescription(questionDescription);
		createpageobj.enterAnsweroptions(optionCountn);
		createpageobj.enterMark(marks);
		createpageobj.selectCorrectoption();
		createpageobj.clickCreate();		
		Thread.sleep(2000);
		listpageobj.clickDelete();	
		
		String actSuccess = createpageobj.getReactAlertMessage();
		Assert.assertEquals(actSuccess, "問題を正常に削除しました", "Success messages does not match");

	}
	
	@Test (dataProvider = "Delete question")
	public void q_ShareQuestion(String TCID, String TCDescription, String qtype, String questionTitle, String questionDescription, String optionCount, String marks) throws AWTException, InterruptedException, IOException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		int optionCountn= Integer.parseInt(optionCount);
	    listpageobj.navigate_questionsPage();
		listpageobj.clickCreate();
		createpageobj.selectCategory();
		createpageobj.selectgroup();
		createpageobj.selectQuestionType(qtype);
		createpageobj.selectDifficulty();
		createpageobj.selectQuestionBank();
		createpageobj.enterQuestiontitle(questionTitle);
		createpageobj.enterQuestiondescription(questionDescription);
		createpageobj.enterAnsweroptions(optionCountn);
		createpageobj.enterMark(marks);
		createpageobj.selectCorrectoption();
		createpageobj.clickCreate();		
		Thread.sleep(2000);
		listpageobj.shareQuestion();	
		
		String actSuccess = createpageobj.getReactAlertMessage();
		Assert.assertEquals(actSuccess, "Question shared successfully.", "Success messages does not match");

	}
	
	@Test (dataProvider = "Edit change question type")
	public void n_EditimgAnswer(String TCID, String TCDescription, String qtype, String changeQtype) throws InterruptedException, AWTException, IOException
	{
		ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(TCID, ExtentColor.INDIGO));
		ExtentTestManager.getTest().info(TCDescription);
		 listpageobj.navigate_questionsPage();
	     listpageobj.clickCreate();
		 listpageobj.createQuestion(qtype);
		 Thread.sleep(2000);
	     listpageobj.checkifListpage();
		 listpageobj.clickEdit();
		 Thread.sleep(2000);
		
		 
		 editpageobj.changeQuestionType(changeQtype);
		 if((changeQtype=="Multiple Answer")||(changeQtype=="Single Answer")) {
			 createpageobj.enterAnsweroptions(4);
			 createpageobj.selectCorrectoption();
		 }
		 if((changeQtype=="Multiple Answer Image")||(changeQtype=="Single Answer Image")) {
			 createpageobj.uploadImageAnsweroption(4);
			 createpageobj.selectCorrectoption();
		 }
		 if(changeQtype == "Text Input") {
				createpageobj.enterCorrectAnswer();
			}
		 
		 editpageobj.clickUpdate();
		 String actSuccess = editpageobj.getReactAlertMessage();
			Assert.assertEquals(actSuccess, "問題を正常に更新しました", "Success messages does not match");
	}
	
	// ---------------------- DATAPROVIDERS-------------------
	
		@DataProvider(name = "Single answer question type")
		public Object[][] questionData() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"create_SingleAnswer", 7);
			/*  Object[][] question = { { "TC-01", "test 1", "Single Answer", "Test Automation title 1", "Test description", 4, "5" },
					{ "TC-01", "test 1", "Single Answer", "Test Automation title 2", "Test description", 5, "5" },
					{ "TC-01", "test 1", "Single Answer", "Test Automation title 2", "Test description", 3, "5" }};  */
					
			return question;
		}
				
		@DataProvider(name = "Multiple answer question type")
		public Object[][] questionData3() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"create_MultipleAnswer", 7);
			/*Object[][] question = { { "Multiple Answer", "Test Automation title 1", "Test description", 4, "5" },
					{ "Multiple Answer", "Test Automation title 2", "Test description", 5, "5" },
					{ "Multiple Answer", "Test Automation title 2", "Test description", 3, "5" }};
					*/
			return question;
		}
		
		@DataProvider(name = "Single and Multiple answer question type- Cases")
		public Object[][] questionData2() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Single_and_Mulitple_cases", 12);
		/*	Object[][] question = { { "Single Answer", "Test Automation title", "Test description", 4, "5", true, true, true, true, true },
					{ "Single Answer", "Test Automation title", "Test description", 4, "5", false, false, true, true, true  },
					{ "Single Answer", "Test Automation title", "Test description", 4, "", false, true, true, true, true  },
					{ "", "Test Automation title", "Test description", 4, "5", false, true, true, true, true  },
					{ "Single Answer", "", "Test description", 4, "5", false, true, true, true, true  },
					{ "Single Answer", "Test1 Title", "Test description", 4, "5", false, true, false, true, true  },
					{ "Single Answer", "Test 2Title", "Test description", 4, "5", false, true, true, false, true  },
					{ "Single Answer", "Test 3Title", "Test description", 4, "5", false, true, true, true, false  },
					{ "Multiple Answer", "Test Automation title", "Test description", 4, "5", true, true, true, true, true  },
					{ "Multiple Answer", "Test Automation title", "Test description", 4, "5", false, false, true, true, true  },
					{ "Multiple Answer", "Test Automation title", "Test description", 4, "", false, true, true, true, true  },
					{ "", "Test Automation title", "Test description", 4, "5", false, true, true, true, true  },
					{ "Multiple Answer", "", "Test description", 4, "5", false, true, true, true, true  },
					{ "Multiple Answer", "Test1 Title", "Test description", 4, "5", false, true, false, true, true  },
					{ "Multiple Answer", "Test2 Title", "Test description", 4, "5", false, true, true, false, true  },
					{ "Multiple Answer", "Test 3Title", "Test description", 4, "5", false, true, true, true, false  }};   */
			return question;
		}
		
		@DataProvider(name = "Single and Multiple answer question type preview")
		public Object[][] questionData5() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Single_and_Mulitple_preview", 7);
			/*Object[][] question = { { "Single Answer", "Test Automation title", "Test description", 4, "5" },
					{ "Multiple Answer", "Test Automation title", "Test description", 4, "5" }};*/
			return question;
		}
		
		@DataProvider(name = "Single image answer question type")
		public Object[][] questionData6() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Create_singleimage_question", 7);
			/*Object[][] question = { { "Single Answer Image", "Test single image Automation title 1", "Test description", 4, "5" },
					{ "Single Answer Image", "Test single image Automation title 2 5op", "Test description", 5, "5" },
					{ "Single Answer Image", "Test single image Automation title 3 -3op", "Test description", 3, "5" }*/
			
			return question;
		}
		
		@DataProvider(name = "Multiple image answer question type")
		public Object[][] questionData7() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Create_multipleimage_question", 7);
			/*Object[][] question = { { "Multiple Answer Image", "Test multiple image Automation title 1", "Test description", 4, "5" },
					{ "Multiple Answer Image", "Test multiple image Automation title 2 5op", "Test description", 5, "5" },
					{ "Multiple Answer Image", "Test multiple image Automation title 3 -3op", "Test description", 3, "5" }
			};*/
			return question;
		}
		
		@DataProvider(name = "Single and Multiple image answer question type- Cases")
		public Object[][] questionData8() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Single_and_Mulitpleimage_cases", 12);
			/*Object[][] question = { { "Single Answer Image", "Test Automation title", "Test description", 4, "5", true, true, true, true, true },
					{ "Single Answer Image", "Test Automation title", "Test description", 4, "5", false, false, true, true, true },
					{ "Single Answer Image", "Test Automation title", "Test description", 4, "", false, true, true, true, true },
					{ "", "Test Automation title", "Test description", 4, "5", false, true, true, true, true },
					{ "Single Answer Image", "", "Test description", 4, "5", false, true, true, true, true },
					{ "Single Answer Image", "Test Title", "Test description", 4, "5", false, true, false, true, true  },
					{ "Single Answer Image", "Test Title", "Test description", 4, "5", false, true, true, false, true  },
					{ "Single Answer Image", "Test Title", "Test description", 4, "5", false, true, true, true, false  },
					{ "Multiple Answer Image", "Test Automation title", "Test description", 4, "5", true, true, true, true, true },
					{ "Multiple Answer Image", "Test Automation title", "Test description", 4, "5", false, false, true, true, true },
					{ "Multiple Answer Image", "Test Automation title", "Test description", 4, "", false, true, true, true, true },
					{ "", "Test Automation title", "Test description", 4, "5", false, true, true, true, true },
					{ "Multiple Answer Image", "", "Test description", 4, "5", false, true, true, true, true },
					{ "Multiple Answer Image", "Test Title", "Test description", 4, "5", false, true, false, true, true  },
					{ "Multiple Answer Image", "Test Title", "Test description", 4, "5", false, true, true, false, true  },
					{ "Multiple Answer Image", "Test Title", "Test description", 4, "5", false, true, true, true, false  }};*/
			return question;
		}

		@DataProvider(name = "Single and multiple image answer question type preview")
		public Object[][] questionData9() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Single_and_Mulitpleimage_preview", 7);
			/*Object[][] question = { { "Single Answer Image", "Test single image Automation title 1", "Test description", 4, "5" },
					{ "Multiple Answer Image", "Test multiple image Automation title 2", "Test description", 4, "5" }};*/
			return question;
		}
		
		@DataProvider(name = "Text answer and image question type")
		public Object[][] questionData10() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Create_textandimage_imageanswer_preview", 8);
			/*Object[][] question = { { "Text Input", "Test Automation title 1", "Test description", 4, "5", false },
					{ "Image Type", "Test Automation title 1", "Test description", 4, "5", false },
			{ "Text Input", "Test Automation title 1", "Test description", 4, "5", true },
			{ "Image Type", "Test Automation title 1", "Test description", 4, "5", true }};*/
			return question;
		}
		
		@DataProvider(name = "Questions with image description")
		public Object[][] questionData11() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"CreateQuestions_imagedesc", 7);
			/*Object[][] question = { { "Single Answer", "Test Automation title 1", "Test description", 4, "5" },
					{ "Multiple Answer", "Test Automation title 1", "Test description", 4, "5" },
					{ "Single Answer Image", "Test Automation title 1", "Test description", 4, "5" },
					{ "Multiple Answer Image", "Test Automation title 1", "Test description", 4, "5"}
					};*/
			return question;
		}
		
		@DataProvider(name = "Edit cases")
		public Object[][] questionData12() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Single_and_Mulitple_Editcases", 13);
			/*Object[][] question = {{4, "Single Answer", true, false, false, false, false, false, false, false, false},
					 {4, "Single Answer", false, true, false, false, false, false, false, false, false},
					 {4, "Single Answer", false, false, true, false, false, false, false, false, false},
					 {4, "Single Answer", false, false, false, true, false, false, false, false, false},
					 {4, "Single Answer", false, false, false, false, true, false, false, false, false},
					 {4, "Single Answer", false, false, false, false, false, true, false, false, false},
					 {4, "Single Answer", false, false, false, false, false, false, true, false, false},
					 {4, "Single Answer", false, false, false, false, false, false, false, true, false},
					 {4, "Single Answer", false, false, false, false, false, false, false, false, true},
					 {5, "Single Answer", false, false, false, false, false, false, false, true, false},
					 {4, "Multiple Answer", true, false, false, false, false, false, false, false, false},
					 {4, "Multiple Answer", false, true, false, false, false, false, false, false, false},
					 {4, "Multiple Answer", false, false, true, false, false, false, false, false, false},
					 {4, "Multiple Answer", false, false, false, true, false, false, false, false, false},
					 {4, "Multiple Answer", false, false, false, false, true, false, false, false, false},
					 {4, "Multiple Answer", false, false, false, false, false, true, false, false, false},
					 {4, "Multiple Answer", false, false, false, false, false, false, true, false, false},
					 {4, "Multiple Answer", false, false, false, false, false, false, false, true, false},
					 {4, "Multiple Answer", false, false, false, false, false, false, false, false, true},
					 {5, "Multiple Answer", false, false, false, false, false, false, false, true, false}    };*/
			return question;
}
		
		@DataProvider(name = "Edit cases - Negative")
		public Object[][] questionData13() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Single_and_Mulitple_invalidEditcases", 8);
			/*Object[][] question = {{4, "Single Answer", true, false, false, false},
					{4, "Single Answer", false, true, false, false},
				    {4, "Single Answer", false, false, true, false},
					{4, "Single Answer", false, false, false, true},				    
				    {4, "Multiple Answer", true, false, false, false},
					{4, "Multiple Answer", false, true, false, false},
				    {4, "Multiple Answer", false, false, true, false},
					{4, "Multiple Answer", false, false, false, true}};*/
			return question;
		}
		
		@DataProvider(name = "Edit cases image answer-Positive")
		public Object[][] questionData14() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Single_and_Mulitpleimage_Editcases", 13);
		/*	Object[][] question = {{4, "Single Answer Image", true, false, false, false, false, false, false, false, false},
					 {4, "Single Answer Image", false, true, false, false, false, false, false, false, false},
					 {4, "Single Answer Image", false, false, true, false, false, false, false, false, false},
					 {4, "Single Answer Image", false, false, false, true, false, false, false, false, false},
					 {4, "Single Answer Image", false, false, false, false, true, false, false, false, false},
					 {4, "Single Answer Image", false, false, false, false, false, true, false, false, false},
					 {4, "Single Answer Image", false, false, false, false, false, false, true, false, false},
					 {4, "Single Answer Image", false, false, false, false, false, false, false, true, false},
					 {4, "Single Answer Image", false, false, false, false, false, false, false, false, true},
					 {5, "Single Answer Image", false, false, false, false, false, false, false, false, false},
					 {4, "Multiple Answer Image", true, false, false, false, false, false, false, false, false},
					 {4, "Multiple Answer Image", false, true, false, false, false, false, false, false, false},
					 {4, "Multiple Answer Image", false, false, true, false, false, false, false, false, false},
					 {4, "Multiple Answer Image", false, false, false, true, false, false, false, false, false},
					 {4, "Multiple Answer Image", false, false, false, false, true, false, false, false, false},
					 {4, "Multiple Answer Image", false, false, false, false, false, true, false, false, false},
					 {4, "Multiple Answer Image", false, false, false, false, false, false, true, false, false},
					 {4, "Multiple Answer Image", false, false, false, false, false, false, false, true, false},
					 {4, "Multiple Answer Image", false, false, false, false, false, false, false, false, true},
					 {5, "Multiple Answer Image", false, false, false, false, false, false, false, false, false} };*/
			return question;
}
		
		@DataProvider(name = "Edit cases Image type- Negative")
		public Object[][] questionData15() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Single_and_Mulitpleimage_negativecases", 8);
		/*	Object[][] question = {{4, "Single Answer Image", true, false, false, false},
					{4, "Single Answer Image", false, true, false, false},
				    {4, "Single Answer Image", false, false, true, false},
					{4, "Single Answer Image", false, false, false, true},				    
				    {4, "Multiple Answer Image", true, false, false, false},
					{4, "Multiple Answer Image", false, true, false, false},
				    {4, "Multiple Answer Image", false, false, true, false},
					{4, "Multiple Answer Image", false, false, false, true}}; */
			return question;
		}
		
		@DataProvider(name = "Edit cases Text and image input answer-Positive")
		public Object[][] questionData16() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Edit_textandimage_positive_cases", 11);
			/*Object[][] question = {{"Text Input", true, false, false, false, false, false, false, false},
					{"Text Input", false, true, false, false, false, false, false, false},
					{"Text Input", false, false, true, false, false, false, false, false},
					{"Text Input", false, false, false, true, false, false, false, false},
					{"Text Input", false, false, false, false, true, false, false, false},
					{"Text Input", false, false, false, false, false, true, false, false},
					{"Text Input", false, false, false, false, false, false, true, false},
					{"Text Input", false, false, false, false, false, false, false, true},
					{"Image Type", true, false, false, false, false, false, false, false},
					{"Image Type", false, true, false, false, false, false, false, false},
					{"Image Type", false, false, true, false, false, false, false, false},
					{"Image Type", false, false, false, true, false, false, false, false},
					{"Image Type", false, false, false, false, true, false, false, false},
					{"Image Type", false, false, false, false, false, true, false, false},
					{"Image Type", false, false, false, false, false, false, true, false}};*/
			

			return question;
}
		@DataProvider(name = "Edit cases Text and image input answer-Negative")
		public Object[][] questionData17() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Edit_textandimage_negative_cases", 7);
			/*Object[][] question = {{"Text Input", true, false, false, false},
					{"Text Input", false, true, false, false},
				    {"Text Input", false, false, true, false},
				    {"Text Input", false, false, false, true},				    
				    {"Image Type", true, false, false, false},
					{"Image Type", false, true, false, false},
					{"Image Type", false, false, false, true}};*/
			return question;
		}
		
		@DataProvider(name = "Single and Multiple answer question type preview-List page")
		public Object[][] questionData18() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Single_and_Mulitple_Listpreview_cases", 7);
			/*Object[][] question = { { "Single Answer", "Test Automation title", "Test description", 4, "5" },
					{ "Multiple Answer", "Test Automation title", "Test description", 4, "5" }};*/
			return question;
		}
		
		@DataProvider(name = "Single and multiple image answer question type preview-List page")
		public Object[][] questionData19() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Single_and_MulitpleImage_Listpreview_cases", 7);
			/*Object[][] question = { { "Single Answer Image", "Test single image Automation title 1", "Test description", 4, "5" },
					{ "Multiple Answer Image", "Test multiple image Automation title 2", "Test description", 4, "5" }};*/
			return question;
		}
		
		@DataProvider(name = "Text answer and image question type-List Page Preview")
		public Object[][] questionData20() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Text_Image_Listpreview_cases", 7);
			/*Object[][] question = {
			{ "Text Input", "Test Automation title 1", "Test description", 4, "5"},
			{ "Image Type", "Test Automation title 1", "Test description", 4, "5"}};*/
			return question;
		}
		
		@DataProvider(name = "Delete question")
		public Object[][] questionData21() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Delete_question", 7);
			/*Object[][] question = { { "Single Answer", "Test single image Automation title 1", "Test description", 4, "5" }
			};*/
			return question;
		}
		
		@DataProvider(name = "Edit change question type")
		public Object[][] questionData22() {
			Object[][] question = ReadExcel.ReadTestData(this.getClass().getSimpleName(),"Edit_change_Questiontype", 4);
			/*Object[][] question = { { "Single Answer", "Multiple Answer"},
					 { "Single Answer", "Single Answer Image"},
					 { "Single Answer", "Multiple Answer Image"},
					 { "Single Answer", "Text Input"},
					 { "Single Answer", "Image Type"},
					 
					 { "Multiple Answer", "Single Answer"},
					 { "Multiple Answer", "Single Answer Image"},
					 { "Multiple Answer", "Multiple Answer Image"},
					 { "Multiple Answer", "Text Input"},
					 { "Multiple Answer", "Image Type"},
					 
					 { "Single Answer Image", "Single Answer"},
					 { "Single Answer Image", "Multiple Answer"},
					 { "Single Answer Image", "Multiple Answer Image"},
					 { "Single Answer Image", "Text Input"},
					 { "Single Answer Image", "Image Type"},
					 
					 { "Multiple Answer Image", "Single Answer"},
					 { "Multiple Answer Image", "Multiple Answer"},
					 { "Multiple Answer Image", "Single Answer Image"},
					 { "Multiple Answer Image", "Text Input"},
					 { "Multiple Answer Image", "Image Type"},
					 
					 { "Text Input", "Single Answer"},
					 { "Text Input", "Multiple Answer"},
					 { "Text Input", "Single Answer Image"},
					 { "Text Input", "Multiple Answer Image"},
					 { "Text Input", "Image Type"},
					 
					 { "Image Type", "Single Answer"},
					 { "Image Type", "Multiple Answer"},
					 { "Image Type", "Single Answer Image"},
					 { "Image Type", "Multiple Answer Image"},
					 { "Image Type", "Text Input"},
			};*/
			return question;
		}
}
	
	
