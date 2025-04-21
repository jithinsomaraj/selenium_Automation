package examCreate;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import login.loginPage;
import projectBase.Base;
import questionBankCreateEdit.QuestionBankCreatePage;
import questionBankList.QuestionBankListPage;

public class ExamCreateCases {
	
	
	WebDriver driver = Base.chromeSetup();
	ExamCreatePage exobj1 = new ExamCreatePage(driver);
	loginPage loginpage = new loginPage(driver);
		
	
	@BeforeTest
	//@Parameters({ "adminUserName", "adminPassword" })
	//public void openbrowserandLogin(String email, String password) {
		public void openbrowserandLogin() {
		Base.loadadminUrl(driver);
		loginpage.login("examadmin@gmail.com", "Admin@123");
	}

	//Tc01,TC05
	
	@Test
	(dataProvider = "examcreate1")
	public void ValidExamCreate(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String message) throws InterruptedException 
		{
		
		
			exobj1.createExam(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty,searchword,qname,passmark,message);
			
			Assert.assertEquals(exobj1.getReactAlertMessage(),message, "Exam created successfully");
			System.out.println("ALert  from cases"+exobj1.getReactAlertMessage()+"Messages as srting"+message);
		}
		
		//TC02
	@Test
	(dataProvider = "examcreate2")
	public void ValidExamCreate2(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String message) throws InterruptedException 
	{
	
	
		exobj1.createExam(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty,searchword,qname,passmark,message);
		
		Assert.assertEquals(exobj1.getReactAlertMessage(),message, "Exam created successfully");
		System.out.println("ALert  from cases"+exobj1.getReactAlertMessage()+"Messages as srting"+message);
	}
	
	//TC03,TC09
	
    @Test
    (dataProvider = "examcreate3")
	public void ValidExamCreate3(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String message) throws InterruptedException 
	{
	
	
		exobj1.createExam(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty,searchword,qname,passmark,message);
		
		Assert.assertEquals(exobj1.getReactAlertMessage(),message, "Exam created successfully");
		System.out.println("ALert  from cases"+exobj1.getReactAlertMessage()+"Messages as srting"+message);
	}
    //TC04
	@Test
	(dataProvider = "examcreate4")
	public void ValidExamCreate4(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String message) throws InterruptedException 
	{
	
	
		exobj1.createExam(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty,searchword,qname,passmark,message);
		
		Assert.assertEquals(exobj1.getReactAlertMessage(),message, "Exam created successfully");
		System.out.println("ALert  from cases"+exobj1.getReactAlertMessage()+"Messages as srting"+message);
	}
	//TC06,TC07
	@Test
	(dataProvider = "examcreate4")
	public void ValidExamCreate5(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String message) throws InterruptedException 
	{
		
		exobj1.createExam_uncheck(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty,searchword,qname,passmark,message);
		
		Assert.assertEquals(exobj1.getReactAlertMessage(),message, "Exam created successfully");
		System.out.println("ALert  from cases"+exobj1.getReactAlertMessage()+"Messages as srting"+message);
	}
	
	//TC11
	
	@Test
	(dataProvider = "examcreate5")
	public void ValidExamCreate6(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String qname,String passmark,String message) throws InterruptedException 
	{
		
		exobj1.createExam_cancelingcreate(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty,qname,passmark,message);
		
		Assert.assertEquals(exobj1.inipage(),"Exam list", "Exam created successfully");
		System.out.println("ALert  from cases"+exobj1.getReactAlertMessage()+"Messages as srting"+message);
	}
	
	
	@Test
	(dataProvider = "multiplequestion1")
	public void ValidExamCreate7(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty) throws InterruptedException 
	{
		
		exobj1.addmultiplequestion1(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty);
		
	}
	
	
	@Test
	(dataProvider = "multiplequestion")
	public void ValidExamCreate8(String q1,String q2,String q3) 
	{		
			exobj1.quesionList(q1);
			exobj1.quesionList(q2);
			exobj1.quesionList(q3);		
	}

	
	@Test
	(dataProvider = "multiplequestion2")
	public void ValidExamCreate9(String passmark,String message) throws InterruptedException 
	{		
		exobj1.addmultiplequestion2(passmark,message);
		Thread.sleep(3000);
		Assert.assertEquals(exobj1.inipage(),"試験リスト", "Exam created successfully");
		System.out.println("ALert  from cases"+exobj1.inipage()+"Messages as srting");
	}

