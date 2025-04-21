package UpcomingExams;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UpcomingExampage {
	private WebDriver driver;
	private By PageTitle = By.xpath("//div[contains(@class,'MuiPaper-root MuiTableContainer-root')]");
	private By UpcomingExambutton =  By.cssSelector(".jss15:nth-child(2)");
	private By WelcomeTextElement = By.xpath("//h4[text()='今後の試験']");
    private By Searchfield =  By.id("titleSearch");
    private By SearchResult = By.xpath("//b[text()='This is an exam for jithin']");
    private By upcomingtitle2 =By.xpath("//th[text()='題名']");
	private By upcomingdurtn2 =By.xpath("//th[text()='試験時間']");
	private By upcomingstart2 =By.xpath("//th[text()='期間開始']");
	private By upcomingend2 =By.xpath("//th[text()='期間終了']");
	private By upcomingapply2 =By.xpath("//th[text()='申し込む']");
	private By Applybutton =By.cssSelector(".MuiTableBody-root .MuiTableRow-root:nth-of-type(1) [focusable]");
	private By Applyconfirm =By.xpath("//span[text()='登録']");
    private By Cancelbutton = By.xpath("//span[text()='キャンセル']");
	
	
	
	
	
	
	
	
	
	
	

	public UpcomingExampage(WebDriver driver) {
		this.driver = driver;
	}
		
		public By PageTitleBy() {
			return PageTitle;
		}
		
		public By WelcomeTextElementBy() {
			return WelcomeTextElement;
		}
		
		
		
		public String getWelocmeText() {
			String WelcomeText = driver.findElement(WelcomeTextElement).getText().trim();
			return WelcomeText;}
		
		public By UpcomingExambuttonBy() {
			return UpcomingExambutton;
		}
		
		public void clickupcomingbutton() throws InterruptedException {
			Thread.sleep(2000);
			driver.findElement(UpcomingExambutton).click();}
		
		public void Searchbar() {
			driver.findElement(Searchfield).click();
			
		
		}
		public void Searcvalid() {
			driver.findElement(Searchfield).sendKeys("jithin");
			driver.findElement(Searchfield).sendKeys(Keys.ENTER);
			
		
		}	
		public String getsearchresults() {
			String result = driver.findElement(SearchResult).getText().trim();
			return result;}

		public By upcomingtitlenBy() {
			return upcomingtitle2;
		}
		
		public String getupcomingtitle() {
			String uptitle = driver.findElement(upcomingtitle2).getText().trim();
			return uptitle;
		}
		
		public By upcomingdurtnBy() {
			return upcomingdurtn2;
		}
		
		public String getupcomingdurtn() {
			String updur = driver.findElement(upcomingdurtn2).getText().trim();
			return updur;
		}
		
		public By upcomingstartBy() {
			return upcomingstart2;
		}
		
		public String getupcomingstart() {
			String upstrt = driver.findElement(upcomingstart2).getText().trim();
			return upstrt;
		}
		
		public By upcomingendBy() {
			return upcomingend2;
		}
		
		public String getupcomingend() {
			String upend = driver.findElement(upcomingend2).getText().trim();
			return upend;
		}
		
		public By upcomingapplyBy() {
			return upcomingapply2;
		}
		
		
		public String getupcomingapply() {
			String upapply = driver.findElement(upcomingapply2).getText().trim();
			return upapply;
		}
		
		public By ApplybuttonBy() {
			return Applybutton;
		}
		
		public String getapplybutton() {
			String applybt = driver.findElement(Applybutton).getText().trim();
			return applybt;
		}

		public void clickapplybutton()  {
			driver.findElement(Applybutton).click();
			}
		
		public void clickapplybuttonconfirm()  {
			driver.findElement(Applyconfirm).click();
			}
		public void clickcancelbutton()  {
			driver.findElement(Cancelbutton).click();
			}
		
		
		
		
		
		
		
		
		
	}
