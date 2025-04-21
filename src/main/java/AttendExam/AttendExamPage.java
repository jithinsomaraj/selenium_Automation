package AttendExam;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AttendExamPage {
	private WebDriver driver;
	private By PageTitle = By.xpath("//div[contains(@class,'MuiPaper-root MuiTableContainer-root')]"); 
	private By ExamNamePath = By
			.xpath("//*[@id=\\\"root\\\"]/div/main/div[2]/div[1]/div/div/div[\"+i+\"]/div/div/div[1]");
	private By StartButton = By.cssSelector(
			".MuiButton-contained.MuiButton-containedSecondary.MuiButton-root.MuiButtonBase-root.jss32 > .MuiButton-label");
	private By RemainingTime = By.xpath("//h6[text()='残り時間']");
	private By UploadButton = By.xpath("//span[text()='アップロード']");
	private By DeleteBtnofuploadimage = By
			.cssSelector(".MuiBox-root.jss33.jss51 .MuiButtonBase-root.MuiIconButton-root");
	private By CancelButton = By.xpath("//span[text()='前へ']");
	private By NextButton = By.cssSelector(
			"#root > div > div.MuiBox-root.jss48 > div.MuiBox-root.jss50.jss32 > div > div.MuiBox-root.jss55 > div.MuiBox-root.jss65 > div > div:nth-child(2) > button");
	private By FinishButton = By.xpath("//span[text()='試験を終了します']");
	private By AnswerBox = By.cssSelector(".form-control");
	private By OriginalAnswer = By.xpath("//div[text()='This is my answer']");
	private By CheckBox = By.id("1");
	private By Image = By.className("img-fluid");
	private By RadioButton = By.cssSelector("input[value^='1']");
	private By finishconfirm = By.cssSelector(
			".MuiButton-contained.MuiButton-containedPrimary.MuiButton-root.MuiButtonBase-root.jss60 > .MuiButton-label");
	private By QuestionQ = By.xpath("//h6[text()='Q']");
	private By EndButton = By.xpath("//span[text()='終了']");
	private By examResults = By.xpath("//h4[text()='試験お疲れ様でした ']");
	private By myscore = By.cssSelector("#root > div > div > div:nth-child(2) > div > div > div:nth-child(1) > h6");
	private By Totalscore = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/div[2]/h6");
	private By correctAnswers = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/div[3]/h6");
	private By TotalQuestions = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/div[4]/h6");
	private By Passmessage = By.cssSelector(
			"#root > div > div > div:nth-child(2) > div > div > div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-sm-12 > div > h4");

	public AttendExamPage(WebDriver driver) {
		this.driver = driver;
	}

	
	public By PageTitleBy () {
		return PageTitle;
	}
	
	
	public WebElement getExamNamePath() {
		WebElement path = driver.findElement(ExamNamePath);
		return path;
	}

	
	
	
	
	public void clickStartButton() {
		driver.findElement(StartButton).click();
	}

	public boolean isStartEnabled() {
		boolean Nxtbtntry = driver.findElement(StartButton).isEnabled();
		return Nxtbtntry;
	}

	public By RemainingTimeBy() {
		return RemainingTime;
	}

	public String getRemainingTime() {
		String RT = driver.findElement(RemainingTime).getText().trim();
		return RT;
	}

	public By FinishButtonBy() {
		return FinishButton;
	}


	public void AttendExam(String Exam) {
		String A1 = Exam;
		for (int i = 1; i < 20; i++) {
			String examnamediv = "//*[@id=\"root\"]/div/main/div[2]/div[1]/div/div/div[" + i + "]/div/div/div[1]";
			WebElement gt = driver.findElement(By.xpath(examnamediv));
			String a = gt.getText();
			if (a.equals(A1)) {
				String examnamediv1 = "//*[@id=\"root\"]/div/main/div[2]/div[1]/div/div/div[" + i
						+ "]/div/div/div[3]/div/div/button/span[1]/div";
				WebElement gt1 = driver.findElement(By.xpath(examnamediv1));
				gt1.click();
				break;
			}
		}

	}

	public void clickUploadButton() {
		driver.findElement(UploadButton).click();

	}

	public WebElement DeleteBtnofuploadimageBy() {
		return (driver.findElement(DeleteBtnofuploadimage));
	}

	public boolean getCancelButton() {
		boolean cncldbtn = driver.findElement(CancelButton) != null;
		return cncldbtn;
	}

	public WebElement NextButtonBy() {
		return (driver.findElement(NextButton));
	}

	public WebElement AnswerBoxBy() {
		return (driver.findElement(AnswerBox));
	}

	public WebElement NextButtonwBy() {
		return (driver.findElement(NextButton));
	}

	public boolean getNextButton() {
		boolean Nxtbtn = driver.findElement(NextButton) != null;
		return Nxtbtn;
	}

	public boolean getNextButtonTry() {
		boolean Nxtbtntry = driver.findElement(NextButton).isEnabled();
		return Nxtbtntry;
	}

	public void clickNextButton() {
		driver.findElement(NextButton).click();
	}

	public WebElement CancelButtonBy() {
		return (driver.findElement(CancelButton));
	}

	public WebElement FinishButtonDispBy() {
		return (driver.findElement(FinishButton));
	}

	public void clickFinishButton() {
		driver.findElement(FinishButton).click();
	}

	public void clickEndButton() {
		driver.findElement(EndButton).click();
	}

	public boolean getAnswerBox() {
		boolean AnsBox = driver.findElement(AnswerBox).isDisplayed();
		System.out.println(AnsBox);
		return AnsBox;
	}

	public String getOriginalAnswer() {
		String OrgAns = driver.findElement(OriginalAnswer).getText().trim();
		return OrgAns;
	}

	public boolean getCheckBox() {
		boolean ChkBox = driver.findElement(CheckBox) != null;
		return ChkBox;
	}

	public boolean getImage() {
		boolean img = driver.findElement(Image) != null;
		return img;
	}

	public boolean getRadioButton() {
		boolean RB = driver.findElement(RadioButton) != null;
		return RB;
	}

	public void clickfinishconfirmButton() {
		driver.findElement(finishconfirm).click();
	}

	public boolean isElementPresent() {
		try {
			driver.findElement(UploadButton);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean isElementPresent2() {
		try {
			driver.findElement(AnswerBox);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean isElementPresent3() {
		try {
			driver.findElement(CheckBox);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean isElementPresent4() {
		try {
			driver.findElement(RadioButton);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public WebElement QuestionQBy() {
		return (driver.findElement(QuestionQ));
	}

	public String getQuestionQ() {
		String Q = driver.findElement(QuestionQ).getText().trim();
		return Q;
	}

	public boolean CancelbuttonPresent() {
		try {
			driver.findElement(CancelButton);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean finishbuttonPresent() {
		try {
			driver.findElement(FinishButton);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public By examResultsBy() {
		return examResults;
	}

	public String getPassmessage() {
		String score = driver.findElement(Passmessage).getText().trim();
		return score;
	}

	public String getmyscore() {
		String score = driver.findElement(myscore).getText().trim();
		return score;
	}

	public String getTotalscore() {
		String score = driver.findElement(Totalscore).getText().trim();
		return score;
	}

	public String getcorrectAnswers() {
		String score = driver.findElement(correctAnswers).getText().trim();
		return score;
	}

	public String getTotalQuestions() {
		String score = driver.findElement(TotalQuestions).getText().trim();
		return score;
	}

	public void UploadImage(String Image) throws InterruptedException, AWTException {
		Thread.sleep(5000);
		String p = Image;
		StringSelection ss = new StringSelection(
				System.getProperty("user.dir") + "\\src\\main\\resources\\AttendExamImages\\" + p + "");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		// imitate mouse events like ENTER, CTRL+C, CTRL+V
		Robot robot = new Robot();
		robot.delay(250);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(90);
	}

	public void AnswerType(String ExpAns) throws InterruptedException, AWTException {
		driver.findElement(AnswerBox).sendKeys(ExpAns);
		Thread.sleep(2000);
	}

	public void MultipleType(String ExpMulAns1) throws InterruptedException, AWTException {
		if (ExpMulAns1 != null) {
			for (String i : ExpMulAns1.split(",")) {

				String Box1 = "" + i + "";
				WebElement Check = driver.findElement(By.id(Box1));
				Check.click();
			}

		}
		Thread.sleep(1000);
	}

	public void SingleType(String ExpSingleAns) throws InterruptedException, AWTException {
		String SingleAns = ExpSingleAns;
		driver.findElement(By.cssSelector("input[value^='" + SingleAns + "']")).click();
	}

}
