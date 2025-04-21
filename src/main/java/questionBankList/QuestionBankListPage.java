package questionBankList;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import login.loginPage;

public class QuestionBankListPage {

	private WebDriver driver;
	int pagenumber;
	private By WelcomeTextElement = By.xpath("//span[contains(@class,'MuiTypography-root jss14')]");
	private By SettingMenu = By.cssSelector(".MuiSvgIcon-root.jss13");
	private By LogoutLink = By.xpath("//div[@id='profile-menu']//ul[@role='menu']/li[3]");
	private By searchbox = By.id("search");
	private By QuestionBanklisttablewithhead = By.cssSelector("table");
	private By QuestionBanklistRows = By.xpath("//table/tbody/tr/td[1]");
	private By EmptyTable = By.cssSelector(".MuiTableContainer-root div");
	private By questionBankCreateButton = By.xpath("//a[@href='/admin/questionbankcreate']");
	private By reactAlert = By.id("__react-alert__");
	private By reactClose = By.cssSelector("button > svg");
	private By prevPage = By.cssSelector("button[title='Previous page']");
	private By nextPage = By.cssSelector("button[title='Next page']");
	private By questionListIconinMenu = By
			.cssSelector(".MuiDrawer-paperAnchorDockedLeft [tabindex='0']:nth-of-type(1) [focusable]");
	private By deleteConfirmation = By.cssSelector(".MuiBox-root [tabindex='0']:nth-of-type(1) .MuiButton-label");

