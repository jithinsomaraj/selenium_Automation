package examCreate;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;

public class ExamCreatePage {

	private WebDriver driver;
	private By questionbutton1=By.cssSelector("div:nth-of-type(3)  .MuiSvgIcon-colorSecondary.MuiSvgIcon-fontSizeSmall.MuiSvgIcon-root > path");
	private By questionbutton2=By.xpath("/html/body/div[1]/div/main/div[2]/div[1]/div[1]/a/button");
	private By createbutton1 = By.xpath("/html/body/div[1]/div/main/form/div[8]/div[2]/div[3]/button");
	private By createbutton2=By.xpath("//*[@id=\"root\"]/div/main/form/div[8]/div[2]/div[3]");
	private By examname=By.id("name");
	private By testtime=By.id("duration");
	private By selectexamtype=By.id("select-exam-type");
	private By selectexamschedule=By.id("select-exam-schedule");
	private By description=By.xpath("//*[@id=\"root\"]/div/main/form/div[2]/div[2]/div[2]/div");
	private By description2=By.xpath("//*[@id=\"root\"]/div/main/form/div[2]/div[2]/div[2]/div/p");
	private By termsofuse=By.xpath("/html/body/div[1]/div/main/form/div[3]/div[2]/div[2]/div");
	private By addproblembutton=By.xpath("//*[@id=\"root\"]/div/main/form/div[4]/button[1]");
	private By cancelbutton=By.xpath("//*[@id=\"root\"]/div/main/form/div[8]/div[2]/div[2]/a/button");
	private By previewbutton=By.xpath("//*[@id=\"root\"]/div/main/form/div[8]/div[2]/div[4]/button/span[1]");
	//*[@id="root"]/div/main/form/div[8]/div[2]/div[4]/button/span[1]
	private By showquestions=By.xpath("//*[@id=\"root\"]/div/main/form/div[4]/button[2]/span");
	private By cancelbuttonshowquestions=By.xpath("/html/body/div[7]/div[3]/div/div[4]/button[1]/span[1]");
	
	//Add question- variables 
	private By searchinquestion=By.id("titleSearch");
	private By group=By.id("select-grade");
	private By category=By.id("select-catagory");
	private By difficulty=By.id("select-difficulty");
	private By questionlist=By.xpath("/html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr/td[1]");
	private By questionlist2=By.xpath("/html/body/div[5]/div[3]/div/div[3]/div/table");
	//private By questionlistn=By.xpath("/html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr[1]/td[1]");
	//private By questionlistr=By.xpath("/html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr[1]/th[1]");
	private By questionlistokbutton=By.xpath("/html/body/div[5]/div[3]/div/div[4]/button[2]");
	//private By questionlistcancelbutton=By.xpath("/html/body/div[5]/div[3]/div/div[4]/button[1]/span[1]");

	private By updatebutton=By.xpath("/html/body/div[5]/div[3]/div/div[4]/button[2]");
	//after adding question
	
	private By qualifyingmarks=By.id("passmark");
	private By reactAlert = By.id("__react-alert__");
	
	//uncheck
	private By uncheck=By.xpath("//*[@id=\"root\"]/div/main/form/div[7]/div/div/h6/span/span[1]/input");
	
	private By title=By.id("name");
	
	
	//Edit question
	private By searchedit=By.id("titleSearch");
	private By editbutton=By.xpath("/html/body/div[1]/div/main/div[2]/div[2]/table/tbody/tr[1]/td[6]/svg[1]");
	
	private By editbutton1=By.cssSelector("tr:nth-of-type(1) > td:nth-of-type(6) > svg:nth-of-type(1)");
	
	
	public ExamCreatePage(WebDriver driver) 
	{
		this.driver = driver;
	}
	
	public void questionButton1()
	{
		driver.findElement(questionbutton1).click();
	}
	public void questionButton2()
	{
		driver.findElement(questionbutton2).click();
	}
	
	
	public void createButton1()
	{WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(createbutton1));
		