	@Test
	(dataProvider="createdel")
	public void ValidExamCreate10(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String qname,String passmark,String Message,String delname) throws InterruptedException
	{
	exobj1.createExamDel(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty,qname,passmark,Message,delname);
		
		Assert.assertEquals(exobj1.getReactAlertMessage(),Message, "Exam created successfully");
		System.out.println("ALert  from cases"+exobj1.getReactAlertMessage()+"Messages as srting"+Message);
	
	}
	
	
	@Test
	(dataProvider="examcreatmkupdt")
	public void ValidExamCreate11(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String Message) throws InterruptedException
	{
	exobj1.createExamMkupdt(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty,searchword,qname,passmark,Message);
		
		Assert.assertEquals(exobj1.getReactAlertMessage(),Message, "Exam created successfully");
		System.out.println("ALert  from cases"+exobj1.getReactAlertMessage()+"Messages as srting"+Message);
	
	}
	
	
	
	@Test
	(dataProvider="examcreatenotitle")
	public void examCreateNotitle(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String Message) throws InterruptedException
	{
	exobj1.createExam(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty,searchword,qname,passmark,Message);
		
		
		Thread.sleep(2000);
	
		System.out.println("ALert  from cases DDDSS:"+exobj1.getTitle().getAttribute("validationMessage")); 
		Assert.assertEquals(exobj1.getTitle().getAttribute("validationMessage"),Message, "Exam created successfully");
	}
	@Test
	(dataProvider="noduration")
	public void examCreateNoduration(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String Message) throws InterruptedException
	{
	exobj1.createExam(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty,searchword,qname,passmark,Message);
		
		
		Thread.sleep(2000);
	
		System.out.println("ALert  from cases DDDSS:"+exobj1.getTitle().getAttribute("validationMessage")); 
		Assert.assertEquals(exobj1.getDuration().getAttribute("validationMessage"),Message, "Exam created successfully");
	}
	
	@Test
	(dataProvider="noexamtype")
	public void examCreateNoexamtype(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String Message) throws InterruptedException
	{
	exobj1.createExam(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty,searchword,qname,passmark,Message);
			
		Thread.sleep(2000);
	
		System.out.println("ALert  from cases DDDSS:"+exobj1.getTitle().getAttribute("validationMessage")); 
		Assert.assertEquals(exobj1.getExamType().getAttribute("validationMessage"),Message, "Exam created successfully");
	}
	
	@Test
	(dataProvider="noschedule")
	public void examCreateNoSchedule(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String Message) throws InterruptedException
	{
	exobj1.createExam(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty,searchword,qname,passmark,Message);
		
		
		Thread.sleep(2000);
	
		System.out.println("ALert  from cases DDDSS:"+exobj1.getTitle().getAttribute("validationMessage")); 
		Assert.assertEquals(exobj1.getExamSchedule().getAttribute("validationMessage"),Message, "Exam created successfully");
	}
	
	//Zero pass mark
	@Test
	(dataProvider = "zeropassmark")
	public void zeroPassMark(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String message) throws InterruptedException 
		{
		
		
			exobj1.createExam(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty,searchword,qname,passmark,message);
						
			System.out.println("AASD"+exobj1.zeroMark().getText());
			Assert.assertEquals(exobj1.zeroMark().getText(),message, "only zero is supported");
		}
	@Test
	(dataProvider = "morepassmark")
	public void morePassMark(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String message) throws InterruptedException 
		{
		
			exobj1.createExam(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty,searchword,qname,passmark,message);			
			System.out.println("AASD"+exobj1.moreMark().getText());
			Assert.assertEquals(exobj1.moreMark().getText(),message, "zero is supported");
		}
	
	
	@Test
	(dataProvider = "preview")
	public void preview(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String q1,String q2,String q3,String passmark,String message) throws InterruptedException 
		{
		exobj1.addmultiplequestion1(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty);

		exobj1.quesionList(q1);
		exobj1.quesionList(q2);
		exobj1.quesionList(q3);	
		Thread.sleep(2000);
		exobj1.questionListokButton();
		
		String W[]= {"Q 1. "+q1,"Q 2. "+q2,"Q 3. "+q3};
		
		exobj1.verifyPreview(W);
		
		}
	@Test
	(dataProvider="edit1")
public void edit1(String searchcd,String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String qname,String passmark,String Message,String delname) throws InterruptedException
{
		
exobj1.edit(searchcd, examname, testtime, selectexamtype, selectexamschedule, description, termsofuse, group, category, difficulty, qname, passmark, Message, delname);
Assert.assertEquals(exobj1.getReactAlertMessage(),Message, "Exam not updated");

}
	
	
	@Test(dataProvider = "examcreate_changeorder2")
	public void edit_changeOrder(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String message) throws InterruptedException
	{
		exobj1.editExamQor(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty,searchword,qname,passmark,message);
		exobj1.editorderQ();
		String actSuccess = exobj1.getReactAlertMessage();
		Assert.assertEquals(actSuccess, "問題は正常に更新されました", "Success messages does not match");
		Thread.sleep(3000);
		exobj1.createButton1();
		Assert.assertEquals(exobj1.getReactAlertMessage(),message, "Exam created successfully");
		//System.out.println("ALert  from cases"+exobj1.getReactAlertMessage()+"Messages as srting"+message);
		
	}
	
