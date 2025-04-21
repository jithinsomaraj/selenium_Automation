package questions;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

public class Questionlistpage {
	
    private WebDriver driver;
 	private By questionbank_ttl = By.xpath ("//*[@id=\"root\"]/div/main/div[2]/h4");
 	private By questionsmenuicon = By.xpath(("//*[@id=\"root\"]/div/div/div/ul/div[2]")); 
 	private By questions_pagettl = By.xpath("//*[@id=\"root\"]/div/main/div[2]/h4");
 	private By questioncreatebtn = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/div[1]/a/button");
    private By editbutton = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[2]/table/tbody/tr[1]/td[7]/a");
    private By previewbutton = By.cssSelector(".MuiTableRow-root:nth-child(1) > .MuiTableCell-root:nth-child(7) path");
  //*[@id="root"]/div/main/div[2]/div[2]/table/tbody/tr[1]/td[6]/svg/path

 	
 	
 	 public Questionlistpage(WebDriver driver) {
			this.driver = driver;
		} 
 	 
 	
 	public void checkLogin() {
 		
 		WebDriverWait wait = new WebDriverWait(driver, 15);
 		wait.until(ExpectedConditions.visibilityOfElementLocated(questionbank_ttl));
 		String questionbanktitle = driver.findElement(questionbank_ttl).getText();
 		Assert.assertEquals(questionbanktitle, "問題集リスト", "Page title does'nt match");

 	}
 	
 	public void navigate_questionsPage() {

 		WebDriverWait wait = new WebDriverWait(driver, 15);
 		driver.findElement(questionsmenuicon).click();
 		wait.until(ExpectedConditions.visibilityOfElementLocated(questions_pagettl));
 		String questionspagetitle = driver.findElement(questions_pagettl).getText();
 		Assert.assertEquals(questionspagetitle, "問題リスト", "Page title does'nt match");

 	}


	public void clickCreate() {
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		driver.findElement(questioncreatebtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(questions_pagettl));
 		String questionspagetitle = driver.findElement(questions_pagettl).getText();
 		Assert.assertEquals(questionspagetitle, "問題を作成", "Page title does'nt match");
		
	}
	
	public void checkifListpage() {
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(questions_pagettl));
 		String questionspagetitle = driver.findElement(questions_pagettl).getText();
 		Assert.assertEquals(questionspagetitle, "問題リスト", "Page title does'nt match");
	}
	
	public void createQuestion(String qtype) throws InterruptedException, AWTException {
		
		 QuestionCreatepage createpageobj2 = new QuestionCreatepage(driver);		    
			createpageobj2.selectCategory();
			createpageobj2.selectgroup();
			Thread.sleep(2000);
			createpageobj2.selectQuestionType(qtype);
			createpageobj2.selectDifficulty();
			createpageobj2.selectQuestionBank();
			createpageobj2.enterQuestiontitle("This is Create Question Title");
			createpageobj2.enterQuestiondescription("This is Create Question description");
			createpageobj2.enterMark("5");
			
			if ((qtype == "Single Answer")||(qtype =="Multiple Answer")) {
				createpageobj2.enterAnsweroptions(4);
				createpageobj2.selectCorrectoption();
			}
			
			if ((qtype == "Single Answer Image")||(qtype =="Multiple Answer Image")) {
				createpageobj2.uploadImageAnsweroption(4);
				createpageobj2.selectCorrectoption();
			}
			if(qtype == "Text Input") {
				createpageobj2.enterCorrectAnswer();
			}
			
			createpageobj2.clickCreate();
			
	}
	
	public void clickEdit() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(editbutton));
		driver.findElement(editbutton).click();
	}

	public void clickPreview() {
	
		driver.findElement(previewbutton).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clickDelete() {
		
		driver.findElement(By.cssSelector(".MuiTableRow-root:nth-child(1) .MuiSvgIcon-root:nth-child(2) > path")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("/html/body/div[4]/div[3]/div/div[2]/form/div/button[1]")).click();		
	}
	
	public void shareQuestion() {
		
		driver.findElement(By.cssSelector(".MuiTableRow-root:nth-child(1) .MuiSvgIcon-root:nth-child(3) > path")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement user = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div/div/form/div[1]/div/div/div/div"));
		user.click();
		driver.findElement(By.id("tags-outlined")).sendKeys("Support WE");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("tags-outlined")).sendKeys(Keys.ARROW_DOWN);
	    driver.findElement(By.id("tags-outlined")).sendKeys(Keys.ENTER);
	    driver.findElement(By.xpath("/html/body/div[4]/div[3]/div/div/form/div[2]/button[2]")).click();
	    
	}
	
	
    public String getPreviewQuestiontitle() {	
		 
		 String Qtitle = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/div[1]/h6")).getText();
		 
		 return(Qtitle);
		 
	 }
	 
	 public String getPreviewQuestionDesc() {
		 		 
		 String Qdesc = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/div[3]")).getText();
		
		 return (Qdesc);
		 
		 
	 }
     
	 public String[] getPreviewAnsweroptions() {
		 
		 String[] strAnsweroptxt = new String[4];
		 int j=1;
		 String temptxt;
		 for(int i=0;i<4;i++) {
			 
			 temptxt = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/fieldset/div/div"+"["+j+"]")).getText();
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
			 
			 temptxt =driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/fieldset/div/div["+j+"]/div/div/span/span[1]/input")).getAttribute("type");
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
			 String imgsrc = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/fieldset/div/div["+j+"]/div/img")).getAttribute("src");
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

	 public void clickClosepreview()
	 {
		 driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/header/div/button[1]")).click();
	 }
	 
	 public String getPreviewTIQuestiontitle() {
			String title = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div[1]")).getText();
			return title;
		}

	 public String getPreviewTIQuestionDesc() {
			String Qdescription = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div[3]")).getText();
			return Qdescription;
		}
		
	 public void ifTextareaisDisplayed() {
			
			boolean textareaDisplayed = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/textarea")).isDisplayed();
			Assert.assertTrue(textareaDisplayed);		
		}
		
	 public String ifUploadbtnDisplayed() {
			
			String role = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/fieldset/label")).getAttribute("role");
			return (role);
		}
}
