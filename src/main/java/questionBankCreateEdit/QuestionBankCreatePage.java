package questionBankCreateEdit;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuestionBankCreatePage {
	private WebDriver driver;
	private By title = By.id("name");
	private By group = By.cssSelector(
			"[class] [class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-sm-12 MuiGrid-grid-md-4 MuiGrid-grid-lg-4']:nth-of-type(2) #tags-outlined");
	private By category = By.cssSelector(
			"[class] [class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-sm-12 MuiGrid-grid-md-4 MuiGrid-grid-lg-4']:nth-of-type(3) #tags-outlined");
	private By description = By.id("description");
	private By submitButton = By.cssSelector(".MuiGrid-item:nth-of-type(2) .MuiButton-label");
	private By addQuestions = By.cssSelector("form .MuiGrid-container:nth-child(4) .MuiButton-label");
	private By addQuestionsinEdit = By.cssSelector("form .MuiGrid-container:nth-of-type(3) .MuiButton-label");
	private By questionTableElement = By.cssSelector("div[role='dialog'] .MuiTableBody-root");
	private By questionRowsElement = By.xpath("//table/tbody/tr/td[1]");
	private By questionRowsElementinEdit = By.xpath("//div[@class = 'MuiDialogContent-root']//table/tbody/tr/td[1]");
	private By saveQuestionButton = By.xpath("(//button/span[text()='ñ‚ëËÇí«â¡'])[2]");
	private By addedQuestionTableElement = By.cssSelector(".MuiTableBody-root");
	private By SaveQbankButton = By.cssSelector(".MuiGrid-item:nth-of-type(2) .MuiButton-label");
	private By titleHelpText = By.cssSelector("p#name-helper-text");
	private By descHelpText = By.cssSelector("p#description-helper-text");
	private By groupErrorText = By.xpath("//form/div/div[2]/div/div/div/p[@id = 'tags-outlined-helper-text']");
	private By cateErrorText = By.xpath("//form/div/div[3]/div/div/div/p[@id = 'tags-outlined-helper-text']");
	private By cancelButton = By.cssSelector("[href] .MuiButton-label");
	private By groupClearButton = By.xpath("//div[2]/div/div[@role='combobox']//button[@title='Clear']/span");
	private By cateClearButton = By.xpath("//div[3]/div/div[@role='combobox']//button[@title='Clear']/span");
	private By cnfrmQuestionDeleteBtn = By.cssSelector(".MuiBox-root [tabindex='0']:nth-of-type(1) .MuiButton-label");
	private By cancelQuestionDeleteBtn = By.cssSelector(".MuiBox-root [tabindex='0']:nth-of-type(2) .MuiButton-label");
	

	public QuestionBankCreatePage(WebDriver driver) {
		this.driver = driver;
	}

	public By submitButtonBy() {
		return submitButton;
	}

	public WebElement getTitle() {
		return driver.findElement(title);
	}

	public void setTitle(String titleData) {
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		driver.findElement(title).sendKeys(selectAll);
		driver.findElement(title).sendKeys(Keys.DELETE);
		driver.findElement(title).sendKeys(titleData);
	}

	public String getTitleError() {
		return driver.findElement(titleHelpText).getText();
	}

	public void setGroups(String[] groups) {
		for (String groupData : groups) {
			driver.findElement(group).sendKeys(groupData);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.findElement(group).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(group).sendKeys(Keys.ENTER);
		}
	}

	public String getGroupError() {
		return driver.findElement(groupErrorText).getText();
	}

	public void setCategories(String[] Category) {
		for (String CategoryData : Category) {
			driver.findElement(category).sendKeys(CategoryData);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.findElement(category).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(category).sendKeys(Keys.ENTER);
		}
	}

	public String getCategoryError() {
		return driver.findElement(cateErrorText).getText();
	}

	public void setDescription(String Description) {
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		driver.findElement(description).sendKeys(selectAll);
		driver.findElement(description).sendKeys(Keys.DELETE);
		driver.findElement(description).sendKeys(Description);
	}
	
	public String getDescription() {
		return driver.findElement(description).getText();
	}

	public String getDescriptionError() {
		return driver.findElement(descHelpText).getText();
	}

	public void navigateToAddQuestion() {
		try {
		driver.findElement(addQuestions).click();
		}catch(NoSuchElementException e) {
			driver.findElement(addQuestionsinEdit).click();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(questionTableElement));
	}

	public void selectQuestions(String[] questions) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(questionRowsElement));
		List<WebElement> questionRows = driver.findElements(questionRowsElement);
		int size = questionRows.size();
		for (String question : questions) {
			for (int i = 1; i <= size; i++) {
				if (driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/th[1]")).getText().equals(question)) {
					driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td/span/span")).click();
				}
			}
		}
		driver.findElement(saveQuestionButton).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void selectQuestionsinEdit(String[] questions) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(questionRowsElementinEdit));
		List<WebElement> questionRows = driver.findElements(questionRowsElementinEdit);
		int size = questionRows.size();
		for (String question : questions) {
			for (int i = 1; i <= size; i++) {
				if (driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/th[1]")).getText().equals(question)) {
					driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td/span/span")).click();
				}
			}
		}
		driver.findElement(saveQuestionButton).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public boolean verifyAddedQuestions(String[] questions) {
		boolean checkStatus = true;
		WebDriverWait wait = new WebDriverWait(driver, 15);
		if (driver.findElement(By.xpath("//table/tbody")).isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(addedQuestionTableElement));
			List<WebElement> questionRows = driver.findElements(questionRowsElement);
			int size = questionRows.size();
			for (String question : questions) {
				for (int i = 1; i <= size; i++) {
					if (driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]")).getText().equals(question)) {
						checkStatus = true;
					} else {
						checkStatus = false;
						break;
					}
				}
			}
		} else {
			checkStatus = false;
		}
		return checkStatus;
	}

	public void deleteAddedQuestions(String[] questions) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(questionRowsElement));
		List<WebElement> questionRows = driver.findElements(questionRowsElement);
		int size = questionRows.size();
		for (String question : questions) {
			for (int i = 1; i <= size; i++) {
				if (driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]")).getText().equals(question)) {
					driver.findElement(
							By.cssSelector("tr:nth-of-type(" + i + ") > td:nth-of-type(5) > svg[title='çÌèú']")).click();
				}
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void deleteAddedQuestionsinEdit(String question) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(questionRowsElement));
		List<WebElement> questionRows = driver.findElements(questionRowsElement);
		int size = questionRows.size();
			for (int i = 1; i <= size; i++) {
				if (driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]")).getText().equals(question)) {
					driver.findElement(
							By.cssSelector("tr:nth-of-type(" + i + ") > td:nth-of-type(5) > svg[title='çÌèú']")).click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(cnfrmQuestionDeleteBtn));
					driver.findElement(cnfrmQuestionDeleteBtn).click();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clearGroupEntries() {
		driver.findElement(group).click();
		try {
			Thread.sleep(1000);
			driver.findElement(groupClearButton).click();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ElementNotInteractableException e) {
			driver.findElement(groupClearButton).click();
		}
	}

	public void clearCateEntries() {
		driver.findElement(category).click();
		try {
			Thread.sleep(1000);
			driver.findElement(cateClearButton).click();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}catch (ElementNotInteractableException e) {
			driver.findElement(cateClearButton).click();
		}
	}

	public void clickSaveButton() {
		driver.findElement(SaveQbankButton).click();
	}

	public void clickCancelButton() {
		driver.findElement(cancelButton).click();
	}

}