		driver.findElement(createbutton1).click();
	}
	
	
	public void examName(String examname)
	{
		driver.findElement(this.examname).sendKeys(examname);
	}
	
	public void testTime(String testtime)
	{   driver.findElement(this.testtime).clear();
		driver.findElement(this.testtime).sendKeys(testtime);
	}
	
	public void selectExamType(String selectexamtype) throws InterruptedException
	{   driver.findElement(this.selectexamtype).click();
	
	WebElement dropdown = driver.findElement(By.cssSelector("ul[role='listbox']"));
	Thread.sleep(500);
	List<WebElement> options = dropdown.findElements(By.tagName("li"));
	for (WebElement option : options)
	{
	    if (option.getText().equals(selectexamtype))
	    {
	        option.click(); // click the desired option
	        break;
	    }
	}
	
	}
	public void selectExamSchedule(String selectexamschedule) throws InterruptedException
{   driver.findElement(this.selectexamschedule).click();
	
	WebElement dropdown = driver.findElement(By.cssSelector("ul[role='listbox']"));
	
	Thread.sleep(3000);
	List<WebElement> options = dropdown.findElements(By.tagName("li"));
	for (WebElement option : options)
	{
	    if (option.getText().equals(selectexamschedule))
	    {
	        option.click(); // click the desired option
	        break;
	    }
	}
	
	}
	
	public void description(String description)
	{   
		driver.findElement(this.description).click();
		driver.findElement(this.description2).sendKeys(description);
	}
	
	public void termsOfUse(String termsofuse)
	{
		driver.findElement(this.termsofuse).sendKeys(termsofuse);
    }
	public void addProblemButton()
	{
		driver.findElement(this.addproblembutton).click();
	}
	public void createButton2()
	{
		driver.findElement(createbutton2).click();	
	}
	
	//Question list dropdowns
	
	
	public void group(String group) throws InterruptedException
{   
		driver.findElement(this.group).click();

	WebElement dropdown = driver.findElement(By.cssSelector("ul[role='listbox']"));
	Thread.sleep(3000);
	List<WebElement> options = dropdown.findElements(By.tagName("li"));
	for (WebElement option : options)
	{
	    if (option.getText().equals(group))
	    {
	        option.click(); // click the desired option
	        break;
	    }
	}
	
	}
	public void category(String category) throws InterruptedException
{   driver.findElement(this.category).click();
	
	WebElement dropdown = driver.findElement(By.cssSelector("ul[role='listbox']"));
	//dropdown.click(); // assuming you have to click the "dropdown" to open it
	Thread.sleep(3000);
	List<WebElement> options = dropdown.findElements(By.tagName("li"));
	for (WebElement option : options)
	{
	    if (option.getText().equals(category))
	    {
	        option.click(); // click the desired option
	        break;
	    }
	}
	
	}
	public void difficulty(String difficulty) throws InterruptedException
{   driver.findElement(this.difficulty).click();
	
	WebElement dropdown = driver.findElement(By.cssSelector("ul[role='listbox']"));
	Thread.sleep(3000);
	List<WebElement> options = dropdown.findElements(By.tagName("li"));
	for (WebElement option : options)
	{
	    if (option.getText().equals(difficulty))
	    {
	        option.click(); // click the desired option
	        break;
	    }
	}
	}
	
	//Question check box select
	
		public void quesionList(String qname) 
		{
			List<WebElement> tableRows = driver.findElements(questionlist);
			int size = tableRows.size();
			
			WebDriverWait wait = new WebDriverWait(driver, 15);
		
			for (int i = 1; i <= size; i++) 
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr["+i+"]/th[1]")));
				
				if (driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr["+i+"]/th[1]")).getText().equals(qname))		
					
				{
					driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr["+i+"]/td[1]")).click();
				}
				
				
			}
				
			}
		
			public void questionListokButton()
			{
				driver.findElement(questionlistokbutton).click();
			}
			
			public void qualifyingMark(String passmark)
			{
				driver.findElement(qualifyingmarks).sendKeys(passmark);
			}
			
			public String  getReactAlertMessage() throws InterruptedException {

				WebDriverWait wait = new WebDriverWait(driver, 15);
				wait.until(ExpectedConditions.visibilityOfElementLocated(reactAlert));
				  Thread.sleep(2000);
				String Alert= driver.findElement(reactAlert).getText();
				//System.out.println("This amnd alertttttt"+Alert);
				return Alert;
			}
			
			public void uncheck()
			
				{
					driver.findElement(uncheck).click();
				}
			public void showquestion()
			{
				driver.findElement(showquestions).click();		
				
			}
					public void cancel()
					{
					driver.findElement(cancelbuttonshowquestions).click();
					}
					
			public void searchinquestion(String searchword)
			{
				driver.findElement(searchinquestion).click();
				driver.findElement(searchinquestion).sendKeys(searchword);
			}
		public void cancelexam()
		{
			driver.findElement(cancelbutton).click();
		}
		
		public String  inipage() 
		{
			
		WebElement TxtBoxContent = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/h4"));
		return(TxtBoxContent.getText());
		
		}
		public void removequestion(String delname)
		{
			{
				List<WebElement> tableRows = driver.findElements(questionlist);
				int size = tableRows.size();
				
				WebDriverWait wait = new WebDriverWait(driver, 25);
			
				for (int i = 1; i <= size; i++) 
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr["+i+"]/th[1]")));
					
					if (driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr["+i+"]/td[1]")).getText().equals(delname))		
						
					{
						driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr["+i+"]/td[5]")).click();
					break;
					}
					
					
				}
		}
		
		}
		
		public void updatethemark(String qname)
		{
			{
				List<WebElement> tableRows = driver.findElements(questionlist);
				int size = tableRows.size();
				
				WebDriverWait wait = new WebDriverWait(driver, 15);
			
				for (int i = 1; i <= size; i++) 
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr["+i+"]/th[1]")));
					
					if (driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr["+i+"]/td[1]")).getText().equals(qname))		
						
					{   driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr["+i+"]/td[4]/div/div/input")).click();
						driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr["+i+"]/td[4]/div/div/input")).clear();
						driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr["+i+"]/td[4]/div/div/input")).sendKeys("2");
					}
					
					
				}
		}
		}
		
		public WebElement getTitle()
		{	
			return driver.findElement(title);
		}
		public WebElement getDuration()
		{
			return driver.findElement(By.id("duration"));
		}
		public WebElement getExamType()
		{
			return driver.findElement(By.id("select-exam-type"));
		}
		public WebElement getExamSchedule()
		{
			return driver.findElement(By.id("select-exam-schedule"));
		}
		public WebElement zeroMark()
		{
			/*driver.findElement(qualifyingmarks).clear();
			driver.findElement(qualifyingmarks).click();
			driver.findElement(qualifyingmarks).sendKeys(Keys.CONTROL + "a");
			driver.findElement(qualifyingmarks).sendKeys(Keys.DELETE); */
			
			return driver.findElement(By.id("passmark-helper-text"));
		}
		public WebElement moreMark()
		{			
				return driver.findElement(By.id("passmark-helper-text"));
		}
		public void clearmark()
		{
			driver.findElement(qualifyingmarks).click();
			driver.findElement(qualifyingmarks).sendKeys(Keys.CONTROL + "a");
			driver.findElement(qualifyingmarks).sendKeys(Keys.DELETE);
		}
		public void verifyPreview(String q[]) throws InterruptedException
		{
			Thread.sleep(1000);

			driver.findElement(previewbutton).click();
			
			Thread.sleep(1000);
			for(int b=1;b<=3;b++)
			{
				Thread.sleep(1000);
			driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div[2]/div[1]/div/div/div[1]/button["+b+"]/span[1]/span")).click();
			Thread.sleep(1000);
			String questioname=driver.findElement(By.cssSelector(".MuiGrid-align-items-xs-flex-start .MuiTypography-root")).getText();			
			Assert.assertEquals(questioname,q[b-1], "Question is wrong in preview");
			
			}
			driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/header/div/button[1]")).click();
	}
			public void createExam(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String Message) throws InterruptedException
			{  
				Thread.sleep(1000);
				questionButton1();
				Thread.sleep(1000);
				questionButton2();		
				Thread.sleep(1000);				
				examName(examname);
				Thread.sleep(1000);
				testTime(testtime);
				selectExamType(selectexamtype);
				Thread.sleep(1000);
				selectExamSchedule(selectexamschedule);
				Thread.sleep(1000);
				description(description);
				Thread.sleep(1000);
				termsOfUse(termsofuse);
				Thread.sleep(1000);
				addProblemButton();
				Thread.sleep(1000);
				//createButton1();
				Thread.sleep(1000);
				group(group);
				Thread.sleep(1000);
				category(category);
				Thread.sleep(1000);
				difficulty(difficulty);
				Thread.sleep(3000);
				//searchinquestion(searchword);
				Thread.sleep(3000);
				quesionList(qname);
				Thread.sleep(3000);				
				questionListokButton();
				Thread.sleep(3000);
				clearmark();
				
				Thread.sleep(3000);
				qualifyingMark(passmark);	
				//showquestion();
				//cancel();
				Thread.sleep(3000);
				createButton1();
				
			}
			
			public void createExam_uncheck(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String Message) throws InterruptedException
			{   Thread.sleep(1000);
				questionButton1();
				Thread.sleep(1000);
				questionButton2();		
				Thread.sleep(1000);				
				examName(examname);
				Thread.sleep(1000);
				testTime(testtime);
				selectExamType(selectexamtype);
				Thread.sleep(1000);
				selectExamSchedule(selectexamschedule);
				Thread.sleep(1000);
				description(description);
				Thread.sleep(1000);
				termsOfUse(termsofuse);
				Thread.sleep(1000);
				addProblemButton();
				Thread.sleep(1000);
				//createButton1();
				Thread.sleep(1000);
				group(group);
				Thread.sleep(1000);
				category(category);
				Thread.sleep(1000);
				difficulty(difficulty);
				Thread.sleep(3000);
				searchinquestion(searchword);
				Thread.sleep(3000);
				quesionList(qname);
				Thread.sleep(3000);				
				questionListokButton();
				Thread.sleep(3000);
				qualifyingMark(passmark);
				uncheck();
				Thread.sleep(3000);
				createButton1();
				
			}
			public void createExam_cancelingcreate(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String qname,String passmark,String Message) throws InterruptedException
			{  
				
				
				Thread.sleep(1000);
				questionButton1();
				Thread.sleep(1000);
				questionButton2();		
				Thread.sleep(1000);				
				examName(examname);
				Thread.sleep(1000);
				testTime(testtime);
				selectExamType(selectexamtype);
				Thread.sleep(1000);
				selectExamSchedule(selectexamschedule);
				Thread.sleep(1000);
				description(description);
				Thread.sleep(1000);
				termsOfUse(termsofuse);
				Thread.sleep(1000);
				addProblemButton();
				Thread.sleep(1000);
				//createButton1();
				Thread.sleep(1000);
				group(group);
				Thread.sleep(1000);
				category(category);
				Thread.sleep(1000);
				difficulty(difficulty);
				Thread.sleep(3000);
				quesionList(qname);
				Thread.sleep(3000);				
				questionListokButton();
				Thread.sleep(3000);
				qualifyingMark(passmark);
				uncheck();
				Thread.sleep(3000);
				cancelexam();
				
			}
			
			public void addmultiplequestion1(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty) throws InterruptedException
			{
				Thread.sleep(1000);
				questionButton1();
				Thread.sleep(1000);
				questionButton2();		
				Thread.sleep(1000);				
				examName(examname);
				Thread.sleep(1000);
				testTime(testtime);
				selectExamType(selectexamtype);
				Thread.sleep(1000);
				selectExamSchedule(selectexamschedule);
				Thread.sleep(1000);
				description(description);
				Thread.sleep(1000);
				termsOfUse(termsofuse);
				Thread.sleep(1000);
				addProblemButton();
				Thread.sleep(1000);
				//createButton1();
				Thread.sleep(1000);
				group(group);
				Thread.sleep(1000);
				category(category);
				Thread.sleep(1000);
				difficulty(difficulty);
				Thread.sleep(3000);
			}
			
			
			public void addmultiplequestion(String qname)
			{
				
				
				quesionList(qname);	
				
				
			}
			public void addmultiplequestion2(String passmark,String Message) throws InterruptedException
			{
			
			Thread.sleep(3000);				
			questionListokButton();
			Thread.sleep(3000);
			
			qualifyingMark(passmark);
			uncheck();
			Thread.sleep(3000);
			Thread.sleep(3000);
			createButton1();
			
		}
			
			public void createExamMrkUpdt(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String qname,String passmark,String Message,String delname) throws InterruptedException
			{  
				Thread.sleep(1000);
				questionButton1();
				Thread.sleep(1000);
				questionButton2();		
				Thread.sleep(1000);				
				examName(examname);
				Thread.sleep(1000);
				testTime(testtime);
				selectExamType(selectexamtype);
				Thread.sleep(1000);
				selectExamSchedule(selectexamschedule);
				Thread.sleep(1000);
				description(description);
				Thread.sleep(1000);
				termsOfUse(termsofuse);
				Thread.sleep(1000);
				addProblemButton();
				Thread.sleep(1000);
				
				group(group);
				Thread.sleep(1000);
				category(category);
				Thread.sleep(1000);
				difficulty(difficulty);
				Thread.sleep(3000);
				
				Thread.sleep(3000);
				quesionList(qname);
				Thread.sleep(3000);				
				questionListokButton();
				Thread.sleep(3000);
				qualifyingMark(passmark);	
				showquestion();
				removequestion(delname);
				driver.findElement(updatebutton).click();
				Thread.sleep(3000);
				
			}
			public void createExamDel(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String qname,String passmark,String Message,String delname) throws InterruptedException
			{  
				Thread.sleep(1000);
				questionButton1();
				Thread.sleep(1000);
				questionButton2();		
				Thread.sleep(1000);				
				examName(examname);
				Thread.sleep(1000);
				testTime(testtime);
				selectExamType(selectexamtype);
				Thread.sleep(1000);
				selectExamSchedule(selectexamschedule);
				Thread.sleep(1000);
				description(description);
				Thread.sleep(1000);
				termsOfUse(termsofuse);
				Thread.sleep(1000);
				addProblemButton();
				Thread.sleep(1000);
				
				group(group);
				Thread.sleep(1000);
				category(category);
				Thread.sleep(1000);
				difficulty(difficulty);
				Thread.sleep(3000);
				
				Thread.sleep(3000);
				quesionList(qname);
				Thread.sleep(3000);				
				questionListokButton();
				Thread.sleep(3000);
				qualifyingMark(passmark);	
				showquestion();
				
				driver.findElement(updatebutton).click();
				Thread.sleep(3000);
				
			}
			public void createExamMkupdt(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String Message) throws InterruptedException
			{  
				Thread.sleep(1000);
				questionButton1();
				Thread.sleep(1000);
				questionButton2();		
				Thread.sleep(1000);				
				examName(examname);
				Thread.sleep(1000);
				testTime(testtime);
				selectExamType(selectexamtype);
				Thread.sleep(1000);
				selectExamSchedule(selectexamschedule);
				Thread.sleep(1000);
				description(description);
				Thread.sleep(1000);
				termsOfUse(termsofuse);
				Thread.sleep(1000);
				addProblemButton();
				Thread.sleep(1000);
				
				group(group);
				Thread.sleep(1000);
				category(category);
				Thread.sleep(1000);
				difficulty(difficulty);
				Thread.sleep(3000);
				searchinquestion(searchword);
				Thread.sleep(3000);
				quesionList(qname);
				Thread.sleep(3000);				
				questionListokButton();
				Thread.sleep(3000);
				qualifyingMark(passmark);	
				showquestion();
				Thread.sleep(3000);
				updatethemark(qname);
				Thread.sleep(3000);
				driver.findElement(updatebutton).click();
				Thread.sleep(3000);
				
			}
			
		public void edit(String searchcd,String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String qname,String passmark,String Message,String delname) throws InterruptedException
		{
			Thread.sleep(3000);

			
			questionButton1();
			
			driver.findElement(searchedit).click();
			driver.findElement(searchedit).sendKeys(searchcd);
			Thread.sleep(3000);

			driver.findElement(editbutton1).click();
			Thread.sleep(1000);
			
			Thread.sleep(1000);				
			examName(examname);
			Thread.sleep(1000);
			testTime(testtime);
			selectExamType(selectexamtype);
			Thread.sleep(1000);
			selectExamSchedule(selectexamschedule);
			Thread.sleep(1000);
			description(description);
			Thread.sleep(1000);
			termsOfUse(termsofuse);
			Thread.sleep(1000);
			addProblemButton();
			Thread.sleep(1000);
			
			group(group);
			Thread.sleep(1000);
			category(category);
			Thread.sleep(1000);
			difficulty(difficulty);
			Thread.sleep(3000);
			
			Thread.sleep(3000);
			quesionList(qname);
			Thread.sleep(3000);	
			
			questionListokButton();
			Thread.sleep(3000);
			clearmark();
			qualifyingMark(passmark);
			Thread.sleep(3000);				
			showquestion();
			Thread.sleep(3000);				
			removequestion(delname);
			driver.findElement(updatebutton).click();
			Thread.sleep(5000);

			createButton1();
		}
		
		
		
		public void editExamQor(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String Message) throws InterruptedException
		{  
			Thread.sleep(1000);
			questionButton1();
			Thread.sleep(1000);
			questionButton2();		
			Thread.sleep(1000);				
			examName(examname);
			Thread.sleep(1000);
			testTime(testtime);
			selectExamType(selectexamtype);
			Thread.sleep(1000);
			selectExamSchedule(selectexamschedule);
			Thread.sleep(1000);
			description(description);
			Thread.sleep(1000);
			termsOfUse(termsofuse);
			Thread.sleep(1000);
			addProblemButton();
			Thread.sleep(1000);
			//createButton1();
			Thread.sleep(1000);
			group(group);
			Thread.sleep(1000);
			category(category);
			Thread.sleep(1000);
			difficulty(difficulty);
			Thread.sleep(3000);
			//searchinquestion(searchword);
			Thread.sleep(3000);
			quesionList(qname);
			Thread.sleep(3000);				
			questionListokButton();
			Thread.sleep(3000);
			clearmark();
			
			Thread.sleep(3000);
			qualifyingMark(passmark);	
			//showquestion();
			//cancel();
			Thread.sleep(3000);
			createButton1();
			
		}

		public void editorderQ() throws InterruptedException {
			Thread.sleep(3000);
			driver.findElement(By.cssSelector(".MuiTableRow-root:nth-child(1) > .MuiTableCell-root:nth-child(7) > .MuiSvgIcon-root:nth-child(1)")).click();
			Thread.sleep(3000);
		    driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/form/div[4]/button[1]")).click();
		    Thread.sleep(3000);
			int i=1;
			while(i<=4) {
				driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr["+i+"]/td[1]/span")).click();
			i++;
			}
			Thread.sleep(3000);
			 driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[4]/button[2]")).click();
			 driver.findElement(By.cssSelector(".MuiButtonBase-root:nth-child(2) > .MuiButton-label")).click();
			 Thread.sleep(3000);
			 int j=1;
			 while(j<=4) {
				 driver.findElement(By.xpath(" /html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr[1]/th/div/div/input")).click();
				 driver.findElement(By.xpath(" /html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr[1]/th/div/div/input")).sendKeys(Keys.BACK_SPACE);
				    driver.findElement(By.xpath(" /html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr[1]/th/div/div/input")).sendKeys("5");
				    driver.findElement(By.xpath(" /html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr[1]/th/div/div/input")).sendKeys(Keys.ENTER);
				    j++;
			 }
			 driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[4]/button[2]")).click();
			 
		}
		
		public void createExamwithQuestion(String examname,String testtime,String selectexamtype,String selectexamschedule,String description,String termsofuse,String group,String category,String difficulty,String searchword,String qname,String passmark,String Message) throws InterruptedException
		{  
			Thread.sleep(1000);
			questionButton1();
			Thread.sleep(1000);
			questionButton2();		
			Thread.sleep(1000);				
			examName(examname);
			Thread.sleep(1000);
			testTime(testtime);
			selectExamType(selectexamtype);
			Thread.sleep(1000);
			selectExamSchedule(selectexamschedule);
			Thread.sleep(1000);
			description(description);
			Thread.sleep(1000);
			termsOfUse(termsofuse);
			Thread.sleep(1000);
			addProblemButton();
			Thread.sleep(1000);
			//createButton1();
			Thread.sleep(1000);
			group(group);
			Thread.sleep(1000);
			category(category);
			Thread.sleep(1000);
			int i=1;
			while(i<=6) {
				driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr["+i+"]/td[1]/span")).click();
			i++;
			}			
			Thread.sleep(3000);				
			questionListokButton();
			Thread.sleep(3000);
			clearmark();
			
			Thread.sleep(3000);
			qualifyingMark(passmark);	
			//showquestion();
			//cancel();
			Thread.sleep(3000);
			createButton1();
			
		}

		public void edtaddQ() throws InterruptedException {
			Thread.sleep(3000);
			driver.findElement(By.cssSelector(".MuiTableRow-root:nth-child(1) > .MuiTableCell-root:nth-child(7) > .MuiSvgIcon-root:nth-child(1)")).click();
			Thread.sleep(3000);
		    driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/form/div[4]/button[1]")).click();
		    Thread.sleep(3000);
		    Thread.sleep(1000);
			group("for automation");
			Thread.sleep(1000);
			category("for automation");
			Thread.sleep(1000);
		    int i=1;
			while(i<=6) {
				driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[3]/div/table/tbody/tr["+i+"]/td[1]/span")).click();
			i++;
			}
			Thread.sleep(3000);
			 driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[4]/button[2]")).click();
		}

		
}



