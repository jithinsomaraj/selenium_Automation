package studentDashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StudentDashboardPage {

	private WebDriver driver;
	private By PageTitle = By.xpath("//div[contains(@class,'MuiPaper-root MuiTableContainer-root')]");
	private By WelcomeTextElement = By.xpath("//span[contains(@class,'MuiTypography-root jss14')]");
	private By Todaysexam = By.cssSelector(("div:nth-of-type(2) > h4:nth-of-type(1)"));
    private By upcomingexam = By.xpath(("//h4[text()='今後の試験']"));
	private By SettingMenu = By.cssSelector(".MuiSvgIcon-root.jss13");
	private By Dashboardbtn = By.xpath(("//h1[text()='ã‚ªãƒ³ãƒ©ã‚¤ãƒ³è©¦é¨“']"));
	private By LogoutLink = By.xpath("//div[@id='profile-menu']//ul[@role='menu']/li[3]");
	private By exam1 = By.xpath(("//div[text()='This is an exam for jithin']"));
	private By exam2 = By.xpath(("//div[text()='Final_20200917']"));
	private By unregexam = By.xpath(("//div[text()='This is an exam for jithin-2']"));
	private By startdate = By.cssSelector(".MuiBox-root.jss52 > p:nth-of-type(1)");
	private By attendbtn =By.xpath("//div[text()='参加 ']");
	private By TimeDur =By.cssSelector(".MuiBox-root.jss52 > p:nth-of-type(3)");
	private By enddate = By.cssSelector((".MuiBox-root.jss52 > p:nth-of-type(2)"));
	private By regbtn =By.xpath("//div[text()='登録']");
	private By regconfirm = By.cssSelector(".MuiButton-contained.MuiButton-containedSecondary.MuiButton-root.MuiButtonBase-root.jss25");
	private By Descr =By.xpath("//h2[contains(text(),'利用規約')]");
	private By Termscond =By.xpath(".//*[@class='MuiDialogContent-root']");
	private By tlc =  By.xpath(".//*[@class='MuiDialogContent-root']//h6");
	private By cancelbtn =By.xpath("//span[contains(text(),'キャンセル')]");
	private By upcomingtitle =By.cssSelector(".MuiTableRow-head [scope='col']:nth-of-type(1)");
	private By upcomingdurtn =By.cssSelector(".MuiTableRow-head.MuiTableRow-root > th:nth-of-type(2)");
	private By upcomingstart =By.cssSelector(".MuiTableRow-head.MuiTableRow-root > th:nth-of-type(3)");
	private By upcomingend =By.cssSelector(".MuiTableRow-head.MuiTableRow-root > th:nth-of-type(4)");
	private By upcomingapply =By.cssSelector(".MuiTableRow-head.MuiTableRow-root > th:nth-of-type(5)");
	private By upcomingshowall =  By.xpath(".//*[@href='/upcoming']");
	private By upcomingshowallclk =By.xpath("//th[text()='ã�™ã�¹ã�¦è¡¨ç¤º']");
    private By Applybutton =By.xpath("(.//*[@class='MuiSvgIcon-root'])[5]");
	private By Applybutton1 =By.cssSelector(".MuiTableBody-root .MuiTableRow-root:nth-of-type(1) [focusable]");
	private By Applyconfirm = By.xpath("//span[contains(text(),'登録')]");
	private By tickbutton =By.cssSelector(".MuiTableBody-root .MuiTableRow-root:nth-of-type(1) [focusable]");
	private By pagecheck =By.xpath("//p[text()='1-5 of 6']");


	

	







	public StudentDashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public By PageTitleBy() {
		return PageTitle;
	}
	
	public String getWelocmeText() {
	String WelcomeText = driver.findElement(WelcomeTextElement).getText().trim();
	return WelcomeText;
	}
	public String getTodaysexam() {
		String Todayexam = driver.findElement(Todaysexam).getText().trim();
		return Todayexam;
	}
	public String getupcomingexam() {
		String upcomingexam1 = driver.findElement(upcomingexam).getText().trim();
		return upcomingexam1;
	}
	
	public void clickdasboard() {
		driver.findElement(Dashboardbtn).click();}
	
	public By exam1By() {
		return exam1;
	}
	
	
	public boolean isExamPresent(String Exam) {
	    try {
	        driver.findElement(By.cssSelector("div[title='"+Exam+"']"));
	        return true;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }
	}
	
	
	public void RegisterExam(String Exam) {
		String MyExam=Exam;
		  for(int i=1;i<20;i++) {
			String examname = "//*[@id=\"root\"]/div/main/div[2]/div[1]/div/div/div["+i+"]/div/div/div[1]";
			WebElement  gt= driver.findElement(By.xpath(examname));
			String ActExam= gt.getText();
			if(MyExam.equals(ActExam)){
				String RegBtn = "//*[@id=\"root\"]/div/main/div[2]/div[1]/div/div/div["+i+"]/div/div/div[3]/div/div/button/span[1]/div";
				WebElement  gt1= driver.findElement(By.xpath(RegBtn));
				String Btn=gt1.getText();
				if(Btn.equals("参加")) {
					break;
				}else {
					
				gt1.click();
			    clickregconfirm();
				break;}}}
		}
	
	
	public String getBttntext() {
		
	 String Btn =driver.findElement(By.xpath(("//*[@id=\"root\"]/div/main/div[2]/div[1]/div/div/div/div/div/div[3]/div/div/button/span[1]"))).getText().trim();
	return 	Btn;
		
	}
	
	public String getexam0() {
		String myexam1 = driver.findElement(exam1).getText().trim();
		return myexam1;}

	public String getexam2() {
		String myexam2 = driver.findElement(exam2).getText().trim();
		return myexam2;}
	
	public String getexam1() {
		String exam1 = driver.findElement(upcomingexam).getText().trim();
		return exam1;
	}
	
	public String getunregexam() {
		String unexam = driver.findElement(unregexam).getText().trim();
		return unexam;
	}
	

	public String getstartdate() {
		String startdat = driver.findElement(startdate).getText().trim();
		return startdat;
	}
	
	public String getenddate() {
		String enddat = driver.findElement(enddate).getText().trim();
		return enddat;
	}
	
	
	public String getregbtntext() {
		String rebtntxt = driver.findElement(regbtn).getText().trim();
		return rebtntxt;
	}
	
	
	public By attendbtnBy() {
		return attendbtn;
	}
	
	public String getattendtext() {
		String atttntxt = driver.findElement(attendbtn).getText().trim();
		return atttntxt;
	}
	
	public void clickattend() {
		driver.findElement(attendbtn).click();
		
	}
	public By TimeDurBy() {
		return TimeDur;
	}
	
	public String gettimeduration() {
		String duration = driver.findElement(TimeDur).getText().trim();
		return duration;
	}
	
	public By regbtnnBy() {
		return regbtn;
	}
	
	public void clickreg() {
		driver.findElement(regbtn).click();
	}
	
	public By regconfirmBy() {
		return regconfirm;
	}
	
	
	
	public void clickregconfirm() {
		driver.findElement(regconfirm).click();
	}
	
	public By DescrBy() {
		return Descr;
	}
	
	public String getDescrt() {
		String desc = driver.findElement(Descr).getText().trim();
		return desc;
	}
	
	
	public By TermscondBy() {
		return Termscond;
	}
	
	public String getTermscond() {
		String term = driver.findElement(Termscond).getText().trim();
		return term;
	}
	
	public By tlcBy() {
		return tlc;
	}
	
	public String gettlc() {
		String timelft = driver.findElement(tlc).getText().trim();
		return timelft;
	}
	

	public By cancelbtnBy() {
		return cancelbtn;
	}

	public void clickcancel() {
		driver.findElement(cancelbtn).click();
	}
	
	public String getcancel() {
		String cancl = driver.findElement(cancelbtn).getText().trim();
		return cancl;
	}
	

	public By upcomingtitlenBy() {
		return upcomingtitle;
	}
	
	public String getupcomingtitle() {
		String uptitle = driver.findElement(upcomingtitle).getText().trim();
		return uptitle;
	}
	
	public By upcomingdurtnBy() {
		return upcomingdurtn;
	}
	
	public String getupcomingdurtn() {
		String updur = driver.findElement(upcomingdurtn).getText().trim();
		return updur;
	}
	
	public By upcomingstartBy() {
		return upcomingstart;
	}
	
	public String getupcomingstart() {
		String upstrt = driver.findElement(upcomingstart).getText().trim();
		return upstrt;
	}
	
	public By upcomingendBy() {
		return upcomingend;
	}
	
	public String getupcomingend() {
		String upend = driver.findElement(upcomingend).getText().trim();
		return upend;
	}
	
	public By upcomingapplyBy() {
		return upcomingapply;
	}
	
	
	public String getupcomingapply() {
		String upapply = driver.findElement(upcomingapply).getText().trim();
		return upapply;
	}
	public By upcomingexamBy() {
		return upcomingexam;
	}
	
	public String getupcomingexamsrt() {
		String upexm = driver.findElement(upcomingexam).getText().trim();
		return upexm;
	}
	
	public By upcomingshowallBy() {
		return upcomingshowall;
	}
	
	public String getupcomingshow() {
		String showall = driver.findElement(upcomingshowall).getText().trim();
		return showall;
	}
	
	public By ApplybuttonBy() {
		return Applybutton;
	}
	
	public String getapplybutton() {
		String applybt = driver.findElement(Applybutton).getText().trim();
		return applybt;
	}
	
	
	public void ApplyExam(String Exam) {
		String UPexam = Exam;
		for(int i=1;i<20;i++) {
			String examname = "tr:nth-of-type("+i+") > th[role='cell'] > b";
			WebElement  gt= driver.findElement(By.cssSelector(examname));
			String actName = gt.getText();
			System.out.println(gt);
			if(actName.equals(UPexam)){
				String ApplyBtn = "tr:nth-of-type("+i+") > td:nth-of-type(4) > .MuiButtonBase-root.MuiIconButton-root";
				WebElement  gt1= driver.findElement(By.cssSelector(ApplyBtn));
				gt1.click();
				clickapplybtnconfirm();
				break;
    	    
    			}
					
		}
	}
	
	
	
	
	
	public void clickApplybutton() {
		driver.findElement(Applybutton1).click();
		
			
	}
	

	public By  ApplyconfirmBy() {
		return Applyconfirm;
	}
	public void clickapplybtnconfirm() {
		driver.findElement(Applyconfirm).click();
	}
	

	public By tickbuttonBy() {
		return tickbutton;
	}
	public boolean istickpresent() {
	    try {
	        driver.findElement(tickbutton);
	        return true;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }
	}
	

	public void clickshowall() {
		driver.findElement(upcomingshowallclk).click();
	}
	
	
	public String getpagecheck() {
		String pagination = driver.findElement(pagecheck).getText().trim();
		return pagination;
		}
	

	public void logout() {
		driver.findElement(SettingMenu).click();
		driver.findElement(LogoutLink).click();	
			
	}
}
