package extentReports;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentManager {

	private static ExtentReports extent;
	private static String reportFileName = "WebExam-Automation-Report" + ".html";
	private static String fileSeperator = System.getProperty("file.separator");
	private static String reportFilepath = System.getProperty("user.dir") + fileSeperator + "TestReport";
	private static String reportFileLocation = reportFilepath + fileSeperator + reportFileName;

	public synchronized static ExtentReports getReporter() {
		String fileName = getReportPath(reportFilepath);
		ExtentSparkReporter spark = new ExtentSparkReporter(fileName);
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("WebExam-Automation");
		spark.config().setReportName("Regression Test Report");
		spark.config().setEncoding("utf-8");
		spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		spark.viewConfigurer().viewOrder().as(new ViewName[] { ViewName.DASHBOARD, ViewName.CATEGORY, ViewName.TEST,
				ViewName.DEVICE, ViewName.EXCEPTION, ViewName.LOG, ViewName.AUTHOR }).apply();
		if (extent == null) {
			extent = new ExtentReports();
			extent.attachReporter(spark);
		}
		return extent;
	}

	// Create the report path
	private static String getReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				System.out.println("Directory: " + path + " is created!");
				return reportFileLocation;
			} else {
				System.out.println("Failed to create directory: " + path);
				return System.getProperty("user.dir");
			}
		} else {
			System.out.println("Directory already exists: " + path);
		}
		return reportFileLocation;
	}

}