	@Test(dataProvider = "examcreate_WithalltypesQ")
	public void createaddQ(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String message) throws InterruptedException
	{
		exobj1.createExamwithQuestion(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty,searchword,qname,passmark,message);
		Assert.assertEquals(exobj1.getReactAlertMessage(),message, "Exam created successfully");
				
	}
	
	@Test(dataProvider = "examcreate_changeorder2")
	public void edit_AddallQtype(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String message) throws InterruptedException
	{
		exobj1.editExamQor(examname,testtime,selectexamtype,selectexamschedule,description,termsofuse,group,category,difficulty,searchword,qname,passmark,message);
		exobj1.edtaddQ();
		Thread.sleep(3000);
		exobj1.createButton1();
		Assert.assertEquals(exobj1.getReactAlertMessage(),message, "Exam created successfully");
		//System.out.println("ALert  from cases"+exobj1.getReactAlertMessage()+"Messages as srting"+message);
		
	}
	
	
	@AfterTest
	public void closeBrowser() {
		QuestionBankListPage QuestionBankListPage = new QuestionBankListPage(driver);
		QuestionBankListPage.logout();
		driver.quit();
	}
	
//------------------------DATA Providers--------------------------------
	
	@DataProvider(name = "examcreate_WithalltypesQ")
	public Object[] validExamCreate7() 
	{
		Object validexamcreate [][]=  { { "Examamal","10","一般公開","固定","A sample description","A sample terms of use","for automation","for automation","Easy","New question","New question test","3","試験が正常に作成されました"}
				 };
		return validexamcreate;
	}

	@DataProvider(name = "examcreate_changeorder2")
	public Object[] validExamCreate78() 
	{
		Object validexamcreate [][]=  { { "Examamal","10","一般公開","固定","A sample description","A sample terms of use","Grade-2","category-1","Easy","New question","New question test","3","試験が正常に作成されました"}
				 };
		return validexamcreate;
	}
	
			@DataProvider(name = "examcreate1")
			public Object[] validExamCreate() 
			{
				Object validexamcreate [][]=  { { "Examamal","10","一般公開","固定","A sample description","A sample terms of use","Grade-2","category-1","Easy","New question","New question test","3","試験が正常に作成されました"},
						{ }, };
				return validexamcreate;
			}
			
		
	
	@DataProvider(name = "examcreate2")
	public Object[] validExamCreate_2() 
	{
		Object validexamcreate [][]=  { { "Examamal","10","一般公開","期間","A sample description","A sample terms of use","Grade-2","category-1","Easy","New question","New question test","3","試験が正常に作成されました"},
				{ }, };
		return validexamcreate;
	}
	@DataProvider(name = "examcreate3")
	public Object[] validExamCreate_3() 
	{
		Object validexamcreate [][]=  { { "Examamal","10","一般公開","固定","A sample description","A sample terms of use","Grade-2","category-1","Easy","New question","New question test","3","試験が正常に作成されました"},
				{ }, };
		return validexamcreate;
	}
	@DataProvider(name = "examcreate4")
	public Object[] validExamCreate_4() 
	{
		Object validexamcreate [][]=  { { "Examamal","10","限定公開","期間","A sample description","A sample terms of use","Grade-2","category-1","Easy","New question","New question test","3","試験が正常に作成されました"},
				{ }, };
		return validexamcreate;
	}
	
