package category;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryList {
	private WebDriver driver;
	int pagenumber;
	private By searchbox = By.id("search");
	private By categoryListIconinMenu = By.cssSelector(".MuiList-padding [tabindex='0']:nth-of-type(8)");
	private By categoryTable = By.cssSelector("table");
	private By nextPage = By.cssSelector("button[title='Next page']");
	private By categorylistRows = By.xpath("//table/tbody/tr/td[1]");
	private By EmptyTable = By.cssSelector(".MuiTableContainer-root div");
	private By categoryCreateButton = By.cssSelector(".MuiButton-label");
	private By createPopup = By.cssSelector(".MuiDialogContent-root");
	private By closeButton = By.cssSelector(".MuiBox-root [tabindex='0']:nth-of-type(2) .MuiButton-label");
	private By categoryName = By.id("name");
	private By categoryHelpText = By.cssSelector("p#name-helper-text");
	private By categoryDesc = By.cssSelector("textarea[placeholder='à–¾']");
	private By categoryDescHelpText = By.cssSelector("p#Description-helper-text");
	private By saveButton = By.cssSelector(".MuiBox-root [tabindex='0']:nth-of-type(1) .MuiButton-label");
	private By reactAlert = By.id("__react-alert__");
	private By reactClose = By.cssSelector("button > svg");
	private By deleteConfirmation = By.cssSelector(".MuiBox-root [tabindex='0']:nth-of-type(1) .MuiButton-label");

	public CategoryList(WebDriver driver) {
		this.driver = driver;
	}

	public void clickCategoryListInMenu() {
		driver.findElement(categoryListIconinMenu).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(categorylistRows));
	}

	public By getCategoryTableBy() {
		return categoryTable;
	}

	public By closeButtonBy() {
		return closeButton;
	}

	public List<String> search(String searchTerm, boolean isResult) {
		List<String> rowNames = new ArrayList<String>();
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		driver.findElement(searchbox).sendKeys(selectAll);
		driver.findElement(searchbox).sendKeys(Keys.DELETE);
		driver.findElement(searchbox).sendKeys(searchTerm);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (isResult) {
			rowNames = this.getCategoryListnamesTotal();
			driver.findElement(searchbox).sendKeys(selectAll);
			driver.findElement(searchbox).sendKeys(Keys.DELETE);
		} else {
			rowNames.add(driver.findElement(EmptyTable).getText());
			driver.findElement(searchbox).sendKeys(selectAll);
			driver.findElement(searchbox).sendKeys(Keys.DELETE);
		}
		return rowNames;
	}

	public List<String> getCategoryListnamesTotal() {
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
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(categorylistRows));
				List<WebElement> tableRows = driver.findElements(categorylistRows);
				int size = tableRows.size();
				for (int i = 1; i <= size; i++) {
					rowNames.add(driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]")).getText());
				}
			} catch (TimeoutException t) {
				rowNames.add(driver.findElement(EmptyTable).getText());
			}
			pagenumber++;
		} while (driver.findElement(nextPage).isEnabled());
		return rowNames;
	}

	public Boolean IsCategoryPresent(String name) {
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
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(categorylistRows));
			List<WebElement> tableRows = driver.findElements(categorylistRows);
			int size = tableRows.size();
			for (int i = 1; i <= size; i++) {
				if (driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]")).getText().equals(name)) {
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
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(categorylistRows));
		}
		return status;
	}

	public void NavigateToCategoryCreate() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		driver.findElement(categoryCreateButton).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(createPopup));
	}

	public void addCategory(String name, String description) {
		this.setTitle(name);
		this.setDesc(description);
		driver.findElement(saveButton).click();
	}

	public WebElement getTitle() {
		return driver.findElement(categoryName);
	}

	public void setTitle(String name) {
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		driver.findElement(categoryName).sendKeys(selectAll);
		driver.findElement(categoryName).sendKeys(Keys.DELETE);
		driver.findElement(categoryName).sendKeys(name);
	}

	public String getTitleError() {
		return driver.findElement(categoryHelpText).getText();
	}

	public void setDesc(String description) {
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		driver.findElement(categoryDesc).sendKeys(selectAll);
		driver.findElement(categoryDesc).sendKeys(Keys.DELETE);
		driver.findElement(categoryDesc).sendKeys(description);
	}

	public String getDescText() {
		return driver.findElement(categoryDesc).getText();
	}

	public String getDescError() {
		return driver.findElement(categoryDescHelpText).getText();
	}

	public void clickSaveButton() {
		driver.findElement(saveButton).click();
	}

	public void clickCloseButton() {
		driver.findElement(closeButton).click();
	}

	public void navigateToCategoryEdit(String name) {
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
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(categorylistRows));
			List<WebElement> tableRows = driver.findElements(categorylistRows);
			int size = tableRows.size();
			for (int i = 1; i <= size; i++) {
				if (driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]")).getText().equals(name)) {
					status = true;
					driver.findElement(By.cssSelector(".MuiTableBody-root .MuiTableRow-root:nth-of-type(" + i
							+ ") .MuiTableCell-alignCenter [focusable='false']:nth-of-type(1)")).click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(createPopup));
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
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(categorylistRows));
		}
	}

	public void deleteCategory(String name) {
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
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(categorylistRows));
			List<WebElement> tableRows = driver.findElements(categorylistRows);
			int size = tableRows.size();
			for (int i = 1; i <= size; i++) {
				if (driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]")).getText().equals(name)) {
					status = true;
					driver.findElement(By.cssSelector(".MuiTableBody-root .MuiTableRow-root:nth-of-type(" + i
							+ ") .MuiTableCell-alignCenter [focusable='false']:nth-of-type(2)")).click();
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
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(categorylistRows));
		}
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
}
