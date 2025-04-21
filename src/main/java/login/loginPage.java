package login;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class loginPage {
	private WebDriver driver;
	private By emailfield = By.id("email");
	private By passwordfield = By.id("password");
	private By loginBtn = By.cssSelector(".MuiButton-label");
	private By reactAlert = By.id("__react-alert__");
	private By reactClose = By.cssSelector("button > svg");
	private By forgotPasswordLink = By.cssSelector(".MuiLink-root.MuiTypography-body2.MuiTypography-colorPrimary.MuiTypography-root");
	private By forgotPasswordEmailField = By.id("email");
	private By forgotPasswordSubmitBtn = By.className("MuiButton-label");
	private By returnToAdminLink = By.xpath("//a[@href='/admin/']");
	

	public loginPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getEmail() {
		return driver.findElement(emailfield);
	}

	public WebElement getPassword() {
		return driver.findElement(passwordfield);
	}

	public void clickloginbtn() {
		driver.findElement(loginBtn).click();
	}
	
	public By loginbtnBy() {
		return loginBtn;
	}
	
	public void clickForgotPasswordLink() {
		driver.findElement(forgotPasswordLink).click();
	}
	
	public WebElement getForgotPasswordEmail() {
		return driver.findElement(forgotPasswordEmailField);
	}
	
	public void submitForgotPassword() {
		driver.findElement(forgotPasswordSubmitBtn).click();
	}
	
	public void returntoLoginPage() {
		driver.findElement(returnToAdminLink).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPasswordLink));
		
	}

	public void login(String email, String password) {
		this.getEmail().sendKeys(email);
		this.getPassword().sendKeys(password);
		this.clickloginbtn();
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
	
	public void forgotPassword(String email) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		this.clickForgotPasswordLink();
		wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPasswordEmailField));
		this.getForgotPasswordEmail().sendKeys(email);
		this.submitForgotPassword();	
	}
}
