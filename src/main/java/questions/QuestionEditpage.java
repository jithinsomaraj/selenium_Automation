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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.inject.Key;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class QuestionEditpage {
	
	 private WebDriver driver;
	 private By questiontitle = By.xpath("//*[@id=\"questionTitle\"]");
	 private By questionDescription = By.cssSelector("p");
	 private By questionMarks = By.id("question-marks");
	 private By correctOption = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[8]/div/div/div[1]/div/table/tbody/tr[1]/td[2]/span/span[1]/input");
	 private By updateButton = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[10]/div[2]/button");
	 private By reactAlert = By.id("__react-alert__");
	 private By addOption = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[8]/div/div/div[2]/button");
	 private By deleteAnsweropBTN = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[8]/div/div/div[1]/div/table/tbody/tr[4]/td[4]/button");
	 private By deleteImageAnsweropBTN = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[8]/div/div/div[1]/div/table/tbody/tr[4]/td[5]/button");
	 private By previewQuestionTitletxt = By.xpath("/html/body/div[6]/div[3]/div/div/div[2]/div/div/div[1]");
	 private By previewQuestionDesctxt = By.xpath("/html/body/div[6]/div[3]/div/div/div[2]/div/div/div[3]");
	 private By closePreviewbtn = By.xpath("/html/body/div[6]/div[3]/div/header/div/button[1]");
	 private By correctAnswer = By.xpath("//*[@id=\"correctAnswer\"]");
	 private By groupfieldError = By.xpath("//*[@id=\"tags-outlined-helper-text\"]");
	 
	 
	 
	 public QuestionEditpage(WebDriver driver) {
			this.driver = driver;
		} 
	 
	 public void checkEditpage(String qtype) throws IOException, InterruptedException {
		 
		 String[] optxt = new String [5]; 		 
		 String cattext = driver.findElement(By.id("question-category-select")).getText();
		 String grouptxt = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[1]/div[2]/div/div/div/div/div[1]")).getText();	
		 String questionType = driver.findElement(By.id("question-type-select")).getText();
		 String difficulty = driver.findElement(By.id("question-difficulty-level")).getText();
		 String questionBanktxt = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[3]/div/div/div/div/div/div[1]")).getText();
		 String Qtitle = driver.findElement(questiontitle).getAttribute("value");
		 String Qdesc = driver.findElement(questionDescription).getText();
		 String marks = driver.findElement(questionMarks).getAttribute("value");
		 int j=1;
		 String tmptext;
		 if ((qtype == "Single Answer")||(qtype == "Multiple Answer")) {
		 for(int i=0;i<4;i++) {
			 tmptext = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[8]/div/div/div[1]/div/table/tbody/tr[" +j+ "]/td[3]/div[2]/div[2]/div/p")).getText();
			 optxt[j] = tmptext;
			 Assert.assertEquals(optxt[j],"Answer option "+j, "Expected text not displayed");
			 j++;
		 }
		 }
		 /* BufferedImage[] actualanswerImage = new BufferedImage[5];
		 BufferedImage[] expectedImage = new BufferedImage[5];
		 if ((qtype == "Single Answer Image")||(qtype == "Multiple Answer Image")) {
			 QuestionCreatepage createpageobj = new QuestionCreatepage(driver);
			 actualanswerImage = createpageobj.compareImageanswerOptEDITpg(qtype+"2 ");	
				
			 int k=1;
			 for(int i=0;i<4;i++) {
				 
				 expectedImage[k] = ImageIO.read(new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Expected_images\\Expected Edit " +k+ ".png"));
				 ImageDiffer imgDiff = new ImageDiffer();
			     ImageDiff diff = imgDiff.makeDiff(actualanswerImage[k], expectedImage[k]);
			     Assert.assertFalse(diff.hasDiff(),"Images are not Same");
			     k++;
			 }
		 }*/
		 
		 Assert.assertEquals(cattext,"Automation category", "Expected text not displayed");
		 Assert.assertEquals(grouptxt,"Automation group", "Expected text not displayed");
		 Assert.assertEquals(questionType,qtype, "Expected text not displayed");
		 Assert.assertEquals(difficulty,"Medium", "Expected text not displayed");
		 Assert.assertEquals(questionBanktxt,"Automation QB", "Expected text not displayed");
		 Assert.assertEquals(Qtitle,"This is Create Question Title", "Expected text not displayed");
		 Assert.assertEquals(Qdesc,"This is Create Question description", "Expected text not displayed");
		 Assert.assertEquals(marks,"5", "Expected text not displayed");		 
	 }
	 
	 
	 public void changeCategory() throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, 15);
		 Thread.sleep(3000);
	 	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("question-category-select")));
		 WebElement element = driver.findElement(By.id("question-category-select"));
	     element.click();
	      WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"menu-\"]/div[3]/ul")); 
	     
	      List<WebElement> options = dropdown.findElements(By.tagName("li")); 
	      for (WebElement option : options) { 
	      if (option.getText().equals("Automation category 2")) 
	      { 
	      option.click(); // click the desired option 
	      break; 
	      }
	      }
	 
	 }
	 
	 public void changeGroup() {
		 
			WebElement group = driver.findElement(By.cssSelector(".MuiGrid-root:nth-child(2) .MuiButtonBase-root:nth-child(2) path:nth-child(1)"));
			group.click();
			driver.findElement(By.id("tags-outlined")).sendKeys("Automation group 2");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.findElement(By.id("tags-outlined")).sendKeys(Keys.ARROW_DOWN);
		    driver.findElement(By.id("tags-outlined")).sendKeys(Keys.ENTER);
		} 

	 public void changeQuestionType(String type) {
	 
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

	 public void changeDifficulty() {
	 
		 WebDriverWait wait = new WebDriverWait(driver, 15);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("question-difficulty-level")));
		 WebElement element = driver.findElement(By.id("question-difficulty-level"));
		 element.click();
		 WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"menu-\"]/div[3]/ul")); 
  
		 List<WebElement> options = dropdown.findElements(By.tagName("li")); 
		 for (WebElement option : options) { 
			 if (option.getText().equals("Hard")) 
			 { 
				 option.click(); // click the desired option 
				 break; 
			 }
		 }
	 
	 }
	 
	 
	 public void changeQuestionBank() {
		 
		 WebElement questionBank = driver.findElement(By.cssSelector(".MuiGrid-root:nth-child(1) > .MuiFormControl-root .MuiAutocomplete-endAdornment"));
		 questionBank.click();
			driver.findElement(By.cssSelector(".Mui-focused > #tags-outlined")).sendKeys("Automation QB 2");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.findElement(By.cssSelector(".Mui-focused > #tags-outlined")).sendKeys(Keys.ARROW_DOWN);
		    driver.findElement(By.cssSelector(".Mui-focused > #tags-outlined")).sendKeys(Keys.ENTER);
	 }
	 
	 public void changeQuestiontitle(String title)
	 {   
		 WebElement toClear = driver.findElement(questiontitle);
		 toClear.sendKeys(Keys.CONTROL + "a");
		 toClear.sendKeys(Keys.DELETE);
		 driver.findElement(questiontitle).sendKeys(title);
	 }
	 
	 public void changeQuestiondescription(String description) 
	 {
		 WebElement toClear = driver.findElement(questionDescription);
		 toClear.sendKeys(Keys.CONTROL + "a");
		 toClear.sendKeys(Keys.DELETE);
		 driver.findElement(questionDescription).sendKeys(description); 
	 }
	 
	 public void changeMark(String mark)
	 {
		WebElement toClear = driver.findElement(questionMarks);
		toClear.sendKeys(Keys.CONTROL + "a");
		toClear.sendKeys(Keys.DELETE); 
		WebElement Qmark = driver.findElement(questionMarks);
		Qmark.sendKeys(mark);
	 }
	 
	 public void changeAnsweroptions(int count) {
		 int j=1;
		 for(int i=0;i<count;i++)
		 {   
			 WebElement toClear = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[8]/div/div/div[1]/div/table/tbody/tr[" +j+ "]/td[3]/div[2]/div[2]/div/p"));
			 toClear.sendKeys(Keys.CONTROL + "a");
			 toClear.sendKeys(Keys.DELETE);
			 WebElement answerop = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[8]/div/div/div[1]/div/table/tbody/tr[" +j+ "]/td[3]/div[2]/div[2]/div/p"));
			 answerop.sendKeys("Edited Answer option " +j);
			 j++;
		 }
		 
	 }
	 
	 public void addAnsweroption()
	 {
		 driver.findElement(addOption).click();
	 }
	 
	 public void deleteAnsweroption() {
			
			driver.findElement(deleteAnsweropBTN).click();
		}
	 
	 public void clickUpdate()
	 {
		 driver.findElement(updateButton).click();
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
	 
	 public void clearGroup() {
		 
		 driver.findElement(By.cssSelector(".MuiGrid-root:nth-child(2) .MuiButtonBase-root:nth-child(2) path:nth-child(1)")).click();
		 WebElement groupclear = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[1]/div[2]/div/div/div/div/div[2]/button[1]"));
		 groupclear.click();
		 driver.findElement(questiontitle).click();
		 
	 }
	 
	 public String getGrouperror() throws InterruptedException {
		 Thread.sleep(2000);
		 
		 String errorMsg = driver.findElement(groupfieldError).getText();
		 return errorMsg;
	 }
	 
	 public void clearTitle() {
		 WebElement toClear = driver.findElement(questiontitle);
		 toClear.sendKeys(Keys.CONTROL + "a");
		 toClear.sendKeys(Keys.DELETE);		 
	 }
	 
	 public String getTitleError() {
		 
		 String error = driver.findElement(By.xpath("//*[@id=\"questionTitle-helper-text\"]")).getText();
		 return error;
	 }
	 
	 public void clearMarks() {
		 WebElement toClear = driver.findElement(questionMarks);
		 toClear.sendKeys(Keys.CONTROL + "a");
		 toClear.sendKeys(Keys.DELETE); 
	 }
	 
	 public String getMarkerror() {
		 String error = driver.findElement(By.xpath("//*[@id=\"question-marks-helper-text\"]")).getText();
		 return error;
	 }
	 
	 public void clearAnswerop() {
		 WebElement toClear = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[8]/div/div/div[1]/div/table/tbody/tr[1]/td[3]/div[2]/div[2]/div/p"));
		 toClear.sendKeys(Keys.CONTROL + "a");
		 toClear.sendKeys(Keys.DELETE);
	 }
	 
	 public void changeImageAnsweroption() throws AWTException, InterruptedException {
		 
		
			 WebElement answerimgop = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/form/div[8]/div/div/div[1]/div/table/tbody/tr[1]/td[3]"));
			 answerimgop.click();
			 Thread.sleep(2000);
			 String stringpath=(System.getProperty("user.dir")+"\\src\\main\\resources\\Upload_img_options\\image 5.jpg");
			  
			   
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

	 public void editCorrectAnswer() {
		 WebElement toClear = driver.findElement(correctAnswer);
		 toClear.sendKeys(Keys.CONTROL + "a");
		 toClear.sendKeys(Keys.DELETE);
	 driver.findElement(correctAnswer).sendKeys("This is a edited correct answer text");
	 }
	 
	 public void clearCorrectAnswer() {
		 WebElement toClear = driver.findElement(correctAnswer);
		 toClear.sendKeys(Keys.CONTROL + "a");
		 toClear.sendKeys(Keys.DELETE);
	 }

	
		
		
	

}
