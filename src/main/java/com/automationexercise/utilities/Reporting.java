package com.automationexercise.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter implements ITestListener, ISuiteListener {
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	static String messageBody;

	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "AutomationExercise-Test-Report-" + timeStamp + ".html";
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\Report\\" + repName);
		try {
			htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project", "Automation System - Automation Exercise Services");
		extent.setSystemInfo("Automation Tester", "Masum Raza");
		extent.setSystemInfo("Organization", "Mannat Info Technologies Pvt Ltd");
		extent.setSystemInfo("Host name", "QA-Server-Testing-Environment");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		htmlReporter.config().setDocumentTitle("AutomationExercise Test Project");
		htmlReporter.config().setReportName("Functional-UI Test Automation Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		htmlReporter.config().setCss(".r-img { width: 50%; }");
		htmlReporter.config().setJs("document.title = 'Automation Exercise Report';");
	}

	public void onTestSuccess(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		String startTime = new SimpleDateFormat("HH:mm:ss").format(new Date(tr.getStartMillis()));
		logger.log(Status.INFO, "Test Start Time: " + startTime);
		String endTime = new SimpleDateFormat("HH:mm:ss").format(new Date(tr.getEndMillis()));
		logger.log(Status.INFO, "Test End Time: " + endTime);
		long duration = tr.getEndMillis() - tr.getStartMillis();
		logger.log(Status.INFO, "Test Duration: " + duration + " ms");
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		logger.log(Status.PASS, "Test Case Passed is " + tr.getName());
	}

	public void onTestFailure(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		logger.log(Status.FAIL, "Test Case Failed is " + tr.getName());
		logger.log(Status.FAIL, "Test Case Failed due to " + tr.getThrowable());
		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";
		File f = new File(screenshotPath);
		if (f.exists()) {
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
		}
	}

	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		String startTime = new SimpleDateFormat("HH:mm:ss").format(new Date(tr.getStartMillis()));
		logger.log(Status.INFO, "Test Start Time: " + startTime);
		String endTime = new SimpleDateFormat("HH:mm:ss").format(new Date(tr.getEndMillis()));
		logger.log(Status.INFO, "Test End Time: " + endTime);
		long duration = tr.getEndMillis() - tr.getStartMillis();
		logger.log(Status.INFO, "Test Duration: " + duration + " ms");
		if (tr.getThrowable() != null) {
			logger.log(Status.SKIP, "Reason for Skipping: " + tr.getThrowable().getMessage());
		}
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
		logger.log(Status.SKIP, "Test Case Skipped is " + tr.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.WARNING, MarkupHelper
				.createLabel(tr.getName() + " partially failed but within success percentage", ExtentColor.YELLOW));
		logger.log(Status.WARNING, "Test Case partially failed but within success percentage is " + tr.getName());
		logger.log(Status.WARNING, "Test Case partially failed due to " + tr.getThrowable());
		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";
		File f = new File(screenshotPath);
		if (f.exists()) {
			logger.warning("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
		}
	}

	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.setSystemInfo("Total Tests", String.valueOf(context.getAllTestMethods().length));
			extent.setSystemInfo("Passed Tests", String.valueOf(context.getPassedTests().size()));
			extent.setSystemInfo("Failed Tests", String.valueOf(context.getFailedTests().size()));
			extent.setSystemInfo("Skipped Tests", String.valueOf(context.getSkippedTests().size()));
			extent.flush();
		}
	}
}
