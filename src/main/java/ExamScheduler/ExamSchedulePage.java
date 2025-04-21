package ExamScheduler;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExamSchedulePage {
	private WebDriver driver;
	private By SchedlueButton = By.cssSelector(".MuiList-padding.MuiList-root > div:nth-of-type(4)");
	private By ExamSchedlueList = By.cssSelector(".MuiList-padding.MuiList-root > div:nth-of-type(4)");
	private By CandidteAssignButton = By.cssSelector("tr:nth-of-type(1) > td:nth-of-type(4) > svg:nth-of-type(2)");
	private By Checkbox0 = By
			.cssSelector("[aria-label] .MuiTableRow-root:nth-of-type(1) .MuiTableCell-paddingCheckbox");
	private By AssigncandidatesButton = By.xpath("//span[text()='候補者を割り当て']");
	private By reactAlert = By.id("__react-alert__");
	private By reactClose = By.cssSelector("button > svg");
	private By Dtitle = By.xpath("//th[text()='説明']");
	private By CreateScheduleButton = By.xpath("//span[text()='スケジュールを作成']");
	private By DropDown = By.cssSelector("div#question-type-select");
	private By CalenderMonthYear = By.cssSelector(".MuiTypography-body1.MuiTypography-alignCenter");
	private By StartcalenderButton = By.cssSelector(
			"div:nth-of-type(1) > div > div:nth-of-type(1) > .MuiInput-formControl.MuiInput-root.MuiInput-underline.MuiInputBase-adornedEnd.MuiInputBase-formControl.MuiInputBase-root > .MuiInputAdornment-positionEnd.MuiInputAdornment-root > .MuiButtonBase-root.MuiIconButton-root");
	private By EndCalenderButton = By.cssSelector(
			"form [class='MuiGrid-root MuiGrid-item MuiGrid-grid-sm-12 MuiGrid-grid-md-12']:nth-of-type(2) .MuiFormControl-marginNormal:nth-of-type(1) [tabindex]");
	private By RightArrowButton = By
			.cssSelector(".MuiPickersCalendarHeader-switchHeader > button:nth-of-type(2)  .MuiSvgIcon-root");
	private By LeftArrowButton = By
			.cssSelector(".MuiPickersCalendarHeader-switchHeader > button:nth-of-type(1)  .MuiSvgIcon-root");
	private By OK = By.xpath("//span[text()='OK']");
	private By StartTime = By.cssSelector(
			"div:nth-of-type(1) > div > div:nth-of-type(2) > .MuiInput-formControl.MuiInput-root.MuiInput-underline.MuiInputBase-adornedEnd.MuiInputBase-formControl.MuiInputBase-root > .MuiInputAdornment-positionEnd.MuiInputAdornment-root > .MuiButtonBase-root.MuiIconButton-root");
	private By EndTime = By.cssSelector(
			"form [class='MuiGrid-root MuiGrid-item MuiGrid-grid-sm-12 MuiGrid-grid-md-12']:nth-of-type(2) .MuiFormControl-marginNormal:nth-of-type(2) [tabindex]");
	private By am = By.cssSelector(
			".MuiPickersTimePickerToolbar-ampmSelection [tabindex='0']:nth-of-type(1) .MuiPickersTimePickerToolbar-ampmLabel");
	private By endDateGreater = By.cssSelector("p#end-time-picker-helper-text");
	private By pm = By.cssSelector(".MuiPickersTimePickerToolbar-ampmSelection [tabindex='0']:nth-of-type(2) h6");
	private By endTimeGreater = By.xpath("/html/body/div[4]/div[3]/div/div[2]/form/div[2]/div[2]/div/div[2]/p");
	private By CreateScheduleBtn = By.xpath("(//span[contains(text(),'スケジュールを作成')])[2]");
	private By cancelButton = By.xpath("//span[contains(text(),'キャンセル')]");
	private By uploadButton = By
			.cssSelector("tr:nth-of-type(1) > td:nth-of-type(4) > svg[title='CSV アップロードを使用して候補を割り当てます']");
	private By scheduleEditButton = By.cssSelector(
			".MuiTableBody-root .MuiTableRow-root:nth-of-type(1) .MuiTableCell-alignCenter:nth-child(7) [focusable='false']:nth-of-type(3)");
	private By updateScheduleButton = By.xpath("/html/body/div[4]/div[3]/div/div[2]/form/div[3]/button[1]/span[1]");
	private By uploadcsvButton = By.cssSelector("[type='file']");
	private By uploadcsv = By.id("rais-button-file");
	private By csvuploadsubmitButtn = By.xpath("//span[text()='ファイルをアップロード']");
	private By csvuploadsubmitButtn1 = By.xpath("(.//*[@focusable='false'])[14]");
	private By csvuploadcancelbutton = By.xpath("//span[text()='キャンセル']");
	private By AssgnCandDeleteButton = By.cssSelector(".MuiTableRow-root > td:nth-of-type(5)");
	private By AssignDeleteConfrmButton = By.xpath("(.//*[@type='submit'])[2]");
	private By DeleteScheduleButton = By.cssSelector("tr:nth-of-type(1) > td:nth-of-type(4) > svg[title='削除']");
	private By confirmDelScheduleButton = By.xpath("//span[text()='削除']");
	private By ReportButton = By.cssSelector(
			".MuiTableBody-root .MuiTableRow-root:nth-of-type(1) .MuiTableCell-alignCenter:nth-child(6) [focusable]");
	private By candidateTotal = By
			.cssSelector(".MuiGrid-container.MuiGrid-root.MuiGrid-spacing-xs-4 > div:nth-of-type(1)");
	private By ExamReportTitle = By.xpath(".//*[@class='jss9']");
	private By CandORApplicant = By
			.cssSelector(".MuiGrid-container.MuiGrid-root.MuiGrid-spacing-xs-4 > div:nth-of-type(2)");
	private By TotalTime = By.cssSelector(".MuiGrid-container.MuiGrid-root.MuiGrid-spacing-xs-4 > div:nth-of-type(3)");
	private By TotalQuestn = By
			.cssSelector(".MuiGrid-container.MuiGrid-root.MuiGrid-spacing-xs-4 > div:nth-of-type(4)");
	private By candAbovAverage = By
			.cssSelector(".MuiGrid-container.MuiGrid-root.MuiGrid-spacing-xs-4 > div:nth-of-type(5)");
	private By candBelowAverage = By
			.cssSelector(".MuiGrid-container.MuiGrid-root.MuiGrid-spacing-xs-4 > div:nth-of-type(6)");
	private By Graph1 = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[2]/div[1]");
	private By Graph2 = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[2]/div[2]");
	private By Graph3 = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[2]/div[3]");
	private By searchBar = By.xpath(".//*[@id='search']");
	private By searchReslt = By.cssSelector("tbody > tr:nth-of-type(1) > th:nth-of-type(2)");

	public ExamSchedulePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickSchedlueButton() {
		driver.findElement(SchedlueButton).click();
	}

	public By ExamSchedlueListBy() {
		return ExamSchedlueList;
	}

	public void clickCandidteAssignButton() {
		driver.findElement(CandidteAssignButton).click();
	}

	public void clickCheckbox0() {
		driver.findElement(Checkbox0).click();
	}

	public void clickAssigncandidatesButton() {
		driver.findElement(AssigncandidatesButton).click();
	}

	public String getReactAlertMessage() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String alertMsg = "";
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(reactAlert));
			Thread.sleep(1000);
			alertMsg = driver.findElement(reactAlert).getText();
			driver.findElement(reactClose).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(reactAlert));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (TimeoutException T) {
			alertMsg = "";
		}

		return alertMsg;
	}

	public By DtitleBy() {
		return Dtitle;
	}

	public void clickCreateScheduleButton() {
		driver.findElement(CreateScheduleButton).click();
	}

	public WebElement DropDownBy() {
		return (driver.findElement(DropDown));
	}

	public void clickDropDown() {
		driver.findElement(DropDown).click();
	}

	public void SelectDropDownValue(String Exam) {

		List<WebElement> dd = driver
				.findElements(By.xpath("//ul[@Class='MuiList-root MuiMenu-list MuiList-padding']//li"));// Xpath of
		// dropdownlist
		for (int i = 0; i < dd.size(); i++) {
			WebElement Examname = dd.get(i);
			String innerhtml = Examname.getAttribute("innerText");
			System.out.println(innerhtml);
			if (innerhtml.contentEquals(Exam)) {
				Examname.click();
				break;
			}
		}
	}

	public String getCalenderMonth() {
		String calmnth = driver.findElement(CalenderMonthYear).getText().trim();
		return calmnth;
	}

	public void clickRightArrowButton() {
		WebElement Ra = driver.findElement(RightArrowButton);
		Actions builder = new Actions(driver);
		builder.moveToElement(Ra).click().perform();

	}

	public boolean isLeftArrowButtonPresent() {
		try {
			driver.findElement(LeftArrowButton);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public void clickLeftArrowButtonn() {
		driver.findElement(LeftArrowButton).click();

	}

	public void clickStartDay(String ExpStartDay) throws InterruptedException {

		String i = ExpStartDay;
		Thread.sleep(1500);
		String StartDay = "//p[contains(text(),'" + i + "')]";
		WebElement Day = driver.findElement(By.xpath(StartDay));
		Day.click();
	}

	public void clickEndDay(String ExpEndDay) throws InterruptedException {

		String m = ExpEndDay;
		driver.findElement(By.xpath("//p[contains(text(),'" + m + "')]")).click();
	}

	public void clickOK() {
		driver.findElement(OK).click();

	}

	public void clickStartcalenderButton() {
		driver.findElement(StartcalenderButton).click();

	}

	public void clickStartTime() {
		driver.findElement(StartTime).click();

	}

	public void selectStartHour(String ExpStartTimeHH) {
		String j = ExpStartTimeHH;
		String StartHour = ".MuiPickersClock-clock > span:nth-of-type(" + j + ")";
		WebElement Ra = driver.findElement(By.cssSelector(StartHour));
		Actions builder = new Actions(driver);
		builder.moveToElement(Ra).click().perform();

	}

	public void selectStartMinute(String ExpStartMM) {
		String k = ExpStartMM;
		String StartMin = ".MuiPickersClock-clock > span:nth-of-type(" + k + ")";
		WebElement Ra1 = driver.findElement(By.cssSelector(StartMin));
		Actions builder1 = new Actions(driver);
		builder1.moveToElement(Ra1).click().perform();

	}

	public void selectEndHour(String ExpEndTimeHH) {
		String l = ExpEndTimeHH;
		String EndHour = ".MuiPickersClock-clock > span:nth-of-type(" + l + ")";
		WebElement Ra11 = driver.findElement(By.cssSelector(EndHour));
		Actions builder11 = new Actions(driver);
		builder11.moveToElement(Ra11).click().perform();

	}

	public void selectEndMinute(String ExpEndMM) {
		String n = ExpEndMM;
		String EndMin = ".MuiPickersClock-clock > span:nth-of-type(" + n + ")";
		WebElement Ra12 = driver.findElement(By.cssSelector(EndMin));
		Actions builder12 = new Actions(driver);
		builder12.moveToElement(Ra12).click().perform();
	}

	public void clickExamEditButton(String Exam) {

		String A1 = Exam;
		for (int i = 1; i < 20; i++) {
			String examnamediv = "tbody > tr:nth-of-type(" + i + ") > th:nth-of-type(2)";
			WebElement gt = driver.findElement(By.cssSelector(examnamediv));
			String a = gt.getText();
			if (a.equals(A1)) {
				String examnamediv1 = "tr:nth-of-type(" + i + ") > td:nth-of-type(4) > svg[title='編集']";
				WebElement gt1 = driver.findElement(By.cssSelector(examnamediv1));
				gt1.click();
				break;

			}
		}

	}

	public void clickCSVuploadButton(String Exam) {

		String A1 = Exam;
		for (int i = 1; i < 20; i++) {
			String examnamediv = "tbody > tr:nth-of-type(" + i + ") > th:nth-of-type(2)";
			WebElement gt = driver.findElement(By.cssSelector(examnamediv));
			String a = gt.getText();
			if (a.equals(A1)) {
				String uploadButton = "tr:nth-of-type(" + i
						+ ") > td:nth-of-type(4) > svg[title='CSV アップロードを使用して候補を割り当てます']";
				WebElement gt1 = driver.findElement(By.cssSelector(uploadButton));
				gt1.click();
				break;

			}
		}

	}

	public void clickDeleteButton(String CandFirstName) {
		String A2 = CandFirstName;
		for (int i = 1; i < 20; i++) {
			String CandidName = ".MuiTable-root.MuiTable-stickyHeader  .MuiTableRow-root > td:nth-of-type(" + i + ")";
			WebElement gt = driver.findElement(By.cssSelector(CandidName));
			String a = gt.getText();
			if (a.equals(A2)) {
				String DeleteBtn = "tr:nth-of-type(" + i + ") > td:nth-of-type(5)";
				WebElement gt1 = driver.findElement(By.cssSelector(DeleteBtn));
				gt1.click();
				break;

			}
		}

	}

	public void clickScheduleDeleteButton(String Exam) {
		String A1 = Exam;
		for (int i = 1; i < 20; i++) {
			String examname = "tbody > tr:nth-of-type(" + i + ") > th:nth-of-type(2)";
			WebElement gt = driver.findElement(By.cssSelector(examname));
			String a = gt.getText();
			if (a.equals(A1)) {
				String SchdDeleteBtn = "tr:nth-of-type(" + i + ") > td:nth-of-type(4) > svg[title='削除']";
				WebElement gt1 = driver.findElement(By.cssSelector(SchdDeleteBtn));
				gt1.click();
				break;

			}
		}

	}

	public void clickAssignButton(String Exam) throws InterruptedException {
		String A1 = Exam;
		for (int i = 1; i < 20; i++) {
			String examname = "tbody > tr:nth-of-type(" + i + ") > th:nth-of-type(2)";
			WebElement gt = driver.findElement(By.cssSelector(examname));
			String a = gt.getText();
			if (a.equals(A1)) {
				String AssignBtn = "tr:nth-of-type(" + i + ") > td:nth-of-type(4) > svg[title='候補者を割り当てます']";
				WebElement gt1 = driver.findElement(By.cssSelector(AssignBtn));
				gt1.click();
				break;

			}
		}
		Thread.sleep(3000);

	}

	public void selectUser(String UserEmail) throws InterruptedException {
		String A2 = UserEmail;
		for (int j = 1; j < 20; j++) {
			String UserMail = "tr:nth-of-type(" + j + ") > td:nth-of-type(5)";
			WebElement gt = driver.findElement(By.cssSelector(UserMail));
			String a = gt.getText();
			if (a.equals(A2)) {
				String AssignBtn = "tr:nth-of-type(" + j
						+ ") > .MuiTableCell-body.MuiTableCell-paddingCheckbox.MuiTableCell-root";
				WebElement gt1 = driver.findElement(By.cssSelector(AssignBtn));
				gt1.click();
				break;

			}
		}

	}

	public void selectReport(String Exam) throws InterruptedException {
		String A1 = Exam;
		for (int i = 1; i < 20; i++) {
			String examname = "tbody > tr:nth-of-type(" + i + ") > th:nth-of-type(2)";
			WebElement gt = driver.findElement(By.cssSelector(examname));
			String a = gt.getText();
			if (a.equals(A1)) {
				String ReportBtn = "tr:nth-of-type(" + i + ") > td:nth-of-type(3)";
				WebElement gt1 = driver.findElement(By.cssSelector(ReportBtn));
				gt1.click();
				break;

			}
		}

	}

	public void Search(String SearchInput) throws InterruptedException {

		driver.findElement(searchBar).sendKeys(SearchInput);

	}

	public String getsearchResult() throws InterruptedException {

		String Result = driver.findElement(searchReslt).getText().trim();
		return Result;

	}

	public void clickAM() {
		WebElement Ra = driver.findElement(am);
		Actions builder = new Actions(driver);
		builder.moveToElement(Ra).click().perform();

	}

	public void clickPM() {
		WebElement Ra = driver.findElement(pm);
		Actions builder = new Actions(driver);
		builder.moveToElement(Ra).click().perform();

	}

	public void clickEndCalenderButton() {
		driver.findElement(EndCalenderButton).click();
	}

	public String getendDateGreater() {
		String endDateG = driver.findElement(endDateGreater).getText().trim();
		return endDateG;
	}

	public String getendTimeGreater() {
		String endTime = driver.findElement(endTimeGreater).getText().trim();
		return endTime;
	}

	public boolean isendTimeGreaterPresent() {
		try {
			driver.findElement(endTimeGreater);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public void clickEndTime() {
		driver.findElement(EndTime).click();
	}

	public void clickCreateScheduleBtn() {
		driver.findElement(CreateScheduleBtn).click();
	}

	public void clickCancelBtn() {
		driver.findElement(cancelButton).click();
	}

	public void clickscheduleEditButton() {
		driver.findElement(scheduleEditButton).click();
	}

	public void clickupdateScheduleButton() {
		driver.findElement(updateScheduleButton).click();

	}

	public void clickuploadButton() {
		driver.findElement(uploadButton).click();
	}

	public boolean uploadcsvButtonPresent() {
		try {
			driver.findElement(uploadcsvButton);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean csvuploadsubmitButtnPresent() {
		try {
			driver.findElement(csvuploadsubmitButtn);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean csvuploadcancelbuttonPresent() {
		try {
			driver.findElement(csvuploadcancelbutton);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public void uploadcsvFile(String CSV) {

		String C = CSV;
		driver.findElement(uploadcsv)
				.sendKeys(System.getProperty("user.dir") + "\\src\\main\\resources\\CSVFiles\\" + C + ".csv");
		;
	}

	public void clickcsvuploadsubmitButtn() {
		driver.findElement(csvuploadsubmitButtn).click();
	}

	public void clickcsvuploadsubmitButtn1() {
		driver.findElement(csvuploadsubmitButtn1).click();
	}

	public void clickcsvuploadcancelbutton() {
		driver.findElement(csvuploadcancelbutton).click();
	}

	public void clickAssgnCandDeleteButton() {
		driver.findElement(AssgnCandDeleteButton).click();
	}

	public void clickAssignDeleteConfrmButton() {
		driver.findElement(AssignDeleteConfrmButton).click();
	}

	public void clickDeleteScheduleButton() {
		driver.findElement(DeleteScheduleButton).click();
	}

	public void clickconfirmDelScheduleButton() {
		driver.findElement(confirmDelScheduleButton).click();
	}

	public void clickReportButton() {
		driver.findElement(ReportButton).click();
	}

	public String getCandORApplicant() {
		String cA = driver.findElement(CandORApplicant).getText().trim();
		return cA;
	}

	public By ExamReportTitleBy() {
		return ExamReportTitle;
	}

	public String getcandidateTotal() {
		String ct = driver.findElement(candidateTotal).getText().trim();
		return ct;
	}

	public String getTotalTime() {
		String TT = driver.findElement(TotalTime).getText().trim();
		return TT;

	}

	public String getTotalQuestn() {
		String TQ = driver.findElement(TotalQuestn).getText().trim();
		return TQ;
	}

	public String getcandAbovAverage() {
		String cAA = driver.findElement(candAbovAverage).getText().trim();
		return cAA;
	}

	public String getcandBelowAverage() {
		String cBA = driver.findElement(candBelowAverage).getText().trim();
		return cBA;
	}

	public boolean isGraph1present() {
		try {
			driver.findElement(Graph1);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean isGraph2present() {
		try {
			driver.findElement(Graph2);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean isGraph3present() {
		try {
			driver.findElement(Graph3);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

}
