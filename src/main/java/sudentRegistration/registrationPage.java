package sudentRegistration;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class registrationPage {

	private WebDriver driver;
	private By signupLink = By.xpath("//*[@id=\"root\"]/div/main/div[1]/form/div[3]/div[2]/a");
	private By firstName = By.name("firstName");
    private By lastName = By.id("last-name");
    private By emailId = By.id("email");
    private By password = By.name("password");
    private By confirmPassword = By.id("password-confirm");
    private By Signupbutton = By.xpath(" //*[@id=\"root\"]/div/main/div/div[1]/form/button/span[1]");
    private By reactAlert = By.id("__react-alert__");
    private By emailfielderror = By.xpath("//*[@id=\"email-helper-text\"]"); 
    private By pwdfielderror = By.xpath("//*[@id=\"root\"]/div/main/div/div[1]/form/div[4]/p");
    private By conpwdfielderror = By.xpath("//*[@id=\"password-confirm-helper-text\"]");
    private By dobfielderror =By.xpath("//*[@id=\"root\"]/div/main/div/div[1]/form/div[2]/div[3]/div/div/div/div/div/p");
    private By firstnameError = By.xpath("//*[@id=\"root\"]/div/main/div/div[1]/form/div[1]/div[1]/div/p");
    private By lastnameError = By.xpath("//*[@id=\"last-name-helper-text\"]");
    private By uploadimg = By.xpath("//*[@id=\"root\"]/div/main/div/div[1]/form/div[6]/label[1]/span[1]");
    private By backtologin = By.xpath("//*[@id=\"root\"]/div/main/div/div[1]/a");
    
    public registrationPage(WebDriver driver) {
		this.driver = driver;
	}
    
    public WebElement get_signupLink() {
    	return driver.findElement(signupLink);    	
    }
    
    public WebElement get_firstName() {
    	return driver.findElement(firstName);    	
    }
    
    public WebElement get_lastName() {
    	return driver.findElement(lastName);    	
    }
     
    
    public WebElement get_emailId() {
    	return driver.findElement(emailId);    	
    }
    
    public WebElement get_password() {
    	return driver.findElement(password);    	
    }
    
    public WebElement get_confirmPassword() {
    	return driver.findElement(confirmPassword);    	
    }
    
    public void selectorg() {
    	  
	      WebElement element = driver.findElement(By.id("mui-component-select-organizationId"));
	      element.click();
	      WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"menu-organizationId\"]/div[3]/ul")); 
	     
	      List<WebElement> options = dropdown.findElements(By.tagName("li")); 
	      for (WebElement option : options) { 
	      if (option.getText().equals("organization 2")) 
	      { 
	      option.click(); // click the desired option 
	      break; 
	      }
	      }  }
    	
   

    public void selectgroup() throws InterruptedException {
    	
    		  Thread.sleep(2000);
    	      WebElement element = driver.findElement(By.id("mui-component-select-gradeId"));
    	      element.click();
    	      Thread.sleep(2000);
    	      WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"menu-gradeId\"]/div[3]/ul")); 
    	     
    	      List<WebElement> options = dropdown.findElements(By.tagName("li")); 
    	      for (WebElement option : options) { 
    	      if (option.getText().equals("Iq students")) 
    	      { 
    	      option.click(); // click the desired option 
    	      break; 
    	      } }
    }
    
    public void selectdob() throws InterruptedException {
    	
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div[1]/form/div[2]/div[3]/div/div/div/div/div/div/div/button")).click();
    	Thread.sleep(1000);
        driver.findElement(By.cssSelector(".MuiButton-textPrimary:nth-child(2) > .MuiButton-label")).click();
    }
    
    public void clickSignup() {
    	
    	WebElement signbtn = driver.findElement(Signupbutton);
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signbtn);
    	signbtn.click();
    }
    

	public String getReactAlertMessage() {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(reactAlert));
		  try { 
			  Thread.sleep(2000); 
			  } 
		  catch (InterruptedException e) 
		  {
			  e.printStackTrace(); 
		  }
		return driver.findElement(reactAlert).getText();
	}
	
	public String getEmailfieldErrortxt() {
		
		return driver.findElement(emailfielderror).getText();
	
	}
	
	public String getsuccessmsg() throws InterruptedException {
		Thread.sleep(3000);
		return driver.findElement(reactAlert).getText();
	}

	public String getPasswordfieldErrortxt() throws InterruptedException {
		
		Thread.sleep(3000);
		return driver.findElement(pwdfielderror).getText();
	}

	public String getconPasswordfieldErrortxt() throws InterruptedException {
		
		Thread.sleep(3000);
		return driver.findElement(conpwdfielderror).getText();
	}

	public String getdobfielderror() throws InterruptedException {
		
		Thread.sleep(3000);
		return driver.findElement(dobfielderror).getText();
	}

	public String getfirstnameError() throws InterruptedException {
		
		Thread.sleep(1000);
		return driver.findElement(firstnameError).getText();
		
	}
	
    public String getlastnameError() throws InterruptedException {
		
    	Thread.sleep(1000);
		return driver.findElement(lastnameError).getText();
		
	}
    
   public void clickBacktoLogin() {
    	
    	WebElement bktologin = driver.findElement(backtologin);
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bktologin);
    	bktologin.click();
    }
   
   public boolean verifyLoginpage() throws InterruptedException {
	   
	   Thread.sleep(2000);
	   String Signintxt = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[1]/h1")).getText();//getting header text
	   
	   if(Signintxt.equals("サインイン")) {
		   return(true);
	   }else {
		   return(false);
	   }
	   
   }
   
   public void uploadimage(String path) throws AWTException, InterruptedException{
	   
	   driver.findElement(uploadimg).click();

	   String stringpath=(System.getProperty("user.dir")+"\\src\\main\\resources\\Registration_image\\")+(path);
	  
	   
	 //put path to your image in a clipboard
	      StringSelection ss = new StringSelection(stringpath);
	     Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

	     //imitate mouse events like ENTER, CTRL+C, CTRL+V
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
	     robot.keyRelease(KeyEvent.VK_ENTER);
	    
	     Thread.sleep(5000);
   }
   
   public String getImgerrormsg() throws InterruptedException {
	   
	   Thread.sleep(1000);
	   return driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div[1]/form/p")).getText();
	   
   }
   

}





