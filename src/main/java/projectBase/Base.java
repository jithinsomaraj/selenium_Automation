package projectBase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utils.ReadExcel;

public class Base {
	public static final String adminUrl = "https://dev.shiken.online/admin/";
	public static final String studentUrl = "https://dev.shiken.online/";
	private static WebDriver driver;
	private static final String TestDataPath = "./src/main/resources/ExcelFiles/WebExam Test Data.xlsx";

	public static WebDriver getDriver() {
		return driver;
	}

	@BeforeSuite
	@Parameters("browser")
	public void initialSetup(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/Drivers/Chromedriver/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./src/main/resources/Drivers/Firefoxdriver/geckodriver.exe");
			driver = new FirefoxDriver();
		}
	}

	@AfterSuite
	public void exitRun() {
		driver.quit();
	}

	public static WebDriver chromeSetup() {
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/Drivers/Chromedriver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		// options.addArguments("--incognito");
		driver = new ChromeDriver(options);
		return driver;
	}

	public static WebDriver firefoxSetup() {
		System.setProperty("webdriver.gecko.driver", "./src/main/resources/Drivers/Firefoxdriver/geckodriver.exe");
		driver = new FirefoxDriver();
		return driver;
	}

	public static void loadadminUrl(WebDriver driver) {
		driver.get(adminUrl);
	}

	public static void loadstudentUrl(WebDriver driver) {
		driver.get(studentUrl);
	}
	
	public static String getTestDataPath() {
		return TestDataPath;
	}

}
