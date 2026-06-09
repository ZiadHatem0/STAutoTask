package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();

    public static final String REPORT_PATH =
            System.getProperty("user.dir") + "/test-output/ExtentReport.html";

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Test Automation Report");
            sparkReporter.config().setReportName("Shopping Flow Tests");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Browser", "Chrome (Headless)");
            extent.setSystemInfo("Java", System.getProperty("java.version"));
        }
        return extent;
    }

    public static void setCurrentTest(ExtentTest test) {
        currentTest.set(test);
    }

    public static void log(Status status, String message) {
        ExtentTest test = currentTest.get();
        if (test != null) {
            test.log(status, message);
        }
    }

    public static void log(String message) {
        log(Status.INFO, message);
    }
}