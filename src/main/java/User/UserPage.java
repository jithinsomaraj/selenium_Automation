package User;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserPage {
	private WebDriver driver;
	private By UserButton = By.cssSelector(".MuiList-padding.MuiList-root > div:nth-of-type(6)");
	private By UserPage = By.cssSelector(".MuiList-padding.MuiList-root > div:nth-of-type(6)");
	private By SearchBar = By.xpath(".//*[@id='titleSearch']");
	private By SearchResult = By.xpath("//td[contains(text(),'Lname')]");
	private By AddUserBtn = By.xpath("//span[contains(text(),'ユーザーを追加')]");
	private By AddUserTitle = By.xpath("//h2[text()='ユーザーを追加']");
	private By FirstName = By.xpath(".//*[@id='plannerLastName']");
	private By LastName = By.xpath(".//*[@id='plannerFirstName']");
	private By Email = By.xpath(".//*[@id='email']");
	private By Password = By.xpath(".//*[@id='password']");
	private By ConfirmPassword = By.xpath(".//*[@id='confirmPassword']");
	private By RoleDropDown = By.cssSelector("div#select-role");
	private By CreateUserBtn = By.xpath("//span[contains(text(),'ユーザーを作成')]");
	private By CancelButton = By.xpath("//span[contains(text(),'キャンセル')]");
	private By FirstNameValidtn = By.xpath("//p[contains(text(),'姓は 50 文字以内で入力してください')]");
	private By LastNameValidtn = By.xpath("//p[contains(text(),'名の長さは 50 文字以内にしてください')]");
	private By PasswordinvalidMsg = By.xpath("//p[contains(text(),'パスワードは 8 文字以上必要です')]");
	private By PasswordBlankMsg = By.xpath("//p[contains(text(),'パスワードが必要です')]");
	private By ConfirmPasswordBlankMsg = By.xpath("//p[contains(text(),'確認用パスワードが必要です')]");
	private By ConfirmPasswordmissMatchMsg = By.xpath("//p[contains(text(),'パスワードが一致しません')]");
	private By EmailValidtn = By.xpath("//p[contains(text(),'有効なEメールを入力してください')]");
	private By EditUserButton = By.xpath("(.//*[@focusable='false'])[13]");
	private By SendButton1 = By.xpath("//span[contains(text(),'送信')]");
	private By ChangePassButton = By.xpath("//span[contains(text(),'パスワード変更')]");
	private By UpdatePassword = By.xpath(".//*[@id='newPassword']");
	private By UpdateconfirmPassword = By.xpath(".//*[@id='confirmPassword']");
	private By DeleteButton = By.xpath("(.//*[@focusable='false'])[14]");
	private By DeleteConfYes = By.xpath("//span[contains(text(),'削除')]");

	public UserPage(WebDriver driver) {
		this.driver = driver;
	}

	public void ClickUserButton() {
		driver.findElement(UserButton).click();
	}

	public By UserPageBy() {
		return UserPage;
	}

	public By SearchBy() {
		return SearchBar;
	}

	public void ValidSearch(String Search) {
		driver.findElement(SearchBar).sendKeys(Search);
	}

	public boolean SearchResultVerify() {
		try {
			driver.findElement(SearchResult);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public void ClickAddUserButton() {
		driver.findElement(AddUserBtn).click();
	}

	public By AddUserTitleBy() {
		return AddUserTitle;

	}

	public void EnterFirstName(String Firstname) {
		driver.findElement(FirstName).sendKeys(Firstname);
	}

	public void EnterFirstNameJapanese() {
		driver.findElement(FirstName).sendKeys("ファーストネーム");
	}

	public void EnterFirstNameMaxzChara() {
		driver.findElement(FirstName)
				.sendKeys("abcdefghijklmnopqrstuvwxyzklmnopqrstuvwxyzklmnopqrstuvwxyzklmnopqrstuvwxyz");
	}

	public void EnterLastName(String Lastname) {
		driver.findElement(LastName).sendKeys(Lastname);
	}

	public void EnterLastNameMaxChara() {
		driver.findElement(LastName)
				.sendKeys("abcdefghijklmnopqrstuvwxyzklmnopqrstuvwxyzklmnopqrstuvwxyzklmnopqrstuvwxyz");
	}

	public void EnterLastNameJapanese() {
		driver.findElement(LastName).sendKeys("苗字");
	}

	public void EnterValidEmail(String email) {
		driver.findElement(Email).sendKeys(email);
	}

	public void EnterValidEmail1() {
		driver.findElement(Email).sendKeys("abcuser1@gmail.com");
	}

	public void EnterInvalidEmail() {
		driver.findElement(Email).sendKeys("abcusergmail.com");
	}

	public void EnterValidPassword(String Passwrd) {
		driver.findElement(Password).sendKeys(Passwrd);
	}

	public void EnterInvalidPassword() {
		driver.findElement(ConfirmPassword).sendKeys();
	}

	public void EnterValidConfirmPassword(String confPasswrd) {
		driver.findElement(ConfirmPassword).sendKeys(confPasswrd);
	}

	public void EnterDisMatchConfirmPassword() {
		driver.findElement(ConfirmPassword).sendKeys("Buy@33345");
	}

	public void ClickRoleDropDown() {
		driver.findElement(RoleDropDown).click();
	}

	public void selectRoleFromDropDown(String UserType) {
		List<WebElement> dd = driver
				.findElements(By.xpath("//ul[@Class='MuiList-root MuiMenu-list MuiList-padding']//li"));// Xpath of
		// dropdownlist
		for (int i = 0; i < dd.size(); i++) {
			WebElement RoleName = dd.get(i);
			String innerhtml = RoleName.getAttribute("innerText");
			System.out.println(innerhtml);
			if (innerhtml.contentEquals(UserType)) {
				RoleName.click();
				break;
			}
		}
	}

	public void ClickCreateUserBtn() {
		driver.findElement(CreateUserBtn).click();
	}

	public void ClickCancelButton() {
		driver.findElement(CancelButton).click();
	}

	public String getFirstNameValidtn() {
		String RT = driver.findElement(FirstNameValidtn).getText().trim();
		return RT;
	}

	public boolean isFirstNameValidtnPresent() {
		try {
			driver.findElement(FirstNameValidtn);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public String getLastNameValidtn() {
		String RT2 = driver.findElement(LastNameValidtn).getText().trim();
		return RT2;
	}

	public boolean isLastNameValidtnPresent() {
		try {
			driver.findElement(LastNameValidtn);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public String getPasswordinvalidMsg() {
		String RT3 = driver.findElement(PasswordinvalidMsg).getText().trim();
		return RT3;
	}

	public boolean isPasswordinvalidMsgPresent() {
		try {
			driver.findElement(PasswordinvalidMsg);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public String getPasswordBlankMsg() {
		String RT4 = driver.findElement(PasswordBlankMsg).getText().trim();
		return RT4;
	}

	public String getConfirmPasswordBlankMsg() {
		String RT5 = driver.findElement(ConfirmPasswordBlankMsg).getText().trim();
		return RT5;
	}

	public String getConfirmPasswordmissMatchMsg() {
		String RT6 = driver.findElement(ConfirmPasswordmissMatchMsg).getText().trim();
		return RT6;
	}

	public String getEmailValidtn() {
		String RT7 = driver.findElement(EmailValidtn).getText().trim();
		return RT7;
	}

	public boolean isEmailValidtnPresent() {
		try {
			driver.findElement(EmailValidtn);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public void ClickEditUserButton() {
		driver.findElement(EditUserButton).click();
	}

	public void ClickEditButton(String Fname) {
		String User = Fname;
		for (int i = 1; i < 20; i++) {
			String username = "tr:nth-of-type(" + i + ") > td:nth-of-type(1)";
			WebElement gt = driver.findElement(By.cssSelector(username));
			String a = gt.getText();
			if (a.equals(User)) {
				String EditBtn = "tr:nth-of-type(" + i
						+ ") > .MuiTableCell-alignCenter.MuiTableCell-body.MuiTableCell-root.jss80 > svg:nth-of-type(1)";
				WebElement gt1 = driver.findElement(By.cssSelector(EditBtn));
				gt1.click();
				break;

			}
		}
	}

	public void ClickDelButton(String UserFirstName) {
		String User = UserFirstName;
		for (int i = 1; i < 20; i++) {
			String username = "tr:nth-of-type(" + i + ") > td:nth-of-type(1)";
			WebElement gt = driver.findElement(By.cssSelector(username));
			String a = gt.getText();
			if (a.equals(User)) {
				String DelBtn = "tr:nth-of-type(" + i
						+ ") > .MuiTableCell-alignCenter.MuiTableCell-body.MuiTableCell-root.jss29 > svg:nth-of-type(2)";
				WebElement gt1 = driver.findElement(By.cssSelector(DelBtn));
				gt1.click();
				break;

			}
		}

	}

	public void UpdateFirstName(String edfirstName) {
		WebElement FName = driver.findElement(FirstName);
		FName.sendKeys(Keys.CONTROL + "a");
		FName.sendKeys(Keys.DELETE);
		FName.sendKeys(edfirstName);
	}

	public void UpdateLasttName(String edlastName) {
		WebElement FName = driver.findElement(LastName);
		FName.sendKeys(Keys.CONTROL + "a");
		FName.sendKeys(Keys.DELETE);
		FName.sendKeys(edlastName);
	}

	public void UpdateFirstNameJapanese() {
		WebElement FName = driver.findElement(FirstName);
		FName.sendKeys(Keys.CONTROL + "a");
		FName.sendKeys(Keys.DELETE);
		FName.sendKeys("名を更新");
	}

	public void UpdateLasttNamejapanese() {
		WebElement FName = driver.findElement(LastName);
		FName.sendKeys(Keys.CONTROL + "a");
		FName.sendKeys(Keys.DELETE);
		FName.sendKeys("姓を更新");
	}

	public void UpdateFirstNameMaxzChara() {
		driver.findElement(FirstName)
				.sendKeys("abcdefghijklmnopqrstuvwxyzklmnopqrstuvwxyzklmnopqrstuvwxyzklmnopqrstuvwxyz");
	}

	public void UpdateLastNameMaxChara() {
		driver.findElement(LastName)
				.sendKeys("abcdefghijklmnopqrstuvwxyzklmnopqrstuvwxyzklmnopqrstuvwxyzklmnopqrstuvwxyz");
	}

	public void ClickSendButton1() {
		driver.findElement(SendButton1).click();
	}

	public void ClickChangePassButton() {
		driver.findElement(ChangePassButton).click();
	}

	public void EnterValidUpdatePassword(String NewPass) {
		driver.findElement(UpdatePassword).sendKeys(NewPass);
	}

	public void EnterValidupdateConfirmPassword(String ConNewPass) {
		driver.findElement(UpdateconfirmPassword).sendKeys(ConNewPass);
	}

	public void EnterInvalidUpdatePassword() {
		driver.findElement(UpdatePassword).sendKeys("bu34555");
	}

	public void EnterDisMatchUpdateConfirmPassword() {
		driver.findElement(ConfirmPassword).sendKeys("Buy@33345");
	}

	public void ClickDeleteButton() {
		driver.findElement(DeleteButton).click();
	}

	public void ClickDeleteConfYes() {
		driver.findElement(DeleteConfYes).click();
	}

}
