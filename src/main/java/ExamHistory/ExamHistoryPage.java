package ExamHistory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExamHistoryPage {

	private WebDriver driver;
	private By HomePageTitle = By.xpath("//div[contains(@class,'MuiPaper-root MuiTableContainer-root')]");
	private By Historybutton = By.cssSelector(".MuiList-padding [tabindex='0']:nth-of-type(3)");
	private By pageTitle = By.xpath("//h4[text()='試験の履歴']");
	private By searchbox = By.xpath(".//*[@id='titleSearch']");
	private By Title = By.xpath("//th[text()='題名']");
	private By Point = By.xpath("//th[text()='点']");
	private By schedule = By.xpath("//th[text()='実施日']");
	private By result = By.xpath("//th[text()='結果']");
	private By SearchResult = By.xpath("//b[text()='This is an exam for jithin']");
	private By TestResultspage = By.xpath("//h4[text()='試験結果']");
	private By ExaminationName = By.cssSelector("div.MuiBox-root:nth-child(5) > div:nth-child(1)");
	private By ExaminationateDate = By.xpath("//br[text()='試験日']");
	private By TotalDuration = By.xpath("//br[text()='合計時間']");
	private By TotalQuestion = By.xpath("//br[text()='問題合計']");
	private By TotalMarks = By.xpath("//br[text()='合計点']");
	private By CorrectAnswers = By.xpath("//br[text()='正解']");
	private By Markscored = By.xpath("//br[text()='得点']");
	private By Examstatus = By.xpath("//br[text()='試験ステータス']");
	private By ExaminationName1 = By.xpath("//div[text()='試験名','This is an exam for jithin-2']");
	private By ExaminationateDate1 = By.xpath("//br[text()='試験日','02/10/2020 23:25']");
	private By TotalDuration1 = By.xpath("//br[text()='合計時間','3 h 0 Min']");
	private By TotalQuestion1 = By.xpath("//br[text()='問題合計'.'6']");
	private By TotalMarks1 = By.xpath("//br[text()='合計点','22']");
	private By CorrectAnswers1 = By.xpath("//br[text()='正解','0']");
	private By Markscored1 = By.xpath("//br[text()='得点','0']");
	private By Examstatus1 = By.xpath("//br[text()='試験ステータス','Failed']");
	private By scoreforMCQ = By.xpath("//div[@innertext='点 : 0/4']");
	private By singleTextqstn = By.xpath("///textarea[@innertext='my answer']");
	private By SingletypeImagequestn = By.xpath("//?/p[@innertext='single  image description']");
	private By SingletypeImage = By.xpath(
			"//div[8]/p[2]/div[@safeclass~'\\bMuiBox-root\\b']/img[@src>'https://webexam-content-upload.s3-ap-northeast-1.amazonaws.c']");
	private By imageuploadquestion = By.xpath("//?/p[@innertext='Image type question description']");
	private By uploadedimage = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[4]/div[4]/div/img");
	private By fitsrQScore = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[4]/div[5]/div[2]/div/div/div/div");
	private By BackgroundColorGreen = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[7]/p[1]");
	private By BackgroundColorGreen1 = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[7]/p[2]");
	private By BackgroundColorGreen2 = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[6]/p[1]");
	private By BackgroundColorGreen3 = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[6]/p[2]");
	private By BackgroundColorGreen4 = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[8]/p[1]");
	private By BackgroundColorGreen5 = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[9]/p[1]");
	private By BackgroundColorRed = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[8]/p[3]");
	private By BackgroundColorRed1 = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[7]/p[3]");
	private By BackgroundColorRed2 = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[8]/p[2]");

	public ExamHistoryPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickhistorybutton() {
		driver.findElement(Historybutton).click();
	}

	
	public By HomePageTitleBy() {
		return HomePageTitle;
	}
	public By pageTitleBy() {
		return pageTitle;

	}

	public String getpageTitle() {
		String title = driver.findElement(pageTitle).getText().trim();
		return title;
	}

	public String getTitle() {
		String title1 = driver.findElement(Title).getText().trim();
		return title1;
	}

	public String getPoint() {
		String points = driver.findElement(Point).getText().trim();
		return points;
	}

	public String getschedule() {
		String schdle = driver.findElement(schedule).getText().trim();
		return schdle;
	}

	public String getresult() {
		String reslt = driver.findElement(result).getText().trim();
		return reslt;
	}

	public By searchboxBy() {
		return searchbox;
	}

	public void Searchbar() {
		driver.findElement(searchbox).click();
	}

	public void Searcvalid() {
		driver.findElement(searchbox).sendKeys("jithin");
		driver.findElement(searchbox).sendKeys(Keys.ENTER);

	}

	public String getsearchresults() {
		String result = driver.findElement(SearchResult).getText().trim();
		return result;
	}

	public void clickResultbutton(String Exam) {
		String A1 = Exam;
		for (int i = 1; i < 20; i++) {
			String examnamediv = "tr:nth-of-type("+i+") > th[role='cell'] > b";
			WebElement gt = driver.findElement(By.cssSelector(examnamediv));
			String a = gt.getText();
			if (a.equals(A1)) {
				String examnamediv1 = "//*[@id=\"root\"]/div/main/div[2]/div[2]/table/tbody/tr["+i+"]/td[3]";
				WebElement gt1 = driver.findElement(By.xpath(examnamediv1));
				gt1.click();
				break;

			}
		}

	}

	

	public By SearchResultBy() {
		return SearchResult;
	}

	public By TestResultspagetBy() {
		return TestResultspage;
	}

	public String getTestResultspages() {
		String pagetitle = driver.findElement(TestResultspage).getText().trim();
		return pagetitle;
	}

	public String getExaminationName() {
		String exmetitle = driver.findElement(ExaminationName).getText();
		return exmetitle;
	}

	public String getExaminationateDate() {
		String exmdate = driver.findElement(ExaminationateDate).getText().trim();
		return exmdate;
	}

	public String getTotalDuration() {
		String exmdurtn = driver.findElement(TotalDuration).getText().trim();
		return exmdurtn;
	}

	public String getTotalQuestion() {
		String exmdqstn = driver.findElement(TotalQuestion).getText().trim();
		return exmdqstn;
	}

	public String getTotalMarks() {
		String exmmarks = driver.findElement(TotalMarks).getText().trim();
		return exmmarks;
	}

	public String getCorrectAnswers() {
		String correctans = driver.findElement(CorrectAnswers).getText().trim();
		return correctans;
	}

	public String getMarkscored() {
		String markscrd = driver.findElement(Markscored).getText().trim();
		return markscrd;
	}

	public String getExamstatus() {
		String examstatus = driver.findElement(Examstatus).getText().trim();
		return examstatus;
	}

	public String getExaminationName1() {
		String exmetitle = driver.findElement(ExaminationName1).getText().trim();
		return exmetitle;
	}

	public String getExaminationateDat1e() {
		String exmdate = driver.findElement(ExaminationateDate1).getText().trim();
		return exmdate;
	}

	public String getTotalDuration1() {
		String exmdurtn = driver.findElement(TotalDuration1).getText().trim();
		return exmdurtn;
	}

	public String getTotalQuestion1() {
		String exmdqstn = driver.findElement(TotalQuestion1).getText().trim();
		return exmdqstn;
	}

	public String getTotalMarks1() {
		String exmmarks = driver.findElement(TotalMarks1).getText().trim();
		return exmmarks;
	}

	public String getCorrectAnswers1() {
		String correctans = driver.findElement(CorrectAnswers1).getText().trim();
		return correctans;
	}

	public String getMarkscored1() {
		String markscrd = driver.findElement(Markscored1).getText().trim();
		return markscrd;
	}

	public String getExamstatus1() {
		String examstatus = driver.findElement(Examstatus1).getText().trim();
		return examstatus;
	}

	public String getscoreforMCQ() {
		String Mcqscore = driver.findElement(scoreforMCQ).getText().trim();
		return Mcqscore;
	}

	public String getsingleTextqstn() {
		String singleqstn = driver.findElement(singleTextqstn).getText().trim();
		return singleqstn;
	}

	public String getSingletypeImagequestn() {
		String singleimageqstn = driver.findElement(SingletypeImagequestn).getText().trim();
		return singleimageqstn;
	}

	public WebElement SingletypeImage() {
		WebElement img = driver.findElement(SingletypeImage);
		return img;
	}

	public String getimageuploadquestion() {
		String imageqstn = driver.findElement(imageuploadquestion).getText().trim();
		return imageqstn;
	}

	public WebElement uploadedimageqstn() {
		WebElement img1 = driver.findElement(uploadedimage);
		return img1;
	}
	
	
	public boolean isImagePresent() {
		try {
			driver.findElement(uploadedimage);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	
	public String getUploadtypeScore() {
		String score = driver.findElement(fitsrQScore).getText().trim();
		return score;
	}
	
	public String getAnswerofAnswerType() {
		String score = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[5]/div[4]/div/textarea[1]")).getText().trim();
		return score;
	}
	
	
	public String getAnswerTypeScore() {
		String score = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[5]/div[5]/div[2]/div/div/div/div")).getText().trim();
		return score;
	}
	
	
	public String getMulImageTypeScore() {
		String score = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[6]/div[4]/div[2]/div/div/div/div")).getText().trim();
		return score;
	}
	
	public String getMulTypeScore() {
		String score = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[7]/div[4]/div[2]/div/div/div/div")).getText().trim();
		return score;
	}
	

	public String getSingleImgScore() {
		String score = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[8]/div[4]/div[2]/div/div/div")).getText().trim();
		return score;
	}
	
	public String getTotalScore() {
		String score = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[10]/div[2]/div/div/div/div")).getText().trim();
		return score;
	}
	
	
	
	
	
	
	
	

	public String getBackgroundColorGreen() {
		String colour = driver.findElement(BackgroundColorGreen).getCssValue("background-color");
		return colour;
	}

	public String getBackgroundColorGreen1() {
		String colour = driver.findElement(BackgroundColorGreen1).getCssValue("background-color");
		return colour;
	}

	public String getBackgroundColorGreen2() {
		String colour = driver.findElement(BackgroundColorGreen2).getCssValue("background-color");
		return colour;
	}

	public String getBackgroundColorGreen3() {
		String colour = driver.findElement(BackgroundColorGreen3).getCssValue("background-color");
		return colour;
	}

	public String getBackgroundColorGreen4() {
		String colour = driver.findElement(BackgroundColorGreen4).getCssValue("background-color");
		return colour;
	}

	public String getBackgroundColorGreen5() {
		String colour = driver.findElement(BackgroundColorGreen5).getCssValue("background-color");
		return colour;
	}

	public String getBackgroundColorRed() {
		String colour = driver.findElement(BackgroundColorRed).getCssValue("background-color");
		return colour;
	}

	public String getBackgroundColorRed1() {
		String colour = driver.findElement(BackgroundColorRed1).getCssValue("background-color");
		return colour;
	}

	public String getBackgroundColorRed2() {
		String colour = driver.findElement(BackgroundColorRed2).getCssValue("background-color");
		return colour;
	}
	
	public String ExamDetails() {
		String Details = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[3]")).getText();
		return Details;

	}
	
	
	

}
