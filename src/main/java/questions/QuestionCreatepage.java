package questions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import projectBase.Base;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class QuestionCreatepage {
	
	 private WebDriver driver;
	 private By questiontitle = By.id("questionTitle");
	 private By questionDescription = By.cssSelector("p");
	 private By questionMarks = By.id("question-marks");
	 private By correctOption = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[8]/div/div/div[1]/div/table/tbody/tr[1]/td[2]/span/span[1]/input");
	 private By createButton = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[10]/div[2]/button");
	 private By reactAlert = By.id("__react-alert__");
	 private By addOption = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[8]/div/div/div[2]/button");
	 private By deleteAnsweropBTN = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[8]/div/div/div[1]/div/table/tbody/tr[4]/td[4]/button");
	 private By deleteImageAnsweropBTN = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[8]/div/div/div[1]/div/table/tbody/tr[4]/td[5]/button");
	 private By previewQuestionTitletxt = By.xpath("/html/body/div[6]/div[3]/div/div/div[2]/div/div/div[1]");
	 //private By previewQuestionDesctxt = By.xpath("//*[@id=\"react-mathjax-preview\"]/div");
	 private By previewQuestionDesctxt = By.xpath("/html/body/div[6]/div[3]/div/div/div[2]/div/div/div[3]");
	 private By closePreviewbtn = By.xpath("/html/body/div[6]/div[3]/div/header/div/button[1]");
	 private By correctAnswer = By.xpath("//*[@id=\"correctAnswer\"]");
	 
	 public QuestionCreatepage(WebDriver driver) {
			this.driver = driver;
		} 
	 
	 public void selectCategory() throws InterruptedException {

		 WebDriverWait wait = new WebDriverWait(driver, 15);
		 Thread.sleep(2000);
	 	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("question-category-select")));
		 WebElement element = driver.findElement(By.id("question-category-select"));
	     element.click();
	      WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"menu-\"]/div[3]/ul")); 
	     
	      List<WebElement> options = dropdown.findElements(By.tagName("li")); 
	      for (WebElement option : options) { 
	      if (option.getText().equals("Automation category")) 
	      { 
	      option.click(); // click the desired option 
	      break; 
	      }
	      }
	 }
	 
	 public void selectgroup() {
		 
				WebElement group = driver.findElement(By.cssSelector(".MuiGrid-root:nth-child(2) .MuiButtonBase-root:nth-child(2) path:nth-child(1)"));
				group.click();
				driver.findElement(By.id("tags-outlined")).sendKeys("Automation group");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				driver.findElement(By.id("tags-outlined")).sendKeys(Keys.ARROW_DOWN);
			    driver.findElement(By.id("tags-outlined")).sendKeys(Keys.ENTER);
			} 
	 
	 public void selectQuestionType(String type) {
		 
		 WebDriverWait wait = new WebDriverWait(driver, 15);
	 	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("question-type-select")));
		 WebElement element = driver.findElement(By.id("question-type-select"));
	     element.click();
	      WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"menu-\"]/div[3]/ul")); 
	     
	      List<WebElement> options = dropdown.findElements(By.tagName("li")); 
	      for (WebElement option : options) { 
	      if (option.getText().equals(type)) 
	      { 
	      option.click(); // click the desired option 
	      break; 
	      }
	      }
		 
	 }
	 
	 public void selectDifficulty() {
		 
		 WebDriverWait wait = new WebDriverWait(driver, 15);
	 	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("question-difficulty-level")));
		 WebElement element = driver.findElement(By.id("question-difficulty-level"));
	     element.click();
	      WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"menu-\"]/div[3]/ul")); 
	     
	      List<WebElement> options = dropdown.findElements(By.tagName("li")); 
	      for (WebElement option : options) { 
	      if (option.getText().equals("Medium")) 
	      { 
	      option.click(); // click the desired option 
	      break; 
	      }
	      }
		 
	 }
	 
	 public void selectQuestionBank() {
		 
		 WebElement questionBank = driver.findElement(By.cssSelector(".MuiGrid-root:nth-child(1) > .MuiFormControl-root .MuiAutocomplete-endAdornment"));
		 questionBank.click();
			driver.findElement(By.cssSelector(".Mui-focused > #tags-outlined")).sendKeys("Automation QB");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.findElement(By.cssSelector(".Mui-focused > #tags-outlined")).sendKeys(Keys.ARROW_DOWN);
		    driver.findElement(By.cssSelector(".Mui-focused > #tags-outlined")).sendKeys(Keys.ENTER);
	 }
	 
	 
	 public void enterQuestiontitle(String title)
	 {
		 driver.findElement(questiontitle).sendKeys(title);
	 }
	 
	 public void enterQuestiondescription(String description) 
	 {
		 driver.findElement(questionDescription).sendKeys(description); 
	 }
	 
	 public void enterAnsweroptions(int count) {
		 int j=1;
		 for(int i=0;i<count;i++)
		 {
			 WebElement answerop = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[8]/div/div/div[1]/div/table/tbody/tr[" +j+ "]/td[3]/div[2]/div[2]/div/p"));
			 answerop.sendKeys("Answer option " +j);
			 j++;
		 }
		 
	 }
	 
	 public void uploadImageAnsweroption(int count) throws AWTException, InterruptedException {
		 
		 int j=1;
		 for(int i=0;i<count;i++)
		 {
			 WebElement answerimgop = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[8]/div/div/div[1]/div/table/tbody/tr["+j+"]/td[3]"));
			 answerimgop.click();
			 Thread.sleep(2000);
			 String stringpath=(System.getProperty("user.dir")+"\\src\\main\\resources\\Upload_img_options\\image "+j+".jpg");
			  
			   
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
			 j++;
		 }
		 
	 }
		 
	 
	 public void enterMark(String mark)
	 {
		WebElement Qmark = driver.findElement(questionMarks);
		Qmark.sendKeys(mark);
	 }
	 
	 public void selectCorrectoption()
	 {
		 driver.findElement(correctOption).click();
	 }
	 
	 public void enterCorrectAnswer() {
		 System.out.println("abc1");
		 driver.findElement(correctAnswer).sendKeys("This is a correct answer text");
	 }
	 
	 public void clickCreate()
	 {
		 driver.findElement(createButton).click();
	 }
	 
	 
	 public String getReactAlertMessage() throws InterruptedException {

			/*WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(reactAlert));
			  try { 
				  Thread.sleep(2000); 
				  } 
			  catch (InterruptedException e) 
			  {
				  e.printStackTrace(); 
			  }*/
		    Thread.sleep(2000);
			return driver.findElement(reactAlert).getText();
		}
	 
	 
	 public void addAnsweroption()
	 {
		 driver.findElement(addOption).click();
	 }
	 
	 
	 public void deleteAnsweroption() {
			
			driver.findElement(deleteAnsweropBTN).click();
		}
	 
	 public void deleteImageAnsweroption() {
		 
		 driver.findElement(deleteImageAnsweropBTN).click();
	 }
	 
	 public boolean checkifsuccessDisp() throws InterruptedException {
		 
		 Thread.sleep(1000);
		 String msg= driver.findElement(reactAlert).getText();
		 String SuccessMsg = "問題を正常に作成しました";
		 if(msg.equals(SuccessMsg)) {
			 return true;
		 }
		 else {
			 return false;
		 }
	 }
	 
	 public void clickPreviewbtn_Create() {
		 
		 WebDriverWait wait = new WebDriverWait(driver, 15);
		 driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[10]/div[3]/button")).click();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(previewQuestionTitletxt));
	 }
	 
	 public String getPreviewQuestiontitle() {	
		 
		 String Qtitle = driver.findElement(previewQuestionTitletxt).getText();
		 return(Qtitle);
		 
	 }
	 
	 public String getPreviewQuestionDesc() {
		 		 
		 String Qdesc = driver.findElement(previewQuestionDesctxt).getText();
		 return (Qdesc);
		 
		 
	 }
     
	 public String[] getPreviewAnsweroptions() {
		 
		 String[] strAnsweroptxt = new String[4];
		 int j=1;
		 String temptxt;
		 for(int i=0;i<4;i++) {
			 
			 temptxt = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div[2]/div/div/fieldset/div/div"+"["+j+"]")).getText();
			 strAnsweroptxt[i] = temptxt;
			 j++;
		 }
		 
		 return (strAnsweroptxt);
		 
	 }
	 
	 public String[] getInputTypesDisplayed() {
		 
		 String[] inputType = new String[4];
		 int j=1;
		 String temptxt;
		 for(int i=0;i<4;i++) {
			 
			 temptxt =driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div[2]/div/div/fieldset/div/div["+j+"]/div/div/span/span[1]/input")).getAttribute("type");
			 inputType[i] = temptxt;
			 j++;
		 }
		 
		 return (inputType);
		 
	 }
	 
	 public BufferedImage[] compareImageanswerOpt(String qtype) throws IOException, InterruptedException {
		 
		 BufferedImage[] actualImage = new BufferedImage[5];
		  WebDriver driver2;
		  driver2 = Base.chromeSetup();
		 int j=1;
		 for(int i=0;i<4;i++) {
			 Thread.sleep(2000);
			 String imgsrc = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div[2]/div/div/fieldset/div/div["+j+"]/div/img")).getAttribute("src");
			 driver2.get(imgsrc);
			 Thread.sleep(5000);
			 Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver2);
		     ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir")+"\\src\\main\\resources\\Actual_images\\"+qtype +"Actual image " +j+ ".png"));
		     actualImage[j] = ImageIO.read(new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Actual_images\\"+qtype+"Actual image " +j+ ".png"));
		     j++;		     
		 }
		 driver2.close();
		 
		 return actualImage;
	 }
	 
	 public BufferedImage[] compareImageanswerOptEDITpg(String qtype) throws IOException, InterruptedException {
		 
		 BufferedImage[] actualImage = new BufferedImage[5];
		  WebDriver driver2;
		  driver2 = Base.chromeSetup();
		 int j=1;
		 for(int i=0;i<4;i++) {
			 Thread.sleep(2000);
			 String imgsrc = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[8]/div/div/div[1]/div/table/tbody/tr["+j+"]/td[4]/div/div/div/img")).getAttribute("src");
			 driver2.get(imgsrc);
			 Thread.sleep(5000);
			 Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver2);
		     ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir")+"\\src\\main\\resources\\Actual_images\\"+qtype +"Actual image " +j+ ".png"));
		     actualImage[j] = ImageIO.read(new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Actual_images\\"+qtype+"Actual image " +j+ ".png"));
		     j++;		     
		 }
		 driver2.close();
		 
		 return actualImage;
	 }
	 
	 
	 public BufferedImage compareImgDescription() throws InterruptedException, IOException {
		 
		 BufferedImage actualImage;
		  WebDriver driver2;
		  driver2 = Base.chromeSetup();
		  Thread.sleep(2000);
			 String imgsrc = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div[2]/div/div/div[4]/div/img")).getAttribute("src");
			 driver2.get(imgsrc);
			 Thread.sleep(5000);
			 Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver2);
		     ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir")+"\\src\\main\\resources\\Actual_images\\Actual image desc.png"));
		     actualImage = ImageIO.read(new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Actual_images\\Actual image desc.png"));
		     driver2.close();
		     return actualImage;
	 }
	 
		 
	 public void clickClosepreview()
	 {
		 driver.findElement(closePreviewbtn).click();
	 }

	public void ifTextareaisDisplayed() {
		
		boolean textareaDisplayed = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div[2]/div/textarea")).isDisplayed();
		Assert.assertTrue(textareaDisplayed);		
	}
	
	public String ifUploadbtnDisplayed() {
		
		String role = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div[2]/div/fieldset/label")).getAttribute("role");
		return (role);
	}
		 
	public void uploadImgDesc() throws AWTException, InterruptedException {

		WebElement imgDesc = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[6]/div"));
		imgDesc.click();
		 String stringpath=(System.getProperty("user.dir")+"\\src\\main\\resources\\Upload_img_options\\japan-map.jpg");
		  
		   
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
		    
		     Thread.sleep(9000);
	}

	public String getPreviewTIQuestiontitle() {
		String title = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div[2]/div/div[1]")).getText();
		return title;
	}

	public String getPreviewTIQuestionDesc() {
		String Qdescription = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div[2]/div/div[3]")).getText();
		return Qdescription;
	}
      // For text image type
	public BufferedImage compareTIImgDescription() throws InterruptedException, IOException {
		
		 BufferedImage actualImage;
		  WebDriver driver2;
		  driver2 = Base.chromeSetup();
		  Thread.sleep(2000);
			 String imgsrc = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div[2]/div/div[4]/div/img")).getAttribute("src");
			 driver2.get(imgsrc);
			 Thread.sleep(5000);
			 Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver2);
		     ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir")+"\\src\\main\\resources\\Actual_images\\Actual image desc.png"));
		     actualImage = ImageIO.read(new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Actual_images\\Actual image desc.png"));
		     driver2.close();
		     return actualImage;
	}
	
	
	 
	 }
	 
	 