	public QuestionBankListPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickQuestionListInMenu() {
		driver.findElement(questionListIconinMenu).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(QuestionBanklistRows));
	}

	public String getWelocmeText() {
		String WelcomeText = driver.findElement(WelcomeTextElement).getText().trim();
		return WelcomeText;
	}

	public void logout() {
		loginPage loginpage = new loginPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(SettingMenu));
		driver.findElement(SettingMenu).click();
		driver.findElement(LogoutLink).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(loginpage.loginbtnBy()));
	}

	public By getQuestionBankListTableBy() {
		return QuestionBanklisttablewithhead;
	}

	public List<String> search(String searchTerm, boolean isResult) {
		List<String> rowNames = new ArrayList<String>();
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		driver.findElement(searchbox).sendKeys(selectAll);
		driver.findElement(searchbox).sendKeys(Keys.DELETE);
		driver.findElement(searchbox).sendKeys(searchTerm);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (isResult) {
			rowNames = this.getQuestionBankListnamesTotal();
			driver.findElement(searchbox).sendKeys(selectAll);
			driver.findElement(searchbox).sendKeys(Keys.DELETE);
		} else {
			rowNames.add(driver.findElement(EmptyTable).getText());
			driver.findElement(searchbox).sendKeys(selectAll);
			driver.findElement(searchbox).sendKeys(Keys.DELETE);
		}
		return rowNames;
	}

	public List<String> getQuestionBankListnamesTotal() {
		pagenumber = 1;
		List<String> rowNames = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		do {
			if (pagenumber > 1) {
				driver.findElement(nextPage).click();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(QuestionBanklistRows));
				List<WebElement> tableRows = driver.findElements(QuestionBanklistRows);
				int size = tableRows.size();
				for (int i = 1; i <= size; i++) {
					rowNames.add(driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/th")).getText());
				}
			} catch (TimeoutException t) {
				rowNames.add(driver.findElement(EmptyTable).getText());
			}
			pagenumber++;
		} while (driver.findElement(nextPage).isEnabled());
		return rowNames;
	}

	public List<String> getQuestionBankListnamesCurrenPage() {
		List<String> rowNames = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(QuestionBanklistRows));
		List<WebElement> tableRows = driver.findElements(QuestionBanklistRows);
		int size = tableRows.size();
		for (int i = 1; i <= size; i++) {
			rowNames.add(driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/th")).getText());
		}
		return rowNames;
	}

	public Boolean IsQbankPresent(String QBankName) {
		Boolean status = false;
		pagenumber = 1;
		WebDriverWait wait = new WebDriverWait(driver, 15);
		do {
			if (pagenumber > 1) {
				driver.findElement(nextPage).click();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(QuestionBanklistRows));
			List<WebElement> tableRows = driver.findElements(QuestionBanklistRows);
			int size = tableRows.size();
			for (int i = 1; i <= size; i++) {
				if (driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/th")).getText().equals(QBankName)) {
					status = true;
					break;
				}
			}
			if (status == true) {
				break;
			}
			pagenumber++;
		} while (driver.findElement(nextPage).isEnabled());
		if (status == false) {
			driver.navigate().refresh();
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(QuestionBanklistRows));
		}
		return status;
	}

	public void NavigateToQBankCreate() {
		driver.findElement(questionBankCreateButton).click();
	}

	public void navigateToQuesionList(String QBankName) {
		Boolean status = false;
		pagenumber = 1;
		WebDriverWait wait = new WebDriverWait(driver, 15);
		do {
			if (pagenumber > 1) {
				driver.findElement(nextPage).click();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(QuestionBanklistRows));
			List<WebElement> tableRows = driver.findElements(QuestionBanklistRows);
			int size = tableRows.size();
			for (int i = 1; i <= size; i++) {
				if (driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/th")).getText().equals(QBankName)) {
					status = true;
					driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[2]/a")).click();
					break;
				}
			}
			if (status == true) {
				break;
			}
			pagenumber++;
		} while (driver.findElement(nextPage).isEnabled());
	}

	public void navigateToQbankEdit(String QBankName) {
		Boolean status = false;
		pagenumber = 1;
		WebDriverWait wait = new WebDriverWait(driver, 15);
		do {
			if (pagenumber > 1) {
				driver.findElement(nextPage).click();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(QuestionBanklistRows));
			List<WebElement> tableRows = driver.findElements(QuestionBanklistRows);
			int size = tableRows.size();
			for (int i = 1; i <= size; i++) {
				if (driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/th")).getText().equals(QBankName)) {
					status = true;
					driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[4]/a[@title='•ÒW']")).click();
					break;
				}
			}
			if (status == true) {
				break;
			}
			pagenumber++;
		} while (driver.findElement(nextPage).isEnabled());
		if (status == false) {
			driver.navigate().refresh();
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(QuestionBanklistRows));
		}
	}

	public void deleteQbank(String QBankName) {
		Boolean status = false;
		pagenumber = 1;
		WebDriverWait wait = new WebDriverWait(driver, 15);
		do {
			if (pagenumber > 1) {
				driver.findElement(nextPage).click();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(QuestionBanklistRows));
			List<WebElement> tableRows = driver.findElements(QuestionBanklistRows);
			int size = tableRows.size();
			for (int i = 1; i <= size; i++) {
				if (driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/th")).getText().equals(QBankName)) {
					status = true;
					driver.findElement(By.cssSelector(".MuiTableBody-root .MuiTableRow-root:nth-of-type(" + i
							+ ") .MuiTableCell-alignCenter > [focusable]")).click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(deleteConfirmation));
					driver.findElement(deleteConfirmation).click();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
			}
			if (status == true) {
				break;
			}
			pagenumber++;
		} while (driver.findElement(nextPage).isEnabled());
		if (status == false) {
			driver.navigate().refresh();
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(QuestionBanklistRows));
		}
	}

	public Boolean IsQuestionNamesPresent(String[] questions) {
		Boolean QuestionSts = false;
		List<String> QNames = getQuestionBankListnamesCurrenPage();
		for (String Qname : QNames) {
			for (String questionSingle : questions) {
				if (Qname.equals(questionSingle)) {
					QuestionSts = true;
				} else {
					QuestionSts = false;
					break;
				}
			}
		}
		return QuestionSts;
	}

	public String getReactAlertMessage() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String alertMsg = "";
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(reactAlert));
			Thread.sleep(1000);
			alertMsg = driver.findElement(reactAlert).getText();
			if (alertMsg.equals("")||alertMsg.equals(null)) {
				Thread.sleep(1000);
				alertMsg = driver.findElement(reactAlert).getText();
			}
			driver.findElement(reactClose).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(reactAlert));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (TimeoutException T) {
			alertMsg = "";
		}

		return alertMsg;
	}

}