	@DataProvider(name = "examcreate5")
	public Object[] validExamCreate_5() 
	{
		Object validexamcreate [][]=  { { "Examamal","10","限定公開","期間","A sample description","A sample terms of use","Grade-2","category-1","Easy","New question test","3","試験が正常に作成されました"},
				{ }, };
		return validexamcreate;
	}
	
	
	@DataProvider(name = "multiplequestion1")
	public Object[] multiplequestion1() 
	{
		Object validexamcreate [][]=  { { "Examamal","10","一般公開","固定","A sample description","A sample terms of use","Grade-2","category-1","Easy"},
				{ }, };
		return validexamcreate;
	}
	
	@DataProvider(name = "multiplequestion")
	public  Object[] multiplequestion()
	{
	 Object multiplequestion [][]=  { { "test text question","New question test","Test single answer question type 1"},
				{ }, };
		return multiplequestion;
	}
	
	
	@DataProvider(name = "multiplequestion2")
	public Object[] multiplequestion2() 
	{
		Object validexamcreate [][]=  { { "3","試験が正常に作成されました"},
				{ }, };
		return validexamcreate;
	}
	
	@DataProvider(name = "createdel")
	public Object[] createDel() 
	{
		Object validexamcreate [][]=  { { "Examamal","10","限定公開","期間","A sample description","A sample terms of use","Grade-2","category-1","Easy","New question test","3","問題は正常に更新されました","New question test"},
				{ }, };
		return validexamcreate;
	}
	
	@DataProvider(name = "examcreatmkupdt")
	public Object[] examCreatMkUpdt() 
	{
		Object validexamcreate [][]=  { { "Examamal","10","一般公開","固定","A sample description","A sample terms of use","Grade-2","category-1","Easy","New question","New question test","3","問題は正常に更新されました"},
				{ }, };
		return validexamcreate;
	}
	
	@DataProvider(name = "examcreatenotitle")
	public Object[] examCreatenotitle() 
	{
		Object validexamcreate [][]=  { { "","10","一般公開","固定","A sample description","A sample terms of use","Grade-2","category-1","Easy","New question","New question test","3","Please fill in this field."},
				{ }, };
		return validexamcreate;
	}
	@DataProvider(name = "noduration")
	public Object[] noDuration() 
	{
		Object validexamcreate [][]=  { { "AmalTitle","","一般公開","固定","A sample description","A sample terms of use","Grade-2","category-1","Easy","New question","New question test","3","Please fill in this field."},
				{ }, };
		return validexamcreate;
	}
	@DataProvider(name = "noexamtype")
	public Object[] noExamtype() 
	{
		Object validexamcreate [][]=  { { "Amaltitle","10","","固定","A sample description","A sample terms of use","Grade-2","category-1","Easy","New question","New question test","3","Please fill in this field."},
				{ }, };
		return validexamcreate;
	}
	@DataProvider(name = "noschedule")
	public Object[] noSchedule() 
	{
		Object validexamcreate [][]=  { { "Amaltitle","10","一般公開","","A sample description","A sample terms of use","Grade-2","category-1","Easy","New question","New question test","3","Please fill in this field."},
				{ }, };
		return validexamcreate;
	}
	
	
	@DataProvider(name = "zeropassmark")
	public Object[] zeropassmark() 
	{
		Object validexamcreate [][]=  { { "Examamal","10","一般公開","固定","A sample description","A sample terms of use","Grade-2","category-1","Easy","New question","New question test","0","合格点は 0 より大きくなければなりません"},
				{ }, };
		return validexamcreate;
	}
	
	@DataProvider(name = "morepassmark")
	public Object[] morepassmark() 
	{
		Object validexamcreate [][]=  { { "Examamal","10","一般公開","固定","A sample description","A sample terms of use","Grade-2","category-1","Easy","test text question","New question test","Test single answer question type 1","3000","合格点は、合計点よりも小さくなければなりません"},
				{ }, };
		return validexamcreate;
	}
	
		
	@DataProvider(name="preview")
	public Object[] preview() 
	{
		Object validexamcreate [][]=  { { "Examamal","10","一般公開","固定","A sample description","A sample terms of use","Grade-2","category-1","Easy","New question","test text question","New question test","Test single answer question type 1","3000","問題は正常に更新されました"},
				{ }, };
		return validexamcreate;
	}
	@DataProvider(name="edit1")
	
		public Object[] edit1() 
		{
			Object validexamcreate [][]=  { {"Examamal1", "Examamal","0","限定公開","期間","A sample description","A sample terms of use","Grade-2","category-2","Easy","放送を聞いて答える問題1","1","試験が正常に作成されました","New question test"},
					{ }, };
			return validexamcreate;
		}
		

		


		
		
}
